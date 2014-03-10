package org.scj.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class AccessToken implements Parcelable {
	public String access_token;
	public int expires_in;
	public String remind_in;
	public String uid;
	
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public int getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
	public String getRemind_in() {
		return remind_in;
	}
	public void setRemind_in(String remind_in) {
		this.remind_in = remind_in;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(access_token);
		dest.writeInt(expires_in);
		dest.writeString(remind_in);
		dest.writeString(uid);
	}
	
	
}
