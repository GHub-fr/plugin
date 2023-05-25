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
import ghub.fr.enchantements.instantmine;
import ghub.fr.entity.clickNPC;
import ghub.fr.menu.api.customInventoryClick;
import ghub.fr.menu.api.persistentDataClick;
import ghub.fr.menu.compass.compassEvents;
import ghub.fr.menu.island.islandEvents;
import ghub.fr.menu.island.islandHeadsEvents;
import ghub.fr.menu.island.bonus.bonusEvents;
import ghub.fr.menu.prison.minesEvents;
import ghub.fr.menu.prison.pickaxeEvents;
import ghub.fr.menu.profil.profilEvents;
import ghub.fr.menu.serverSelector.serverSelectorEvents;
import ghub.fr.menu.settings.settingsEvents;
import ghub.fr.menu.shop.classique.shopEvents;
import ghub.fr.menu.shop.specials.specialsMenuEvents;
import ghub.fr.menu.shop.specials.specialsUseEvents;
import ghub.fr.menu.shop.specials.bonus.joinQuitMessage;
import ghub.fr.menu.shop.specials.bonus.teleportationAura;
import ghub.fr.menu.voyage.voyageEvents;
import ghub.fr.player.*;
import ghub.fr.system.BossBarMessage;
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
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.logging.Logger;

public class main extends JavaPlugin implements PluginMessageListener {

    private static final Logger logger = Bukkit.getLogger();
    instantmine instantmine = new instantmine(new NamespacedKey(this, "instantmine"));
    public static String url = "https://ghub.fr/storage/zip/resourcePack.zip";
    public static String sha1;

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
            generateResourcePack();
        } catch (Exception e) {
        }
        Bukkit.getPluginManager().registerEvents(new eventsMinecraft(), this);
        registerEvents();
        registerEnchant();
        registerCommands();
        lib.hook();
        scheduler.start();
        /*
         * 
         */
        if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.SkyBlock)) {
            SkyBlockEventsCommands();
        } else if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.RPG)) {
            Bukkit.getPluginManager().registerEvents(new xpBonus(), this);
        }
        /*
         *
         */
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord",
                new BungeePluginMessageListener());
    }

    private void generateResourcePack() throws IOException {
        sha1 = ResourcePackHandler.getSHA1(url);
    }

    private void loadEnchantments() {
        try {
            try {
                Field f = Enchantment.class.getDeclaredField("acceptingNew");
                f.setAccessible(true);
                f.set(null, true);

            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Enchantment.registerEnchantment(instantmine);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void disableEnchant() {
        try {
            Field byKeyField = Enchantment.class.getDeclaredField("byKey");
            Field byNameField = Enchantment.class.getDeclaredField("byName");

            byKeyField.setAccessible(true);
            byNameField.setAccessible(true);

            HashMap<NamespacedKey, Enchantment> byKey = (HashMap<NamespacedKey, Enchantment>) byKeyField.get(null);
            HashMap<NamespacedKey, Enchantment> byName = (HashMap<NamespacedKey, Enchantment>) byNameField.get(null);

            if (byKey.containsKey(instantmine.getKey())) {
                byKey.remove(instantmine.getKey());
            }

            if (byName.containsKey(instantmine.getName())) {
                byName.remove(instantmine.getName());
            }

        } catch (Exception ignored) {

        }
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
        Bukkit.getPluginManager().registerEvents(new profilEvents(), this);
        Bukkit.getPluginManager().registerEvents(new minesEvents(), this);
        Bukkit.getPluginManager().registerEvents(new pickaxeEvents(), this);
        Bukkit.getPluginManager().registerEvents(new BossBarMessage(), this);
        Bukkit.getPluginManager().registerEvents(new ResourcePackHandler(), this);
        Bukkit.getPluginManager().registerEvents(new settingsEvents(), this);
    }

    public void registerEnchant() {
        loadEnchantments();
        Bukkit.getPluginManager().registerEvents(instantmine, this);
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
        Bukkit.getPluginCommand("profil").setExecutor(new profil());
        Bukkit.getPluginCommand("pay").setExecutor(new pay());
        Bukkit.getPluginCommand("money").setExecutor(new money());
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