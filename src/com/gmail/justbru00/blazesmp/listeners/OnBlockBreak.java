package com.gmail.justbru00.blazesmp.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.gmail.justbru00.blazesmp.utils.Debug;
import com.gmail.justbru00.blazesmp.utils.cores.Core;
import com.gmail.justbru00.blazesmp.utils.cores.CoreManager;

public class OnBlockBreak implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
	public void onBlockBreakEvent(BlockBreakEvent e) {
		
		Debug.send("Block broken");
		
		if (CoreManager.areCoresEnabled()) { // Are you allowed to break the cores?
			
		
			
			for (Core c : CoreManager.cores) { // Check all setup cores
				if (e.getBlock().getLocation() == c.getLocation()) {
					c.coreBreakHandle();	
					e.setCancelled(true);
				}
			}
			
		} 
		
	}

}
