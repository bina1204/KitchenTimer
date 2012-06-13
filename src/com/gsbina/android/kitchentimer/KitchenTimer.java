package com.gsbina.android.kitchentimer;

import java.util.concurrent.TimeUnit;

import android.os.CountDownTimer;

public class KitchenTimer extends CountDownTimer {

	private static final long CD_INTERVAL = TimeUnit.MILLISECONDS.toMillis(100);

	private KitchenTimerController mController;
	private final int mSetHour;
	private final int mSetMinute;
	private final int mSetSecond;

	public KitchenTimer(KitchenTimerController controller, int hour,
			int minute, int second) {
		super(TimeUnit.HOURS.toMillis(hour) + TimeUnit.MINUTES.toMillis(minute)
				+ TimeUnit.SECONDS.toMillis(second), CD_INTERVAL);
		mController = controller;
		mSetHour = hour;
		mSetMinute = minute;
		mSetSecond = second;

		mController.updateTime(mSetHour, mSetMinute, mSetSecond);
	}

	@Override
	public void onFinish() {
		mController.finishTime();
	}

	@Override
	public void onTick(long millisUntilFinished) {
		// 1ïbñ¢ñûÇÕåJÇËè„Ç∞Çƒï\é¶Ç∑ÇÈÇΩÇﬂÅA1000ms â¡éZÇ∑ÇÈ
		millisUntilFinished += 1000;

		long seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);
		int hour = (int) (seconds / 3600);
		seconds %= 3600;
		int minute = (int) (seconds / 60);
		seconds %= 60;
		int second = (int) seconds;
		mController.updateTime(hour, minute, second);
	}

	public void reset() {
		mController.updateTime(mSetHour, mSetMinute, mSetSecond);
		cancel();
	}
}
