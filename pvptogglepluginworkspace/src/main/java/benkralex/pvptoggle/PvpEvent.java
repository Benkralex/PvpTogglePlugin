package benkralex.pvptoggle;


import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.projectiles.ProjectileSource;

public class PvpEvent implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void pvpListener(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity victim = event.getEntity();

        if (victim instanceof Player) {
            Player victimplayer=(Player) victim;
            Player damagerplayer;
            if (damager instanceof Player) {
                damagerplayer=(Player) damager;
            } else if (damager instanceof Projectile) {
                ProjectileSource shooter =((Projectile) damager).getShooter();
                if(shooter instanceof Player){
                    damagerplayer=(Player) shooter;
                } else {
                    return;
                }
            } else {
                return;
            }

            if (!Util.canPvP(damagerplayer, victimplayer)) {
                event.setCancelled(true);
            } else {
                Util.savePvPData(damagerplayer,victimplayer);
            }
        }
    }

    @EventHandler
    public void playerLeaveListener(PlayerQuitEvent event){
        event.getPlayer().getPersistentDataContainer().remove(new NamespacedKey(Pvptoggle.pvptoggle,"pvpdamagers"));
    }

    @EventHandler
    public void playerJoinListener(PlayerJoinEvent event) {
        if (!event.getPlayer().getPersistentDataContainer().has(new NamespacedKey(Pvptoggle.pvptoggle, "pvptoggle"), PersistentDataType.BOOLEAN)) {
            event.getPlayer().getPersistentDataContainer().set(new NamespacedKey(Pvptoggle.pvptoggle, "pvptoggle"), PersistentDataType.BOOLEAN, Config.getPvpProt());
        } else {
            event.getPlayer().sendMessage("Dein PvP-Schutz ist " + (event.getPlayer().getPersistentDataContainer().get(new NamespacedKey(Pvptoggle.pvptoggle, "pvptoggle"), PersistentDataType.BOOLEAN)?"an":"aus"));
        }
    }
}
