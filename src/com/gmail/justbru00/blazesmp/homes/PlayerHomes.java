package com.gmail.justbru00.blazesmp.homes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.gmail.justbru00.blazesmp.main.Main;

public class PlayerHomes {

	
	private String playerUUID;	
	private ArrayList<Home> playerHomes = new ArrayList<Home>();
	private List<String> homeNames = new ArrayList<String>();
	
	public PlayerHomes(Player player) {
		playerUUID = player.getUniqueId().toString();
		
		if (Main.getInstance().homesStorage.getStringList("data." + playerUUID + ".home_names") == null) {
			Main.getInstance().homesStorage.set("data." + playerUUID + ".home_names", Arrays.asList("null"));
		}
		
		
		homeNames = Main.getInstance().homesStorage.getStringList("data." + playerUUID + ".home_names");
		
		for (String s : homeNames) {
			if (!s.equals("null")) {
				playerHomes.add(new Home(playerUUID, s));
			}			
		}	
	}
	/**
	 * Call {@link #checkName()} first.
	 * @param loc
	 * @param name
	 */
	public void addHome(Location loc, String name, Material m) {
		playerHomes.add(new Home(playerUUID, name, loc, m));
		homeNames.add(name);
		Main.getInstance().homesStorage.set("data." + playerUUID + ".home_names", homeNames);
		Main.getInstance().homesStorage.save();		
	}
	
	/**
	 * Checks if a name is safe to use.
	 * @param name The name to check
	 * @return True if the name is okay. False if the name is used.
	 */
	public boolean checkName(String name) {
		if (name.equals("null")) {
			return false;
		}
		if (homeNames.contains(name)) {
			return false;
		}
		return true;
	}

	
}
