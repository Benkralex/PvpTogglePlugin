package benkralex.pvptoggle;

import dev.jorel.commandapi.executors.CommandArguments;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ListListener implements Listener {
    @EventHandler
    public static void onListMenuInteract(InventoryClickEvent e) {
        ItemStack item = e.getCurrentItem();
        String invtitle = e.getView().getTitle();
        Player p = (Player) e.getWhoClicked();

        if (item.getType() == Material.PLAYER_HEAD && e.isRightClick()) {
            Player ptarget = (Player) ((SkullMeta) item.getItemMeta()).getOwningPlayer();
            if (invtitle.equals("Whitelist-Menu")) {
                PvpCommand.pvpList(p, ptarget, "whitelist", "Whitelist", 3);
                p.openInventory(InventoryMenu.pvpWhitelistMenu(p));
            } else if (invtitle.equals("Blacklist-Menu")) {
                PvpCommand.pvpList(p, ptarget, "blacklist", "Blacklist", 3);
                p.openInventory(InventoryMenu.pvpBlacklistMenu(p));
            }
        }
    }
}
