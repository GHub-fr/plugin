package ghub.fr.menu.prison;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ghub.fr.menu.api.persistentData;
import ghub.fr.text.itemsTranslation;
import ghub.fr.text.lang;

public class pickaxeItems {
    public static ItemStack ItemStackPickaxeSelector(lang.languages lang) {
        ItemStack is = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.PickaxeSelectorTitle(lang));
        meta.setLore(itemsTranslation.PickaxeSelectorLore(lang));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.pickaxeSelector);
        return is;
    }

   /*  public static ItemStack ItemStackPickaxeABC(lang.languages lang) {
        ItemStack is = new ItemStack(Material.STONE);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.PickaxeABCTitle(lang));
        meta.setLore(itemsTranslation.PickaxeABCLore(lang));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.ABC);
        return is;
    } */
}
