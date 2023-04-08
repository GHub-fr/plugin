package ghub.fr.menu.profile;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

import ghub.fr.menu.api.customInventory;
import ghub.fr.text.lang;
import ghub.fr.text.playerLang;
import ghub.fr.text.textTranslation;

import java.io.IOException;

public class profileMenu {
    public static void menuProfile(Player player, OfflinePlayer offlinePlayer)
            throws IllegalArgumentException, IOException {
        lang.languages lang = playerLang.getPlayerLang(player);
        customInventory inventoryBuilder = new customInventory(customInventory.CustomInventoryType.PROFILE,
                InventoryType.HOPPER, offlinePlayer.getName() + textTranslation.MenuGoldFormat(lang, offlinePlayer));
        inventoryBuilder.getInventory().setItem(0, profileItems.ItemStackInfo(lang, offlinePlayer));
        inventoryBuilder.getInventory().setItem(2, profileItems.ItemStackTags(lang, offlinePlayer));
        inventoryBuilder.getInventory().setItem(4, profileItems.ItemStackPlayerData(lang, offlinePlayer));
        // if SB add item for click /w event
        player.openInventory(inventoryBuilder.getInventory());
    }
}