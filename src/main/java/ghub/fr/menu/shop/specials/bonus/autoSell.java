package ghub.fr.menu.shop.specials.bonus;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import ghub.fr.main.main;
import ghub.fr.menu.island.island;
import ghub.fr.menu.shop.classique.shopPrice;
import ghub.fr.system.ServerBootFile;
import ghub.fr.system.getDataStorage;
import ghub.fr.system.gold;
import ghub.fr.system.ServerBootFile.serverType;

public class autoSell {
    public static void autoSellStarter() {
        if (ServerBootFile.getServerType().equals(serverType.SkyBlock)) {
            Plugin plugin = JavaPlugin.getPlugin(main.class);
            new BukkitRunnable() {
                @Override
                public void run() {
                    try {
                        for (World world : Bukkit.getWorlds()) {
                            String worldName = world.getName();
                            System.out.println("world" + worldName);
                            if (worldName.startsWith("i.")) {
                                String worldNameSplitDot = worldName.split(".")[1];
                                System.out.println("split" + worldNameSplitDot);
                                String worldNameSplitUnderScore = worldNameSplitDot.split("_")[0];
                                int i = Integer.valueOf(worldNameSplitUnderScore);

                                System.out.println("ile" + i);

                                if (getDataStorage.islandAutoSell(i).exists()) {
                                    FileConfiguration fileConfiguration = YamlConfiguration
                                            .loadConfiguration(getDataStorage.islandAutoSell(i));
                                    int totalChest = fileConfiguration.getInt("totalChest");
                                    int actual = 0;
                                    int total = 0;
                                    int players = island.GetPlayerList(i).size();
                                    List<String> playerList = island.GetPlayerList(i);

                                    System.out.println("total" + totalChest);

                                    while (actual < totalChest) {
                                        Location loc = new Location(
                                                Bukkit.getWorld(fileConfiguration.getString(actual + "." + "world")),
                                                fileConfiguration.getInt(actual + "." + "x"),
                                                fileConfiguration.getInt(actual + "." + "y"),
                                                fileConfiguration.getInt(actual + "." + "z"));

                                        System.out.println(loc.getWorld().getName());

                                        Block block = world.getBlockAt(loc);
                                        if (block instanceof InventoryHolder) {
                                            InventoryHolder ih = (InventoryHolder) block.getState();
                                            Inventory inventory = ih.getInventory();

                                            System.out.println("holder" + block.getType());

                                            if (inventory.getContents().length >= 1) {
                                                System.out.println("content" + inventory.getContents().length);
                                                for (ItemStack item : inventory.getContents()) {
                                                    System.out.println("type" + item.getType());
                                                    if (shopPrice.getPrix(item.getType()) != Integer.MAX_VALUE) {
                                                        total += (shopPrice.getPrix(item.getType()) * item.getAmount());
                                                        System.out.println("totaux" + total);
                                                        inventory.remove(item);
                                                    }
                                                }
                                            }

                                            if (total > 0) {
                                                for (String uuid : playerList) {
                                                    gold.AddGold(Bukkit.getOfflinePlayer(uuid), total / players);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.runTaskTimerAsynchronously(plugin, (20 * 60 * 2), (20 * 60 * 30));
        }

    }

    public static void addChest(Block block, int island) throws IOException {
        File file = getDataStorage.islandAutoSell(island);
        if (!file.exists()) {
            file.createNewFile();
        }

        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);

        int totalChest = fileConfiguration.getInt("totalChest");

        // Check for all value possible if world != null
        // if yes it mean there is a chest registered
        // cancel it and dont remove item on hand

        fileConfiguration.set(totalChest + "." + "x", block.getX());
        fileConfiguration.set(totalChest + "." + "y", block.getY());
        fileConfiguration.set(totalChest + "." + "z", block.getZ());
        fileConfiguration.set(totalChest + "." + "world", block.getWorld().getName());

        totalChest++;
        fileConfiguration.set("totalChest", totalChest);

        fileConfiguration.save(file);
    }
}
