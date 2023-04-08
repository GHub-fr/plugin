package ghub.fr.chat;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.OfflinePlayer;

import ghub.fr.text.lang;
import ghub.fr.text.playerLang;
import ghub.fr.text.textTranslation;

import java.io.IOException;
import java.text.ParseException;

public class playerTextHover {
    @SuppressWarnings("deprecation")
    public static TextComponent getHover(OfflinePlayer offlinePlayer, OfflinePlayer viewer)
            throws IOException, ParseException {
        TextComponent PlayerComponent = new TextComponent();
        PlayerComponent.setText("[ " + prefixTag.prefixTag(offlinePlayer) + "§6" + offlinePlayer.getName() + " §r]");
        String hover = "";
        hover = hover.concat(" \n ");
        hover = hover.concat("§6§lUUID§r: " + offlinePlayer.getUniqueId());
        hover = hover.concat(" \n ");
        lang.languages lang = playerLang.getPlayerLang(viewer);
        hover = hover.concat(textTranslation.clickPlayerInfo(lang));
        hover = hover.concat(" \n ");
        ComponentBuilder hoverText = new ComponentBuilder(hover);
        PlayerComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, hoverText.create()));
        PlayerComponent
                .setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/profile " + offlinePlayer.getName()));
        return PlayerComponent;
    }
}