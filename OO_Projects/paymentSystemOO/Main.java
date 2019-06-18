package paymentSystemOO;

import java.util.Scanner;

public class Main extends Functions{

	private static Scanner input = new Scanner(System.in);
	
	// Undo \ Redo
	
		protected static boolean change;
		private static Employee state_list[][] = new Employee[51][1000];
		private static int state_index = 0;
		private static int states_size = 0;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
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
						System.out.printf("       /// 7 - Create a new Payment Schedule            ///\n");// TODO
						System.out.printf("       /// 8 - Show Details of an Employee              ///\n");
						System.out.printf("       /// 9 - Show Details of All Employees            ///\n");// TODO
						System.out.printf("       /// 10 - Change System Password                  ///\n");// TODO
						System.out.printf("       /// 11 - Back to Main Menu                       ///\n");
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
						System.out.printf("       /// 3 - Change a Employee Details                ///\n");
						System.out.printf("       /// 4 - Payment Schedules                        ///\n"); // TODO
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
									System.out.printf("\n\n  Comming soon...\n\n  Press enter to continue...");
									input.nextLine();
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
						catch(Exception e)
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
			catch(Exception e)
			{
				input.nextLine();
				System.out.printf("\n\n  The typed value is not a Integer!!\n Press enter to try again...\n\n\n\n");
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
				catch(Exception e)
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
	
}
