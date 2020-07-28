package com.montran.main;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.montran.pojo.Employee;
import com.montran.util.EmployeeDatabaseUtil;
import com.montran.util.EmployeeDatabaseUtil;

public class EmployeeDatabaseMain{

	public static void main(String[] args) throws SQLException {
		Scanner scanner = new Scanner(System.in);
		List<Employee> employeeList;
		
		EmployeeDatabaseUtil util = new EmployeeDatabaseUtil(100);
		Employee employee;
		Employee employees[] = null;
		int employeeId;
		String name;
		double salary;
		boolean result;
		int employeeChoice;
		String userChoice;

		do {
			employeeList = util.getAllEmployees();
			for (Employee emp : employeeList) {
				if (emp != null) {
					//	System.out.println(emp);
					System.out.println(emp.getEmployeeId() + "\t" + emp.getName() + "\t" + emp.getSalary());
				}
			}

			System.out.println("Menu");
			System.out.println("1.Add new Employee");
			System.out.println("2.Add Multiple Employees");
			System.out.println("3.Update Existing Employee ");
			System.out.println("4.Delete Existing Employee");
			System.out.println("5.print Single Employee Details");
			System.out.println("6.Exit");

			System.out.println("Enter your Choice");
			employeeChoice = scanner.nextInt();

			switch (employeeChoice) {
			case 1:
				System.out.println("Enter EmployeeId");
				employeeId = scanner.nextInt();
				System.out.println("Enter name");
				name = scanner.next();
				System.out.println("Enter Salary::");
				salary = scanner.nextDouble();
				employee = new Employee(employeeId, name, salary);
				result = util.addNewEmployee(employee);

				if (result) {
					System.out.println("Employees Details Added successfully");
				} else {
					System.out.println("failed to add employee details");
				}
				break;
			case 2:
				System.out.println("How many employee details");
				int numberOfEmployee = scanner.nextInt();
				employees = new Employee[numberOfEmployee];
				for (int i = 0; i < numberOfEmployee; i++) {
					System.out.println("Enter EmployeeId");
					employeeId = scanner.nextInt();
					System.out.println("Enter name");
					name = scanner.next();
					System.out.println("Enter Salary");
					salary = scanner.nextDouble();
					employee = new Employee(employeeId, name, salary);
					employees[i] = employee;
				}
				result = util.addAllEmployees(employees);
				if (result)
					System.out.println("Employees Details Added successfully");
				else
					System.out.println("failed to add employee details");
				
				break;
			case 3:
				System.out.println("Enter EmployeeId");
				employeeId = scanner.nextInt();
				employee = util.getEmployeeByEmployeeId(employeeId);

				if (employee != null) {

					System.out.println("Enter employee Name");
					name = scanner.next();
					System.out.println("Enter New Salary::");
					salary = scanner.nextDouble();
					result = util.updateEmployee(employeeId, salary, name);
					if (result)
						System.out.println("Employees Details updated successfully");
					else
						System.out.println("failed to add employee details");

				} else {
					System.out.println("No Employee Found");
				}
				
				break;
			case 4:
				System.out.println("Enter EmployeeId");
				employeeId = scanner.nextInt();
				employee = util.getEmployeeByEmployeeId(employeeId);
				if (employee != null) {
					result = util.deleteEmployee(employeeId);
					if (result)
						System.out.println("Employees Details deleted successfully");
					else
						System.out.println("failed to delete employee details");

				} else {
					System.out.println("No Employee Found");
				}
				break;
			case 5:
				System.out.println("Enter EmployeeId");
				employeeId = scanner.nextInt();
				employee = util.getEmployeeByEmployeeId(employeeId);
				if (employee != null) {
					System.out.println(employee);
				} else {
					System.out.println("No Employee Found");
				}
				break;
			case 6:
				System.out.println("Thank you");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid Choice");
				break;

			}
			System.out.println("Do you want to continue??");
			userChoice = scanner.next();
		} while (userChoice.equals("yes"));
		System.out.println("Thank you");

	}

}