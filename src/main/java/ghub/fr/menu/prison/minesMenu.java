package ghub.fr.menu.prison;

import java.io.IOException;
import java.text.ParseException;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

import ghub.fr.menu.api.customInventory;
import ghub.fr.text.lang;
import ghub.fr.text.playerLang;
import ghub.fr.text.textTranslation;

public class minesMenu {
    public static void minesMenu(Player player) throws IllegalArgumentException, IOException, ParseException {
        lang.languages lang = playerLang.getPlayerLang(player);
        customInventory inventoryBuilder = new customInventory(customInventory.CustomInventoryType.MENU,
                InventoryType.HOPPER,
                textTranslation.Mines(lang) + textTranslation.MenuGoldFormat(lang, player));
        inventoryBuilder.getInventory().addItem(minesItems.ItemStackMineStone(lang));
        inventoryBuilder.getInventory().addItem(minesItems.ItemStackMineIron(lang));
        inventoryBuilder.getInventory().addItem(minesItems.ItemStackMineGold(lang));
        inventoryBuilder.getInventory().addItem(minesItems.ItemStackMineDiamond(lang));
        inventoryBuilder.getInventory().addItem(minesItems.ItemStackMineNetherite(lang));
        player.openInventory(inventoryBuilder.getInventory());
    }
}
