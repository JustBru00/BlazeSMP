package com.gmail.justbru00.blazesmp.utils.team;

import java.util.ArrayList;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.gmail.justbru00.blazesmp.enums.Team;
import com.gmail.justbru00.blazesmp.main.Main;
import com.gmail.justbru00.blazesmp.scoreboard.EpicScoreboardManager;
import com.gmail.justbru00.blazesmp.utils.Debug;
import com.gmail.justbru00.blazesmp.utils.Messager;

public class TeamManager {	
	
	private static ArrayList<TeamChangeRequest> teamChangeRequests = new ArrayList<TeamChangeRequest>();
	private static int currentRequestsID = Main.getInstance().teamRequestsFile.getInt("current_id");
	
	
	public static void setFriendlyFireEnabled(boolean b) {
		EpicScoreboardManager.ICE.setAllowFriendlyFire(b);
		EpicScoreboardManager.NETHER.setAllowFriendlyFire(b);		
	}
	
	public static void refreshRequestsFromConfig() {
		
		teamChangeRequests = new ArrayList<TeamChangeRequest>();
		
		int i = 1;
		
		while (true) {
			if (Main.getInstance().teamRequestsFile.get("requests." + i) == null) {
				break;
			}
			
			TeamChangeRequest trc = new TeamChangeRequest(i);
			teamChangeRequests.add(trc);	
			i++;
		}				
		Debug.send("Done Refreshing the requests.");
	}
	
	public static int getNextRequestID() {
		Main.getInstance().teamRequestsFile.set("current_id", (currentRequestsID + 1));
		Main.getInstance().teamRequestsFile.save();		
		
		currentRequestsID = currentRequestsID + 1;
		return currentRequestsID;
	}
	
	
	public static Team getTeamFromString(String team) {
		
		switch (team) {
			case "NONE": {
				return Team.NONE;
			}
			case "NETHER": {
				return Team.NETHER;
			}
			case "ICE": {
				return Team.ICE;
			}
		}
		return null;
		
	}

	public static Team getTeam(OfflinePlayer player) {		
		String team = "";
		
		try {
			team = Main.getInstance().getConfig().getString("players.data." + player.getUniqueId().toString() + ".team");	
		} catch (NullPointerException e) {
			Debug.send("&c&lString team = NULL");
		}
		
		if (team == null) {
			Debug.send("&cString team = NULL");
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
	public static void setTeam(OfflinePlayer player, Team team) { 
		if (getTeam(player) == team) {
			Debug.send("TeamHandler.setTeam() had an error: Player is already on that team.");
			return;
		}	
		
		if (getTeam(player) == Team.ICE) {
			Main.getInstance().getConfig().set("teams.ice.total", Main.getInstance().getConfig().getInt("teams.ice.total") - 1);
			Main.getInstance().saveConfig();
		} else if (getTeam(player) == Team.NETHER) {
			Main.getInstance().getConfig().set("teams.nether.total", Main.getInstance().getConfig().getInt("teams.nether.total") - 1);
			Main.getInstance().saveConfig();
		} 
		
		if (team == Team.ICE) {
			Main.getInstance().getConfig().set("players.data." + player.getUniqueId().toString() + ".team", "ICE");
			Main.getInstance().saveConfig();	
			
			Main.getInstance().getConfig().set("teams.ice.total", Main.getInstance().getConfig().getInt("teams.ice.total") + 1);
			Main.getInstance().saveConfig();
			
			EpicScoreboardManager.ICE.addPlayer(player);
			
			Messager.sendBC("&b" + player.getName() + " joined ICE team.");
		} else if (team == Team.NETHER) {
			
			if (Main.getInstance() == null) {
				Debug.send("Main.getInstance() is null");
			}
			
			Main.getInstance().getConfig().set("players.data." + player.getUniqueId().toString() + ".team", "NETHER");
			Main.getInstance().saveConfig();
			
			Main.getInstance().getConfig().set("teams.nether.total", Main.getInstance().getConfig().getInt("teams.nether.total") + 1);
			Main.getInstance().saveConfig();

			EpicScoreboardManager.NETHER.addPlayer(player);
			
			Messager.sendBC("&c" + player.getName() + " joined NETHER team.");
		} else if (team == Team.NONE) {
			Main.getInstance().getConfig().set("players.data." + player.getUniqueId().toString() + ".team", "NONE");
			Main.getInstance().saveConfig();

			EpicScoreboardManager.NONE.addPlayer(player);
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

	/**
	 * Gets the teamChangeRequests. Runs {@link TeamManager#refreshRequestsFromConfig()} automagiclly
	 * @return the teamChangeRequests The array with TeamChangeRequests
	 */
	public static ArrayList<TeamChangeRequest> getTeamChangeRequests() {
		TeamManager.refreshRequestsFromConfig();
		return teamChangeRequests;
	}

	/**
	 * @param teamChangeRequests the teamChangeRequests to set
	 */
	public static void setTeamChangeRequests(ArrayList<TeamChangeRequest> teamChangeRequests) {
		TeamManager.teamChangeRequests = teamChangeRequests;
	}
}

