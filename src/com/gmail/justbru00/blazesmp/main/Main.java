package com.gmail.justbru00.blazesmp.main;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.justbru00.blazesmp.commands.BlazeSMPAdmin;
import com.gmail.justbru00.blazesmp.utils.Messager;

public class Main extends JavaPlugin implements CommandExecutor {
	
	public static ConsoleCommandSender clogger = Bukkit.getServer().getConsoleSender();
	public static Logger log = Bukkit.getLogger();
	public static boolean debug = false;
	public static final String PREFIX = Messager.color("&8[&6Blaze&cSMP&8] &f");
	public static Main plugin;

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
		
		// Register Command Executors 
		getCommand("blazesmpadmin").setExecutor(new BlazeSMPAdmin());
		
	}
	
	public static Main getInstance(){
		return plugin;
	}

}
