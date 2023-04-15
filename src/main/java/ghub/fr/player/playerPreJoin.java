package ghub.fr.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import ghub.fr.menu.shop.specials.bonus.bonus;
import ghub.fr.system.BungeePluginMessageListener;
import ghub.fr.system.ServerBootFile;
import ghub.fr.system.getDataStorage;
import ghub.fr.system.security;
import ghub.fr.system.tags;
import ghub.fr.text.lang;
import ghub.fr.text.playerLang;
import ghub.fr.text.textTranslation;

import java.io.IOException;
import java.text.ParseException;

public class playerPreJoin implements Listener {
    @EventHandler
    public void onTeleportation(PlayerLoginEvent e) throws IOException, ParseException {
        if (getDataStorage.playerExist(e.getPlayer())) {
            lang.languages lang = playerLang.getPlayerLang(e.getPlayer());
            if (security.isObsoletSecurity(e.getPlayer(), security.SecurityList.Ban)
                    && security.isObsoletSecurity(e.getPlayer(), security.SecurityList.Cheat)) {

                if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.Survie)
                        && !tags.hasTags(e.getPlayer(), tags.TagsList.Survie)) {
                    e.disallow(PlayerLoginEvent.Result.KICK_OTHER, textTranslation.Whitelist(lang));
                    return;
                }
                if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.Creatif)
                        && !tags.hasTags(e.getPlayer(), tags.TagsList.Creatif)) {
                    e.disallow(PlayerLoginEvent.Result.KICK_OTHER, textTranslation.Whitelist(lang));
                    return;
                }
                if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.RPG)
                        && !tags.hasTags(e.getPlayer(), tags.TagsList.RPG)) {
                    e.disallow(PlayerLoginEvent.Result.KICK_OTHER, textTranslation.Whitelist(lang));
                    return;
                }
                if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.Anarchie)
                        && !security.isObsoletSecurity(e.getPlayer(), security.SecurityList.BanAnarchie)) {
                    e.disallow(PlayerLoginEvent.Result.KICK_BANNED, textTranslation.bannedAnarchie(lang));
                    return;
                }

                if (e.getResult().equals(PlayerLoginEvent.Result.KICK_FULL)) {
                    if (!bonus.isObsoletBonus(e.getPlayer(), bonus.BonusList.VIP)) {
                        e.allow();
                    } else {
                        e.disallow(PlayerLoginEvent.Result.KICK_FULL, textTranslation.serveurFull(lang));
                    }
                }

            } else {
                e.disallow(PlayerLoginEvent.Result.KICK_BANNED, textTranslation.banned(lang));
            }

        } else {
            if (!ServerBootFile.getServerType().equals(ServerBootFile.serverType.Hub)) {
                //lang.languages lang = ghub.fr.text.lang.languages.fr;
                //e.disallow(PlayerLoginEvent.Result.KICK_OTHER, textTranslation.hubfirstLogin(lang));
                BungeePluginMessageListener.teleportPlayer("hub", e.getPlayer());
            }
        }
    }
}