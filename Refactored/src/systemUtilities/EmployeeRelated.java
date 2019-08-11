package systemUtilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import DesignPatterns.EmployeeFactory;
import paymentSystemOO.Commissioned;
import paymentSystemOO.Employee;
import paymentSystemOO.ExtraFunctions;
import paymentSystemOO.Hourly;

public class EmployeeRelated {
	
	private static Scanner input = new Scanner(System.in);
	private EmployeeFactory EFactory = new EmployeeFactory();
	private ExtraFunctions extra_func = new ExtraFunctions();
	
	public Employee addEmployee(ExtraFunctions extra_func, UndoRedo system_state, int id)
	{
		int choice;
		Employee new_employee;
		
		System.out.printf("\n\n\n\n  Insert type of the Employee followed by a enter:\n\n  H - Hourly\n  S - Salaried\n  C - Commissioned\n\n\n  Your option: ");
		
		new_employee = EFactory.newEmployee(input.nextLine(), extra_func);
		
		if(new_employee == null)
		{
			System.out.printf("\n\n  Invalid option!!\n  Press enter to try again...\n\n\n");
			input.nextLine();
			return null;
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
		
		choice = extra_func.integerScan("\n\n\n  Does this Employee belongs to the Syndicate?\n\n  0 - No\n  1 - Yes\n\n  Your option: ");
		
		if(choice == 0)
		{
			new_employee.setSyndicate(false);
		}
		else if(choice == 1)
		{
			new_employee.setSyndicate(true);
			new_employee.setSyndicate_id(extra_func.integerScan("\n\n\n  Insert the Syndicate ID: "));
			new_employee.setSyndicate_tax(extra_func.doubleScan("\n\n\n  Insert the Syndicate Tax: "));
			new_employee.setService_taxes(0);
		}
		else
		{
			System.out.printf("\n\n  Invalid option!!\n  Setting to default: 'not in syndicate'\n  Press enter to continue...\n\n\n");
			input.nextLine();
		}
		
		new_employee.myDetails();
		
		System.out.printf("\n Save your ID number!!!!\n");
        System.out.printf("\n\n    Employee added in the system!");
        System.out.printf("\n\n Press enter to continue...\n\n\n\n");
        input.nextLine();
        
        system_state.setChange_made(true);
        
        return new_employee;
	}
	
	public Employee[] timeCard(Employee list[], UndoRedo system_state, int day_of_week)
	{
		int given;
		int choice;
		
		given = extra_func.integerScan("\n\n  Insert your ID: ");
		
		if(list[given] != null)
		{
			if(list[given].isCard_submit())
			{
				System.out.printf("\n\n\n\n  This Employee already submited the time card today!!\n\n  Press enter to return to Functions...\n\n\n\n");
				input.nextLine();
				return list;
			}
			System.out.println("\n\n  Insert the hours in the format : 'HH:mm' where 'HH' is hours and 'mm' minutes, followed by enter\n\n Be sure to check this: if hour/minutes < 10 put '01', '09'.!!\n\n ");
			
			choice = extra_func.integerScan("\n\n  Are you comming now? (1 - yes / 0 - no)\n\n  Your option: ");
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
                    
                    if(list[given] instanceof Hourly)// necessary instanceof (only hourly employee submit extrasalary)
                    {
                    	System.out.printf("\n\n Current Salary: %.2f\n Today salary: %.2f\n Total (No Extra included) : %.2f\n\n ", list[given].getSalary(), (hour * 30.00), list[given].getSalary() + (hour * 30.00));
                        list[given].addSalary(hour * 30.00);
                        if(hour > 8)
                        {
                        	int limit = (hour - 8);
                            double bonus = (limit * 1.5 * 30.00);
                            ( (Hourly) list[given]).addHours(hour); // total worked hours
                            ( (Hourly) list[given]).addExtra(bonus);
                            System.out.printf("\n\n  Extra salary: %.2f\n\n ", ( (Hourly) list[given]).getExtra_salary());
                        }
                    }
                    else
                    {
                        if(day_of_week != 5 && day_of_week != 6)
                        {
                        	list[given].addDay();
                        }
                    }
                    list[given].setCard_submit(true);
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
            else if(choice == 1)
            {
                System.out.printf("\n\n\n Insert the hours (start): ");
                list[given].setArrival_time(input.nextLine());
                
                System.out.println("\n\n    Time Card Approved!\n\n");
                System.out.printf("\n\n\n      Press any key to continue...\n\n ");
                input.nextLine();
            }
            else
            {
            	System.out.printf("\n\n  Failed to submit Time Card!!\n\n  Press enter to return to Employee Functions...");
            	input.nextLine();
            	return list;
            }
            system_state.setChange_made(true);
		}
		else
		{
			System.out.printf("\n\n  There's no Employee with this Identification!!\n\n  Press enter to return to Adminstrator Functions...\n\n\n");
			input.nextLine();
		}
		
		return list;
	}
	
	public Employee[] changeDetails(Employee list[], ExtraFunctions extra_func, UndoRedo system_state)
	{
		int i = extra_func.integerScan("\n\n  Insert the ID from the Employee: ");
		
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
                    system_state.setChange_made(true);
                    break;
    
                case 2:
                    System.out.println("\n\n\n  Insert the new address followed by a enter: ");
                    input.nextLine();
                    list[i].setAddress(input.nextLine());
                    System.out.println("\n\n    Change done!!\n\n ");
                    system_state.setChange_made(true);
                    break;
    
                case 3:
                	String new_type;
                	System.out.println("\n\n\n  Insert the new type followed by a enter:\n\n  H - Hourly\n  S - Salaried\n  C - Commissioned\n  Z - Cancel\n\n\n  Option: ");
                	new_type = input.nextLine();
                	list[i] = EFactory.changeType(list[i], new_type, extra_func);
                	if(!new_type.equalsIgnoreCase("4"))
                	{
                		System.out.printf("\n\n    Change done!!\n\n  Press enter to return to Functions...\n\n\n\n");
                        input.nextLine();
                        system_state.setChange_made(true);
                	}
                	
                    break;
    
                case 4:
                	
                	dado = extra_func.integerScan("\n\n\n  Choose your Payment Method: \n\n 0 - Postal Check via Mail\n 1 - Receive Postal Check in hands\n 2 - Bank Deposit\n\n  Your choice: ");
                    
                    if(dado == 0 || dado == 1 || dado == 2)
                    {
                        list[i].setPayment_method(dado);
                        System.out.println("\n\n    Change done!!\n\n ");
                        system_state.setChange_made(true);
                    }
                    else
                    {
                        System.out.println("\n\n  Invalid Method!!\n\n  Press enter to go back to options...\n\n\n");
                        input.nextLine();
                    }
                    break;
    
                case 5:
                	
                	dado = extra_func.integerScan("\n\n\n  Insert your new Syndicate status: ( 1 - belong to syndicate // 0 - not in syndicate)\n\n  Your option: ");
                    
                	if(dado == 1)
                    {
                        list[i].setSyndicate(true);
                        list[i].setSyndicate_id(extra_func.integerScan("\n\n Insert your Syndicate ID (Numbers Only): "));
                        list[i].setSyndicate_tax(extra_func.doubleScan("\n\n Insert your Syndicate Tax (Numbers Only): "));
                        list[i].setService_taxes(0);
                        
                        System.out.println("\n\n\n    Change done!!\n\n ");
                        system_state.setChange_made(true);
                    }
                    else if(dado == 0)
                    {
                    	list[i].setSyndicate(false);
                        list[i].setSyndicate_id(-1);
                        list[i].setService_taxes(0);
                        list[i].setSyndicate_tax(0);
                        System.out.println("\n\n\n    Change done!!\n\n ");
                        system_state.setChange_made(true);
                    }
                    else
                        System.out.println("\n\n  Invalid option!!\n\n ");
                	
                    break;
    
                case 6:
                    if(list[i].isSyndicate())
                    {
                    	list[i].setSyndicate_id(extra_func.integerScan("\n\n\n  Insert the new Syndicate ID (Integer Numbers Only): "));
                    	
                        System.out.println("\n\n    Change done!!\n\n ");
                        system_state.setChange_made(true);
                    }
                    else
                        System.out.printf("\n\n    This Employee does not belong to the Syndicate!!\n\n");
                    
                    break;
    
                case 7:
                    if(list[i].isSyndicate())
                    {
                    	list[i].setSyndicate_tax(extra_func.doubleScan("\n\n\n  Insert your Syndicate Tax (Numbers Only): "));
                        
                        System.out.println("\n\n    Change done!!\n\n ");
                        system_state.setChange_made(true);
                    }
                    else
                        System.out.printf("\n\n    This Employee does not belong to the Syndicate!!\n\n");
                    
                    break;
    
                case 8:
                    return list;

                default:
                    System.out.printf("\n\n   Invalid Option!! / Press enter to return to Functions...\n\n\n");
                    input.nextLine();
                    
                    return list;

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
	
	public Employee[] sellingSubmit(Employee list[], UndoRedo system_state)
	{
		int i = extra_func.integerScan("\n\n\n  Insert Your ID: ");
		
		if(list[i] != null)
		{
			if(list[i] instanceof Commissioned)
			{
				( (Commissioned)list[i]).addSelling(extra_func.doubleScan("\n\n\n  Insert the Selling Value: "));
				System.out.printf("\n\n\n     Selling added to User: %s with ID: %d\n\n", list[i].getName(), list[i].getID());
				system_state.setChange_made(true);
				
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
	
	public Employee[] serviceSubmit(Employee list[], UndoRedo system_state)
	{
		boolean flag = false;
		int s_id = extra_func.integerScan("\n\n\n  Insert the Syndicate ID from the Employee: ");
		for(int i = 0;i<1000;i++)
		{
			if(list[i] != null)
			{
				if(s_id == list[i].getSyndicate_id())
				{
					list[i].addService_taxes(extra_func.doubleScan("\n\n Insert the tax value: "));
					System.out.printf("\n\n    Tax added to the user: %s with ID: %d !!!", list[i].getName(), list[i].getID());
	                System.out.printf("\n\n Press enter to continue...");
	                input.nextLine();
	                system_state.setChange_made(true);
	                flag = true;
				}
			}
		}
		if(!flag)
		{
			System.out.printf("\n\n     Typed Syndicate ID not found in the System!!\n\n\n     Press enter to return to Functions... ");
            input.nextLine();
		}
		return list;
	}
	
}
