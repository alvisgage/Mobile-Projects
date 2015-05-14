package rga001.mobile4013.razorrunner;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class workoutAdapter extends ArrayAdapter<workout>{
	int resource;
	
	public workoutAdapter(Context context, int resource, List<workout> workouts){
		super(context, resource, workouts);
		this.resource = resource;
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout todoView;
		workout item = getItem(position);
		
		String name = item.toString();
		String date = item.getDate();
		String distance = String.valueOf(item.getDistance()) + " " + item.getUnit();
		if (convertView == null) {
			todoView = new LinearLayout(getContext());
			String inflater = Context.LAYOUT_INFLATER_SERVICE;
			LayoutInflater lifr = (LayoutInflater)getContext().getSystemService(inflater);
			lifr.inflate(resource, todoView, true);
		}
		else {
			todoView = (LinearLayout)convertView;
		}
		//set views to display row information
		TextView nameView = (TextView)todoView.findViewById(R.id.rowName);
		TextView dateView = (TextView)todoView.findViewById(R.id.date); 
		TextView disView = (TextView)todoView.findViewById(R.id.rowDistance);
		
		disView.setText(distance);
		nameView.setText(name);
		dateView.setText(date);
		return todoView;
	}

}
