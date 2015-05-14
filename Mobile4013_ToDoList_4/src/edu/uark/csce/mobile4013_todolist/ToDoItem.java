package edu.uark.csce.mobile4013_todolist;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.text.format.DateFormat;

public class ToDoItem {

	private String task;
	private Date createdDate;
	
	public String getTask() {
		return task;
	}
	
	public Date getCreatedDate(){
		return createdDate;
	}
	
	public ToDoItem(String _task) {
		task = _task;
		createdDate = new Date(java.lang.System.currentTimeMillis());
	}
	
	public ToDoItem(String _task, Date _date) {
		task = _task;
		createdDate = _date;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String dateString = sdf.format(createdDate);
		return "(" + dateString + ")" + task;
	}
}
