package ghub.fr.menu.settings;

import java.io.IOException;
import java.text.ParseException;

import org.bukkit.entity.Player;

import ghub.fr.menu.api.customInventory;
import ghub.fr.text.itemsTranslation;
import ghub.fr.text.lang;
import ghub.fr.text.playerLang;
import ghub.fr.text.textTranslation;

public class settingsMenu {
    public static void SettingsMenu(Player player) throws IllegalArgumentException, IOException, ParseException {
        lang.languages lang = playerLang.getPlayerLang(player);
        customInventory inventoryBuilder = new customInventory(customInventory.CustomInventoryType.SETTINGS, 54,
                itemsTranslation.SettingsTitle(lang) + textTranslation.MenuGoldFormat(lang, player));

        inventoryBuilder.getInventory().addItem(settingsItems.ItemStackResourcePack(lang));
        inventoryBuilder.getInventory().addItem(settingsItems.ItemStackLang(lang));
        inventoryBuilder.getInventory().addItem(settingsItems.ItemStackCompassPose(lang));

        player.openInventory(inventoryBuilder.getInventory());
    }
}
