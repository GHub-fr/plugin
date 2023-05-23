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
            prefix += "";
        }

        if (tags.hasTags(offlinePlayer, tags.TagsList.Creatif)
                || tags.hasTags(offlinePlayer, tags.TagsList.Builder)
                || tags.hasTags(offlinePlayer, tags.TagsList.Build)) {
            prefix += "";
        }

        if (tags.hasTags(offlinePlayer, tags.TagsList.Messages)
                || tags.hasTags(offlinePlayer, tags.TagsList.Moderation)) {
            prefix += "";
        }

        if (tags.hasTags(offlinePlayer, tags.TagsList.Beta)
                || tags.hasTags(offlinePlayer, tags.TagsList.Test)) {
            prefix += "";
        }

        if (tags.hasTags(offlinePlayer, tags.TagsList.YouTube)) {
            prefix += "";
        }

        if (!bonus.isObsoletBonus(offlinePlayer, bonus.BonusList.VIPPlus)) {
            prefix += "";
        }

        if (!bonus.isObsoletBonus(offlinePlayer, bonus.BonusList.VIP)) {
            prefix += "";
        }

        if (tags.hasTags(offlinePlayer, tags.TagsList.Survie)) {
            prefix += "";
        }

        if (tags.hasTags(offlinePlayer, tags.TagsList.Joueur)) {
            prefix += "";
        }
        return prefix;
    }

    public static String prefixTagDiscord(OfflinePlayer offlinePlayer) throws IOException, ParseException {
        String prefix = "";
        if (tags.hasTags(offlinePlayer, tags.TagsList.Admin)) {
            prefix += "\uD83D\uDC51";
        }
        if (tags.hasTags(offlinePlayer, tags.TagsList.Creatif)
                || tags.hasTags(offlinePlayer, tags.TagsList.Builder)
                || tags.hasTags(offlinePlayer, tags.TagsList.Build)
                || tags.hasTags(offlinePlayer, tags.TagsList.Messages)
                || tags.hasTags(offlinePlayer, tags.TagsList.Moderation)) {
            prefix += "\uD83D\uDC77";
        }
        if (tags.hasTags(offlinePlayer, tags.TagsList.Beta)
                || tags.hasTags(offlinePlayer, tags.TagsList.Test)) {
            prefix += "\uD83D\uDEA7";
        }
        if (!bonus.isObsoletBonus(offlinePlayer, bonus.BonusList.VIP)
                || !bonus.isObsoletBonus(offlinePlayer, bonus.BonusList.VIPPlus)) {
            prefix += "\uD83C\uDF1F";
        }
        prefix += "   ";
        return prefix;
    }
}