package main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class Sector {
	
	private String _name;
	public List<Warp> warps;
	
	public Sector(String name) {
		_name = name;
		warps = new ArrayList<Warp>();
	}

	public String getName() {
		return _name;
	}

	public boolean IsAllowedSectorFor(Player player) {
		// TODO Check if the player is allowed inside this sector.
		return false;
	}
}
