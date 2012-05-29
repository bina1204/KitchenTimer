package com.gsbina.android.kitchentimer;

public interface KitchenTimerController {

	public void updateTime(int hour, int minute, int second);

	public void finishTime();

	public void timerStart();

	public void timerReset();
}
