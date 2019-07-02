package systemFunctions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import paymentSystemOO.Commissioned;
import paymentSystemOO.Employee;
import paymentSystemOO.UtilityFunctions;
import paymentSystemOO.Hourly;
import paymentSystemOO.Salaried;

public abstract class EmployeeRelated {
	
	private static Scanner input = new Scanner(System.in);
	
	public static Employee addEmployee(Employee new_employee, int id, int month)
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
			catch(InputMismatchException e)
			{
				input.nextLine();
				System.out.printf("\n\n  The given value is not a integer!!\n  Press enter to try again...\n\n\n");
				input.nextLine();
			}
		}
		if(choice == 1)
		{
			new_employee = new Hourly();
			( (Hourly) new_employee).setPayment_date(4);
			( (Hourly) new_employee).setExtra_salary(0);
			( (Hourly) new_employee).setSalary(0);
		}
		else if(choice == 2)
		{
			new_employee = new Salaried();
			( (Salaried) new_employee).setSalaried_default(true);
			( (Salaried) new_employee).setPayment_date(UtilityFunctions.dayUtil(month));
			( (Salaried) new_employee).setDays_worked(0);
			while(true)
			{
				try {
					System.out.printf("\n\n  Insert the salary per day worked: ");
					( (Salaried) new_employee).setSalary(input.nextDouble());
					input.nextLine();
					break;
				}
				catch(InputMismatchException e)
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
			( (Commissioned) new_employee).setPayment_date(4);
			( (Commissioned) new_employee).setSellings(0);
			( (Commissioned) new_employee).setDays_worked(0);
			( (Commissioned) new_employee).setTwo_week(0);
			while(true)
			{
				try {
					System.out.printf("\n\n  Insert the salary per day worked: ");
					( (Commissioned) new_employee).setSalary(input.nextDouble());
					input.nextLine();
					break;
				}
				catch(InputMismatchException e)
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
			catch(InputMismatchException e)
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
			catch(InputMismatchException e)
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
				catch(InputMismatchException e)
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
				catch(InputMismatchException e)
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
        
        UndoRedo.setChange_made(true);
        
        return new_employee;
	}
	
	public static Employee[] timeCard(Employee list[], int day_of_week)
	{
		int given;
		int choice;
		while(true)
		{
			try {
				System.out.printf("\n\n  Insert your ID: ");
				given = input.nextInt();
				input.nextLine();
				if(list[given] != null)
				{
					
					
					
					
					System.out.println("\n\n  Insert the hours in the format : 'HH:mm' where 'HH' is hours and 'mm' minutes, followed by enter\n\n Be sure to check this: if hour/minutes < 10 put '01', '09'.!!\n\n ");
					while(true)
					{
						try{
							System.out.printf("\n\n  Are you comming now? (1 - yes / 0 - no)\n\n  Your option: ");
		            		choice = input.nextInt();
		            		input.nextLine();
		            		break;
						}
						catch(InputMismatchException e)
						{
							input.nextLine();
							System.out.printf("\n\n\n  the typed option is not an integer!!\n\n  Press enter to try again...\n\n\n");
							input.nextLine();
						}
					}
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
		                    
		                    if(hour > 8 && list[given] instanceof Hourly)// hourly employees only
		                    {
		                        int limit = (hour - 8);
		                        double bonus = (limit * 1.5 * 30.00);
		                        ( (Hourly) list[given]).addHours(hour); // total worked hours
		                        ( (Hourly) list[given]).addExtra(bonus);
		                        System.out.printf("\n\n  Extra salary: %.2f\n\n ", ( (Hourly) list[given]).getExtra_salary());
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
		                catch(ParseException e)
		                {
		                	e.printStackTrace();
		                	System.out.printf("\n\n\n  Failed to Submit a timecard!!\n\n  Check if you typed the start hour or the end hour correctly!!\n\n  Press enter to Return to Functions...\n\n\n");
		                	input.nextLine();
		                    
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
		            UndoRedo.setChange_made(true);
					
					
					
					
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
	
	public static Employee[] changeDetails(Employee list[], int month)
	{
		Employee new_type;
		int i;
		while(true)
		{
			try {
				System.out.printf("\n\n  Insert the ID from the Employee: ");
				i = input.nextInt();
				input.nextLine();
				break;
			}
			catch(InputMismatchException e)
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
			int given;
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
					catch(InputMismatchException e)
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
                    UndoRedo.setChange_made(true);
                    break;
    
                case 2:
                    System.out.println("\n\n\n  Insert the new address followed by a enter: ");
                    input.nextLine();
                    list[i].setAddress(input.nextLine());
                    System.out.println("\n\n    Change done!!\n\n ");
                    UndoRedo.setChange_made(true);
                    break;
    
                case 3:
                	while(true)
                	{
                		while(true)
                    	{
                    		try {
                    			System.out.println("\n\n\n  Insert the new type followed by a enter:\n\n  1 - Hourly\n  2 - Salaried\n  3 - Commissioned\n  4 - Cancel\n\n\n  Option: ");
                                given = input.nextInt();
                                input.nextLine();
                                break;
                    		}
                    		catch(InputMismatchException e)
                    		{
                    			input.nextLine();
                    			System.out.printf("\n\n\n  Invalid type value!!\n\n  Press enter to try again...");
                    		}
                    	}
                        
                        if(given == 1)
                        {
                        	new_type = new Hourly();
                            new_type.setAddress(list[i].getAddress());
                            new_type.setArrival_time(list[i].getArrival_time());
                            new_type.setID(list[i].getID());
                            new_type.setName(list[i].getName());
                            new_type.setPayment_method(list[i].getPayment_method());
                            new_type.setReceived_tax(list[i].isReceived_tax());
                            new_type.setSalary(0);
                            new_type.setService_taxes(list[i].getService_taxes());
                            new_type.setSyndicate(list[i].isSyndicate());
                            new_type.setSyndicate_id(list[i].getSyndicate_id());
                            new_type.setSyndicate_tax(list[i].getSyndicate_tax());
                            
                            ( (Hourly) new_type).setPayment_date(4);
                			( (Hourly) new_type).setExtra_salary(0);
                			
                            
                            list[i] = new_type;
                            
                            System.out.println("\n\n    Change done!!\n\n  Press enter to return to Functions...\n\n\n\n");
                            input.nextLine();
                        	UndoRedo.setChange_made(true);
                        }
                        else if(given == 2)
                        {
                        	new_type = new Salaried();
                            new_type.setAddress(list[i].getAddress());
                            new_type.setArrival_time(list[i].getArrival_time());
                            new_type.setID(list[i].getID());
                            new_type.setName(list[i].getName());
                            new_type.setPayment_method(list[i].getPayment_method());
                            new_type.setReceived_tax(list[i].isReceived_tax());
                            new_type.setService_taxes(list[i].getService_taxes());
                            new_type.setSyndicate(list[i].isSyndicate());
                            new_type.setSyndicate_id(list[i].getSyndicate_id());
                            new_type.setSyndicate_tax(list[i].getSyndicate_tax());
                            
                            while(true)
                            {
                            	try {
                            		System.out.printf("\n\n  Insert the salary per day: ");
                                    new_type.setSalary(input.nextDouble());
                                    input.nextLine();
                                    break;
                            	}
                            	catch(InputMismatchException e)
                            	{
                            		input.nextLine();
                            		System.out.printf("\n\n\n  The typed salary is not an numeric value!!\n\n  Press enter to try again...\n\n\n\n");
                            		input.nextLine();
                            	}
                            }
                            
                            ( (Salaried) new_type).setSalaried_default(true);
                			( (Salaried) new_type).setPayment_date(UtilityFunctions.dayUtil(month));
                			( (Salaried) new_type).setDays_worked(0);
                			
                			list[i] = new_type;
                            
                            System.out.println("\n\n    Change done!!\n\n  Press enter to return to Functions...\n\n\n\n");
                            input.nextLine();
                        	UndoRedo.setChange_made(true);
                        }
                        else if(given == 3)
                        {
                        	new_type = new Commissioned();
                            new_type.setAddress(list[i].getAddress());
                            new_type.setArrival_time(list[i].getArrival_time());
                            new_type.setID(list[i].getID());
                            new_type.setName(list[i].getName());
                            new_type.setPayment_method(list[i].getPayment_method());
                            new_type.setReceived_tax(list[i].isReceived_tax());
                            new_type.setService_taxes(list[i].getService_taxes());
                            new_type.setSyndicate(list[i].isSyndicate());
                            new_type.setSyndicate_id(list[i].getSyndicate_id());
                            new_type.setSyndicate_tax(list[i].getSyndicate_tax());
                            
                            while(true)
                            {
                            	try {
                            		System.out.printf("\n\n  Insert the salary per day: ");
                                    new_type.setSalary(input.nextDouble());
                                    input.nextLine();
                                    break;
                            	}
                            	catch(InputMismatchException e)
                            	{
                            		input.nextLine();
                            		System.out.printf("\n\n\n  The typed salary is not an numeric value!!\n\n  Press enter to try again...\n\n\n\n");
                            		input.nextLine();
                            	}
                            }
                            
                            ( (Commissioned) new_type).setPayment_date(4);
                			( (Commissioned) new_type).setSellings(0);
                			( (Commissioned) new_type).setDays_worked(0);
                			( (Commissioned) new_type).setTwo_week(0);
                            
                            System.out.println("\n\n    Change done!!\n\n  Press enter to return to Functions...\n\n\n\n");
                            input.nextLine();
                            UndoRedo.setChange_made(true);
                        }
                        else if(given == 4)
                        {
                            break;
                        }
                	}
                	
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
                		catch(InputMismatchException e)
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
                        UndoRedo.setChange_made(true);
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
                		catch(InputMismatchException e)
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
                        	catch(InputMismatchException e)
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
                        	catch(InputMismatchException e)
                    		{
                    			input.nextLine();
        						System.out.printf("\n\n\n\n  The value of this Syndicate tax is not an numeric value!!\n\n  Press enter to try again...\n\n\n");
        						input.nextLine();
                    		}
                        }
                        
                        list[i].setService_taxes(0);
                        System.out.println("\n\n\n    Change done!!\n\n ");
                        UndoRedo.setChange_made(true);
                    }
                    else if(dado == 0)
                    {
                    	list[i].setSyndicate(false);
                        list[i].setSyndicate_id(-1);
                        list[i].setService_taxes(0);
                        list[i].setSyndicate_tax(0);
                        System.out.println("\n\n\n    Change done!!\n\n ");
                        UndoRedo.setChange_made(true);
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
                        	catch(InputMismatchException e)
                    		{
                    			input.nextLine();
        						System.out.printf("\n\n\n\n  The value of this ID is not an integer!!\n\n  Press enter to try again...\n\n\n");
        						input.nextLine();
                    		}
                        }
                    	
                        System.out.println("\n\n    Change done!!\n\n ");
                        UndoRedo.setChange_made(true);
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
                        	catch(InputMismatchException e)
                    		{
                    			input.nextLine();
        						System.out.printf("\n\n\n\n  The value of this Syndicate tax is not an numeric value!!\n\n  Press enter to try again...\n\n\n");
        						input.nextLine();
                    		}
                        }
                        
                        System.out.println("\n\n    Change done!!\n\n ");
                        UndoRedo.setChange_made(true);
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
                    
                    return EmployeeRelated.changeDetails(list, month);

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
	
	public static Employee[] sellingSubmit(Employee list[])
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
			catch(InputMismatchException e)
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
					catch(InputMismatchException e)
					{
						input.nextLine();
						System.out.printf("\n\n\n  The given Selling is not an numeric value!!\n\n  Press enter to try again...\n\n\n");
						input.nextLine();
					}
				}
				System.out.printf("\n\n\n     Selling added to User: %s with ID: %d\n\n", list[i].getName(), list[i].getID());
				UndoRedo.setChange_made(true);
				
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
	
	
}
