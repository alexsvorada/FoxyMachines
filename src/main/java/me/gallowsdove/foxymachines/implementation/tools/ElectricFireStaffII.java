package me.gallowsdove.foxymachines.implementation.tools;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.Rechargeable;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.gallowsdove.foxymachines.Items;
import org.bukkit.entity.LargeFireball;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;


public class ElectricFireStaffII extends SlimefunItem implements Rechargeable {

    private static final float COST = 1.5F;

    public ElectricFireStaffII() {
        super(Items.TOOLS_ITEM_GROUP, Items.ELECTRIC_FIRE_STAFF_II, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
                SlimefunItems.CARBONADO.item(), SlimefunItems.POWER_CRYSTAL.item(), SlimefunItems.CARBONADO.item(),
                SlimefunItems.HEATING_COIL.item(), Items.ELECTRIC_FIRE_STAFF.item(), SlimefunItems.HEATING_COIL.item(),
                SlimefunItems.REINFORCED_ALLOY_INGOT.item(), SlimefunItems.MEDIUM_CAPACITOR.item(), SlimefunItems.REINFORCED_ALLOY_INGOT.item()
        });
    }

    @Override
    public void preRegister() {
        addItemHandler(onUse());
    }

    @Override
    public float getMaxItemCharge(@Nonnull ItemStack item) {
        return 200;
    }

    @Nonnull
    protected ItemUseHandler onUse() {
        return e -> {
            Player p = e.getPlayer();
            ItemStack item = e.getItem();

            if (removeItemCharge(item, COST)) {
                LargeFireball fireball = p.launchProjectile(LargeFireball.class);
                fireball.setVelocity(fireball.getVelocity().multiply(50));
            }
        };
    }
}
