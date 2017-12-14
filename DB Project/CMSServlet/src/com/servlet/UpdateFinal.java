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
public class UpdateFinal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private static Connection connection;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateFinal() {
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
		String dID=(String) httpSession.getAttribute("DID");
		String name=request.getParameter("First Name");
		String name2=request.getParameter("Last Name");
		String ssn=request.getParameter("SSN");
		String nuid=request.getParameter("NUID");
		String number1=request.getParameter("Contact Phone");
		String number2=request.getParameter("Home Phone");
		String Street=request.getParameter("Street"); 
		String city=request.getParameter("City"); 
		String state=request.getParameter("State"); 
		String ano=request.getParameter("Apartment Number"); 
		
		
		try {
			connection = DatabaseManage.getConnection();
			
			String selectQuery="SELECT address_id FROM driver "
					+ " WHERE D_id=?";
			
			PreparedStatement ps1= connection.prepareStatement(selectQuery);
			ps1.setString(1, dID);
			System.out.println("DriverID= " +dID);
			ResultSet rs1= ps1.executeQuery();
			int aid=0;
			if(rs1.next()) {
				 aid = rs1.getInt(1);
				}
			String InsertQuery = "UPDATE address SET streetName = ?,city = ?,State = ?, apartment_no = ?"
					+ " WHERE address_id = ?";
					
			PreparedStatement preparedStatement;
				preparedStatement = connection.prepareStatement(InsertQuery);
				preparedStatement.setString(1, Street);
				preparedStatement.setString(2, city);
				preparedStatement.setString(3, state);
				preparedStatement.setString(4, ano);
				preparedStatement.setInt(5, aid);
				preparedStatement.executeUpdate();
				
			
				String InsertQuery2 = "UPDATE driver SET first_name = ?,last_name = ?,SSN = ?,contact_phone = ?,home_phone = ?,address_id = ?,NUID = ?"
						+ " WHERE D_id = ?";
				PreparedStatement ps;
				ps = connection.prepareStatement(InsertQuery2);
				ps.setString(1, name);
				ps.setString(2, name2);
					ps.setString(3, ssn);
					ps.setString(4, number1);
					ps.setString(5, number2);
					ps.setInt(6, aid);
					ps.setString(7, nuid);
					ps.setString(8, dID);
					
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
