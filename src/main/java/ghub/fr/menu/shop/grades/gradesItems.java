package ghub.fr.menu.shop.grades;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ghub.fr.menu.api.persistentData;
import ghub.fr.text.lang;
import ghub.fr.text.shopTranslation;

public class gradesItems {
    public static ItemStack grade(lang.languages lang) {
        ItemStack is = new ItemStack(Material.GOAT_HORN, 1);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(shopTranslation.gradeTitle(lang));
        ArrayList<String> Lore = new ArrayList<String>();
        Lore.add(" ");
        Lore.addAll(shopTranslation.gradeLore(lang));
        Lore.add(" ");
        meta.setLore(Lore);
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.grade);
        return is;
    }

    public static ItemStack Aventurier(lang.languages lang) {
        ItemStack is = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(shopTranslation.aventurierTitle(lang));
        ArrayList<String> Lore = new ArrayList<String>();
        Lore.add(" ");
        Lore.addAll(shopTranslation.aventurierLore(lang));
        Lore.add(shopTranslation.Escape(lang));
        Lore.add(" ");
        meta.setLore(Lore);
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.aventurier);
        return is;
    }
}
