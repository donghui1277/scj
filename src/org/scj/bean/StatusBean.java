package org.scj.bean;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class StatusBean implements Parcelable {
	private	String	created_at; //微博创建时间
	private	long	id; //微博ID
	private	long	mid; //微博MID
	private	String	idstr; //字符串型的微博ID
	private	String	text; //微博信息内容
	private	String	source; //微博来源
	private	boolean	favorited; //是否已收藏，true：是，false：否
	private	boolean	truncated; //是否被截断，true：是，false：否
	private	String	in_reply_to_status_id; //（暂未支持）回复ID
	private	String	in_reply_to_user_id; //（暂未支持）回复人UID
	private	String	in_reply_to_screen_name; //（暂未支持）回复人昵称
	private	ArrayList<PicUrls> pic_urls = new ArrayList<PicUrls>(); //微博配图地址。多图时返回多图链接。无配图返回“[]”
	private	String	thumbnail_pic; //缩略图片地址，没有时不返回此字段
	private	String	bmiddle_pic; //中等尺寸图片地址，没有时不返回此字段
	private	String	original_pic; //原始图片地址，没有时不返回此字段
	private	GeoBean	geo; //地理信息字段 详细
	private	UserBean	user; //微博作者的用户信息字段 详细
	private	StatusBean	retweeted_status; //被转发的原微博信息字段，当该微博为转发微博时返回 详细
	private	int	reposts_count; //转发数
	private	int	comments_count; //评论数
	private	int	attitudes_count; //表态数
	private	int	mlevel; //暂未支持
	private	Visible	visible; //微博的可见性及指定可见分组信息。该object中type取值，0：普通微博，1：私密微博，3：指定分组微博，4：密友微博；list_id为分组的组号

    public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getMid() {
		return mid;
	}
	public void setMid(long mid) {
		this.mid = mid;
	}
	public String getIdstr() {
		return idstr;
	}
	public void setIdstr(String idstr) {
		this.idstr = idstr;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public boolean isFavorited() {
		return favorited;
	}
	public void setFavorited(boolean favorited) {
		this.favorited = favorited;
	}
	public boolean isTruncated() {
		return truncated;
	}
	public void setTruncated(boolean truncated) {
		this.truncated = truncated;
	}
	public String getIn_reply_to_status_id() {
		return in_reply_to_status_id;
	}
	public void setIn_reply_to_status_id(String in_reply_to_status_id) {
		this.in_reply_to_status_id = in_reply_to_status_id;
	}
	public String getIn_reply_to_user_id() {
		return in_reply_to_user_id;
	}
	public void setIn_reply_to_user_id(String in_reply_to_user_id) {
		this.in_reply_to_user_id = in_reply_to_user_id;
	}
	public String getIn_reply_to_screen_name() {
		return in_reply_to_screen_name;
	}
	public void setIn_reply_to_screen_name(String in_reply_to_screen_name) {
		this.in_reply_to_screen_name = in_reply_to_screen_name;
	}
	public ArrayList<PicUrls> getPic_urls() {
		return pic_urls;
	}
	public void setPic_urls(ArrayList<PicUrls> pic_urls) {
		this.pic_urls = pic_urls;
	}
	public String getThumbnail_pic() {
		return thumbnail_pic;
	}
	public void setThumbnail_pic(String thumbnail_pic) {
		this.thumbnail_pic = thumbnail_pic;
	}
	public String getBmiddle_pic() {
		return bmiddle_pic;
	}
	public void setBmiddle_pic(String bmiddle_pic) {
		this.bmiddle_pic = bmiddle_pic;
	}
	public String getOriginal_pic() {
		return original_pic;
	}
	public void setOriginal_pic(String original_pic) {
		this.original_pic = original_pic;
	}
	public GeoBean getGeo() {
		return geo;
	}
	public void setGeo(GeoBean geo) {
		this.geo = geo;
	}
	public UserBean getUser() {
		return user;
	}
	public void setUser(UserBean user) {
		this.user = user;
	}
	public StatusBean getRetweeted_status() {
		return retweeted_status;
	}
	public void setRetweeted_status(StatusBean retweeted_status) {
		this.retweeted_status = retweeted_status;
	}
	public int getReposts_count() {
		return reposts_count;
	}
	public void setReposts_count(int reposts_count) {
		this.reposts_count = reposts_count;
	}
	public int getComments_count() {
		return comments_count;
	}
	public void setComments_count(int comments_count) {
		this.comments_count = comments_count;
	}
	public int getAttitudes_count() {
		return attitudes_count;
	}
	public void setAttitudes_count(int attitudes_count) {
		this.attitudes_count = attitudes_count;
	}
	public int getMlevel() {
		return mlevel;
	}
	public void setMlevel(int mlevel) {
		this.mlevel = mlevel;
	}
	public Visible getVisible() {
		return visible;
	}
	public void setVisible(Visible visible) {
		this.visible = visible;
	}
	
	public static class PicUrls implements Parcelable {
        public String thumbnail_pic;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(thumbnail_pic);
        }

        public static final Parcelable.Creator<PicUrls> CREATOR =
                new Parcelable.Creator<PicUrls>() {
                    public PicUrls createFromParcel(Parcel source) {
                        PicUrls picUrls = new PicUrls();
                        picUrls.thumbnail_pic = source.readString();
                        return picUrls;
                    }

                    public PicUrls[] newArray(int size) {
                        return new PicUrls[size];
                    }
                };

    }
	
	public static class Visible implements Parcelable {
        public int type;
        public int list_id;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
        	dest.writeInt(type);
        	dest.writeInt(list_id);
        }

        public static final Parcelable.Creator<Visible> CREATOR =
                new Parcelable.Creator<Visible>() {
                    public Visible createFromParcel(Parcel source) {
                        Visible visible = new Visible();
                        visible.type = source.readInt();
                        visible.list_id = source.readInt();
                        return visible;
                    }

					public Visible[] newArray(int size) {
						return new Visible[size];
					}
                };

    }
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(created_at);
		dest.writeLong(id);
		dest.writeLong(mid);
		dest.writeString(idstr);
		dest.writeString(text);
		dest.writeString(source);
		dest.writeString(in_reply_to_status_id);
		dest.writeString(in_reply_to_user_id);
		dest.writeString(in_reply_to_screen_name);
		dest.writeTypedList(pic_urls);
		dest.writeString(thumbnail_pic);
		dest.writeString(bmiddle_pic);
		dest.writeString(original_pic);
		dest.writeParcelable(geo, flags);
		dest.writeParcelable(user, flags);
		dest.writeParcelable(retweeted_status, flags);
		dest.writeInt(reposts_count);
		dest.writeInt(comments_count);
		dest.writeInt(attitudes_count);
		dest.writeInt(mlevel);
		dest.writeParcelable(visible, flags);
		dest.writeBooleanArray(new boolean[]{this.favorited, this.truncated});
	}
	
	
	public final static Parcelable.Creator<StatusBean> CREATOR = 
			new Creator<StatusBean>() {

				@Override
				public StatusBean createFromParcel(Parcel source) {
					StatusBean statusBean = new StatusBean();
					statusBean.created_at = source.readString();
					statusBean.id = source.readLong();
					statusBean.mid = source.readLong();
					statusBean.idstr = source.readString();
					statusBean.text = source.readString();
					statusBean.source = source.readString();
					statusBean.in_reply_to_status_id = source.readString();
					statusBean.in_reply_to_user_id = source.readString();
					statusBean.in_reply_to_screen_name = source.readString();
					statusBean.pic_urls = new ArrayList<PicUrls>();
                    source.readTypedList(statusBean.pic_urls, PicUrls.CREATOR);
                    statusBean.thumbnail_pic = source.readString();
                    statusBean.bmiddle_pic = source.readString();
                    statusBean.original_pic = source.readString();
                    statusBean.geo = source.readParcelable(GeoBean.class.getClassLoader());
                    statusBean.user = source.readParcelable(UserBean.class.getClassLoader());
                    statusBean.retweeted_status = source.readParcelable(StatusBean.class.getClassLoader());
                    statusBean.reposts_count = source.readInt();
                    statusBean.comments_count = source.readInt();
                    statusBean.attitudes_count = source.readInt();
                    statusBean.mlevel = source.readInt();
                    
                    statusBean.visible = source.readParcelable(Visible.class.getClassLoader());
                    boolean[] booleans = new boolean[2];
					source.readBooleanArray(booleans);
					statusBean.favorited = booleans[0];
					statusBean.truncated = booleans[1];
					
					return statusBean;
				}

				@Override
				public StatusBean[] newArray(int size) {
					return new StatusBean[size];
				}
			};
}
