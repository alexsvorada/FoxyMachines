package me.gallowsdove.foxymachines.implementation.mobs;

import me.gallowsdove.foxymachines.abstracts.CustomMob;
import me.gallowsdove.foxymachines.utils.Utils;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTargetEvent;

import javax.annotation.Nonnull;

public class Pixie extends CustomMob {
    public Pixie() {
        super("PIXIE", "Pixie", EntityType.VEX, 5);
    }

    @Override
    public void onSpawn(@Nonnull LivingEntity spawned) {
        spawned.getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(24);
    }

    @Override
    public void onDeath(@Nonnull EntityDeathEvent event) {
        super.onDeath(event);

        event.getDrops().clear();
    }

    @Override
    protected void onAttack(@Nonnull EntityDamageByEntityEvent event) {
        if (!event.isCancelled()) {
            Utils.dealDamageBypassingArmor((LivingEntity) event.getEntity(), (event.getDamage() - event.getFinalDamage()) * 0.2);
        }
    }

    @Override
    protected void onTarget(@Nonnull EntityTargetEvent event) {
        if (!(event.getTarget() instanceof Player)) {
            event.setCancelled(true);
        }
    }
}
