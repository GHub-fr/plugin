package ghub.fr.menu.profile;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.SkullMeta;

import ghub.fr.menu.api.persistentData;

import java.io.IOException;

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
}