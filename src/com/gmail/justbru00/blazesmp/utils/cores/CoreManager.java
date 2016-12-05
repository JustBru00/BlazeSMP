package com.gmail.justbru00.blazesmp.utils.cores;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockState;

/**
 * Handles Team Core Stuff
 * @author Justin Brubaker
 *
 */
public class CoreManager {
	/**
	 * If the core is disabled or not.
	 */
	private static boolean coresEnabled = false;

	/**
	 * Sets if the cores can be broken.
	 * @param x True to disable cores. False to enable cores.
	 */
	public static void setCoresEnabled(boolean x) {
		coresEnabled = x;
		// TODO If disabling cores -> Reset them.
	}
	
	public static boolean areCoresEnabled() {
		return coresEnabled;
	}
	 
	/**
	 * Sets the block at the specified location
	 * @param m the {@link Material} to set the block to
	 * @param x The x coord
	 * @param y The y coord
	 * @param z The z coord
	 * @param world The world
	 */
	public static void setBlock(Material m, int x, int y, int z, World world) {
		BlockState a = world.getBlockAt(x, y, z).getState();
		a.setType(m);
		a.update(true);
	}
	
}
