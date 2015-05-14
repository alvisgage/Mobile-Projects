package edu.uark.csce.mobile.intentexample;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

@SuppressLint("NewApi")
public class DisplayActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			// Show the Up button in the action bar.
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}

		// Get the message from the intent
		Intent intent = getIntent();
		
		// Bundle with Strings
//		Bundle extras = intent.getExtras();
//		String username = extras.getString("username");
//		String password = extras.getString("password");
		
		// Bundle with Parceables
		myData extras = (myData) intent.getParcelableExtra("parceable.data");
		String username = extras.getUsername();
		String password = extras.getPassword();
		
		TextView usernameView = (TextView) findViewById(R.id.textView_username);
		usernameView.setTextSize(30);
		usernameView.setText(username);
		
		TextView passwordView = (TextView) findViewById(R.id.textView_password);
		passwordView.setTextSize(30);
		passwordView.setText(password);	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
