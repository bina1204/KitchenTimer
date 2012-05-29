package com.gsbina.android.kitchentimer;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputFilter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class KitchenTimerActivity extends Activity implements
		KitchenTimerController {

	private final KitchenTimerActivity mSelf = this;

	private EditText mHour;
	private EditText mMinute;
	private EditText mSecond;
	private Button mStart;
	private Button mReset;

	private KitchenTimer mTimer;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		setupView();
	}

	private void setupView() {
		InputFilter[] filters = new InputFilter[] { new SexagesimalFilter() };

		mHour = (EditText) findViewById(R.id.hour);
		mMinute = (EditText) findViewById(R.id.minute);
		mMinute.setFilters(filters);
		mSecond = (EditText) findViewById(R.id.second);
		mSecond.setFilters(filters);
		mStart = (Button) findViewById(R.id.btn_start);
		mReset = (Button) findViewById(R.id.btn_reset);

		TimerButtonListener listener = new TimerButtonListener(mSelf);
		mStart.setOnClickListener(listener);
		mReset.setOnClickListener(listener);
	}

	@Override
	public void updateTime(int hour, int minute, int second) {
		setInt(mHour, hour);
		setInt(mMinute, minute);
		setInt(mSecond, second);
	}

	@Override
	public void finishTime() {
		updateTime(0, 0, 0);
		Toast.makeText(mSelf, "finish", 0).show();
	}

	@Override
	public void timerStart() {
		mReset.setEnabled(true);

		setEditable(mHour, false);
		setEditable(mMinute, false);
		setEditable(mSecond, false);

		int hour = getInt(mHour);
		int minute = getInt(mMinute);
		int second = getInt(mSecond);
		mTimer = new KitchenTimer(mSelf, hour, minute, second);
		mTimer.start();
	}

	@Override
	public void timerReset() {
		mStart.setEnabled(true);

		setEditable(mHour, true);
		setEditable(mMinute, true);
		setEditable(mSecond, true);

		mTimer.reset();
	}

	private int getInt(EditText et) {
		String text = et.getText().toString();
		try {
			return Integer.parseInt(text);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return 0;
	}

	private void setInt(EditText et, int value) {
		et.setText(String.valueOf(value));
	}

	private void setEditable(EditText et, boolean editable) {
		et.setFocusable(editable);
		et.setFocusableInTouchMode(editable);
		et.setClickable(editable);
	}
}