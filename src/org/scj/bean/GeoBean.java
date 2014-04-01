package org.scj.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 
 * @author donghui
 * 
 */
public class GeoBean implements Parcelable {
	private String longitude; // 经度坐标
	private String latitude; // 维度坐标
	private String city; // 所在城市的城市代码
	private String province; // 所在省份的省份代码
	private String city_name; // 所在城市的城市名称
	private String province_name; // 所在省份的省份名称
	private String address; // 所在的实际地址，可以为空
	private String pinyin; // 地址的汉语拼音，不是所有情况都会返回该字段
	private String more; // 更多信息，不是所有情况都会返回该字段

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getProvince_name() {
		return province_name;
	}

	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getMore() {
		return more;
	}

	public void setMore(String more) {
		this.more = more;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(longitude);
		dest.writeString(latitude);
		dest.writeString(city);
		dest.writeString(province);
		dest.writeString(city_name);
		dest.writeString(province_name);
		dest.writeString(address);
		dest.writeString(pinyin);
		dest.writeString(more);
	}
	
	public final static Parcelable.Creator<GeoBean> CREATOR = 
			new Creator<GeoBean>() {
				
				@Override
				public GeoBean[] newArray(int size) {
					return new GeoBean[size];
				}
				
				@Override
				public GeoBean createFromParcel(Parcel source) {
					GeoBean geoBean = new GeoBean();
					geoBean.longitude = source.readString();
					geoBean.latitude = source.readString();
					geoBean.city = source.readString();
					geoBean.province = source.readString();
					geoBean.city_name = source.readString();
					geoBean.province_name = source.readString();
					geoBean.address = source.readString();
					geoBean.pinyin = source.readString();
					geoBean.more = source.readString();
					
					return geoBean;
				}
			};

}
