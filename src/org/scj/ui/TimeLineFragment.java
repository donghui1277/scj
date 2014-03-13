package org.scj.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.scj.R;
import org.scj.ScjApp;
import org.scj.URLHelper;
import org.scj.bean.StatusBean;
import org.scj.bean.UserBean;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TimeLineFragment extends ListFragment implements OnScrollListener {
	private static final String TAG = "TimeLineFragment";
	
	private ScjApp mApp;
	private RequestQueue mRequestQueue;
	private TimeLineAdapter adapter;
	private String token;
	private ListView listView;
	private int listPositon;
	JSONObject data;
	ArrayList<StatusBean> list = new ArrayList<StatusBean>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		listView = (ListView) inflater.inflate(R.layout.fragment_timeline, container, false);
		listView.setOnScrollListener(TimeLineFragment.this);
		return listView;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		mApp = ScjApp.getApp();
		mRequestQueue = mApp.getRequestQueue();
		
		listPositon = 0;
		
		mRequestQueue.add(
				new JsonObjectRequest(Request.Method.GET,
						getTimeLineUri(token), null,
						new Response.Listener<JSONObject>() {
							@Override
							public void onResponse(JSONObject response) {
								Log.d(TAG, response.toString());
								
								Gson gson = new Gson();
								Map<String, Object> map = gson.fromJson(response.toString(), new TypeToken<Map<String, Object>>(){}.getType());
								
								List<Object> statusList = (List<Object>) map.get("statuses");

								Map map2 = null;
								Map map3 = null;
								Map map4 = null;
								for(int i = 0; i < statusList.size() ; i++)
								{
									StatusBean status = new StatusBean();
									StatusBean retweetStatus = new StatusBean();
									UserBean user = new UserBean();
									map2 = (Map) statusList.get(i);
									System.out.println(map2);
									map3 = (Map) map2.get("user");
									map4 = (Map) map2.get("retweeted_status");
									user.setScreen_name(map3.get("screen_name").toString());
									if (map4 != null) {
										retweetStatus.setText(map4.get("text").toString());
									}
									status.setText(map2.get("text").toString());
									status.setRetweeted_status(retweetStatus);
									status.setUser(user);
									list.add(status);
								}
								
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
		TextView retweet;
	}
	
	private class TimeLineAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
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
				holder.retweet = (TextView) convertView.findViewById(R.id.retweet);
				
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}
			
			holder.userName.setText(list.get(position).getUser().getScreen_name());
			holder.text.setText(list.get(position).getText());
			if (list.get(position).getRetweeted_status().getText() != null) {
				holder.retweet.setVisibility(View.VISIBLE);
				holder.retweet.setText(list.get(position).getRetweeted_status().getText());
			} else {
				holder.retweet.setVisibility(View.GONE);
			}
			
			return convertView;
		}
		
	}
	
	private String getTimeLineUri(String token) {
		return new StringBuilder(URLHelper.friends_timeline)
				.append("?access_token=").append(token)
		// .append("&since_id=authorization_code")
		// .append("&max_id=")
		 .append("&count=50").toString();
		// .append("&page=authorization_code")
		// .append("&base_app=")
		// .append("&feature=")
		// .append("&trim_user=authorization_code").toString();
	}
	
	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onScrollStateChanged" + scrollState);
//		switch (scrollState) {
//		case SCROLL_STATE_TOUCH_SCROLL:
//			TimeLineFragment.this.getActivity().getActionBar().hide();
//			listView.scrollBy(0, -48);
//			break;
//		case SCROLL_STATE_FLING:
//			TimeLineFragment.this.getActivity().getActionBar().hide();
//			break;
//		default:
//			break;
//		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onScroll" + firstVisibleItem + " " + listPositon);
		if (firstVisibleItem > listPositon) {
			TimeLineFragment.this.getActivity().getActionBar().hide();
		} else if (firstVisibleItem < listPositon) {
			TimeLineFragment.this.getActivity().getActionBar().show();
		}
		listPositon = firstVisibleItem;
	}
}
