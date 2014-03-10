package org.scj;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ScjApp extends Application {
	private static ScjApp sApp;
	private RequestQueue mRequestQueue;
	private SharedPreferences mPreferences;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		sApp = this;
		mRequestQueue = Volley.newRequestQueue(this);
		mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		
		super.onCreate();
	}
	
	public SharedPreferences getPreferences() {
		return mPreferences;
	}
	
	/**
	 * 获取异步http请求队列对象
	 *
	 * @return RequestQueue
	 */
	public RequestQueue getRequestQueue() {
		return mRequestQueue;
	}

	
	/**
	 * 直接以静态方法获取应用程序对象
	 *
	 * @return CatnutApp
	 */
	public static ScjApp getApp() {
		return sApp;
	}
}