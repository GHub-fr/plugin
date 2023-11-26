package ghub.fr.menu.shop.grades;

import java.io.IOException;

import org.bukkit.entity.Player;

import ghub.fr.menu.api.customInventory;
import ghub.fr.system.tags;
import ghub.fr.system.tags.TagsList;
import ghub.fr.text.itemsTranslation;
import ghub.fr.text.lang;
import ghub.fr.text.playerLang;
import ghub.fr.text.textTranslation;

public class gradesMenu {

    public static void openGradeShop(Player player) throws IOException {
        lang.languages lang = playerLang.getPlayerLang(player);
        customInventory inventoryBuilder = new customInventory(customInventory.CustomInventoryType.SHOP, 9,
                itemsTranslation.shopTitle(lang) + textTranslation.MenuGoldFormat(lang, player));

        inventoryBuilder.getInventory().setItem(1, gradesItems.Aventurier(lang, tags.hasTags(player, TagsList.Aventurier)));

        inventoryBuilder.getInventory().setItem(2, gradesItems.Soldat(lang, tags.hasTags(player, TagsList.Soldat)));

        player.openInventory(inventoryBuilder.getInventory());
    }
}
