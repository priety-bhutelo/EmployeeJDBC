package com.montran.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.montran.pojo.Employee;

public class EmployeeDatabaseUtil {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	String user = "montran";
	String password = "montran";
	String sql = "";
	ResultSet resultSet;
	Statement statement;
	PreparedStatement preparedStatement;
	Connection connection;
	private List<Employee> employeeList = new ArrayList<>();
	Employee employee1;
	private Employee employees[];
	private int nextIndex = 0;
	private int maxIndex;

	public EmployeeDatabaseUtil(int max) {
		try {
			Class.forName(driver);
			System.out.println("Driver loaded successfully.");
			connection = DriverManager.getConnection(url, user, password);
			employees = new Employee[max];
			maxIndex = max;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	public boolean addNewEmployee(Employee employee) throws SQLException {
		if(connection!=null)
		{
			if (nextIndex >= 0 && nextIndex < maxIndex) {
		
				
	    sql = "insert into employee_master values(?,?,?)";
	    preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, employee.getEmployeeId());
		preparedStatement.setString(2, employee.getName());
		preparedStatement.setDouble(3, employee.getSalary());
		preparedStatement.executeUpdate();
		nextIndex++;
		return true;	

	}
		}		
return false;
		
}
	public boolean addAllEmployees(Employee[] employee) throws SQLException {
		for (Employee employees : employee) {
			addNewEmployee(employees);
		}
		return true;

	}

	public boolean updateEmployee(int employeeId, double newSalary, String name) throws SQLException {
		for (Employee employees : employeeList) {
			if (employees.getEmployeeId() == employeeId) {
				if(connection!=null)
				{
				
				sql = "update employee_master set name=? , salary=? where employee_id=?";
				 preparedStatement= connection.prepareStatement(sql);
				 preparedStatement.setString(1, name);
				 preparedStatement.setDouble(2, newSalary);
				 preparedStatement.setInt(3, employeeId);
				 preparedStatement.executeUpdate();

				return true;
			}
			}
		}
		return false;

	}

	public boolean deleteEmployee(int employeeId) throws SQLException {
		for (Employee employees : employeeList) {
			if (employees.getEmployeeId() == employeeId) {
				sql = "delete from employee_master where employee_id = ?";
			     preparedStatement = connection.prepareStatement(sql);
			     preparedStatement.setInt(1, employeeId);
			     preparedStatement.executeUpdate();

				return true;
			}
		}

		return false;

	}

	public Employee getEmployeeByEmployeeId(int employeeId)throws SQLException {
		for (Employee employees : employeeList) {
			if (employees != null) {
				if(connection!=null)
				{
					sql = "select * from employee_master where employee_id=?";
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setInt(1, employeeId);
					resultSet = preparedStatement.executeQuery();

					while (resultSet.next()) {
						if(resultSet.getInt("employee_id")==employeeId)
						System.out.println("Employee Id :: " + resultSet.getInt("employee_id"));
						System.out.println("Name :: " + resultSet.getString("name"));
						System.out.println("Salary :: " + resultSet.getDouble("salary"));
						System.out.println("----------------------------------------");
					
					}
				}
					return employees;
			}
		}
		return null;

	}

	public List<Employee> getAllEmployees() throws SQLException {
		if(connection!=null)
		{
		sql = "select * from employee_master";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			employee1 = new Employee();
			employee1.setEmployeeId(resultSet.getInt("employee_id"));
			employee1.setName(resultSet.getString("name"));
			employee1.setSalary(resultSet.getDouble("salary"));
			employeeList.add(employee1);
		}
		}
		return employeeList;

	}

}
