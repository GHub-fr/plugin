package ghub.fr.menu.shop.specials.bonus;

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
                            if (worldName.startsWith("i.")) {
                                String worldNameSplitDot = worldName.split(".")[1];
                                String worldNameSplitUnderScore = worldNameSplitDot.split("_")[0];
                                int i = Integer.valueOf(worldNameSplitUnderScore);

                                if (getDataStorage.islandAutoSell(i).exists()) {
                                    FileConfiguration fileConfiguration = YamlConfiguration
                                            .loadConfiguration(getDataStorage.islandAutoSell(i));
                                    List<Location> locationList = (List<Location>) fileConfiguration
                                            .getList("autoSell");

                                    for (Location loc : locationList) {
                                        Block block = world.getBlockAt(loc);
                                        if (block instanceof InventoryHolder) {
                                            InventoryHolder ih = (InventoryHolder) block.getState();
                                            Inventory inventory = ih.getInventory();

                                            int total = 0;
                                            int players = island.GetPlayerList(i).size();
                                            List<String> playerList = island.GetPlayerList(i);

                                            if (inventory.getContents().length >= 1) {
                                                for (ItemStack item : inventory.getContents()) {
                                                    if (shopPrice.getPrix(item.getType()) != Integer.MAX_VALUE) {
                                                        total += (shopPrice.getPrix(item.getType()) * item.getAmount());
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
            }.runTaskTimerAsynchronously(plugin, (20 * 60 * 10), (20 * 60 * 30));
        }
    }

    public static void addChest(Block block, int island) throws IOException {
        if (!getDataStorage.islandAutoSell(island).exists()) {
            getDataStorage.islandAutoSell(island).createNewFile();
        }

        FileConfiguration fileConfiguration = YamlConfiguration
                .loadConfiguration(getDataStorage.islandAutoSell(island));
        List<Location> locationList = (List<Location>) fileConfiguration.getList("autoSell");
        locationList.add(block.getLocation());
        fileConfiguration.set("autoSell", locationList);
        fileConfiguration.save(getDataStorage.islandAutoSell(island));
    }
}
