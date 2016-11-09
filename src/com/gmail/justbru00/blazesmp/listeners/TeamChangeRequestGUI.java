package com.gmail.justbru00.blazesmp.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.gmail.justbru00.blazesmp.utils.Debug;
import com.gmail.justbru00.blazesmp.utils.Messager;
import com.gmail.justbru00.blazesmp.utils.itemstuffs.PremadeInventory;
import com.gmail.justbru00.blazesmp.utils.team.TeamChangeRequest;
import com.gmail.justbru00.blazesmp.utils.team.TeamManager;

import io.puharesource.mc.titlemanager.api.TitleObject;

public class TeamChangeRequestGUI implements Listener{

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		
		if (e.getCurrentItem() == null || e.getClickedInventory() == null || e.getCurrentItem().getType() == Material.AIR) {
			return;
		}
		
		if (e.getInventory().getName().equals(PremadeInventory.TEAM_REQUESTS_NAME)) {
			
			e.setCancelled(true);
			
			if (e.getCurrentItem().getType() != Material.PAPER && e.getCurrentItem().getType() != Material.SKULL_ITEM) {
				Debug.send("NOT PAPER");
				return;
			}
			
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messager.color("&cBack"))) { // Back
				e.getWhoClicked().openInventory(PremadeInventory.basMain((Player) e.getWhoClicked()));
				return;
			} else if (e.getCurrentItem().getItemMeta().hasLore()) {
				e.getWhoClicked().openInventory(PremadeInventory.confirmTeamRequest(e.getCurrentItem()));
			} 
		} else if (e.getInventory().getName().contains(PremadeInventory.TEAM_REQUESTS_CONFIRM_PREFIX)) {
			e.setCancelled(true);
			
			if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messager.color("&a&lAccept"))) { // Accept
				
				TeamChangeRequest tcr = new TeamChangeRequest(Integer.parseInt(e.getInventory().getName().substring(e.getInventory().getName().length() - 1)));
				
				tcr.setAccepted(true);
				tcr.setAcceptedTime(System.currentTimeMillis());
				tcr.setAccepterUUID(e.getWhoClicked().getUniqueId().toString());
				tcr.setDenied(false);
				tcr.writeToConfig();
				
				TeamManager.setTeam((Player) e.getWhoClicked(), tcr.getTeamToChangeTo());
				
				e.getWhoClicked().closeInventory();
				
				new TitleObject("", Messager.color("&aRequest accepted!")).send((Player) e.getWhoClicked());
				
			} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messager.color("&c&lDeny"))) { // Deny
				
				TeamChangeRequest tcr = new TeamChangeRequest(Integer.parseInt(e.getInventory().getName().substring(e.getInventory().getName().length() - 1)));
				
				tcr.setAccepted(false);
				tcr.setAcceptedTime(System.currentTimeMillis());
				tcr.setAccepterUUID(e.getWhoClicked().getUniqueId().toString());
				tcr.setDenied(true);
				tcr.writeToConfig();				
				
				e.getWhoClicked().closeInventory();
				
				new TitleObject("", Messager.color("&aRequest denied!")).send((Player) e.getWhoClicked());
			} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(Messager.color("&cBack"))) { // Back
				e.getWhoClicked().openInventory(PremadeInventory.teamRequests());
				return;
			}
		}
		
	}
	
}
