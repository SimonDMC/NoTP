package me.simondmc.notp;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTeleportEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public final class NoTP extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void enderTP(EntityTeleportEvent e) {
        if (e.getEntityType() == EntityType.ENDERMAN) {
            e.getEntity().getNearbyEntities(3,3,3).forEach( entity -> {
                if (entity.getType() == EntityType.ARROW) e.setCancelled(true);
            });
        }
    }

    @EventHandler
    public void hitEnder(ProjectileHitEvent e) {
        if (e.getHitEntity() != null)
            if (e.getHitEntity().getType() == EntityType.ENDERMAN) {
                if (e.getHitEntity() instanceof LivingEntity) {
                    LivingEntity eman = (LivingEntity) e.getHitEntity();
                    eman.damage(6);
                    eman.setVelocity(eman.getLocation().toVector().subtract(e.getEntity().getLocation().toVector()).normalize().setY(.3333));
                    e.getEntity().remove();
                }
            }
    }
}
