package com.gmail.justbru00.blazesmp.utils.timestuffs;

import org.bukkit.scheduler.BukkitRunnable;

public class TimerRunnable extends BukkitRunnable {

	@Override
	public void run() {
		
		
			TimerHandler.countdownCurrentTime();
		
	
	}
	

}
