package paymentSystemOO;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

	static Scanner input = new Scanner(System.in);
	
	//Date Settings

		private static int day = 1;// starting the year
		private static int month = 1;// ^^^^^^^^^^^^
		private static String week_name[] = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};// aux for printing the day of week
		private static int day_of_week = 1;// this controls the day of week
		private static int last_day_month[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};// last day of every month (not considering bisect years)
		
	/////////////////////
	///               ///
	///   TEMPORARY   /// TODO
	///               ///
	/////////////////////

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
			System.out.printf("\n       //////////////////////////////////\n");
			System.out.printf("       ///                            ///\n");
			System.out.printf("       ///          Main Menu         ///\n");
			System.out.printf("       ///                            ///\n");
			System.out.printf("       /// 1 - Admin Functions        ///\n");
			System.out.printf("       /// 2 - Employee Functions     ///\n");
			System.out.printf("       /// 3 - Exit                   ///\n");
			System.out.printf("       ///                            ///\n");
			System.out.printf("       //////////////////////////////////\n\n");
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
									input.nextLine();
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
									System.out.printf("\n\n Comming soon...\n\n Press enter to continue...");
									input.nextLine();
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
						System.out.printf("       /// 6 - Payment Schedules                        ///\n");
						System.out.printf("       /// 7 - Show Details of an Employee              ///\n");
						System.out.printf("       /// 8 - Back to Main Menu                        ///\n");
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
									System.out.printf("\n\n Comming soon...\n\n Press enter to continue...");
									input.nextLine();
									break;
									
								case 3:
									input.nextLine();
									System.out.printf("\n\n Comming soon...\n\n Press enter to continue...");
									input.nextLine();
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
									consoleClear();
									showDetails(Employee_list);
									break;
									
								case 8:
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
				else if(option == 3)
				{
					System.out.printf("\n\n\n Program Stopped!!\n\n");
					input.close();
					System.exit(0);
				}
				else
				{
					System.out.printf("\n\n Invalid operation !!\n Press enter to try again...");
					input.nextLine();
				}
			}
			catch(Exception e)
			{
				input.nextLine();
				System.out.printf("\n\n The typed value is not a Integer!!\n Press enter to try again...\n\n\n\n");
				input.nextLine();
			}
		}
	}

	public static void consoleClear()
	{
		for(int i = 0;i<20;i++)
		{
			System.out.printf("\n");
		}
	}
	
	public static Employee addEmployee(Employee new_employee, int id)
	{
		int choice;
		System.out.printf("\n\n  Insert type of the Employee followed by a enter: ");
		while(true)
		{
			try {
				
				choice = input.nextInt();
				input.nextLine();
				if(choice == 1)
				{
					new_employee = new Hourly();
					( (Hourly) new_employee).setPayment_week(4);
					( (Hourly) new_employee).setSalary(0);
					break;
				}
				else if(choice == 2)
				{
					new_employee = new Salaried();
					( (Salaried) new_employee).setSalaried_default(true);
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
					break;
				}
				else if(choice == 3)
				{
					new_employee = new Commissioned();
					( (Commissioned) new_employee).setPayment_week(4);
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
					break;
				}
				else
				{
					System.out.printf("\n\n  Invalid option!!\n  Press enter to try again...\n\n\n");
					input.nextLine();
				}
				
			}
			catch(Exception e)
			{
				input.nextLine();
				System.out.printf("\n\n  The given value is not a integer!!\n  Press enter to try again...\n\n\n");
				input.nextLine();
			}
		}
		new_employee.setID(id);
		System.out.printf("\n\n  Insert the name of the Employee followed by a enter: ");
		new_employee.setName(input.nextLine());
		System.out.printf("\n\n\n  Insert the address of the Employee followed by a enter: ");
		new_employee.setAddress(input.nextLine());
		System.out.printf("\n\n\n  Insert the Payment Method:\n\n  1 - Postal Check\n  2 - Receive Check in hands\n  3 - Bank Deposit\n\n  Your option: ");
		while(true)
		{
			try {
				
				choice = input.nextInt();
				input.nextLine();
				if(choice == 1 || choice == 2 || choice == 3)
				{
					new_employee.setPayment_method(choice);
					break;
				}
				else
				{
					System.out.printf("\n\n  Invalid option!!\n  Press enter to try again...\n\n\n");
					input.nextLine();
				}
				
			}
			catch(Exception e)
			{
				input.nextLine();
				System.out.printf("\n\n  The given value is not a integer!!\n  Press enter to try again...\n\n\n");
				input.nextLine();
			}
		}
		
		while(true)
		{
			try {
				System.out.printf("\n\n\n  Does this Employee belongs to the Syndicate?\n\n  0 - No\n  1 - Yes\n\n  Your option: ");
				choice = input.nextInt();
				input.nextLine();
				if(choice == 0)
				{
					new_employee.setSyndicate(false);
					break;
				}
				else if(choice == 1)
				{
					new_employee.setSyndicate(true);
//	TODO LATER		while(true)
//					{
//						System.out.printf("\n\n  Insert the Syndicate ID: ");
//						
//					}
					
					break;
				}
				else
				{
					System.out.printf("\n\n  Invalid option!!\n  Press enter to try again...\n\n\n");
					input.nextLine();
				}
				
			}
			catch(Exception e)
			{
				input.nextLine();
				System.out.printf("\n\n  The given value is not a integer!!\n  Press enter to try again...\n\n\n");
				input.nextLine();
			}
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
			//System.out.printf("  Syndicate ID: ");
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
	
	public static Employee[] timeCard(Employee list[])
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
					
					
					
					
					System.out.println("\n\n Insert the hours in the format : 'HH:mm' where 'HH' is hours and 'mm' minutes, followed by enter\n\n Be sure to check this: if hour/minutes < 10 put '01', '09'.!!\n\n ");
		            System.out.println("\n\n Are you comming now? (1 - yes / 0 - no)   ");
		            int choice = input.nextInt();
		            input.nextLine();
		            if(choice == 0)
		            {
		                System.out.printf("\n\n\n Insert the hours (end): ");
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
		                input.nextLine();
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
	
	public static void showDetails(Employee list[])
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
	                System.out.printf(" Salary per hour worked: 30.0\n Salary (No Extra): %.2f\n Extra Salary: %.2f\n Payment: ", list[i].getSalary(), ( (Hourly) list[i]).getExtra_hour());
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
		    		System.out.printf(" Belong to Syndicate\n Syndicate ID: \n Syndicate Tax: \n Service Tax: \n Hours: %d\n\n ", ( (Hourly) list[i]).getTotal_hours());
		    	}
		    	// TODO
		    	else
		    	{
		    		System.out.printf(" Belong to Syndicate\n\n ");
		    	}
		    }
		    else
		    {
		    	if(list[i] instanceof Hourly)
		    	{
		    		System.out.printf(" Not in Syndicate\n Total Hours Worked (Hourly): %d\n\n", ( (Hourly) list[i]).getTotal_hours());
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
	
}
