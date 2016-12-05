package com.gmail.justbru00.blazesmp.utils.cores;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockState;

import com.gmail.justbru00.blazesmp.enums.Team;
import com.gmail.justbru00.blazesmp.utils.Debug;

public class Core {

	private Team owner;
	private Location coreLoc;
	private World world;
	private short timesLeftToBreak = -1;
	private byte percentageLeft = 100;
	private boolean coreBreakable = false;		
			
	public Core(Team _owner, int locX, int locY, int locZ, World coreIsIn) {
		owner = _owner;
		coreLoc = new Location(coreIsIn, locX, locY, locZ);
		world = coreIsIn;
	}

	/**
	 * Sets the core as breakable or not. (Used during war)
	 * 
	 * @param x
	 */
	public void setBreakable(boolean x) {
		if (x) {
			// Enable breaking
		} else {
			// disable breaking
			
		}
	}
	
	public void placeDefaultCoreStructure() {
		int x = coreLoc.getBlockX();
		int y = coreLoc.getBlockY();
		int z = coreLoc.getBlockZ();		
		
		
		Debug.send("X = " + x);
		Debug.send("Y = " + y);
		Debug.send("Z = " + z);
		Debug.send("World Name = " + world.getName());	
		
		CoreManager.setBlock(Material.AIR, x-1, y, z, world);	
		CoreManager.setBlock(Material.AIR, x+1, y, z, world);
		CoreManager.setBlock(Material.AIR, x, y, z+1, world);
		CoreManager.setBlock(Material.AIR, x, y, z-1, world);		
		
		CoreManager.setBlock(Material.SEA_LANTERN, x, y, z, world);
		
		
		
		Debug.send("Should have done the stuff.");
	
	}
	
	/**
	 * Resets the core back to it's normal state.
	 */
	public void reset() {
		// TODO Place blocks and reset values
	}

	/**
	 * Updates the percentage display and the glass color
	 */
	public void updateCoreStatus() {
		// TODO Calc percentage and change glass color
	}

	public void coreBreakHandle() {
		if (coreIsBreakable()) {
			timesLeftToBreak = (short) (timesLeftToBreak - 1);
			updateCoreStatus();
			return;
		} else {
			Debug.send("Core is unbreakable.");
			return;
		}
	}

	public boolean coreIsBreakable() {
		return coreBreakable;
	}

}
