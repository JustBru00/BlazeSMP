package com.gmail.justbru00.blazesmp.utils;

import org.bukkit.entity.Player;

import com.gmail.justbru00.blazesmp.enums.Team;
import com.gmail.justbru00.blazesmp.main.Main;

public class Teams {

	public static Team getTeam(Player player) {
		String team = Main.getInstance().getConfig().getString("players.data." + player.getUniqueId() + ".team");
		if (team.equalsIgnoreCase("ICE")) {
			return Team.ICE;
		} else if (team.equalsIgnoreCase("NETHER")) {
			return Team.NETHER;
		} else {
			return Team.NONE;
		}
	}
}
