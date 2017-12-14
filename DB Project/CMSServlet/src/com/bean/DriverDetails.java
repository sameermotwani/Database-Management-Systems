package com.bean;

public class DriverDetails {
	String dID=null,fName = null,lName = null,start=null,end=null;

	public DriverDetails(String dID, String fName, String lName, String start, String end) {
		super();
		this.dID = dID;
		this.fName = fName;
		this.lName = lName;
		this.start = start;
		this.end = end;
	}

	public String getdID() {
		return dID;
	}

	public void setdID(String dID) {
		this.dID = dID;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
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
}
