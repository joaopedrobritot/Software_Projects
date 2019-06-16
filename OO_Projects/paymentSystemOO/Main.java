package paymentSystemOO;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

	static Scanner input = new Scanner(System.in);
	
	//Date Settings

		private static int day = 26;// starting the year
		private static int month = 6;// ^^^^^^^^^^^^
		private static String week_name[] = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};// aux for printing the day of week
		private static int day_of_week = 2;// this controls the day of week
		private static int last_day_month[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};// last day of every month (not considering bisect years)

	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args) {
		String system_pass = "admin";
		String employee_pass = "0000";
		String given_pass;
		int option;
		int i;
		boolean choice = true;
		
		Employee Employee_list[] = new Employee[1000]; 
		
		while(true)
		{
			consoleClear();
			System.out.printf("\n       ////////////////////////////////////\n");
			System.out.printf("       ///                              ///\n");
			System.out.printf("       ///          Main Menu           ///\n");
			System.out.printf("       ///                              ///\n");
			System.out.printf("       /// 1 - Admin Functions          ///\n");
			System.out.printf("       /// 2 - Employee Functions       ///\n");
			System.out.printf("       /// 3 - Increase Date (+1 Day)   ///\n");
			System.out.printf("       /// 4 - Exit                     ///\n");
			System.out.printf("       ///                              ///\n");
			System.out.printf("       ////////////////////////////////////\n\n");
			
			if(day < 10 && month < 10)
                System.out.printf("       Date : 0%d / 0%d / 2019\n       %s\n", day, month, week_name[day_of_week]);
            else if(day < 10)
                System.out.printf("       Date : 0%d / %d / 2019\n       %s\n", day, month, week_name[day_of_week]);
            else if(month < 10)
                System.out.printf("       Date : %d / 0%d / 2019\n       %s\n", day, month, week_name[day_of_week]);
            else
                System.out.printf("       Date : %d / %d / 2019\n       %s\n", day, month, week_name[day_of_week]);
			
			try {
				System.out.printf("\n\n Option: ");option = input.nextInt();input.nextLine();System.out.printf("\n\n");
				if(option == 1)
				{
					while(choice)
					{
						System.out.printf("\n\n Insert the System Password (Default: 'admin'): ");given_pass = input.nextLine();System.out.printf("\n");
						if(given_pass.equalsIgnoreCase(system_pass))
						{
							choice = false;
						}
						else
						{
							System.out.printf("\n\n Incorrect Password!!\n Insert 'back' to go back to menu or simply press enter to try again\n\n Option :");
							if(input.nextLine().equalsIgnoreCase("back"))
							{
								break;
							}
						}
					}
					while(!choice)
					{
						consoleClear();
						System.out.printf("\n       ////////////////////////////////////////////////////\n");
						System.out.printf("       ///                                              ///\n");
						System.out.printf("       ///           *Administrator Functions*          ///\n");
						System.out.printf("       ///                                              ///\n");
						System.out.printf("       /// 1 - Add a Employee                           ///\n");
						System.out.printf("       /// 2 - Delete a Employee                        ///\n");
						System.out.printf("       /// 3 - Change a Employee Details                ///\n");
						System.out.printf("       /// 4 - Payments for Today                       ///\n");
						System.out.printf("       /// 5 - Launch a Tax of Service                  ///\n");
						System.out.printf("       /// 6 - Undo / Redo                              ///\n");
						System.out.printf("       /// 7 - Create a new Payment Schedule            ///\n");
						System.out.printf("       /// 8 - Show Details of an Employee              ///\n");
						System.out.printf("       /// 9 - Show Details of All Employees            ///\n");
						System.out.printf("       /// 10 - Change System Password                  ///\n");
						System.out.printf("       /// 11 - Back to Main Menu                       ///\n");
						System.out.printf("       ///                                              ///\n");
						System.out.printf("       ////////////////////////////////////////////////////\n\n\n Option: ");
						try {
							switch(input.nextInt())
							{
								case 1:
									input.nextLine();
									consoleClear();
									for(i = 0; i< 1000;i++)
									{
										if(Employee_list[i] == null)
										{
											Employee_list[i] = addEmployee(Employee_list[i], i);
											break;
										}
									}
									break;
									
								case 2:
									input.nextLine();
									consoleClear();
									int a;
									
									while(true)
									{
										try {
											System.out.printf("\n\n\n  Insert the ID from the Employee you want to Remove: ");
											a = input.nextInt();
											input.nextLine();
											if(Employee_list[a] != null)
											{
												Employee_list[a] = null;
												System.out.printf("\n\n\n  Employee Removed from the System!!\n\n  Press enter to return to Adminstrator Functions...\n\n\n");
											}
											else
											{
												System.out.printf("\n\n  There's no Employee with this Identification!!\n\n  Press enter to return to Adminstrator Functions...\n\n\n");
											}
											input.nextLine();
											break;
										}
										catch(Exception e)
										{
											input.nextLine();
											System.out.printf("\n\n\n  The given ID is not an integer!!\n\n  Press enter to Try Again...\n\n\n");
											input.nextLine();
										}
									}
									input.nextLine();
									break;
									
								case 3:
									input.nextLine();
									consoleClear();
									Employee_list = changeDetails(Employee_list);
									break;
									
								case 4:
									input.nextLine();
									System.out.printf("\n\n Comming soon...\n\n Press enter to continue...");
									input.nextLine();
									break;
									
								case 5:
									input.nextLine();
									System.out.printf("\n\n Comming soon...\n\n Press enter to continue...");
									input.nextLine();
									break;
									
								case 6:
									input.nextLine();
									System.out.printf("\n\n Comming soon...\n\n Press enter to continue...");
									input.nextLine();
									break;
									
								case 7:
									input.nextLine();
									System.out.printf("\n\n Comming soon...\n\n Press enter to continue...");
									input.nextLine();
									break;
									
								case 8:
									input.nextLine();
									consoleClear();
									showDetails(Employee_list);
									break;
									
								case 9:
									input.nextLine();
									System.out.printf("\n\n Comming soon...\n\n Press enter to continue...");
									input.nextLine();
									break;
									
								case 10:
									input.nextLine();
									System.out.printf("\n\n Comming soon...\n\n Press enter to continue...");
									input.nextLine();
									break;
									
								case 11:
									choice = true;
									break;
									
								default:
									input.nextLine();
									System.out.printf("\n\n Invalid Option!!\n Press enter to try again...");
									input.nextLine();
									break;
							}
						}
						catch(Exception e)
						{
							input.nextLine();
							System.out.printf("\n\n The typed value is not an integer!!\n Press enter to try again...\n\n\n\n");
							input.nextLine();
						}
					}
				}
				else if(option == 2)
				{
					while(choice)
					{
						System.out.printf("\n\n Insert the Employee Password (Default: '0000'): ");given_pass = input.nextLine();System.out.printf("\n");
						if(given_pass.equalsIgnoreCase(employee_pass))
						{
							choice = false;
						}
						else
						{
							System.out.printf("\n\n Incorrect Password!!\n Insert 'back' to go back to menu or simply press enter to try again");
							if(input.nextLine().equalsIgnoreCase("back"))
							{
								break;
							}
						}
					}
					while(!choice)
					{
						consoleClear();
						System.out.printf("\n       ////////////////////////////////////////////////////\n");
						System.out.printf("       ///                                              ///\n");
						System.out.printf("       ///             *Employee Functions*             ///\n");
						System.out.printf("       ///                                              ///\n");
						System.out.printf("       /// 1 - Launch a TimeCard                        ///\n");
						System.out.printf("       /// 2 - Launch a Selling                         ///\n");
						System.out.printf("       /// 3 - Change a Employee Details                ///\n");
						System.out.printf("       /// 4 - Payment Schedules                        ///\n");
						System.out.printf("       /// 5 - Undo / Redo                              ///\n");
						System.out.printf("       /// 6 - Show Details of an Employee              ///\n");
						System.out.printf("       /// 7 - Back to Main Menu                        ///\n");
						System.out.printf("       ///                                              ///\n");
						System.out.printf("       ////////////////////////////////////////////////////\n\n\n Option: ");
						try {
							switch(input.nextInt())
							{
								case 1:
									input.nextLine();
									consoleClear();
									Employee_list = timeCard(Employee_list);
									break;
									
								case 2:
									input.nextLine();
									consoleClear();
									Employee_list = sellingSubmit(Employee_list);
									break;
									
								case 3:
									input.nextLine();
									consoleClear();
									Employee_list = changeDetails(Employee_list);
									break;
									
								case 4:
									input.nextLine();
									System.out.printf("\n\n  Comming soon...\n\n  Press enter to continue...");
									input.nextLine();
									break;
									
								case 5:
									input.nextLine();
									System.out.printf("\n\n  Comming soon...\n\n  Press enter to continue...");
									input.nextLine();
									break;
									
								case 6:
									input.nextLine();
									consoleClear();
									showDetails(Employee_list);
									break;
									
								case 7:
									input.nextLine();
									choice = true;
									break;
									
								default:
									input.nextLine();
									System.out.printf("\n\n  Invalid Option!!\n  Press enter to try again...");
									input.nextLine();
									break;
							}
						}
						catch(Exception e)
						{
							input.nextLine();
							System.out.printf("\n\n  The typed value is not an integer!!\n  Press enter to try again...\n\n\n\n");
							input.nextLine();
						}
					}
				}
				else if(option == 3)
				{
					spendDay(Employee_list);
				}
				else if(option == 4)
				{
					System.out.printf("\n\n\n  Program Stopped!!\n\n");
					input.close();
					System.exit(0);
				}
				else
				{
					System.out.printf("\n\n  Invalid operation !!\n  Press enter to try again...");
					input.nextLine();
				}
			}
			catch(Exception e)
			{
				input.nextLine();
				System.out.printf("\n\n  The typed value is not a Integer!!\n Press enter to try again...\n\n\n\n");
				input.nextLine();
			}
		}
	}

	public static void consoleClear()
	{
		for(int i = 0;i<30;i++)
		{
			System.out.printf("\n");
		}
	}
	
	private static Employee addEmployee(Employee new_employee, int id)
	{
		int choice;
		
		while(true)
		{
			try {
				System.out.printf("\n\n\n\n  Insert type of the Employee followed by a enter:\n\n  1 - Hourly\n  2 - Salaried\n  3 - Commissioned\n\n\n  Your option: ");
				choice = input.nextInt();
				input.nextLine();
				break;
			}
			catch(Exception e)
			{
				input.nextLine();
				System.out.printf("\n\n  The given value is not a integer!!\n  Press enter to try again...\n\n\n");
				input.nextLine();
			}
		}
		if(choice == 1)
		{
			new_employee = new Hourly();
			( (Hourly) new_employee).setPayment_week(4);
			( (Hourly) new_employee).setExtra_hour(0);
			( (Hourly) new_employee).setSalary(0);
		}
		else if(choice == 2)
		{
			new_employee = new Salaried();
			( (Salaried) new_employee).setSalaried_default(true);
			( (Salaried) new_employee).setPayment_day(dayUtil(month));
			( (Salaried) new_employee).setDays_worked(0);
			while(true)
			{
				try {
					System.out.printf("\n\n  Insert the salary per day worked: ");
					( (Salaried) new_employee).setSalary(input.nextDouble());
					input.nextLine();
					break;
				}
				catch(Exception e)
				{
					input.nextLine();
					System.out.printf("\n\n  Salary is not an integer!!\n  Press enter to try again...\n\n\n");
					input.nextLine();
				}
			}
		}
		else if(choice == 3)
		{
			new_employee = new Commissioned();
			( (Commissioned) new_employee).setPayment_week(4);
			( (Commissioned) new_employee).setSellings(0);
			( (Commissioned) new_employee).setDays_worked(0);
			( (Commissioned) new_employee).setTwo_week(0);
			while(true)
			{
				try {
					System.out.printf("\n\n  Insert the salary per day worked: ");
					( (Salaried) new_employee).setSalary(input.nextDouble());
					input.nextLine();
					break;
				}
				catch(Exception e)
				{
					input.nextLine();
					System.out.printf("\n\n  Salary is not an integer!!\n  Press enter to try again...\n\n\n");
					input.nextLine();
				}
			}
		}
		else
		{
			System.out.printf("\n\n  Invalid option!!\n  Press enter to try again...\n\n\n");
			input.nextLine();
		}
		new_employee.setID(id);
		System.out.printf("\n\n  Insert the name of the Employee followed by a enter: ");
		new_employee.setName(input.nextLine());
		System.out.printf("\n\n\n  Insert the address of the Employee followed by a enter: ");
		new_employee.setAddress(input.nextLine());
		
		while(true)
		{
			try {
				System.out.printf("\n\n\n  Insert the Payment Method:\n\n  1 - Postal Check\n  2 - Receive Check in hands\n  3 - Bank Deposit\n\n  Your option: ");
				choice = input.nextInt();
				input.nextLine();
				break;
			}
			catch(Exception e)
			{
				input.nextLine();
				System.out.printf("\n\n  The given value is not a integer!!\n  Press enter to try again...\n\n\n");
				input.nextLine();
			}
		}
		if(choice == 1 || choice == 2 || choice == 3)
		{
			new_employee.setPayment_method(choice);
		}
		else
		{
			System.out.printf("\n\n  Invalid option!!\n  Press enter to try again...\n\n\n");
			input.nextLine();
		}
				
		while(true)
		{
			try {
				System.out.printf("\n\n\n  Does this Employee belongs to the Syndicate?\n\n  0 - No\n  1 - Yes\n\n  Your option: ");
				choice = input.nextInt();
				input.nextLine();
				break;
			}
			catch(Exception e)
			{
				input.nextLine();
				System.out.printf("\n\n  The given value is not a integer!!\n  Press enter to try again...\n\n\n");
				input.nextLine();
			}
		}
		if(choice == 0)
		{
			new_employee.setSyndicate(false);
		}
		else if(choice == 1)
		{
			new_employee.setSyndicate(true);
			while(true)
			{
				try {
					System.out.printf("\n\n\n  Insert the Syndicate ID: ");
					new_employee.setSyndicate_id(input.nextInt());
					input.nextLine();
					break;
				}
				catch(Exception e)
				{
					input.nextLine();
					System.out.printf("\n\n\n  The given ID is not an integer!!\n\n  Press enter to try again...\n\n\n");
					input.nextLine();
				}
			}
			
			while(true)
			{
				try {
					System.out.printf("\n\n\n  Insert the Syndicate Tax: ");
					new_employee.setSyndicate_tax(input.nextDouble());
					input.nextLine();
					break;
				}
				catch(Exception e)
				{
					input.nextLine();
					System.out.printf("\n\n\n  The given Syndicate Tax is not an numeric value!!\n\n  Press enter to try again...\n\n\n");
					input.nextLine();
				}
			}
			
			new_employee.setService_taxes(0);
		}
		else
		{
			System.out.printf("\n\n  Invalid option!!\n  Press enter to try again...\n\n\n");
			input.nextLine();
		}
				
		System.out.printf("\n\n  Employee ID: %d\n  Name: %s\n  Address: %s\n  Type: ", new_employee.getID(), new_employee.getName(), new_employee.getAddress());
		if(new_employee instanceof Hourly)
		{
			System.out.printf("Hourly\n");
			System.out.printf("  Salary per Hour Worked: 30.00\n  Salary: %.2f\n", new_employee.getSalary());
		}
		else if(new_employee instanceof Salaried)
		{
			System.out.printf("Salaried\n");
			System.out.printf("  Salary per Hour Worked: %.2f\n  Salary: 0.00\n", new_employee.getSalary());
		}
		else if(new_employee instanceof Commissioned)
		{
			System.out.printf("Commissioned\n");
			System.out.printf("  Salary per Hour Worked: %.2f\n  Salary: 0.00\n", new_employee.getSalary());
		}
		
		if(new_employee.isSyndicate())
		{
			System.out.printf("  This Employee belongs to the Syndicate.\n");
			System.out.printf("  Syndicate ID: %d\n  Syndicate Tax: %.2f\n  Syndicate Services Taxes: %.2f\n", new_employee.getSyndicate_id(), new_employee.getSyndicate_tax(), new_employee.getService_taxes());
		}
		else
		{
			System.out.printf("  This Employee does not belong to the Syndicate.\n");
		}
		
		System.out.printf("\n\n\n Save your ID number!!!!\n\n");
        System.out.printf("\n\n    Employee added in the system!\n\n");
        System.out.printf("\n\n Press enter to continue...\n\n\n\n");
        input.nextLine();
        return new_employee;
	}
	
	private static Employee[] timeCard(Employee list[])
	{
		int given;
		while(true)
		{
			try {
				System.out.printf("\n\n  Insert your ID: ");
				given = input.nextInt();
				input.nextLine();
				if(list[given] != null)
				{
					
					
					
					
					System.out.println("\n\n  Insert the hours in the format : 'HH:mm' where 'HH' is hours and 'mm' minutes, followed by enter\n\n Be sure to check this: if hour/minutes < 10 put '01', '09'.!!\n\n ");
		            System.out.printf("\n\n  Are you comming now? (1 - yes / 0 - no)\n\n  Your option: ");
		            int choice = input.nextInt();
		            input.nextLine();
		            if(choice == 0)
		            {
		                System.out.printf("\n\n\n  Insert the hours (end): ");
		                String leave = input.nextLine();
		                SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		                Date start = null;
		                Date end = null;
		                try 
		                {
		                    start = format.parse(list[given].getArrival_time());
		                    end = format.parse(leave);

		                    long diff = end.getTime() - start.getTime();
		                    int diffHours = (int)diff / (60 * 60 * 1000) % 24;
		                    diffHours = Math.abs(diffHours);
		                    if(start.getTime() > end.getTime())
		                    {
		                        diffHours = 24 % diffHours;
		                    }
		                    System.out.print("\n\n\n\n  " + diffHours + " hours today\n\n ");
		                    int hour = diffHours;
		                    
		                    ( (Hourly) list[given]).addHours(hour); // total worked hours
		                    if(hour > 8 && list[given] instanceof Hourly)// hourly employees only
		                    {
		                        int limit = (hour - 8);
		                        double bonus = (limit * 1.5 * 30.00);
		                        ( (Hourly) list[given]).addExtra(bonus);
		                        System.out.printf("\n\n  Extra salary: %.2f\n\n ", ( (Hourly) list[given]).getExtra_hour());
		                    }
		                    if(list[given] instanceof Hourly)
		                    {
		                        System.out.printf("\n\n Current Salary: %.2f\n Today salary: %.2f\n Total (No Extra included) : %.2f\n\n ", list[given].getSalary(), (hour * 30.00), list[given].getSalary() + (hour * 30.00));
		                        list[given].addSalary(hour * 30.00);
		                    }
		                    else
		                    {
		                        if(day_of_week != 5 && day_of_week != 6)
		                        {
		                            if(list[given] instanceof Salaried)
		                            {
		                            	( (Salaried) list[given]).addDay();
		                            }
		                            else if(list[given] instanceof Commissioned)
		                            {
		                            	( (Commissioned) list[given]).addDay();
		                            }
		                        }
		                    }
		                    System.out.printf("\n\n    Time Card approved!\n\n\n\n\n\n\n ");
		                    System.out.printf("\n\n\n      Press any key to continue...\n\n ");
		                    input.nextLine();
		                } 
		                catch(Exception e)
		                {
		                    e.printStackTrace();
		                }
		            }
		            else
		            {
		                System.out.printf("\n\n\n Insert the hours (start): ");
		                list[given].setArrival_time(input.nextLine());
		                
		                System.out.println("\n\n    Time Card Approved!\n\n");
		                System.out.printf("\n\n\n      Press any key to continue...\n\n ");
		                input.nextLine();
		            }
					
					
					
					
					
					break;
				}
				else
				{
					System.out.printf("\n\n  There's no Employee with this Identification!!\n\n  Press enter to return to Adminstrator Functions...\n\n\n");
					input.nextLine();
					return list;
				}
			}
			catch(Exception e)
			{
				input.nextLine();
				System.out.printf("\n\n The typed value is not an integer!!\n Press enter to try again...\n\n\n\n");
				input.nextLine();
			}
			
		}
		
		return list;
	}
	
	private static void showDetails(Employee list[])
	{
		double result;
		int i;
		while(true)
		{
			try {
				System.out.printf("\n\n  Insert the ID from the Employee: ");
				i = input.nextInt();
				input.nextLine();
				break;
			}
			catch(Exception e)
			{
				input.nextLine();
				System.out.printf("\n\n\n  the typed ID is not an integer!!\n\n  Press enter to try again...\n\n\n\n");
				input.nextLine();
			}
		}
		if(list[i] != null)
		{
			System.out.printf("\n\n Name: %s\n Address: %s\n Type:", list[i].getName(), list[i].getAddress());
			if(list[i] instanceof Hourly)
			{
				System.out.println(" Hourly");
	                System.out.printf(" Salary per hour worked: 30,00\n Salary (No Extra): %.2f\n Extra Salary: %.2f\n Payment: ", list[i].getSalary(), ( (Hourly) list[i]).getExtra_hour());
			}
			else if(list[i] instanceof Salaried)
			{
				result = list[i].getSalary() * ( (Salaried) list[i]).getDays_worked();
	                System.out.println(" Salaried");
	                System.out.printf(" Salary per day worked: %.2f\n Salary: %.2f\n Payment: ", list[i].getSalary(), result);
			}
			else if(list[i] instanceof Commissioned)
			{
				result = list[i].getSalary() * ( (Commissioned) list[i]).getDays_worked();
	                System.out.println(" Commissioned");
	                System.out.printf(" Salary per day worked: %.2f\n Salary: %.2f\n Selling Results: %.2f\n Payment: ", list[i].getSalary(), result, ( (Commissioned) list[i]).getSellings());
			}
			else
			{
				System.out.printf(" NULL");
			}
		       
		    switch(list[i].getPayment_method())
		    {
		        case 1:
		        System.out.println("Postal Check");break;
		        case 2:
		        System.out.println("Check in hands");break;
		        case 3:
		        System.out.println("Bank Deposit");break;
		        default:
		        System.out.printf("NULL");break;
		    }
		    if(list[i].isSyndicate())
		    {
		    	if(list[i] instanceof Hourly)
		    	{
		    		System.out.printf(" Belong to Syndicate\n Syndicate ID: %d\n Syndicate Tax: %.2f\n Syndicate Service Tax: %.2f\n Total Hours Worked: %d\n\n ", list[i].getSyndicate_id(), list[i].getSyndicate_tax(), list[i].getService_taxes(), ( (Hourly) list[i]).getTotal_hours());
		    	}
		    	else
		    	{
		    		System.out.printf(" Belong to Syndicate\n Syndicate ID: %d\n Syndicate Tax: %.2f\n Syndicate Service Tax: %.2f\n\n ", list[i].getSyndicate_id(), list[i].getSyndicate_tax(), list[i].getService_taxes());
		    	}
		    }
		    else
		    {
		    	if(list[i] instanceof Hourly)
		    	{
		    		System.out.printf(" Not in Syndicate\n Total Hours Worked: %d\n\n", ( (Hourly) list[i]).getTotal_hours());
		    	}
		    	else
		    	{
		    		System.out.printf(" Not in Syndicate\n\n");
		    	}
		    }
		    System.out.printf("\n\n Press enter to return to Functions...\n\n\n");
		    input.nextLine();
		}
		else
		{
		    System.out.println("\n\n\n\n    Invalid ID!!\n\n");
		    System.out.printf("\n\n    Press enter to return to Functions...\n\n\n");
		    input.nextLine();
		}
		
	}
	
	private static Employee[] changeDetails(Employee list[])
	{
		int i;
		while(true)
		{
			try {
				System.out.printf("\n\n  Insert the ID from the Employee: ");
				i = input.nextInt();
				input.nextLine();
				break;
			}
			catch(Exception e)
			{
				input.nextLine();
				System.out.printf("\n\n The typed ID is not a Integer!!\n Press enter to try again...\n\n\n\n");
				input.nextLine();
			}
		}
		if(list[i] != null)
		{
			int choice;
			int dado;
			while(true)
            {
				while(true)
				{
					try {
						System.out.printf("\n\n       //////////////////////////////////////////////////////////////////////\n");
			              System.out.printf("       ///                                                                ///\n");
			              System.out.printf("       ///                   Insert what will you change:                 ///\n");
			              System.out.printf("       ///                                                                ///\n");
			              System.out.printf("       /// 1 - Name                                                       ///\n");
			              System.out.printf("       /// 2 - Address                                                    ///\n");
			              System.out.printf("       /// 3 - Type                                                       ///\n");
			              System.out.printf("       /// 4 - Payment Method                                             ///\n");
			              System.out.printf("       /// 5 - Syndicate Status                                           ///\n");
			              System.out.printf("       /// 6 - Syndicate ID                                               ///\n");
			              System.out.printf("       /// 7 - Syndicate Tax                                              ///\n");
			              System.out.printf("       /// 8 - Return to menu                                             ///\n");
			              System.out.printf("       ///                                                                ///\n");
			              System.out.printf("       //////////////////////////////////////////////////////////////////////\n\n\n  Your Option: ");
			              choice = input.nextInt();
			              input.nextLine();
			              break;
					}
					catch(Exception e)
					{
						input.nextLine();
						System.out.printf("\n\n\n\n  Invalid option!!\n\n  Press enter to try again...\n\n\n");
						input.nextLine();
					}
				}
              switch(choice)
              {
                case 1:
                    System.out.println("\n\n\n  Insert the new name followed by a enter: ");
                    list[i].setName(input.nextLine());
                    System.out.println("\n\n    Change done!!\n\n ");
                    break;
    
                case 2:
                    System.out.println("\n\n\n  Insert the new address followed by a enter: ");
                    input.nextLine();
                    list[i].setAddress(input.nextLine());
                    System.out.println("\n\n    Change done!!\n\n ");
                    break;
    
                case 3:
//                    System.out.println("Insert the new type followed by a enter ('hourly' / 'salaried' / 'commissioned'):");
//                    String given = input.nextLine();
//                    if("hourly".equalsIgnoreCase(given))
//                    {
//    TODO                	list[i] = new Hourly();
//                        two_week[id] = 0;
//                        days_worked[id] = 0;
//                        selling_result[id] = 0;
//                        extra_hour[id] = 0;
//                        System.out.printf("\n\n Insert the salary per hour: ");
//                        salary[id] = input.nextDouble();
//                        input.nextLine();
//                        System.out.printf("\n\n ");
//                        payment_week[id] = 4; 
//                    }
//                    else if("salaried".equalsIgnoreCase(given))
//                    {
//                        two_week[id] = 0;
//                        salaried_default[id] = 1;
//                        days_worked[id] = 0;
//                        extra_hour[id] = 0;
//                        selling_result[id] = 0;
//                        System.out.printf("\n\n Insert the salary per day: ");
//                        salary[id] = input.nextDouble();
//                        input.nextLine();
//                        System.out.printf("\n\n ");
//                        payment_day[id] = dayUtil(month);
//                    }
//                    else if("commissioned".equalsIgnoreCase(given))
//                    {
//                        two_week[id] = 0;
//                        days_worked[id] = 0;
//                        extra_hour[id] = 0;
//                        selling_result[id] = 0;
//                        System.out.printf("\n\n Insert the salary per day: ");
//                        salary[id] = input.nextDouble();
//                        input.nextLine();
//                        System.out.printf("\n\n ");
//                        payment_week[id] = 4;
//                    }
//                    else
//                    {
//                        System.out.printf("\n\n Invalid Type...Setting to Default: 'hourly'\n\n");
//                        days_worked[id] = 0;
//                        selling_result[id] = 0;
//                        extra_hour[id] = 0;
//                        System.out.printf("\n\n Insert the salary per hour: ");
//                        salary[id] = input.nextDouble();
//                        input.nextLine();
//                        System.out.printf("\n\n ");
//                        given = "hourly";
//                        payment_week[id] = 4;
//                    }
//                    type[id] = given;
//                    System.out.println("\n\n    Change done!!\n\n ");
                    break;
    
                case 4:
                	while(true)
                	{
                		try {
                			System.out.printf("\n\n\n  Choose your Payment Method: \n\n 0 - Postal Check via Mail\n 1 - Receive Postal Check in hands\n 2 - Bank Deposit\n\n  Your choice: ");
                            dado = input.nextInt();
                            input.nextLine();
                            break;
                		}
                		catch(Exception e)
                		{
                			input.nextLine();
                			System.out.printf("\n\n\n  The given value is not an integer!!\n\n  Press enter to try again...\n\n\n");
                			input.nextLine();
                		}
                	}
                    
                    if(dado == 0 || dado == 1 || dado == 2)
                    {
                        list[i].setPayment_method(dado);
                        System.out.println("\n\n    Change done!!\n\n ");
                    }
                    else
                    {
                        System.out.println("\n\n  Invalid Method!!\n\n  Press enter to go back to options...\n\n\n");
                        input.nextLine();
                    }
                    break;
    
                case 5:
                	while(true)
                	{
                		try {
                			System.out.println("\n\n\n  Insert your new Syndicate status: ( 1 - belong to syndicate // 0 - not in syndicate)\n\n  Your option: ");
                            dado = input.nextInt();
                            input.nextLine();
                            break;
                		}
                		catch(Exception e)
                		{
                			input.nextLine();
    						System.out.printf("\n\n\n\n  Invalid value!!\n\n  Press enter to try again...\n\n\n");
    						input.nextLine();
                		}
                	}
                    if(dado == 1)
                    {
                        list[i].setSyndicate(true);
                        while(true)
                        {
                        	try {
                        		System.out.printf("\n\n Insert your Syndicate ID (Numbers Only): ");
                                list[i].setSyndicate_id(input.nextInt());
                                input.nextLine();
                                break;
                        	}
                        	catch(Exception e)
                    		{
                    			input.nextLine();
        						System.out.printf("\n\n\n\n  The value of this ID is not an integer!!\n\n  Press enter to try again...\n\n\n");
        						input.nextLine();
                    		}
                        }
                        
                        while(true)
                        {
                        	try {
                        		System.out.printf("\n\n Insert your Syndicate Tax (Numbers Only): ");
                                list[i].setSyndicate_tax(input.nextDouble());
                                input.nextLine();
                                break;
                        	}
                        	catch(Exception e)
                    		{
                    			input.nextLine();
        						System.out.printf("\n\n\n\n  The value of this Syndicate tax is not an numeric value!!\n\n  Press enter to try again...\n\n\n");
        						input.nextLine();
                    		}
                        }
                        
                        list[i].setService_taxes(0);
                        System.out.println("\n\n\n    Change done!!\n\n ");
                    }
                    else if(dado == 0)
                    {
                    	list[i].setSyndicate(false);
                        list[i].setSyndicate_id(-1);
                        list[i].setService_taxes(0);
                        list[i].setSyndicate_tax(0);
                        System.out.println("\n\n\n    Change done!!\n\n ");
                    }
                    else
                    {
                        System.out.println("\n\n  Invalid option!!\n\n ");
                    }
                    break;
    
                case 6:
                    if(list[i].isSyndicate())
                    {
                    	while(true)
                        {
                        	try {
                        		System.out.printf("\n\n\n  Insert the new Syndicate ID (Integer Numbers Only): ");
                                list[i].setSyndicate_id(input.nextInt());
                                input.nextLine();
                                break;
                        	}
                        	catch(Exception e)
                    		{
                    			input.nextLine();
        						System.out.printf("\n\n\n\n  The value of this ID is not an integer!!\n\n  Press enter to try again...\n\n\n");
        						input.nextLine();
                    		}
                        }
                    	
                        System.out.println("\n\n    Change done!!\n\n ");
                    }
                    else
                    {
                        System.out.printf("\n\n    This Employee does not belong to the Syndicate!!\n\n");
                    }
                    
                    break;
    
                case 7:
                    if(list[i].isSyndicate())
                    {
                    	while(true)
                        {
                        	try {
                        		System.out.printf("\n\n\n  Insert your Syndicate Tax (Numbers Only): ");
                                list[i].setSyndicate_tax(input.nextDouble());
                                input.nextLine();
                                break;
                        	}
                        	catch(Exception e)
                    		{
                    			input.nextLine();
        						System.out.printf("\n\n\n\n  The value of this Syndicate tax is not an numeric value!!\n\n  Press enter to try again...\n\n\n");
        						input.nextLine();
                    		}
                        }
                        
                        System.out.println("\n\n    Change done!!\n\n ");
                    }
                    else
                    {
                        System.out.printf("\n\n    This Employee does not belong to the Syndicate!!\n\n");
                    }
                    
                    break;
    
                case 8:
                    return list;

                default:
                    System.out.printf("\n\n   Invalid Option!! / Press enter to try again...\n\n\n");
                    input.nextLine();
                    
                    return changeDetails(list);

              }
              System.out.printf("\n\n\n Press enter to go back to options...\n\n\n");
              input.nextLine();
            }
		}
		else
		{
			System.out.printf("\n\n\n  There's no Employee with this ID in the System!!\n\n  Press enter to go back to Functions...\n\n\n");
			input.nextLine();
		}
		
		return list;
	}
	
	private static Employee[] sellingSubmit(Employee list[])
	{
		int i;
		while(true)
		{
			try {
				System.out.printf("\n\n\n  Insert Your ID: ");
				i = input.nextInt();
				input.nextLine();
				break;
			}
			catch(Exception e)
			{
				input.nextLine();
				System.out.printf("\n\n\n  The given ID is not valid!!\n\n  Press enter to try again...\n\n\n");
				input.nextLine();
			}
		}
		if(list[i] != null)
		{
			if(list[i] instanceof Commissioned)
			{
				while(true)
				{
					try {
						System.out.printf("\n\n\n  Insert the Selling Value: ");
						( (Commissioned)list[i]).addSelling(input.nextDouble());
						input.nextLine();
						break;
					}
					catch(Exception e)
					{
						input.nextLine();
						System.out.printf("\n\n\n  The given Selling is not an numeric value!!\n\n  Press enter to try again...\n\n\n");
						input.nextLine();
					}
				}
				System.out.printf("\n\n\n     Selling added to User: %s with ID: %d\n\n", list[i].getName(), list[i].getID());
				
			}
			else
				System.out.printf("\n\n  This Employee is not Commissioned!!\n\n");
			
		}
		else
			System.out.printf("\n\n\n  There's no Employee with this ID in the System!!\n\n");
		System.out.printf("\n\n\n  Press enter to return to Functions...\n\n\n");
		input.nextLine();
		
		return list;
	}
	
	private static int dayUtil(int mth)
    {
        int result = last_day_month[mth];
        int week = day_of_week;
        int d = day;
        for(int i = d;i <= last_day_month[mth];i++)
        {
            week++;
            if(week == 7)
            {
                week = 0;
            }
        }
        switch(week)
        {
            case 5:
                result--;
                break;

            case 6:
                result -= 2;
                break;
        }
        return result;
    }
	
	private static void spendDay(Employee list[])
    {
        int i;
        day++;
        day_of_week++;
        if(day_of_week == 7)// day of week
        {
            day_of_week = 0;
        }
        if(day == last_day_month[month] + 1)
        {
            day = 1;
            month++;
            if(month == 13)
            {
                for(i=0;i<1000;i++)// refresh the received syndicate tax (mensal tax)
                {
                    if(list[i] != null)
                    {
                    	list[i].setReceived_tax(false);
                    }
                }
                month = 1;
            }
            for(i = 0;i<1000;i++)// refresh payment days for default salaried
            {
                if(list[i] != null)
                {
                    if(list[i] instanceof Salaried)
                    {
                    	if(( (Salaried)list[i]).isSalaried_default())
                    	{
                    		( (Salaried)list[i]).setPayment_day(dayUtil(month)); // refresh salaried default day of payment for each month
                    	}
                    }
                }
            }
        }
        for(i = 0; i < 1000 ; i++)
        {
            if(list[i] != null)
            {
                if(list[i] instanceof Commissioned)
                {
                	if(( (Commissioned)list[i]).getPayment_week() == day_of_week)
                	{
                		( (Commissioned)list[i]).addWeek();
                	}
                }
            }
        }
    }
	
}
