package com.gsbina.android.kitchentimer;

import android.text.InputFilter;
import android.text.Spanned;

public class SexagesimalFilter implements InputFilter {

	@Override
	public CharSequence filter(CharSequence source, int start, int end,
			Spanned dest, int dstart, int dend) {

		String destStr = dest.toString();
		String newValue = destStr.substring(0, dstart) + source
				+ destStr.substring(dend);

		try {
			if (Integer.parseInt(newValue) < 60) {
				return source;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return "";
	}

}
