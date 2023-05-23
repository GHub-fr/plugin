package ghub.fr.menu.settings;

import java.io.IOException;
import java.text.ParseException;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import ghub.fr.menu.api.persistentData;
import ghub.fr.menu.compass.compassEvents;
import ghub.fr.player.ResourcePackHandler;
import ghub.fr.text.playerLang;

public class settingsEvents implements Listener {
    @EventHandler
    public void PlayerClickSettings(InventoryClickEvent e)
            throws IllegalArgumentException, IOException, ParseException {
        if (e.getCurrentItem() != null) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.settings)) {
                settingsMenu.SettingsMenu((Player) e.getWhoClicked());
            }
        }
    }

    @EventHandler
    public void PlayerClickLang(InventoryClickEvent e) throws IllegalArgumentException, IOException, ParseException {
        if (e.getCurrentItem() != null) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.langSwap)) {
                playerLang.SwapLang((Player) e.getWhoClicked());
            }
        }
    }

    @EventHandler
    public void PlayerClickCompassPose(InventoryClickEvent e)
            throws IllegalArgumentException, IOException, ParseException {
        if (e.getCurrentItem() != null) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.compassPose)) {
                compassEvents.setNextPose((Player) e.getWhoClicked());
            }
        }
    }

    @EventHandler
    public void PlayerClickResourcePack(InventoryClickEvent e)
            throws IllegalArgumentException, IOException, ParseException {
        if (e.getCurrentItem() != null) {
            if (persistentData.hasPersistentDataItemStack(e.getCurrentItem(), persistentData.customKey.resourcepack)) {
                ResourcePackHandler.setResourcePack((Player) e.getWhoClicked(), ResourcePackHandler.url,
                        ResourcePackHandler.sha1, ResourcePackHandler.text, ResourcePackHandler.force);
            }
        }
    }
}
