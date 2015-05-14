package rga001.mobile4013.razorrunner;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;

public class WorkoutActivity extends Activity implements OnClickListener {
	long time = 0;
	Button startTimer,save,cancel;
	Chronometer timer;
	private EditText workoutname;
	private TextView calories;
	private workout workout;
	private boolean resume = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_workout);

		
		//////////////////////////////////////////////////////
		//set views/edits/btns
		startTimer = (Button)findViewById(R.id.button_timer);
		timer = (Chronometer)findViewById(R.id.timer);
		save = (Button)findViewById(R.id.button_workout_save);
		cancel = (Button)findViewById(R.id.button_workout_cancel);
		workoutname = (EditText)findViewById(R.id.edit_workout_name);
		calories = (TextView)findViewById(R.id.text_cal);
		//////////////////////////////////////////////////////
		startTimer.setOnClickListener(this);
		workoutname.setText("Workout_" + DateFormat.format("yyyy_mm_dd", Calendar.getInstance().getTime()));
		save.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				onSaveClick();
			}
		});
		cancel.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				onCancelClick();
			}
		});
	}
	
	public void onSaveClick(){
		//create new workout
		//call addworkout method
		//finish activity
		workout = new workout();
		workout.setName(workoutname.getText().toString());
		workout.setCaloriesBurned(Double.parseDouble(calories.getText().toString()));
		workout.setDuration(time);
		//System.out.println(time);
		OverviewActivity.addNewWorkout(workout);
		finishActivity(3);
		finish();
	}
	
	public void onCancelClick(){
		//finish activity
		finishActivity(3);
		finish();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.workout, menu);
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
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String button_text = (String) startTimer.getText();
		switch(v.getId()){
		case R.id.button_timer:
			if (button_text.equals("Start")){
				if (!resume){
				timer.setBase(SystemClock.elapsedRealtime() + time);}				
				timer.start();
				startTimer.setText("Pause");
			}else{
				time = SystemClock.elapsedRealtime() - timer.getBase();
				timer.stop();
				startTimer.setText("Start");
				resume = true;
			}
			break;
		}
	}
}
