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

import ghub.fr.main.main;

public class ScoreBoardManager implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective objective = board.registerNewObjective("sidebar", "view", "", RenderType.INTEGER);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score space = objective.getScore("§f ");
        space.setScore(1);

        Score onlineName = objective.getScore("§7» §2en ligne §f: §2" + Bukkit.getOnlinePlayers().size() + "§f / §4"
                + Bukkit.getMaxPlayers());
        onlineName.setScore(2);

        Score space2 = objective.getScore(" ");
        space2.setScore(3);

        Score test = objective.getScore("§7» §6Gold : " + gold.GetGoldFormat(e.getPlayer()));
        test.setScore(4);
        e.getPlayer().setScoreboard(board);
        //updatePlayer(e.getPlayer(), objective, board);
    }

    public static void send(Player player, Objective objective, Scoreboard board) {
        Score space = objective.getScore("§f ");
        space.setScore(1);

        Score onlineName = objective.getScore("§7» §2en ligne §f: §2" + Bukkit.getOnlinePlayers().size() + "§f / §4"
                + Bukkit.getMaxPlayers());
        onlineName.setScore(2);

        Score space2 = objective.getScore(" ");
        space2.setScore(3);

        Score test = objective.getScore("§7» §6Gold : " + gold.GetGoldFormat(player));
        test.setScore(4);
    }

    public static void updatePlayer(Player player, Objective objective, Scoreboard board) {
        Plugin plugin = JavaPlugin.getPlugin(main.class);

        new BukkitRunnable() {
            @Override
            public void run() {

                send(player, objective, board);

            }
        }.runTaskTimer(plugin, 0, 30);
    }
}
