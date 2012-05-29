package com.gsbina.android.kitchentimer;

import android.view.View;
import android.view.View.OnClickListener;

public class TimerButtonListener implements OnClickListener {

	private KitchenTimerController mController;

	public TimerButtonListener(KitchenTimerController controller) {
		super();
		mController = controller;
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.btn_start) {
			start();
			v.setEnabled(false);
		} else if (id == R.id.btn_reset) {
			reset();
			v.setEnabled(false);
		}
	}

	private void start() {
		mController.timerStart();
	}

	private void reset() {
		mController.timerReset();
	}
}
