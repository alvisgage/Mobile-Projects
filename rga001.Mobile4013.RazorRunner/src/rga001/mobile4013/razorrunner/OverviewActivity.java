package rga001.mobile4013.razorrunner;


import java.util.ArrayList;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import rga001.mobile4013.razorrunner.*;

public class OverviewActivity extends Activity implements NewWorkoutFragment.OnNewItemAddedListener{

	private static ArrayList<workout> workouts;
	private static workoutAdapter workoutAdapter;
	public static profile user;
	private workout w1, w2, w3;
	public final int PICK_REQUEST = 1;
	private static TextView lbl_username;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_overview);
		
		lbl_username = (TextView) findViewById(R.id.text_cal);

		FragmentManager fm = getFragmentManager();
		workoutListFragment workoutFragment = (workoutListFragment) fm.findFragmentById(R.id.workoutListFragment);
		
		workouts = new ArrayList<workout>();
		workoutAdapter = new workoutAdapter(this, R.layout.workout_item, workouts);
		workoutFragment.setListAdapter(workoutAdapter);
		
		//////////////////////////////////////////////////////////////
		//get profile information to pass to profile
		user = new profile("Gage Alvis", 23, "Male", 170.0);
		/////////////////////////////////////////////////////////////
		
		lbl_username.setText(user.toString());
		Button new_workout = (Button)findViewById(R.id.button_new_workout);
		lbl_username.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				onProfileBtnClick();
			}
		});
//		to_profile.setOnClickListener(new OnClickListener() {
//			public void onClick(View v) {
//				onProfileBtnClick();
//			}
//		});
		//removed profile button to set text to link to profile
		new_workout.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				onNewWorkBtnClick();
			}
		});
	}

	public static void deleteWorkout(workout deleteme){
		try{
			//System.out.println(deleteme.getPosition());
			workouts.remove(deleteme.getPosition());}
		catch(Exception e){e.printStackTrace();}
			
			workoutAdapter.notifyDataSetChanged();

	}
	
	public static void updateProfile(profile newuserinfo){
		try{
			user.setName(newuserinfo.getName());
			user.setAge(newuserinfo.getAge());
			user.setGender(newuserinfo.getGender());
			user.setWeight(newuserinfo.getWeight());
		}catch(Exception e){
			
		}
		lbl_username.setText(user.getName());
	}

	public static void addNewWorkout(workout newworkout){
		try{
			workouts.add(newworkout);
		}catch(Exception e){
			
		}
		workoutAdapter.notifyDataSetChanged();
	}
	public void onProfileBtnClick(){
		//send profile name
		Intent intent = new Intent(OverviewActivity.this, ProfileActivity.class);
		Bundle mBundle = new Bundle();
		mBundle.putParcelable("parcelable.user", (Parcelable) user);
		intent.putExtras(mBundle);
		startActivityForResult(intent, PICK_REQUEST);
	}
	public void onNewWorkBtnClick(){
		Intent intent = new Intent(OverviewActivity.this, WorkoutActivity.class);
		startActivityForResult(intent, PICK_REQUEST);
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
	
	@Override
	public void onNewItemAdded(String item){
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    // Check which request we're responding to
	    setResult(Activity.RESULT_OK);
	}
}
