package ghub.fr.menu.compass;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import ghub.fr.menu.api.persistentData;
import ghub.fr.system.getDataStorage;
import ghub.fr.text.lang;
import ghub.fr.text.playerLang;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class compassEvents implements Listener {
    public static ArrayList<Integer> listPose() {
        ArrayList<Integer> listPose = new ArrayList<>();
        listPose.add(9);
        listPose.add(17);
        listPose.add(27);
        listPose.add(35);
        listPose.add(36);
        listPose.add(44);
        return listPose;
    }

    public static int getNextPose(int current) {
        if (current >= listPose().size() - 1) {
            return 0;
        } else {
            int i = current;
            i++;
            return i;
        }
    }

    public static void setNextPose(OfflinePlayer offlinePlayer) throws IOException {
        moveCompass(offlinePlayer, getNextPose(getDataStorage.playerFileConfiguration(offlinePlayer).getInt("compassPose")));
    }

    public static boolean playerHasCompass(Player p) throws IOException {
        for (int i : listPose()) {
            if (p.getInventory().getItem(i) != null) {
                if (persistentData.hasPersistentDataItemStack(p.getInventory().getItem(i),
                        persistentData.customKey.compass)) {
                    lang.languages lang = playerLang.getPlayerLang(p);
                    if (p.getInventory().getItem(i).equals(compassItems.ItemStackCompass(lang))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static int getPlayerCompassSlot(OfflinePlayer offlinePlayer) throws IOException {
        return listPose().get(getDataStorage.playerFileConfiguration(offlinePlayer).getInt("compassPose"));
    }

    public static void setPlayerCompassSlot(OfflinePlayer offlinePlayer, int slot) throws IOException {
        getDataStorage.playerFileConfiguration(offlinePlayer).set("compassPose", slot);
        getDataStorage.playerFileConfiguration(offlinePlayer).save(getDataStorage.playerFile(offlinePlayer));
    }

    public static void setCompassInv(Player p) throws IOException {
        if (!playerHasCompass(p)) {
            lang.languages lang = playerLang.getPlayerLang(p);
            p.getInventory().setItem(getPlayerCompassSlot(p), compassItems.ItemStackCompass(lang));
        }
    }

    public static void moveCompass(OfflinePlayer offlinePlayer, int pose) throws IOException {
        if (offlinePlayer.isOnline()) {
            Player p = offlinePlayer.getPlayer();
            int oldSlot = getPlayerCompassSlot(p);
            if (p.getInventory().getItem(pose).getType().equals(Material.AIR)) {
                p.getInventory().setItem(oldSlot, new ItemStack(Material.AIR));
            } else {
                p.getInventory().setItem(oldSlot, p.getInventory().getItem(pose));
                p.getInventory().setItem(pose, new ItemStack(Material.AIR));
            }
            setPlayerCompassSlot(offlinePlayer, pose);
            setCompassInv(p);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) throws IOException {
        setCompassInv(e.getPlayer());
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) throws IOException {
        setCompassInv(e.getPlayer());
    }

    @EventHandler
    public void PlayerClickCompass(InventoryClickEvent e) throws IllegalArgumentException, IOException, ParseException {
        if (e.getCurrentItem() != null) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.compass)) {
                compassMenu.menuCompass((Player) e.getWhoClicked());
                lang.languages lang = playerLang.getPlayerLang((OfflinePlayer) e.getWhoClicked());
                Player player = (Player) e.getWhoClicked();
                if (!e.getCurrentItem().equals(compassItems.ItemStackCompass(lang))) {
                    player.getInventory().setItem(e.getSlot(), compassItems.ItemStackCompass(lang));
                }

                if (!listPose().contains(e.getSlot())) {
                    player.getInventory().setItem(e.getSlot(), new ItemStack(Material.AIR));
                    setCompassInv(player);
                }
            }
        }
    }
}