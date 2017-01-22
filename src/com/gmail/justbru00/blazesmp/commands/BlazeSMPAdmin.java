package com.gmail.justbru00.blazesmp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.gmail.justbru00.blazesmp.enums.CoreState;
import com.gmail.justbru00.blazesmp.enums.Team;
import com.gmail.justbru00.blazesmp.enums.WarState;
import com.gmail.justbru00.blazesmp.main.Main;
import com.gmail.justbru00.blazesmp.utils.Debug;
import com.gmail.justbru00.blazesmp.utils.Messager;
import com.gmail.justbru00.blazesmp.utils.PluginLogger;
import com.gmail.justbru00.blazesmp.utils.cores.Core;
import com.gmail.justbru00.blazesmp.utils.cores.CoreManager;
import com.gmail.justbru00.blazesmp.utils.itemstuffs.PremadeInventory;
import com.gmail.justbru00.blazesmp.utils.team.TeamManager;
import com.gmail.justbru00.blazesmp.war.WarManager;

import net.md_5.bungee.api.ChatColor;

public class BlazeSMPAdmin implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("blazesmpadmin")) {
			if (sender.hasPermission("blazesmp.admin")) {
				if (args.length > 0) {
					if (args[0].equalsIgnoreCase("gui")) {
						Debug.send("Ready to open the GUI.");
						
						if (!(sender instanceof Player)) {
							Messager.msgSender("&cSorry only players can open gui.", sender);
							return true;
						}
						
						Player player = (Player) sender;
						
						FileConfiguration config = Main.getInstance().getConfig();
						if (!config.isSet("players.data." + player.getUniqueId().toString() +  ".admin.notifications.nocheat")) {
							config.set("players.data." + player.getUniqueId().toString() +  ".admin.notifications.nocheat", true);
							config.set("players.data." + player.getUniqueId().toString() +  ".admin.notifications.ores", false);
							config.set("players.data." + player.getUniqueId().toString() +  ".admin.notifications.staffmode", false);
							Main.getInstance().saveConfig();
						}
						
						player.openInventory(PremadeInventory.basMain(player));
						
						return true;
					} else if (args[0].equalsIgnoreCase("setstate")) {
						
						if (!(sender instanceof Player)) {
							Messager.msgSender("&cOnly players set the state.", sender);
							return true;
						}
						
						Player player = (Player) sender;
							
						
						
						if (args.length != 2) {
							Messager.msgPlayer("&cPlease provide a state.", player);
							return true;
						}
						
						if (args[1].equalsIgnoreCase("start")) {
							WarManager.CURRENT_WAR_STATE = WarState.START;
						} else if (args[1].equalsIgnoreCase("end")) {
							WarManager.CURRENT_WAR_STATE = WarState.END;
						} else if (args[1].equalsIgnoreCase("during")) {
							WarManager.CURRENT_WAR_STATE = WarState.DURING;
						} else if (args[1].equalsIgnoreCase("cooldown")) {
							WarManager.CURRENT_WAR_STATE = WarState.COOLDOWN;
						} else if (args[1].equalsIgnoreCase("peace")) {
							WarManager.CURRENT_WAR_STATE = WarState.PEACE;
						}
						
						Messager.msgPlayer("&aSet state.", player);
						
						return true;
					} else if (args[0].equalsIgnoreCase("settime")) {
						
						if (!(sender instanceof Player)) {
							Messager.msgSender("&cOnly players set the time.", sender);
							return true;
						}
						
						Player player = (Player) sender;
							
						
						
						if (args.length != 2) {
							Messager.msgPlayer("&cPlease provide a time.", player);
							return true;
						}
						
						WarManager.timeLeft = Integer.parseInt(args[1]);
						
						Messager.msgPlayer("&aSet time.", player);
						
						return true;
					}else if (args[0].equalsIgnoreCase("corebreak")) {
						if (CoreManager.areCoresEnabled()) {
							CoreManager.setCoresEnabled(false);
							Debug.send("Cores are now disabled");
						} else {
							CoreManager.setCoresEnabled(true);
							Debug.send("Cores are now enabled.");
						}
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
							
							String oldTeam = TeamManager.getColoredTeamName(TeamManager.getTeam(commandTarget)); // Remember Old team for message and logging
							TeamManager.setTeam(commandTarget, setTeam); // Set the team
							Messager.msgSender("&aSwitched player " + commandTarget.getName() + " from " + oldTeam + " to " + TeamManager.getColoredTeamName(TeamManager.getTeam(commandTarget)) + ".", sender); // Tell sender they change the persons team successfully.
							PluginLogger.log(ChatColor.stripColor(sender.getName() + " switched player " + commandTarget.getName() + " from " + oldTeam + " to " + TeamManager.getColoredTeamName(TeamManager.getTeam(commandTarget)) + "."));
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
					Debug.send("Ready to open the GUI.");
					
					if (!(sender instanceof Player)) {
						Messager.msgSender("&cSorry only players can open gui.", sender);
						return true;
					}
					
					Player player = (Player) sender;
					
					FileConfiguration config = Main.getInstance().getConfig();
					if (!config.isSet("players.data." + player.getUniqueId().toString() +  ".admin.notifications.nocheat")) {
						config.set("players.data." + player.getUniqueId().toString() +  ".admin.notifications.nocheat", true);
						config.set("players.data." + player.getUniqueId().toString() +  ".admin.notifications.ores", false);
						config.set("players.data." + player.getUniqueId().toString() +  ".admin.notifications.staffmode", false);
						Main.getInstance().saveConfig();
					}
					
					player.openInventory(PremadeInventory.basMain(player));
					
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
