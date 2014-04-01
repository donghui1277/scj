package org.scj.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class CommentBean implements Parcelable {
	private	String	created_at; //评论创建时间
	private	long	id; //评论的ID
	private	String	text; //评论的内容
	private	String	source; //评论的来源
	private	UserBean	user; //评论作者的用户信息字段 详细
	private	String	mid; //评论的MID
	private	String	idstr; //字符串型的评论ID
	private	StatusBean	status; //评论的微博信息字段 详细
	private	CommentBean	reply_comment; //评论来源评论，当本评论属于对另一评论的回复时返回此字段
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
	public UserBean getUser() {
		return user;
	}
	public void setUser(UserBean user) {
		this.user = user;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getIdstr() {
		return idstr;
	}
	public void setIdstr(String idstr) {
		this.idstr = idstr;
	}
	public StatusBean getStatus() {
		return status;
	}
	public void setStatus(StatusBean status) {
		this.status = status;
	}
	public CommentBean getReply_comment() {
		return reply_comment;
	}
	public void setReply_comment(CommentBean reply_comment) {
		this.reply_comment = reply_comment;
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
		dest.writeString(text);
		dest.writeString(source);
		dest.writeParcelable(user, flags);
		dest.writeString(idstr);
		dest.writeParcelable(status, flags);
		dest.writeParcelable(reply_comment, flags);
	}
	
	public final static Parcelable.Creator<CommentBean> CREATOR =
			new Creator<CommentBean>() {
				
				@Override
				public CommentBean[] newArray(int size) {
					return new CommentBean[size];
				}
				
				@Override
				public CommentBean createFromParcel(Parcel source) {
					CommentBean commentBean = new CommentBean();
					commentBean.created_at = source.readString();
					commentBean.id = source.readLong();
					commentBean.text = source.readString();
					commentBean.source = source.readString();
					commentBean.user = source.readParcelable(UserBean.class.getClassLoader());
					commentBean.idstr = source.readString();
					commentBean.status = source.readParcelable(StatusBean.class.getClassLoader());
					commentBean.reply_comment = source.readParcelable(CommentBean.class.getClassLoader());
					
					return commentBean;
				}
			};

}
