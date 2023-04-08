package ghub.fr.discord;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import ghub.fr.chat.prefixTag;
import ghub.fr.system.ServerBootFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class eventsMinecraft implements Listener {
    @EventHandler
    public void onAchievement(PlayerAdvancementDoneEvent event)
            throws IOException, ParseException, ExecutionException, InterruptedException {
        String achievement = event.getAdvancement().getKey().getKey();
        if (!achievement.contains("recipes")) {
            String playerName = prefixTag.prefixTagDiscord(event.getPlayer()) + event.getPlayer().getDisplayName();
            ArrayList<String> messages = new ArrayList<>();
            messages.add("```");
            messages.add(playerName);
            messages.add("Succès réussi ✨ " + achievement);
            messages.add("```");
            ghub.fr.discord.messages.SendMessageToChannel(messages);
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e)
            throws IOException, ParseException, ExecutionException, InterruptedException {
        String playerName = prefixTag.prefixTagDiscord(e.getPlayer()) + e.getPlayer().getDisplayName();
        ArrayList<String> messages = new ArrayList<>();
        messages.add("```");
        messages.add(playerName + "  \uD83D\uDCAC");
        messages.add(e.getMessage());
        messages.add("```");
        ghub.fr.discord.messages.SendMessageToChannel(messages);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
            throws IOException, ParseException, ExecutionException, InterruptedException {
        String playerName = prefixTag.prefixTagDiscord(e.getPlayer()) + e.getPlayer().getDisplayName();
        ArrayList<String> messages = new ArrayList<>();
        messages.add("```");
        messages.add("✋ Connecté à " + ServerBootFile.getServerType() + ".ghub.fr");
        messages.add(playerName);
        messages.add("```");
        ghub.fr.discord.messages.SendMessageToChannel(messages);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e)
            throws IOException, ParseException, ExecutionException, InterruptedException {
        String playerName = prefixTag.prefixTagDiscord(e.getPlayer()) + e.getPlayer().getDisplayName();
        ArrayList<String> messages = new ArrayList<>();
        messages.add("```");
        messages.add("⛔ Déconnecté de " + ServerBootFile.getServerType() + ".ghub.fr");
        messages.add(playerName);
        messages.add("```");
        ghub.fr.discord.messages.SendMessageToChannel(messages);
    }
}