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
                } else {
                    npc.setAgeLock(true);
                    npc.setBaby();
                    npc.setCollidable(false);
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

    public static void summonNPCAll() {
        summonNPCChild();
        summonNPCCreatif();
        summonNPCHub();
        summonNPCOPPrison();
        summonNPCRPG();
        summonNPCSkyBlock();
        summonNPCSurvieAnarchie();
    }

    public static void summonNPCHub() {
        World spawn = Bukkit.getWorld("Spawn");
        Villager villager = null;
        if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.Hub)) {
            villager = Summon(new Location(spawn, 0.5, 64, 5.5, -180, 0), "Menu", false, Villager.Profession.FLETCHER,
                    Villager.Type.DESERT);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.Mainmenu);
            villager.setGlowing(true);

            villager = Summon(new Location(spawn, -2.5, 64, 12.5, -165, 0), "Guide", false,
                    Villager.Profession.LIBRARIAN,
                    Villager.Type.SAVANNA);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.Guide);
            villager.setGlowing(true);
        }
    }

    public static void summonNPCCreatif() {
        World spawn = Bukkit.getWorld("Spawn");
        Villager villager = null;
        if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.Creatif)) {
            villager = Summon(new Location(spawn, 0.5, 64, 5.5, -180, 0), "Menu", false, Villager.Profession.FLETCHER,
                    Villager.Type.DESERT);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.Mainmenu);
            villager.setGlowing(true);
        }
    }

    public static void summonNPCOPPrison() {
        World spawn = Bukkit.getWorld("Spawn");
        Villager villager = null;
        if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.OPPrison)) {
            villager = Summon(new Location(spawn, 0.5, 62, 13.5, 180, 0), "Menu", false, Villager.Profession.FLETCHER,
                    Villager.Type.DESERT);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.Mainmenu);
            villager.setGlowing(true);

            villager = Summon(new Location(spawn, -11.5, 63, 53.5, 180, 0), "Guide", false,
                    Villager.Profession.LIBRARIAN,
                    Villager.Type.SAVANNA);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.Guide);
            villager.setGlowing(true);

            villager = Summon(new Location(spawn, 12.5, 63, 53.5, 180, 0), "Pioche", false,
                    Villager.Profession.CARTOGRAPHER,
                    Villager.Type.TAIGA);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.pickaxe);
            villager.setGlowing(true);

            villager = Summon(new Location(spawn, 31.5, 63, 20.5,90, 0), "Mines", false,
                    Villager.Profession.CARTOGRAPHER,
                    Villager.Type.TAIGA);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.mines);
            villager.setGlowing(true);

            villager = Summon(new Location(spawn, -30.5, 63, 20.5, -90, 0), "Mines", false,
                    Villager.Profession.CARTOGRAPHER,
                    Villager.Type.TAIGA);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.mines);
            villager.setGlowing(true);
        }
    }

    public static void summonNPCRPG() {
        World spawn = Bukkit.getWorld("Spawn");
        Villager villager = null;
        if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.RPG)) {
            villager = Summon(new Location(spawn, 0.5, 64, 5.5, -180, 0), "Menu", false, Villager.Profession.FLETCHER,
                    Villager.Type.DESERT);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.Mainmenu);
            villager.setGlowing(true);

            villager = Summon(new Location(spawn, -2.5, 64, 12.5, -165, 0), "Guide", false,
                    Villager.Profession.LIBRARIAN,
                    Villager.Type.SAVANNA);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.Guide);
            villager.setGlowing(true);

            villager = Summon(new Location(spawn, 9.5, 64, 9.5, 135, 0), "Quêtes (Bientôt)", false,
                    Villager.Profession.LIBRARIAN,
                    Villager.Type.JUNGLE);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.Quetes);
            villager.setGlowing(true);
        }
    }

    public static void summonNPCSurvieAnarchie() {
        World spawn = Bukkit.getWorld("Spawn");
        Villager villager = null;
        if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.Survie)
                || ServerBootFile.getServerType().equals(ServerBootFile.serverType.Anarchie)) {
            villager = Summon(new Location(spawn, 0.5, 64, 5.5, -180, 0), "Menu", false, Villager.Profession.FLETCHER,
                    Villager.Type.DESERT);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.Mainmenu);
            villager.setGlowing(true);
            //
            villager = Summon(new Location(spawn, -2.5, 64, 12.5, -165, 0), "Guide", false,
                    Villager.Profession.LIBRARIAN,
                    Villager.Type.SAVANNA);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.Guide);
            villager.setGlowing(true);
            //
            villager = Summon(new Location(spawn, 5.5, 64, 10.5, 144, 0), "Téléportation ~ Monde", false,
                    Villager.Profession.FARMER,
                    Villager.Type.JUNGLE);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.tpWorld);
            villager.setGlowing(true);
            //
            villager = Summon(new Location(spawn, 2.5, 64, 11.5, 170, 0), "Téléportation ~ lit", false,
                    Villager.Profession.FARMER,
                    Villager.Type.JUNGLE);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.tpBed);

            villager = Summon(new Location(spawn, 7.5, 64, -0.5, -90, 30), "Commerçant", false,
                    Villager.Profession.CARTOGRAPHER,
                    Villager.Type.TAIGA);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.commercant);
            villager.setGlowing(true);
        }
    }

    public static void summonNPCSkyBlock() {
        World spawn = Bukkit.getWorld("Spawn");
        Villager villager = null;
        if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.SkyBlock)) {
            villager = Summon(new Location(spawn, 0.5, 64, 5.5, -180, 0), "Menu", false, Villager.Profession.FLETCHER,
                    Villager.Type.DESERT);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.Mainmenu);
            villager.setGlowing(true);

            villager = Summon(new Location(spawn, 5.0, 64, 2.0, 110, 0), "Guide", false,
                    Villager.Profession.LIBRARIAN,
                    Villager.Type.SAVANNA);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.Guide);
            villager.setGlowing(true);

            villager = Summon(new Location(spawn, -2.5, 64, 3.5, -148, 0), "Îles", false, Villager.Profession.FARMER,
                    Villager.Type.JUNGLE);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.Ile);
            villager.setGlowing(true);

            villager = Summon(new Location(spawn, 10.5, 64, 13.5, 135, 0), "Commerçant", false,
                    Villager.Profession.CARTOGRAPHER,
                    Villager.Type.TAIGA);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.commercant);
            villager.setGlowing(true);
        }
    }

    public static void summonNPCChild() {
        World spawn = Bukkit.getWorld("Spawn");
        Villager villager = null;
        if (!ServerBootFile.getServerType()
                .equals(ServerBootFile.serverType.Creatif)) {
            villager = Summon(new Location(spawn, 0, 64, 0, 0, 0), "Louis", true, Villager.Profession.NITWIT,
                    Villager.Type.PLAINS);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.Villager);
            //
            villager = Summon(new Location(spawn, 0, 64, 0, 0, 0), "Nicolas", true, Villager.Profession.NITWIT,
                    Villager.Type.PLAINS);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.Villager);
            //
            villager = Summon(new Location(spawn, 0, 64, 0, 0, 0), "Bertrand", true, Villager.Profession.NITWIT,
                    Villager.Type.PLAINS);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.Villager);
            //
            villager = Summon(new Location(spawn, 0, 64, 0, 0, 0), "Romain", true, Villager.Profession.NITWIT,
                    Villager.Type.PLAINS);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.Villager);
            //
            villager = Summon(new Location(spawn, 0, 64, 0, 0, 0), "Oliver", true, Villager.Profession.NITWIT,
                    Villager.Type.PLAINS);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.spawnVillager);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.custom);
            entityPersistentData.setPersistentDataEntity(villager, entityPersistentData.customKey.Villager);
        }
    }

    public static void start() {
        Plugin plugin = JavaPlugin.getPlugin(main.class);
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            try {
                keepSpawnInMemory();
                DeleteNPCOnSpawn();
                summonNPCAll();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 20 * 10, 20 * 60 * 30);
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
