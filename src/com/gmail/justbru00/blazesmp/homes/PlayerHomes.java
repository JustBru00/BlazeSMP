package com.gmail.justbru00.blazesmp.homes;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class PlayerHomes {

	
	private String playerUUID;	
	private ArrayList<Home> playerHomes = new ArrayList<Home>();
	private ArrayList<String> homeNames = new ArrayList<String>();
	
	public PlayerHomes(Player player) {
		playerUUID = player.getUniqueId().toString();
		
	
	}
	
}
