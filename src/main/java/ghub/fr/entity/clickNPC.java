package ghub.fr.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import ghub.fr.menu.compass.compassMenu;
import ghub.fr.menu.island.islandMenu;
import ghub.fr.menu.shop.classique.shopMenu;
import ghub.fr.text.lang;
import ghub.fr.text.playerLang;
import ghub.fr.text.textTranslation;

import java.io.IOException;
import java.text.ParseException;
import java.util.Random;

public class clickNPC implements Listener {
    @EventHandler
    public void onPlayerInteractNpc(PlayerInteractEntityEvent e)
            throws IllegalArgumentException, IOException, ParseException {
        Entity entity = e.getRightClicked();
        if (entityPersistentData.isCustomEntity(entity)) {
            e.setCancelled(true);
            Player player = e.getPlayer();
            if (entityPersistentData.hasPersistentDataEntity(entity, entityPersistentData.customKey.commercant)) {
                Commerçant(player);
            } else if (entityPersistentData.hasPersistentDataEntity(entity, entityPersistentData.customKey.Guide)) {
                Guide(player);
            } else if (entityPersistentData.hasPersistentDataEntity(entity, entityPersistentData.customKey.Ile)) {
                Ile(player);
            } else if (entityPersistentData.hasPersistentDataEntity(entity, entityPersistentData.customKey.Quetes)) {
                QuestMenu(player);
            } else if (entityPersistentData.hasPersistentDataEntity(entity, entityPersistentData.customKey.Villager)) {
                RandomMessage(player);
            } else if (entityPersistentData.hasPersistentDataEntity(entity, entityPersistentData.customKey.Mainmenu)) {
                compassMenu.menuCompass(player);
            } else if (entityPersistentData.hasPersistentDataEntity(entity, entityPersistentData.customKey.tpWorld)) {
                tpWorld(player);
            } else if (entityPersistentData.hasPersistentDataEntity(entity, entityPersistentData.customKey.tpBed)) {
                tpBed(player);
            }
        }
    }

    public void tpWorld(Player player) {
    }

    public void tpBed(Player player) {
    }

    public void QuestMenu(Player player) {
    }

    public void Commerçant(Player player) throws IOException {
        shopMenu.OpenShop(player);
    }

    public void Ile(Player player) throws IOException, ParseException {
        islandMenu.islandCompass(player);
    }

    public void Guide(Player player) throws IOException {
        lang.languages languages = playerLang.getPlayerLang(player);
        player.sendMessage(textTranslation.helpMSG(languages));
    }

    public void RandomMessage(Player player) throws IOException {
        Random random = new Random();
        lang.languages languages = playerLang.getPlayerLang(player);
        player.sendMessage(
                textTranslation.VillagerName(languages) + textTranslation.randomMSGNPC(languages, random.nextInt(16)));
    }
}