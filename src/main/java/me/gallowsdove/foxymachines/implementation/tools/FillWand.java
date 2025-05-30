package me.gallowsdove.foxymachines.implementation.tools;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import me.gallowsdove.foxymachines.FoxyMachines;
import me.gallowsdove.foxymachines.Items;
import me.gallowsdove.foxymachines.abstracts.AbstractWand;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class FillWand extends AbstractWand {
    public FillWand() {
        super(Items.FILL_WAND, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                Items.AQUATIC_NETHERITE_INGOT.item(), Items.WIRELESS_TRANSMITTER.item(), Items.AQUATIC_NETHERITE_INGOT.item(),
                Items.DEMONIC_PLATE.item(), SlimefunItems.PROGRAMMABLE_ANDROID_2.item(), Items.DEMONIC_PLATE.item(),
                Items.SWEETENED_SWEET_INGOT.item(), Items.WIRELESS_TRANSMITTER.item(), Items.SWEETENED_SWEET_INGOT.item()
        });
    }

    @Override
    protected int getMaxBlocks() {
        return FoxyMachines.getInstance().getConfig().getInt("max-fill-wand-blocks");
    }

    @Override
    protected boolean isRemoving() {return false;}

    @Override
    protected float getCostPerBlock() {
        return 0.4F;
    }

    @Override
    protected boolean blockPredicate(Player player, Block block) {
        return block.getType().isAir() && Slimefun.getProtectionManager().hasPermission(player, block, Interaction.PLACE_BLOCK);
    }

    @Override
    public float getMaxItemCharge(ItemStack itemStack) {
        return 1000;
    }
}
