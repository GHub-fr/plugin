package ghub.fr.menu.prison;

import java.io.IOException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import ghub.fr.system.ServerBootFile;
import ghub.fr.system.ServerBootFile.serverType;
import ghub.fr.world.api.BuildRules;

public class EventLimiter implements Listener {
    @EventHandler
    public void onPlayerBreak(BlockBreakEvent e) throws IOException {
        if (ServerBootFile.getServerType().equals(serverType.OPPrison)) {
            if (e.getPlayer().getWorld().getName().startsWith("Mine.")) {

                if (e.getBlock().getX() >= -44 && e.getBlock().getX() <= 44) {
                    if (e.getBlock().getY() >= -41 && e.getBlock().getY() <= -2) {
                        if (e.getBlock().getZ() >= -44 && e.getBlock().getZ() <= 44) {
                            e.setCancelled(false);
                            return;
                        }
                    }
                }
                
                if (!BuildRules.GetCanBuild(e.getPlayer())) {
                    e.setCancelled(true);
                    return;
                }
            }
        }
    }
}
