package org.scj.data;

/**
 * Manage for the request queue;
 * @author donghui
 */

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RequestManage {
	public static final String TAG = "RequestManage";
	
	public static RequestQueue mRequestQueue;
	
	public RequestManage() {
		
	}
	
	public static void init(Context context) {
		mRequestQueue = Volley.newRequestQueue(context);
	}
	
	public static RequestQueue getRequestQueue() {
		return mRequestQueue;
	}
	
	public <T> void addToRequestQueue(Request<T> request, Object tag) {
		request.setTag(tag == null ? TAG : tag);
		
		getRequestQueue().add(request);
	}
	
	public void cancelPendingRequests(Object tag) {
		if (mRequestQueue != null) {
			mRequestQueue.cancelAll(tag);
		}
	}
}
