package org.scj.ui;

import org.json.JSONObject;
import org.scj.R;
import org.scj.AppData;
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
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends Activity implements OnClickListener {
	public static String TAG = "TimeLineActivity";

	String token;
	TextView timeline;
	ActionBar actionBar;
	ViewPager mViewPager;
	SectionsPagerAdapter mSectionsPagerAdapter;
	ImageView topnav[] = new ImageView[8];
	RelativeLayout topnav_home_layout, topnav_search_layout, topnav_account_layout, topnav_activity_layout; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
//		getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
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
		
		topnav_home_layout = (RelativeLayout) findViewById(R.id.topnav_home_layout);
		topnav_home_layout.setOnClickListener(this);
		topnav_search_layout = (RelativeLayout) findViewById(R.id.topnav_search_layout);
		topnav_search_layout.setOnClickListener(this);
		topnav_account_layout = (RelativeLayout) findViewById(R.id.topnav_account_layout);
		topnav_account_layout.setOnClickListener(this);
		topnav_activity_layout = (RelativeLayout) findViewById(R.id.topnav_activity_layout);
		topnav_activity_layout.setOnClickListener(this);
		
		
//		Resources res = getResources();
//		Bitmap bm = BitmapFactory.decodeResource(res,
//				R.drawable.topnav_background);
//		BitmapDrawable bd = new BitmapDrawable(res, bm);
//		actionBar.setBackgroundDrawable(bd);
		
		actionBar.setBackgroundDrawable(getWallpaper());

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
				getActionBar().show();
				topnav[position * 2 + 1].setAlpha(1 - positionOffset);
				if (position != 3) {
					topnav[position * 2 + 3].setAlpha(positionOffset);
				}
			}
		});
		super.onCreate(savedInstanceState);
	}

	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			switch (arg0) {
			case 0:
				TimeLineFragment timeLinefragment =new TimeLineFragment();
				timeLinefragment.setToken(token);
				return timeLinefragment;
			case 1:
				MessageFragment messageFragment =new MessageFragment();
				messageFragment.setToken(token);
				return messageFragment;
			case 2:
				return new MessageFragment();
			case 3:
				return new MessageFragment();
			default:
				break;
			}
			return null;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 4;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.topnav_home_layout:
			if (mViewPager.getCurrentItem() != 0) {
				mViewPager.setCurrentItem(0);
			} else {
				System.out.println("ScrollToTop");
			}
			break;

		case R.id.topnav_search_layout:
			mViewPager.setCurrentItem(1);
			break;
			
		case R.id.topnav_account_layout:
			mViewPager.setCurrentItem(2);
			break;
			
		case R.id.topnav_activity_layout:
			mViewPager.setCurrentItem(3);
			break;
		}
	}

}
