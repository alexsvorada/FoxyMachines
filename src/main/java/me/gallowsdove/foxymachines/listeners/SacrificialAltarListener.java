package me.gallowsdove.foxymachines.listeners;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import me.gallowsdove.foxymachines.Items;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Fox;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.ThreadLocalRandom;

public class SacrificialAltarListener implements Listener {
    @EventHandler
    private void onDeath(EntityDeathEvent e) {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        LivingEntity entity = e.getEntity();
        if (findAltar(entity.getLocation().getBlock()) != null) {
            switch (entity.getType()) {
                case ARMOR_STAND -> {
                    return;
                }
                case RABBIT ->
                        entity.getWorld().dropItemNaturally(entity.getLocation(), new SlimefunItemStack(Items.CURSED_RABBIT_PAW, 1).item());
                case PLAYER ->
                        entity.getWorld().dropItemNaturally(entity.getLocation(), new SlimefunItemStack(Items.HUMAN_SKULL, 1).item());
                case WITHER_SKELETON -> {
                    if (random.nextInt(100) < 75) {
                        entity.getWorld().dropItemNaturally(entity.getLocation(), new SlimefunItemStack(Items.UNHOLY_WITHER_SKELETON_BONE, random.nextInt(100) < 33 ? 2 : 1).item());
                    }
                }
                case FOX -> {
                    if (((Fox) entity).getFoxType() == Fox.Type.SNOW) {
                        if (random.nextInt(100) < 75) {
                            entity.getWorld().dropItemNaturally(entity.getLocation(), new SlimefunItemStack(Items.POLAR_FOX_HIDE, random.nextInt(100) < 33 ? 2 : 1).item());
                        }
                    }
                }
                case MAGMA_CUBE -> {
                    if (random.nextInt(100) < 50) {
                        entity.getWorld().dropItemNaturally(entity.getLocation(), new SlimefunItemStack(Items.MAGMA_ESSENCE, random.nextInt(100) < 25 ? 2 : 1).item());
                    }
                }
                case PARROT -> {
                    if (random.nextInt(100) < 75) {
                        entity.getWorld().dropItemNaturally(entity.getLocation(), new SlimefunItemStack(Items.PARROT_FEATHER, random.nextInt(100) < 33 ? 2 : 1).item());
                    }
                }
                case TROPICAL_FISH -> {
                    if (random.nextInt(100) < 75) {
                        entity.getWorld().dropItemNaturally(entity.getLocation(), new SlimefunItemStack(Items.TROPICAL_FISH_SCALE, random.nextInt(100) < 33 ? 2 : 1).item());
                    }
                }
                default -> {
                }
            }

            if (random.nextInt(100) < 33) {
                entity.getWorld().dropItem(entity.getLocation(), new SlimefunItemStack(Items.BLOOD, random.nextInt(100) < 25 ? 2 : 1).item());
            }

            particleAnimation(entity.getLocation());
        }
    }

    @EventHandler(ignoreCancelled = true)
    private void onWaterTorchDestroy(BlockFromToEvent e) {
        if (e.getToBlock().getType() == Material.SOUL_TORCH && BlockStorage.hasBlockInfo(e.getToBlock())) {
            e.setCancelled(true);
        }
    }

    @Nullable
    public static Block findAltar(@Nonnull Block b) {
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    Block block = b.getRelative(x, y, z);

                    if (block.getType() == Material.POLISHED_BLACKSTONE_PRESSURE_PLATE && BlockStorage.getLocationInfo(block.getLocation(), "id") != null &&
                            BlockStorage.getLocationInfo(block.getLocation(), "id").equals("SACRIFICIAL_ALTAR_BLACKSTONE_PRESSURE_PLATE")) {
                        return block;
                    }
                }
            }
        }

        return null;
    }

    public static void particleAnimation(Location location) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < 16; i++) {
            location.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, location, 1,
                    random.nextDouble(-1, 1), random.nextDouble(1.2, 2), random.nextDouble(-1, 1), 0);
        }
        location.getWorld().spawnParticle(Particle.SOUL, location, 40);
    }
}
