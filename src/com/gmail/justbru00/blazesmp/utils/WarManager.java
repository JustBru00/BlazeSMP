package com.gmail.justbru00.blazesmp.utils;

import com.gmail.justbru00.blazesmp.enums.WarState;

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
	 * Run the proper code to set the {@link WarState}
	 * @param ws The {@link WarState} you would like to set to.
	 */
	public static void gotoWarState(WarState ws) {
		
		if (ws == WarState.PEACE) {
			Debug.send("Trying to start WarState.PEACE");
			setWarState(ws);
			//TODO Make this work
			
		} else if (ws == WarState.WAR_START) {
			
		} else if (ws == WarState.WAR_DURING) {
			
		} else if (ws == WarState.WAR_END) {
			
		} else if (ws == WarState.WAR_FORCESTOP) {
			
		}
		
	}
	
	private static void setWarState(WarState ws) {
		CURRENT_WAR_STATE = ws;
	}
	
}
