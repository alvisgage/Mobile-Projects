package rga001.csce.uark.edu.mobile4013.razorrunner;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Overview_activity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_overview);
		//get all workouts to display
		//asignment 1: fill listview with placeholder info
		
		
		//fill username with placeholder		
		//profile button opens profile activity
		Button profile_btn = (Button)findViewById(R.id.button_un);
		profile_btn.setText("Gage Alvis");
		profile_btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				onPressProfileButton();
			}
		});
		
		//new workout button opens workout activity
		Button workout_btn = (Button)findViewById(R.id.button_new_workout);
		workout_btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				onPressWorkButton();
		}});
	}
	public void onPressWorkButton(){
		//explicit intent
		Intent workout_intent =  new Intent(Overview_activity.this, Workout_activity.class);
		startActivity(workout_intent);
	}
	public void onPressProfileButton(){
		//explicit intent
		Intent profile_intent = new Intent(Overview_activity.this, Profile_activity.class);
		startActivity(profile_intent);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.overview, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
}
