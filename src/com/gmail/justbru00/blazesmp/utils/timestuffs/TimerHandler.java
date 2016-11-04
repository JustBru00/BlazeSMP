package com.gmail.justbru00.blazesmp.utils.timestuffs;

import com.gmail.justbru00.blazesmp.customexceptions.TimerDoneException;

public class TimerHandler {

	private static int CURRENT_TIME_LEFT = -1;
	
	public static int getCurrentTimeLeft() {
		return CURRENT_TIME_LEFT;
	}
	
	public static void setCurrentTimeLeft(int timeLeft) {
		CURRENT_TIME_LEFT = timeLeft;
	}
	
	public static void countdownCurrentTime() throws TimerDoneException {
		
		if (CURRENT_TIME_LEFT <= 0) {
			throw new TimerDoneException();
		}
		
		CURRENT_TIME_LEFT = CURRENT_TIME_LEFT - 1;
	}
	
	
}
