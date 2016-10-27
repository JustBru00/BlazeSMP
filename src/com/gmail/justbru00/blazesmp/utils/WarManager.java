package com.gmail.justbru00.blazesmp.utils;

import org.bukkit.Bukkit;

import com.gmail.justbru00.blazesmp.enums.War;
import com.gmail.justbru00.blazesmp.listeners.PvpListener;

public class WarManager {
	/**
	 * Default is {@link War.PEACE}
	 */
	public static War CURRENT_WAR_STATE = War.PEACE; 
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
	
	/**
	 * Run the proper code to set the {@link War}
	 * @param ws The {@link War} you would like to set to.
	 */
	public static void gotoWarState(War ws) {
		
		if (ws == War.PEACE) {
			Debug.send("Trying to start WarState.PEACE");
			setWarState(ws);
			//TODO Make this work
			
		} else if (ws == War.START) {
			
		} else if (ws == War.DURING) {
			
		} else if (ws == War.END) {
			Debug.send("Attemting to GOTO War.END");
			
			setWarState(ws);
			
			Cores.setEnabled(false);
			Effects.setEnabled(true);
			ChestLocks.setAllLocksEnabled(true);
			// Enabled dungons.
			
			gotoWarState(War.COOLDOWN);
			
		} else if (ws == War.FORCESTOP) {
			
		} else if (ws == War.FORCESTART) {
			
		} else if (ws == War.COOLDOWN) {
			PvpListener.setPvpEnabled(false);
			TimerHandler.setCurrentTimeLeft(AFTER_WAR_COOLDOWN);
		}
		
	}
	
	private static void setWarState(War ws) {
		CURRENT_WAR_STATE = ws;
	}
	
}
