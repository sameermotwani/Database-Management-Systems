package com.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class assignEmployeeToMachineClass {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Map<String,String> inputMap = new HashMap<String,String>();

		inputMap.put("Block B", "codeAB");
		inputMap.put("Block A", "codeAA");
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/assignment4", "root", "root@123");
		Map<Integer,Integer> outputMap = new HashMap<Integer,Integer>();
		assignEmployeeToMachineClass ourObject = new assignEmployeeToMachineClass();
		outputMap = ourObject.assignEmployeeToMachineBasedOnLevel(inputMap,connection);
		System.out.println(outputMap);
	}

	public Map<Integer, Integer> assignEmployeeToMachineBasedOnLevel(Map<String, String> ourMap,Connection connection) throws SQLException {
		// TODO Auto-generated method stub
		Map<Integer,Integer> outMap = new HashMap<Integer,Integer>();
		int flag = 1;

		for(String key: ourMap.keySet())
		{
			String selectMachineID= "select m.id "
					+ "from "
					+ "factory f,machine m "
					+ "where f.address= ? and f.id=m.partOf and m.code=?";


			PreparedStatement ps= connection.prepareStatement(selectMachineID);
			ps.setString(1, key);
			ps.setString(2, ourMap.get(key));
			ResultSet rs= ps.executeQuery();
			int machineID = 0;
			if(rs.next()){
				machineID = rs.getInt(1);
			}

			String selectEmployeeIDExpert="SELECT q.canBeRepairedBy "
					+ "FROM machine m "
					+ "INNER JOIN productProcessor pp ON m.id=pp.processedBy "
					+ "INNER JOIN product p ON pp.processes=p.id "
					+ "INNER JOIN productType pt ON pt.id=p.type "
					+ "INNER JOIN qualification q ON q.canRepair=pt.id "
					+ "WHERE m.id=? AND q.level='Expert'";



			PreparedStatement ps1= connection.prepareStatement(selectEmployeeIDExpert);
			ps1.setInt(1, machineID);
			ResultSet rs1= ps1.executeQuery();
			while(rs1.next())
			{
				if(outMap.get(rs1.getInt(1)) != null)
				{
					flag=1;
				}
				else
				{
					outMap.put(rs1.getInt(1), machineID);
					flag = 0;
				}

				if(flag==0)
				{
					break;
				}
			}
			if(flag==0)
			{
				continue;
			}	

			String selectEmployeeIDIntermediate= "SELECT q.canBeRepairedBy "
					+ "FROM machine m "
					+ "INNER JOIN productProcessor pp ON m.id=pp.processedBy "
					+ "INNER JOIN product p ON pp.processes=p.id "
					+ "INNER JOIN productType pt ON pt.id=p.type "
					+ "INNER JOIN qualification q ON q.canRepair=pt.id "
					+ "WHERE m.id=? AND q.level='Intermediate'";

			PreparedStatement ps2= connection.prepareStatement(selectEmployeeIDIntermediate);
			ps2.setInt(1, machineID);
			ResultSet rs2= ps2.executeQuery();

			while(rs2.next())
			{
				if(outMap.get(rs2) != null)
				{
					flag=1;
				}
				else
				{
					outMap.put(rs2.getInt(1), machineID);
					flag = 0;
				}

				if(flag==0)
				{
					break;
				}
			}
			if(flag==0)
			{
				continue;
			}


			String selectEmployeeIDNovice= "SELECT q.canBeRepairedBy "
					+ "FROM machine m "
					+ "INNER JOIN productProcessor pp ON m.id=pp.processedBy "
					+ "INNER JOIN product p ON pp.processes=p.id "
					+ "INNER JOIN productType pt ON pt.id=p.type "
					+ "INNER JOIN qualification q ON q.canRepair=pt.id "
					+ "WHERE m.id=? AND q.level='Novice'";


			PreparedStatement ps3= connection.prepareStatement(selectEmployeeIDNovice);
			ps3.setInt(1, machineID);
			ResultSet rs3= ps3.executeQuery();

			while(rs3.next())
			{
				if(outMap.get(rs3) != null)
				{
					flag=1;
				}
				else
				{
					outMap.put(rs3.getInt(1), machineID);
					flag = 0;
				}

				if(flag==0)
				{
					break;
				}
			}
			if(flag==0)
			{
				continue;
			}

		}

		return outMap;

	}
}