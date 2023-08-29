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
        boolean toggle = damagerPDC.getOrDefault(new NamespacedKey(Pvptoggle.pvptoggle, "pvptoggle"),PersistentDataType.BOOLEAN, false);
        boolean blacklisted;
        boolean whitelisted;
        PersistentDataContainer victimWithelist = victimPDC.get(new NamespacedKey(Pvptoggle.pvptoggle, "whitelist"), PersistentDataType.TAG_CONTAINER);
        if (victimWithelist != null) {
            whitelisted = victimWithelist.has(new NamespacedKey(Pvptoggle.pvptoggle, damager.getUniqueId().toString()), PersistentDataType.STRING);
        } else {
            whitelisted = false;
        }
        PersistentDataContainer victimBlacklist = victimPDC.get(new NamespacedKey(Pvptoggle.pvptoggle, "blacklist"), PersistentDataType.TAG_CONTAINER);
        if (victimBlacklist != null) {
            blacklisted = victimBlacklist.has(new NamespacedKey(Pvptoggle.pvptoggle, damager.getUniqueId().toString()), PersistentDataType.STRING);
        } else {
            blacklisted = false;
        }

        //return (!(toggle||ultravictim)||checkPvPData(damager,victim))&&!ultradamager;
        //debug
        victim.sendMessage(ChatColor.RED + "DEBUG:");
        victim.sendMessage("Rückschlag: " + checkPvPData(damager,victim));
        victim.sendMessage("Damager in Whitelist: " + whitelisted);
        victim.sendMessage("Damager in Blacklist: " + blacklisted);
        victim.sendMessage("PvP-Schutz (Toggle): " + toggle);
        victim.sendMessage("PvP-Schutz (Ultra): " + ultravictim);
        victim.sendMessage("Ultra bei Damager: " + ultradamager);
        victim.sendMessage("Schutz: " + (!toggle||!ultravictim));
        victim.sendMessage(" ");
        victim.sendMessage("2.Oder: " + (whitelisted && !ultradamager));
        victim.sendMessage("3.Oder: " + ((!toggle||!ultravictim) && !blacklisted && !ultradamager));

        damager.sendMessage(ChatColor.RED + "DEBUG:");
        damager.sendMessage("Rückschlag: " + checkPvPData(damager,victim));
        damager.sendMessage("Damager in Whitelist: " + whitelisted);
        damager.sendMessage("Damager in Blacklist: " + blacklisted);
        damager.sendMessage("Victim PvP-Schutz (Toggle): " + toggle);
        damager.sendMessage("Victim PvP-Schutz (Ultra): " + ultravictim);
        damager.sendMessage("Ultra bei Damager: " + ultradamager);
        damager.sendMessage("Victim Schutz: " + (!toggle||!ultravictim));
        damager.sendMessage(" ");
        damager.sendMessage("2.Oder: " + (whitelisted && !ultradamager));
        damager.sendMessage("3.Oder: " + ((!toggle||!ultravictim) && !blacklisted && !ultradamager));

        Pvptoggle.pvptoggle.getLogger().info("1.Oder: " + checkPvPData(damager,victim));
        Pvptoggle.pvptoggle.getLogger().info("2.Oder: " + (whitelisted && !ultradamager));
        Pvptoggle.pvptoggle.getLogger().info("3.Oder: " + (((!toggle||!ultravictim) && !blacklisted && !ultradamager)?"true":"false"));
        //debug ende

        return  checkPvPData(damager,victim) || (whitelisted && !ultradamager) || ((!toggle||!ultravictim) && !blacklisted && !ultradamager);
    }
    public static boolean checkPvPData(Player damager, Player victim){
        String victimUUID=victim.getUniqueId().toString();
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
            if(pvpdamagers.get(damagerKey,PersistentDataType.LONG)>= Instant.now().getEpochSecond() + Config.getPvpTime()){
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
