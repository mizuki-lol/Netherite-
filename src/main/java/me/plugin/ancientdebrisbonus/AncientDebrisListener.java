package me.plugin.ancientdebrisbonus;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class AncientDebrisListener implements Listener {

    private final AncientDebrisBonus plugin;

    public AncientDebrisListener(AncientDebrisBonus plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();

        // Проверяем, что это Ancient Debris
        if (block.getType() != Material.ANCIENT_DEBRIS) {
            return;
        }

        Player player = event.getPlayer();
        ItemStack tool = player.getInventory().getItemInMainHand();

        // Проверяем, что инструмент не зачарован шёлковым касанием
        if (tool.containsEnchantment(Enchantment.SILK_TOUCH)) {
            return;
        }

        // Отменяем стандартный дроп и добавляем свой
        event.setDropItems(false);

        // Даём 3 Netherite Scrap
        ItemStack netheriteScraps = new ItemStack(Material.NETHERITE_SCRAP, 3);
        block.getWorld().dropItemNaturally(block.getLocation(), netheriteScraps);

        plugin.getLogger().fine("Игрок " + player.getName() +
                " добыл Ancient Debris — выдано 3 Netherite Scrap.");
    }
}
