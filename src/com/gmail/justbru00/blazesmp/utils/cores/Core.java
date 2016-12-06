package com.gmail.justbru00.blazesmp.utils.cores;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import com.gmail.justbru00.blazesmp.enums.CoreState;
import com.gmail.justbru00.blazesmp.enums.Team;
import com.gmail.justbru00.blazesmp.utils.Debug;

public class Core {

	private Team owner;
	private Location coreLoc;
	private World world;
	private short timesLeftToBreak = -1;
	private byte percentageLeft = 100;
	private boolean coreBreakable = false;		
	private CoreState currentState = CoreState.DEFAULT;
			
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
	/**
	 * Places the blocks to build the core for the current state.
	 * @param ct The {@link CoreState} design you would like to place blocks for.
	 */
	public void buildCoreStructure(CoreState ct) {
		int x = coreLoc.getBlockX();
		int y = coreLoc.getBlockY();
		int z = coreLoc.getBlockZ();		
		
		if (ct == CoreState.DEFAULT) {
				
		CoreManager.setBlock(Material.AIR, x-1, y, z, world);	
		CoreManager.setBlock(Material.AIR, x+1, y, z, world);
		CoreManager.setBlock(Material.AIR, x, y, z+1, world);
		CoreManager.setBlock(Material.AIR, x, y, z-1, world);		
		
		CoreManager.setBlock(Material.SEA_LANTERN, x, y, z, world);
		
		// White Glass
		CoreManager.setBlock(Material.STAINED_GLASS_PANE, 0, x-1, y, z+1, world); // Core Height
		CoreManager.setBlock(Material.STAINED_GLASS_PANE, 0, x-1, y, z-1, world); // Core Height
		CoreManager.setBlock(Material.STAINED_GLASS_PANE, 0, x+1, y, z+1, world); // Core Height
		CoreManager.setBlock(Material.STAINED_GLASS_PANE, 0, x+1, y, z-1, world); // Core Height
		
		CoreManager.setBlock(Material.STAINED_GLASS_PANE, 0, x+1, y+1, z-1, world); // Above Core Height
		CoreManager.setBlock(Material.STAINED_GLASS_PANE, 0, x+1, y+1, z, world); // Above Core Height
		CoreManager.setBlock(Material.STAINED_GLASS_PANE, 0, x+1, y+1, z+1, world); // Above Core Height
		CoreManager.setBlock(Material.STAINED_GLASS_PANE, 0, x-1, y+1, z-1, world); // Above Core Height
		CoreManager.setBlock(Material.STAINED_GLASS_PANE, 0, x-1, y+1, z, world); // Above Core Height
		CoreManager.setBlock(Material.STAINED_GLASS_PANE, 0, x-1, y+1, z+1, world); // Above Core Height
		
		CoreManager.setBlock(Material.STAINED_GLASS_PANE, 0, x+1, y-1, z-1, world); // Below Core Height
		CoreManager.setBlock(Material.STAINED_GLASS_PANE, 0, x+1, y-1, z, world); // Below Core Height
		CoreManager.setBlock(Material.STAINED_GLASS_PANE, 0, x+1, y-1, z+1, world); // Below Core Height
		CoreManager.setBlock(Material.STAINED_GLASS_PANE, 0, x-1, y-1, z-1, world); // Below Core Height
		CoreManager.setBlock(Material.STAINED_GLASS_PANE, 0, x-1, y-1, z, world); // Below Core Height
		CoreManager.setBlock(Material.STAINED_GLASS_PANE, 0, x-1, y-1, z+1, world); // Below Core Height
		
		CoreManager.setBlock(Material.STAINED_GLASS_PANE, 0, x, y-1, z+1, world); // Below Core Height
		CoreManager.setBlock(Material.STAINED_GLASS_PANE, 0, x, y-1, z-1, world); // Below Core Height

		CoreManager.setBlock(Material.STAINED_GLASS_PANE, 0, x, y+1, z+1, world); // Above Core Height
		CoreManager.setBlock(Material.STAINED_GLASS_PANE, 0, x, y+1, z-1, world); // Above Core Height
		// End White Glass
		
		// End Rods
		CoreManager.setBlock(Material.END_ROD, 0, x, y+1, z, world);
		CoreManager.setBlock(Material.END_ROD, 1, x, y-1, z, world);
		// End End Rods
		
		// Stone Bricks
		CoreManager.setBlock(Material.SMOOTH_BRICK, x, y+2, z, world);
		CoreManager.setBlock(Material.SMOOTH_BRICK, x, y-2, z, world);
		CoreManager.setBlock(Material.SMOOTH_BRICK, x+1, y+2, z, world);
		CoreManager.setBlock(Material.SMOOTH_BRICK, x+1, y-2, z, world);
		CoreManager.setBlock(Material.SMOOTH_BRICK, x-1, y+2, z, world);
		CoreManager.setBlock(Material.SMOOTH_BRICK, x-1, y-2, z, world);
		CoreManager.setBlock(Material.SMOOTH_BRICK, x, y+2, z+1, world);
		CoreManager.setBlock(Material.SMOOTH_BRICK, x, y-2, z+1, world);
		CoreManager.setBlock(Material.SMOOTH_BRICK, x, y+2, z-1, world);
		CoreManager.setBlock(Material.SMOOTH_BRICK, x, y-2, z-1, world);
		CoreManager.setBlock(Material.SMOOTH_BRICK, x+1, y+2, z-1, world);
		CoreManager.setBlock(Material.SMOOTH_BRICK, x+1, y-2, z-1, world);
		CoreManager.setBlock(Material.SMOOTH_BRICK, x+1, y+2, z+1, world);
		CoreManager.setBlock(Material.SMOOTH_BRICK, x+1, y-2, z+1, world);
		CoreManager.setBlock(Material.SMOOTH_BRICK, x-1, y+2, z+1, world);
		CoreManager.setBlock(Material.SMOOTH_BRICK, x-1, y-2, z+1, world);
		CoreManager.setBlock(Material.SMOOTH_BRICK, x-1, y+2, z-1, world);
		CoreManager.setBlock(Material.SMOOTH_BRICK, x-1, y-2, z-1, world);
		// End Stone Bricks
		} else if (ct == CoreState.GREEN) {
			CoreManager.setBlock(Material.AIR, x-1, y, z, world);	
			CoreManager.setBlock(Material.AIR, x+1, y, z, world);
			CoreManager.setBlock(Material.AIR, x, y, z+1, world);
			CoreManager.setBlock(Material.AIR, x, y, z-1, world);		
			
			CoreManager.setBlock(Material.SEA_LANTERN, x, y, z, world);
			
			// White Glass
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 5, x-1, y, z+1, world); // Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 5, x-1, y, z-1, world); // Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 5, x+1, y, z+1, world); // Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 5, x+1, y, z-1, world); // Core Height
			
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 5, x+1, y+1, z-1, world); // Above Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 5, x+1, y+1, z, world); // Above Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 5, x+1, y+1, z+1, world); // Above Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 5, x-1, y+1, z-1, world); // Above Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 5, x-1, y+1, z, world); // Above Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 5, x-1, y+1, z+1, world); // Above Core Height
			
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 5, x+1, y-1, z-1, world); // Below Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 5, x+1, y-1, z, world); // Below Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 5, x+1, y-1, z+1, world); // Below Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 5, x-1, y-1, z-1, world); // Below Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 5, x-1, y-1, z, world); // Below Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 5, x-1, y-1, z+1, world); // Below Core Height
			
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 5, x, y-1, z+1, world); // Below Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 5, x, y-1, z-1, world); // Below Core Height

			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 5, x, y+1, z+1, world); // Above Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 5, x, y+1, z-1, world); // Above Core Height
			// End White Glass
			
			// End Rods
			CoreManager.setBlock(Material.END_ROD, 0, x, y+1, z, world);
			CoreManager.setBlock(Material.END_ROD, 1, x, y-1, z, world);
			// End End Rods
			
			// Stone Bricks
			CoreManager.setBlock(Material.SMOOTH_BRICK, x, y+2, z, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x, y-2, z, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x+1, y+2, z, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x+1, y-2, z, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x-1, y+2, z, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x-1, y-2, z, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x, y+2, z+1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x, y-2, z+1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x, y+2, z-1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x, y-2, z-1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x+1, y+2, z-1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x+1, y-2, z-1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x+1, y+2, z+1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x+1, y-2, z+1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x-1, y+2, z+1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x-1, y-2, z+1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x-1, y+2, z-1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x-1, y-2, z-1, world);
			// End Stone Bricks
		} else if (ct == CoreState.YELLOW) {
			CoreManager.setBlock(Material.AIR, x-1, y, z, world);	
			CoreManager.setBlock(Material.AIR, x+1, y, z, world);
			CoreManager.setBlock(Material.AIR, x, y, z+1, world);
			CoreManager.setBlock(Material.AIR, x, y, z-1, world);		
			
			CoreManager.setBlock(Material.SEA_LANTERN, x, y, z, world);
			
			// White Glass
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 4, x-1, y, z+1, world); // Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 4, x-1, y, z-1, world); // Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 4, x+1, y, z+1, world); // Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 4, x+1, y, z-1, world); // Core Height
			
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 4, x+1, y+1, z-1, world); // Above Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 4, x+1, y+1, z, world); // Above Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 4, x+1, y+1, z+1, world); // Above Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 4, x-1, y+1, z-1, world); // Above Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 4, x-1, y+1, z, world); // Above Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 4, x-1, y+1, z+1, world); // Above Core Height
			
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 4, x+1, y-1, z-1, world); // Below Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 4, x+1, y-1, z, world); // Below Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 4, x+1, y-1, z+1, world); // Below Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 4, x-1, y-1, z-1, world); // Below Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 4, x-1, y-1, z, world); // Below Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 4, x-1, y-1, z+1, world); // Below Core Height
			
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 4, x, y-1, z+1, world); // Below Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 4, x, y-1, z-1, world); // Below Core Height

			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 4, x, y+1, z+1, world); // Above Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 4, x, y+1, z-1, world); // Above Core Height
			// End White Glass
			
			// End Rods
			CoreManager.setBlock(Material.END_ROD, 0, x, y+1, z, world);
			CoreManager.setBlock(Material.END_ROD, 1, x, y-1, z, world);
			// End End Rods
			
			// Stone Bricks
			CoreManager.setBlock(Material.SMOOTH_BRICK, x, y+2, z, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x, y-2, z, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x+1, y+2, z, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x+1, y-2, z, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x-1, y+2, z, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x-1, y-2, z, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x, y+2, z+1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x, y-2, z+1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x, y+2, z-1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x, y-2, z-1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x+1, y+2, z-1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x+1, y-2, z-1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x+1, y+2, z+1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x+1, y-2, z+1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x-1, y+2, z+1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x-1, y-2, z+1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x-1, y+2, z-1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x-1, y-2, z-1, world);
			// End Stone Bricks
		} else if (ct == CoreState.RED) {
			CoreManager.setBlock(Material.AIR, x-1, y, z, world);	
			CoreManager.setBlock(Material.AIR, x+1, y, z, world);
			CoreManager.setBlock(Material.AIR, x, y, z+1, world);
			CoreManager.setBlock(Material.AIR, x, y, z-1, world);		
			
			CoreManager.setBlock(Material.SEA_LANTERN, x, y, z, world);
			
			// White Glass
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 14, x-1, y, z+1, world); // Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 14, x-1, y, z-1, world); // Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 14, x+1, y, z+1, world); // Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 14, x+1, y, z-1, world); // Core Height
			
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 14, x+1, y+1, z-1, world); // Above Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 14, x+1, y+1, z, world); // Above Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 14, x+1, y+1, z+1, world); // Above Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 14, x-1, y+1, z-1, world); // Above Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 14, x-1, y+1, z, world); // Above Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 14, x-1, y+1, z+1, world); // Above Core Height
			
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 14, x+1, y-1, z-1, world); // Below Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 14, x+1, y-1, z, world); // Below Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 14, x+1, y-1, z+1, world); // Below Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 14, x-1, y-1, z-1, world); // Below Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 14, x-1, y-1, z, world); // Below Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 14, x-1, y-1, z+1, world); // Below Core Height
			
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 14, x, y-1, z+1, world); // Below Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 14, x, y-1, z-1, world); // Below Core Height

			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 14, x, y+1, z+1, world); // Above Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 14, x, y+1, z-1, world); // Above Core Height
			// End White Glass
			
			// End Rods
			CoreManager.setBlock(Material.END_ROD, 0, x, y+1, z, world);
			CoreManager.setBlock(Material.END_ROD, 1, x, y-1, z, world);
			// End End Rods
			
			// Stone Bricks
			CoreManager.setBlock(Material.SMOOTH_BRICK, x, y+2, z, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x, y-2, z, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x+1, y+2, z, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x+1, y-2, z, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x-1, y+2, z, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x-1, y-2, z, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x, y+2, z+1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x, y-2, z+1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x, y+2, z-1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x, y-2, z-1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x+1, y+2, z-1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x+1, y-2, z-1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x+1, y+2, z+1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x+1, y-2, z+1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x-1, y+2, z+1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x-1, y-2, z+1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x-1, y+2, z-1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x-1, y-2, z-1, world);
			// End Stone Bricks
		} else if (ct == CoreState.BROKEN) {
			CoreManager.setBlock(Material.AIR, x-1, y, z, world);	
			CoreManager.setBlock(Material.AIR, x+1, y, z, world);
			CoreManager.setBlock(Material.AIR, x, y, z+1, world);
			CoreManager.setBlock(Material.AIR, x, y, z-1, world);		
			
			CoreManager.setBlock(Material.OBSIDIAN, x, y, z, world);
			
			// White Glass
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 14, x-1, y, z+1, world); // Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 14, x-1, y, z-1, world); // Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 14, x+1, y, z+1, world); // Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 14, x+1, y, z-1, world); // Core Height
			
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 14, x+1, y+1, z-1, world); // Above Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 14, x+1, y+1, z, world); // Above Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 14, x+1, y+1, z+1, world); // Above Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 14, x-1, y+1, z-1, world); // Above Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 14, x-1, y+1, z, world); // Above Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 14, x-1, y+1, z+1, world); // Above Core Height
			
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 14, x+1, y-1, z-1, world); // Below Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 14, x+1, y-1, z, world); // Below Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 14, x+1, y-1, z+1, world); // Below Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 14, x-1, y-1, z-1, world); // Below Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 14, x-1, y-1, z, world); // Below Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 14, x-1, y-1, z+1, world); // Below Core Height
			
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 14, x, y-1, z+1, world); // Below Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 14, x, y-1, z-1, world); // Below Core Height

			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 14, x, y+1, z+1, world); // Above Core Height
			CoreManager.setBlock(Material.STAINED_GLASS_PANE, 14, x, y+1, z-1, world); // Above Core Height
			// End White Glass
			
			// End Rods
			CoreManager.setBlock(Material.END_ROD, 0, x, y+1, z, world);
			CoreManager.setBlock(Material.END_ROD, 1, x, y-1, z, world);
			// End End Rods
			
			// Stone Bricks
			CoreManager.setBlock(Material.SMOOTH_BRICK, x, y+2, z, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x, y-2, z, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x+1, y+2, z, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x+1, y-2, z, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x-1, y+2, z, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x-1, y-2, z, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x, y+2, z+1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x, y-2, z+1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x, y+2, z-1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x, y-2, z-1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x+1, y+2, z-1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x+1, y-2, z-1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x+1, y+2, z+1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x+1, y-2, z+1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x-1, y+2, z+1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x-1, y-2, z+1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x-1, y+2, z-1, world);
			CoreManager.setBlock(Material.SMOOTH_BRICK, x-1, y-2, z-1, world);
			// End Stone Bricks			
		}
		
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
