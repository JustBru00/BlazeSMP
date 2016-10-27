package com.gmail.justbru00.blazesmp.customexceptions;

public class TimerDoneException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6575450330354306102L;

	public TimerDoneException() {
	
	}
	
	public TimerDoneException(String msg) {
		super(msg);
	}
	
}
