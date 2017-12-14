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
import com.bean.Student;

/**
 * Servlet implementation class Update
 */
public class fetchDefaulters extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private static Connection connection;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public fetchDefaulters() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		
			HttpSession httpSession=request.getSession();
			connection = DatabaseManage.getConnection();
			try {
				String temp = "SELECT * FROM student where num_missed_rides>2";
				PreparedStatement preStatement = connection
						.prepareStatement(temp);
		    	System.out.println("NAME\t\t\t\tDOB\t\t\tAnniversary");
				ResultSet resultSet = preStatement.executeQuery();
				String fName = null,NUID = null;
				ArrayList<Student> atemp=new ArrayList<Student>();
				while (resultSet.next()) {
					System.out.println();
					fName=resultSet.getString("first_name");
					NUID=resultSet.getString("NUID");
					System.out.print(resultSet.getString("first_name"));
					atemp.add(new Student(fName,NUID));
				}
				System.out.println(atemp);
				
				httpSession.setAttribute("List", atemp);
				httpSession.setAttribute("length", atemp.size());
				request.getRequestDispatcher("fetchDefaulters.jsp").forward(request, response);
			}
			catch(SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		}

}
