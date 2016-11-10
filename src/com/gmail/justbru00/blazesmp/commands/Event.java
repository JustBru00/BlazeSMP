package com.gmail.justbru00.blazesmp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.gmail.justbru00.blazesmp.main.Main;
import com.gmail.justbru00.blazesmp.utils.Messager;
import com.gmail.justbru00.blazesmp.utils.team.TeamManager;

public class Event implements CommandExecutor {
	
	public static boolean TEAM_REQUESTS_ENABLED = true;
	public static boolean FRIENDLY_FIRE_ENABLED = false;
	public static boolean SCOREBOARD_ENABLED = false;
	public static int COUNTDOWN_TIME = -1;
	

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (command.getName().equalsIgnoreCase("event")) {
			if (sender.hasPermission("blazesmp.event")) {
				if (args.length >= 1) {
					
					try {
					if (args[0].equalsIgnoreCase("teamrequests")) {
						if (args[1].equalsIgnoreCase("true")) {
							TEAM_REQUESTS_ENABLED = true;
							Messager.msgSender("&6Team requests are now &a&lENABLED&6.", sender);
							return true;
						} else if (args[1].equalsIgnoreCase("false")) {
							TEAM_REQUESTS_ENABLED = false;
							Messager.msgSender("&6Team requests are now &c&lDISABLED&6.", sender);
							return true;
						} else {
							Messager.msgSender("&6Team requests are: " + TEAM_REQUESTS_ENABLED, sender);
							return true;
						}
					} else if (args[0].equalsIgnoreCase("friendlyfire")) {
						if (args[1].equalsIgnoreCase("true")) {
							FRIENDLY_FIRE_ENABLED = true;
							TeamManager.setFriendlyFireEnabled(FRIENDLY_FIRE_ENABLED);
							Messager.msgSender("&6Friendly Fire is now &a&lENABLED&6.", sender);
							return true;
						} else if (args[1].equalsIgnoreCase("false")) {
							FRIENDLY_FIRE_ENABLED = false;
							TeamManager.setFriendlyFireEnabled(FRIENDLY_FIRE_ENABLED);
							Messager.msgSender("&6Friendly Fire is now &c&lDISABLED&6.", sender);
							return true;
						} else {
							Messager.msgSender("&6Friendly Fire is: " + FRIENDLY_FIRE_ENABLED, sender);
							return true;
						}
					} else if (args[0].equalsIgnoreCase("prefix")) {
						Main.PREFIX = Messager.color(args[1]);
						return true;
					}
					
					} catch (ArrayIndexOutOfBoundsException e) {
						Messager.msgSender("&cYou failed to provide proper args. (ArrayIndexOutOfBoundsException)", sender);
						return true;
					}
					
				} else {
					Messager.msgSender("&cPlease provide some arguments. /event <args>", sender);
					return true;
				}
			} else {
				Messager.msgSender("&cSorry you don't have permission.", sender);
				return true;
			}
		}
		
		return false;
	}
	
	

}
