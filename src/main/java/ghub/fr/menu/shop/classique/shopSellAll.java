package ghub.fr.menu.shop.classique;

import java.io.IOException;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import ghub.fr.system.gold;

public class shopSellAll {
    public static void sellAll(Player player) throws IOException {
        Inventory inventory = player.getInventory();

        int total = 0;
        System.out.println("SellAllRun");
        for (ItemStack item : inventory.getContents()) {
            if (item != null && !item.getType().equals(Material.AIR)) {
                System.out.println("item" + item.getType());
                if (shopPrice.getPrix(item.getType()) != Integer.MAX_VALUE) {
                    total += (shopPrice.getPrix(item.getType()) * item.getAmount());
                    inventory.remove(item);

                    System.out.println("total" + total);
                }
            }
        }

        if (total > 0) {
            gold.AddGold(player, total);
        }
    }
}
