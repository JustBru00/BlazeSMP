package com.gmail.justbru00.blazesmp.war;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.gmail.justbru00.blazesmp.abilities.AbilitiesManager;
import com.gmail.justbru00.blazesmp.enums.WarState;
import com.gmail.justbru00.blazesmp.utils.Messager;
import com.gmail.justbru00.blazesmp.utils.chestlock.ChestLocks;
import com.gmail.justbru00.blazesmp.utils.cores.CoreManager;

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
	
	public static int timeLeft = -1;
	
	@SuppressWarnings("deprecation")
	public static void everySecond() {
		if (timeLeft < -5) {
			return;
		}
		
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
				
			}
		} 		
			
		// TODO Display time left with scoreboard
		Messager.sendBC("Time left: " + timeLeft + " state = " + CURRENT_WAR_STATE);
		timeLeft = timeLeft -1;
	}
	
	private static void setWarState(WarState ws) {
		CURRENT_WAR_STATE = ws;
	}
	
}
