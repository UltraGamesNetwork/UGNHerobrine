package main;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements CommandExecutor {

	private static Main _instance;

	@Override
	public void onEnable() {
		ConfigManager.GetManager();
		SetupCommands();
	}
	
	private void SetupCommands() {
		getCommand("warps").setExecutor(WarpsManager.GetManager());
		getCommand("warp").setExecutor(WarpsManager.GetManager());
	}

	@Override
	public void onDisable() {
		
	}
	
	protected static Main GetInstance() {
		return _instance;
	}
}