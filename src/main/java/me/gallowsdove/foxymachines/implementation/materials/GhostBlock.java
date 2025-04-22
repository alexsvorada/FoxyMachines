package me.gallowsdove.foxymachines.implementation.materials;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import me.gallowsdove.foxymachines.FoxyMachines;
import me.gallowsdove.foxymachines.Items;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class GhostBlock extends SlimefunItem {

    public static final NamespacedKey KEY = new NamespacedKey(FoxyMachines.getInstance(), "ghost_block");

    public static final Set<Material> EXCLUDED = Set.of(Material.BARRIER, Material.SPAWNER, Material.COMMAND_BLOCK,
            Material.STRUCTURE_BLOCK, Material.REPEATING_COMMAND_BLOCK, Material.CHAIN_COMMAND_BLOCK, Material.JIGSAW);

    public static final Set<UUID> BLOCK_CACHE = new HashSet<>();

    @Nonnull
    private final Material material;

    public GhostBlock(SlimefunItemStack item) {
        super(Items.GHOST_BLOCKS_ITEM_GROUP, item, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                SlimefunItems.BILLON_INGOT.item(), SlimefunItems.BILLON_INGOT.item(), SlimefunItems.BILLON_INGOT.item(),
                SlimefunItems.MAGICAL_GLASS.item(), new ItemStack(item.getType()), SlimefunItems.MAGICAL_GLASS.item(),
                SlimefunItems.BILLON_INGOT.item(), SlimefunItems.BILLON_INGOT.item(), SlimefunItems.BILLON_INGOT.item()
        });

        this.material = item.getType();
    }

    @Override
    public void preRegister() {
        addItemHandler(onUse());
    }

    @Nonnull
    private ItemUseHandler onUse() {
        return e -> {
            e.cancel();
            if (e.getClickedBlock().isEmpty()) {
                return;
            }

            Player p = e.getPlayer();
            Block b = e.getClickedBlock().get().getRelative(e.getClickedFace());

            if (!Slimefun.getProtectionManager().hasPermission(p, b, Interaction.PLACE_BLOCK)) {
                p.sendMessage(ChatColor.LIGHT_PURPLE + "You don't have permission to place this here!");
                return;
            }

            if (!b.getWorld().getNearbyEntities(b.getLocation().add(0.5, 0, 0.5), 0.01, 0.01, 0.01).isEmpty()) {
                return;
            }

            FallingBlock block = b.getWorld().spawnFallingBlock(b.getLocation().add(0.5, 0, 0.5), material.createBlockData());
            block.setVelocity(new Vector(0, 0, 0));
            block.setGravity(false);
            block.setDropItem(false);
            block.setPersistent(true);
            block.setInvulnerable(true);
            block.getPersistentDataContainer().set(KEY, PersistentDataType.STRING, "true");

            ItemStack item = e.getInteractEvent().getItem();
            item.setAmount(item.getAmount() - 1);

            BLOCK_CACHE.add(block.getUniqueId());
        };
    }

    public static boolean isGhostBlock(Entity entity) {
        return entity.getPersistentDataContainer().has(KEY, PersistentDataType.STRING);
    }
}
