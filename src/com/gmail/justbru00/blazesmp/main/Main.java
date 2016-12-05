package com.gmail.justbru00.blazesmp.main;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import com.gmail.justbru00.blazesmp.commands.BlazeSMPAdmin;
import com.gmail.justbru00.blazesmp.commands.Event;
import com.gmail.justbru00.blazesmp.commands.RequestTeam;
import com.gmail.justbru00.blazesmp.listeners.BlazeSMPAdminMain;
import com.gmail.justbru00.blazesmp.listeners.OnJoin;
import com.gmail.justbru00.blazesmp.listeners.OnLeave;
import com.gmail.justbru00.blazesmp.listeners.TeamChangeRequestGUI;
import com.gmail.justbru00.blazesmp.listeners.TempInteract;
import com.gmail.justbru00.blazesmp.utils.Messager;
import com.gmail.justbru00.blazesmp.utils.PluginFile;
import com.gmail.justbru00.blazesmp.utils.team.TeamManager;
import com.gmail.justbru00.blazesmp.utils.timestuffs.TimerRunnable;

import io.puharesource.mc.titlemanager.TitleManager;

public class Main extends JavaPlugin implements CommandExecutor {
	
	public static ConsoleCommandSender clogger = Bukkit.getServer().getConsoleSender();
	public static Logger log = Bukkit.getLogger();
	public static boolean debug = true;
	public static String PREFIX = Messager.color("&8[&6Blaze&cSMP&8] &f");
	public static Main plugin;
	public static ScoreboardManager sm;
	public static Scoreboard board;	
	public static Team ICE;
	public static Team NETHER;
	public static Team NONE;
	public PluginFile teamRequestsFile = null;
	

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		return false;
	}

	@Override
	public void onDisable() {
		
		Messager.msgConsole("&6The plugin has been disabled.");
		plugin = null; // Close Memory Leak
	}

	@Override
	public void onEnable() {
		plugin = this;
		Messager.msgConsole("&aStarting Plugin...");
		
		PluginManager pm = Bukkit.getServer().getPluginManager();
		
		if (pm.getPlugin("TitleManager") == null) {
			Messager.msgConsole("&4&lTitleManager is not installed.");
			log.severe("[BlazeSMP] TitleManager is not installed. DISABLING PLUGIN.");
			pm.disablePlugin(this);
			return;
		}
		
		if (pm.getPlugin("ResourcePackApi") == null) {
			Messager.msgConsole("&cResourcePackApi is not installed on this server. Custom sounds are now disabled.");			
		}
		
		
		
		// Configs
		saveDefaultConfig();
		teamRequestsFile = new PluginFile(this, "teamrequests.yml", "teamrequests.yml");
		
		readyScoreboardTeams();
		TeamManager.refreshRequestsFromConfig();
		
		// TODO Check for TitleMananger and ResoucePackApi
		
		// Register Command Executors 
		getCommand("blazesmpadmin").setExecutor(new BlazeSMPAdmin());
		getCommand("requestteam").setExecutor(new RequestTeam());
		getCommand("event").setExecutor(new Event());
		
		
		
		
		// Listeners
		pm.registerEvents(new OnJoin(), plugin);
		pm.registerEvents(new OnLeave(), plugin);
		pm.registerEvents(new BlazeSMPAdminMain(), plugin);
		pm.registerEvents(new TeamChangeRequestGUI(), plugin);	
		pm.registerEvents(new TempInteract(), plugin);
		
		@SuppressWarnings("unused")
		BukkitTask task = new TimerRunnable().runTaskTimer(plugin, 20, 20);
		
		
		Messager.msgConsole("&aEnable Complete!!!");	
	}
	
	
	
	/**
	 * 
	 * @return The current instance of {@link Main}
	 */
	public static Main getInstance(){
		return plugin;
	}	
	
	
	/**
	 * Prepares scoreboard teams.
	 */
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
	
	/**
	 * Gets you the beginning of the {@link Player}'s data path.
	 * @param player The player to get the data path for.
	 * @return A string with: "players.data.UUID_HERE."
	 */
	public static String getDataPath(Player player) {
		return "players.data." + player.getUniqueId() + ".";
	}

}
