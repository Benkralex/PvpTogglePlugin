package benkralex.pvptoggle;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.jorel.commandapi.executors.CommandArguments;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class PvpCommand {
    private static final int SHOW_ACTION=1;
    private static final int ADD_ACTION=2;
    private static final int REMOVE_ACTION=3;
    private static final int CHALLENGE_ACTION=0;
    private static final int ACCEPT_ACTION=1;
    private static final int DENY_ACTION=2;

    public static void createPvpCommand() {
        //Create PVP-Command with Command-API
        new CommandAPICommand("pvp")
	        .executesPlayer(PvpCommand::pvpInv)
            .withUsage("/pvp")
            .withHelp("PvP Menu", "Du kannst damit ein Menu öffnen, in dem du alles verwalten kannst.")
                .withSubcommand(new CommandAPICommand("toggle")
                    .executesPlayer(PvpCommand::pvpToggle)
                    .withPermission("pvp.toggle")
                    .withUsage("/pvp toggle")
                    .withHelp("PvP damage für sich an/auschalten.", "Du kannst damit an/ausschalten, ob du geschlagen werden kannst."))
                .withSubcommand(new CommandAPICommand("ultra")
                    .executesPlayer(PvpCommand::pvpUltra)
                    .withPermission("pvp.ultra")
                    .withUsage("/pvp ultra")
                    .withHelp("Schutz + Schutz gegen ausversehen angreifen", "Du kannst damit an/ausschalten, ob du geschlagen werden kannst und andere schlagen kannst."))
            	.withSubcommand(new CommandAPICommand("whitelist")
                    .executesPlayer((sender, args)->{pvpList(sender, null,"whitelist","Whitelist", SHOW_ACTION);})
                    .withPermission("pvp.whitelist")
                    .withUsage("/pvp whitelist")
                    .withHelp("Whitelist zeigen", "Damit kannst du dir die Whitelist anzeigen. Alle die in der Whitelist sind können dich immer schlagen.")
                    	.withSubcommand(new CommandAPICommand("add")
                           .executesPlayer((sender, args)->{pvpList(sender, (Player) args.get("Player"),"whitelist","Whitelist", ADD_ACTION);})
                           .withPermission("pvp.whitelist")
                           .withUsage("/pvp whitelist add <Player>")
						   .withArguments(new PlayerArgument("Player"))
                           .withHelp("Spieler zu Whitelist hinzufügen", "Du kannst damit Spieler zu deiner Whitelist hinzufügen."))
                        .withSubcommand(new CommandAPICommand("remove")
                           .executesPlayer((sender, args)->{pvpList(sender, (Player) args.get("Player"),"whitelist","Whitelist", REMOVE_ACTION);})
                           .withPermission("pvp.whitelist")
                           .withUsage("/pvp whitelist remove <Player>")
						   .withArguments(new PlayerArgument("Player"))
                           .withHelp("Spieler aus Whitelist entfernen", "Du kannst damit Spieler aus deiner Whitelist entfernen.")))
            	.withSubcommand(new CommandAPICommand("blacklist")
                    .executesPlayer((sender, args)->{pvpList(sender, null,"blacklist","Blacklist", SHOW_ACTION);})
                    .withPermission("pvp.blacklist")
   	                .withUsage("/pvp blacklist <Player>")
					.withHelp("Blacklist anzeigen", "Damit kannst du dir die Blacklist anzeigen. Alle die in der Blacklist sind können dich nur zurückschlagen.")
           	        	.withSubcommand(new CommandAPICommand("add")
                            .executesPlayer((sender, args)->{pvpList(sender, (Player) args.get("Player"),"blacklist","Blacklist", ADD_ACTION);})
                            .withPermission("pvp.blacklist")
                            .withUsage("/pvp blacklist add <Player>")
						    .withArguments(new PlayerArgument("Player"))
                            .withHelp("Spieler zu Blacklist hinzufügen", "Du kannst damit Spieler zu deiner Blacklist hinzufügen."))
                        .withSubcommand(new CommandAPICommand("remove")
                            .executesPlayer((sender, args)->{pvpList(sender, (Player) args.get("Player"),"blacklist","Blacklist", REMOVE_ACTION);})
                            .withPermission("pvp.blacklist")
                            .withUsage("/pvp blacklist remove <Player>")
						    .withArguments(new PlayerArgument("Player"))
                            .withHelp("Spieler aus Blacklist entfernen", "Du kannst damit Spieler aus deiner Blacklist entfernen.")))
				.withSubcommand(new CommandAPICommand("fight")
					.executesPlayer((sender, args)->{pvpFight(sender, args, CHALLENGE_ACTION);})
					.withPermission("pvp.fight")
					.withUsage("/pvp fight <Player>")
					.withArguments(new PlayerArgument("Player"))
					.withHelp("Spieler zu einem Kampf herausfordern", "Man kann sich schlagen bis einer stirbt, der der stirbt verliert nichts.")
					.withSubcommand(new CommandAPICommand("accept")
						.executesPlayer((sender, args)->{pvpFight(sender, args, ACCEPT_ACTION);})
						.withPermission("pvp.fight")
						.withUsage("/pvp fight accept")
						.withHelp("Herausforderung annehmen", "Letzte Herrausforderung annehmen"))
					.withSubcommand(new CommandAPICommand("deny")
						.executesPlayer((sender, args)->{pvpFight(sender, args, DENY_ACTION);})
						.withPermission("pvp.fight")
						.withUsage("/pvp fight deny")
						.withHelp("Herausforderung ablehnen", "Letzte Herrausforderung ablehnen")))
				.register();
    }

	
	
	public static void pvpInv(Player sender, CommandArguments args) {
        if (sender.hasPermission("pvp.inv.normal")) {
            sender.openInventory(InventoryMenu.pvpMenu(sender));
        }
	}
	
	
	
    public static void pvpToggle(Player sender, CommandArguments args) {
        NamespacedKey pvptoggle = new NamespacedKey(Pvptoggle.pvptoggle, "pvptoggle");
        PersistentDataContainer pdc = sender.getPersistentDataContainer();
        if (pdc.has(pvptoggle, PersistentDataType.BOOLEAN)) {
            pdc.set(pvptoggle, PersistentDataType.BOOLEAN, !pdc.get(pvptoggle, PersistentDataType.BOOLEAN));
            sender.sendMessage("Dein PvP-Schutz ist jetzt " + (pdc.get(pvptoggle, PersistentDataType.BOOLEAN)?"an":"aus"));
        } else {
            pdc.set(pvptoggle, PersistentDataType.BOOLEAN, Config.getPvpProt());
            sender.sendMessage("Dein PvP-Schutz ist jetzt " + (pdc.get(pvptoggle, PersistentDataType.BOOLEAN)?"an":"aus"));
        }
    }

	
	
    public static void pvpUltra(Player sender, CommandArguments args) {
        //PvP Ultra Command
        NamespacedKey ultra = new NamespacedKey(Pvptoggle.pvptoggle, "ultra");
        PersistentDataContainer pdc = sender.getPersistentDataContainer();
        if (pdc.has(ultra, PersistentDataType.BOOLEAN)) {
            pdc.set(ultra, PersistentDataType.BOOLEAN, !pdc.get(ultra, PersistentDataType.BOOLEAN));
            sender.sendMessage("PvP-Ultra ist für dich jetzt " + (pdc.get(ultra, PersistentDataType.BOOLEAN)?"an":"aus"));
        } else {
            pdc.set(ultra, PersistentDataType.BOOLEAN, true);
            sender.sendMessage("PvP-Ultra ist für dich jetzt " + (pdc.get(ultra, PersistentDataType.BOOLEAN)?"an":"aus"));
        }
    }


    public static void pvpList(Player sender, Player ptarget, String key,String listName, int action) {
        //PvP Whitelist Command
        PersistentDataContainer pdc = sender.getPersistentDataContainer();
        PersistentDataContainer pdcList = pdc.getOrDefault(new NamespacedKey(Pvptoggle.pvptoggle,key),PersistentDataType.TAG_CONTAINER,pdc.getAdapterContext().newPersistentDataContainer());
        if (action == 1) {
            //anzeigen
            if(!pdcList.isEmpty()){
                int i = 0;
                sender.sendMessage(ChatColor.DARK_GREEN + "Deine " + listName + ":");
                for (NamespacedKey uuid:pdcList.getKeys()) {
                    i++;
                    sender.sendMessage("" + ChatColor.BLUE + i + ". " + Bukkit.getOfflinePlayer(UUID.fromString(uuid.getKey())).getName());
                }
            } else {
                sender.sendMessage("Deine " + listName + " ist leer");
            }
        } else if (action == 2) {
            //hinzufügen
            if (pdcList.has(new NamespacedKey(Pvptoggle.pvptoggle,ptarget.getUniqueId().toString()),PersistentDataType.TAG_CONTAINER)) {
                sender.sendMessage(ChatColor.RED + "Der Spieler " + ptarget.getDisplayName() + ChatColor.RED + " ist schon in deiner " + listName);
            }else{
                pdcList.set(new NamespacedKey(Pvptoggle.pvptoggle,ptarget.getUniqueId().toString()),PersistentDataType.TAG_CONTAINER,pdcList.getAdapterContext().newPersistentDataContainer());
                sender.sendMessage(ChatColor.GREEN + ptarget.getDisplayName() + ChatColor.GREEN + " wurde zu deiner "+listName+" hinzugefügt");
            }
        } else if (action == 3) {
            //entfernen
            if (!pdcList.has(new NamespacedKey(Pvptoggle.pvptoggle,ptarget.getUniqueId().toString()),PersistentDataType.TAG_CONTAINER)) {
                sender.sendMessage(ChatColor.RED + "Der Spieler " + ptarget.getDisplayName() + ChatColor.RED + " ist nicht in deiner "+listName);
            }else{
                pdcList.remove(new NamespacedKey(Pvptoggle.pvptoggle,ptarget.getUniqueId().toString()));
                sender.sendMessage(ChatColor.GREEN + ptarget.getDisplayName() + ChatColor.GREEN + " wurde aus deiner "+listName+" entfernt");
            }
        } else {
            sender.sendMessage("Fehler");
        }
        pdc.set(new NamespacedKey(Pvptoggle.pvptoggle,key),PersistentDataType.TAG_CONTAINER,pdcList);
    }


    public static void pvpFight(Player sender, CommandArguments args, int action) {
		//PvP Fight Command
		if (action == 0) {
			//Kampf herrausgefordert
			Player pargs = (Player) args.get("Player");
            TextComponent tc1 = new TextComponent("" + ChatColor.WHITE + " | ");
			TextComponent accept = new TextComponent(ChatColor.GREEN + "Annehmen");
			TextComponent deny = new TextComponent(ChatColor.RED + "Ablehnen");
			accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/pvp fight accept"));
			deny.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/pvp fight deny"));
			pargs.sendMessage("Du wurdest von " + sender.getDisplayName() + " zu einem Kampf herrausgefordert.");
            accept.addExtra(tc1);
            accept.addExtra(deny);
			pargs.spigot().sendMessage(accept);
			sender.sendMessage("Der Command funktioniert noch nicht");
		} else if (action == 1) {
			//Kampf angenommen
			String s = "Kampf angenomen, der Command funktioniert noch nicht";
			sender.sendMessage(s);
		} else if (action == 2) {
			//Kampf abgelehnt
			String s = "Kampf abgelehnt, der Command funktioniert noch nicht";
			sender.sendMessage(s);
		}
	}
}
