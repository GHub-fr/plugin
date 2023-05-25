package ghub.fr.player;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;

import ghub.fr.main.main;
import ghub.fr.system.ServerBootFile;
import ghub.fr.system.ServerBootFile.serverType;

public class ResourcePackHandler implements Listener {
    public static String url = main.url;
    public static String sha1 = main.sha1;
    public static String text = "§4§lUtilisation du resource pack §r§f..." + "\n§f[ §6§lGHub.fr §r§f] Resource pack";
    public static Boolean force = true;

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent e) throws IOException {
        if (sha1 == null || sha1 == "") {
            sha1 = getSHA1(url);
        }

        serverType serverTypes = ServerBootFile.getServerTypeFromYML();
        if (serverTypes.equals(serverType.Hub)) {
            setResourcePack(e.getPlayer(), url, sha1, text, force);
        }
    }

    public static void setResourcePack(Player player, String url, String hash, String text, boolean force) {
        byte[] hashed = HexFormat.of().parseHex(hash);
        player.setResourcePack(url, hashed, text, force);
    }

    @EventHandler
    public void ResourcePackStatusEvent(PlayerResourcePackStatusEvent e) {
        switch (e.getStatus()) {
            case ACCEPTED:
                setResourcePack(e.getPlayer(), url, sha1, text, true);
                return;
            case DECLINED:
                e.getPlayer().kickPlayer(
                        "§4§lLe ressource pack doit être accepté §rpour jouer sur le serveur en raison de son utilisation");
                return;
            case FAILED_DOWNLOAD:
                setResourcePack(e.getPlayer(), url, sha1, text, true);
                return;
            case SUCCESSFULLY_LOADED:
                e.getPlayer().sendMessage("ResourcePack OK Debug MSG");
                return;
        }
    }

    public static String getSHA1(String url) throws IOException {
        try {
            final URLConnection urlConnection = new URL(url).openConnection();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            InputStream fis = urlConnection.getInputStream();
            byte[] dataBytes = new byte[1024];

            int nread = 0;

            while ((nread = fis.read(dataBytes)) != -1) {
                md.update(dataBytes, 0, nread);
            }
            ;

            byte[] mdbytes = md.digest();

            StringBuffer sb = new StringBuffer("");
            for (int i = 0; i < mdbytes.length; i++) {
                sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }
}