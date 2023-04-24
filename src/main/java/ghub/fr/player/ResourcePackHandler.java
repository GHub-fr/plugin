package ghub.fr.player;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import ghub.fr.main.main;

public class ResourcePackHandler implements Listener {

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent e) throws IOException {
        String url = main.url;
        String sha1 = main.sha1;
        String text = "§4Utilisation du resource pack §f...";
        Boolean force = true;

        System.out.println(sha1);
        System.out.println(sha1);
        System.out.println(sha1);
        System.out.println(sha1);
        System.out.println(sha1);
        System.out.println(sha1);
        System.out.println(sha1);
        System.out.println(sha1);
        System.out.println(sha1.getBytes());
        System.out.println(sha1.getBytes());
        System.out.println(sha1.getBytes());
        System.out.println(sha1.getBytes());
        System.out.println(sha1.getBytes());
        System.out.println(sha1.getBytes());
        System.out.println(sha1.getBytes());
        System.out.println(sha1.getBytes());
        System.out.println(sha1.getBytes());
        System.out.println(sha1.getBytes());
        System.out.println(sha1.getBytes("UTF-8"));
        System.out.println(sha1.getBytes("UTF-8"));
        System.out.println(sha1.getBytes("UTF-8"));
        System.out.println(sha1.getBytes("UTF-8"));
        System.out.println(sha1.getBytes("UTF-8"));
        System.out.println(sha1.getBytes("UTF-8"));
        System.out.println(sha1.getBytes("UTF-8"));
        System.out.println(sha1.getBytes("UTF-8"));
        System.out.println(sha1.getBytes("UTF-8"));

        setResourcePack(e.getPlayer(), url, sha1, text, force);
    }

    public static void setResourcePack(Player player, String url, String hash, String text, boolean force) {
        player.setResourcePack(url, hash.getBytes(), text, force);
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