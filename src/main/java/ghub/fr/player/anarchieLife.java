package ghub.fr.player;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.text.ParseException;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ghub.fr.menu.api.persistentData;
import ghub.fr.system.dateAPI;
import ghub.fr.system.getDataStorage;
import ghub.fr.text.itemsTranslation;
import ghub.fr.text.lang;

public class anarchieLife implements Listener {
    public static ItemStack GetlifeItemStack(lang.languages lang, OfflinePlayer offlinePlayer) throws ParseException, IOException {
        ItemStack is = new ItemStack(Material.DIRT);
        if (canGetLife(offlinePlayer)) {
            is.setType(Material.POTION);
        } else {
            is.setType(Material.BARRIER);
        }
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.anarchieLifeTitle(lang, canGetLife(offlinePlayer)));
        meta.setLore(itemsTranslation.anarchieLifeLore(lang, getLastLifeDate(offlinePlayer)));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.anarchielife);
        return is;
    }

    @EventHandler
    public void PlayerClickSettings(InventoryClickEvent e)
            throws IllegalArgumentException, IOException, ParseException {
        if (e.getCurrentItem() != null) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.anarchielife)) {
                if (canGetLife((Player) e.getWhoClicked())) {
                    removeDeath((Player) e.getWhoClicked());
                }
            }
        }
    }

    public static int getDeathCount(OfflinePlayer offlinePlayer) {
        File file = getDataStorage.playerFile(offlinePlayer);
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        return fileConfiguration.getInt("deathAnarchie");
    }

    public static void addDeath(OfflinePlayer offlinePlayer) throws IOException {
        File file = getDataStorage.playerFile(offlinePlayer);
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        int death = fileConfiguration.getInt("deathAnarchie");
        death++;
        fileConfiguration.set("deathAnarchie", death);
        fileConfiguration.save(file);
    }

    public static void removeDeath(OfflinePlayer offlinePlayer) throws IOException {
        File file = getDataStorage.playerFile(offlinePlayer);
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        int death = fileConfiguration.getInt("deathAnarchie");
        death--;
        fileConfiguration.set("deathAnarchie", death);
        fileConfiguration.save(file);
    }

    public static int getRemainingLifes(OfflinePlayer offlinePlayer) {
        return 3 - getDeathCount(offlinePlayer);
    }

    public static Date getLastLifeDate(OfflinePlayer offlinePlayer) throws ParseException {
        File file = getDataStorage.playerFile(offlinePlayer);
        return dateAPI.ReturnDate(file, "lifeAnarchie");
    }

    public static void setDateLifeDate(OfflinePlayer offlinePlayer) {
        File file = getDataStorage.playerFile(offlinePlayer);
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        fileConfiguration.set("lifeAnarchie", dateAPI.DateFormated(dateAPI.now()));
    }

    public static boolean canGetLife(OfflinePlayer offlinePlayer) throws ParseException {
        Date date = getLastLifeDate(offlinePlayer);
        Date now = new Date();
        date.setHours(date.getHours() + 24);
        if (now.after(date)) {
            return true;
        }
        return false;
    }
}
