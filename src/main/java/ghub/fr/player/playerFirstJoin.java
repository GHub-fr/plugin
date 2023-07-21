package ghub.fr.player;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import ghub.fr.system.dateAPI;
import ghub.fr.system.getDataStorage;
import ghub.fr.system.tags;
import ghub.fr.text.lang;
import ghub.fr.text.playerLang;
import ghub.fr.text.textTranslation;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class playerFirstJoin implements Listener {
    public static File file(OfflinePlayer offlinePlayer) {
        return getDataStorage.playerFile(offlinePlayer);
    }

    public static FileConfiguration fileConfiguration(OfflinePlayer offlinePlayer) throws IOException {
        File file = file(offlinePlayer);
        if (!file.exists()) {
            file.createNewFile();
        }
        return YamlConfiguration.loadConfiguration(file);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onFirstJoin(PlayerJoinEvent e) throws IOException {
        if (getDataStorage.playerExist(e.getPlayer())) {
            setPlayerUpdate(e.getPlayer());
            welcomeMessageToPlayer(e.getPlayer());
        } else {
            File file = file(e.getPlayer());
            file.createNewFile();
            setPlayerInfo(e.getPlayer());
            setPlayerBeta(e.getPlayer());
            setPlayerUpdate(e.getPlayer());
            welcomeMessageEverybody(e.getPlayer());
        }

        tags.addTags(e.getPlayer(), tags.TagsList.Joueur);
    }

    public void welcomeMessageEverybody(Player player) throws IOException {
        for (Player players : Bukkit.getOnlinePlayers()) {
            lang.languages lang = playerLang.getPlayerLang(player);
            players.sendMessage(textTranslation.Welcome(lang, player.getDisplayName()));
        }
    }

    public void welcomeMessageToPlayer(Player player) throws IOException {
        lang.languages lang = playerLang.getPlayerLang(player);
        player.sendMessage(textTranslation.WelcomeBack(lang, player.getDisplayName()));
    }

    public void setPlayerBeta(Player player) throws IOException {
        FileConfiguration fileConfiguration = fileConfiguration(player);
        fileConfiguration.set("Gold", 5000);
        fileConfiguration.save(file(player));
        tags.addTags(player, tags.TagsList.Beta);
        lang.languages lang = playerLang.getPlayerLang(player);
        player.sendMessage(textTranslation.Beta(lang));
    }

    public void setPlayerInfo(Player player) throws IOException {
        FileConfiguration fileConfiguration = fileConfiguration(player);
        fileConfiguration.set("FirstLogin", dateAPI.DateFormated(dateAPI.now()));
        fileConfiguration.set("Gold", 500);
        fileConfiguration.save(file(player));
        lang.languages lang = playerLang.getPlayerLang(player);
        player.sendMessage(textTranslation.goToPNJ(lang));
    }

    public void setPlayerUpdate(Player player) throws IOException {
        FileConfiguration fileConfiguration = fileConfiguration(player);
        fileConfiguration.set("LastLogin", dateAPI.DateFormated(dateAPI.now()));
        fileConfiguration.set("AmountLogin", fileConfiguration.getInt("AmountLogin") + 1);
        fileConfiguration.set("LastIP", player.getAddress().getHostName());
        List<String> IP = fileConfiguration.getStringList("ListIP");
        if (!IP.contains(player.getAddress().getHostName())) {
            IP.add(player.getAddress().getHostName());
            fileConfiguration.set("ListIP", IP);
        }
        fileConfiguration.save(file(player));
    }
}