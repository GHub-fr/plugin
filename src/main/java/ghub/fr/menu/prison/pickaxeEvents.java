package ghub.fr.menu.prison;

import java.io.IOException;
import java.text.ParseException;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import ghub.fr.menu.api.persistentData;
import ghub.fr.menu.shop.classique.shopLogs;
import ghub.fr.system.gold;
import ghub.fr.text.playerLang;
import ghub.fr.text.shopTranslation;

public class pickaxeEvents implements Listener {
    @EventHandler
    public void PlayerClickSelector(InventoryClickEvent e)
            throws IllegalArgumentException, IOException, ParseException {
        if (e.getCurrentItem() != null) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                    persistentData.customKey.pickaxeSelector)) {
                pickaxeMenu.pickaxeMenu((Player) e.getWhoClicked());
            }
        }
    }

    @EventHandler
    public void PlayerClickUpgrade(InventoryClickEvent e)
            throws IllegalArgumentException, IOException, ParseException {
        if (e.getCurrentItem() != null) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                    persistentData.customKey.pickaxeUpgrade)) {
                Player player = (Player) e.getWhoClicked();
                if (gold.GetHasEnoughGold(player, pickaxe.NextpickaxePrice(prisonData.getPickaxe(player)))) {
                    gold.RemoveGold(player, pickaxe.NextpickaxePrice(prisonData.getPickaxe(player)));
                    shopLogs.LogData(player, false, e.getCurrentItem().getType(), 1);
                    prisonData.setPickaxe(player, pickaxe.Nextpickaxe(prisonData.getPickaxe(player)));
                    pickaxe.UpdatePickaxe(player);
                } else {
                    e.getWhoClicked().sendMessage(shopTranslation.PasAssezArgent(playerLang.getPlayerLang((Player) e.getWhoClicked())));
                }
            }
        }
    }
}