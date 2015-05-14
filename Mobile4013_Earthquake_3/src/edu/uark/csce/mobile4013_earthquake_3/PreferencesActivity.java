package edu.uark.csce.mobile4013_earthquake_3;

import android.os.Bundle;
import android.preference.PreferenceActivity;

@SuppressWarnings("deprecation")
public class PreferencesActivity extends PreferenceActivity {
	
	public static final String PREF_MIN_MAG = "PREF_MIN_MAG";
	public static final String PREF_UPDATE_FREQ = "PREF_UPDATE_FREQ";
	public static final String PREF_AUTO_UPDATE = "PREF_AUTO_UPDATE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.userpreferences);
	}

}
