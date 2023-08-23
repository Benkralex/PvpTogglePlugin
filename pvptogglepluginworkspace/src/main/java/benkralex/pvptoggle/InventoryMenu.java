



public class InventoryMenu {
	public static ItemStack getMenuItem(int i, Player p) {
		switch i {
			case 0:
				ItemStack itoggle = new ItemStack(Material.IRON_SWORD);
				ItemMeta mtoggle = itoggle.getItemMeta();
				mtoggle.setDispalyName("PvP-Schutz an/aus schalten");
				mtoggle.setLore(ChatColor.BLUE + (p.getPersistentDataContainer().get(new NamespacedKey(Pvptoggle.pvptoggle, "pvptoggle"), PersistentDataType.BOOLEAN)?"Aus":"An" +) " schalten");
				itoggle.setItemMeta(mtoggle);
				return itoggle;
				break;
			case 1:
				ItemStack iultra = new ItemStack(Material.DIAMOND_SWORD);
				ItemMeta multra = iultra.getItemMeta();
				multra.setDispalyName("Ultra an/aus schalten");
				multra.setLore(ChatColor.BLUE + (p.getPersistentDataContainer().get(new NamespacedKey(Pvptoggle.pvptoggle, "pvpultra"), PersistentDataType.BOOLEAN)?"Aus":"An") + " schalten");
				iultra.setItemMeta(multra);
				return iultra;
				break;
			case 2:
				ItemStack iwhitelist = new ItemStack(Material.WHITE_CONCRET);
				ItemMeta mwhitelist = iwhitelist.getItemMeta();
				mwhitelist.setDispalyName("Whitelist");
				iwhitelist.setItemMeta(mwhitelist);
				return iwhitelist;
				break;
			case 3:
				ItemStack iblacklist = new ItemStack(Material.BLACK_CONCRET);
				ItemMeta mblacklist = iblacklist.getItemMeta();
				mblacklist.setDispalyName("Blacklist");
				iblacklist.setItemMeta(mblacklist);
				return iblacklist;
				break;
			case 4:
				ItemStack iop = new ItemStack(Material.BEDROCK);
				ItemMeta mop = iop.getItemMeta();
				mop.setDispalyName("Menu für Operators");
				iop.setItemMeta(mop);
				/*if (p.hasPermission("pvp.inv.op")) {
				    inv.setItem(22, getMenuItem(4));
				}*/
				return iop;
				break;
			case 5:
				ItemStack iclose = new ItemStack(Material.BARRIER);
				ItemMeta mclose = iclose.getItemMeta();
				mclose.setDispalyName("Inventar schließen");
				iclose.setItemMeta(mclose);
				//inv.setItem(8, getMenuItem(5));
				return iclose;
				break;
			case 6:
				ItemStack iback = new ItemStack(Material.HOPPER);
				ItemMeta mback = iback.getItemMeta();
				mback.setDispalyName("Zurück");
				iback.setItemMeta(mback);
				//inv.setItem(26, getMenuItem(6));
				return iback;
				break;
			case 7:
				ItemStack ivoid = new ItemStack(Material.LIGHT_GRAY_STAIND_GLASS_PAIN);
  				ItemMeta mvoid = ivoid.getItemMeta();
		  		mvoid.setDisplayName("");
 				ivoid.setItemMeta(mvoid);
 		 		/*for (int i = 0, i <= 26, i++) {
    				if (inv.getItem(i) == Material.AIR) {
    					inv.set(i, getMenuItem(7));
    				}
  				}*/
				return ivoid;
				break;
			case 8:
				ItemStack idefaultprot = new ItemStack(Material.GOLDEN_SWORD);
				ItemMeta mdefaultprot = idefaultprot.getItemMeta();
				mdefaultprot.setDisplayName("Standard PvP-Schutz an/aus schalten");
				mdefaultprot.setLore(ChatColor.BLUE + (Config.getPvpProt()?"Aus":"An") + " schalten");
				idefaultprot.setItemMeta(mdefaultprot);
				return idefaultprot;
				break;
			case 9:
				ItemStack ipvptime = new ItemStack(Material.CLOCK);
				ItemMeta mpvptime = ipvptime.getItemMeta();
				mpvptime.setDisplayName("Zeit um zurückzuschlagen");
				mpvptime.setLore(ChatColor.BLUE + Config.getPvpTime() + " Sekunden um zurückzuschlagen");
				ipvptime.setItemMeta(mpvptime);
				return ipvptime;
				break;
			case 10:
				ItemStack ipvptimeplus = new ItemStack(Material.GREEN_WOOL);
				ItemMeta mpvptimeplus = ipvptimeplus.getItemMeta();
				mpvptimeplus.setDisplayName("Zeit um zurückzuschlagen erhöhen");
				mpvptimeplus.setLore(ChatColor.BLUE + "Rechtsklick:" + ChatColor.GREEN + " +1\n" + ChatColor.BLUE + "Linksklick:" + ChatColor.GREEN + " +10");
				ipvptimeplus.setItemMeta(mpvptimeplus);
				return ipvptimeplus;
				break;
			case 11:
				ItemStack ipvptimeminus = new ItemStack(Material.RED_WOOL);
				ItemMeta mpvptimeminus = ipvptimeminus.getItemMeta();
				mpvptimeminus.setDisplayName("Zeit um zurückzuschlagen verringern");
				mpvptimeminus.setLore(ChatColor.BLUE + "Rechtsklick:" + ChatColor.RED + " -1\n" + ChatColor.BLUE + "Linksklick:" + ChatColor.RED + " -10");
				ipvptimeminus.setItemMeta(mpvptimeminus);
				return ipvptimeminus;
				break;
		}
	}
	
