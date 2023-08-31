package benkralex.pvptoggle;

import org.bukkit.NamespacedKey;
import benkralex.pvptoggle.InventoryMenu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener; 
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class InventoryListener implements Listener{
    @EventHandler
    public void onPvpMenuInteract(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
	    Inventory inv = e.getClickedInventory();
        String invtitle = e.getView().getTitle();
        PersistentDataContainer pdc = p.getPersistentDataContainer();
        NamespacedKey pvptoggle = new NamespacedKey(Pvptoggle.pvptoggle, "pvptoggle");
        NamespacedKey ultra = new NamespacedKey(Pvptoggle.pvptoggle, "ultra");
        if (invtitle == "PvP-Menu") {
            e.setCancelled(true);
            if (e.getCurrentItem() != null) {
                String iname = e.getCurrentItem().getItemMeta().getDisplayName();
                if (iname.equals("Inventar schließen")) {
                    p.closeInventory();
                    return;
                } else if (iname.equals("Ultra an/aus schalten")) {
                    if (pdc.has(ultra, PersistentDataType.BOOLEAN)) {
                        pdc.set(ultra, PersistentDataType.BOOLEAN, !pdc.get(ultra, PersistentDataType.BOOLEAN));
                        p.sendMessage("PvP-Ultra ist für dich jetzt " + (pdc.get(ultra, PersistentDataType.BOOLEAN)?"an":"aus"));
                        inv.setItem(12, InventoryMenu.getMenuItem(1, p));
                    } else {
                        pdc.set(ultra, PersistentDataType.BOOLEAN, true);
                        p.sendMessage("PvP-Ultra ist für dich jetzt " + (pdc.get(ultra, PersistentDataType.BOOLEAN)?"an":"aus"));
                        inv.setItem(12, InventoryMenu.getMenuItem(1, p));
                    }
                    return;
                } else if(iname.equals("PvP-Schutz an/aus schalten")) {
                    if (pdc.has(pvptoggle, PersistentDataType.BOOLEAN)) {
                        pdc.set(pvptoggle, PersistentDataType.BOOLEAN, !pdc.get(pvptoggle, PersistentDataType.BOOLEAN));
                        p.sendMessage("Dein PvP-Schutz ist jetzt " + (pdc.get(pvptoggle, PersistentDataType.BOOLEAN)?"an":"aus"));
                        inv.setItem(10, InventoryMenu.getMenuItem(0, p));
                    } else {
                        pdc.set(pvptoggle, PersistentDataType.BOOLEAN, Config.getPvpProt());
                        p.sendMessage("Dein PvP-Schutz ist jetzt " + (pdc.get(pvptoggle, PersistentDataType.BOOLEAN)?"an":"aus"));
                        inv.setItem(10, InventoryMenu.getMenuItem(0, p));
                    }
                    return;
                } else if (iname.equals("Whitelist")) {
                    p.openInventory(InventoryMenu.pvpListMenu(p,"Whitelist","whitelist"));
                    return;
                } else if (iname.equals("Blacklist")) {
                    p.openInventory(InventoryMenu.pvpListMenu(p,"Blacklist","blacklist"));
                    return;
                } else if (iname.equals("Menu für Operators")) {
                    p.openInventory(InventoryMenu.pvpOpSettingsMenu(p));
                    return;
                } else {
                    return;
                }
            }
        } else if (invtitle.equals("Whitelist-Menu")) {
            if (e.getCurrentItem() != null) {
                e.setCancelled(true);
                String iname = e.getCurrentItem().getItemMeta().getDisplayName();
                if (iname.equals("Inventar schließen")) {
                    p.closeInventory();
                    return;
                } else if (iname.equals("Zurück")) {
                    p.openInventory(InventoryMenu.pvpMenu(p));
                    return;
                } else {
                    return;
                }
            }
        } else if (invtitle.equals("Blacklist-Menu")) {
            if (e.getCurrentItem() != null) {
                e.setCancelled(true);
                String iname = e.getCurrentItem().getItemMeta().getDisplayName();
                if (iname.equals("Inventar schließen")) {
                    p.closeInventory();
                    return;
                } else if (iname.equals("Zurück")) {
                    p.openInventory(InventoryMenu.pvpMenu(p));
                    return;
                } else {
                    return;
                }
            }
        } else if (invtitle.equals("Operator-Menu")) {
            if (e.getCurrentItem() != null) {
                e.setCancelled(true);
                String iname = e.getCurrentItem().getItemMeta().getDisplayName();
                if (iname.equals("Inventar schließen")) {
                    p.closeInventory();
                } else if (iname.equals("Zurück")) {
                    p.openInventory(InventoryMenu.pvpMenu(p));
                } else if (iname.equals("Standard PvP-Schutz an/aus schalten")){
                    Config.setPvpProt(!Config.getPvpProt());
                    inv.setItem(11, InventoryMenu.getMenuItem(8, p));
                } else if (iname.equals("Zeit um zurückzuschlagen erhöhen")) {
                    if (e.isRightClick()) {
                        Config.setPvpTime(Config.getPvpTime() + 1);
                    } else if (e.isLeftClick()) {
                        Config.setPvpTime(Config.getPvpTime() + 10);
                    }
                    inv.setItem(14, InventoryMenu.getMenuItem(InventoryMenu.PVP_TIME, p));
                } else if (iname.equals("Zeit um zurückzuschlagen verringern")) {
                    if (e.isRightClick()) {
                        if (!(Config.getPvpTime() < 1)) {
                            Config.setPvpTime(Config.getPvpTime() - 1);
                        }
                    } else if (e.isLeftClick()) {
                        if (!(Config.getPvpTime() < 10)) {
                            Config.setPvpTime(Config.getPvpTime() - 10);
                        }
                    }
                    inv.setItem(14, InventoryMenu.getMenuItem(InventoryMenu.PVP_TIME, p));
                }
            }
        }
    }
}
