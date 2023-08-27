package benkralex.pvptoggle;

import benkralex.pvptoggle.Config;
import benkralex.pvptoggle.Pvptoggle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class InventoryMenu {
	public static final int PVP_TOGGLE=0;
	public static final int ULTRA_TOGGLE=1;
	public static final int WHITELIST=2;
	public static final int BLACKLIST=3;
	public static final int OP=4;
	public static final int CLOSE=5;
	public static final int BACK=6;
	public static final int VOID=7;
	public static final int DEFAULT_PROTECTION=8;
	public static final int PVP_TIME=9;
	public static final int PVP_TIME_PLUS=10;
	public static final int PVP_TIME_MINUS=11;
	
	public static ItemStack getMenuItem(int i, Player p) {
		List<String> lore = new ArrayList<>();
		switch (i) {
			case 0:
				ItemStack itoggle = new ItemStack(Material.IRON_SWORD);
				ItemMeta mtoggle = itoggle.getItemMeta();
				mtoggle.setDisplayName("PvP-Schutz an/aus schalten");
				mtoggle.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				String s3 = p.getPersistentDataContainer().getOrDefault(new NamespacedKey(Pvptoggle.pvptoggle, "pvptoggle"), PersistentDataType.BOOLEAN, false)?"an":"aus";
				lore.add(ChatColor.LIGHT_PURPLE + "Toggle ist " + s3);
				lore.add(ChatColor.BLUE + "");
				String s = p.getPersistentDataContainer().getOrDefault(new NamespacedKey(Pvptoggle.pvptoggle, "pvptoggle"), PersistentDataType.BOOLEAN, false)?"Aus":"An";
				lore.add(ChatColor.DARK_PURPLE + s + " schalten");
				mtoggle.setLore(lore);
				itoggle.setItemMeta(mtoggle);
				return itoggle;
			case 1:
				ItemStack iultra = new ItemStack(Material.DIAMOND_SWORD);
				ItemMeta multra = iultra.getItemMeta();
				multra.setDisplayName("Ultra an/aus schalten");
				multra.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				String s2 = p.getPersistentDataContainer().getOrDefault(new NamespacedKey(Pvptoggle.pvptoggle, "ultra"), PersistentDataType.BOOLEAN, false)?"an":"aus";
				lore.add(ChatColor.LIGHT_PURPLE + "Ultra ist " + s2);
				lore.add(ChatColor.BLUE + "");
				String s1 = p.getPersistentDataContainer().getOrDefault(new NamespacedKey(Pvptoggle.pvptoggle, "ultra"), PersistentDataType.BOOLEAN, false)?"Aus":"An";
				lore.add(ChatColor.DARK_PURPLE + s1 + " schalten");
				multra.setLore(lore);
				iultra.setItemMeta(multra);
				return iultra;
			case 2:
				ItemStack iwhitelist = new ItemStack(Material.WHITE_CONCRETE);
				ItemMeta mwhitelist = iwhitelist.getItemMeta();
				mwhitelist.setDisplayName("Whitelist");
				mwhitelist.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				iwhitelist.setItemMeta(mwhitelist);
				return iwhitelist;
			case 3:
				ItemStack iblacklist = new ItemStack(Material.BLACK_CONCRETE);
				ItemMeta mblacklist = iblacklist.getItemMeta();
				mblacklist.setDisplayName("Blacklist");
				mblacklist.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				iblacklist.setItemMeta(mblacklist);
				return iblacklist;
			case 4:
				ItemStack iop = new ItemStack(Material.BEDROCK);
				ItemMeta mop = iop.getItemMeta();
				mop.setDisplayName("Menu für Operators");
				mop.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				iop.setItemMeta(mop);
				/*if (p.hasPermission("pvp.inv.op")) {
				    inv.setItem(22, getMenuItem(4));
				}*/
				return iop;
			case 5:
				ItemStack iclose = new ItemStack(Material.BARRIER);
				ItemMeta mclose = iclose.getItemMeta();
				mclose.setDisplayName("Inventar schließen");
				mclose.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				iclose.setItemMeta(mclose);
				//inv.setItem(8, getMenuItem(5));
				return iclose;
			case 6:
				ItemStack iback = new ItemStack(Material.HOPPER);
				ItemMeta mback = iback.getItemMeta();
				mback.setDisplayName("Zurück");
				mback.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				iback.setItemMeta(mback);
				//inv.setItem(26, getMenuItem(6));
				return iback;
			case 7:
				ItemStack ivoid = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
  				ItemMeta mvoid = ivoid.getItemMeta();
		  		mvoid.setDisplayName("X");
				mvoid.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
 				ivoid.setItemMeta(mvoid);
 		 		/*for (int i = 0, i <= 26, i++) {
    				if (inv.getItem(i) == Material.AIR) {
    					inv.set(i, getMenuItem(7));
    				}
  				}*/
				return ivoid;
			case 8:
				ItemStack idefaultprot = new ItemStack(Material.GOLDEN_SWORD);
				ItemMeta mdefaultprot = idefaultprot.getItemMeta();
				mdefaultprot.setDisplayName("Standard PvP-Schutz an/aus schalten");
				lore.add(ChatColor.LIGHT_PURPLE + "Standart ist PvP-Schutz" + (Config.getPvpProt()?"an":"aus"));
				lore.add(ChatColor.BLUE + "");
				lore.add(ChatColor.DARK_PURPLE + (Config.getPvpProt()?"Aus":"An") + " schalten");
				mdefaultprot.setLore(lore);
				mdefaultprot.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				idefaultprot.setItemMeta(mdefaultprot);
				return idefaultprot;
			case 9:
				ItemStack ipvptime = new ItemStack(Material.CLOCK);
				ItemMeta mpvptime = ipvptime.getItemMeta();
				mpvptime.setDisplayName("Zeit um zurückzuschlagen");
				lore.add(ChatColor.BLUE + "" + Config.getPvpTime() + " Sekunden um zurückzuschlagen");
				mpvptime.setLore(lore);
				mpvptime.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				ipvptime.setItemMeta(mpvptime);
				return ipvptime;
			case 10:
				ItemStack ipvptimeplus = new ItemStack(Material.GREEN_WOOL);
				ItemMeta mpvptimeplus = ipvptimeplus.getItemMeta();
				mpvptimeplus.setDisplayName("Zeit um zurückzuschlagen erhöhen");
				mpvptimeplus.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				lore.add(ChatColor.BLUE + "Rechtsklick:" + ChatColor.GREEN + " +1");
				lore.add(ChatColor.BLUE + "Linksklick:" + ChatColor.GREEN + " +10");
				mpvptimeplus.setLore(lore);
				ipvptimeplus.setItemMeta(mpvptimeplus);
				return ipvptimeplus;
			case 11:
				ItemStack ipvptimeminus = new ItemStack(Material.RED_WOOL);
				ItemMeta mpvptimeminus = ipvptimeminus.getItemMeta();
				mpvptimeminus.setDisplayName("Zeit um zurückzuschlagen verringern");
				mpvptimeminus.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				lore.add(ChatColor.BLUE + "Rechtsklick:" + ChatColor.RED + " -1");
				lore.add(ChatColor.BLUE + "Linksklick:" + ChatColor.RED + " -10");
				mpvptimeminus.setLore(lore);
				ipvptimeminus.setItemMeta(mpvptimeminus);
				return ipvptimeminus;
			default:
				return null;
		}
	}
	
	public static Inventory pvpMenuFillEmpty(Inventory inv, Player p) {
		for (int i = 0; i <= inv.getSize()-1; i++) {
			try {
				if (inv.getItem(i) == null || inv.getItem(i).equals(Material.AIR)) {
					inv.setItem(i, getMenuItem(VOID, p));
				}
			} catch (Exception e) {
				Pvptoggle.pvptoggle.getLogger().warning(ChatColor.RED + "ERROR IN pvpMenuFillEmpty(): " + e.toString());
			}
  		}
		return inv;
	}
	
	public static Inventory pvpMenu(Player p) {
		if (p.hasPermission("pvp.inv.normal")) {
			Inventory inv = Bukkit.createInventory(null, 3 * 9, "PvP-Menu");
			Inventory inv2;

			inv.setItem(10, getMenuItem(PVP_TOGGLE, p));
			inv.setItem(12, getMenuItem(ULTRA_TOGGLE, p));
			inv.setItem(14, getMenuItem(WHITELIST, p));
			inv.setItem(16, getMenuItem(BLACKLIST, p));
			if (p.hasPermission("pvp.inv.op")) {
				inv.setItem(22, getMenuItem(OP, p));
			}
			inv.setItem(8, getMenuItem(CLOSE, p));

			inv2 = pvpMenuFillEmpty(inv, p);
			return inv2;
		} else {
			return pvpMenuFillEmpty(Bukkit.createInventory(null, 3*9, "No Permissiom"), p);
		}
	}
	
	
	public static Inventory pvpBlacklistMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 3*9, "Blacklist-Menu");
		inv.setItem(8, getMenuItem(CLOSE, p));
		inv.setItem(26, getMenuItem(BACK, p));
		
		return pvpMenuFillEmpty(inv, p);
	}
	
	
	public static Inventory pvpWhitelistMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 3*9, "Whitelist-Menu");
		inv.setItem(8, getMenuItem(CLOSE, p));
		inv.setItem(26, getMenuItem(BACK, p));
		
		return pvpMenuFillEmpty(inv, p);
	}
	
	
	public static Inventory pvpOpSettingsMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 3*9, "Operator-Menu");
		inv.setItem(8, getMenuItem(CLOSE, p));
		inv.setItem(26, getMenuItem(BACK, p));
		inv.setItem(11, getMenuItem(DEFAULT_PROTECTION, p));
		inv.setItem(13, getMenuItem(PVP_TIME_MINUS, p));
		inv.setItem(14, getMenuItem(PVP_TIME, p));
		inv.setItem(15, getMenuItem(PVP_TIME_PLUS, p));
		
		return pvpMenuFillEmpty(inv, p);
	}
}


/*
0  1  2  3  4  5  6  7  8
9  10 11 12 13 14 15 16 17
18 19 20 21 22 23 24 25 26
*/
