package benkralex.pvptoggle;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.executors.CommandArguments;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class PvpCommand {
    public static void createPvpCommand() {
        //Create PVP-Command with Command-API
        new CommandAPICommand("pvp")
	        .executesPlayer((sender, args)->{pvpInv(sender, args);})
            .withPermission("pvp.inv.op")
            .withUsage("/pvp")
            .withHelp("PvP Menu", "Du kannst damit ein Menu öffnen, in dem du alles verwalten kannst.")
                .withSubcommand(new CommandAPICommand("toggle")
                    .executesPlayer((sender, args)->{pvpToggle(sender, args);})
                    .withPermission("pvp.toggle")
                    .withUsage("/pvp toggle")
                    .withHelp("PvP damage für sich an/auschalten.", "Du kannst damit an/ausschalten, ob du geschlagen werden kannst."))
                .withSubcommand(new CommandAPICommand("ultra")
                    .executesPlayer((sender, args)->{pvpUltra(sender, args);})
                    .withPermission("pvp.ultra")
                    .withUsage("/pvp ultra")
                    .withHelp("Schutz + Schutz gegen ausversehen angreifen", "Du kannst damit an/ausschalten, ob du geschlagen werden kannst und andere schlagen kannst."))
            	.withSubcommand(new CommandAPICommand("whitelist")
                    .executesPlayer((sender, args)->{pvpWhitelist(sender, args, 1);})
                    .withPermission("pvp.whitelist")
                    .withUsage("/pvp whitelist")
                    .withHelp("Whitelist zeigen", "Damit kannst du dir die Whitelist anzeigen. Alle die in der Whitelist sind können dich immer schlagen.")
                    	.withSubcommand(new CommandAPICommand("add")
                           .executesPlayer((sender, args)->{pvpWhitelist(sender, args, 2);})
                           .withPermission("pvp.whitelist")
                           .withUsage("/pvp whitelist add <Player>")
						   .withArguments(new PlayerArgument("Player"))
                           .withHelp("Spieler zu Whitelist hinzufügen", "Du kannst damit Spieler zu deiner Whitelist hinzufügen."))
                        .withSubcommand(new CommandAPICommand("remove")
                           .executesPlayer((sender, args)->{pvpWhitelist(sender, args, 3);})
                           .withPermission("pvp.whitelist")
                           .withUsage("/pvp whitelist remove <Player>")
						   .withArguments(new PlayerArgument("Player"))
                           .withHelp("Spieler aus Whitelist entfernen", "Du kannst damit Spieler aus deiner Whitelist entfernen.")))
            	.withSubcommand(new CommandAPICommand("blacklist")
                    .executesPlayer((sender, args)->{pvpBlacklist(sender, args, 1);})
                    .withPermission("pvp.blacklist")
   	                .withUsage("/pvp blacklist <Player>")
					.withHelp("Blacklist anzeigen", "Damit kannst du dir die Blacklist anzeigen. Alle die in der Blacklist sind können dich nur zurückschlagen.")
           	        	.withSubcommand(new CommandAPICommand("add")
                            .executesPlayer((sender, args)->{pvpBlacklist(sender, args, 2);})
                            .withPermission("pvp.blacklist")
                            .withUsage("/pvp blacklist add <Player>")
						    .withArguments(new PlayerArgument("Player"))
                            .withHelp("Spieler zu Blacklist hinzufügen", "Du kannst damit Spieler zu deiner Blacklist hinzufügen."))
                        .withSubcommand(new CommandAPICommand("remove")
                            .executesPlayer((sender, args)->{pvpBlacklist(sender, args, 3);})
                            .withPermission("pvp.blacklist")
                            .withUsage("/pvp blacklist remove <Player>")
						    .withArguments(new PlayerArgument("Player"))
                            .withHelp("Spieler aus Blacklist entfernen", "Du kannst damit Spieler aus deiner Blacklist entfernen.")))
						.withSubcommand(new ConmmandAPICommand("fight")
							.executesPlayer((sender, args)->{pvpFight(sender, args);})
							.withPermission("pvp.fight")
							.withUsage("/pvp fight <Player>")
							.withArguments(new PlayerArgument("Player"))
							.withHelp("Spieler zu einem Kampf herrausfordern", "Man kann sich schlagen bis einer stirbt, der der stirbt verliert nichts."))
				.register();
    }

	
	
	public static void pvpInv(Player sender, CommandArguments args) {
		 sender.openInventory(InventoryMenu.pvpMenu(sender));
	}
	
	
	
    public static void pvpToggle(Player sender, CommandArguments args) {
        NamespacedKey pvptoggle = new NamespacedKey(Pvptoggle.pvptoggle, "pvptoggle");
        PersistentDataContainer pdc = sender.getPersistentDataContainer();
        if (pdc.has(pvptoggle, PersistentDataType.BOOLEAN)) {
            pdc.set(pvptoggle, PersistentDataType.BOOLEAN, !pdc.get(pvptoggle, PersistentDataType.BOOLEAN));
            sender.sendMessage("Dein PvP-Schutz ist jetzt " + (pdc.get(pvptoggle, PersistentDataType.BOOLEAN)?"an":"aus"));
        } else {
            pdc.set(pvptoggle, PersistentDataType.BOOLEAN, Config.getPvpProt());
            sender.sendMessage("Dein PvP-Schutz ist jetzt " + (pdc.get(pvptoggle, "pvptoggle"), PersistentDataType.BOOLEAN)?"an":"aus"));
        }
    }

	
	
    public static void pvpUltra(Player sender, CommandArguments args) {
        //PvP Ultra Command
        NamespacedKey ultra = new NamespacedKey(Pvptoggle.pvptoggle, "ultra");
        PersistentDataContainer pdc = sender.getPersistentDataContainer();
        if (pdc.has(ultra, PersistentDataType.BOOLEAN)) {
            pdc.set(ultra, PersistentDataType.BOOLEAN, !pdc.get(ultra, PersistentDataType.BOOLEAN));
            sender.sendMessage("PvP-Ultra ist für dich jetzt " + (pdc.get(pvptoggle, PersistentDataType.BOOLEAN)?"an":"aus"));
        } else {
            pdc.set(ultra, PersistentDataContainer.BOOLEAN, true);
            sender.sendMessage("PvP-Ultra ist für dich jetzt " + (pdc.get(pvptoggle, PersistentDataType.BOOLEAN)?"an":"aus"));
        }
    }

	
	
    public static void pvpWhitelist(Player sender, CommandArguments args, Int action) {
        //PvP Whitelist Command
        PersistentDataContainer pdc = sender.getPersistentDataContainer();
        NamespacedKey whitelist = new NamespacedKey(Pvptoggle.pvptoggle, "whitelist");
        if (action == 1) {
            //anzeigen
            if (pdc.has(whitelist, PersistentDataType.TAG_CONTAINER)) {
				PersistentDataContainer pdcwhitelist = pdc.get(whitelist, PersistentDataType.TAG_CONTAINER);
				if(pdcwhitelist != null) {
					int i = 0;
        	        for (NamespacedKey whitelistkey:pdcwhitelist.getKeys()) {
                        i++;
    	                sender.sendMessage(ChatColor.LIGHT_BLUE + i + ". " + ChatColor.BLUE + Bukkit.getPlayer(UUID.fromString(pdcwhitelist.get(whitelistkey, PersistentDataType.STRING))).getDisplayName());
                    }
				} else {
					sender.sendMessage("Deine Whitelist ist leer");
				}
            } else {
		    	sender.sendMessage("Deine Whitelist ist leer");
	    	}
            sender.sendMessage(ChatColor.RED + "Die Whitelist funktioniert aktuell nicht");
        } else if (action == 2) {
            //hinzufügen
            if (!pdc.has(whitelist, PersistentDataType.TAG_CONTAINER)) {
                //pdc erstellen
            }
            PersistentDataContainer pdcwhitelist = pdc.get(whitelist, PersistentDataType.TAG_CONTAINER);
            pdcwhitelist.add(new NamespacedKey(Pvptoggle.pvptoggle, args.get("Player").getUniqueId().toString()),  PersistentDataType.STRING, args.get("Player").getUniqueId().toString()));
            sender.sendMessage(ChatColor.LIGHT_GREEN + args.get("Player").getDisplayName() + "wurde zu deiner Whitelist hinzugefügt");
            
            sender.sendMessage(ChatColor.RED + "Die Whitelist funktioniert aktuell nicht");
        } else if (action == 3) {
            //entfernen
            if (!pdc.has(whitelist, PersistentDataType.TAG_CONTAINER)) {
                sender.sendMessage(ChatColor.RED + "Der Spieler" + args.get("Player") + "ist nicht in deiner Whitelist");
				return;
            }
            PersistentDataContainer whitelistpdc = pdc.get(whitelist, PersistentDataType.TAG_CONTAINER); 
            if (whitelistpdc.has(new NamespacedKey(Pvptoggle.pvptoggle, args.get("Player").getUniqueId()))) {
                whitelistpdc.remove(new NamespacedKey(Pvptoggle.pvptoggle, args.get("Player").getUniqueId().toString()), PersistentDataType.STRING);
                sender.sendMessage(ChatColor.LIGHT_GREEN + args.get("Player").getDispalyName() + "wurde aus deiner Whitelist entfernt");
            } else {
                sender.sendMessage(ChatColor.RED + args.get("Player").getDisplayName() + "ist nicht in deiner Whitelist");
            }
            
            sender.sendMessage(ChatColor.RED + "Die Whitelist funktioniert aktuell nicht");
        } else {
            sender.sendMessage("Fehler");
        }
    }

	
	
    public static void pvpBlacklist(Player sender, CommandArguments args, Int action) {
        //PvP Blacklist Command
        PersistentDataContainer pdc = sender.getPersistentDataContainer();
        NamespacedKey blacklist = new NamespacedKey(Pvptoggle.pvptoggle, "blacklist");
        if (action == 1) {
            //anzeigen
            if (pdc.has(blacklist, PersistentDataType.TAG_CONTAINER)) {
				PersistentDataContainer pdcblacklist = pdc.get(blacklist, PersistentDataType.TAG_CONTAINER);
				if(pdcblacklist != null) {
					int i = 0;
        	        for (NamespacedKey blacklistkey:pdcblacklist.getKeys()) {
                        i++;
    	                sender.sendMessage(ChatColor.LIGHT_BLUE + i + ". " + ChatColor.BLUE + Bukkit.getPlayer(UUID.fromString(pdcblacklist.get(blacklistkey, PersistentDataType.STRING))).getDisplayName());
                    }
				} else {
					sender.sendMessage("Deine Blacklist ist leer");
				}
            } else {
		    	sender.sendMessage("Deine Blacklist ist leer");
	    	}
            sender.sendMessage(ChatColor.RED + "Die Blacklist funktioniert aktuell nicht");
        } else if (action == 2) {
            //hinzufügen
            if (!pdc.has(blacklist, PersistentDataType.TAG_CONTAINER)) {
                //pdc erstellen
            }
            PersistentDataContainer pdcblacklist = pdc.get(blacklistlist, PersistentDataType.TAG_CONTAINER);
            pdcblacklist.add(new NamespacedKey(Pvptoggle.pvptoggle, args.get("Player").getUniqueId().toString()),  PersistentDataType.STRING, args.get("Player").getUniqueId().toString()));
            sender.sendMessage(ChatColor.LIGHT_GREEN + args.get("Player").getDisplayName() + "wurde zu deiner Blacklist hinzugefügt");
            
            sender.sendMessage(ChatColor.RED + "Die Blacklist funktioniert aktuell nicht");
        } else if (action == 3) {
            //entfernen
            if (!pdc.has(blacklistlist, PersistentDataType.TAG_CONTAINER)) {
                sender.sendMessage(ChatColor.RED + "Der Spieler" + args.get("Player") + "ist nicht in deiner Blacklist");
				return;
            }
            PersistentDataContainer blacklistpdc = pdc.get(blacklist, PersistentDataType.TAG_CONTAINER); 
            if (blacklistpdc.has(new NamespacedKey(Pvptoggle.pvptoggle, args.get("Player").getUniqueId()))) {
                whitelistpdc.remove(new NamespacedKey(Pvptoggle.pvptoggle, args.get("Player").getUniqueId().toString()), PersistentDataType.STRING);
                sender.sendMessage(ChatColor.LIGHT_GREEN + args.get("Player").getDispalyName() + "wurde aus deiner Blacklist entfernt");
            } else {
                sender.sendMessage(ChatColor.RED + args.get("Player").getDisplayName() + "ist nicht in deiner Blacklist");
            }
            
            sender.sendMessage(ChatColor.RED + "Die Blacklist funktioniert aktuell nicht");
        } else {
            sender.sendMessage("Fehler");
        }
    }

	public static void pvpFight(Player sender, Commandarguments args) {
		//PvP Fight Command
		Player pargs = args.get("Player");
		Boolean fight;
		TextComponent accept = new TextComponent(ChatColor.LIGHT_GREEN + "Annehmen");
		TextComponent deny = new TextComponent(ChatColor.RED + "Ablehnen");
		pargs.sendMessage("Du wurdest von " + sender.getDisplayName() + " zu einem Kampf herrausgefordert.");
		pargs.sendMessage(accept + ChatColor.WHITE + " | " + deny);
		
	}
}
