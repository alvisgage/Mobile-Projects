package rga001.mobile4013.razorrunner;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ListView;

public class workoutListFragment extends ListFragment{
	private workout selection;
	@Override
	public void onListItemClick(ListView l, View v, int position, long id){
		Intent intent = new Intent(getActivity().getApplicationContext(), HistoryActivity.class);
		selection = (workout) l.getItemAtPosition(position);
		selection.setPosition(position);
		//need to set position in order to possibly delete later
		
		Bundle mBundle = new Bundle();
		mBundle.putParcelable("parcelable.selection", (Parcelable) selection);
		intent.putExtras(mBundle);
		
		startActivity(intent);
	}
}
