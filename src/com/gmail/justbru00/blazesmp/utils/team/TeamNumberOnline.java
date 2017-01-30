package com.gmail.justbru00.blazesmp.utils.team;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.gmail.justbru00.blazesmp.enums.Team;

public class TeamNumberOnline {

	public int netherOnline = 0;
	public int iceOnline = 0;
	
	public TeamNumberOnline() {
		for (Player loop : Bukkit.getOnlinePlayers()) {
			Team t = TeamManager.getTeam(loop);
			if (t == Team.ICE) {
				iceOnline++;
			} else if (t == Team.NETHER) {
				netherOnline++;
			}
		}		
	}
	
	public int getIce() {
		return iceOnline;
	}
	
	public int getNether() {
		return netherOnline;
	}
	
}
