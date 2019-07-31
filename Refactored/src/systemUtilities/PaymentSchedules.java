package systemUtilities;

import java.util.InputMismatchException;
import java.util.Scanner;

import paymentSystemOO.Commissioned;
import paymentSystemOO.Employee;
import paymentSystemOO.ExtraFunctions;
import paymentSystemOO.Hourly;
import paymentSystemOO.Salaried;
import systemUtilities.Schedule;

public abstract class PaymentSchedules {
	
	private static Scanner input = new Scanner(System.in);
	
	public static Schedule[] addSchedule(Schedule list[], String week_name[])
	{
		int i;
        int choice;
        boolean flag = false;
        int week;
        ExtraFunctions.consoleClear();
        for(i = 0;i<1000;i++)
        {
            if(list[i] == null)
            {
            	while(true)
            	{
            		ExtraFunctions.consoleClear();
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
                ExtraFunctions.consoleClear();
                switch(choice)
                {
                    case 1:
                        list[i].setSchedule_id(i);
                        list[i].setSchedule_type(1);
                        while(true)
                        {
                        	ExtraFunctions.consoleClear();
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
	
	public static Schedule[] delSchedule(Schedule list[])
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
	
	public static Employee[] chooseSchedule(Employee list1[], Schedule list2[], ExtraFunctions extra_func)
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
                        if(list1[id] instanceof Hourly)// TODO
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
                                ( (Salaried)list1[id]).setPayment_date(extra_func.dayUtil(extra_func.getMonth()));
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
	
	
}
