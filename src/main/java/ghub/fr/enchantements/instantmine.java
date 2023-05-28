package ghub.fr.enchantements;

import java.util.Set;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.EntityCategory;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.NamespacedKey;

import io.papermc.paper.enchantments.EnchantmentRarity;
import net.kyori.adventure.text.Component;

public class instantmine extends Enchantment implements Listener {

    @EventHandler
    public void onEnchantUse(BlockBreakEvent e) {
        ItemStack it = e.getPlayer().getInventory().getItemInMainHand();
        if (it.getEnchantments().containsKey(this)) {
            e.setDropItems(false);
            for (ItemStack is : e.getBlock().getDrops(it, e.getPlayer())) {
                e.getPlayer().getInventory().addItem(is);
            }

            e.getPlayer().giveExp(e.getExpToDrop());
            e.setExpToDrop(0);
        }
    }

    public instantmine(String namespace) {
        super(NamespacedKey.minecraft(namespace));
    }

    /*
     * public instantmine(NamespacedKey key) {
     * super(key);
     * }
     */

    @Override
    public NamespacedKey getKey() {
        return super.getKey();
    }

    @Override
    public String getName() {
        return "instantmine";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.TOOL;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment enchantment) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack itemStack) {
        return true;
    }

    @Override
    public String translationKey() {
        throw new UnsupportedOperationException("Unimplemented method 'translationKey'");
    }

    @Override
    public Component displayName(int arg0) {
        throw new UnsupportedOperationException("Unimplemented method 'displayName'");
    }

    @Override
    public Set<EquipmentSlot> getActiveSlots() {
        throw new UnsupportedOperationException("Unimplemented method 'getActiveSlots'");
    }

    @Override
    public float getDamageIncrease(int arg0, EntityCategory arg1) {
        return 0;
    }

    @Override
    public EnchantmentRarity getRarity() {
        return EnchantmentRarity.VERY_RARE;
    }

    @Override
    public boolean isDiscoverable() {
        return true;
    }

    @Override
    public boolean isTradeable() {
        return true;
    }

}
