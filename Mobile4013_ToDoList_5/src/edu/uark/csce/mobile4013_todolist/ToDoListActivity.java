package edu.uark.csce.mobile4013_todolist;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.view.Menu;
import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;

public class ToDoListActivity extends Activity 
implements NewItemFragment.OnNewItemAddedListener, LoaderManager.LoaderCallbacks<Cursor>{

	private ArrayList<ToDoItem> todoItems;
	private ToDoItemAdapter todoAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_to_do_list);
		
		FragmentManager fm = getFragmentManager();
		ToDoListFragment todoListFragment = (ToDoListFragment)fm.findFragmentById(R.id.ToDoListFragment);

		todoItems = new ArrayList<ToDoItem>();
		todoAdapter = new ToDoItemAdapter(this, R.layout.todolist_item, todoItems);
		todoListFragment.setListAdapter(todoAdapter);

		
		getLoaderManager().initLoader(0, null, this);
		
	}
	
	@Override
	public void onResume() {
		
		super.onResume();
		getLoaderManager().restartLoader(0, null, this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.to_do_list, menu);
		return true;
	}

	@Override
	public void onNewItemAdded(String item) {		
//		ToDoItem newItem = new ToDoItem(item);
//		todoItems.add(0, newItem);
//		todoAdapter.notifyDataSetChanged();
		
		
		ContentResolver cr = getContentResolver();
		ContentValues values = new ContentValues();
		
		values.put(ToDoContentProvider.KEY_TASK, item);
		cr.insert(ToDoContentProvider.CONTENT_URI, values);
		getLoaderManager().restartLoader(0, null, this);
		
	}
	
	// loader class

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		CursorLoader loader = new CursorLoader(this, 
				ToDoContentProvider.CONTENT_URI, null, null, null, null);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		int keyTaskIndex = cursor.getColumnIndexOrThrow(ToDoContentProvider.KEY_TASK);
		todoItems.clear();
		
		while (cursor.moveToNext()) {
			ToDoItem newitem = new ToDoItem(cursor.getString(keyTaskIndex));
			todoItems.add(newitem);
		}
		
		todoAdapter.notifyDataSetChanged();
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		// TODO Auto-generated method stub
		
	}

}
