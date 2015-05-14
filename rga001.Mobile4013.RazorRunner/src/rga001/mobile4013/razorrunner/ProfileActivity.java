package rga001.mobile4013.razorrunner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ProfileActivity extends Activity {
	private profile userinfo;
	private TextView username;
	private EditText nameedit;
	private EditText ageedit;
	private Spinner genderedit;
	private EditText weightedit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		
		Intent intent = getIntent();
		userinfo = (profile) intent.getParcelableExtra("parcelable.user");
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		R.array.genders, android.R.layout.simple_spinner_dropdown_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		username = (TextView) findViewById(R.id.lbl_pro_un);
		nameedit = (EditText) findViewById(R.id.edit_pro_name);
		ageedit = (EditText) findViewById(R.id.edit_pro_age);
		genderedit = (Spinner) findViewById(R.id.spinner_gender);
	 	weightedit = (EditText) findViewById(R.id.edit_pro_weight);
		
		genderedit.setAdapter(adapter);
		/////////////////////////////////////////////////////////////
		//set inputs
		username.setText(userinfo.getName());
		nameedit.setText(userinfo.getName());
		ageedit.setText(String.valueOf(userinfo.getAge()));
		genderedit.setSelection(userinfo.getGender().equals(genderedit.getItemAtPosition(0)) ? 0:1);
		weightedit.setText(String.valueOf(userinfo.getWeight()));
		//////////////////////////////////////////////////////////////
		
		Button save = (Button) findViewById(R.id.btn_pro_save);
		save.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				onSaveClick();
			}
		});
	}

	public void onSaveClick(){
		//update current infro before passing back to activity
		updateCurrentInfo();
		//System.out.println(userinfo.getName());
		OverviewActivity.updateProfile(userinfo);
		finishActivity(2);
		finish();
	}
	private void updateCurrentInfo(){
		userinfo.setName(nameedit.getText().toString());
		userinfo.setAge(Integer.parseInt(ageedit.getText().toString()));
		userinfo.setGender(genderedit.getSelectedItem().toString());
		userinfo.setWeight(Double.parseDouble(weightedit.getText().toString()));
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
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
