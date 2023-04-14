package ghub.fr.world.api;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import ghub.fr.menu.island.island;
import ghub.fr.menu.island.bonus.bonusPlayerFile;
import ghub.fr.menu.shop.specials.bonus.fly;
import ghub.fr.menu.shop.specials.bonus.teleportationAura;
import ghub.fr.system.ServerBootFile;
import ghub.fr.text.playerLang;
import ghub.fr.text.textTranslation;

import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.CompletableFuture;

public class teleportation {
    public static void Teleport(Player player, String world, Boolean sync) throws IOException, ParseException {
        if (Bukkit.getWorld(world) == null) {
            worldManager.Generate(world, false, World.Environment.NORMAL, WorldType.NORMAL, true);
        }
        Location spawn = Bukkit.getWorld(world).getSpawnLocation();
        spawn.set(spawn.getX() + 0.5, spawn.getY() + 0.5, spawn.getZ() + 0.5);
        Teleport(player, world, sync, spawn);
    }

    public static void Teleport(Player player, String world, Boolean sync, Location location)
            throws IOException, ParseException {
        if (Bukkit.getWorld(world) == null) {
            worldManager.Generate(world, false, World.Environment.NORMAL, WorldType.NORMAL, true);
        }
        
        CompletableFuture<Boolean> tp = null;
        if (sync) {
            player.teleport(location);
        } else {
            tp = player.teleportAsync(location);
        }

        if (tp == null) {
            AfterTeleportation(player, world);
        } else {
            tp.thenRun(() -> {
                try {
                    AfterTeleportation(player, world);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static void AfterTeleportation(Player player, String world) throws IOException, ParseException {
        player.sendMessage(textTranslation.Teleportation(playerLang.getPlayerLang(player), world));
        textTranslation.GameRulesMessage(Bukkit.getWorld(world), player);
        PlayerTeleportationRules(player, world);
        IgnoreSleeping(player, world);
        if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.SkyBlock)) {
            bonusPlayerFile.updateAllIslandLvl(player);
        }
    }

    public static void PlayerTeleportationRules(Player player, String world) throws IOException, ParseException {
        if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.SkyBlock)) {
            if (world.startsWith("i.") && !island.IsInHerIsland(player)) {
                player.setGameMode(GameMode.SPECTATOR);
            } else {
                defaultGameMode(player, world);
            }
        } else if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.Creatif)) {
            player.setGameMode(GameMode.CREATIVE);
            player.setFlying(true);
        } else {
            defaultGameMode(player, world);
        }
    }

    public static void defaultGameMode(Player player, String world) throws IOException, ParseException {
        player.setGameMode(GameMode.SURVIVAL);
        player.setFallDistance(0);
        TeleportPlayerPotion(player);
        fly.setfly(player, world);
        teleportationAura.FireWorkSpawn(player);
    }

    public static void TeleportPlayerPotion(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 30 * 20, 9));
        player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 15 * 20, 4));
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 10 * 20, 2));
    }

    public static void IgnoreSleeping(Player player, String world) throws IOException {
        player.setSleepingIgnored(
                world.startsWith("Spawn") || (world.startsWith("i.") && !island.IsInHerIsland(player)));
    }
}