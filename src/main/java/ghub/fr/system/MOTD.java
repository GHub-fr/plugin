package ghub.fr.system;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class MOTD implements Listener {
    @EventHandler
    public void onServerListPing(ServerListPingEvent e) {
        int maxPlayer = Bukkit.getMaxPlayers();
        e.setMotd(MOTD());
        e.setMaxPlayers(maxPlayer);
        Bukkit.getServer().setMaxPlayers(maxPlayer);
    }

    public static String MOTD() {
        String BootText = "§6[ §2§lghub§r.fr §6] §6";
        String BottomText = " §r!\n§rghub.fr/§2discord";
        return BootText + ServerBootFile.getServerType().toString() + BottomText;
    }
}