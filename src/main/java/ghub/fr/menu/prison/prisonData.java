package ghub.fr.menu.prison;

import java.io.File;
import java.io.IOException;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import ghub.fr.system.getDataStorage;

public class prisonData {
    public static FileConfiguration fileConfiguration(OfflinePlayer player) throws IOException {
        File file = getDataStorage.playerOPPrisonData(player);
        if (!file.exists()) {
            file.createNewFile();
        }
        return YamlConfiguration.loadConfiguration(file);
    }

    public static Material getPickaxe(OfflinePlayer player) throws IOException {
        FileConfiguration fileConfiguration = fileConfiguration(player);
        Material material = (Material) fileConfiguration.get("material");
        if (material == null) {
            return Material.WOODEN_PICKAXE;
        }
        return material;
    }

    public static void setPickaxe(OfflinePlayer player, Material material) throws IOException {
        FileConfiguration fileConfiguration = fileConfiguration(player);
        fileConfiguration.set("material", material);
        fileConfiguration.save(getDataStorage.playerOPPrisonData(player));
    }
}
