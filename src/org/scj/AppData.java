package org.scj;

import org.scj.data.RequestManage;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AppData extends Application {
	public static final String TAG = "ApplicationController";
	
	private static Context sContext;
	private SharedPreferences mPreferences;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		sContext = getApplicationContext();
		mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		RequestManage.init(AppData.getContext());
		
		super.onCreate();
	}
	
	public SharedPreferences getPreferences() {
		return mPreferences;
	}
	
	public static Context getContext() {
		return sContext;
	}
}