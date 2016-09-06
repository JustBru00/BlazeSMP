package com.gmail.justbru00.blazesmp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.gmail.justbru00.blazesmp.utils.Messager;

public class BlazeSMPAdmin implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("blazesmpadmin")) {
			if (sender.hasPermission("blazesmp.admin")) {
				if (args.length > 0) {
					if (args[0].equalsIgnoreCase("gui")) {
						// Open Admin GUI
					} else if (args[0].equalsIgnoreCase("setteam")) {
						
					} else {
						Messager.msgSender("&fHey ", sender);
						return true;
					}
				} else {
					Messager.msgSender("&fSo you want the GUI huh? Then type /blazesmpadmin gui. How hard is that? (No args)", sender);
					return true;	
				}					
			} else {
				Messager.msgSender("&4Sorry can only command admins that use. (No permission)", sender);
				return true;				
			}
		}
		return false;
	}

}
