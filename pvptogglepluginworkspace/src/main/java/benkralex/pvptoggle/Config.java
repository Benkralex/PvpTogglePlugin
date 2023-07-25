package benkralex.pvptoggle;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.time.Instant;

public class Config {
    public static void createConfig() {
        FileConfiguration config = Pvptoggle.pvptoggle.getConfig();
        config.options().copyDefaults();
        config.addDefault("pvp-time-hit-back", 60);
        Pvptoggle.pvptoggle.saveConfig();
    }

    public static int getpvptime() {
        FileConfiguration config = Pvptoggle.pvptoggle.getConfig();
        return config.getInt("pvp-time-hit-back");
    }
}
