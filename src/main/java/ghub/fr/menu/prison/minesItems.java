package ghub.fr.menu.prison;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ghub.fr.menu.api.persistentData;
import ghub.fr.text.itemsTranslation;
import ghub.fr.text.lang;

public class minesItems {
    public static ItemStack ItemStackMineSelector(lang.languages lang) {
        ItemStack is = new ItemStack(Material.STONE);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.MineSelectorTitle(lang));
        meta.setLore(itemsTranslation.MineSelectorLore(lang));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.mineselector);
        return is;
    }

    public static ItemStack ItemStackMineStone(lang.languages lang) {
        ItemStack is = new ItemStack(Material.STONE);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.MineStoneTitle(lang));
        meta.setLore(itemsTranslation.MineStoneLore(lang));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.minestone);
        return is;
    }

    public static ItemStack ItemStackMineIron(lang.languages lang) {
        ItemStack is = new ItemStack(Material.IRON_INGOT);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.MineIronTitle(lang));
        meta.setLore(itemsTranslation.MineIronLore(lang));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.mineiron);
        return is;
    }

    public static ItemStack ItemStackMineGold(lang.languages lang) {
        ItemStack is = new ItemStack(Material.GOLD_INGOT);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.MineGoldTitle(lang));
        meta.setLore(itemsTranslation.MineGoldLore(lang));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.minegold);
        return is;
    }

    public static ItemStack ItemStackMineDiamond(lang.languages lang) {
        ItemStack is = new ItemStack(Material.DIAMOND);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.MineDiamondTitle(lang));
        meta.setLore(itemsTranslation.MineDiamondLore(lang));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.minediamond);
        return is;
    }

    public static ItemStack ItemStackMineNetherite(lang.languages lang) {
        ItemStack is = new ItemStack(Material.NETHERITE_INGOT);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.MineNetheriteTitle(lang));
        meta.setLore(itemsTranslation.MineNetheriteLore(lang));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.minenetherite);
        return is;
    }
}
