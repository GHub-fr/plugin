package ghub.fr.system;

import com.sun.management.OperatingSystemMXBean;

import ghub.fr.chat.prefixTag;
import ghub.fr.main.main;
import ghub.fr.menu.shop.specials.bonus.bonus;
import ghub.fr.text.textTranslation;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.text.ParseException;

public class Tab {
    public static void scheduler() {
        Plugin plugin = JavaPlugin.getPlugin(main.class);
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective objective = board.registerNewObjective("showhealth", "view", "", RenderType.HEARTS);
        objective.setDisplaySlot(DisplaySlot.BELOW_NAME);

        new BukkitRunnable() {
            @Override
            public void run() {

                Runtime runtime = Runtime.getRuntime();

                long maxMemory = runtime.maxMemory();
                long allocatedMemory = runtime.totalMemory();
                long freeMemory = runtime.freeMemory();
                long usedMemory = allocatedMemory - freeMemory;
                OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory
                        .getOperatingSystemMXBean();
                double cpuusage = operatingSystemMXBean.getSystemCpuLoad() * 100;
                File diskPartition = new File("/");
                long free = diskPartition.getFreeSpace();
                long total = diskPartition.getTotalSpace();

                long currentTime = System.currentTimeMillis();
                boolean isPinged = false;
                try {
                    isPinged = InetAddress.getByName("www.google.fr").isReachable(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                currentTime = System.currentTimeMillis() - currentTime;

                String footer = footer(maxMemory, usedMemory, allocatedMemory, freeMemory, cpuusage, free, total,
                        isPinged, currentTime);
                for (Player player : Bukkit.getOnlinePlayers()) {
                    try {
                        Score score = objective.getScore(player);
                        score.setScore((int) player.getHealth());

                        setTab(player, header(player), footer);

                        setTabName(player);
                        player.setScoreboard(board);
                        setTeamTag(player, board);

                    } catch (Exception e) {
                    }
                }
            }
        }.runTaskTimerAsynchronously(plugin, 0, 30);
    }

    @SuppressWarnings("deprecation")
    public static void setTab(Player player, String header, String footer) {
        player.setPlayerListHeaderFooter(header, footer);
    }

    public static String header(Player player) {
        String header = "";
        header += "\n\n\n\n";
        header += "§6[ §4§l" + ServerBootFile.getServerType().toString() + ".§2ghub§6.fr ]\n";
        header += textTranslation.DiscordNoNewLine() + "\n\n";
        header += Bukkit.getServer().getOnlinePlayers().size() + " §6/§r " + Bukkit.getServer().getMaxPlayers();
        header += "\n§6Ping §r: §6" + player.getPing() + " §rMs";
        return header;
    }

    public static String footer(long maxMemory, long usedMemory, long allocatedMemory, long freeMemory, double cpuusage,
            long free, long total, boolean isPinged, long currentTime) {
        String footer = "";
        footer += "\n§6RAM §r: " + Math.round(usedMemory / 1024 / 1024) + "/" + Math.round(maxMemory / 1024 / 1024)
                + " Mo";
        footer += "\n§6CPU §r: " + Math.round(cpuusage) + "§6%";
        footer += "\n§6TPS §r: " + Math.round(Bukkit.getTPS()[0]);
        footer += "\n§6HDD §r: " + Math.round(free / 1024 / 1024 / 1024) + "/" + Math.round(total / 1024 / 1024 / 1024)
                + " Go";

        if (isPinged) {
            footer += "\n§6Ping §r: google.fr : §6" + currentTime + " §rMs";
        } else {
            footer += "\n§6Ping §r: google.fr : §4§lErreur";
        }

        return footer;
    }

    public static void setTabName(Player player) throws IOException, ParseException {
        player.setPlayerListName(prefixTag.prefixTag(player) + "§r§f" + player.getDisplayName() + " " + player.getHealth() + "");
    }

    public static String nameTeam(OfflinePlayer offlinePlayer) throws IOException, ParseException {
        String prefix = "";
        if (tags.hasTags(offlinePlayer, tags.TagsList.Admin)) {
            prefix += "A";
        }
        if (tags.hasTags(offlinePlayer, tags.TagsList.Moderation) || tags.hasTags(offlinePlayer, tags.TagsList.Builder)
                || tags.hasTags(offlinePlayer, tags.TagsList.Build)) {
            prefix += "S";
        }
        if (tags.hasTags(offlinePlayer, tags.TagsList.Beta)) {
            prefix += "B";
        }
        if (!bonus.isObsoletBonus(offlinePlayer, bonus.BonusList.VIP)) {
            prefix += "V";
        }
        return prefix;
    }

    @SuppressWarnings("deprecation")
    public static void setTeamTag(Player player, Scoreboard scoreboard) throws IOException, ParseException {
        String nameTeam = nameTeam(player);
        if (scoreboard.getTeam(nameTeam) == null) {
            org.bukkit.scoreboard.Team newTeam = scoreboard.registerNewTeam(nameTeam);
            String tag = prefixTag.prefixTag(player);
            newTeam.setPrefix(tag);
            newTeam.addPlayer(player);
            player.setScoreboard(scoreboard);
        } else {
            if (!scoreboard.getTeam(nameTeam).getPlayers().contains(player)) {
                org.bukkit.scoreboard.Team team = scoreboard.getTeam(nameTeam);
                team.addPlayer(player);
            }
        }
    }
}