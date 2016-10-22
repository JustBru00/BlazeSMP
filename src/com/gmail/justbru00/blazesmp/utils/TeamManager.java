package com.gmail.justbru00.blazesmp.utils;

import org.bukkit.entity.Player;

import com.gmail.justbru00.blazesmp.enums.Team;
import com.gmail.justbru00.blazesmp.main.Main;

public class TeamManager {

	public static Team getTeam(Player player) {		
		String team = Main.getInstance().getConfig().getString("players.data." + player.getUniqueId() + ".team");
		
		if (team == null) {
			return null;
		}
		
		if (team.equalsIgnoreCase("ICE")) {
			return Team.ICE;
		} else if (team.equalsIgnoreCase("NETHER")) {
			return Team.NETHER;
		} else {
			return Team.NONE;
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void setTeam(Player player, Team team) { 
		if (getTeam(player) == team) {
			Debug.send("TeamHandler.setTeam() had an error: Player is already on that team.");
			return;
		}
		
		if (team == Team.ICE) {
			Main.getInstance().getConfig().set("players.data." + player.getUniqueId() + ".team", "ICE");
			Main.getInstance().saveConfig();	
			
			Main.getInstance().getConfig().set("teams.ice.total", Main.getInstance().getConfig().getInt("teams.ice.total") + 1);
			Main.getInstance().saveConfig();
			
			Main.ICE.addPlayer(player);
			
			Messager.sendBC("&b" + player.getName() + " joined ICE team.");
		} else if (team == Team.NETHER) {
			Main.getInstance().getConfig().set("players.data." + player.getUniqueId() + ".team", "NETHER");
			Main.getInstance().saveConfig();
			
			Main.getInstance().getConfig().set("teams.nether.total", Main.getInstance().getConfig().getInt("teams.nether.total") + 1);
			Main.getInstance().saveConfig();

			Main.NETHER.addPlayer(player);
			
			Messager.sendBC("&c" + player.getName() + " joined NETHER team.");
		} else if (team == Team.NONE) {
			Main.getInstance().getConfig().set("players.data." + player.getUniqueId() + ".team", "NONE");
			Main.getInstance().saveConfig();

			Main.NONE.addPlayer(player);
		}	
	}
	
	/**
	 * 
	 * @param team The Team that you need the name for.
	 * @return The colored name from the config. Returns null fails.
	 */
	public static String getColoredTeamName(Team team) {
		if (team == Team.ICE) {
			return Main.getInstance().getConfig().getString("teams.ice.name");
		} else if (team == Team.NETHER) {
			return Main.getInstance().getConfig().getString("teams.nether.name");
		} else if (team == Team.NONE) {
			return Main.getInstance().getConfig().getString("teams.none.name");
		}
		return null;
	}
	
	public static void findAndJoinTeam(Player player) {
		int netherTotal = Main.getInstance().getConfig().getInt("teams.nether.total");
		int iceTotal = Main.getInstance().getConfig().getInt("teams.ice.total");
		//int netherActive = Main.getInstance().getConfig().getInt("teams.nether.active");
		//int iceActive = Main.getInstance().getConfig().getInt("teams.ice.active");
		
		// TODO Add joining per active
		
		if (netherTotal > iceTotal) {
			TeamManager.setTeam(player, Team.ICE); 				
			return;
		} else if (iceTotal > netherTotal) {
			TeamManager.setTeam(player, Team.NETHER);				
			return;
		} else if (iceTotal == netherTotal) {
			TeamManager.setTeam(player, Team.NETHER);		
			return;
		}
	}
}

