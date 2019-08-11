package DesignPatterns;

import java.util.Scanner;

import paymentSystemOO.Employee;
import paymentSystemOO.Hourly;
import paymentSystemOO.Salaried;
import paymentSystemOO.Commissioned;
import paymentSystemOO.ExtraFunctions;

public class EmployeeFactory {
	
	private static Scanner input = new Scanner(System.in);
	
	/*
	  
	  This one prevents you from add this same code just for instantiate a new employee.
	  Instead, give this class a String identification of the Object you want to instantiate
	  and it will return the Object you want.
	  this sure help for creating new Objects that share the same SuperClass.
	
	*/
	
	public Employee newEmployee(String employee_type, ExtraFunctions extra_func) // extra is optional
	{
		Employee new_employee = null;
		if(employee_type.equalsIgnoreCase("H"))
		{
			new_employee = new Hourly();
		}
		else if(employee_type.equalsIgnoreCase("S"))
		{
			new_employee = new Salaried();
			( (Salaried) new_employee).WEK.setPayment_date(extra_func.dayUtil(extra_func.getMonth()));
			( (Salaried) new_employee).setSalary(extra_func.doubleScan("\n\n  Insert the salary per day worked: "));
		}
		else if(employee_type.equalsIgnoreCase("C"))
		{
			new_employee = new Commissioned();
			( (Commissioned) new_employee).setSalary(extra_func.doubleScan("\n\n  Insert the salary per day worked: "));
		}
		
		return new_employee;
	}
	
	public Employee changeType(Employee employee, String new_type, ExtraFunctions extra_func)
	{
		Employee new_employee = null;
		double salary;
		if(new_type.equalsIgnoreCase("H"))
		{
			new_employee = new Hourly(employee.getID(), employee.getName(), employee.getAddress(), 0, employee.isSyndicate(), employee.getSyndicate_id(), employee.getSyndicate_tax(), employee.getService_taxes(), employee.isReceived_tax(), employee.isCard_submit(), employee.getPayment_method(), employee.getArrival_time(), 0, 4, 0);
		}
		else if (new_type.equalsIgnoreCase("S"))
		{
			salary = extra_func.doubleScan("\n\n  Insert the salary per day: ");
			new_employee = new Salaried(employee.getID(), employee.getName(), employee.getAddress(), salary, employee.isSyndicate(), employee.getSyndicate_id(), employee.getSyndicate_tax(), employee.getService_taxes(), employee.isReceived_tax(), employee.isCard_submit(), employee.getPayment_method(), employee.getArrival_time(), extra_func.dayUtil(extra_func.getMonth()), true, 0);
		}
		else if (new_type.equalsIgnoreCase("C"))
		{
			salary = extra_func.doubleScan("\n\n  Insert the salary per day: ");
			new_employee = new Commissioned(employee.getID(), employee.getName(), employee.getAddress(), salary, employee.isSyndicate(), employee.getSyndicate_id(), employee.getSyndicate_tax(), employee.getService_taxes(), employee.isReceived_tax(), employee.isCard_submit(), employee.getPayment_method(), employee.getArrival_time(), 0, 4, 0, 0);
		}
		else
		{
			System.out.printf("\n\n  Failed to change the type of this employee!!\n\n  Press enter to continue...\n\n\n");
			input.nextLine();
			return employee;
		}
		return new_employee;
	}
}
