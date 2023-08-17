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
        new CommandAPICommand("pvp").
                withSubcommand(new CommandAPICommand("toggle").
                        executesPlayer((sender, args)->{
                            pvpToggle(sender, args);
                        }).
                        withPermission("pvp.toggle").
                            withUsage("/pvp toggle").
                        withHelp("PvP damage für sich an/auschalten.", "Du kannst damit an/ausschalten, ob du geschlagen werden kannst.")).
                register();
    }

    public static void pvpToggle(Player sender, CommandArguments args) {
        PersistentDataContainer pdc = sender.getPersistentDataContainer();
        if (pdc.has(new NamespacedKey(Pvptoggle.pvptoggle, "pvptoggle"), PersistentDataType.BOOLEAN)) {
            pdc.set(new NamespacedKey(Pvptoggle.pvptoggle, "pvptoggle"), PersistentDataType.BOOLEAN,
                    !pdc.get(new NamespacedKey(Pvptoggle.pvptoggle, "pvptoggle"), PersistentDataType.BOOLEAN));
            sender.sendMessage("Dein PvP-Schutz ist jetzt " + (pdc.get(new NamespacedKey(Pvptoggle.pvptoggle, "pvptoggle"), PersistentDataType.BOOLEAN)?"an":"aus"));
        } else {
            pdc.set(new NamespacedKey(Pvptoggle.pvptoggle, "pvptoggle"), PersistentDataType.BOOLEAN, true);
            sender.sendMessage("Dein PvP-Schutz ist jetzt " + (pdc.get(new NamespacedKey(Pvptoggle.pvptoggle, "pvptoggle"), PersistentDataType.BOOLEAN)?"an":"aus"));
        }
    }

    public static void pvpUltra(Player sender, CommandArguments args) {
        //PvP Ultra Command
    }

    public static void pvpTrust(Player sender, CommandArguments args) {
        //PvP Trust Command
    }

    public static void pvpMisstrust(Player sender, CommandArguments args) {
        //PvP Misstrust Command
    }
}
