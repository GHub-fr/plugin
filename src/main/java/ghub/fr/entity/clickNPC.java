package ghub.fr.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import ghub.fr.menu.compass.compassMenu;
import ghub.fr.menu.island.islandMenu;
import ghub.fr.menu.prison.minesMenu;
import ghub.fr.menu.prison.pickaxeMenu;
import ghub.fr.menu.shop.classique.shopMenu;
import ghub.fr.text.lang;
import ghub.fr.text.playerLang;
import ghub.fr.text.textTranslation;
import ghub.fr.world.api.teleportation;

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
            } else if (entityPersistentData.hasPersistentDataEntity(entity, entityPersistentData.customKey.guide)) {
                Guide(player);
            } else if (entityPersistentData.hasPersistentDataEntity(entity, entityPersistentData.customKey.ile)) {
                Ile(player);
            } else if (entityPersistentData.hasPersistentDataEntity(entity, entityPersistentData.customKey.quetes)) {
                QuestMenu(player);
            } else if (entityPersistentData.hasPersistentDataEntity(entity, entityPersistentData.customKey.villager)) {
                RandomMessage(player);
            } else if (entityPersistentData.hasPersistentDataEntity(entity, entityPersistentData.customKey.mainmenu)) {
                compassMenu.menuCompass(player);
            } else if (entityPersistentData.hasPersistentDataEntity(entity, entityPersistentData.customKey.tpworld)) {
                tpWorld(player);
            } else if (entityPersistentData.hasPersistentDataEntity(entity, entityPersistentData.customKey.tpbed)) {
                tpBed(player);
            } else if (entityPersistentData.hasPersistentDataEntity(entity, entityPersistentData.customKey.mines)) {
                mineMenu(player);
            } else if (entityPersistentData.hasPersistentDataEntity(entity, entityPersistentData.customKey.pickaxe)) {
                pickaxeMenu(player);
            }
        }
    }

    public void mineMenu(Player player) throws IOException, ParseException {
        minesMenu.minesMenu(player);
    }

    public void pickaxeMenu(Player player) throws IOException, ParseException {
        pickaxeMenu.pickaxeMenu(player);
    }

    public void tpWorld(Player player) throws IOException, ParseException {
        teleportation.Teleport(player, "world", false);
    }

    public void tpBed(Player player) throws IOException, ParseException {
        boolean canTP = false;
        boolean obstruct = false;

        if (player.getPotentialBedLocation() != null) {
            canTP = true;
            if (player.getBedSpawnLocation() == null) {
                obstruct = true;
            }
        }

        lang.languages lang = playerLang.getPlayerLang(player);
        if (!canTP) {
            player.sendMessage(textTranslation.noBed(lang));
        }
        if (canTP) {
            if (obstruct) {
                player.sendMessage(textTranslation.bedObstrued(lang));
            } else {
                teleportation.Teleport(player, "world", false, player.getBedSpawnLocation());
            }
        }
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