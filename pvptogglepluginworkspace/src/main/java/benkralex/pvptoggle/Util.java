package benkralex.pvptoggle;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.time.Instant;

public class Util {
    public static boolean canPvP(Player damager, Player victim){
        PersistentDataContainer victimPDC=damager.getPersistentDataContainer();
        Boolean toggle = victimPDC.get(new NamespacedKey(Pvptoggle.pvptoggle, "pvptoggle"),PersistentDataType.BOOLEAN);
        if(toggle!=null) {
            return !toggle || checkPvPData(damager, victim);
        } else {
            return true;
        }
    }
    public static boolean checkPvPData(Player damager, Player victim){
        String victimUUID=victim.getUniqueId().toString();
        PersistentDataContainer damagerPDC=damager.getPersistentDataContainer();
        PersistentDataContainer damagersOfDamager=damagerPDC.get(new NamespacedKey(Pvptoggle.pvptoggle,"pvpdamagers"), PersistentDataType.TAG_CONTAINER);
        if (damagersOfDamager!=null) {
            delOldData(damagersOfDamager);
            return damagersOfDamager.has(new NamespacedKey(Pvptoggle.pvptoggle, victimUUID), PersistentDataType.LONG);
        } else {
            return false;
        }
    }
    public static void delOldData(PersistentDataContainer pvpdamagers){
        for(NamespacedKey damagerKey:pvpdamagers.getKeys()){
            if(pvpdamagers.get(damagerKey,PersistentDataType.LONG)>= Instant.now().getEpochSecond() + Config.getpvptime()){
                pvpdamagers.remove(damagerKey);
            }
        }
    }
    public static void savePvPData(Player damager, Player victim){
        PersistentDataContainer victimPDC=victim.getPersistentDataContainer();
        String damagerUUID=damager.getUniqueId().toString();

        PersistentDataContainer damagersOfVictim=victimPDC.get(new NamespacedKey(Pvptoggle.pvptoggle,"pvpdamagers"), PersistentDataType.TAG_CONTAINER);
        if(damagersOfVictim == null) {
            damagersOfVictim = victimPDC.getAdapterContext().newPersistentDataContainer();
        }
        damagersOfVictim.set(new NamespacedKey(Pvptoggle.pvptoggle, damagerUUID), PersistentDataType.LONG, Instant.now().getEpochSecond());
        victimPDC.set(new NamespacedKey(Pvptoggle.pvptoggle,"pvpdamagers"), PersistentDataType.TAG_CONTAINER,damagersOfVictim);
    }
}
