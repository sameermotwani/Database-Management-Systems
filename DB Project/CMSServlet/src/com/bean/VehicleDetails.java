package com.bean;

public class VehicleDetails {
	String vid = null,vnum = null,start=null,end=null;

	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	public String getVnum() {
		return vnum;
	}

	public void setVnum(String vnum) {
		this.vnum = vnum;
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

	public VehicleDetails(String vid, String vnum, String start, String end) {
		super();
		this.vid = vid;
		this.vnum = vnum;
		this.start = start;
		this.end = end;
	}
}
