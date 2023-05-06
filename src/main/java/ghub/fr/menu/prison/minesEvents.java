package ghub.fr.menu.prison;

import java.io.IOException;
import java.text.ParseException;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import ghub.fr.menu.api.persistentData;
import ghub.fr.world.api.teleportation;

public class minesEvents implements Listener {
    @EventHandler
    public void PlayerClickCompass(InventoryClickEvent e) throws IllegalArgumentException, IOException, ParseException {
        if (e.getCurrentItem() != null) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.mineselector)) {
                minesMenu.minesMenu((Player) e.getWhoClicked());
            }

            else if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                    persistentData.customKey.minestone)) {
                if (prisonData.getMinesUnlocked((Player) e.getWhoClicked()).contains("Mine.1")) {
                    teleportation.Teleport((Player) e.getWhoClicked(), "Mine.1", false);
                } else {
                    // check gold amount
                    // Penser à update le menu pour show avec la condition if un text buy
                }
            } else if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                    persistentData.customKey.mineiron)) {
                if (prisonData.getMinesUnlocked((Player) e.getWhoClicked()).contains("Mine.2")) {
                    teleportation.Teleport((Player) e.getWhoClicked(), "Mine.2", false);
                } else {
                }
            } else if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                    persistentData.customKey.minegold)) {
                if (prisonData.getMinesUnlocked((Player) e.getWhoClicked()).contains("Mine.3")) {
                    teleportation.Teleport((Player) e.getWhoClicked(), "Mine.3", false);
                } else {
                }
            } else if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                    persistentData.customKey.minediamond)) {
                if (prisonData.getMinesUnlocked((Player) e.getWhoClicked()).contains("Mine.4")) {
                    teleportation.Teleport((Player) e.getWhoClicked(), "Mine.4", false);
                } else {
                }
            } else if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                    persistentData.customKey.minenetherite)) {
                if (prisonData.getMinesUnlocked((Player) e.getWhoClicked()).contains("Mine.5")) {
                    teleportation.Teleport((Player) e.getWhoClicked(), "Mine.5", false);
                } else {
                }
            }
        }
    }
}
