package com.gmail.justbru00.blazesmp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.justbru00.blazesmp.enums.Team;
import com.gmail.justbru00.blazesmp.utils.Messager;
import com.gmail.justbru00.blazesmp.utils.team.TeamChangeRequest;
import com.gmail.justbru00.blazesmp.utils.team.TeamManager;

public class RequestTeam implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (command.getName().equalsIgnoreCase("requestteam")) {
			
			if (!Event.TEAM_REQUESTS_ENABLED) {
				Messager.msgSender("&6Team requests are disabled. Contact an admin if you think this is in error.", sender);
				return true;
			}
			
			if (sender instanceof Player) {
				Player player = (Player) sender;
				
				if (args.length >= 2) {
					
					TeamChangeRequest tcr;
					
					StringBuilder builder = new StringBuilder("");
					
					for (int i = 1; i < args.length; i++) {
						builder.append(args[i] + " ");
					}
					
					if (args[0].equalsIgnoreCase("ICE")) {
						tcr = new TeamChangeRequest(player.getUniqueId().toString(), Team.ICE, builder.toString(), TeamManager.getNextRequestID());
						tcr.writeToConfig();
						Messager.msgPlayer("&aSubmitted a team change request. It will be reviewed by an admin soon.", player);
						return true;
					} else if (args[0].equalsIgnoreCase("NETHER")) {
						tcr = new TeamChangeRequest(player.getUniqueId().toString(), Team.NETHER, builder.toString(), TeamManager.getNextRequestID());
						tcr.writeToConfig();
						Messager.msgPlayer("&aSubmitted a team change request. It will be reviewed by an admin soon.", player);
						return true;
					} else {
						Messager.msgPlayer("&cPlease provide a valid team.", player);
						return true;
					}
					
				} else {
					Messager.msgPlayer("&cPlease provide proper arguments. /requestteam <teamName> <Reason>", player);
					return true;
				}
				
			} else {
				Messager.msgSender("&cSorry that can only be preformed by a player.", sender);
				return true;
			}			
		}
		
		return false;
	}

}
