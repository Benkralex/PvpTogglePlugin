package benkralex.pvptoggle;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Pvptoggle extends JavaPlugin {

    public static Pvptoggle pvptoggle;

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new PvpEvent(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
        Bukkit.getPluginManager().registerEvents(new ListListener(), this);
        Config.createConfig();
        if (Config.getPvpTime() < 0) {
            Config.setPvpTime(0);
        }
    }

    @Override
    public void onLoad(){
        pvptoggle = this;
        PvpCommand.createPvpCommand();
    }
}