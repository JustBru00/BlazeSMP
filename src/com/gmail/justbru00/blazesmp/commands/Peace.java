package com.gmail.justbru00.blazesmp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.justbru00.blazesmp.enums.Team;
import com.gmail.justbru00.blazesmp.utils.team.TeamManager;

public class Peace implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (command.getName().equalsIgnoreCase("peace")) {
			
			if (sender instanceof Player) {
				Player player = (Player) sender;
				
				if (TeamManager.getTeam(player) == Team.ICE) {
					
				} else if (TeamManager.getTeam(player) == Team.NETHER){
					
				}
				
			}
			
		}
		
		return false;
	}

	
	
	
}
