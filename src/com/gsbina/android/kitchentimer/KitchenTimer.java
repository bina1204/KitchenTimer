package com.gsbina.android.kitchentimer;

import java.util.concurrent.TimeUnit;

import android.os.CountDownTimer;
import android.util.Log;

public class KitchenTimer extends CountDownTimer {

	private static final long CD_INTERVAL = TimeUnit.MILLISECONDS.toMillis(100);
	private int mSecondInterval = 0;

	private KitchenTimerController mController;
	private final int mSetHour;
	private final int mSetMinute;
	private final int mSetSecond;
	private int mHour;
	private int mMinute;
	private int mSecond;

	public KitchenTimer(KitchenTimerController controller, int hour, int minute,
			int second) {
		super(TimeUnit.HOURS.toMillis(hour) + TimeUnit.MINUTES.toMillis(minute)
				+ TimeUnit.SECONDS.toMillis(second), CD_INTERVAL);
		mController = controller;
		mSetHour = mHour = hour;
		mSetMinute = mMinute = minute;
		mSetSecond = mSecond = second;

		mController.updateTime(mHour, mMinute, mSecond);
	}

	@Override
	public void onFinish() {
		// TODO èIóπ
		mController.finishTime();
	}

	@Override
	public void onTick(long millisUntilFinished) {
		if (++mSecondInterval < 10) {
			Log.d("kt", "time : " + millisUntilFinished);
			return;
		}

		mSecondInterval = 0;

		Log.d("KitchenTimer", "time : " + millisUntilFinished);
		if (--mSecond < 0 && (mMinute > 0 || mHour > 0)) {
			mSecond = 59;
			if (--mMinute < 0 && mHour > 0) {
				mMinute = 59;
				--mHour;
			}
		}

		mController.updateTime(mHour, mMinute, mSecond);
	}

	public void reset() {
		mController.updateTime(mSetHour, mSetMinute, mSetSecond);
		cancel();
	}
}
