package com.gmail.justbru00.blazesmp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.justbru00.blazesmp.enums.Team;
import com.gmail.justbru00.blazesmp.main.Main;
import com.gmail.justbru00.blazesmp.utils.Debug;
import com.gmail.justbru00.blazesmp.utils.Messager;
import com.gmail.justbru00.blazesmp.utils.PluginLogger;
import com.gmail.justbru00.blazesmp.utils.TeamHandler;

import net.md_5.bungee.api.ChatColor;

public class BlazeSMPAdmin implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("blazesmpadmin")) {
			if (sender.hasPermission("blazesmp.admin")) {
				if (args.length > 0) {
					if (args[0].equalsIgnoreCase("gui")) {
						Debug.send("Ready to open the GUI.");
						
						// TODO Open Admin GUI
						
						return true;
					} else if (args[0].equalsIgnoreCase("setteam")) { // /blazesmpadmin setteam [player] [team] <reason>
						Debug.send("Handling setteam in /blazesmpadmin");
						
						if (args.length >= 3)  { // Required Args handling.
							
							Player commandTarget = null;
							
							// Safely check for the requested player.
							try {
								commandTarget = Bukkit.getPlayer(args[1]);
							} catch (Exception e) {
								Messager.msgSender("&cYeah.... you need to type a player name. In other news please thank JustBru00 for making that try and catch block.", sender);
								if (Main.debug) e.printStackTrace(); // Only print error if debug is true.
								return true;
							}
							// Player checks out. Yea!
							
							Team setTeam = null;
							
							if (args[2].equalsIgnoreCase("ICE"))  {
								setTeam = Team.ICE;
							} else if (args[2].equalsIgnoreCase("NETHER"))  {
								setTeam = Team.NETHER;
							} else if (args[2].equalsIgnoreCase("NONE")) {
								setTeam = Team.NONE;
							} else {
								Messager.msgSender("&cSorry you need to type a proper team. WHY YOU DO THIS?!", sender);
								return true;								
							}							
							
							String oldTeam = TeamHandler.getColoredTeamName(TeamHandler.getTeam(commandTarget));
							TeamHandler.setTeam(commandTarget, setTeam);
							Messager.msgSender("&aSwitched player " + commandTarget.getName() + " from " + oldTeam + " to " + TeamHandler.getColoredTeamName(TeamHandler.getTeam(commandTarget)) + ".", sender);
							PluginLogger.log(ChatColor.stripColor(sender.getName() + " switched player " + commandTarget.getName() + " from " + oldTeam + " to " + TeamHandler.getColoredTeamName(TeamHandler.getTeam(commandTarget)) + "."));
							return true;
						} else {
							Messager.msgSender("&cPlease follow usage for /blazesmpadmin setteam [player] [team] <reason>.", sender);
							return true;
						}						
					} else {
						Messager.msgSender("&fHey please type an argument after /blazesmpadmin. (Or just type 'gui' after it.)", sender);
						return true;
					}
				} else {
					Messager.msgSender("&cSo you want the GUI huh? Then type /blazesmpadmin gui. How hard is that? (No args)", sender);
					return true;	
				}					
			} else {
				Messager.msgSender("&cSorry can only command admins that use. (No permission)", sender);
				return true;				
			}
		}
		return false;
	}

}
