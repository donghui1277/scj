package org.scj;
import org.json.JSONObject;
import org.scj.bean.AccessToken;
import org.scj.data.RequestManage;
import org.scj.support.SwipeBackActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

public class OAuthActivity extends SwipeBackActivity {
	private static String TAG = "OAuthActivity";

	private WebView webView;
	private Context mApp;
	private RequestQueue mRequestQueue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_oauth);

		mApp = AppData.getContext();
		mRequestQueue = RequestManage.getRequestQueue();
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("授权");
		actionBar.setDisplayHomeAsUpEnabled(true);
		webView = (WebView) findViewById(R.id.authView);
		webView.setWebViewClient(new MyWebViewClient());

		WebSettings settings = webView.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setSaveFormData(false);
		settings.setCacheMode(WebSettings.LOAD_NO_CACHE);

		CookieSyncManager.createInstance(this);
		CookieManager cookieManager = CookieManager.getInstance();
		cookieManager.removeAllCookie();
		webView.loadUrl(getOAuthUri());
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (item.getItemId() == android.R.id.home) {
			this.finish();
			overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public static final String APP_KEY = "291952992";

	public static final String APP_SECRET = "44930f2c4f60749054b8d710348e2618";

	/**
	 * 授权回调页
	 */
	public static final String AUTH_REDIRECT_URI = "https://api.weibo.com/oauth2/default.html";

	/**
	 * 取消授权回调页
	 */
	public static final String UNAUTH_REDIRECT_URI = "https://api.weibo.com/oauth2/default.html";

	/**
	 * 获取授权页面uri
	 */
	public static String getOAuthUri() {
		return new StringBuilder("https://api.weibo.com/oauth2/authorize")
				.append("?client_id=")
				.append(APP_KEY)
				.append("&response_type=code")
				.append("&display=mobile")
				.append("&redirect_uri=")
				.append(AUTH_REDIRECT_URI)
				.append("&scope=friendships_groups_read,friendships_groups_write")
				.toString();
	}

	/**
	 * 获取access token
	 * 
	 * @param code
	 *            用户认证成功后新浪返回的校检码
	 */
	public static String getAccessTokenUri(String code) {
		return new StringBuilder("https://api.weibo.com/oauth2/access_token")
				.append("?client_id=").append(APP_KEY)
				.append("&client_secret=").append(APP_SECRET)
				.append("&grant_type=authorization_code")
				.append("&redirect_uri=").append(AUTH_REDIRECT_URI)
				.append("&code=").append(code).toString();
	}

	private class MyWebViewClient extends WebViewClient {

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// TODO Auto-generated method stub
			Log.d(TAG, url);

			String code = Uri.parse(url).getQueryParameter("code");
			Log.d(TAG, "the auth code is " + code);
			String accessTokenUri = getAccessTokenUri(code);
			mRequestQueue.add(
					new JsonObjectRequest(Request.Method.POST, accessTokenUri,
							null, new Response.Listener<JSONObject>() {
								@Override
								public void onResponse(JSONObject response) {
									Log.i(TAG, "auth success with result: "
											+ response.toString());
									Intent intent = new Intent();
									intent.putExtra("token", response.toString());
									OAuthActivity.this.setResult(RESULT_OK, intent);
									OAuthActivity.this.finish();
								}
								
							}, new Response.ErrorListener() {
								@Override
								public void onErrorResponse(VolleyError error) {
									Log.wtf(TAG, "auth fail!", error);
								}
							})).setTag(TAG);
			return true;
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			// TODO Auto-generated method stub
			Log.d(TAG, "donghui  " + url);
			super.onPageStarted(view, url, favicon);
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			// TODO Auto-generated method stub
			super.onPageFinished(view, url);
		}

		@Override
		public void onReceivedError(WebView view, int errorCode,
				String description, String failingUrl) {
			// TODO Auto-generated method stub
			super.onReceivedError(view, errorCode, description, failingUrl);
		}

		@Override
		public void onReceivedSslError(WebView view, SslErrorHandler handler,
				SslError error) {
			// TODO Auto-generated method stub
			super.onReceivedSslError(view, handler, error);
		}

	}
	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
			overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
		}
		return super.onKeyDown(keyCode, event);
	}

}