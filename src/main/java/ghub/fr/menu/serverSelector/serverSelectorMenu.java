package ghub.fr.menu.serverSelector;

import org.bukkit.entity.Player;

import ghub.fr.menu.api.customInventory;
import ghub.fr.text.lang;
import ghub.fr.text.playerLang;
import ghub.fr.text.textTranslation;

import java.io.IOException;
import java.text.ParseException;

public class serverSelectorMenu {
    public static void menuCompass(Player player) throws IllegalArgumentException, IOException, ParseException {
        lang.languages lang = playerLang.getPlayerLang(player);
        customInventory inventoryBuilder = new customInventory(customInventory.CustomInventoryType.VOYAGE, 9,
                textTranslation.voyage(lang) + textTranslation.MenuGoldFormat(lang, player));

        inventoryBuilder.getInventory().addItem(serverSelectorItems.ItemStackSurvie(lang, player));
        inventoryBuilder.getInventory().addItem(serverSelectorItems.ItemStackCreatif(lang, player));
        inventoryBuilder.getInventory().addItem(serverSelectorItems.ItemStackRPG(lang, player));
        inventoryBuilder.getInventory().addItem(serverSelectorItems.ItemStackSkyBlock(lang, player));
        inventoryBuilder.getInventory().addItem(serverSelectorItems.ItemStackAnarchie(lang, player));

        player.openInventory(inventoryBuilder.getInventory());
    }
}