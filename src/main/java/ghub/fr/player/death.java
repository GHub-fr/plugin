package ghub.fr.player;

import org.bukkit.GameMode;
import org.bukkit.GameRule;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import ghub.fr.menu.api.persistentData;
import ghub.fr.system.ServerBootFile;
import ghub.fr.system.gold;
import ghub.fr.system.security;
import ghub.fr.text.lang;
import ghub.fr.text.playerLang;
import ghub.fr.text.textTranslation;
import ghub.fr.world.api.teleportation;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class death implements Listener {
    @EventHandler
    public void DeathMessage(PlayerDeathEvent e) throws IOException {
        e.setDeathMessage(null);
        lang.languages lang = playerLang.getPlayerLang(e.getEntity());
        e.getEntity().sendMessage(textTranslation.death(lang));
    }

    @EventHandler
    public void DeathBan(PlayerDeathEvent e) throws IOException {
        e.setDeathMessage(null);
        lang.languages lang = playerLang.getPlayerLang(e.getEntity());
        if (ServerBootFile.getServerType().equals(ServerBootFile.serverType.Anarchie)) {
            security.addSecurity(e.getPlayer(), security.SecurityList.BanAnarchie, 1);
        }
    }

    @EventHandler
    public void DropArgent(PlayerDeathEvent e) throws IOException {
        int Argent = gold.GetGold(e.getEntity().getPlayer());
        int ArgentAvecDrop = (int) (Argent * 0.95D);
        if (gold.GetHasEnoughGold(e.getEntity(), ArgentAvecDrop)) {
            gold.RemoveGold(e.getEntity(), Argent - ArgentAvecDrop);
        } else {
            gold.SetGold(e.getEntity(), 0);
        }
    }

    @EventHandler
    public void Respawn(PlayerRespawnEvent e) throws IOException, ParseException {
        teleportation.Teleport(e.getPlayer(), "Spawn", false);
        e.getPlayer().setGameMode(GameMode.SURVIVAL);
    }

    @EventHandler
    public void KeepOnDeath(PlayerDeathEvent e) {
        if (!e.getEntity().getWorld().getGameRuleValue(GameRule.KEEP_INVENTORY)) {
            e.setKeepInventory(true);
            e.setShouldDropExperience(true);
            e.getDrops().clear();
            ArrayList<Integer> remove = new ArrayList<>();
            for (int i = 0; i < e.getEntity().getInventory().getSize(); i++) {
                ItemStack itemStack = e.getEntity().getInventory().getItem(i);
                if (itemStack != null && !itemStack.getType().equals(Material.AIR)) {
                    if (!persistentData.hasPersistentDataItemStack(itemStack, persistentData.customKey.locked)) {
                        e.getDrops().add(itemStack);
                        remove.add(i);
                    }
                }
            }
            for (int i : remove) {
                e.getEntity().getInventory().setItem(i, new ItemStack(Material.AIR));
            }
        }
    }
}