package ghub.fr.commands.player;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ghub.fr.text.lang;
import ghub.fr.text.playerLang;
import ghub.fr.text.textTranslation;

public class help implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            try {
                lang.languages lang = playerLang.getPlayerLang(((Player) sender).getPlayer());
                sender.sendMessage(textTranslation.helpMSG(lang));
                return true;
            } catch (Exception e) {
            }
        }
        return false;
    }
}