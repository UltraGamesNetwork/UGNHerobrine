package main;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

	private static ConfigManager instance;
	public File worldsFolder;
	public File configFile;
	
	public static ConfigManager GetManager() {
		return instance == null? instance = new ConfigManager() : instance;
	}

	private ConfigManager() {
		if (!Main.GetInstance().getDataFolder().exists()) {
			Main.GetInstance().getDataFolder().mkdirs();
		}
		configFile = new File(Main.GetInstance().getDataFolder(), "config.yml");
		if (!configFile.exists()) {
			try {
				configFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		worldsFolder = new File(Main.GetInstance().getDataFolder(), "Worlds");
		if (!worldsFolder.exists()) {
			worldsFolder.mkdirs();
		}
	}
}
