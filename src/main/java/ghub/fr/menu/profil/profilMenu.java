package ghub.fr.menu.profil;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

import ghub.fr.menu.api.customInventory;
import ghub.fr.menu.island.islandItems;
import ghub.fr.system.ServerBootFile;
import ghub.fr.system.ServerBootFile.serverType;
import ghub.fr.text.lang;
import ghub.fr.text.playerLang;
import ghub.fr.text.textTranslation;

import java.io.IOException;

public class profilMenu {
    public static void menuProfil(Player player, OfflinePlayer offlinePlayer)
            throws IllegalArgumentException, IOException {
        lang.languages lang = playerLang.getPlayerLang(player);
        customInventory inventoryBuilder = new customInventory(customInventory.CustomInventoryType.PROFIL,
                InventoryType.HOPPER, offlinePlayer.getName() + textTranslation.MenuGoldFormat(lang, offlinePlayer));
        inventoryBuilder.getInventory().setItem(0, profilItems.ItemStackInfo(lang, offlinePlayer));
        inventoryBuilder.getInventory().setItem(1, profilItems.ItemStackTags(lang, offlinePlayer));
        inventoryBuilder.getInventory().setItem(2, profilItems.ItemStackPlayerData(lang, offlinePlayer));
        if (ServerBootFile.getServerTypeFromYML().equals(serverType.SkyBlock)) {
            inventoryBuilder.getInventory().setItem(3, islandItems.ItemStackPlayerHeadInfoVisite(offlinePlayer, lang));
        }
        player.openInventory(inventoryBuilder.getInventory());
    }
}