package com.gmail.justbru00.blazesmp.utils.timestuffs;

import com.gmail.justbru00.blazesmp.utils.Messager;

import io.puharesource.mc.titlemanager.api.ActionbarTitleObject;
import io.puharesource.mc.titlemanager.api.TitleObject;

public class TimerHandler {

	private static int CURRENT_TIME_LEFT = -1;
	public static TimerMode timerMode = TimerMode.BLAZESMP;
	public static int[] BATTLEDOME_TIMES = { 60, 900, 60 };
	public static String[] BATTLEDOME_STAGES = { "&6WARMUP", "&cPREPARE", "&aFIGHT" };
	public static int CURRENT_PHASE_ID = -1;

	public static int getCurrentTimeLeft() {
		return CURRENT_TIME_LEFT;
	}

	public static void setCurrentTimeLeft(int timeLeft) {
		CURRENT_TIME_LEFT = timeLeft;
	}

	public static void countdownCurrentTime() {
		if (CURRENT_TIME_LEFT < 0) {
			return;
		}

		// TODO Update Scoreboard

		if (timerMode == TimerMode.BATTLEDOME) {

			if (CURRENT_PHASE_ID == -1) {
				TitleObject title = new TitleObject(Messager.color("&aGame will start"),
						Messager.color("&ain 60 seconds"));
				title.broadcast();

				// TODO Send to scoredboard
				setCurrentTimeLeft(BATTLEDOME_TIMES[0]);
				CURRENT_PHASE_ID = 0;

			} else if (CURRENT_PHASE_ID == 0 && CURRENT_TIME_LEFT == 0) {
				setCurrentTimeLeft(BATTLEDOME_TIMES[1]);

				// TODO Update Scoreboard

				TitleObject title = new TitleObject("", Messager.color("&cPREPARE!!!"));
				title.broadcast();

				CURRENT_PHASE_ID = 1;
			} else if (CURRENT_TIME_LEFT == 0 && CURRENT_PHASE_ID == 1) {
				setCurrentTimeLeft(BATTLEDOME_TIMES[2]);

				// TODO update scoreboard

				TitleObject title = new TitleObject(Messager.color("&aFighting begins in"),
						Messager.color("&a60 seconds"));
				title.broadcast();

				CURRENT_PHASE_ID = 2;
			} else if (CURRENT_PHASE_ID == 2 && CURRENT_TIME_LEFT == 0) {
				TitleObject title = new TitleObject(Messager.color("&4&lFIGHT"), "");
				title.broadcast();
				CURRENT_PHASE_ID = -1;
				setCurrentTimeLeft(-1);
			}

		} else if (timerMode == TimerMode.BLAZESMP) {

		} else if (timerMode == TimerMode.NORMAL) {

		}		

		if (CURRENT_PHASE_ID != -1) {
			//if (CURRENT_TIME_LEFT % 30 == 0) {
				//Messager.sendBC("&6Timer has &c" + CURRENT_TIME_LEFT + " &6seconds remaining in the stage "	+ BATTLEDOME_STAGES[CURRENT_PHASE_ID]);
			//	ActionbarTitleObject ab = new ActionbarTitleObject(Messager.color("&c" + CURRENT_TIME_LEFT + " &6seconds remaining in the stage "	+ BATTLEDOME_STAGES[CURRENT_PHASE_ID]));
			//	ab.broadcast();
			//} else if (CURRENT_TIME_LEFT < 11) {
				//Messager.sendBC("&6Timer has &c" + CURRENT_TIME_LEFT + " &6seconds remaining in the stage "	+ BATTLEDOME_STAGES[CURRENT_PHASE_ID]);
			//	ActionbarTitleObject ab = new ActionbarTitleObject(Messager.color("&c" + CURRENT_TIME_LEFT + " &6seconds remaining in the stage "	+ BATTLEDOME_STAGES[CURRENT_PHASE_ID]));
			//	ab.broadcast();
			//}
			
			ActionbarTitleObject ab = new ActionbarTitleObject(Messager.color("&c" + CURRENT_TIME_LEFT + " &6seconds remaining in the stage "	+ BATTLEDOME_STAGES[CURRENT_PHASE_ID]));
			ab.broadcast();
		}

		CURRENT_TIME_LEFT = CURRENT_TIME_LEFT - 1;
	}

}
