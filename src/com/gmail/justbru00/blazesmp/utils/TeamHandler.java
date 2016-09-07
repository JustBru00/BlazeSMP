package com.gmail.justbru00.blazesmp.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.gmail.justbru00.blazesmp.enums.Team;
import com.gmail.justbru00.blazesmp.main.Main;

public class TeamHandler {

	public static Team getTeam(Player player) {
		String team = Main.getInstance().getConfig().getString("players.data." + player.getUniqueId() + ".team");
		if (team.equalsIgnoreCase("ICE")) {
			return Team.ICE;
		} else if (team.equalsIgnoreCase("NETHER")) {
			return Team.NETHER;
		} else {
			return Team.NONE;
		}
	}
	
	public static void setTeam(Player player, Team team) {
		if (getTeam(player) == team) {
			Debug.send("TeamHandler.setTeam() had an error: Player is already on that team.");
			return;
		}
		
		if (team == Team.ICE) {
			Main.getInstance().getConfig().set("players.data." + player.getUniqueId() + ".team", "ICE");
			Main.getInstance().saveConfig();			
			
			Bukkit.dispatchCommand(Main.clogger, "scoreboard teams join ICE " + player.getName());
		} else if (team == Team.NETHER) {
			Main.getInstance().getConfig().set("players.data." + player.getUniqueId() + ".team", "NETHER");
			Main.getInstance().saveConfig();

			Bukkit.dispatchCommand(Main.clogger, "scoreboard teams join NETHER " + player.getName());
		} else if (team == Team.NONE) {
			Main.getInstance().getConfig().set("players.data." + player.getUniqueId() + ".team", "NONE");
			Main.getInstance().saveConfig();

			Bukkit.dispatchCommand(Main.clogger, "scoreboard teams join NONE " + player.getName());
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
	
}

