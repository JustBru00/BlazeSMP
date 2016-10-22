package com.gmail.justbru00.blazesmp.main;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import com.gmail.justbru00.blazesmp.commands.BlazeSMPAdmin;
import com.gmail.justbru00.blazesmp.listeners.BlazeSMPAdminMain;
import com.gmail.justbru00.blazesmp.listeners.OnJoin;
import com.gmail.justbru00.blazesmp.listeners.OnLeave;
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
		
		// TODO Check for TitleMananger and ResoucePackApi
		
		// Register Command Executors 
		getCommand("blazesmpadmin").setExecutor(new BlazeSMPAdmin());
		
		PluginManager pm = Bukkit.getServer().getPluginManager();
		
		// Listeners
		pm.registerEvents(new OnJoin(), plugin);
		pm.registerEvents(new OnLeave(), plugin);
		pm.registerEvents(new BlazeSMPAdminMain(), plugin);
		
		Messager.msgConsole("&aEnable Complete!!!");
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
			Messager.msgConsole("&cICE team is already made.");
		}
		
		try { // NETHER
			NETHER = board.registerNewTeam("NETHER");		 
		} catch (IllegalArgumentException e) {
			Messager.msgConsole("&cNETHER team is already made.");
		}
		
		try { // NONE
			NONE = board.registerNewTeam("NONE");		 
		} catch (IllegalArgumentException e) {
			Messager.msgConsole("&cNONE team is already made.");
		}
		
		ICE = board.getTeam("ICE");
		NETHER = board.getTeam("NETHER");
		NONE = board.getTeam("NONE");
		
		 //Ice Setup
		ICE.setAllowFriendlyFire(false);
		ICE.setPrefix(Messager.color("&b"));
		
		// Nether Setup
		NETHER.setAllowFriendlyFire(false);
		NETHER.setPrefix(Messager.color("&c"));
		
		// None Setup
		NONE.setAllowFriendlyFire(false);
		NONE.setPrefix(Messager.color("&7"));
		
		
		
	}

}
