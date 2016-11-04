package com.gmail.justbru00.blazesmp.utils;

import java.util.ArrayList;

import com.gmail.justbru00.blazesmp.main.Main;
import com.gmail.justbru00.blazesmp.utils.timestuffs.TimeGetter;

public class PluginLogger {

	public static void log(String whatToLog) {
		ArrayList<String> oldLog = getLog();
		
		// MM-DD-YYYY HH:MM:SS -> logreason
		String log = TimeGetter.getCurrentTimeStamp() + " -> " + whatToLog;
		oldLog.add(log);
		
		Main.getInstance().getConfig().set("log", oldLog);
		Main.getInstance().saveConfig();
		Debug.send("Attempted to log: " + log);
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<String> getLog() {
		return (ArrayList<String>) Main.getInstance().getConfig().get("log");
	}
}
