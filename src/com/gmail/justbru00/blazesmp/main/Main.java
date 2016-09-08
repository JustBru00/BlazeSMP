package com.gmail.justbru00.blazesmp.main;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import com.gmail.justbru00.blazesmp.commands.BlazeSMPAdmin;
import com.gmail.justbru00.blazesmp.listeners.OnJoin;
import com.gmail.justbru00.blazesmp.utils.Messager;

public class Main extends JavaPlugin implements CommandExecutor {
	
	public static ConsoleCommandSender clogger = Bukkit.getServer().getConsoleSender();
	public static Logger log = Bukkit.getLogger();
	public static boolean debug = true;
	public static final String PREFIX = Messager.color("&8[&6Blaze&cSMP&8] &f");
	public static Main plugin;
	public static ScoreboardManager sm;
	public static Scoreboard board;	
	public static Team ICE;
	public static Team NETHER;
	public static Team NONE;
	

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		return false;
	}

	@Override
	public void onDisable() {
		
		
		plugin = null; // Close Memory Leak
	}

	@Override
	public void onEnable() {
		plugin = this;
		Messager.msgConsole("&aStarting Plugin...");
		
		saveDefaultConfig();
		readyScoreboardTeams();
		
		// Register Command Executors 
		getCommand("blazesmpadmin").setExecutor(new BlazeSMPAdmin());
		
		// Listeners
		Bukkit.getServer().getPluginManager().registerEvents(new OnJoin(), this);
		
	}
	
	public static Main getInstance(){
		return plugin;
	}	
	
	
	
	public static void readyScoreboardTeams() { 
		
		sm = Bukkit.getScoreboardManager();
		board = sm.getMainScoreboard();
		
		try { // ICE
		 ICE = board.registerNewTeam("ICE");		 
		} catch (IllegalArgumentException e) {
			Messager.msgConsole("&cWell the ICE team is already made.");
		}
		
		try { // NETHER
			NETHER = board.registerNewTeam("NETHER");		 
		} catch (IllegalArgumentException e) {
			Messager.msgConsole("&cWell the NETHER team is already made.");
		}
		
		try { // NONE
			NONE = board.registerNewTeam("NONE");		 
		} catch (IllegalArgumentException e) {
			Messager.msgConsole("&cWell the NONE team is already made.");
		}
		
		// Ice Setup
		//ICE.setAllowFriendlyFire(false);
		//ICE.setPrefix(Messager.color("&a"));
		
		// Nether Setup
		//NETHER.setAllowFriendlyFire(false);
		//NETHER.setPrefix(Messager.color("&c"));
		
		// None Setup
		//NONE.setAllowFriendlyFire(false);
		//NONE.setPrefix(Messager.color("&7"));
		
	}

}
