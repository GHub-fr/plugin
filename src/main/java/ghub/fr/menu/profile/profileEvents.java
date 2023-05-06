package ghub.fr.menu.profile;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.SkullMeta;

import ghub.fr.menu.api.persistentData;
import ghub.fr.menu.island.island;
import ghub.fr.world.api.teleportation;

import java.io.IOException;
import java.text.ParseException;

public class profileEvents implements Listener {
    @EventHandler
    public void PlayerClickIle(InventoryClickEvent e) throws IllegalArgumentException, IOException {
        if ((e.getCurrentItem() != null)) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.ileplayerhead)) {
                SkullMeta itemMeta = (SkullMeta) e.getCurrentItem().getItemMeta();
                OfflinePlayer offlinePlayer = itemMeta.getOwningPlayer();
                profileMenu.menuProfile((Player) e.getWhoClicked(), offlinePlayer);
            }
        }
    }

    @EventHandler
    public void PlayerClickIleVisite(InventoryClickEvent e) throws IllegalArgumentException, IOException, ParseException {
        if ((e.getCurrentItem() != null)) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                    persistentData.customKey.ileprofilviste)) {
                SkullMeta itemMeta = (SkullMeta) e.getCurrentItem().getItemMeta();
                OfflinePlayer offlinePlayer = itemMeta.getOwningPlayer();
                if (island.GetHasIsland(offlinePlayer)) {
                    String world = "i." + island.GetIslandNumber(offlinePlayer);
                    teleportation.Teleport((Player) e.getWhoClicked(), world, false);
                }
            }
        }
    }
}