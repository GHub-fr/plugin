package ghub.fr.world.api;

import org.bukkit.WorldType;
import org.bukkit.World.Environment;

import ghub.fr.system.ServerBootFile;
import ghub.fr.system.ServerBootFile.serverType;

public class LastWorldsInit {
    public static void LoadWorld() {
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
                // set structure
                return;
            case Creatif:
                return;
            case Hub:
                return;
        }
    }
}
