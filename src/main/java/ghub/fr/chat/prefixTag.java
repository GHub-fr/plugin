package ghub.fr.chat;

import org.bukkit.OfflinePlayer;

import ghub.fr.menu.shop.specials.bonus.bonus;
import ghub.fr.system.tags;

import java.io.IOException;
import java.text.ParseException;

public class prefixTag {
    public static String prefixTag(OfflinePlayer offlinePlayer) throws IOException, ParseException {
        String prefix = "";
        if (tags.hasTags(offlinePlayer, tags.TagsList.Admin)) {
            prefix += "§4§kA";
        }
        if (tags.hasTags(offlinePlayer, tags.TagsList.Moderation) || tags.hasTags(offlinePlayer, tags.TagsList.Builder)
                || tags.hasTags(offlinePlayer, tags.TagsList.Build)) {
            prefix += "§2🛡";
        }
        if (tags.hasTags(offlinePlayer, tags.TagsList.Beta)) {
            prefix += "§6β";
        }
        if (!bonus.isObsoletBonus(offlinePlayer, bonus.BonusList.VIP)) {
            prefix += "§6⭐";
        }
        prefix += " ";
        return prefix;
    }

    public static String prefixTagDiscord(OfflinePlayer offlinePlayer) throws IOException, ParseException {
        String prefix = "";
        if (tags.hasTags(offlinePlayer, tags.TagsList.Admin)) {
            prefix += "\uD83D\uDC51";
        }
        if (tags.hasTags(offlinePlayer, tags.TagsList.Moderation) || tags.hasTags(offlinePlayer, tags.TagsList.Builder)
                || tags.hasTags(offlinePlayer, tags.TagsList.Build)) {
            prefix += "\uD83D\uDC77";
        }
        if (tags.hasTags(offlinePlayer, tags.TagsList.Beta)) {
            prefix += "\uD83D\uDEA7";
        }
        if (!bonus.isObsoletBonus(offlinePlayer, bonus.BonusList.VIP)) {
            prefix += "\uD83C\uDF1F";
        }
        prefix += "   ";
        return prefix;
    }
}