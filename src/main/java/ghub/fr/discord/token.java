package ghub.fr.discord;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import ghub.fr.system.ServerBootFile;
import ghub.fr.system.ServerBootFile.serverType;

public class token {
    public static File tokensFile() {
        String FolderName = "/Serveurs/DataFolder/";
        // String FolderName = "C:\\Users\\user\\Desktop";
        String FileName = "tokens.yml";
        File file = new File(FolderName, FileName);
        return file;
    }

    public static String getTokens() throws IOException {
        if (tokensFile().exists()) {
            FileConfiguration fileConfiguration = YamlConfiguration
                    .loadConfiguration(tokensFile());
            serverType bot = ServerBootFile.getServerTypeFromYML();
            return fileConfiguration.getString(bot.toString());
        } else {
            tokensFile().createNewFile();
        }
        return "";
    }

    public static String channel() {
        switch (ServerBootFile.getServerTypeFromYML()) {
            case SkyBlock:
                return "1081945139355848835";
            case RPG:
                return "1081946116045676564";
            case Survie:
                return "1081945244888735804";
            case Anarchie:
                return "1081945360764772462";
            case Creatif:
                return "1081946383004733602";
            case Hub:
                return "1081944880114303066";
            default:
                return "";
        }
    }

    public static String rebootChannelID() {
        return "1081945572744892466";
    }
}