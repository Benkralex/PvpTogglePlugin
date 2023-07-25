package benkralex.pvptoggle;


import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import sun.reflect.generics.tree.ArrayTypeSignature;

import java.sql.Array;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class PvpEvent implements Listener {

    @EventHandler
    public static void pvpListener(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity victim = event.getEntity();

        if (damager instanceof Player && victim instanceof Player) {
            List<PersistentDataContainer> pvpvictimsList;
            List<PersistentDataContainer> pvpvictimsListdamager;
            PersistentDataContainer pdc = victim.getPersistentDataContainer();
            PersistentDataContainer pdcdamager = damager.getPersistentDataContainer();
            PersistentDataContainer[] pvpvictims = pdc.get(new NamespacedKey(Pvptoggle.pvptoggle, "pvpvictims"), PersistentDataType.TAG_CONTAINER_ARRAY);
            PersistentDataContainer[] pvpvictimsdamager = pdcdamager.get(new NamespacedKey(Pvptoggle.pvptoggle, "pvpvictims"), PersistentDataType.TAG_CONTAINER_ARRAY);
            boolean canpvp = false;
            String victimuuid = victim.getUniqueId().toString();

            if (pvpvictims != null) {
                pvpvictimsList = new ArrayList<PersistentDataContainer>(Arrays.asList(pvpvictims));
            } else {
                pvpvictimsList = new ArrayList<PersistentDataContainer>();
            }

            if (pvpvictimsdamager != null) {
                pvpvictimsListdamager = new ArrayList<PersistentDataContainer>(Arrays.asList(pvpvictimsdamager));
            } else {
                pvpvictimsListdamager = new ArrayList<PersistentDataContainer>();
            }

            for (int i = 0; i<pvpvictimsListdamager.size(); i++) {
                PersistentDataContainer pdcfor = pvpvictimsListdamager.get(i);
                if (pdcfor.get(new NamespacedKey(Pvptoggle.pvptoggle, "time"), PersistentDataType.LONG) >= Instant.now().getEpochSecond() - Config.getpvptime()) {
                    if (pdcfor.get(new NamespacedKey(Pvptoggle.pvptoggle, "uuid"), PersistentDataType.STRING).equals(victimuuid)) {
                        canpvp = true;
                    }
                } else {
                    pvpvictimsListdamager.remove(i);
                    i--;
                }
            }

            if (pdc.has(new NamespacedKey(Pvptoggle.pvptoggle, "pvptoggle"), PersistentDataType.BOOLEAN) &&
                    pdc.get(new NamespacedKey(Pvptoggle.pvptoggle, "pvptoggle"), PersistentDataType.BOOLEAN) &&
                    !canpvp) {

                        event.setCancelled(true);

            } else {
                PersistentDataContainer pdcupdated = pdc.getAdapterContext().newPersistentDataContainer();
                String uuid = damager.getUniqueId().toString();
                pdcupdated.set(new NamespacedKey(Pvptoggle.pvptoggle, "uuid"), PersistentDataType.STRING, uuid);
                pdcupdated.set(new NamespacedKey(Pvptoggle.pvptoggle, "time"), PersistentDataType.LONG, Instant.now().getEpochSecond());
                pvpvictimsList.add(pdcupdated);
                pvpvictims = pvpvictimsList.toArray(new PersistentDataContainer[pvpvictimsList.size()]);
                pdc.set(new NamespacedKey(Pvptoggle.pvptoggle, "pvpvictims"), PersistentDataType.TAG_CONTAINER_ARRAY, pvpvictims);
            }
        }
    }

}
//pdc.getAdapterContext().newPersistentDataContainer()