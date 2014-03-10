package org.scj.ui;

import org.json.JSONObject;
import org.scj.R;
import org.scj.ScjApp;
import org.scj.URLHelper;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

public class MainActivity extends Activity {
	public static String TAG = "TimeLineActivity";

	String token;
	TextView timeline;
	private ScjApp mApp;
	private RequestQueue mRequestQueue;
	ActionBar actionBar;
	ViewPager mViewPager;
	SectionsPagerAdapter mSectionsPagerAdapter;
	ImageView topnav[] = new ImageView[8];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Intent intent = getIntent();
		token = intent.getStringExtra("token");
		setContentView(R.layout.root_fragment_activity);

		actionBar = getActionBar();
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		actionBar.setCustomView(R.layout.top_nav_bar);

		topnav[0] = (ImageView) findViewById(R.id.topnav_home);
		topnav[1] = (ImageView) findViewById(R.id.topnav_home_on);
		topnav[2] = (ImageView) findViewById(R.id.topnav_search);
		topnav[3] = (ImageView) findViewById(R.id.topnav_search_on);
		topnav[4] = (ImageView) findViewById(R.id.topnav_account);
		topnav[5] = (ImageView) findViewById(R.id.topnav_account_on);
		topnav[6] = (ImageView) findViewById(R.id.topnav_activity);
		topnav[7] = (ImageView) findViewById(R.id.topnav_activity_on);
		
		topnav[3].setAlpha(0);
		topnav[5].setAlpha(0);
		topnav[7].setAlpha(0);
		
		Resources res = getResources();
		Bitmap bm = BitmapFactory.decodeResource(res,
				R.drawable.topnav_background);
		BitmapDrawable bd = new BitmapDrawable(res, bm);
		actionBar.setBackgroundDrawable(bd);

		mApp = ScjApp.getApp();
		mRequestQueue = mApp.getRequestQueue();

		mRequestQueue.add(
				new JsonObjectRequest(Request.Method.GET,
						getTimeLineUri(token), null,
						new Response.Listener<JSONObject>() {
							@Override
							public void onResponse(JSONObject response) {
								Log.d(TAG, response.toString());
							}

						}, new Response.ErrorListener() {
							@Override
							public void onErrorResponse(VolleyError error) {
								Log.wtf(TAG, "fail!", error);
							}
						})).setTag(TAG);

		mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mViewPager
		.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				
			}
			
			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				// TODO Auto-generated method stub
				System.out.println(position + " " + positionOffset + " " + positionOffsetPixels);
				topnav[position * 2 + 1].setAlpha(1 - positionOffset);
			}
		});
		super.onCreate(savedInstanceState);
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

	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int position) {
			// TODO Auto-generated method stub
			Fragment fragment = null;
			switch (position) {
			case 0:
				fragment = new TimeLineFragment();
				break;
			case 1:
				fragment = new MessageFragment();
				break;
			case 2:
				fragment = new TimeLineFragment();
				break;
			case 3:
				fragment = new MessageFragment();
				break;

			default:
				break;
			}
			
			return fragment;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 4;
		}
	}

}
