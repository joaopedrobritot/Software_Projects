package paymentSystemOO;

import java.util.Scanner;

import systemUtilities.EmployeeRelated;
import systemUtilities.PaymentSchedules;
import systemUtilities.Schedule;
import DesignPatterns.UndoRedoSingleton;

public class SystemHead{
	
	private static Scanner input = new Scanner(System.in);
	private static String system_pass = "admin"; // default passwords
	private static String employee_pass = "0000";
	
	Employee Employee_list[];
	Schedule Schedule_list[];
	static ExtraFunctions extra_func = new ExtraFunctions();
	EmployeeRelated employee_func;
	UndoRedoSingleton system_state;
	
	public void initiateSystem()
	{
		String given_pass;
		int option = 0;
		boolean choice = true;
		
		Employee_list = new Employee[1000];
		Schedule_list = new Schedule[1000];
		employee_func = new EmployeeRelated();
		system_state = UndoRedoSingleton.getInstance();
		
		while(true)
		{
			ExtraFunctions.consoleClear();
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
			
			showDate();
			
			option = extra_func.integerScan("\n\n Option: ");
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
						else
						{
							System.out.printf("\n\n\n   Invalid Option!!\n\n  Try Again...\n\n\n");
						}
					}
				}
				while(!choice)
				{
					choice = showAdministratorMenu();
					if(system_state.isChange_made())
					{
						system_state.saveState(Employee_list);
						system_state.setChange_made(false);
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
					choice = showEmployeeMenu();
					if(system_state.isChange_made())
					{
						system_state.saveState(Employee_list);
						system_state.setChange_made(false);
					}
				}
			}
			else if(option == 3)
			{
				extra_func.spendDay(Employee_list);
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
				ExtraFunctions.consoleClear();
				choice = extra_func.integerScan("\n\n\n  Insert the Password you want to change:\n\n  1 - System Password / Current Password: %s\n  2 - Employee Password / Current Password: %s\n  3 - Return to Admin Functions\n\n\n  Your Option: ");
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
	
	private void hasVacancy()
	{
		for(int i = 0; i< 1000;i++)
		{
			if(Employee_list[i] == null) // found one!!
			{
				Employee_list[i] = employee_func.addEmployee(extra_func, system_state, i);
				return;
			}
		}
	}
	
	private void deleteEmployee()
	{
		int id = extra_func.integerScan("\n\n\n  Insert the ID from the Employee you want to Remove: ");
		
		if(Employee_list[id] != null)
		{
			Employee_list[id] = null;
			System.out.printf("\n\n\n  Employee Removed from the System!!\n\n  Press enter to return to Adminstrator Functions...\n\n\n");
			system_state.setChange_made(true);
		}
		else
		{
			System.out.printf("\n\n  There's no Employee with this Identification!!\n\n  Press enter to return to Adminstrator Functions...\n\n\n");
		}
		input.nextLine();
	}
	
	private void showDate()
	{
		if(extra_func.getDay() < 10 && extra_func.getMonth() < 10)
            System.out.printf("       Date : 0%d / 0%d / 2019\n       %s\n", extra_func.getDay(), extra_func.getMonth(), extra_func.getWeek_name(extra_func.getDay_of_week()));
        else if(extra_func.getDay() < 10)
            System.out.printf("       Date : 0%d / %d / 2019\n       %s\n", extra_func.getDay(), extra_func.getMonth(), extra_func.getWeek_name(extra_func.getDay_of_week()));
        else if(extra_func.getMonth() < 10)
            System.out.printf("       Date : %d / 0%d / 2019\n       %s\n", extra_func.getDay(), extra_func.getMonth(), extra_func.getWeek_name(extra_func.getDay_of_week()));
        else
            System.out.printf("       Date : %d / %d / 2019\n       %s\n", extra_func.getDay(), extra_func.getMonth(), extra_func.getWeek_name(extra_func.getDay_of_week()));
		
	}
	
	private boolean showAdministratorMenu()
	{
		ExtraFunctions.consoleClear();
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
		
		showDate();
		
		int option = extra_func.integerScan("\n\n       Option: ");
		
		switch(option)
		{
			case 1:
				ExtraFunctions.consoleClear();
				hasVacancy();
				break;
				
			case 2:
				ExtraFunctions.consoleClear();
				deleteEmployee();
				break;
				
			case 3:
				ExtraFunctions.consoleClear();
				Employee_list = employee_func.changeDetails(Employee_list, extra_func, system_state);
				break;
				
			case 4:
				ExtraFunctions.consoleClear();
				extra_func.todayPayments(Employee_list, system_state);
				break;
				
			case 5:
				ExtraFunctions.consoleClear();
				Employee_list = employee_func.serviceSubmit(Employee_list, system_state);
				break;
				
			case 6:
				ExtraFunctions.consoleClear();
				Employee_list = system_state.undoRedo(Employee_list);
				break;
				
			case 7:
				ExtraFunctions.consoleClear();
				Schedule_list = PaymentSchedules.addSchedule(Schedule_list, extra_func.getWeek_name());
				break;
				
			case 8:
				ExtraFunctions.consoleClear();
				Schedule_list = PaymentSchedules.delSchedule(Schedule_list);
				break;
				
			case 9:
				ExtraFunctions.consoleClear();
				extra_func.showDetails(Employee_list);
				break;
				
			case 10:
				ExtraFunctions.consoleClear();
				extra_func.showAllEmployees(Employee_list);
				break;
				
			case 11:
				ExtraFunctions.consoleClear();
				changePassword();
				break;
				
			case 12:
				return true;
				
			default:
				System.out.printf("\n\n Invalid Option!!\n Press enter to try again...");
				input.nextLine();
				break;
		}
		
		return false;
	}
	
	private boolean showEmployeeMenu()
	{
		ExtraFunctions.consoleClear();
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
		showDate();
		
		int option = extra_func.integerScan("\n\n       Option: ");
		
		switch(option)
		{
			case 1:
				ExtraFunctions.consoleClear();
				Employee_list = employee_func.timeCard(Employee_list, system_state, extra_func.getMonth());
				break;
				
			case 2:
				ExtraFunctions.consoleClear();
				Employee_list = employee_func.sellingSubmit(Employee_list, system_state);
				break;
				
			case 3:
				ExtraFunctions.consoleClear();
				Employee_list = employee_func.changeDetails(Employee_list, extra_func, system_state);
				break;
				
			case 4:
				ExtraFunctions.consoleClear();
				Employee_list = PaymentSchedules.chooseSchedule(Employee_list, Schedule_list, extra_func);
				break;
				
			case 5:
				ExtraFunctions.consoleClear();
				Employee_list = system_state.undoRedo(Employee_list);
				break;
				
			case 6:
				ExtraFunctions.consoleClear();
				extra_func.showDetails(Employee_list);
				break;
				
			case 7:
				return true;
				
			default:
				System.out.printf("\n\n  Invalid Option!!\n  Press enter to try again...");
				input.nextLine();
				break;
		}
		
		return false;
	}
}