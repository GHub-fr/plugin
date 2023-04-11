package ghub.fr.main;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import ghub.fr.chat.playerChat;
import ghub.fr.commands.admin.removePlayerAttribute;
import ghub.fr.commands.admin.entity.*;
import ghub.fr.commands.admin.playersStorage.bonus;
import ghub.fr.commands.admin.playersStorage.security;
import ghub.fr.commands.admin.playersStorage.tag;
import ghub.fr.commands.admin.worldsRelated.*;
import ghub.fr.commands.api.commandLimiter;
import ghub.fr.commands.api.tabCompleterHidder;
import ghub.fr.commands.api.test;
import ghub.fr.commands.player.*;
import ghub.fr.discord.discordMain;
import ghub.fr.discord.eventsMinecraft;
import ghub.fr.discord.messages;
import ghub.fr.entity.clickNPC;
import ghub.fr.menu.api.customInventoryClick;
import ghub.fr.menu.api.persistentDataClick;
import ghub.fr.menu.compass.compassEvents;
import ghub.fr.menu.island.islandEvents;
import ghub.fr.menu.island.islandHeadsEvents;
import ghub.fr.menu.island.bonus.bonusEvents;
import ghub.fr.menu.profile.profileEvents;
import ghub.fr.menu.serverSelector.serverSelectorEvents;
import ghub.fr.menu.shop.classique.shopEvents;
import ghub.fr.menu.shop.specials.specialsMenuEvents;
import ghub.fr.menu.shop.specials.specialsUseEvents;
import ghub.fr.menu.shop.specials.bonus.joinQuitMessage;
import ghub.fr.menu.shop.specials.bonus.teleportationAura;
import ghub.fr.menu.voyage.voyageEvents;
import ghub.fr.player.*;
import ghub.fr.system.BungeePluginMessageListener;
import ghub.fr.system.MOTD;
import ghub.fr.system.ServerBootFile;
import ghub.fr.world.api.BuildRules;
import ghub.fr.world.api.entityGriefing;
import ghub.fr.world.api.fire;
import ghub.fr.world.api.voidWorld;
import ghub.fr.world.api.worldManager;
import ghub.fr.world.spawn.joinAtSpawn;
import ghub.fr.world.spawn.limiter;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.logging.Logger;

