package ghub.fr.entity;

import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import ghub.fr.main.main;
import ghub.fr.system.getDataStorage;
import ghub.fr.system.gold;
import ghub.fr.world.api.worldManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class armorStand {
    static ArrayList<OfflinePlayer> listTopGold = new ArrayList<>();

    public static ArmorStand Summon(Location location, String text) {
        if (location != null) {
            if (location.getWorld() != null) {
                ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
                armorStand.setVisible(false);
                armorStand.setGravity(false);
                armorStand.setCanPickupItems(false);
                armorStand.setCollidable(false);
                armorStand.setInvulnerable(true);
                armorStand.setCustomName(text);
                armorStand.setCustomNameVisible(true);
                return armorStand;
            }
        }
        return null;
    }

    public static ArmorStand summonTopGold(Location location, String text) {
        ArmorStand armorStand = Summon(location, text);
        entityPersistentData.setPersistentDataEntity(armorStand, entityPersistentData.customKey.topGold);
        entityPersistentData.setPersistentDataEntity(armorStand, entityPersistentData.customKey.custom);
        return armorStand;
    }

    public static void start() {
        Plugin plugin = JavaPlugin.getPlugin(main.class);
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            try {
                // if world != null
                keepSpawnInMemory();
                listTopGold.clear();
                getTopGold();
                PlayerCount();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 20 * 10, 20 * 60);
    }

    public static void keepSpawnInMemory() {
        if (Bukkit.getWorld("Spawn") == null) {
            worldManager.Generate("Spawn", false, World.Environment.NORMAL, WorldType.FLAT, true);
        }
        World world = Bukkit.getWorld("Spawn");
        for (int x = 0; x < 5; x++) {
            for (int z = 0; z < 5; z++) {
                if (!world.isChunkForceLoaded(x, z)) {
                    world.setChunkForceLoaded(x, z, true);
                }
            }
        }
    }

    public static void getTopGold() {
        int topPlayerGold = 0;
        OfflinePlayer topPlayer = null;
        for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
            if (getDataStorage.playerExist(offlinePlayer) && !listTopGold.contains(offlinePlayer)) {
                if (topPlayerGold < gold.GetGold(offlinePlayer)) {
                    topPlayerGold = gold.GetGold(offlinePlayer);
                    topPlayer = offlinePlayer;
                }
            }
        }
        listTopGold.add(topPlayer);
        if (listTopGold.size() <= 2) {
            getTopGold();
        }
    }

    public static void DeleteArmorStandOnSpawn() {
        World world = Bukkit.getWorld("Spawn");
        List<Entity> list = world.getEntities();

        for (Entity entity : list) {
            if (entity.getType().equals(EntityType.ARMOR_STAND)) {
                if (entityPersistentData.isCustomEntity(entity)) {
                    if (entityPersistentData.hasPersistentDataEntity(entity, entityPersistentData.customKey.topGold)) {
                        ArmorStand armorStand = (ArmorStand) entity;
                        armorStand.remove();
                    }
                }
            }
        }
    }

    public static void PlayerCount() throws IOException {
        if (!listTopGold.isEmpty()) {
            DeleteArmorStandOnSpawn();
            Location location = new Location(Bukkit.getWorld("Spawn"), 0.5, 66.0, 19.5);

            summonTopGold(location, "§6Joueurs inscrits §r: §2" + Bukkit.getOfflinePlayers().length);
            location.setY(location.getY() - 0.5);

            summonTopGold(location, "§f< §6Top Gold§f >");
            location.setY(location.getY() - 0.25);

            OfflinePlayer player1 = listTopGold.get(0);
            OfflinePlayer player2 = listTopGold.get(1);
            OfflinePlayer player3 = listTopGold.get(2);

            if (player1 != null) {
                summonTopGold(location, "§4N°1 §f: §2" + player1.getName());
                location.setY(location.getY() - 0.25);
                summonTopGold(location, "§6" + gold.GetGoldFormat(player1) + " §fgold");
                location.setY(location.getY() - 0.4);
            }
            if (player2 != null) {
                summonTopGold(location, "§6N°2 §f: §2" + player2.getName());
                location.setY(location.getY() - 0.25);
                summonTopGold(location, "§6" + gold.GetGoldFormat(player2) + " §fgold");
                location.setY(location.getY() - 0.4);
            }
            if (player3 != null) {
                summonTopGold(location, "§eN°3 §f: §2" + player3.getName());
                location.setY(location.getY() - 0.25);
                summonTopGold(location, "§6" + gold.GetGoldFormat(player3) + " §fgold");
            }
        }
    }
}