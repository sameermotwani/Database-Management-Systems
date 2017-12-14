package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.DatabaseManage;

/**
 * Servlet implementation class Add
 */
public class ActualUpdateDriver extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private static Connection connection;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActualUpdateDriver() {
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
		HttpSession httpSession=request.getSession();
		String name=request.getParameter("DRIVER ID");
		
		try {
			connection = DatabaseManage.getConnection();
			String temp = "SELECT * FROM driver d "
					+ "INNER JOIN address a ON a.address_id=d.address_id "
					+ " WHERE D_id=?;";
			System.out.println(temp);
			PreparedStatement preStatement = connection.prepareStatement(
					temp, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			preStatement.setString(1, name);
			ResultSet resultSet = preStatement.executeQuery();
			
			String dID=null,fName = null,nuid=null,lName = null,ssn=null,contact_phone=null,home_phone=null, sName=null,city=null, State=null,apartment_no=null;
			while (resultSet.next()) {
				System.out.println();
				dID=resultSet.getString("D_id");
				fName=resultSet.getString("first_name");
				lName=resultSet.getString("last_name");
				ssn=resultSet.getString("SSN");
				nuid=resultSet.getString("NUID");
				contact_phone=resultSet.getString("contact_phone");
				home_phone=resultSet.getString("home_phone");
				sName=resultSet.getString("streetName");
				apartment_no=resultSet.getString("apartment_no");
				city=resultSet.getString("city");
				State=resultSet.getString("State");

			}

	
	
		httpSession.setAttribute("DID", dID);
		httpSession.setAttribute("fName", fName);
		httpSession.setAttribute("lName", lName);
		httpSession.setAttribute("ssn", ssn);
		httpSession.setAttribute("nuid", nuid);
		httpSession.setAttribute("contact_phone", contact_phone);
		httpSession.setAttribute("home_phone", home_phone);
		httpSession.setAttribute("streetName", sName);
		httpSession.setAttribute("city", city);
		httpSession.setAttribute("State", State);
		httpSession.setAttribute("apartment_no", apartment_no);
		request.getRequestDispatcher("UpdateRecord.jsp").forward(request, response);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
