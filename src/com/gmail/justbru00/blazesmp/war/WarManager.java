package com.gmail.justbru00.blazesmp.war;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.gmail.justbru00.blazesmp.abilities.AbilitiesManager;
import com.gmail.justbru00.blazesmp.enums.Team;
import com.gmail.justbru00.blazesmp.enums.WarState;
import com.gmail.justbru00.blazesmp.scoreboard.EpicScoreboardManager;
import com.gmail.justbru00.blazesmp.utils.Messager;
import com.gmail.justbru00.blazesmp.utils.chestlock.ChestLocks;
import com.gmail.justbru00.blazesmp.utils.cores.CoreManager;
import com.gmail.justbru00.blazesmp.utils.team.TeamManager;

public class WarManager {
	/**
	 * Default is {@link WarState.PEACE}
	 */
	public static WarState CURRENT_WAR_STATE = WarState.PEACE; 
	/**
	 * Default is 30 min
	 */
	public static int SECONDS_UNTIL_WAR_END = 1800; 
	/**
	 * Default is 2
	 */
	public static int MIN_REQUIRED_PLAYERS_ONLINE = 2; 
	/**
	 * Default is 1
	 */
	public static int MIN_REQUIRED_PLAYERS_PER_TEAM = 1;
	/**
	 * Default is 600
	 */
	public static int CANCELATION_COOLDOWN = 600;
	/**
	 * Default is 10 min
	 */
	public static int AFTER_WAR_COOLDOWN = 600;
	
	public static boolean hasATeamWon = false;
	
	public static Team winner = Team.NONE;
	
	public static int timeLeft = -1;
	
	public static ArrayList<String> icePeace = new ArrayList<String>(); 
	
	public static ArrayList<String> netherPeace = new ArrayList<String>(); 
	
	@SuppressWarnings("deprecation")
	public static void everySecond() {
		EpicScoreboardManager.updateScoreboard();
				
		if (timeLeft == -1) { // Change state
			if (CURRENT_WAR_STATE == WarState.PEACE) {
				return;
			} else if (CURRENT_WAR_STATE == WarState.START) {
				CoreManager.setCoresEnabled(true);
				AbilitiesManager.setAbilitiesEnabled(true);
				ChestLocks.setAllLocksEnabled(true);
				timeLeft = SECONDS_UNTIL_WAR_END;
				CURRENT_WAR_STATE = WarState.DURING;
				for (Player p : Bukkit.getOnlinePlayers()) {
					p.sendTitle(Messager.color("&cWAR HAS STARTED"), Messager.color("&7Use /peace to propose a truce"));
				}
			} else if (CURRENT_WAR_STATE == WarState.DURING) {
				// War ended without a winner	
				CoreManager.setCoresEnabled(false);
				CURRENT_WAR_STATE = WarState.END;
				timeLeft = 4;				
				
				if (hasATeamWon == false) {				
					for (Player p : Bukkit.getOnlinePlayers()) {
						p.sendTitle(Messager.color("&cWAR HAS ENDED"), Messager.color("&aThere was no winner"));
					}
				} else {
					for (Player p : Bukkit.getOnlinePlayers()) {
						p.sendTitle(Messager.color("&cLooting Time Ended"), Messager.color("&aYou can resume normal survival"));
					}
				}
			} else if (CURRENT_WAR_STATE == WarState.END) {
				CoreManager.setCoresEnabled(false);
				winner = Team.NONE;
				hasATeamWon = false;
				icePeace = new ArrayList<String>();
				netherPeace = new ArrayList<String>();
				CURRENT_WAR_STATE = WarState.COOLDOWN;
				timeLeft = AFTER_WAR_COOLDOWN;
				for (Player p : Bukkit.getOnlinePlayers()) {
					p.sendTitle(Messager.color("&cNo war can start"), Messager.color("&7until the cooldown is over"));
				}
			} else if (CURRENT_WAR_STATE == WarState.COOLDOWN) {
				CURRENT_WAR_STATE = WarState.PEACE;
				timeLeft = 0;
				for (Player p : Bukkit.getOnlinePlayers()) {
					p.sendTitle(Messager.color("&aWar can be started"), Messager.color("&7The cooldown is over"));
				}
			}
		} 					
		
		
		//Messager.sendBC("Time left: " + timeLeft + " state = " + CURRENT_WAR_STATE);
		timeLeft = timeLeft -1;
	}
	
		
	public static void proposePeace(Team from, Player p) {
		
		if (from == Team.ICE) {
			if (icePeace.contains(p.getUniqueId().toString())) { // If player already asked for peace0
				Messager.msgPlayer("&cYou already proposed peace.", p);
				return;
			} else {
				icePeace.add(p.getUniqueId().toString());				
			}
		} else if (from == Team.NETHER) {
			if (netherPeace.contains(p.getUniqueId().toString())) { // If player already asked for peace
				Messager.msgPlayer("&cYou already proposed peace.", p);
				return;
			} else {
				netherPeace.add(p.getUniqueId().toString());				
			}
		}
		
		Messager.sendBC("&a" + p.getName() + " proposed peace. Everyone online on both teams must agree for peace.");
		tryPeace();
	}
	
	public static void tryPeace() {
		int netherOnline = 0;
		int iceOnline = 0;
		
		iceOnline = TeamManager.getOnline().getIce();
		netherOnline = TeamManager.getOnline().getNether();
		
		if ((netherOnline) <= netherPeace.size()) {
			if ((iceOnline) <= icePeace.size()) {
				CURRENT_WAR_STATE = WarState.END;
				timeLeft = 0;
				Messager.sendBC("&aPeace has been established.");
			}
		}
	}
	
	@SuppressWarnings("unused")
	private static void setWarState(WarState ws) {
		CURRENT_WAR_STATE = ws;
	}
	
	public static String getCurrentObjective(Team forTeam) {
		if (CURRENT_WAR_STATE == WarState.DURING) {
			if (hasATeamWon) {
				if (winner == Team.NETHER) {
					if (forTeam == Team.NETHER) {
						return "&cLoot Enemies Chests";
					} else {
						return "&cDefend Chests";
					}
				} else if (winner == Team.ICE) {
					if (forTeam == Team.ICE) {
						return "&cLoot Enemies Chests";
					} else {
						return "&cDefend Chests";
					}
				}
			} else {
				return "&cWAR";
			}			
		} else if (CURRENT_WAR_STATE == WarState.COOLDOWN) {
			return "&cWar Cooldown";
		} else {
			return "&aNormal Survival";
		}
		return null;		
	}
	
	public static String getTimeLeftFormated() {
		if (timeLeft == -1) {
			return "Forever";
		}	
	
		int hours;
		int mins;
		int seconds;
		hours = (timeLeft % 86400 ) / 3600 ;
		mins = ((timeLeft % 86400 ) % 3600 ) / 60; 
		seconds = ((timeLeft % 86400 ) % 3600 ) % 60;
		
		
		return hours + ":" + mins + ":" + seconds;
	}
	
}
