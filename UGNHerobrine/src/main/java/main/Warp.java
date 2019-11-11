package main;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Warp {
	
	private String _name;
	private Location _location;
	
	public Warp(String name, Location loc) {
		_name = name;
		_location = loc;
	}
	
	public boolean WarpFireteam(Player player) {
		//TODO Warp all members of the player's fireteam to the location!
		return false;
	}

	public String getName() {
		return _name;
	}

	public boolean IsAllowedWarpFor(Player player) {
		// TODO Check if player is allowed to warp to this location.
		return false;
	}
}
