package org.scj.ui;

import org.json.JSONObject;
import org.scj.R;
import org.scj.URLHelper;
import org.scj.bean.ListStatusBean;
import org.scj.data.RequestManage;
import org.scj.util.DateHelper;

import android.app.ListFragment;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

public class TimeLineFragment extends ListFragment implements OnScrollListener {
	private static final String TAG = "TimeLineFragment";
	
	private RequestQueue mRequestQueue;
	private TimeLineAdapter adapter;
	private String token;
	private ListView listView;
	ListStatusBean listStatus;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_timeline, container, false);
		listView = (ListView) view.findViewById(android.R.id.list);
		listView.setOnScrollListener(TimeLineFragment.this);
		return view;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		mRequestQueue = RequestManage.getRequestQueue();
		
		mRequestQueue.add(
				new JsonObjectRequest(Request.Method.GET,
						getTimeLineUri(token), null,
						new Response.Listener<JSONObject>() {
							@Override
							public void onResponse(JSONObject response) {
								Gson gson = new Gson();
								listStatus = gson.fromJson(response.toString(), ListStatusBean.class);
								
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
		TextView create_at;
		TextView source;
		ImageView status_pic;
		TextView retweet;
		ImageView retweet_status_pic;
		LinearLayout retweet_status_layout;
	}
	
	private class TimeLineAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return listStatus.getStatuses().size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return listStatus.getStatuses().get(position);
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
				holder.create_at = (TextView) convertView.findViewById(R.id.create_at);
				holder.source = (TextView) convertView.findViewById(R.id.source);
				holder.status_pic = (ImageView) convertView.findViewById(R.id.status_pic);
				holder.retweet = (TextView) convertView.findViewById(R.id.retweet);
				holder.retweet_status_pic = (ImageView) convertView.findViewById(R.id.retweet_status_pic);
				holder.retweet_status_layout = (LinearLayout) convertView.findViewById(R.id.retweet_status_layout);
				
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}
			
			holder.userName.setText(listStatus.getStatuses().get(position).getUser().getScreen_name());
			holder.text.setText(listStatus.getStatuses().get(position).getText());
			
			String create_at_string = listStatus.getStatuses().get(position).getCreated_at();
			String source_string = listStatus.getStatuses().get(position).getSource();
			
			DateHelper.Format(create_at_string);
			
			holder.create_at.setText(DateHelper.Format(create_at_string));
			holder.source.setText(Html.fromHtml(source_string));
			
			if (listStatus.getStatuses().get(position).getPic_urls().size() > 0) {
				ImageRequest imgRequest=new ImageRequest(listStatus.getStatuses().get(position).getPic_urls().get(0).thumbnail_pic, new Response.Listener<Bitmap>() {
					@Override
					public void onResponse(Bitmap arg0) {
						// TODO Auto-generated method stub
						holder.status_pic.setImageBitmap(arg0);
					}
				}, 300, 200, Config.ARGB_8888, new ErrorListener(){
					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub
						
					}			
				});
				mRequestQueue.add(imgRequest);
			}
			
			
			if (listStatus.getStatuses().get(position).getRetweeted_status() != null) {
//				holder.retweet_username.setText("@" + listStatus.getStatuses().get(position).getRetweeted_status().getUser().getScreen_name() + ":");
				holder.retweet.setVisibility(View.VISIBLE);
				holder.retweet.setText("@" + listStatus.getStatuses().get(position).getRetweeted_status().getUser().getScreen_name() + ":" + listStatus.getStatuses().get(position).getRetweeted_status().getText());
				holder.retweet_status_layout.setVisibility(View.VISIBLE);
			} else {
				holder.retweet_status_layout.setVisibility(View.GONE);
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
//		Log.d(TAG, "onScrollStateChanged" + scrollState);
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
//		Log.d(TAG, "onScroll" + firstVisibleItem + " " + listPositon);
//		if (firstVisibleItem > listPositon) {
//			TimeLineFragment.this.getActivity().getActionBar().hide();
//			firstVisibleItem--;
//		} else if (firstVisibleItem < listPositon) {
//			TimeLineFragment.this.getActivity().getActionBar().show();
//		}
//		listPositon = firstVisibleItem;
	}
}
