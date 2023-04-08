package ghub.fr.menu.compass;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ghub.fr.menu.api.persistentData;
import ghub.fr.text.itemsTranslation;
import ghub.fr.text.lang;

public class compassItems {
    public static ItemStack ItemStackCompass(lang.languages lang) {
        ItemStack is = new ItemStack(Material.COMPASS);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.compassTitle());
        meta.setLore(itemsTranslation.compassLore(lang));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.compass);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.locked);
        return is;
    }

    public static ItemStack ItemStackIle(lang.languages lang) {
        ItemStack is = new ItemStack(Material.OAK_SAPLING);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.IleTitle(lang));
        meta.setLore(itemsTranslation.IleLore(lang));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.ile);
        return is;
    }

    public static ItemStack ItemStackQuest(lang.languages lang) {
        ItemStack is = new ItemStack(Material.WRITABLE_BOOK);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.questTitle(lang));
        meta.setLore(itemsTranslation.questLore(lang));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.quest);
        return is;
    }

    public static ItemStack ItemStackShop(lang.languages lang) {
        ItemStack is = new ItemStack(Material.EMERALD);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.shopTitle(lang));
        meta.setLore(itemsTranslation.shopLore(lang));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.shop);
        return is;
    }

    public static ItemStack ItemStackShopEnchant(lang.languages lang) {
        ItemStack is = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.shopEnchantTitle(lang));
        meta.setLore(itemsTranslation.shopEnchantLore(lang));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.enchant);
        return is;
    }

    public static ItemStack ItemStackTeleporation(lang.languages lang) {
        ItemStack is = new ItemStack(Material.MINECART);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.teleporationTitle(lang));
        meta.setLore(itemsTranslation.teleporationLore(lang));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.voyage);
        return is;
    }
}