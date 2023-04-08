package ghub.fr.world.spawn;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import ghub.fr.system.ServerBootFile;
import ghub.fr.world.api.teleportation;

import java.io.IOException;
import java.text.ParseException;

public class joinAtSpawn implements Listener {
    @EventHandler
    public void PlayerJoinToSpawn(PlayerJoinEvent e)
            throws IllegalArgumentException, IllegalStateException, IOException, ParseException {
        if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.SkyBlock)
                || ServerBootFile.getServerType().equals(ServerBootFile.serverType.Hub)
                || ServerBootFile.getServerType().equals(ServerBootFile.serverType.Creatif)
                || ServerBootFile.getServerType().equals(ServerBootFile.serverType.RPG)) {
            teleportation.Teleport(e.getPlayer(), "Spawn", false);
        }
    }
}