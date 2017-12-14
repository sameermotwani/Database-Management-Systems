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

import com.bean.VehicleDetails;
import com.db.DatabaseManage;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

/**
 * Servlet implementation class updateAni
 */
public class monitorVehicle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private static Connection connection;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public monitorVehicle() {
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
   SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
   Date tempDate=simpleDateFormat.parse(indate);
   simpleDateFormat.applyPattern("yyyy-dd-MM");
    outputDateFormat = new SimpleDateFormat("yyyy-dd-MM");   
    date=simpleDateFormat.format(tempDate);
   System.out.println("Output date is = "+outputDateFormat.format(tempDate));
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
		String temp = "SELECT r.vehicle_id,vehicle_number,start_time,end_time FROM ride r  "
				+ "INNER JOIN vehicle d ON r.vehicle_id=d.vehicle_id"
				+ " WHERE start_time LIKE ?";
		PreparedStatement preStatement = connection.prepareStatement(
				temp, ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		preStatement.setString(1, date + "%");
		ResultSet resultSet = preStatement.executeQuery();
		
		String vid = null,vnum = null,start=null,end=null;
		ArrayList<VehicleDetails> atemp=new ArrayList<VehicleDetails>();
		while (resultSet.next()) {
			System.out.println();

			vid=resultSet.getString("vehicle_id");
			vnum=resultSet.getString("vehicle_number");
			start=resultSet.getString("start_time");
			end=resultSet.getString("end_time");
			atemp.add(new VehicleDetails(vid,vnum,start,end));
		}
		System.out.println(atemp);
		
		httpSession.setAttribute("List", atemp);
		httpSession.setAttribute("length", atemp.size());
		request.getRequestDispatcher("showVehicleDetails.jsp").forward(request, response);
	
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
