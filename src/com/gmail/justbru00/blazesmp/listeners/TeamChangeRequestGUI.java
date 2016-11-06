package com.gmail.justbru00.blazesmp.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.gmail.justbru00.blazesmp.utils.Debug;
import com.gmail.justbru00.blazesmp.utils.itemstuffs.PremadeInventory;

public class TeamChangeRequestGUI implements Listener{

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		
		if (e.getCurrentItem() == null || e.getClickedInventory() == null || e.getCurrentItem().getType() == Material.AIR) {
			return;
		}
		
		if (e.getClickedInventory().getName().equals(PremadeInventory.TEAM_REQUESTS_NAME)) {
			
			e.setCancelled(true);
			
			if (e.getCurrentItem().getType() != Material.PAPER) {
				Debug.send("NOT PAPER");
				return;
			}
			
			e.getWhoClicked().openInventory(PremadeInventory.confirmTeamRequest(e.getCurrentItem()));
			
			//TODO MAKE SURE PLAYER CAN'T PUT ITEMS IN INVENTORY
			
		}
		
	}
	
}
