package org.scj.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.scj.R;
import org.scj.ScjApp;
import org.scj.URLHelper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class TimeLineFragment extends ListFragment {
	private static final String TAG = "TimeLineFragment";
	
	private ScjApp mApp;
	private RequestQueue mRequestQueue;
	private TimeLineAdapter adapter;
	private String token;
	JSONObject data;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_timeline, container, false);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		mApp = ScjApp.getApp();
		mRequestQueue = mApp.getRequestQueue();

		mRequestQueue.add(
				new JsonObjectRequest(Request.Method.GET,
						getTimeLineUri(token), null,
						new Response.Listener<JSONObject>() {
							@Override
							public void onResponse(JSONObject response) {
								Log.d(TAG, response.toString());

								data = response;
								adapter = new TimeLineAdapter();
								TimeLineFragment.this.setListAdapter(adapter);
							}

						}, new Response.ErrorListener() {
							@Override
							public void onErrorResponse(VolleyError error) {
								Log.wtf(TAG, "fail!", error);
							}
						})).setTag(TAG);
		
		
		super.onCreate(savedInstanceState);
	}
	
	static class Holder {
		TextView userName;
		TextView text;
	}
	
	private class TimeLineAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 2;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			Holder holder = null;
			if (convertView == null) {
				holder = new Holder();
				
				convertView = LayoutInflater.from(getActivity()).inflate(R.layout.weibo, null);
				holder.userName = (TextView) convertView.findViewById(R.id.username);
				holder.text = (TextView) convertView.findViewById(R.id.text);
				
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}
			
			try {
				holder.userName.setText(data.getString("total_number"));
				holder.text.setText(data.getString("statuses"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return convertView;
		}
		
	}
	
	private String getTimeLineUri(String token) {
		return new StringBuilder(URLHelper.friends_timeline)
				.append("?access_token=").append(token).toString();
		// .append("&since_id=authorization_code")
		// .append("&max_id=")
		// .append("&count=")
		// .append("&page=authorization_code")
		// .append("&base_app=")
		// .append("&feature=")
		// .append("&trim_user=authorization_code").toString();
	}
	
	public void setToken(String token) {
		this.token = token;
	}
}
