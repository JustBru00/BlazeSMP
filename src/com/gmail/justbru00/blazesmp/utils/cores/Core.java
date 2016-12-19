package com.gmail.justbru00.blazesmp.utils.cores;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

import com.gmail.justbru00.blazesmp.enums.CoreState;
import com.gmail.justbru00.blazesmp.enums.Team;
import com.gmail.justbru00.blazesmp.utils.Debug;
import com.gmail.justbru00.blazesmp.utils.Messager;

import io.puharesource.mc.titlemanager.api.ActionbarTitleObject;

public class Core {

	private Team owner;
	private Location coreLoc;
	private World world;
	private short timesLeftToBreak = -1;
	private double percentageLeft = 100;
	private boolean coreBreakable = false;		
	private CoreState currentState = CoreState.DEFAULT;
			
	public Core(Team _owner, int locX, int locY, int locZ, World coreIsIn) {
		owner = _owner;
		coreLoc = new Location(coreIsIn, locX, locY, locZ);
		world = coreIsIn;
		CoreManager.cores.add(this);
	}
	
	/**
	 * Gets the location of the core. Keep in mind this is NOT the core structure.
	 * @return The location of the core.
	 */
	public Location getLocation() {
		return coreLoc;
	}

	/**
	 * @deprecated {@link CoreManager#setCoresEnabled(boolean)} SHOULD BE USED INSTEAD OF THIS FOR WAR. THIS METHOD IS FOR A FUTURE IMPLEMENTATION.
	 * Sets the core as breakable or not.
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
	 * Resets the core back to it's normal state.
	 */
	public void reset() {
		buildCoreStructure(CoreState.DEFAULT);
		timesLeftToBreak = (short) 300;		
		Debug.send("Core at " + coreLoc.toString() + " has been reset.");
	}

	/**
	 * Updates the percentage display and the glass color
	 */
	public void updateCoreStatus() {
		if (CoreState.DEFAULT == currentState) {
			buildCoreStructure(CoreState.GREEN);
			currentState = CoreState.GREEN;
		}
		
		percentageLeft = (timesLeftToBreak / 300) * (int) 100;		
		
		if ((percentageLeft < 101) && (percentageLeft > 74)) { // 100% to 74%
			currentState = CoreState.GREEN;
		} else if ((percentageLeft < 75) && (percentageLeft > 14)) { // 74% to 15%
			currentState = CoreState.YELLOW;
		} else if (percentageLeft < 15) { // 14% to 0%
			currentState = CoreState.RED;
		} else if (percentageLeft <=0) {
			Debug.send("CORE IS BROKEN.");
			currentState = CoreState.BROKEN;
		}
		
		buildCoreStructure(currentState);
		
		Debug.send("There is " + percentageLeft + "% left.");
	}
	/**
	 * Gets the players nearby the cores location.
	 * @param distance
	 * @return
	 */
	public List<Player> getNearbyPlayers(double distance) {
	   double radius = distance;	   
	   List<Entity> nearby = coreLoc.getWorld().getEntities();
	   List<Player> players = new ArrayList<Player>();
	   
	   for (Entity e : nearby) {
		   if (e.getLocation().distance(coreLoc) <= radius) {
			   if (e.getType() == EntityType.PLAYER) {
				   Debug.send("Found player " + e.getName());
				   players.add((Player) e);
			   }
		   }
	   }
	   
	   return players;
	}

	public void coreBreakHandle() {		
		if (CoreManager.areCoresEnabled()) {
			timesLeftToBreak = (short) (timesLeftToBreak - 1);
			updateCoreStatus();
			return;
		} else {
			Debug.send("Core is unbreakable.");
			return;
		}
	}

	/**
	 * @deprecated Use {@link CoreManager#areCoresEnabled()}
	 * @return
	 */
	public boolean coreIsBreakable() {
		return coreBreakable;
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
	

}
