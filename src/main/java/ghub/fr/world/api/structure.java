package ghub.fr.world.api;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.block.Barrel;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import ghub.fr.main.main;
import ghub.fr.system.getDataStorage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class structure {
    public static void saveStructure(double x1, double x2, double y1, double y2, double z1, double z2, World world,
            String structureName) throws IOException {
        Plugin plugin = JavaPlugin.getPlugin(main.class);
        Bukkit.getScheduler().runTaskAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {
                try {
                    double xPos1 = Math.min(x1, x2);
                    double xPos2 = Math.max(x1, x2);
                    double yPos1 = Math.min(y1, y2);
                    double yPos2 = Math.max(y1, y2);
                    double zPos1 = Math.min(z1, z2);
                    double zPos2 = Math.max(z1, z2);
                    Vector minimumPoint = new Vector(xPos1, yPos1, zPos1);
                    Vector maximumPoint = new Vector(xPos2, yPos2, zPos2);

                    File file = getDataStorage.structureFile(structureName);
                    if (file.exists()) {
                        file.delete();
                    }
                    file.createNewFile();
                    FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);

                    if (world != null) {
                        int blockCount = 0;
                        for (int x = minimumPoint.getBlockX(); x <= maximumPoint.getBlockX(); x++) {
                            for (int y = minimumPoint.getBlockY(); y <= maximumPoint.getBlockY()
                                    && y <= world.getMaxHeight(); y++) {
                                for (int z = minimumPoint.getBlockZ(); z <= maximumPoint.getBlockZ(); z++) {
                                    if (!world.getBlockAt(x, y, z).getType().equals(Material.AIR)
                                            && !world.getBlockAt(x, y, z).getType().equals(Material.VOID_AIR)
                                            && !world.getBlockAt(x, y, z).getType().equals(Material.CAVE_AIR)) {
                                        blockCount++;
                                        fileConfiguration.set(blockCount + ".type",
                                                world.getBlockAt(x, y, z).getType().toString());
                                        fileConfiguration.set(blockCount + ".location.x",
                                                world.getBlockAt(x, y, z).getLocation().getBlockX());
                                        fileConfiguration.set(blockCount + ".location.y",
                                                world.getBlockAt(x, y, z).getLocation().getBlockY());
                                        fileConfiguration.set(blockCount + ".location.z",
                                                world.getBlockAt(x, y, z).getLocation().getBlockZ());
                                        fileConfiguration.set(blockCount + ".data",
                                                world.getBlockAt(x, y, z).getBlockData().getAsString());
                                    }
                                }
                            }
                        }
                        if (blockCount > 0) {
                            fileConfiguration.set("blockCount", blockCount);
                            fileConfiguration.save(file);
                        } else {
                            file.delete();
                        }
                    }
                } catch (Exception e) {
                }
            }
        });
    }

    public static void setCustomBlock(Block block) {
        if (block.getWorld().getName().startsWith("i.")) {
            if (block.getType().equals(Material.CHEST)) {
                Chest chest = (Chest) block.getState();
                ItemStack itemStack1 = new ItemStack(Material.OAK_SAPLING, 2);
                ItemStack itemStack2 = new ItemStack(Material.STICK, 10);
                ItemStack itemStack3 = new ItemStack(Material.OAK_LOG, 6);
                ItemStack itemStack4 = new ItemStack(Material.OAK_PLANKS, 36);
                ItemStack itemStack5 = new ItemStack(Material.LAVA_BUCKET, 1);
                chest.getInventory().addItem(itemStack1, itemStack2, itemStack3, itemStack4, itemStack5);
            }
            if (block.getType().equals(Material.BARREL)) {
                Barrel barrel = (Barrel) block.getState();
                ItemStack itemStack1 = new ItemStack(Material.WHEAT_SEEDS, 2);
                ItemStack itemStack2 = new ItemStack(Material.BREAD, 3);
                ItemStack itemStack3 = new ItemStack(Material.COOKED_BEEF, 16);
                ItemStack itemStack4 = new ItemStack(Material.WHEAT, 9);
                ItemStack itemStack5 = new ItemStack(Material.RED_BED, 1);
                barrel.getInventory().addItem(itemStack1, itemStack2, itemStack3, itemStack4, itemStack5);

            }
        }
    }

    public static void setStructure(String structureName, String worldName) {
        setStructure(structureName, worldName, 0, 0, 0);
    }

    public static void setStructure(String structureName, String worldName, int x, int y, int z) {
        File file = getDataStorage.structureFile(structureName);
        if (file.exists()) {
            worldManager.Generate(worldName, false, World.Environment.NORMAL, WorldType.NORMAL, true);
            FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
            int blockCount = fileConfiguration.getInt("blockCount");
            int i = 1;
            World world = Bukkit.getWorld(worldName);
            while (i <= blockCount) {
                Block block = world.getBlockAt(fileConfiguration.getInt(i + ".location.x") + x,
                        fileConfiguration.getInt(i + ".location.y") + y,
                        fileConfiguration.getInt(i + ".location.z") + z);
                block.setType(Material.valueOf(fileConfiguration.getString(i + ".type")));
                block.setBlockData(Bukkit.getServer().createBlockData(fileConfiguration.getString(i + ".data")));
                i++;
                setCustomBlock(block);
            }
        }
    }

    public static List<String> Structures() {
        List<String> argsToReturn = new ArrayList<String>();
        File file = getDataStorage.structureFolder();
        for (File files : file.listFiles()) {
            argsToReturn.add(files.getName().split(".yml")[0]);
        }
        return argsToReturn;
    }
}