package com.gmail.justbru00.blazesmp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.gmail.justbru00.blazesmp.enums.Team;
import com.gmail.justbru00.blazesmp.main.Main;
import com.gmail.justbru00.blazesmp.utils.Messager;
import com.gmail.justbru00.blazesmp.utils.TeamManager;

import io.puharesource.mc.titlemanager.api.ActionbarTitleObject;

public class OnJoin implements Listener {

	ActionbarTitleObject iceMsg = new ActionbarTitleObject(Messager.color("&aA player on your team just joined."));
	ActionbarTitleObject netherMsg = new ActionbarTitleObject(Messager.color("&cA player on your team just joined."));
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		
		if (TeamManager.getTeam(player) == null) { // Player Joined for the first time.
			TeamManager.findAndJoinTeam(player);			
			return;
		} 		
		
		if (TeamManager.getTeam(player) == Team.ICE) {			
			for(Player online : Bukkit.getOnlinePlayers()) {
				if (Main.board.getPlayerTeam(player).getName() == Main.ICE.getName()) {
					iceMsg.send(online);
				}
			}
		} else if (TeamManager.getTeam(player) == Team.NETHER) {
			for(Player online : Bukkit.getOnlinePlayers()) {
				if (Main.board.getPlayerTeam(player).getName() == Main.NETHER.getName()) {
					netherMsg.send(online);
				}
			}
		}
		
		
	}
}