public class main extends JavaPlugin implements PluginMessageListener {
    private static final Logger logger = Bukkit.getLogger();

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new voidWorld();
    }

    @Override
    public void onLoad() {
        logger.info("Plugin Loaded");
        try {
            ServerBootFile.loadServerTypeFile();
            logger.info(ServerBootFile.getServerType().toString());
        } catch (Exception e) {
        }
    }

    @Override
    public void onEnable() {
        logger.info("Plugin Enabled");
        try {
            discordMain.startBot();
        } catch (Exception e) {
        }
        Bukkit.getPluginManager().registerEvents(new eventsMinecraft(), this);
        registerEvents();
        registerCommands();
        lib.hook();
        scheduler.start();
        if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.SkyBlock)) {
            SkyBlockEventsCommands();
        } else if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.RPG)) {
            Bukkit.getPluginManager().registerEvents(new xpBonus(), this);
        }
        /*
        
         */
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord",
                new BungeePluginMessageListener());
        /*
        
         */
        // load spawn on server load
    }

    @Override
    public void onDisable() {
        logger.info("Plugin Disabled");
        try {
            messages.botStop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*
         */
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
        this.getServer().getMessenger().unregisterIncomingPluginChannel(this);
        /*
         */
    }

    public void SkyBlockEventsCommands() {
        Bukkit.getPluginCommand("island").setExecutor(new island());
        Bukkit.getPluginManager().registerEvents(new islandEvents(), this);
        Bukkit.getPluginManager().registerEvents(new islandHeadsEvents(), this);
        Bukkit.getPluginManager().registerEvents(new bonusEvents(), this);
        Bukkit.getPluginManager().registerEvents(new ghub.fr.world.islandEvents(), this);
    }

    public void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new customInventoryClick(), this);
        Bukkit.getPluginManager().registerEvents(new serverSelectorEvents(), this);
        Bukkit.getPluginManager().registerEvents(new compassEvents(), this);
        Bukkit.getPluginManager().registerEvents(new persistentDataClick(), this);
        Bukkit.getPluginManager().registerEvents(new playerFirstJoin(), this);
        Bukkit.getPluginManager().registerEvents(new playerPreJoin(), this);
        Bukkit.getPluginManager().registerEvents(new limiter(), this);
        Bukkit.getPluginManager().registerEvents(new joinAtSpawn(), this);
        Bukkit.getPluginManager().registerEvents(new entityGriefing(), this);
        Bukkit.getPluginManager().registerEvents(new MOTD(), this);
        Bukkit.getPluginManager().registerEvents(new teleportationAura(), this);
        Bukkit.getPluginManager().registerEvents(new joinQuitMessage(), this);
        Bukkit.getPluginManager().registerEvents(new playerChat(), this);
        Bukkit.getPluginManager().registerEvents(new death(), this);
        Bukkit.getPluginManager().registerEvents(new worldManager(), this);
        Bukkit.getPluginManager().registerEvents(new fire(), this);
        Bukkit.getPluginManager().registerEvents(new commandLimiter(), this);
        Bukkit.getPluginManager().registerEvents(new tabCompleterHidder(), this);
        Bukkit.getPluginManager().registerEvents(new anvilUnlimited(), this);
        Bukkit.getPluginManager().registerEvents(new clickNPC(), this);
        Bukkit.getPluginManager().registerEvents(new shopEvents(), this);
        Bukkit.getPluginManager().registerEvents(new specialsUseEvents(), this);
        Bukkit.getPluginManager().registerEvents(new specialsMenuEvents(), this);
        Bukkit.getPluginManager().registerEvents(new BuildRules(), this);
        Bukkit.getPluginManager().registerEvents(new voyageEvents(), this);
        Bukkit.getPluginManager().registerEvents(new profileEvents(), this);
    }

    public void registerCommands() {
        registerAdminCommands();
        registerUserCommands();
    }

    public void registerAdminCommands() {
        Bukkit.getPluginCommand("test").setExecutor(new test());
        Bukkit.getPluginCommand("createworld").setExecutor(new worldCreate());
        Bukkit.getPluginCommand("unloadworld").setExecutor(new worldUnload());
        Bukkit.getPluginCommand("deleteworld").setExecutor(new worldDelete());
        Bukkit.getPluginCommand("setspawn").setExecutor(new setSpawn());
        Bukkit.getPluginCommand("bonus").setExecutor(new bonus());
        Bukkit.getPluginCommand("tag").setExecutor(new tag());
        Bukkit.getPluginCommand("security").setExecutor(new security());
        Bukkit.getPluginCommand("removeplayerattribute").setExecutor(new removePlayerAttribute());
        Bukkit.getPluginCommand("npc").setExecutor(new npc());
        Bukkit.getPluginCommand("armorstand").setExecutor(new armorStand());
        Bukkit.getPluginCommand("setspawner").setExecutor(new setSpawner());
        Bukkit.getPluginCommand("structuresave").setExecutor(new structureSave());
        Bukkit.getPluginCommand("structureset").setExecutor(new structureSet());
        Bukkit.getPluginCommand("structurelocate").setExecutor(new structureLocate());
        Bukkit.getPluginCommand("settag").setExecutor(new setTag());
        Bukkit.getPluginCommand("summon").setExecutor(new summon());
    }

    public void registerUserCommands() {
        Bukkit.getPluginCommand("spawn").setExecutor(new spawn());
        Bukkit.getPluginCommand("help").setExecutor(new help());
        Bukkit.getPluginCommand("commandes").setExecutor(new commandes());
        Bukkit.getPluginCommand("menu").setExecutor(new menu());
        Bukkit.getPluginCommand("profile").setExecutor(new profile());
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();
        /*
         * if (subchannel.equals("SomeSubChannel"))
         * {
         * // Use the code sample in the 'Response' sections below to read
         * // the data.
         * }
         */
    }
}