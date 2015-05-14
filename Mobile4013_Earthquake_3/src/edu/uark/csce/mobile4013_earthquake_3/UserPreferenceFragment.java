package edu.uark.csce.mobile4013_earthquake_3;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class UserPreferenceFragment extends PreferenceFragment {

	public UserPreferenceFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.userpreferences);
	}

}
