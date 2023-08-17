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
                .withSubcommand(new CommandAPICommand("toggle")
                    .executesPlayer((sender, args)->{pvpToggle(sender, args);})
                    .withPermission("pvp.toggle")
                    .withUsage("/pvp toggle")
                    .withHelp("PvP damage für sich an/auschalten.", "Du kannst damit an/ausschalten, ob du geschlagen werden kannst."))
                .withSubcommand(new CommandAPICommand("ultra")
                    .executesPlayer((sender, args)->{pvpUltra(sender, args);})
                    .withPermission("pvp.ultra")
                    .withUsage("/pvp ultra")
                    .withHelp("Command noch nicht verfügbar", "Du kannst damit an/ausschalten, ob du geschlagen werden kannst und andere schlagen kannst."))
            .withSubcommand(new CommandAPICommand("trust")
                    .executesPlayer((sender, args)->{pvpTrust(sender, args, 1);})
                    .withPermission("pvp.trust")
                    .withUsage("/pvp trust")
                    .withHelp("Command noch nicht verfügbar", "Damit kannst du dir die Trust-List anzeigen. Alle die in der Trustliste sind können dich immer schlagen.")
                           .withSubcommand(new CommandAPICommand("add")
                               .executesPlayer((sender, args)->{pvpTrust(sender, args, 2);})
                               .withPermission("pvp.trust")
                               .withUsage("/pvp trust add <Player>")
                               .withHelp("Command noch nicht verfügbar", "Du kannst damit Spieler zu deiner Trust-Liste hinzufügen."))
                           .withSubcommand(new CommandAPICommand("remove")
                               .executesPlayer((sender, args)->{pvpTrust(sender, args, 3);})
                               .withPermission("pvp.trust")
                               .withUsage("/pvp trust remove <Player>")
                               .withHelp("Command noch nicht verfügbar", "Du kannst damit Spieler aus deiner Trust-Liste entfernen.")))
            .withSubcommand(new CommandAPICommand("misstrust")
                    .executesPlayer((sender, args)->{pvpMisstrust(sender, args, 1);})
                    .withPermission("pvp.misstrust")
                    .withUsage("/pvp misstrust <Player>")
                    .withHelp("Command noch nicht verfügbar", "Damit kannst du dir die Misstrust-List anzeigen. Alle die in der Misstrustliste sind können dich nur zurückschlagen.")
                           .withSubcommand(new CommandAPICommand("add")
                               .executesPlayer((sender, args)->{pvpMisstrust(sender, args, 2);})
                               .withPermission("pvp.misstrust")
                               .withUsage("/pvp misstrust add <Player>")
                               .withHelp("Command noch nicht verfügbar", "Du kannst damit Spieler zu deiner Misstrust-Liste hinzufügen."))
                           .withSubcommand(new CommandAPICommand("remove")
                               .executesPlayer((sender, args)->{pvpMisstrust(sender, args, 3);})
                               .withPermission("pvp.misstrust")
                               .withUsage("/pvp misstrust remove <Player>")
                               .withHelp("Command noch nicht verfügbar", "Du kannst damit Spieler aus deiner Misstrust-Liste entfernen.")))
            .register();
    }

    public static void pvpToggle(Player sender, CommandArguments args) {
        NamespacedKey pvptoggle = new NamespacedKey(Pvptoggle.pvptoggle, "pvptoggle");
        PersistentDataContainer pdc = sender.getPersistentDataContainer();
        if (pdc.has(pvptoggle, PersistentDataType.BOOLEAN)) {
            pdc.set(pvptoggle, PersistentDataType.BOOLEAN, !pdc.get(pvptoggle, PersistentDataType.BOOLEAN));
            sender.sendMessage("Dein PvP-Schutz ist jetzt " + (pdc.get(pvptoggle, PersistentDataType.BOOLEAN)?"an":"aus"));
        } else {
            pdc.set(pvptoggle, PersistentDataType.BOOLEAN, true);
            sender.sendMessage("Dein PvP-Schutz ist jetzt " + (pdc.get(pvptoggle, "pvptoggle"), PersistentDataType.BOOLEAN)?"an":"aus"));
        }
    }

    public static void pvpUltra(Player sender, CommandArguments args) {
        //PvP Ultra Command
        sender.sendMessage("Command noch nicht fertig, er schützt noch nicht vor PvP");
        NamespacedKey ultra = new NamespacedKey(Pvptoggle.pvptoggle, "ultra");
        PersistentDataContainer pdc = sender.getPersistentDataContainer();
        if (pdc.has(ultra, PersistentDataContainer.BOOLEAN)) {
            pdc.set(ultra, PersistentDataContainer.BOOLEAN, !pdc.get(ultra, PersistentDataContainer.BOOLEAN));
            sender.sendMessage("PvP-Ultra ist für dich jetzt " + (pdc.get(pvptoggle, PersistentDataType.BOOLEAN)?"an":"aus"));
        } else {
            pdc.set(ultra, PersistentDataContainer.BOOLEAN, true);
            sender.sendMessage("PvP-Ultra ist für dich jetzt " + (pdc.get(pvptoggle, PersistentDataType.BOOLEAN)?"an":"aus"));
        }
    }

    public static void pvpTrust(Player sender, CommandArguments args, Int action) {
        //PvP Trust Command
        if (action == 1) {
            //anzeigen
            sender.sendMessage("Du kannst dir die Trust-Liste nicht anzeigen");
        } else if (action == 2) {
            //hinzufügen
            sender.sendMessage("Du kannst " + args[0] + " nicht zur der Trust-Liste hinzufügen");
        } else if (action == 3) {
            //entfernen
            sender.sendMessage("Du kannst " + args[0] + " nicht aus der Trust-Liste entfernen");
        } else {
            sender.sendMessage("Fehler");
        }
    }

    public static void pvpMisstrust(Player sender, CommandArguments args, Int action) {
        //PvP Misstrust Command
        if (action == 1) {
            //anzeigen
            sender.sendMessage("Du kannst dir die Misstrust-Liste nicht anzeigen");
        } else if (action == 2) {
            //hinzufügen
            sender.sendMessage("Du kannst " + args[0] + " nicht zur der Misstrust-Liste hinzufügen");
        } else if (action == 3) {
            //entfernen
            sender.sendMessage("Du kannst " + args[0] + " nicht aus der Misstrust-Liste entfernen");
        } else {
            sender.sendMessage("Fehler");
        }
    }
}
