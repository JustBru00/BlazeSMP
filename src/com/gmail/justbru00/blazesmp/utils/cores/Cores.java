package com.gmail.justbru00.blazesmp.utils.cores;
/**
 * Handles Team Core Stuff
 * @author Justin Brubaker
 *
 */
public class Cores {
	/**
	 * If the core is disabled or not.
	 */
	private static boolean CORE_ENABLED = false;

	/**
	 * Sets if the cores can be broken.
	 * @param x True to disable cores. False to enable cores.
	 */
	public static void setEnabled(boolean x) {
		CORE_ENABLED = x;
		// TODO If disabling cores -> Reset them.
	}
	
	public static boolean istEnabled() {
		return CORE_ENABLED;
	}
	
}
