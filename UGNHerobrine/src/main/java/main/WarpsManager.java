package main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpsManager implements CommandExecutor {

	private static WarpsManager instance;
	
	public static WarpsManager GetManager() {
		return instance == null? instance = new WarpsManager() : instance;
	}

	private Map<Destination, List<Sector>> destinations;
	
	private WarpsManager() {
		destinations = new HashMap<Destination, List<Sector>>();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player))return false;
		Player executor = (Player)sender;
		
		if (label.equalsIgnoreCase("warps")) {
			//Show all available locations to the executor.
			
		}else if (label.equalsIgnoreCase("warp")) {
			Warp w = null;
			if (args.length == 0 || (w = FindWarpByName(args[0], executor)) == null) {
				if (!w.WarpFireteam(executor)) {// <-- Tries to warp the executor to the requested warp location.
					//TODO check why the warp failed. The executor may not be the leader of the fireteam!
				}
			}
		}
		
		return false;
	}

	/**
	 * Finds the warp with the specified name. Leave the player parameter null if the requested warp does't have to be an available warp to the player.
	 * @param name The name of the warp.
	 * @param player The player to check for availability. (May be null to disable check for availability)
	 * @return The requested warp. Or null if not found or not unavailable to the specified player.
	 */
	private Warp FindWarpByName(String name, Player player) {
		for (Entry<Destination, List<Sector>> entry : destinations.entrySet()) {
			if (player != null && !entry.getKey().IsAllowedDestinationFor(player))continue;
			for (Sector sector : entry.getValue()) {
				if (player != null && !sector.IsAllowedSectorFor(player))continue;
				for (Warp warp : sector.warps) {
					if (player != null && !warp.IsAllowedWarpFor(player))continue;
					String warpName = warp.getName();
					if ((name.charAt(0) == warpName.toLowerCase().charAt(0) || name.charAt(0) == warpName.toUpperCase().charAt(0)) && name.equalsIgnoreCase(warp.getName())) {
						return warp;
					}
				}
			}
		}
		return null;
	}
}
