package edu.uark.csce.mobile4013_todolist;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ToDoItemAdapter extends ArrayAdapter<ToDoItem> {

	int resource;
	
	public ToDoItemAdapter(Context context, 
			int resource, List<ToDoItem> items) {
		super(context, resource, items);
		this.resource = resource;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout todoView;
		ToDoItem item = getItem(position);
		
		String taskString = item.getTask();
		Date createdDate = item.getCreatedDate();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String dateString = sdf.format(createdDate);
		
		if (convertView == null) {
			todoView = new LinearLayout(getContext());
			String inflater = Context.LAYOUT_INFLATER_SERVICE;
			LayoutInflater lifr = (LayoutInflater)getContext().getSystemService(inflater);
			lifr.inflate(resource, todoView, true);
		}
		else {
			todoView = (LinearLayout)convertView;
		}
		
		TextView dateView = (TextView)todoView.findViewById(R.id.rowDate);
		TextView taskView = (TextView)todoView.findViewById(R.id.row);
		
		dateView.setText(dateString);
		taskView.setText(taskString);
		
		return todoView;
	}
	
}
