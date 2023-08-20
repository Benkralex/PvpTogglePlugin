package benkralex.pvptoggle;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.time.Instant;

public class Config {
	
	privat FileConfiguration config = Pvptoggle.pvptoggle.getConfig();
	
    public static void createConfig() {
        config.options().copyDefaults();
        config.addDefault("pvp-time-hit-back", 60);
		config.addDefault("default-pvp-protection", true)
        Pvptoggle.pvptoggle.saveConfig();
    }

    public static int getPvpTime() {
        return config.getInt("pvp-time-hit-back");
    }
	
	public static int getPvpProt() {
		return config.getBoolean("default-pvp-protection");
	}
}
