package ghub.fr.system;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.RenderType;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import ghub.fr.main.main;

public class ScoreBoardManager implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        updatePlayer(e.getPlayer());
    }

    public static void send(Player player) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective objective = board.registerNewObjective("sidebar", "view", "", RenderType.INTEGER);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score onlineName = objective.getScore("§7» §2en ligne");
        onlineName.setScore(15);

        Team onlineCounter = board.registerNewTeam("onlineCounter");
        onlineCounter.addEntry(player.getUniqueId().toString());
        onlineCounter.setPrefix("§2" + Bukkit.getOnlinePlayers().size() + "§f / §4" + Bukkit.getMaxPlayers());

        player.setScoreboard(board);
    }

    public static void updatePlayer(Player player) {
        Plugin plugin = JavaPlugin.getPlugin(main.class);

        new BukkitRunnable() {
            @Override
            public void run() {

                send(player);

            }
        }.runTaskTimerAsynchronously(plugin, 0, 30);
    }
}