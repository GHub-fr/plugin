package ghub.fr.world.api;

import org.bukkit.WorldType;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.World.Environment;

import ghub.fr.main.main;
import ghub.fr.system.ServerBootFile;
import ghub.fr.system.ServerBootFile.serverType;

public class LastWorldsInit {
    public static void LoadWorld() {
        Plugin plugin = JavaPlugin.getPlugin(main.class);
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    serverType serverType = ServerBootFile.getServerTypeFromYML();
                    switch (serverType) {
                        case RPG:
                            return;
                        case SkyBlock:
                            return;
                        case Survie:
                        case Anarchie:
                            worldManager.Generate("world", true, Environment.NORMAL, WorldType.NORMAL, false);
                            return;
                        case OPPrison:
                            worldManager.Generate("Mine.1", false, Environment.NORMAL, WorldType.FLAT, true);
                            structure.setStructure("mine", "Mine.1", 0, 64, 0, 1, 20 * 10);
                            worldManager.Generate("Mine.2", false, Environment.NORMAL, WorldType.FLAT, true);
                            structure.setStructure("mine", "Mine.2", 0, 64, 0, 1, 20 * 10 * 2);
                            worldManager.Generate("Mine.3", false, Environment.NORMAL, WorldType.FLAT, true);
                            structure.setStructure("mine", "Mine.3", 0, 64, 0, 1, 20 * 10 * 3);
                            worldManager.Generate("Mine.4", false, Environment.NORMAL, WorldType.FLAT, true);
                            structure.setStructure("mine", "Mine.4", 0, 64, 0, 1, 20 * 10 * 4);
                            worldManager.Generate("Mine.5", false, Environment.NORMAL, WorldType.FLAT, true);
                            structure.setStructure("mine", "Mine.5", 0, 64, 0, 1, 20 * 10 * 5);
                            return;
                        case Creatif:
                            return;
                        case Hub:
                            return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.runTaskLater(plugin, 20 * 60 * 1);
    }
}
