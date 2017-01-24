package com.gmail.justbru00.blazesmp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.gmail.justbru00.blazesmp.enums.Team;
import com.gmail.justbru00.blazesmp.enums.WarState;
import com.gmail.justbru00.blazesmp.main.Main;
import com.gmail.justbru00.blazesmp.utils.Messager;
import com.gmail.justbru00.blazesmp.utils.team.TeamManager;
import com.gmail.justbru00.blazesmp.war.WarManager;

public class OnDeath implements Listener {

	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		final Player p = e.getEntity();		
		
		if (WarManager.CURRENT_WAR_STATE == WarState.PEACE) {
			e.setKeepInventory(true);
			e.setKeepLevel(true);
			e.setDroppedExp(0);		
			
			if (p.getKiller() != null) {
				if (p.getKiller().getType() == EntityType.PLAYER) {
					Player killer = p.getKiller();
					
					if (TeamManager.getTeam(p) == Team.ICE && TeamManager.getTeam(killer) == Team.NETHER) {
						Messager.sendBC("&c" + killer.getName() + "&c just killed &b" + p.getName() + "&c.");
						Messager.sendBC("&cA WAR STARTS NOW.");
						WarManager.CURRENT_WAR_STATE = WarState.START;
					} else if (TeamManager.getTeam(p) == Team.NETHER && TeamManager.getTeam(killer) == Team.ICE) {
						Messager.sendBC("&b" + killer.getName() + "&c just killed &c" + p.getName() + "&c.");
						Messager.sendBC("&cA WAR STARTS NOW.");
						WarManager.CURRENT_WAR_STATE = WarState.START;
					}				
				}
			}
			
		} 		
		
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				p.spigot().respawn();
				
			}
		}, 2);		
	}
	
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		
		
		
		
	}
	
}
