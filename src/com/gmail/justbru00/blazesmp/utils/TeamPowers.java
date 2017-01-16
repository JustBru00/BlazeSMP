package com.gmail.justbru00.blazesmp.utils;

public class TeamPowers {
	
	private static boolean EFFECTS_ENABLED = true;

	public static void setEnabled(boolean x) {
		EFFECTS_ENABLED = x;
		//TODO Accually enable.
	}
	
	public static boolean isEnabled() {
		return EFFECTS_ENABLED;
	}
	
}
