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
                        worldManager.Generate("Mine.1", false, Environment.NORMAL, WorldType.NORMAL, true);
                        worldManager.Generate("Mine.2", false, Environment.NORMAL, WorldType.NORMAL, true);
                        worldManager.Generate("Mine.3", false, Environment.NORMAL, WorldType.NORMAL, true);
                        worldManager.Generate("Mine.4", false, Environment.NORMAL, WorldType.NORMAL, true);
                        worldManager.Generate("Mine.5", false, Environment.NORMAL, WorldType.NORMAL, true);
                        // set structure
                        return;
                    case Creatif:
                        return;
                    case Hub:
                        return;
                }
            }
        }.runTaskLater(plugin, 20 * 60 * 1);
    }
}
