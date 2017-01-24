package com.gmail.justbru00.blazesmp.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import com.coloredcarrot.api.sidebar.Sidebar;
import com.coloredcarrot.api.sidebar.SidebarString;
import com.gmail.justbru00.blazesmp.main.Main;
import com.gmail.justbru00.blazesmp.utils.Debug;
import com.gmail.justbru00.blazesmp.utils.Messager;
import com.gmail.justbru00.blazesmp.utils.team.TeamManager;
import com.gmail.justbru00.blazesmp.war.WarManager;

public class EpicScoreboardManager {

	public static Scoreboard sb;
	public static ScoreboardManager sm;
	public static Objective sidebar;
	public static Team ICE;
	public static Team NETHER;
	public static Team NONE;		
	
	
	public static void init() {
		try {
		sidebar = sb.registerNewObjective("sidebar", "dummy");
		} catch (Exception e) {
			Debug.send("A objective with the name: 'sidebar' already exists.");
		}
		sidebar = sb.getObjective("sidebar");
	}
	  
	public static void updateScoreboard() {
		
		
		
		// ICE
		SidebarString iceLine1 = new SidebarString(Messager.color("&aPlayers Online: " + Bukkit.getOnlinePlayers().size()));
		SidebarString iceLine2 = new SidebarString(Messager.color("&b"));
		int iceTeamOnline = 0;
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (TeamManager.getTeam(p) == com.gmail.justbru00.blazesmp.enums.Team.ICE) {
				iceTeamOnline = iceTeamOnline +1;
			}
		}
		
		SidebarString iceLine3 = new SidebarString(Messager.color("&bTeam Online: " + iceTeamOnline));
		SidebarString iceLine4 = new SidebarString(Messager.color("&a"));
		SidebarString iceLine5 = new SidebarString(Messager.color("&6Time Left: "));
		SidebarString iceLine6 = new SidebarString(Messager.color("&6" +WarManager.getTimeLeftFormated()));
		SidebarString iceLine7 = new SidebarString(Messager.color("&c"));
		SidebarString iceLine8 = new SidebarString(Messager.color("&7Objective: "));
		SidebarString iceLine9 = new SidebarString(Messager.color(WarManager.getCurrentObjective()));
		
		Sidebar iceSideBar = new Sidebar(Messager.color(Main.PREFIX), Main.getInstance(), 60, iceLine1, iceLine2, iceLine3, iceLine4, iceLine5, iceLine6, iceLine7, iceLine8, iceLine9);
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (TeamManager.getTeam(p) == com.gmail.justbru00.blazesmp.enums.Team.ICE) {
				iceSideBar.showTo(p);
			}
		}
		
		// NETHER
				SidebarString netherLine1 = new SidebarString(Messager.color("&aPlayers Online: " + Bukkit.getOnlinePlayers().size()));
				SidebarString netherLine2 = new SidebarString(Messager.color("&a"));
				int netherTeamOnline = 0;
				for (Player p : Bukkit.getOnlinePlayers()) {
					if (TeamManager.getTeam(p) == com.gmail.justbru00.blazesmp.enums.Team.NETHER) {
						netherTeamOnline = netherTeamOnline +1;
					}
				}
				
				SidebarString netherLine3 = new SidebarString(Messager.color("&cTeam Online: " + netherTeamOnline));
				SidebarString netherLine4 = new SidebarString(Messager.color("&b"));
				SidebarString netherLine5 = new SidebarString(Messager.color("&6Time Left: "));
				SidebarString netherLine6 = new SidebarString(Messager.color("&6" +WarManager.getTimeLeftFormated()));
				SidebarString netherLine7 = new SidebarString(Messager.color("&c"));
				SidebarString netherLine8 = new SidebarString(Messager.color("&7Objective: "));
				SidebarString netherLine9 = new SidebarString(Messager.color(WarManager.getCurrentObjective()));
				
				Sidebar netherSideBar = new Sidebar(Messager.color(Main.PREFIX), Main.getInstance(), 60, netherLine1, netherLine2, netherLine3, netherLine4, netherLine5, netherLine6, netherLine7, netherLine8, netherLine9);
				
				for (Player p : Bukkit.getOnlinePlayers()) {
					if (TeamManager.getTeam(p) == com.gmail.justbru00.blazesmp.enums.Team.NETHER) {
						netherSideBar.showTo(p);
					}
				}
	}
	
	
}
