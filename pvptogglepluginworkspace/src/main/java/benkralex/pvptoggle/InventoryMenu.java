



public class InventoryMenu {
	
	public static Inventory pvpMenu(Player p) {
		Inventory inv = new Bukkit.createInventory(null, 3*9, "PvP-Menu");
		//Items erstellen
		ItemStack itoggle = new ItemStack(Material.IRON_SWORD);
		ItemStack iultra = new ItemStack(Material.DIAMOND_SWORD);
		ItemStack iwhitelist = new ItemStack(Material.WHITE_CONCRET);
		ItemStack iblacklist = new ItemStack(Material.BLACK_CONCRET);
		ItemStack iop = new ItemStack(Material.BEDROCK);
		ItemStack iclose = new ItemStack(Material.BARRIER);
		//ItemMeta hohlen
		ItemMeta mtoggle = itoggle.getItemMeta();
		ItemMeta multra = iultra.getItemMeta();
		ItemMeta mwhitelist = iwhitelist.getItemMeta();
		ItemMeta mblacklist = iblacklist.getItemMeta();
		ItemMeta mop = iop.getItemMeta();
		ItemMeta mclose = iclose.getItemMeta();
		//ItemMeta bearbeiten
		mtoggle.setDispalyName("PvP-Schutz an/aus schalten");
		mtoggle.setLore(ChatColor.BLUE + p.getPersistentDataContainer.get(new NamespacedKey(Pvptoggle.pvptoggle, "pvptoggle"), PersistentDataType.BOOLEAN)?"Aus":"An" + " schalten");
		multra.setDispalyName("Ultra an/aus schalten");
		multra.setLore(ChatColor.BLUE + p.getPersistentDataContainer.get(new NamespacedKey(Pvptoggle.pvptoggle, "pvpultra"), PersistentDataType.BOOLEAN)?"Aus":"An" + " schalten");
		mwhitelist.setDispalyName("Whitelist");
		mblacklist.setDispalyName("Blacklist");
		mop.setDispalyName("Menu für Operators");
		mclose.setDispalyName("Inventar schließen");
		//ItemMeta speichern
		itoggle.setItemMeta(mtoggle);
		iultra.setItemMeta(multra);
		iwhitelist.setItemMeta(mwhitelist);
		iblacklist.setItemMeta(mblacklist);
		iop.setItemMeta(mop);
		iclose.setItemMeta(mclose);
		//Items in das Inv machen
		inv.setItem(10, itoggle);
		inv.setItem(12, iultra);
		inv.setItem(14, iwhitelist);
		inv.setItem(16, iblacklist);
		inv.setItem(22, iop);
		inv.setItem(8, iclose);
		return inv;
	}
	
	
	public static Inventory pvpBlacklistMenu(Player p) {
		Inventory inv = new Bukkit.createInventory(null, 3*9, "Blacklist-Menu");
		//Items erstellen
		ItemStack iback = new ItemStack(Material.HOPPER);
		ItemStack iclose = new ItemStack(Material.BARRIER);
		//ItemMeta hohlen
		ItemMeta mback = iback.getItemMeta();
		ItemMeta mclose = iclose.getItemMeta();
		//ItemMeta bearbeiten
		mback.setDispalyName("Zurück");
		mclose.setDispalyName("Inventar schließen");
		//ItemMeta speichern
		iback.setItemMeta(mback);
		iclose.setItemMeta(mclose);
		//Items in das Inv machen
		inv.setItem(26, iback);
		inv.setItem(8, iclose);
		return inv;
	}
	
	
	public static Inventory pvpWhitelistMenu(Player p) {
		Inventory inv = new Bukkit.createInventory(null, 3*9, "Whitelist-Menu");
		//Items erstellen
		ItemStack iback = new ItemStack(Material.HOPPER);
		ItemStack iclose = new ItemStack(Material.BARRIER);
		//ItemMeta hohlen
		ItemMeta mback = iback.getItemMeta();
		ItemMeta mclose = iclose.getItemMeta();
		//ItemMeta bearbeiten
		mback.setDispalyName("Zurück");
		mclose.setDispalyName("Inventar schließen");
		//ItemMeta speichern
		iback.setItemMeta(mback);
		iclose.setItemMeta(mclose);
		//Items in das Inv machen
		inv.setItem(26, iback);
		inv.setItem(8, iclose);
		return inv;
	}
	
	
	public static Inventory pvpOpSettingsMenu(Player p) {
		Inventory inv = new Bukkit.createInventory(null, 3*9, "Operator-Menu");
		//Items erstellen
		ItemStack iback = new ItemStack(Material.HOPPER);
		ItemStack iclose = new ItemStack(Material.BARRIER);
		//ItemMeta hohlen
		ItemMeta mback = iback.getItemMeta();
		ItemMeta mclose = iclose.getItemMeta();
		//ItemMeta bearbeiten
		mback.setDispalyName("Zurück");
		mclose.setDispalyName("Inventar schließen");
		//ItemMeta speichern
		iback.setItemMeta(mback);
		iclose.setItemMeta(mclose);
		//Items in das Inv machen
		inv.setItem(26, iback);
		inv.setItem(8, iclose);
		return inv;
	}
}


/*
0  1  2  3  4  5  6  7  8
9  10 11 12 13 14 15 16 17
18 19 20 21 22 23 24 25 26

ItemStack iback = new ItemStack(Material.HOPPER);
ItemMeta mback = iback.getItemMeta();
mback.setDispalyName("Zurück");
iback.setItemMeta(mback);
inv.setItem(26, iback);
*/
