package ghub.fr.menu.compass;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
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

import java.io.File;
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

    public static int getActualPose(OfflinePlayer offlinePlayer) {
        return YamlConfiguration.loadConfiguration(getDataStorage.playerFile(offlinePlayer)).getInt("compassPose");
    }

    public static void setNextPose(OfflinePlayer offlinePlayer) throws IOException {
        moveCompass(offlinePlayer, getNextPose(getActualPose(offlinePlayer)));
        offlinePlayer.getPlayer().sendMessage("Pose : " + getActualPose(offlinePlayer));
        offlinePlayer.getPlayer().sendMessage("Next : " + getNextPose(getActualPose(offlinePlayer)));
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
        return listPose().get(getActualPose(offlinePlayer));
    }

    public static void setPlayerCompassSlot(OfflinePlayer offlinePlayer, int slot) throws IOException {
        File file = getDataStorage.playerFile(offlinePlayer);
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        fileConfiguration.set("CompassPose", slot);
        fileConfiguration.save(file);
        offlinePlayer.getPlayer().sendMessage("Save on data pose index : " + slot);
    }

    public static void setCompassInv(Player p) throws IOException {
        lang.languages lang = playerLang.getPlayerLang(p);
        p.getInventory().setItem(getPlayerCompassSlot(p), compassItems.ItemStackCompass(lang));
        p.sendMessage("set compass on pose : " + getPlayerCompassSlot(p));
    }

    public static void moveCompass(OfflinePlayer offlinePlayer, int pose) throws IOException {
        if (offlinePlayer.isOnline()) {
            Player p = offlinePlayer.getPlayer();
            int oldSlot = getPlayerCompassSlot(p);
            int newSlot = listPose().get(pose);
            p.sendMessage("newSlot : " + newSlot + "     oldSlot : " + oldSlot);
            if (p.getInventory().getItem(newSlot) == null
                    || p.getInventory().getItem(newSlot).getType().equals(Material.AIR)) {
                p.getInventory().setItem(oldSlot, new ItemStack(Material.AIR));
                p.sendMessage("set to air");
            } else {
                p.getInventory().setItem(oldSlot, p.getInventory().getItem(newSlot));
                p.sendMessage("swap");
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