package ghub.fr.main;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import ghub.fr.discord.messages;
import ghub.fr.entity.armorStand;
import ghub.fr.entity.spawnNPC;
import ghub.fr.menu.island.islandUnloader;
import ghub.fr.system.ServerBootFile;
import ghub.fr.system.Tab;
import ghub.fr.system.dateAPI;
import ghub.fr.system.getDataStorage;
import ghub.fr.world.api.structure;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Random;

public class scheduler {
    public static void start() {
        try {
            delayedScheduler();
            islandUnloader.IslandScheduler();
            restart();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void ServersStats() throws IOException {
        Random random = new Random();
        Plugin plugin = JavaPlugin.getPlugin(main.class);
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    String server = ServerBootFile.getServerTypeFromYML().toString().toLowerCase(Locale.ROOT);
                    File file = getDataStorage.serversStatsFile(server);
                    if (!file.exists()) {
                        file.createNewFile();
                        structure.setStructure("spawn", "Spawn");
                    }
                    FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
                    fileConfiguration.set("onlinePlayers", Bukkit.getServer().getOnlinePlayers().size());
                    fileConfiguration.set("maxPlayers", Bukkit.getServer().getMaxPlayers());
                    fileConfiguration.set("lastTick", dateAPI.DateFormated(dateAPI.now()));
                    fileConfiguration.save(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.runTaskTimerAsynchronously(plugin, 0, 20 + random.nextInt(80));
    }

    public static void delayedScheduler() {
        Plugin plugin = JavaPlugin.getPlugin(main.class);
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    armorStand.start();
                    spawnNPC.start();
                    Tab.scheduler();
                    ServersStats();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.runTaskLater(plugin, 5);
    }

    public static void restart() {
        try {
            sendRestartMessage(30 * 60 * 20);
            sendRestartMessage(15 * 60 * 20);
            sendRestartMessage(10 * 60 * 20);
            sendRestartMessage(5 * 60 * 20);
            sendRestartMessage(3 * 60 * 20);
            sendRestartMessage(2 * 60 * 20);
            sendRestartMessage(60 * 20);
            sendRestartMessage(30 * 20);
            sendRestartMessage(15 * 20);
            sendRestartMessage(10 * 20);
            sendRestartMessage(5 * 20);
            sendRestartMessage(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendRestartMessage(int time) {
        Plugin plugin = JavaPlugin.getPlugin(main.class);
        int timeH = (20 * 60 * 60 * 8);
        int timeUntil = timeH - time;
        new BukkitRunnable() {
            @Override
            public void run() {
                if (time == 0) {
                    Bukkit.spigot().restart();
                } else {
                    messages.ServerRestart(time / 20);
                }
            }
        }.runTaskLater(plugin, timeUntil);
    }
}