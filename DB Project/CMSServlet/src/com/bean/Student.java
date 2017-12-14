package com.bean;

public class Student {
		private String fName, NUID;

		public Student(String fName, String nUID) {
			super();
			this.fName = fName;
			NUID = nUID;
		}

		public String getfName() {
			return fName;
		}

		public void setfName(String fName) {
			this.fName = fName;
		}

		public String getNUID() {
			return NUID;
		}

		public void setNUID(String nUID) {
			NUID = nUID;
		}
		
}
