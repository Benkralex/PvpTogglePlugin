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
        if (invtitle.equals("PvP-Menu")) {
            e.setCancelled(true);
            if (e.getCurrentItem() != null) {
                switch (e.getCurrentItem().getItemMeta().getDisplayName()) {
                    case "Inventar schließen":
                        p.closeInventory();
                        break;
                    case "Ultra an/aus schalten":
                        if (pdc.has(ultra, PersistentDataType.BOOLEAN)) {
                            pdc.set(ultra, PersistentDataType.BOOLEAN, !pdc.get(ultra, PersistentDataType.BOOLEAN));
                            p.sendMessage("PvP-Ultra ist für dich jetzt " + (pdc.get(pvptoggle, PersistentDataType.BOOLEAN)?"an":"aus"));
                            inv.setItem(12, InventoryMenu.getMenuItem(1, p));
                        } else {
                            pdc.set(ultra, PersistentDataType.BOOLEAN, true);
                            p.sendMessage("PvP-Ultra ist für dich jetzt " + (pdc.get(pvptoggle, PersistentDataType.BOOLEAN)?"an":"aus"));
                            inv.setItem(12, InventoryMenu.getMenuItem(1, p));
                        }
                        break;
                    case "PvP-Schutz an/aus schalten":
                        if (pdc.has(pvptoggle, PersistentDataType.BOOLEAN)) {
                            pdc.set(pvptoggle, PersistentDataType.BOOLEAN, !pdc.get(pvptoggle, PersistentDataType.BOOLEAN));
                            p.sendMessage("Dein PvP-Schutz ist jetzt " + (pdc.get(pvptoggle, PersistentDataType.BOOLEAN)?"an":"aus"));
                            inv.setItem(10, InventoryMenu.getMenuItem(0, p));
                        } else {
                            pdc.set(pvptoggle, PersistentDataType.BOOLEAN, Config.getPvpProt());
                            p.sendMessage("Dein PvP-Schutz ist jetzt " + (pdc.get(pvptoggle, PersistentDataType.BOOLEAN)?"an":"aus"));
                            inv.setItem(10, InventoryMenu.getMenuItem(0, p));
                        }
                        break;
                    case "Whitelist":
                        p.openInventory(InventoryMenu.pvpWhitelistMenu(p));
                        break;
                    case "Blacklist":
                        p.openInventory(InventoryMenu.pvpBlacklistMenu(p));
                        break;
                    case "Menu für Operators":
                        p.openInventory(InventoryMenu.pvpOpSettingsMenu(p));
                        break;
                    default:
                        break;
                }
            }
        } else if (invtitle.equals("Whitelist-Menu")) {
            if (e.getCurrentItem() != null) {
                switch (e.getCurrentItem().getItemMeta().getDisplayName()) {
                case "Inventar schließen":
                        p.closeInventory();
                        break;
                case "Zurück":
                        p.closeInventory();
                        p.openInventory(InventoryMenu.pvpMenu(p));
                        break;
                }
            }
        } else if (invtitle.equals("Blacklist-Menu")) {
            if (e.getCurrentItem() != null) {
                switch (e.getCurrentItem().getItemMeta().getDisplayName()) {
                case "Inventar schließen":
                        p.closeInventory();
                        break;
                case "Zurück":
                        p.closeInventory();
                        p.openInventory(InventoryMenu.pvpMenu(p));
                        break;
                }
            }
        } else if (invtitle.equals("Operator-Menu")) {
            if (e.getCurrentItem() != null) {
                switch (e.getCurrentItem().getItemMeta().getDisplayName()) {
                case "Inventar schließen":
                    p.closeInventory();
                    break;
                case "Zurück":
                    p.closeInventory();
                    p.openInventory(InventoryMenu.pvpMenu(p));
                    break;
				case "Standard PvP-Schutz an/aus schalten":
					Config.setPvpProt(!Config.getPvpProt());
					inv.setItem(11, InventoryMenu.getMenuItem(8, p));
					break;
				case "Zeit um zurückzuschlagen erhöhen":
					if (e.isRightClick()) {
						Config.setPvpTime(Config.getPvpTime() + 1);
					} else if (e.isLeftClick()) {
						Config.setPvpTime(Config.getPvpTime() + 10);
					}
					inv.setItem(15, InventoryMenu.getMenuItem(9, p));
					break;
				case "Zeit um zurückzuschlagen erniedrigen":
					if (e.isRightClick()) {
						Config.setPvpTime(Config.getPvpTime() - 1);
					} else if (e.isLeftClick()) {
						Config.setPvpTime(Config.getPvpTime() - 10);
					}
					inv.setItem(15, InventoryMenu.getMenuItem(9, p));
                	break;
				}
            }
        }
    }
}
