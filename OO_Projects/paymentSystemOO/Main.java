package paymentSystemOO;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main extends Functions{

	private static Scanner input = new Scanner(System.in);
	private static String system_pass = "admin";
	private static String employee_pass = "0000";
	
	// Undo \ Redo
	
		protected static boolean change;
		private static Employee state_list[][] = new Employee[51][1000];
		private static int state_index = 0;
		private static int states_size = 0;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args) {
		
		String given_pass;
		int option = 0;
		int i;
		boolean choice = true;
		
		Employee Employee_list[] = new Employee[1000];
		Schedule Schedule_list[] = new Schedule[1000];
		
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
				System.out.printf("\n\n Option: ");
				option = input.nextInt();
				input.nextLine();
				System.out.printf("\n\n");
			}
			catch(InputMismatchException e)
			{
				input.nextLine();
				System.out.printf("\n\n  The typed value is not a Integer!!\n Press enter to try again...\n\n\n\n");
				input.nextLine();
			}
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
					System.out.printf("       /// 2 - Remove an Employee                       ///\n");
					System.out.printf("       /// 3 - Change an Employee's Details             ///\n");
					System.out.printf("       /// 4 - Payments for Today                       ///\n");
					System.out.printf("       /// 5 - Launch a Tax of Service                  ///\n");
					System.out.printf("       /// 6 - Undo / Redo                              ///\n");
					System.out.printf("       /// 7 - Create a new Payment Schedule            ///\n");
					System.out.printf("       /// 8 - Delete a Payment Schedule                ///\n");
					System.out.printf("       /// 9 - Show Details of an Employee              ///\n");
					System.out.printf("       /// 10 - Show Details of All Employees           ///\n");
					System.out.printf("       /// 11 - Change System Password                  ///\n");
					System.out.printf("       /// 12 - Back to Main Menu                       ///\n");
					System.out.printf("       ///                                              ///\n");
					System.out.printf("       ////////////////////////////////////////////////////\n\n");
					
					if(day < 10 && month < 10)
			               System.out.printf("       Date : 0%d / 0%d / 2019\n       %s\n", day, month, week_name[day_of_week]);
			           else if(day < 10)
			               System.out.printf("       Date : 0%d / %d / 2019\n       %s\n", day, month, week_name[day_of_week]);
			           else if(month < 10)
			               System.out.printf("       Date : %d / 0%d / 2019\n       %s\n", day, month, week_name[day_of_week]);
			           else
			               System.out.printf("       Date : %d / %d / 2019\n       %s\n", day, month, week_name[day_of_week]);
					
					System.out.printf("\n\n       Option: ");
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
										break;
									}
									catch(Exception e)
									{
										input.nextLine();
										System.out.printf("\n\n\n  The given ID is not an integer!!\n\n  Press enter to Try Again...\n\n\n");
										input.nextLine();
									}
								}
								
								if(Employee_list[a] != null)
								{
									Employee_list[a] = null;
									System.out.printf("\n\n\n  Employee Removed from the System!!\n\n  Press enter to return to Adminstrator Functions...\n\n\n");
									setChange(true);
								}
								else
								{
									System.out.printf("\n\n  There's no Employee with this Identification!!\n\n  Press enter to return to Adminstrator Functions...\n\n\n");
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
								consoleClear();
								todayPayments(Employee_list);
								break;
								
							case 5:
								input.nextLine();
								consoleClear();
								Employee_list = serviceSubmit(Employee_list);
								break;
								
							case 6:
								input.nextLine();
								consoleClear();
								Employee_list = undoRedo(Employee_list);
								break;
								
							case 7:
								input.nextLine();
								consoleClear();
								Schedule_list = addSchedule(Schedule_list);
								break;
								
							case 8:
								input.nextLine();
								consoleClear();
								Schedule_list = delSchedule(Schedule_list);
								break;
								
							case 9:
								input.nextLine();
								consoleClear();
								showDetails(Employee_list);
								break;
								
							case 10:
								input.nextLine();
								consoleClear();
								showAllEmployees(Employee_list);
								break;
								
							case 11:
								consoleClear();
								changePassword();
								break;
								
							case 12:
								choice = true;
								break;
								
							default:
								input.nextLine();
								System.out.printf("\n\n Invalid Option!!\n Press enter to try again...");
								input.nextLine();
								break;
						}
					}
					catch(InputMismatchException e)
					{
						input.nextLine();
						System.out.printf("\n\n The typed value is not an integer!!\n Press enter to try again...\n\n\n\n");
						input.nextLine();
					}
					if(isChange())
					{
						saveState(Employee_list);
						setChange(false);
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
					System.out.printf("       /// 3 - Change an Employee's Details             ///\n");
					System.out.printf("       /// 4 - Payment Schedules                        ///\n");
					System.out.printf("       /// 5 - Undo / Redo                              ///\n");
					System.out.printf("       /// 6 - Show Details of an Employee              ///\n");
					System.out.printf("       /// 7 - Back to Main Menu                        ///\n");
					System.out.printf("       ///                                              ///\n");
					System.out.printf("       ////////////////////////////////////////////////////\n\n");
					
					if(day < 10 && month < 10)
			               System.out.printf("       Date : 0%d / 0%d / 2019\n       %s\n", day, month, week_name[day_of_week]);
			           else if(day < 10)
			               System.out.printf("       Date : 0%d / %d / 2019\n       %s\n", day, month, week_name[day_of_week]);
			           else if(month < 10)
			               System.out.printf("       Date : %d / 0%d / 2019\n       %s\n", day, month, week_name[day_of_week]);
			           else
			               System.out.printf("       Date : %d / %d / 2019\n       %s\n", day, month, week_name[day_of_week]);
					
					System.out.printf("\n\n       Option: ");
					
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
								consoleClear();
								Employee_list = chooseSchedule(Employee_list, Schedule_list);
								break;
								
							case 5:
								input.nextLine();
								consoleClear();
								Employee_list = undoRedo(Employee_list);
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
					catch(InputMismatchException e)
					{
						input.nextLine();
						System.out.printf("\n\n  The typed value is not an integer!!\n  Press enter to try again...\n\n\n\n");
						input.nextLine();
					}
					if(isChange())
					{
						saveState(Employee_list);
						setChange(false);
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
	}

	private static boolean isChange()
	{
		return change;
	}
	
	private static void saveState(Employee list[])
	{
		int i,j;
		if(states_size < 50)
		{
			state_index++;
			states_size = state_index; // previne redos indesejados
			for(i = 0;i<1000;i++)
			{
				state_list[state_index][i] = list[i];
			}
			
			for(i = states_size + 1;i<51;i++) // cleaning unwanted states (i think this save some memory :p )
			{
				for(j = 0;j<1000;j++)
				{
					state_list[i][j] = null;
				}
			}
		}
		else
		{
			System.out.printf("\n\n\n  Can't add anymore state (undoRedo capacity full!!)\n  Reseting the UndoRedo Configurations...\n\n");
            states_size = 0;
            state_index = 0;
            System.out.printf(" Press enter to continue...\n\n\n");
            input.nextLine();
		}
	}
	
	private static Employee[] applyState(Employee list[])
	{
		for(int i = 0;i<1000;i++)
		{
			list[i] = state_list[state_index][i];
		}
		return list;
	}
	
	private static Employee[] undoRedo(Employee list[])
	{
		int choice;
		while(true)
		{
			while(true)
			{
				try {
					consoleClear();
				    System.out.printf("\n       ////////////////////////////////////\n");
					System.out.printf("       ///                              ///\n");
					System.out.printf("       ///          Undo / Redo         ///\n");
					System.out.printf("       ///                              ///\n");
					System.out.printf("       /// 1 - Undo                     ///\n");
					System.out.printf("       /// 2 - Redo                     ///\n");
					System.out.printf("       /// 3 - Cancel                   ///\n");
					System.out.printf("       ///                              ///\n");
					System.out.printf("       ////////////////////////////////////\n\n\n       Option: ");
					choice = input.nextInt();
					input.nextLine();
					break;
				}
				catch(InputMismatchException e)
				{
					input.nextLine();
					System.out.printf("\n\n\n  the option is not an integer!!\n\n  Press enter to try again...\n\n\n");
					input.nextLine();
				}
			}
			if(choice == 1)
			{
				if(state_index > 0)
	            {
					consoleClear();
	                state_index--;
	                list = applyState(list);
	                System.out.printf("\n\n\n\n    Settings UN-done!!\n\n    Press enter to return to Undo / Redo Functions..\n\n\n\n");
	                input.nextLine();
	            }
	            else
	            {
	            	System.out.printf("\n\n\n\n    Undo Limit reached!!\n\n    Press enter to return to Undo / Redo Functions...\n\n\n\n");
	            	input.nextLine();
	            }
	                
					
			}
			else if(choice == 2)
			{
				if(state_index < states_size && state_index < 50)
	            {
					consoleClear();
	                state_index++;
	                list = applyState(list);
	                System.out.printf("\n\n\n\n    Settings RE-done!!\n\n    Press enter to return to Undo / Redo Functions..\n\n\n\n");
	                input.nextLine();
	            }
	            else
	            {
	            	System.out.printf("\n\n\n\n    Redo Limit reached!!\n\n    Press enter to return to Undo / Redo Functions...\n\n\n\n");
	            	input.nextLine();
	            }
			}
			else if(choice == 3)
			{
				break;
			}
			else
			{
				System.out.printf("\n\n\n\n  Invalid Option!!\n\n Press enter to try again...\n\n\n");
				input.nextLine();
			}
		}
		return list;
	}
	
	private static void showAllEmployees(Employee list[])
	{
		double result;
		boolean flag = false;
		for(int i = 0;i<1000;i++)
		{
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
			    System.out.printf("\n\n Press enter to go to the next Employee...\n\n\n");
			    input.nextLine();
			    consoleClear();
			    flag = true;
			}
		}
		if(!flag)
		{
			System.out.printf("\n\n\n\n  No Employees found in the System!\n\n\n");
		}
		System.out.printf("\n\n\n  Press enter to return to Functions...\n\n\n");
	    input.nextLine();
	}
	
	private static void changePassword()
	{
		String given_pass;
		int choice;
		System.out.printf("\n\n\n  Insert the System Password: ");
		given_pass = input.nextLine();
		if(given_pass.equals(system_pass))
		{
			while(true)
			{
				consoleClear();
				while(true)
				{
					try {
						System.out.printf("\n\n\n  Insert the Password you want to change:\n\n  1 - System Password / Current Password: %s\n  2 - Employee Password / Current Password: %s\n  3 - Return to Admin Functions\n\n\n  Your Option: ");
						choice = input.nextInt();
						input.nextLine();
						break;
					}
					catch(InputMismatchException e)
					{
						input.nextLine();
						System.out.printf("\n\n\n  The given password is not an integer...\n\n\n");
						input.nextLine();
					}
				}
				switch(choice)
				{
					case 1:
						System.out.printf("\n\n\n  Insert the new System Password: ");
						system_pass = input.nextLine();
						System.out.printf("\n\n\n  System Password Changed!!\n\n  Press enter to go back to menu...\n\n\n");
						input.nextLine();
						break;
						
					case 2:
						System.out.printf("\n\n\n  Insert the new Employee Password: ");
						employee_pass = input.nextLine();
						System.out.printf("\n\n\n  Employee Password Changed!!\n\n  Press enter to go back to menu...\n\n\n");
						input.nextLine();
						break;
						
					case 3:
						return;
						
					default:
						System.out.printf("\n\n\n  Invalid option!!\n\n  Press enter to try again...\n\n\n");
						input.nextLine();
						break;
				}
			}
			
			
		}
		
	}
	
	private static Schedule[] addSchedule(Schedule list[])
	{
		int i;
        int choice;
        boolean flag = false;
        int week;
        consoleClear();
        for(i = 0;i<1000;i++)
        {
            if(list[i] == null)
            {
            	while(true)
            	{
            		consoleClear();
            		try {
            			System.out.printf("\n\n       //////////////////////////////////////////////////////////////////////\n");
                        System.out.printf("       ///                                                                ///\n");
                        System.out.printf("       ///    Insert the type of the new Schedule:                        ///\n");
                        System.out.printf("       ///                                                                ///\n");
                        System.out.printf("       ///  1 - Weekly (Hourly) / BiWeekly (Commissioned)                 ///\n");
                        System.out.printf("       ///  2 - Monthly                                                   ///\n");
                        System.out.printf("       ///                                                                ///\n");
                        System.out.printf("       //////////////////////////////////////////////////////////////////////\n");
                        System.out.printf("\n\n       Your choice: ");
                        choice = input.nextInt();
                        input.nextLine();
            			break;
            		}
            		catch(InputMismatchException e)
            		{
            			input.nextLine();
            			System.out.printf("\n\n\n The given type is not an integer!!\n\n  Press enter to trya agin...\n\n\n");
            			input.nextLine();
            		}
            	}
                list[i] = new Schedule();
                consoleClear();
                switch(choice)
                {
                    case 1:
                        list[i].setSchedule_id(i);
                        list[i].setSchedule_type(1);
                        while(true)
                        {
                        	consoleClear();
                        	try {
                        		System.out.printf("\n\n       //////////////////////////////////////////////////////////////////////\n");
                                System.out.printf("       ///                                                                ///\n");
                                System.out.printf("       ///    Insert the day of the week from the new Schedule:           ///\n");
                                System.out.printf("       ///                                                                ///\n");
                                System.out.printf("       ///  0 - Monday                                                    ///\n");
                                System.out.printf("       ///  1 - Tuesday                                                   ///\n");
                                System.out.printf("       ///  2 - Wednesday                                                 ///\n");
                                System.out.printf("       ///  3 - Thursday                                                  ///\n");
                                System.out.printf("       ///  4 - Friday (Default)                                          ///\n");
                                System.out.printf("       ///                                                                ///\n");
                                System.out.printf("       //////////////////////////////////////////////////////////////////////\n");
                                System.out.printf("\n\n       Your choice: ");
                                week = input.nextInt();
                                input.nextLine();
                                break;
                        	}
                        	catch(InputMismatchException e)
                        	{
                        		input.nextLine();
                        		System.out.printf("\n\n\n\n  The given choice is not an integer!!\n\n  Press enter to try again...\n\n\n");
                        		input.nextLine();
                        	}
                        }
                        
                        if(week >= 0 && week < 5)
                        {
                            list[i].setSchedule_option(week_name[week]);

                        }
                        else
                        {
                            System.out.printf("\n\n Invalid option, setting to default day: 'Friday'\n\n");
                            list[i].setSchedule_option(week_name[4]);
                            System.out.printf("\n Press enter to continue...\n\n\n\n");
                            input.nextLine();
                            
                        }
                        System.out.printf("\n\n\n\n");
                        list[i].printSchedule();
                        System.out.printf("\n\n\n  Schedule added in the System!!\n");
                        System.out.printf("\n\n  Press enter to continue...\n\n\n\n");
                        input.nextLine();
                        break;

                    case 2:
                        list[i].setSchedule_id(i);
                        list[i].setSchedule_type(2);
                        System.out.printf("\n\n       //////////////////////////////////////////////////////////////////////\n");
                        System.out.printf("       ///                                                                ///\n");
                        System.out.printf("       ///      Insert the day of the month from the new Schedule:        ///\n");
                        System.out.printf("       ///                                                                ///\n");
                        System.out.printf("       ///         (  Must be between or equal to 1 and 28!!  )           ///\n");
                        System.out.printf("       ///  (  Insert 'last' to put on last business day of the month )   ///\n");
                        System.out.printf("       ///                                                                ///\n");
                        System.out.printf("       //////////////////////////////////////////////////////////////////////\n");
                        System.out.printf("\n\n       Your choice: ");
                        String day = input.nextLine();
                        try {
                        	
	                        if(day.equalsIgnoreCase("last"))
	                        {
	                            list[i].setSchedule_option(day);
	                        }
	                      
	                        else if(Integer.parseInt(day) >= 1 && Integer.parseInt(day) < 29)
	                        {
	                          	list[i].setSchedule_option(day);
	                        }
	                        else
	                        {
	                            System.out.printf("\n\n\n\n  Invalid option!!, setting to default: 'last'...");
	                            list[i].setSchedule_option("last");
	                            System.out.printf("\n\n  Press enter to continue...\n\n\n\n");
	                            input.nextLine();
	                        }
                        }
                        catch(NumberFormatException e)
                        {
                        	System.out.printf("\n\n\n\n  Invalid option!!, setting to default: 'last'...");
                            list[i].setSchedule_option("last");
                            System.out.printf("\n\n  Press enter to continue...\n\n\n\n");
                            input.nextLine();
                        }
                        System.out.printf("\n\n\n\n");
                        list[i].printSchedule();
                        System.out.printf("\n\n\n  Schedule added in the System!!\n");
                        System.out.printf("\n  Press enter to continue...\n\n\n\n");
                        input.nextLine();
                        break;

                    default :
                        System.out.printf("\n\n  Invalid option!!\n\n  executing default action: 'return to Functions...'.\n\n\n");
                        System.out.printf("\n\n  Press enter to continue...\n\n\n\n");
                        input.nextLine();
                        
                        return list;
                }
                flag = true;
                break;
            }
        }
        if(!flag)
        {
        	System.out.printf("\n\n  Schedule limit Reached!!\n\n  Press enter to return to Functions...\n\n\n\n");
        	input.nextLine();
        }
		
		return list;
	}
	
	private static Employee[] chooseSchedule(Employee list1[], Schedule list2[])
	{
		int choice;
		int i,id;
		int aux;
		boolean flag = false;
		while(true)
		{
			try {
				System.out.printf("\n\n  Insert your ID: ");
				id = input.nextInt();
				input.nextLine();
				break;
			}
			catch(InputMismatchException e)
			{
				input.nextLine();
				System.out.printf("\n\n  the given ID is not an integer!!\n\n  Press enter to try again...\n\n\n\n");
				input.nextLine();
			}
		}
		if(list1[id] == null)
		{
			System.out.printf("\n\n\n  There's no Employee with the ID: %d int the System!!\n\n  Press enter to return to Functions...\n\n\n\n");
			input.nextLine();
			return list1;
		}
		System.out.printf("\n\n       //////////////////////////////////////////////////////////////////////\n");
        System.out.printf("       ///                                                                ///\n");
        System.out.printf("       ///                      Payment Schedules                         ///\n");
        System.out.printf("       ///                                                                ///\n");
        System.out.printf("       //////////////////////////////////////////////////////////////////////\n\n\n\n\n");
        for(i = 0;i<1000;i++)
        {
        	if(list2[i] != null)
        	{
        		list2[i].printSchedule();
        		flag = true;
        	}
        }
        if(!flag)
        {
        	System.out.printf("\n       There's no Schedules in the System!!\n\n\n");
        }
        while(true)
        {
        	while(true)
        	{
        		try {
        			System.out.printf("\n\n  Insert the Schedule ID you want to change to ('-1' to Cancel): ");
                    choice = input.nextInt();
                    input.nextLine();
                    break;
        		}
        		catch(InputMismatchException e)
        		{
        			input.nextLine();
        			System.out.printf("\n\n  The schedule id is not an integer!!\n\n  Press enter to try again...\n\n\n\n");
        			input.nextLine();
        		}
        	}
            if(choice == -1)
            {
            	return list1;
            }
            if(list2[choice] != null)
            {
                switch(list2[choice].getSchedule_type())
                {
                    case 1:
                        if(list1[id] instanceof Hourly)
                        {
                            switch(list2[choice].getSchedule_option())
                            {
                                case "Monday":
                                    ( (Hourly)list1[id]).setPayment_date(0);
                                    break;

                                case "Tuesday":
                                	( (Hourly)list1[id]).setPayment_date(1);
                                    break;

                                case "Wednesday":
                                	( (Hourly)list1[id]).setPayment_date(2);
                                    break;

                                case "Thursday":
                                	( (Hourly)list1[id]).setPayment_date(3);
                                    break;

                                case "Friday":
                                	( (Hourly)list1[id]).setPayment_date(4);
                                    break;
                            }
                            System.out.printf("\n\n\n\n  Change Done!!\n\n");
                        }
                        else if(list1[id] instanceof Commissioned)
                        {
                        	( (Commissioned)list1[id]).setTwo_week(0);
                        	switch(list2[choice].getSchedule_option())
                            {
                                case "Monday":
                                    ( (Commissioned)list1[id]).setPayment_date(0);
                                    break;

                                case "Tuesday":
                                	( (Commissioned)list1[id]).setPayment_date(1);
                                    break;

                                case "Wednesday":
                                	( (Commissioned)list1[id]).setPayment_date(2);
                                    break;

                                case "Thursday":
                                	( (Commissioned)list1[id]).setPayment_date(3);
                                    break;

                                case "Friday":
                                	( (Commissioned)list1[id]).setPayment_date(4);
                                    break;
                            }
                            System.out.printf("\n\n\n\n  Change Done!!\n\n");
                        }
                        else
                        {
                        	System.out.printf("\n\n\n  This Employee is not an Hourly OR Commissioned!!\n\n  Please move to 'Change an Employee Details' on menu.\n\n\n");
                        }
                        break;

                    case 2:
                        if(list1[id] instanceof Salaried)
                        {
                            if(list2[choice].getSchedule_option().equalsIgnoreCase("last"))
                            {
                                ( (Salaried)list1[id]).setPayment_date(dayUtil(month));
                                ( (Salaried)list1[id]).setSalaried_default(true);
                            }
                            else
                            {
                            	( (Salaried)list1[id]).setSalaried_default(false);
                            	aux = Integer.parseInt(list2[choice].getSchedule_option());//           POSSIVEL ERRO (favor verificar addSchedule)
                            	( (Salaried)list1[id]).setPayment_date(aux);
                                
                            }
                            System.out.printf("\n\n\n  Change Done!!");
                        }
                        else
                        {
                            System.out.printf("\n\n\n  This Employee is not an Salaried!!\n\n  Please move to 'Change an Employee Details' on menu.\n\n\n");
                        }
                        break;
                        
                }
                System.out.printf("\n\n  Press enter to continue...\n\n\n\n");
                input.nextLine();
                break;
            }
            else
            {
                System.out.printf("\n\n\n\n  Invalid Schedule ID!!");
                System.out.printf("\n\n  Press enter to try again...\n\n\n\n");
                input.nextLine();
                System.out.printf("\n\n\n\n");
            }
        }
		
		return list1;
	}
	
	private static Schedule[] delSchedule(Schedule list[])
	{
		int i,choice;
		System.out.printf("\n\n       //////////////////////////////////////////////////////////////////////\n");
        System.out.printf("       ///                                                                ///\n");
        System.out.printf("       ///                      Payment Schedules                         ///\n");
        System.out.printf("       ///                                                                ///\n");
        System.out.printf("       //////////////////////////////////////////////////////////////////////\n\n\n\n\n");
        for(i = 0;i<1000;i++)
        {
        	if(list[i] != null)
        	{
        		list[i].printSchedule();
        	}
        }
        while(true)
    	{
    		try {
    			System.out.printf("\n\n\n  Insert the Schedule ID you want to change to: ");
                choice = input.nextInt();
                input.nextLine();
                break;
    		}
    		catch(InputMismatchException e)
    		{
    			input.nextLine();
    			System.out.printf("\n\n  The schedule id is not an integer!!\n\n  Press enter to try again...\n\n\n\n");
    			input.nextLine();
    		}
    	}
        if(list[choice] != null)
        {
        	list[choice] = null;
        	System.out.printf("\n\n\n\n  Schedule Number: %d Removed from the System!!\n\n  Press enter to return to Functions...\n\n\n", choice);
        	input.nextLine();
        }
        else
        {
        	System.out.printf("\n\n\n\n  There's no Schedule with the ID: %d int the System!!\n\n  Press enter to return to Functions...\n\n\n", choice);
        	input.nextLine();
        }
		
		return list;
	}
}
