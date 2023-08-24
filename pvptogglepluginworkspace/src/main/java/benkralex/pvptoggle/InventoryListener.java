package benkralex.pvptoggle;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener; 
import org.bukkit.entity.Player;

public class InventoryListener implements Listener{
    @EventHandler
    public void onPvpMenuInteract(InventoryClickEvent e) {
        Player p = e.getWhoClicked();
	Inventory inv = e.getClickedInventory();
        String invtitle = e.getView().getTitle();
        if (invtitle.equals("PvP-Menu")) {
            event.setCancelled(true);
            if (event.getCurrentItem() != null) {
                switch (e.getCurrentItem().getItemMeta().getDisplayName()) {
                    case "Inventar schließen":
                        p.closeInventory();
                        break;
                    case "Ultra an/aus schalten":
                        multra.setDisplayName("Ultra an/aus schalten");
                        if (pdc.has(ultra, PersistentDataType.BOOLEAN)) {
                            pdc.set(ultra, PersistentDataType.BOOLEAN, !pdc.get(ultra, PersistentDataType.BOOLEAN));
                            sender.sendMessage("PvP-Ultra ist für dich jetzt " + (pdc.get(pvptoggle, PersistentDataType.BOOLEAN)?"an":"aus"));
                            inv.setItem(12, InventoryMenu.getMenuItem(1, p));
                        } else {
                            pdc.set(ultra, PersistentDataContainer.BOOLEAN, true);
                            sender.sendMessage("PvP-Ultra ist für dich jetzt " + (pdc.get(pvptoggle, PersistentDataType.BOOLEAN)?"an":"aus"));
                            inv.setItem(12, InventoryMenu.getMenuItem(1, p));
                        }
                        break;
                    case "PvP-Schutz an/aus schalten":
                        mtoggle.setDisplayName("PvP-Schutz an/aus schalten");
                        if (pdc.has(pvptoggle, PersistentDataType.BOOLEAN)) {
                            pdc.set(pvptoggle, PersistentDataType.BOOLEAN, !pdc.get(pvptoggle, PersistentDataType.BOOLEAN));
                            sender.sendMessage("Dein PvP-Schutz ist jetzt " + (pdc.get(pvptoggle, PersistentDataType.BOOLEAN)?"an":"aus"));
                            inv.setItem(10, InventoryMenu.getMenuItem(0, p));
                        } else {
                            pdc.set(pvptoggle, PersistentDataType.BOOLEAN, Config.getPvpProt());
                            sender.sendMessage("Dein PvP-Schutz ist jetzt " + (pdc.get(pvptoggle, "pvptoggle"), PersistentDataType.BOOLEAN)?"an":"aus"));
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
            if (event.getCurrentItem() != null) {
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
            if (event.getCurrentItem() != null) {
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
            if (event.getCurrentItem() != null) {
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
					inv.set(11, InventoryMenu.getMenuItem(8));
					break;
				case "Zeit um zurückzuschlagen erhöhen":
					if (e.isRightClick()) {
						Config.setPvpTime(getPvpTime() + 1);
					} else if (e.isLeftClick()) {
						Config.setPvpTime(getPvpTime() + 10);
					}
					inv.setItem(15, InventoryMenu.getMenuItem(9));
					break;
				case "Zeit um zurückzuschlagen erniedrigen":
					if (e.isRightClick()) {
						Config.setPvpTime(getPvpTime() - 1);
					} else if (e.isLeftClick()) {
						Config.setPvpTime(getPvpTime() - 10);
					}
					inv.setItem(15, InventoryMenu.getMenuItem(9, p));
                	break;
				}
            }
        }
    }
}