	public static Inventory pvpMenuFillEmpty(Inventory inv, Player p) {
		for (int i = 0, i <= (inv.getSize-1), i++) {
    		if (inv.getItem(i) == Material.AIR) {
    			inv.set(i, getMenuItem(7, p));
    		}
  		}
		return inv;
	}
	
	public static Inventory pvpMenu(Player p) {
		Inventory inv = new Bukkit.createInventory(null, 3*9, "PvP-Menu");
		
		inv.setItem(10, getMenuItem(0, p));
		inv.setItem(12, getMenuItem(1, p));
		inv.setItem(14, getMenuItem(2, p));
		inv.setItem(16, getMenuItem(3, p));
		if (p.hasPermission("pvp.inv.op")) {
			inv.setItem(22, getMenuItem(4, p));
		}
		inv.setItem(8, getMenuItem(5, p));
		
		return pvpMenuFillEmpty(inv, p);
	}
	
	
	public static Inventory pvpBlacklistMenu(Player p) {
		Inventory inv = new Bukkit.createInventory(null, 3*9, "Blacklist-Menu");
		inv.setItem(8, getMenuItem(5, p));
		inv.setItem(26, getMenuItem(6, p));
		
		return pvpMenuFillEmpty(inv, p);
	}
	
	
	public static Inventory pvpWhitelistMenu(Player p) {
		Inventory inv = new Bukkit.createInventory(null, 3*9, "Whitelist-Menu");
		inv.setItem(8, getMenuItem(5, p));
		inv.setItem(26, getMenuItem(6, p));
		
		return pvpMenuFillEmpty(inv, p);
	}
	
	
	public static Inventory pvpOpSettingsMenu(Player p) {
		Inventory inv = new Bukkit.createInventory(null, 3*9, "Operator-Menu");
		inv.setItem(8, getMenuItem(5, p));
		inv.setItem(26, getMenuItem(6, p));
		inv.setItem(11, getMenuItem(8, p));
		inv.setItem(14, getMenuItem(11, p));
		inv.setItem(15, getMenuItem(9, p));
		inv.setItem(16, getMenuItem(10, p));
		
		return pvpMenuFillEmpty(inv, p);
	}
}


/*
0  1  2  3  4  5  6  7  8
9  10 11 12 13 14 15 16 17
18 19 20 21 22 23 24 25 26
*/
