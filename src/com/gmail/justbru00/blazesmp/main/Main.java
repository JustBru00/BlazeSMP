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
import com.gmail.justbru00.blazesmp.commands.Peace;
import com.gmail.justbru00.blazesmp.commands.RequestTeam;
import com.gmail.justbru00.blazesmp.listeners.BlazeSMPAdminMain;
import com.gmail.justbru00.blazesmp.listeners.OnBlockBreak;
import com.gmail.justbru00.blazesmp.listeners.OnDeath;
import com.gmail.justbru00.blazesmp.listeners.OnJoin;
import com.gmail.justbru00.blazesmp.listeners.OnLeave;
import com.gmail.justbru00.blazesmp.listeners.TeamChangeRequestGUI;
import com.gmail.justbru00.blazesmp.scoreboard.EpicScoreboardManager;
import com.gmail.justbru00.blazesmp.utils.Messager;
import com.gmail.justbru00.blazesmp.utils.PluginFile;
import com.gmail.justbru00.blazesmp.utils.cores.Core;
import com.gmail.justbru00.blazesmp.utils.cores.CoreManager;
import com.gmail.justbru00.blazesmp.utils.team.TeamManager;
import com.gmail.justbru00.blazesmp.utils.timestuffs.TimerRunnable;

import io.puharesource.mc.titlemanager.TitleManager;

public class Main extends JavaPlugin implements CommandExecutor {
	
	public static ConsoleCommandSender clogger = Bukkit.getServer().getConsoleSender();
	public static Logger log = Bukkit.getLogger();
	public static boolean debug = true;
	public static String PREFIX = Messager.color("&8[&6Blaze&cSMP&8] &f");
	public static Main plugin;	
	public PluginFile teamRequestsFile = null;
	public PluginFile homesStorage = null;
	

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		return false;
	}

	@Override
	public void onDisable() {
		
		for (Core core : CoreManager.cores) {
			core.reset();
		}
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.setScoreboard(EpicScoreboardManager.sm.getNewScoreboard());
		}
		
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
		
		if (pm.getPlugin("SidebarAPI") == null) {
			Messager.msgConsole("&cSideBarAPI is not installed on this server. Umm... WHAT HAVE YOU DONE. &oinsert funny crash message here");		
			pm.disablePlugin(this);
			return;
		}
		
		
		// Configs
		saveDefaultConfig();
		teamRequestsFile = new PluginFile(this, "teamrequests.yml", "teamrequests.yml");
		homesStorage = new PluginFile(this, "homes.yml", "homes.yml");
		
		readyScoreboardTeams();
		TeamManager.refreshRequestsFromConfig();
		EpicScoreboardManager.init();
		
		
		
		// Register Command Executors 
		getCommand("blazesmpadmin").setExecutor(new BlazeSMPAdmin());
		getCommand("requestteam").setExecutor(new RequestTeam());
		getCommand("event").setExecutor(new Event());
		getCommand("peace").setExecutor(new Peace());
	
		
		// Listeners
		pm.registerEvents(new OnJoin(), plugin);
		pm.registerEvents(new OnLeave(), plugin);
		pm.registerEvents(new BlazeSMPAdminMain(), plugin);
		pm.registerEvents(new TeamChangeRequestGUI(), plugin);	
		pm.registerEvents(new OnBlockBreak(), plugin);
		pm.registerEvents(new OnDeath(), plugin);

		
		@SuppressWarnings("unused")
		BukkitTask task = new TimerRunnable().runTaskTimer(plugin, 20, 20);
		
		Core core = new Core(com.gmail.justbru00.blazesmp.enums.Team.NETHER, 61, 28, -549, Bukkit.getWorld("testworld"));		
		// -147 66 214
		Core core2 = new Core(com.gmail.justbru00.blazesmp.enums.Team.ICE, 61, 27, -411, Bukkit.getWorld("testworld"));	
		
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
		
		EpicScoreboardManager.sm = Bukkit.getScoreboardManager();
		EpicScoreboardManager.sb = EpicScoreboardManager.sm.getMainScoreboard();		
		
		try { // ICE
		 EpicScoreboardManager.ICE = EpicScoreboardManager.sb.registerNewTeam("ICE");		 
		} catch (IllegalArgumentException e) {
			Messager.msgConsole("&cICE team is already made.");
		}
		
		try { // NETHER
			EpicScoreboardManager.NETHER = EpicScoreboardManager.sb.registerNewTeam("NETHER");		 
		} catch (IllegalArgumentException e) {
			Messager.msgConsole("&cNETHER team is already made.");
		}
		
		try { // NONE
			EpicScoreboardManager.NONE = EpicScoreboardManager.sb.registerNewTeam("NONE");		 
		} catch (IllegalArgumentException e) {
			Messager.msgConsole("&cNONE team is already made.");
		}
		
		EpicScoreboardManager.ICE = EpicScoreboardManager.sb.getTeam("ICE");
		EpicScoreboardManager.NETHER = EpicScoreboardManager.sb.getTeam("NETHER");
		EpicScoreboardManager.NONE = EpicScoreboardManager.sb.getTeam("NONE");
		
		 //Ice Setup
		EpicScoreboardManager.ICE.setAllowFriendlyFire(false);
		EpicScoreboardManager.ICE.setPrefix(Messager.color("&b"));
		
		// Nether Setup
		EpicScoreboardManager.NETHER.setAllowFriendlyFire(false);
		EpicScoreboardManager.NETHER.setPrefix(Messager.color("&c"));
		
		// None Setup
		EpicScoreboardManager.NONE.setAllowFriendlyFire(false);
		EpicScoreboardManager.NONE.setPrefix(Messager.color("&7"));		
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
