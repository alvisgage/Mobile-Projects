package rga001.mobile4013.razorrunner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HistoryActivity extends Activity {
	private workout workoutHistory;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);
		Intent intent = getIntent();

		workoutHistory = (workout) intent.getParcelableExtra("parcelable.selection");
		TextView nameView = (TextView) findViewById(R.id.lbl_name);
		TextView dateView = (TextView) findViewById(R.id.text_his_date);
		TextView durView = (TextView) findViewById(R.id.text_his_dur);
		TextView disView = (TextView) findViewById(R.id.text_his_dis);
		TextView calView = (TextView) findViewById(R.id.text_his_cal);
		/////////////////////////////////////////////////////////////
		//set text fields
		nameView.setText(workoutHistory.toString());
		dateView.setText(workoutHistory.getDate());
		durView.setText(workoutHistory.getDuration());
		disView.setText(String.valueOf(workoutHistory.getDistance()) + " " + workoutHistory.getUnit());
		calView.setText(String.valueOf(workoutHistory.getCaloriesburned()));
		/////////////////////////////////////////////////////////////
		System.out.println(workoutHistory.getDuration());
		Button back = (Button) findViewById(R.id.btn_his_back);
		Button delete = (Button) findViewById(R.id.btn_his_del);
		
		back.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				onBackClick();
			}
		});
		delete.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				onDeleteClick();
			}
		});
	}

	public void onBackClick(){
	//calls overview activity
		finish();
		//finishActivity(1);
	}
	public void onDeleteClick(){
	//calls overview activity and deletes item
		OverviewActivity.deleteWorkout(workoutHistory);
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.history, menu);
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
