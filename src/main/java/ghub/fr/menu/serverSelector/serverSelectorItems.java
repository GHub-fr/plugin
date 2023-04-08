package ghub.fr.menu.serverSelector;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ghub.fr.menu.api.persistentData;
import ghub.fr.system.dateAPI;
import ghub.fr.system.getDataStorage;
import ghub.fr.system.tags;
import ghub.fr.text.itemsTranslation;
import ghub.fr.text.lang;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class serverSelectorItems {
    public static ArrayList<String> StringToAdd(Player player, lang.languages lang, String server)
            throws ParseException {
        File file = getDataStorage.serversStatsFile(server);
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
        int onlinePlayers = fileConfiguration.getInt("onlinePlayers");
        int maxPlayers = fileConfiguration.getInt("maxPlayers");
        Date date = dateAPI.ReturnDate(file, "lastTick");
        Date dateMoins = dateAPI.ReturnDateLessXSeconds(new Date(), -7);
        ArrayList<String> list = new ArrayList<>();
        if (dateMoins.after(date)) {
            list.add(itemsTranslation.Offline(lang));
            list.add(itemsTranslation.lastSeen(lang, date));
        } else {
            list.add(itemsTranslation.Online(lang));
            list.add(itemsTranslation.OnlineCount(lang, onlinePlayers, maxPlayers));
        }
        list.add("");
        return list;
    }

    public static ItemStack ItemStackCreatif(lang.languages lang, Player player) throws IOException, ParseException {
        ItemStack is = null;
        if (tags.hasTags(player, tags.TagsList.Creatif)) {
            is = new ItemStack(Material.DIAMOND_SHOVEL);
        } else {
            is = new ItemStack(Material.BARRIER);
        }
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.CreatifTitle(lang));
        List<String> list = new ArrayList<>();
        if (tags.hasTags(player, tags.TagsList.Creatif)) {
            list.addAll(itemsTranslation.CreatifLore(lang));
        } else {
            list.addAll(itemsTranslation.CreatifLoreWhiteList(lang));
        }
        list.addAll(StringToAdd(player, lang, "creatif"));
        meta.setLore(list);
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        if (tags.hasTags(player, tags.TagsList.Creatif)) {
            persistentData.setPersistentDataItemStack(is, persistentData.customKey.creatif);
        }
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.locked);
        return is;
    }

    public static ItemStack ItemStackAnarchie(lang.languages lang, Player player) throws IOException, ParseException {
        ItemStack is = new ItemStack(Material.SKELETON_SKULL);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.AnarchieTitle(lang));
        List<String> list = new ArrayList<>();
        list.addAll(itemsTranslation.AnarchieLore(lang));
        list.addAll(StringToAdd(player, lang, "anarchie"));
        meta.setLore(list);
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.anarchie);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.locked);
        return is;
    }

    public static ItemStack ItemStackRPG(lang.languages lang, Player player) throws IOException, ParseException {
        ItemStack is = null;
        if (tags.hasTags(player, tags.TagsList.RPG)) {
            is = new ItemStack(Material.SHIELD);
        } else {
            is = new ItemStack(Material.BARRIER);
        }
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.RPGTitle(lang));
        List<String> list = new ArrayList<>();
        if (tags.hasTags(player, tags.TagsList.RPG)) {
            list.addAll(itemsTranslation.RPGLore(lang));
        } else {
            list.addAll(itemsTranslation.RPGLoreWhiteList(lang));
        }
        list.addAll(StringToAdd(player, lang, "rpg"));
        meta.setLore(list);
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        if (tags.hasTags(player, tags.TagsList.RPG)) {
            persistentData.setPersistentDataItemStack(is, persistentData.customKey.rpg);
        }
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.locked);
        return is;
    }

    public static ItemStack ItemStackSkyBlock(lang.languages lang, Player player) throws IOException, ParseException {
        ItemStack is = new ItemStack(Material.OAK_SAPLING);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.SkyBlockTitle(lang));
        List<String> list = new ArrayList<>();
        list.addAll(itemsTranslation.SkyBlockLore(lang));
        list.addAll(StringToAdd(player, lang, "skyblock"));
        meta.setLore(list);
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.skyblock);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.locked);
        return is;
    }

    public static ItemStack ItemStackHub(lang.languages lang, Player player) throws IOException, ParseException {
        ItemStack is = new ItemStack(Material.ENDER_PEARL);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.hubTitle(lang));
        List<String> list = new ArrayList<>();
        list.addAll(itemsTranslation.hubLore(lang));
        list.addAll(StringToAdd(player, lang, "hub"));
        meta.setLore(list);
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.hub);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.locked);
        return is;
    }

    public static ItemStack ItemStackSurvie(lang.languages lang, Player player) throws IOException, ParseException {
        ItemStack is = null;
        if (tags.hasTags(player, tags.TagsList.Survie)) {
            is = new ItemStack(Material.IRON_SWORD);
        } else {
            is = new ItemStack(Material.BARRIER);
        }
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(itemsTranslation.SurvieTitle(lang));
        List<String> list = new ArrayList<>();
        if (tags.hasTags(player, tags.TagsList.Survie)) {
            list.addAll(itemsTranslation.SurvieLore(lang));
        } else {
            list.addAll(itemsTranslation.SurvieLoreWhiteList(lang));
        }
        list.addAll(StringToAdd(player, lang, "survie"));
        meta.setLore(list);
        is.setItemMeta(meta);
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.custom);
        if (tags.hasTags(player, tags.TagsList.Survie)) {
            persistentData.setPersistentDataItemStack(is, persistentData.customKey.survie);
        }
        persistentData.setPersistentDataItemStack(is, persistentData.customKey.locked);
        return is;
    }
}