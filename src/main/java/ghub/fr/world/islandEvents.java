package ghub.fr.world;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;

import ghub.fr.menu.api.persistentData;
import ghub.fr.menu.island.island;
import ghub.fr.menu.island.bonus.bonusPlayerFile;
import ghub.fr.world.api.worldManager;

import java.io.IOException;

public class islandEvents implements Listener {
    public boolean CoinFlip() {
        return Math.random() < 0.5;
    }

    @EventHandler
    public void onObsidianToLava(PlayerInteractEvent e) throws IOException {
        if (island.IsInHerIsland(e.getPlayer())) {
            if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if (e.getClickedBlock().getType().equals(Material.OBSIDIAN)) {
                    if (e.getItem() != null && e.getItem().getType().equals(Material.BUCKET)) {

                        if (e.getItem().getAmount() > 1) {
                            e.getItem().setAmount(e.getItem().getAmount() - 1);
                            if (e.getPlayer().getInventory().addItem(new ItemStack(Material.LAVA_BUCKET)).size() > 0) {
                                e.getPlayer().getWorld().dropItem(e.getPlayer().getLocation(),
                                        new ItemStack(Material.LAVA_BUCKET));
                            }
                        } else {
                            e.getItem().setType(Material.LAVA_BUCKET);
                        }
                        e.getPlayer().setCooldown(Material.LAVA_BUCKET, 30);
                        e.getClickedBlock().setType(Material.AIR);
                        e.getPlayer().updateInventory();
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerTeleport(PlayerPortalEvent e) {
        Player player = e.getPlayer();
        player.sendMessage("Starting event trigger");
        if (player.getWorld().getName().startsWith("i.")) {
            player.sendMessage("World match i.[x]");
            Location to = e.getTo();
            Location from = e.getFrom();

            if (player.getWorld().getEnvironment().equals(World.Environment.NORMAL)) {
                if (e.getCause().equals(PlayerTeleportEvent.TeleportCause.NETHER_PORTAL)) {
                    player.sendMessage("Over world to nether");
                    worldManager.Generate(from.getWorld().getName() + "_nether", true, World.Environment.NETHER,
                            WorldType.NORMAL, true);
                    to.setWorld(Bukkit.getWorld(from.getWorld().getName() + "_nether"));
                } else if (e.getCause().equals(PlayerTeleportEvent.TeleportCause.END_PORTAL)) {
                    player.sendMessage("Overworld to end");
                    worldManager.Generate(from.getWorld().getName() + "_the_end", true, World.Environment.THE_END,
                            WorldType.NORMAL, false);
                    to.setWorld(Bukkit.getWorld(from.getWorld().getName() + "_the_end"));
                }
            } else if (player.getWorld().getEnvironment().equals(World.Environment.NETHER)) {
                if (e.getCause().equals(PlayerTeleportEvent.TeleportCause.NETHER_PORTAL)) {
                    player.sendMessage("Nether to overworld");
                    worldManager.Generate(from.getWorld().getName().replace("_nether", ""), true,
                            World.Environment.NORMAL, WorldType.NORMAL, true);
                    to.setWorld(Bukkit.getWorld(from.getWorld().getName().replace("_nether", "")));
                }
            } else if (player.getWorld().getEnvironment().equals(World.Environment.THE_END)) {
                if (e.getCause().equals(PlayerTeleportEvent.TeleportCause.END_PORTAL)) {
                    player.sendMessage("End to overworld");
                    worldManager.Generate(from.getWorld().getName().replace("_the_end", ""), true,
                            World.Environment.NORMAL, WorldType.NORMAL, true);
                    to.setWorld(Bukkit.getWorld(from.getWorld().getName().replace("_the_end", "")));
                }
            }
            e.setTo(to);
            player.sendMessage("dest = " + to);
            player.sendMessage(to.getWorld().getName());
        }
    }

    @EventHandler
    public void onCobbleGenerate(BlockFormEvent e) throws IOException {
        if (e.getNewState().getType().equals(Material.COBBLESTONE)
                || e.getNewState().getType().equals(Material.STONE)) {
            String worldName = e.getBlock().getWorld().getName().split("\\.")[1];
            int WorldNameCast = Integer.valueOf(worldName);
            int lvl = bonusPlayerFile.getLvlIsland(WorldNameCast, persistentData.customKey.bonusores);

            if (CoinFlip()) {
                e.getNewState().setType(Material.MOSSY_COBBLESTONE);
            } else {
                if (lvl >= 500) {
                    e.getNewState().setType(Material.COBBLED_DEEPSLATE);
                }
            }

            if (CoinFlip() && lvl >= 20) {
                if (CoinFlip()) {
                    e.getNewState().setType(Material.IRON_ORE);
                } else {
                    e.getNewState().setType(Material.COAL_ORE);
                }

                if (CoinFlip() && lvl >= 50) {
                    e.getBlock().getWorld().spawnParticle(Particle.CRIT_MAGIC, e.getBlock().getLocation(), 200);
                    e.getBlock().getWorld().playSound(e.getBlock().getLocation(), Sound.BLOCK_ANVIL_PLACE, 0.5F, 5);

                    if (CoinFlip()) {
                        e.getNewState().setType(Material.LAPIS_ORE);
                    } else {
                        e.getNewState().setType(Material.REDSTONE_ORE);
                    }

                    if (CoinFlip() && lvl >= 100) {

                        if (CoinFlip()) {
                            e.getNewState().setType(Material.GOLD_ORE);
                        } else {
                            e.getNewState().setType(Material.COPPER_ORE);
                        }

                        if (CoinFlip() && lvl >= 250) {
                            e.getNewState().setType(Material.EMERALD_ORE);
                            e.getBlock().getWorld().spawnParticle(Particle.CRIT_MAGIC,
                                    e.getBlock().getLocation(), 200);
                            e.getBlock().getWorld().playSound(e.getBlock().getLocation(),
                                    Sound.ITEM_TOTEM_USE, 1F, 0);

                            if (CoinFlip() && lvl >= 500) {
                                e.getNewState().setType(Material.DIAMOND_ORE);
                                e.getBlock().getWorld().spawnParticle(Particle.CRIT_MAGIC,
                                        e.getBlock().getLocation(), 200);
                                e.getBlock().getWorld().playSound(e.getBlock().getLocation(),
                                        Sound.ENTITY_PLAYER_LEVELUP, 1.5F, 0);

                                if (CoinFlip() && lvl >= 1000) {
                                    e.getNewState().setType(Material.ANCIENT_DEBRIS);
                                    e.getBlock().getWorld().spawnParticle(Particle.FLAME,
                                            e.getBlock().getLocation(), 200);
                                    e.getBlock().getWorld().playSound(e.getBlock().getLocation(),
                                            Sound.BLOCK_RESPAWN_ANCHOR_AMBIENT, 1.5F, 0);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}