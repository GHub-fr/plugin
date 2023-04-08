package ghub.fr.text;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import ghub.fr.system.getDataStorage;

import java.io.File;
import java.io.IOException;

public class playerLang {
    public static lang.languages getPlayerLang(OfflinePlayer offlinePlayer) throws IOException {
        return lang.languages.values()[getDataStorage.playerFileConfiguration(offlinePlayer).getInt("lang")];
    }

    public static void setPlayerLang(OfflinePlayer offlinePlayer, int i) throws IOException {
        File file = getDataStorage.playerFile(offlinePlayer);
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        if (i == 0) {
            fileConfiguration.set("lang", null);
        } else {
            fileConfiguration.set("lang", i);
        }
        fileConfiguration.save(file);
    }
}