package ghub.fr.menu.compass;

import org.bukkit.entity.Player;

import ghub.fr.menu.api.customInventory;
import ghub.fr.menu.island.islandItems;
import ghub.fr.menu.serverSelector.serverSelectorItems;
import ghub.fr.menu.shop.classique.shopItems;
import ghub.fr.system.ServerBootFile;
import ghub.fr.text.lang;
import ghub.fr.text.playerLang;
import ghub.fr.text.textTranslation;

import java.io.IOException;
import java.text.ParseException;

public class compassMenu {
    public static void menuCompass(Player player) throws IllegalArgumentException, IOException, ParseException {
        lang.languages lang = playerLang.getPlayerLang(player);
        customInventory inventoryBuilder = new customInventory(customInventory.CustomInventoryType.MENU, 9,
                textTranslation.Menu(lang) + textTranslation.MenuGoldFormat(lang, player));

        if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.Survie)
                || ServerBootFile.getServerType().equals(ServerBootFile.serverType.Creatif)
                || ServerBootFile.getServerType().equals(ServerBootFile.serverType.Anarchie)) {
            inventoryBuilder.getInventory().setItem(4, compassItems.ItemStackTeleporation(lang));
            if (!ServerBootFile.getServerType().equals(ServerBootFile.serverType.Creatif)) {
                inventoryBuilder.getInventory().setItem(8, compassItems.ItemStackShop(lang));
            }
        } else if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.Hub)) {
            inventoryBuilder.getInventory().setItem(0, serverSelectorItems.ItemStackSurvie(lang, player));
            inventoryBuilder.getInventory().setItem(1, serverSelectorItems.ItemStackCreatif(lang, player));
            inventoryBuilder.getInventory().setItem(2, serverSelectorItems.ItemStackRPG(lang, player));
            inventoryBuilder.getInventory().setItem(3, serverSelectorItems.ItemStackSkyBlock(lang, player));
            inventoryBuilder.getInventory().setItem(4, serverSelectorItems.ItemStackAnarchie(lang, player));
            inventoryBuilder.getInventory().setItem(7, shopItems.Custom(lang));
            inventoryBuilder.getInventory().setItem(8, islandItems.ItemStackPlayerHeadIle(player, lang));
        } else if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.RPG)) {
            inventoryBuilder.getInventory().setItem(8, compassItems.ItemStackShop(lang));
            inventoryBuilder.getInventory().setItem(7, compassItems.ItemStackShopEnchant(lang));
            inventoryBuilder.getInventory().setItem(1, compassItems.ItemStackTeleporation(lang));
            inventoryBuilder.getInventory().setItem(0, compassItems.ItemStackQuest(lang));
        } else if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.SkyBlock)) {
            inventoryBuilder.getInventory().setItem(0, compassItems.ItemStackIle(lang));
            inventoryBuilder.getInventory().setItem(8, compassItems.ItemStackShop(lang));
            inventoryBuilder.getInventory().setItem(7, compassItems.ItemStackShopEnchant(lang));
            inventoryBuilder.getInventory().setItem(2, compassItems.ItemStackTeleporation(lang));
        }

        player.openInventory(inventoryBuilder.getInventory());
    }
}