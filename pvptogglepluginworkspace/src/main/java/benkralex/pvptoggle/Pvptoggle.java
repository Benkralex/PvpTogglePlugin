package benkralex.pvptoggle;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Pvptoggle extends JavaPlugin {

    public static Pvptoggle pvptoggle;

    @Override
    public void onEnable() {
        // Plugin enable logic
        pvptoggle = this;
        Bukkit.getPluginManager().registerEvents(new PvpEvent(), this);
        PvpCommand.createPvpCommand();
        Config.createConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}