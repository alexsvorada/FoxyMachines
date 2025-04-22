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

public class SpongeWand extends AbstractWand {
    public SpongeWand() {
        super(Items.SPONGE_WAND, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                Items.NUCLEAR_SALT.item(), Items.WIRELESS_TRANSMITTER.item(), Items.NUCLEAR_SALT.item(),
                Items.DEMONIC_PLATE.item(), SlimefunItems.PROGRAMMABLE_ANDROID_2.item(), Items.DEMONIC_PLATE.item(),
                Items.NUCLEAR_SALT.item(), Items.COMPRESSED_SPONGE.item(), Items.NUCLEAR_SALT.item()
        });
    }

    @Override
    protected int getMaxBlocks() {
        return FoxyMachines.getInstance().getConfig().getInt("max-sponge-wand-blocks");
    }

    @Override
    protected boolean isRemoving() {return true;}

    @Override
    protected float getCostPerBlock() {
        return 0.24F;
    }

    @Override
    protected boolean blockPredicate(Player player, Block block) {
        return block.isLiquid() && Slimefun.getProtectionManager().hasPermission(player, block, Interaction.BREAK_BLOCK);
    }

    @Override
    public float getMaxItemCharge(ItemStack itemStack) {
        return 2000;
    }
}
