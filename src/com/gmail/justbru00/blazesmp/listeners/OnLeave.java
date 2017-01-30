package com.gmail.justbru00.blazesmp.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.gmail.justbru00.blazesmp.enums.Team;
import com.gmail.justbru00.blazesmp.enums.WarState;
import com.gmail.justbru00.blazesmp.main.Main;
import com.gmail.justbru00.blazesmp.scoreboard.EpicScoreboardManager;
import com.gmail.justbru00.blazesmp.utils.Debug;
import com.gmail.justbru00.blazesmp.utils.team.TeamManager;
import com.gmail.justbru00.blazesmp.war.WarManager;

public class OnLeave implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		
		if (TeamManager.getTeam(player) == Team.ICE)  {
			EpicScoreboardManager.ICE.removePlayer(player);
			Debug.send("Removed " + player.getName() + " from Scoreboard team ICE");
		} else if (TeamManager.getTeam(player) == Team.NETHER) {
			EpicScoreboardManager.NETHER.removePlayer(player);
			Debug.send("Removed " + player.getName() + " from Scoreboard team NETHER");
		}
		
		
		if (WarManager.CURRENT_WAR_STATE == WarState.DURING) {			
			if (WarManager.icePeace.contains(player.getUniqueId().toString())) {
				WarManager.icePeace.remove(player.getUniqueId().toString());
			} else if (WarManager.netherPeace.contains(player.getUniqueId().toString())) {
				WarManager.netherPeace.remove(player.getUniqueId().toString());
			}
			WarManager.tryPeace();						
		}
		
	}
	
}
