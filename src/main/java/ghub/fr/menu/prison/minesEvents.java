package ghub.fr.menu.prison;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import ghub.fr.menu.api.persistentData;
import ghub.fr.system.gold;
import ghub.fr.text.itemsTranslation;
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
                    BuyMine((Player) e.getWhoClicked(), itemsTranslation.mineStone, "Mine.1");
                }
            }

            else if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                    persistentData.customKey.mineiron)) {
                if (prisonData.getMinesUnlocked((Player) e.getWhoClicked()).contains("Mine.2")) {
                    teleportation.Teleport((Player) e.getWhoClicked(), "Mine.2", false);
                } else {
                    BuyMine((Player) e.getWhoClicked(), itemsTranslation.mineIron, "Mine.2");
                }
            }

            else if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                    persistentData.customKey.minegold)) {
                if (prisonData.getMinesUnlocked((Player) e.getWhoClicked()).contains("Mine.3")) {
                    teleportation.Teleport((Player) e.getWhoClicked(), "Mine.3", false);
                } else {
                    BuyMine((Player) e.getWhoClicked(), itemsTranslation.mineGold, "Mine.3");
                }
            }

            else if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                    persistentData.customKey.minediamond)) {
                if (prisonData.getMinesUnlocked((Player) e.getWhoClicked()).contains("Mine.4")) {
                    teleportation.Teleport((Player) e.getWhoClicked(), "Mine.4", false);
                } else {
                    BuyMine((Player) e.getWhoClicked(), itemsTranslation.mineDiamond, "Mine.4");
                }
            }

            else if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(),
                    persistentData.customKey.minenetherite)) {
                if (prisonData.getMinesUnlocked((Player) e.getWhoClicked()).contains("Mine.5")) {
                    teleportation.Teleport((Player) e.getWhoClicked(), "Mine.5", false);
                } else {
                    BuyMine((Player) e.getWhoClicked(), itemsTranslation.mineNetherite, "Mine.5");
                }
            }
        }
    }

    public void BuyMine(OfflinePlayer offlinePlayer, int prix, String nomMine)
            throws IOException, IllegalArgumentException, ParseException {
        if (gold.GetHasEnoughGold(offlinePlayer, prix)) {
            gold.RemoveGold(offlinePlayer, prix);
            ArrayList<String> mines = prisonData.getMinesUnlocked(offlinePlayer);
            mines.add(nomMine);
            prisonData.setMinesUnlocked(offlinePlayer, mines);
            minesMenu.minesMenu(offlinePlayer.getPlayer());
            // send message buy mine
        } else {
            // send message pas assez gold
        }
    }
}

//
// Penser faire event block break + generate (44, -2 +64, 44 -> -44, -41 +64,
// -44)
//