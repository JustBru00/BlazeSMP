package com.gmail.justbru00.blazesmp.utils.cores;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;

import com.gmail.justbru00.blazesmp.utils.Messager;

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
		} else if (x == true) {
			updateBossBars();
		}
		
	}
	
	public static void updateBossBars() {
		for (Core core : cores) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				core.getBossBar().addPlayer(p);
				core.getBossBar().setVisible(true);
			}
			
			if (core.getPercentage() == 0) {
				core.getBossBar().setTitle(Messager.color(core.getBossBar().getTitle() + " &7[BROKEN]"));
			} else if (core.getPercentage() >= 0){
				core.getBossBar().setProgress((core.getPercentage()) / 100 ); 
			}
		}
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
