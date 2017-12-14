package com.bean;

public class RideDetails {
	String rid = null,start=null,end=null;

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public RideDetails(String rid, String start, String end) {
		super();
		this.rid = rid;
		this.start = start;
		this.end = end;
	}
}
