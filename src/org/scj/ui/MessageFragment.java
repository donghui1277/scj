package org.scj.ui;

import org.json.JSONObject;
import org.scj.OAuthActivity;
import org.scj.R;
import org.scj.ScjApp;
import org.scj.URLHelper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MessageFragment extends Fragment implements OnClickListener {
	private static final String TAG = "MessageFragment";
	
	private String token;
	private EditText sendinfo;
	private ImageView send;
	private ScjApp mApp;
	private RequestQueue mRequestQueue;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(org.scj.R.layout.fragment_messsage, container,
				false);
		sendinfo = (EditText) rootView.findViewById(R.id.sendinfo);
		send = (ImageView) rootView.findViewById(R.id.send);
		
		send.setOnClickListener(this);
		
		return rootView;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mApp = ScjApp.getApp();
		mRequestQueue = mApp.getRequestQueue();
		
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.send:
			String status = sendinfo.getText().toString();
			mRequestQueue.add(
					new JsonObjectRequest(Request.Method.POST, getUpdateUri(token, status),
							null, new Response.Listener<JSONObject>() {
								@Override
								public void onResponse(JSONObject response) {
									Toast.makeText(getActivity(), "发送成功！", Toast.LENGTH_SHORT).show();
									sendinfo.setText("");
								}
								
							}, new Response.ErrorListener() {
								@Override
								public void onErrorResponse(VolleyError error) {
									Log.wtf(TAG, "auth fail!", error);
								}
							})).setTag(TAG);
			break;

		default:
			break;
		}
	}
	
	
	private String getUpdateUri(String token, String status) {
		return new StringBuilder(URLHelper.update)
				.append("?access_token=").append(token)
				.append("&status=").append(status).toString();
	}
	
	public void setToken(String token) {
		this.token = token;
	}
}
