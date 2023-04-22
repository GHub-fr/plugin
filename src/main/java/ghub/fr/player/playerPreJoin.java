package ghub.fr.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import ghub.fr.main.main;
import ghub.fr.menu.shop.specials.bonus.bonus;
import ghub.fr.system.BungeePluginMessageListener;
import ghub.fr.system.ServerBootFile;
import ghub.fr.system.getDataStorage;
import ghub.fr.system.security;
import ghub.fr.system.tags;
import ghub.fr.system.tags.TagsList;
import ghub.fr.text.lang;
import ghub.fr.text.playerLang;
import ghub.fr.text.textTranslation;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

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
                        && !tags.hasTags(e.getPlayer(), tags.TagsList.Test)) {
                    e.disallow(PlayerLoginEvent.Result.KICK_OTHER, textTranslation.Whitelist(lang));
                    return;
                }
                if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.OPPrison)
                        && !tags.hasTags(e.getPlayer(), tags.TagsList.Test)) {
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
                // lang.languages lang = ghub.fr.text.lang.languages.fr;
                // e.disallow(PlayerLoginEvent.Result.KICK_OTHER,
                // textTranslation.hubfirstLogin(lang));
                BungeePluginMessageListener.teleportPlayer("hub", e.getPlayer());
            }
        }
    }

    @EventHandler
    public void CreatifPerm(PlayerJoinEvent e) throws IOException, ParseException {
        if (tags.hasTags(e.getPlayer(), TagsList.Builder)) {
            Plugin plugin = JavaPlugin.getPlugin(main.class);
            HashMap<String, PermissionAttachment> attachments = new HashMap<String, PermissionAttachment>();
            PermissionAttachment attachment = e.getPlayer().addAttachment(plugin);
            attachment.setPermission("worldedit.*", true);
        }
    }

    @EventHandler
    public void CreatifPerm(PlayerQuitEvent e) throws IOException, ParseException {
        if (tags.hasTags(e.getPlayer(), TagsList.Builder)) {
            Plugin plugin = JavaPlugin.getPlugin(main.class);
            HashMap<String, PermissionAttachment> attachments = new HashMap<String, PermissionAttachment>();
            PermissionAttachment attachment = e.getPlayer().addAttachment(plugin);
            attachment.unsetPermission("worldedit.*");
        }
    }
}