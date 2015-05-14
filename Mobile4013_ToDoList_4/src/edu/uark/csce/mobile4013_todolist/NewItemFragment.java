package edu.uark.csce.mobile4013_todolist;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class NewItemFragment extends Fragment {
		
	public interface OnNewItemAddedListener {
		public void onNewItemAdded(String item);
	}
	
	private OnNewItemAddedListener onNewItemAddedListener;

//	public NewItemFragment() {
//		// Required empty public constructor
//	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View myView = inflater.inflate(R.layout.new_item_fragment, container, false);
		final EditText myEditText = (EditText)myView.findViewById(R.id.editText1);
		myEditText.setOnKeyListener(new View.OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (event.getAction() == KeyEvent.ACTION_DOWN)
					if ((keyCode == KeyEvent.KEYCODE_DPAD_CENTER) || (keyCode == KeyEvent.KEYCODE_ENTER)) { 
						String newItem = myEditText.getText().toString();
						onNewItemAddedListener.onNewItemAdded(newItem);
						myEditText.setText("");
						return true;
					}
				return false;
			}
		});
		return myView;
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		try {
			onNewItemAddedListener = (OnNewItemAddedListener)activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + 
					" must implement OnNewItemAddedListener.");
		}
	}

}
