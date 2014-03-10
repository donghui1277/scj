package org.scj;

import org.scj.bean.AccessToken;
import org.scj.ui.MainActivity;

import com.google.gson.Gson;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;

public class AccountActivity extends ListActivity {
	SharedPreferences settings;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		settings = getSharedPreferences("token", 0);
		String token = settings.getString("access_token", null);
		if (token != null) {
			Intent intent = new Intent(AccountActivity.this, MainActivity.class);
			intent.putExtra("token", token);
			this.finish();
			startActivity(intent);
		}
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public ListView getListView() {
		// TODO Auto-generated method stub
		return super.getListView();
	}

	@Override
	public ListAdapter getListAdapter() {
		// TODO Auto-generated method stub
		return super.getListAdapter();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_auth) {
			startActivityForResult(new Intent(this, OAuthActivity.class), 0);
			overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_out_right);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (requestCode == 0 && resultCode == RESULT_OK) {
			String token = data.getStringExtra("token");
			Gson gson = new Gson();
			AccessToken accessToken = gson.fromJson(token, AccessToken.class);
			SharedPreferences.Editor editor = settings.edit();
			editor.putString("access_token", accessToken.getAccess_token());
			editor.putInt("expires_in", accessToken.getExpires_in());
			editor.putString("remind_in", accessToken.getRemind_in());
			editor.putString("uid", accessToken.getUid());
			editor.commit();
			Intent intent = new Intent(AccountActivity.this, MainActivity.class);
			intent.putExtra("token", accessToken.getAccess_token());
			this.finish();
			startActivity(intent);
		}
	}

}
