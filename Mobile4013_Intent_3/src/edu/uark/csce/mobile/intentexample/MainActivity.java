package edu.uark.csce.mobile.intentexample;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	private myData data = new myData();
	public static final String debug_tag = "MainActivity";
	public final static String EXTRA_MESSAGE = "this.MESSAGE";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// setup buttons
		Button expBtn = (Button)findViewById(R.id.activity_button_1);
		expBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				onPressExpButton();
		}});
		Button impBtn = (Button)findViewById(R.id.activity_button_2);
		impBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				onPressImpButton();
		}});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void onPressExpButton() {
		Intent myExpIntent = new Intent(MainActivity.this, DisplayActivity.class);
		
		EditText usernameEdit = (EditText) findViewById(R.id.editText1);
		String username = usernameEdit.getText().toString();
		EditText passwordEdit = (EditText) findViewById(R.id.editText2);
		String password = passwordEdit.getText().toString();
		
		Bundle mBundle = new Bundle();
		
//		mBundle.putString("username", username);
//		mBundle.putString("password", password);
//		mBundle.putExtras(mBundle);
		
		data.setUsername(username);
		data.setPassword(password);
		mBundle.putParcelable("parceable.data", (Parcelable) data);
		myExpIntent.putExtras(mBundle);
		
		startActivity(myExpIntent);
	}
	
	private void onPressImpButton() {
		Uri myUri = Uri.parse("tel:(479)575-6043");
		Intent myImpIntent = new Intent(Intent.ACTION_DIAL, myUri);
		// sanity check
		PackageManager pm = getPackageManager();
		ComponentName cn = myImpIntent.resolveActivity(pm);
		if (cn == null) {
			// no activity, check the google play.
			Log.d(debug_tag, "no activity matched!");
			Uri playUri = Uri.parse("market://search?q=dialer");
			Intent playIntent = new Intent(Intent.ACTION_VIEW).setData(playUri);
			startActivity(playIntent);
		}
		else {
			startActivity(myImpIntent);
		}
	}

}
