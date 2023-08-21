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
                    .withHelp("Command noch nicht verfügbar", "Damit kannst du dir die Whitelist anzeigen. Alle die in der Whitelist sind können dich immer schlagen.")
                    	.withSubcommand(new CommandAPICommand("add")
                           .executesPlayer((sender, args)->{pvpWhitelist(sender, args, 2);})
                           .withPermission("pvp.whitelist")
                           .withUsage("/pvp whitelist add <Player>")
                           .withHelp("Command noch nicht verfügbar", "Du kannst damit Spieler zu deiner Whitelist hinzufügen."))
                        .withSubcommand(new CommandAPICommand("remove")
                           .executesPlayer((sender, args)->{pvpWhitelist(sender, args, 3);})
                           .withPermission("pvp.whitelist")
                           .withUsage("/pvp whitelist remove <Player>")
                           .withHelp("Command noch nicht verfügbar", "Du kannst damit Spieler aus deiner Whitelist entfernen.")))
            	.withSubcommand(new CommandAPICommand("blacklist")
                    .executesPlayer((sender, args)->{pvpBlacklist(sender, args, 1);})
                    .withPermission("pvp.blacklist")
   	                .withUsage("/pvp blacklist <Player>")
					.withHelp("Command noch nicht verfügbar", "Damit kannst du dir die Blacklist anzeigen. Alle die in der Blacklist sind können dich nur zurückschlagen.")
           	        	.withSubcommand(new CommandAPICommand("add")
                            .executesPlayer((sender, args)->{pvpBlacklist(sender, args, 2);})
                            .withPermission("pvp.blacklist")
                            .withUsage("/pvp blacklist add <Player>")
                            .withHelp("Command noch nicht verfügbar", "Du kannst damit Spieler zu deiner Blacklist hinzufügen."))
                        .withSubcommand(new CommandAPICommand("remove")
                            .executesPlayer((sender, args)->{pvpBlacklist(sender, args, 3);})
                            .withPermission("pvp.blacklist")
                            .withUsage("/pvp blacklist remove <Player>")
                            .withHelp("Command noch nicht verfügbar", "Du kannst damit Spieler aus deiner Blacklist entfernen.")))
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
                //for () {
                //    sender.sendMessage();
                //}
            }
            sender.sendMessage("Du kannst dir die Whitelist nicht anzeigen");
        } else if (action == 2) {
            //hinzufügen
            if (pdc.has(whitelist, PersistentDataType.TAG_CONTAINER)) {
                //
            }
            sender.sendMessage("Du kannst " + args[0] + " nicht zur der Whitelist hinzufügen");
        } else if (action == 3) {
            //entfernen
            if (pdc.has(whitelist, PersistentDataType.TAG_CONTAINER)) {
                //
            }
            sender.sendMessage("Du kannst " + args[0] + " nicht aus der Whitelist entfernen");
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
                //
            }
            sender.sendMessage("Du kannst dir die Blacklist nicht anzeigen");
        } else if (action == 2) {
            //hinzufügen
            if (pdc.has(blacklist, PersistentDataType.TAG_CONTAINER)) {
                //
            }
            sender.sendMessage("Du kannst " + args[0] + " nicht zur der Blacklist hinzufügen");
        } else if (action == 3) {
            //entfernen
            if (pdc.has(blacklist, PersistentDataType.TAG_CONTAINER)) {
                //
            }
            sender.sendMessage("Du kannst " + args[0] + " nicht aus der Blacklist entfernen");
        } else {
            sender.sendMessage("Fehler");
        }
    }
}
