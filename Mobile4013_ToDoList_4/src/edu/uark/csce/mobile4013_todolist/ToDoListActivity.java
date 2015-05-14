package edu.uark.csce.mobile4013_todolist;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.view.Menu;

public class ToDoListActivity extends Activity implements
		NewItemFragment.OnNewItemAddedListener {

//	private ArrayList<String> todoItems;
//	private ArrayAdapter<String> todoAdapter;
	
	// replace ArrayAdapter by our own.
	private ArrayList<ToDoItem> todoItems;
	private ToDoItemAdapter todoAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_to_do_list);

		FragmentManager fm = getFragmentManager();
		ToDoListFragment todoListFragment = (ToDoListFragment) fm
				.findFragmentById(R.id.ToDoListFragment);

//		 todoItems = new ArrayList<String>();
//		 todoAdapter = new ArrayAdapter<String>(
//		 this,
//		 R.layout.todolist_item,
//		 todoItems);

		// initiate our own list and adapter.
		todoItems = new ArrayList<ToDoItem>();
		todoAdapter = new ToDoItemAdapter(this, R.layout.todolist_item,
				todoItems);

		todoListFragment.setListAdapter(todoAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.to_do_list, menu);
		return true;
	}

	@Override
	public void onNewItemAdded(String item) {
//		 todoItems.add(item);
//		 todoAdapter.notifyDataSetChanged();
		
		// update the handler.
		ToDoItem newItem = new ToDoItem(item);
		todoItems.add(0, newItem);
		todoAdapter.notifyDataSetChanged();
	}

}
