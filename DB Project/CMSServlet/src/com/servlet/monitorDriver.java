package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.DriverDetails;
import com.db.DatabaseManage;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

/**
 * Servlet implementation class updateAni
 */
public class monitorDriver extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private static Connection connection;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public monitorDriver() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession();
		String indate = request.getParameter("timestamp");
		 System.out.println("DATE=" +indate);
		SimpleDateFormat outputDateFormat;
System.out.println("IN");
String date= null;
try
{
   SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
   Date tempDate=simpleDateFormat.parse(indate);
   simpleDateFormat.applyPattern("yyyy-MM-dd"); 
    date=simpleDateFormat.format(tempDate);
   System.out.println("DATE=" +date);
  
 } catch (ParseException ex) 
 {
       System.out.println("Parse Exception");
 } catch (java.text.ParseException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

try {
	connection = DatabaseManage.getConnection();
	try {
		String temp = "SELECT r.driver_id,first_name,last_name,start_time,end_time FROM ride r "
				+ "INNER JOIN driver d ON r.driver_id=d.D_ID "
				+ " WHERE start_time LIKE ?";
		PreparedStatement preStatement = connection.prepareStatement(
				temp, ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		preStatement.setString(1, date + "%");
		ResultSet resultSet = preStatement.executeQuery();
		
		String fName = null,lName = null,dID=null,start=null,end=null;
		ArrayList<DriverDetails> atemp=new ArrayList<DriverDetails>();
		while (resultSet.next()) {
			System.out.println();
			dID=resultSet.getString("driver_id");
			fName=resultSet.getString("first_name");
			lName=resultSet.getString("last_name");
			start=resultSet.getString("start_time");
			end=resultSet.getString("end_time");
			atemp.add(new DriverDetails(dID,fName,lName,start,end));
		}
		System.out.println(atemp);
		
		httpSession.setAttribute("List", atemp);
		httpSession.setAttribute("length", atemp.size());
		request.getRequestDispatcher("showDriverDetails.jsp").forward(request, response);
	
	}

	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
} catch (Exception e) {
	// TODO: handle exception
	e.printStackTrace();
}




	System.out.println("dfsfs");
		
		
					}

		

	

}
