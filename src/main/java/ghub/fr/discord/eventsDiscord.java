package ghub.fr.discord;

import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.javacord.api.entity.message.MessageAuthor;

import java.io.IOException;
import java.text.ParseException;

public class eventsDiscord {
    public static void ChatLinker() {
        discordMain.api.addMessageCreateListener(event -> {
            if (event.getChannel().getIdAsString().equals(token.channel())) {
                if (!event.getMessageAuthor().isBotUser()) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        try {
                            TextComponent message = new TextComponent();
                            message.setText("§r §7>>§r " + event.getMessage().getReadableContent());
                            p.getPlayer().spigot().sendMessage(TextPlayerHover(event.getMessageAuthor()), message);
                        } catch (IOException | ParseException e) {
                        }
                    }
                    Bukkit.getServer().getLogger()
                            .info(event.getMessageAuthor().getId() + " : "
                                    + event.getMessageAuthor().getDiscriminatedName() + " : "
                                    + event.getMessage().getReadableContent());
                }
            }
        });
    }

    public static TextComponent TextPlayerHover(MessageAuthor messageAuthor) throws IOException, ParseException {
        TextComponent PlayerComponent = new TextComponent();
        PlayerComponent.setText("[ §6" + messageAuthor.getDiscriminatedName() + " §r]");
        String hover = "";
        hover = hover.concat(" \n ");
        hover = hover.concat("§6§lUUID§r: " + messageAuthor.getIdAsString());
        hover = hover.concat(" \n ");
        hover = hover.concat("§6§lTAG§r: " + messageAuthor.getDiscriminator().get());
        if (messageAuthor.getConnectedVoiceChannel().isPresent()) {
            hover = hover.concat(" \n ");
            hover = hover.concat("§6§lVocal§r: " + messageAuthor.getConnectedVoiceChannel().get().getName());
        }
        hover = hover.concat(" \n ");
        ComponentBuilder hoverText = new ComponentBuilder(hover);
        PlayerComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, hoverText.create()));
        return PlayerComponent;
    }

    public static void reconnect() {
        discordMain.api.addReconnectListener(event -> {
            messages.SendMessageToChannel("**Bot & serveur __reconnecté__** ! ⚡");
            messages.SendMessageToBukkit("§2Bot Discord reconnecté !");
        });
    }
}