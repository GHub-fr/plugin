package ghub.fr.commands.api;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import ghub.fr.main.main;
import ghub.fr.system.getDataStorage;
import ghub.fr.world.api.worldManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class test implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            if (isAdmin.isAdmin(sender)) {
                sender.sendMessage("run");
                setStructure(sender, "spawn", "Spawn", 0, 0, 0, 10);
                sender.sendMessage("stop");
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void setStructure(
            CommandSender sender, String structureName, String worldName, int x, int y, int z,
            int waitTime) {
        Plugin plugin = JavaPlugin.getPlugin(main.class);

        sender.sendMessage("run schedule");
        new BukkitRunnable() {
            int counter = 1;
            File file = getDataStorage.structureFile(structureName);
            World world = worldManager.Generate(worldName, false, World.Environment.NORMAL, WorldType.NORMAL, true);

            @Override
            public void run() {
                try {
                    if (file.exists()) {
                        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
                        int blockCount = fileConfiguration.getInt("blockCount");
                        World world = Bukkit.getWorld(worldName);
                        Block block = world.getBlockAt(fileConfiguration.getInt(counter + ".location.x") + x,
                                fileConfiguration.getInt(counter + ".location.y") + y,
                                fileConfiguration.getInt(counter + ".location.z") + z);
                        block.setType(Material.valueOf(fileConfiguration.getString(counter + ".type")));
                        block.setBlockData(
                                Bukkit.getServer().createBlockData(fileConfiguration.getString(counter + ".data")));
                        System.out.println("paste block");
                        if (counter >= blockCount) {
                            cancel();
                            System.out.println("cancel");
                        }
                        counter++;
                    }
                } catch (Exception e) {
                }

            }
        }.runTaskTimerAsynchronously(plugin, 0, 20);

        sender.sendMessage("end schedule");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> list = new ArrayList<String>();
        return list;
    }
}