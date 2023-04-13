package ghub.fr.entity;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import ghub.fr.main.main;
import ghub.fr.system.ServerBootFile;
import ghub.fr.world.api.worldManager;

public class spawnNPC {
    public static Villager Summon(Location location, String nom, boolean IA, Villager.Profession profession,
            Villager.Type type) {
        if (location != null) {
            if (location.getWorld() != null) {
                Villager npc = (Villager) location.getWorld().spawnEntity(location, EntityType.VILLAGER);
                if (!IA) {
                    npc.setAI(false);
                    npc.setSilent(true);
                }
                npc.setVillagerType(type);
                npc.setProfession(profession);
                npc.setCanPickupItems(false);
                npc.setCustomNameVisible(true);
                npc.setInvulnerable(true);
                String nomFinal = "§6{§fNPC§6} §6" + nom;
                npc.setCustomName(nomFinal);
                return npc;
            }
        }
        return null;
    }

    public static void summonNPC() {
        World spawn = Bukkit.getWorld("Spawn");
        Villager villager = null;

        villager = Summon(new Location(spawn, 0.5, 64, 5.5, -180, 0), "Menu", false, Villager.Profession.FLETCHER,
                Villager.Type.DESERT);
        entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
        entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
        entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.Mainmenu);

        villager = Summon(new Location(spawn, -2.5, 64, 12.5, -165, 0), "Guide", false, Villager.Profession.LIBRARIAN,
                Villager.Type.SAVANNA);
        entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
        entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
        entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.Guide);

        villager = Summon(new Location(spawn, -6.5, 64, 17.5, 0, 0), "Commerçant", false,
                Villager.Profession.CARTOGRAPHER,
                Villager.Type.TAIGA);
        entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
        entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
        entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.commercant);
        //
        villager = Summon(new Location(spawn, -15.5, 64, 8.5, 0, 0), "Commerçant", false,
                Villager.Profession.CARTOGRAPHER,
                Villager.Type.TAIGA);
        entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
        entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
        entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.commercant);
        //
        villager = Summon(new Location(spawn, -15.5, 64, 25.5, -180, 0), "Commerçant", false,
                Villager.Profession.CARTOGRAPHER,
                Villager.Type.TAIGA);
        entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
        entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
        entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.commercant);
        //
        villager = Summon(new Location(spawn, -24.5, 64, 14.5, 0, 35), "Commerçant", false, Villager.Profession.CLERIC,
                Villager.Type.SAVANNA);
        entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
        entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
        entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.commercant);
        //
        villager = Summon(new Location(spawn, -4.5, 64, 22.5, 90, 20), "Commerçant", false, Villager.Profession.ARMORER,
                Villager.Type.SNOW);
        entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
        entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
        entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.commercant);

        /* if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.Anarchie)) {
            villager = Summon(new Location(spawn, 0, 64, 0, 0, 0), "ABC", false, Villager.Profession.NITWIT,
                    Villager.Type.PLAINS);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
        }
        if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.Creatif)) {
            villager = Summon(new Location(spawn, 0, 64, 0, 0, 0), "ABC", false, Villager.Profession.NITWIT,
                    Villager.Type.PLAINS);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
        }
        if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.Hub)) {
            villager = Summon(new Location(spawn, 0, 64, 0, 0, 0), "ABC", false, Villager.Profession.NITWIT,
                    Villager.Type.PLAINS);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
        }
        */
        if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.SkyBlock)) {
            villager = Summon(new Location(spawn, 9.5, 64, 9.5, 135, 0), "Îles", false, Villager.Profession.FARMER,
                    Villager.Type.JUNGLE);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.Ile);
        }
        /*
        if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.Survie)) {
            villager = Summon(new Location(spawn, 0, 64, 0, 0, 0), "ABC", false, Villager.Profession.NITWIT,
                    Villager.Type.PLAINS);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
        }
        if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.RPG)) {
            villager = Summon(new Location(spawn, 0, 64, 0, 0, 0), "ABC", false, Villager.Profession.NITWIT,
                    Villager.Type.PLAINS);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
        } */

        villager = Summon(new Location(spawn, 0, 64, 0, 0, 0), "?", true, Villager.Profession.NITWIT,
                Villager.Type.PLAINS);
        entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
        entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
        entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.Villager);
        //
        villager = Summon(new Location(spawn, 0, 64, 0, 0, 0), "?", true, Villager.Profession.NITWIT,
                Villager.Type.PLAINS);
        entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
        entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
        entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.Villager);
        //
        villager = Summon(new Location(spawn, 0, 64, 0, 0, 0), "?", true, Villager.Profession.NITWIT,
                Villager.Type.PLAINS);
        entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
        entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
        entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.Villager);
        //
        villager = Summon(new Location(spawn, 0, 64, 0, 0, 0), "?", true, Villager.Profession.NITWIT,
                Villager.Type.PLAINS);
        entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
        entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
        entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.Villager);
        //
        villager = Summon(new Location(spawn, 0, 64, 0, 0, 0), "?", true, Villager.Profession.NITWIT,
                Villager.Type.PLAINS);
        entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
        entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
        entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.Villager);
    }

    public static void start() {
        Plugin plugin = JavaPlugin.getPlugin(main.class);
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            try {
                keepSpawnInMemory();
                DeleteNPCOnSpawn();
                summonNPC();
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

    public static void DeleteNPCOnSpawn() {
        World world = Bukkit.getWorld("Spawn");
        List<Entity> list = world.getEntities();

        for (Entity entity : list) {
            if (entity.getType().equals(EntityType.VILLAGER)) {
                if (entityPersistentData.isCustomEntity(entity)) {
                    if (entityPersistentData.hasPersistentDataEntity(entity,
                            entityPersistentData.customKey.spawnVillager)) {
                        Villager villager = (Villager) entity;
                        villager.remove();
                    }
                }
            }
        }
    }

}
