package com.gmail.justbru00.blazesmp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.gmail.justbru00.blazesmp.enums.Team;
import com.gmail.justbru00.blazesmp.main.Main;
import com.gmail.justbru00.blazesmp.utils.Debug;
import com.gmail.justbru00.blazesmp.utils.Messager;
import com.gmail.justbru00.blazesmp.utils.team.TeamManager;

import io.puharesource.mc.titlemanager.api.ActionbarTitleObject;

public class OnJoin implements Listener {

	ActionbarTitleObject iceMsg = new ActionbarTitleObject(Messager.color("&bA player on your team just joined."));
	ActionbarTitleObject netherMsg = new ActionbarTitleObject(Messager.color("&cA player on your team just joined."));
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();		
		
		Main.getInstance().getConfig().set("players.data." + player.getUniqueId().toString() + ".lastname", player.getName());
		Main.getInstance().saveConfig();
		
		if (TeamManager.getTeam(player) == null) { // Player Joined for the first time.
			TeamManager.findAndJoinTeam(player);	
			Debug.send("Attempted to join a team.");
			return;
		} 		
		
		if (TeamManager.getTeam(player) == Team.ICE) {	
			Debug.send("Ice player joined");
			
			Main.ICE.addPlayer(player);
			
			for(Player online : Bukkit.getOnlinePlayers()) {
				if (TeamManager.getTeam(online) == Team.ICE) {
					iceMsg.send(online);
				}
			}
		} else if (TeamManager.getTeam(player) == Team.NETHER) { 			
			Debug.send("Nether player joined.");
			
			Main.NETHER.addPlayer(player);
			
			for(Player online : Bukkit.getOnlinePlayers()) {
				if (TeamManager.getTeam(online) == Team.NETHER) {
					netherMsg.send(online);
				}
			}
		}
		
		
	}
}
