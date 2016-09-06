package com.gmail.justbru00.blazesmp.utils;

import java.util.ArrayList;

import com.gmail.justbru00.blazesmp.main.Main;

public class PluginLogger {

	public static void log(String whatToLog) {
		ArrayList<String> oldLog = getLog();
		
		// MM-DD-YYYY HH:MM:SS -> logreason
		
		oldLog.add(TimeGetter.getCurrentTimeStamp() + " -> " + whatToLog);
		
		Main.getInstance().getConfig().set("log", oldLog);
		Main.getInstance().saveConfig();
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<String> getLog() {
		return (ArrayList<String>) Main.getInstance().getConfig().get("log");
	}
}
