package com.gmail.justbru00.blazesmp.utils.cores;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockState;

import com.gmail.justbru00.blazesmp.enums.Team;

/**
 * Handles Team Core Stuff
 * @author Justin Brubaker
 *
 */
public class CoreManager {
	
	public static ArrayList<Core> cores = new ArrayList<Core>();
	
	/**
	 * If the core breaking is enabled or not.
	 */
	private static boolean coresEnabled = false;

	/**
	 * Sets if the cores can be broken.
	 * @param x True to disable cores. False to enable cores.
	 */
	public static void setCoresEnabled(boolean x) {
		coresEnabled = x;
		
		if (x == false) {
			for (Core c : cores) {
				c.reset();
			}
		}
		
	}
	
	public static void updateBossBars(int percent, Team coresTeam) {
		
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
	
	@SuppressWarnings("deprecation")
	public static void setBlock(Material m, int data, int x, int y, int z, World world) {
		BlockState a = world.getBlockAt(x, y, z).getState();
		a.setType(m);
		a.setRawData((byte) data);
		a.update(true);
	}
	
}
