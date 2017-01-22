package com.gmail.justbru00.blazesmp.utils.timestuffs;

import org.bukkit.scheduler.BukkitRunnable;

import com.gmail.justbru00.blazesmp.war.WarManager;

public class TimerRunnable extends BukkitRunnable {

	@Override
	public void run() {
		
		
			TimerHandler.countdownCurrentTime();
			WarManager.everySecond();
	
	}
	

}
