package org.scj.bean;

import java.util.List;

public class ListStatusBean {
	private List<StatusBean> statuses;
	private long previous_cursor;
	private long next_cursor;
	private long total_number;
	public List<StatusBean> getStatuses() {
		return statuses;
	}
	public void setStatuses(List<StatusBean> statuses) {
		this.statuses = statuses;
	}
	public long getPrevious_cursor() {
		return previous_cursor;
	}
	public void setPrevious_cursor(long previous_cursor) {
		this.previous_cursor = previous_cursor;
	}
	public long getNext_cursor() {
		return next_cursor;
	}
	public void setNext_cursor(long next_cursor) {
		this.next_cursor = next_cursor;
	}
	public long getTotal_number() {
		return total_number;
	}
	public void setTotal_number(long total_number) {
		this.total_number = total_number;
	}
}
