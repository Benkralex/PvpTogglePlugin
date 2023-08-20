package benkralex.pvptoggle;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener; 
import org.bukkit.entity.Player;

public class InventoryListener implements Listener{
    @EventHandler
    public void onPvpMenuInteract(InventoryInteractEvent e) {
        Player p = e.getWhoClicked();
        String invtitle = e.getView().getTitle();
        if (invtitle.equals("PvP-Menu")) {
            event.setCancled(true);
            if (event.getCurrendItem != null) {
                switch (e.getCurrendItem().getItemMeta().getDiplayName()) {
                    case "Inventar schließen":
                        p.closeInventory;
                        break;
                    case "Ultra an/aus schalten":
                        ItemStack iultra = new ItemStack(Material.DIAMOND_SWORD);
                        ItemMeta multra = iultra.getItemMeta();
                        multra.setDispalyName("Ultra an/aus schalten");
                        if (pdc.has(ultra, PersistentDataType.BOOLEAN)) {
                            pdc.set(ultra, PersistentDataType.BOOLEAN, !pdc.get(ultra, PersistentDataType.BOOLEAN));
                            sender.sendMessage("PvP-Ultra ist für dich jetzt " + (pdc.get(pvptoggle, PersistentDataType.BOOLEAN)?"an":"aus"));
                            multra.setLore(ChatColor.BLUE + p.getPersistentDataContainer.get(new NamespacedKey(Pvptoggle.pvptoggle, "pvpultra"), PersistentDataType.BOOLEAN)?"Aus":"An" + " schalten");
                            iultra.setItemMeta(multra);
                            inv.setItem(12, iultra);
                        } else {
                            pdc.set(ultra, PersistentDataContainer.BOOLEAN, true);
                            sender.sendMessage("PvP-Ultra ist für dich jetzt " + (pdc.get(pvptoggle, PersistentDataType.BOOLEAN)?"an":"aus"));
                            multra.setLore(ChatColor.BLUE + p.getPersistentDataContainer.get(new NamespacedKey(Pvptoggle.pvptoggle, "pvpultra"), PersistentDataType.BOOLEAN)?"Aus":"An" + " schalten");
                            iultra.setItemMeta(multra);
                            inv.setItem(12, iultra);
                        }
                        break;
                    case "PvP-Schutz an/aus schalten":
                        ItemStack itoggle = new ItemStack(Material.IRON_SWORD);
                        ItemMeta mtoggle = itoggle.getItemMeta();
                        mtoggle.setDispalyName("PvP-Schutz an/aus schalten");
                        if (pdc.has(pvptoggle, PersistentDataType.BOOLEAN)) {
                            pdc.set(pvptoggle, PersistentDataType.BOOLEAN, !pdc.get(pvptoggle, PersistentDataType.BOOLEAN));
                            sender.sendMessage("Dein PvP-Schutz ist jetzt " + (pdc.get(pvptoggle, PersistentDataType.BOOLEAN)?"an":"aus"));
                            mtoggle.setLore(ChatColor.BLUE + p.getPersistentDataContainer.get(new NamespacedKey(Pvptoggle.pvptoggle, "pvptoggle"), PersistentDataType.BOOLEAN)?"Aus":"An" + " schalten");
                            itoggle.setItemMeta(mtoggle);
                            inv.setItem(10, itoggle);
                        } else {
                            pdc.set(pvptoggle, PersistentDataType.BOOLEAN, Config.getPvpProt());
                            sender.sendMessage("Dein PvP-Schutz ist jetzt " + (pdc.get(pvptoggle, "pvptoggle"), PersistentDataType.BOOLEAN)?"an":"aus"));
                            mtoggle.setLore(ChatColor.BLUE + p.getPersistentDataContainer.get(new NamespacedKey(Pvptoggle.pvptoggle, "pvptoggle"), PersistentDataType.BOOLEAN)?"Aus":"An" + " schalten");
                            itoggle.setItemMeta(mtoggle);
                            inv.setItem(10, itoggle);
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
            if (event.getCurrendItem != null) {
                switch (e.getCurrendItem().getItemMeta().getDiplayName()) {
                case "Inventar schließen":
                        p.closeInventory;
                        break;
                case "Zurück":
                        p.closeInventory;
                        p.openInventory(InventoryMenu.pvpMenu(p));
                        break;
                }
            }
        } else if (invtitle.equals("Blacklist-Menu")) {
            if (event.getCurrendItem != null) {
                switch (e.getCurrendItem().getItemMeta().getDiplayName()) {
                case "Inventar schließen":
                        p.closeInventory;
                        break;
                case "Zurück":
                        p.closeInventory;
                        p.openInventory(InventoryMenu.pvpMenu(p));
                        break;
                }
            }
        } else if (invtitle.equals("Operator-Menu")) {
            if (event.getCurrendItem != null) {
                switch (e.getCurrendItem().getItemMeta().getDiplayName()) {
                case "Inventar schließen":
                        p.closeInventory;
                        break;
                case "Zurück":
                        p.closeInventory;
                        p.openInventory(InventoryMenu.pvpMenu(p));
                        break;
                }
            }
        }
    }
    
    public void onPvpMenuItemMove(InventoryMoveItemEvent e) {
        if (e.getInventory().getName().equals("PvP-Menu")) {
            event.setCancled(true);
        }
    }
}
