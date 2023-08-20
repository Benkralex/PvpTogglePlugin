package benkralex.pvptoggle;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener; 
import org.bukkit.entity.Player;

public class InventoryListener implements Listener{
    @EventHandler
    public void onPvpMenuInteract(InventoryInteractEvent e) {
        if (e.getView().getTitle().equals("PvP-Menu")) {
            event.setCancled(true);
            Player p = e.getWhoClicked();
            if (event.getCurrendItem != null) {
                switch (e.getCurrendItem().getItemMeta().getDiplayName()) {
                    case "Inventar schließen":
                        p.closeInventory;
                        break;
                    case "Ultra an/aus schalten":
                        if (pdc.has(ultra, PersistentDataType.BOOLEAN)) {
                            pdc.set(ultra, PersistentDataType.BOOLEAN, !pdc.get(ultra, PersistentDataType.BOOLEAN));
                            sender.sendMessage("PvP-Ultra ist für dich jetzt " + (pdc.get(pvptoggle, PersistentDataType.BOOLEAN)?"an":"aus"));
                        } else {
                            pdc.set(ultra, PersistentDataContainer.BOOLEAN, true);
                            sender.sendMessage("PvP-Ultra ist für dich jetzt " + (pdc.get(pvptoggle, PersistentDataType.BOOLEAN)?"an":"aus"));
                        }
                        break;
                    case "PvP-Schutz an/aus schalten":
                        if (pdc.has(pvptoggle, PersistentDataType.BOOLEAN)) {
                            pdc.set(pvptoggle, PersistentDataType.BOOLEAN, !pdc.get(pvptoggle, PersistentDataType.BOOLEAN));
                            sender.sendMessage("Dein PvP-Schutz ist jetzt " + (pdc.get(pvptoggle, PersistentDataType.BOOLEAN)?"an":"aus"));
                        } else {
                            pdc.set(pvptoggle, PersistentDataType.BOOLEAN, Config.getPvpProt());
                            sender.sendMessage("Dein PvP-Schutz ist jetzt " + (pdc.get(pvptoggle, "pvptoggle"), PersistentDataType.BOOLEAN)?"an":"aus"));
                        }
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
