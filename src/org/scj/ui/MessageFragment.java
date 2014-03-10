package org.scj.ui;

import org.scj.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class MessageFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(org.scj.R.layout.fragment_messsage, container,
				false);
		ListView timelineList = (ListView) rootView.findViewById(R.id.timeline_list);

		return rootView;
	}
}
