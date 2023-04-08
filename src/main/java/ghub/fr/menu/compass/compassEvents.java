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

    public static int getPlayerCompassSlot(Player p) {
        // return this value by default while no config for this
        return 9;
    }

    public static void setCompassInv(Player p) throws IOException {
        if (!playerHasCompass(p)) {
            lang.languages lang = playerLang.getPlayerLang(p);
            p.getInventory().setItem(getPlayerCompassSlot(p), compassItems.ItemStackCompass(lang));
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