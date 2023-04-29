package ghub.fr.menu.prison;

import java.io.IOException;
import java.text.ParseException;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

import ghub.fr.menu.api.customInventory;
import ghub.fr.text.itemsTranslation;
import ghub.fr.text.lang;
import ghub.fr.text.playerLang;
import ghub.fr.text.textTranslation;

public class pickaxeMenu {
    public static void pickaxeMenu(Player player) throws IllegalArgumentException, IOException, ParseException {
        lang.languages lang = playerLang.getPlayerLang(player);
        customInventory inventoryBuilder = new customInventory(customInventory.CustomInventoryType.MENU,
                InventoryType.HOPPER,
                itemsTranslation.PickaxeSelectorTitle(lang) + textTranslation.MenuGoldFormat(lang, player));
        inventoryBuilder.getInventory().setItem(0,pickaxeItems.ItemStackPickaxeUpgrade(lang, player));
        inventoryBuilder.getInventory().setItem(2,pickaxeItems.ItemStackPickaxeEnchantDigSpeed(lang, player));
        inventoryBuilder.getInventory().setItem(3,pickaxeItems.ItemStackPickaxeEnchantLooting(lang, player));
        player.openInventory(inventoryBuilder.getInventory());
    }
}
