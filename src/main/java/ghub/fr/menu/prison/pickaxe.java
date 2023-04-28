package ghub.fr.menu.prison;

import java.io.IOException;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ghub.fr.menu.api.persistentData;
import ghub.fr.text.itemsTranslation;
import ghub.fr.text.lang;
import ghub.fr.text.playerLang;

public class pickaxe {
    public static ItemStack getPickaxe(OfflinePlayer offlinePlayer) throws IOException {
        lang.languages lang = playerLang.getPlayerLang(offlinePlayer);
        ItemStack is = new ItemStack(prisonData.getPickaxe(offlinePlayer));
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.PickaxeSelectorTitle(lang));
        meta.setLore(itemsTranslation.YourPickaxeLore(lang));
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.locked);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.myPickaxe);
        return null;
    }

    public static Material Nextpickaxe(Material material) {
        switch (material) {
            case WOODEN_PICKAXE:
                return Material.STONE_PICKAXE;
            case STONE_PICKAXE:
                return Material.IRON_PICKAXE;
            case IRON_PICKAXE:
                return Material.GOLDEN_PICKAXE;
            case GOLDEN_PICKAXE:
                return Material.DIAMOND_PICKAXE;
            case DIAMOND_PICKAXE:
                return Material.NETHERITE_PICKAXE;
            default:
                return Material.AIR;
        }
    }

    public static int NextpickaxePrice(Material material) {
        switch (material) {
            case WOODEN_PICKAXE:
                return 250;
            case STONE_PICKAXE:
                return 1_000;
            case IRON_PICKAXE:
                return 5_000;
            case GOLDEN_PICKAXE:
                return 25_000;
            case DIAMOND_PICKAXE:
                return 100_000;
            default:
                return Integer.MAX_VALUE;
        }
    }

    public static void UpdatePickaxe(Player player) throws IllegalArgumentException, IOException {
        player.getInventory().addItem(getPickaxe(player));
        // remove old one
        // -> To remove loop all item for CustomItem tag, or get item 1 if air, else
        // next item slot loop
        // add new one
        // do it on spawn
        // Enchants storage
    }
}
