package benkralex.pvptoggle;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.time.Instant;

public class Util {
    public static boolean canPvP(Player damager, Player victim){
        PersistentDataContainer damagerPDC=damager.getPersistentDataContainer();
        PersistentDataContainer victimPDC=victim.getPersistentDataContainer();
        boolean ultradamager = damagerPDC.getOrDefault(new NamespacedKey(Pvptoggle.pvptoggle, "ultra"),PersistentDataType.BOOLEAN, false);
        boolean ultravictim = victimPDC.getOrDefault(new NamespacedKey(Pvptoggle.pvptoggle, "ultra"),PersistentDataType.BOOLEAN, false);
        boolean toggle = victimPDC.getOrDefault(new NamespacedKey(Pvptoggle.pvptoggle, "pvptoggle"),PersistentDataType.BOOLEAN, false);
        boolean blacklisted;
        boolean whitelisted;
        PersistentDataContainer victimWithelist = victimPDC.get(new NamespacedKey(Pvptoggle.pvptoggle, "whitelist"), PersistentDataType.TAG_CONTAINER);
        if (victimWithelist != null) {
            whitelisted = victimWithelist.has(new NamespacedKey(Pvptoggle.pvptoggle, damager.getUniqueId().toString()), PersistentDataType.TAG_CONTAINER);
        } else {
            whitelisted = false;
        }
        PersistentDataContainer victimBlacklist = victimPDC.get(new NamespacedKey(Pvptoggle.pvptoggle, "blacklist"), PersistentDataType.TAG_CONTAINER);
        if (victimBlacklist != null) {
            blacklisted = victimBlacklist.has(new NamespacedKey(Pvptoggle.pvptoggle, damager.getUniqueId().toString()), PersistentDataType.TAG_CONTAINER);
        } else {
            blacklisted = false;
        }

        //einstellung ob ultradamager an kein rückschlag
        return  (checkPvPData(damager,victim) && !ultradamager) || (whitelisted && !ultradamager) || ((!toggle && !ultravictim) && !blacklisted && !ultradamager);
    }
    public static boolean checkPvPData(Player damager, Player victim){
        String victimUUID = victim.getUniqueId().toString();
        PersistentDataContainer damagerPDC = damager.getPersistentDataContainer();
        PersistentDataContainer damagersOfDamager = damagerPDC.get(new NamespacedKey(Pvptoggle.pvptoggle,"pvpdamagers"), PersistentDataType.TAG_CONTAINER);
        if (damagersOfDamager != null) {
            delOldData(damagersOfDamager);
            return damagersOfDamager.has(new NamespacedKey(Pvptoggle.pvptoggle, victimUUID), PersistentDataType.LONG);
        } else {
            return false;
        }
    }
    public static void delOldData(PersistentDataContainer pvpdamagers){
        for(NamespacedKey damagerKey:pvpdamagers.getKeys()){
            if(pvpdamagers.get(damagerKey, PersistentDataType.LONG) <= Instant.now().getEpochSecond() - Config.getPvpTime()){
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
