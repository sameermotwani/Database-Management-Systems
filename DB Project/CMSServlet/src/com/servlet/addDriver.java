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
public class addDriver extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private static Connection connection;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addDriver() {
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
		String name=request.getParameter("First Name");
		String name2=request.getParameter("Last Name");
		String ssn=request.getParameter("SSN");
		String number1=request.getParameter("Contact Phone");
		String number2=request.getParameter("Home Phone");
		String Street=request.getParameter("Street"); 
		String city=request.getParameter("City"); 
		String state=request.getParameter("State"); 
		String ano=request.getParameter("Apartment Number");
	    String nuid=request.getParameter("NUID"); 
		
		
		try {
			connection = DatabaseManage.getConnection();
			String InsertQuery = "INSERT INTO address(streetName,city,State,apartment_no) VALUES(?,?,?,?)";
			PreparedStatement preparedStatement;
				preparedStatement = connection.prepareStatement(InsertQuery);
				preparedStatement.setString(1, Street);
				preparedStatement.setString(2, city);
				preparedStatement.setString(3, state);
				preparedStatement.setString(4, ano);
				preparedStatement.executeUpdate();
				
				
				String select_aid= "SELECT max(address_id) "
						+ "FROM address d ";
				PreparedStatement ps2= connection.prepareStatement(select_aid);
				ResultSet rs2= ps2.executeQuery();
				int id=0;
				if(rs2.next()) {
					 id = rs2.getInt(1);
					}
				String InsertQuery2 = "INSERT INTO driver(first_name,last_name,SSN,contact_phone,home_phone,address_id,NUID) VALUES(?,?,?,?,?,?,?)";
				PreparedStatement ps;
				ps = connection.prepareStatement(InsertQuery2);
				ps.setString(1, name);
				ps.setString(2, name2);
					ps.setString(3, ssn);
					ps.setString(4, number1);
					ps.setString(5, number2);
					ps.setInt(6, id);
					ps.setString(7, nuid);
					
				ps.executeUpdate();
				
				request.getRequestDispatcher("NUPD.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
