package main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class Destination {

	private String _name;
	public List<Sector> sectors;
	
	public Destination(String name) {
		_name = name;
		sectors = new ArrayList<Sector>();
	}

	public String getName() {
		return _name;
	}

	public boolean IsAllowedDestinationFor(Player player) {
		// TODO Check if the player is allowed inside this destination.
		return false;
	}
}
