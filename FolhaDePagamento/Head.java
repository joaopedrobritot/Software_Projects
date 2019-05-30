import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Head {

	private static String name[] = new String[1000];
    private static String address[] = new String[1000];
    private static String date_start[] = new String[1000];
    private static String date_end[] = new String[1000];
    private static String agenda[] = new String[1000];
    private static double salary[] = new double[1000];
    private static double extra_hour[] = new double[1000];
    private static double selling_result[] = new double[1000];
    private static double syndicate_tax[] = new double[1000];
    private static double service_tax[] = new double[1000];
    private static int two_friday[] = new int[1000];
    private static int payment[] = new int[1000];// 0 - Postal Check // 1 - Receive check in hands // 2 - Bank Deposit
    private static int hours[] = new int[1000];
    private static int ID[] = new int[1000];// indicates what's position of the employee
    private static int type[] = new int[1000];// 0 - hourly // 1 - salaried // 2 - commissioned
    private static int hourly_day[] = new int[1000]; 
    private static int salaried_week[] = new int[1000];
    private static int syndicate[] = new int[1000];// 1 belong to syndicate // 0 - not in syndicate
    private static int syndicate_id[] = new int[1000];

    private static int day = 1;
    private static int month = 1;

    private static String day_of_week[] = new String[7];
    private static int d_w = 1;
    day_of_week[0] = "Monday";
    day_of_week[1] = "Tuesday";
    day_of_week[2] = "Wednesday";
    day_of_week[3] = "Thursday";
    day_of_week[4] = "Friday";
    day_of_week[5] = "Saturday";
    day_of_week[6] = "Sunday";
    private static int last_day_month[] = new int[13];
    last_day_month[1] = 31;
    last_day_month[2] = 28;
    last_day_month[3] = 31;
    last_day_month[4] = 30;
    last_day_month[5] = 31;
    last_day_month[6] = 30;
    last_day_month[7] = 31;
    last_day_month[8] = 31;
    last_day_month[9] = 30;
    last_day_month[10] = 31;
    last_day_month[11] = 30;
    last_day_month[12] = 31;

    private static void addEmployee(int id) 
    {

        Scanner input = new Scanner(System.in);
        System.out.printf("\n Insert your name followed by a enter: ");
        name[id] = input.nextLine();
        System.out.printf("\n\n Insert you address followed by a enter: ");
        address[id] = input.nextLine();
        System.out.printf("\n\n Insert the type:\n hourly - 0\n salaried - 1\n commissioned - 2\n\n Type: ");
        type[id] = input.nextInt();
        System.out.printf("\n\n Choose your Payment Method: \n\n 0 - Postal Check\n 1 - Receive check in hands\n 2 - Bank Deposit\n\n  Your choice: ");
        payment[id] = input.nextInt();
        System.out.printf("\n\n Insert your salary: ");
        salary[id] = input.nextDouble();
        System.out.println("\n\n Do you belong to the syndicate?\n\n 0 - No\n 1 - Yes\n\n");
        int a = input.nextInt();
        if(a == 1)
        {
        	syndicate[id] = 1;
        	System.out.println("\n Insert your Syndicate ID (Numbers Only): ");
        	syndicate_id[id] = input.nextInt();
        	System.out.println("\n Insert your Syndicate Tax: ");
        	syndicate_tax[id] = input.nextDouble();
        }
        else
        {
        	syndicate[id] = 0;
        	syndicate_id[id] = -1;
        	syndicate_tax[id] = 0;
        }
        ID[id] = id;
        extra_hour[id] = 0;
        service_tax[id] = 0;
        //////printando
        System.out.printf("\n\n Employee ID: %d\n Name: %s\n Address: %s\n Type: ", ID[id], name[id], address[id]);
        switch(type[id])
        {
            case 0:
                System.out.println("Hourly");
                break;
            case 1:
                System.out.println("Salaried");
                break;
            case 2:
                System.out.println("Commissioned");
                break;
        }
        if(a == 1)
        {
        	System.out.println(" Belong to Syndicate: Yes\n Syndicate ID: " + syndicate_id[id]);
        	System.out.println("\n\n\n Save your Syndicate ID number!!!!\n\n");
        }
        else
        {
        	System.out.println("\n Belong to Syndicate: No\n");
        }
        System.out.printf(" Salary: %.2f\n",salary[id]);
        System.out.println("\n\n\n Save your ID number!!!!\n\n");
        System.out.printf("\n\n Press anykey to continue...");
        input.nextLine();
        input.nextLine();
    }

    private static int delEmployee(int id) 
    {
    	Scanner input = new Scanner(System.in);
        int flag = 0;
        if(ID[id] != -1)
        {
        	//  ERASE
        	/////////////////////
            ID[id] = -1;
            name[id] = null;
            address[id] = null;
            date_start[id] = null;
            date_end[id] = null;
            payment[id] = -1;
            syndicate_id[id] = -1;
            syndicate_tax[id] = 0;
            syndicate[id] = -1;
            salary[id] = 0;
            extra_hour[id] = 0;
            selling_result[id] = 0;
            service_tax[id] = 0;
            type[id] = -1;
            /////////////////////
            /////
            flag = 1;
            System.out.println("\n\n Employee removed with sucess!\n\n");
            System.out.printf(" Press anykey to continue...");
            input.nextLine();
        }
        else
        {
            System.out.println("\n\n Employee not found!\n");
        }
        return flag;
    }

    private static void timeCard(int id) 
    {
        Scanner input = new Scanner(System.in);
    	int hour;
        if(ID[id] != -1)
        {
        	System.out.println("\n\n Insert the hours in the format : 'HH:mm' where 'HH' is hours and 'mm' minutes, followed by enter\n\n\n");
        	System.out.println("\n\n Are you starting to work? (1 - yes / 0 - no)   ");
        	int choice = input.nextInt();
        	if(choice == 0)
        	{
        		System.out.printf("\n\n\n Insert the hours (end): ");
        		input.nextLine();
        		date_end[id] = input.nextLine();
        		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        		Date start = null;
       			Date end = null;
        		try 
        		{
					start = format.parse(date_start[id]);
					end = format.parse(date_end[id]);

                    long diff = end.getTime() - start.getTime();
                    int diffHours = (int)diff / (60 * 60 * 1000) % 24;
                    diffHours = Math.abs(diffHours);
                    if(start.getTime() > end.getTime())
                    {
                    	diffHours = 24 % diffHours;
                    }
                    System.out.print("\n\n\n\n  " + diffHours + " hours today\n\n ");
                    hour = diffHours;
				    
					hours[id] = hour;
					if(hours[id] > 8 && type[id] == 0)// hourly employees only
					{
						int limit = (hours[id] - 8);
						double bonus = (salary[id] * 1.5) / 100;
						for(int i = 0;i < limit;i++)
						{
							extra_hour[id] += bonus;
						}
						System.out.printf("\n\n  Extra salary: %.2f\n\n ", extra_hour[id]);
					}
					
					System.out.printf("\n\n\n      Press any key to continue...\n\n ");
					input.nextLine();
					System.out.println("\n\n    Time Card approved!\n\n    Finishing the day...\n\n ");

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
                date_start[id] = input.nextLine();
                
        		System.out.println("\n\n    Time Card Approved!\n\n");
        		System.out.printf("\n\n\n      Press any key to continue...\n\n ");
				input.nextLine();
        	}   
        }
        else
        {
            System.out.println("\n\n Employee not found");
        }
    }

    private static void changeDetails(int id)
    {
    	if(ID[id] != -1)
    	{
    		Scanner input = new Scanner(System.in);
    		while(true)
    		{
    		  System.out.printf("//////////////////////////////////////////////////////////////////////\n");
        	  System.out.printf("///                                                                ///\n");
        	  System.out.printf("///     			   Insert what will you change:  		        ///\n");
        	  System.out.printf("///                                                                ///\n");
        	  System.out.printf("/// 1 - Name            		                                    ///\n");
        	  System.out.printf("/// 2 - Address   				                                    ///\n");
        	  System.out.printf("/// 3 - Type       			                                    ///\n");
        	  System.out.printf("/// 4 - Payment Method 				                            ///\n");
        	  System.out.printf("/// 5 - Syndicate Status 			                                ///\n");
        	  System.out.printf("/// 6 - Syndicate ID 					                            ///\n");
        	  System.out.printf("/// 7 - Syndicate Tax 			                                    ///\n");
        	  System.out.printf("/// 8 - Return to menu                                             ///\n");
        	  System.out.printf("///                                                                ///\n");
        	  System.out.printf("//////////////////////////////////////////////////////////////////////\n\n ");
    		  int choice = input.nextInt();
    		  switch(choice)
    		  {
    		  	case 1:
    				System.out.println("Insert the new name followed by a enter: ");
    				input.nextLine();
    				name[id] = input.nextLine();
    				System.out.println("\n\n    Change done!!\n\n ");
    				break;
    
    		  	case 2:
    				System.out.println("Insert the new address followed by a enter: ");
    				input.nextLine();
    				address[id] = input.nextLine();
    				System.out.println("\n\n    Change done!!\n\n ");
    				break;
    
    		  	case 3:
    				System.out.println("Insert the new type followed by a enter (0 - hourly / 1 - salaried / 2 - commissioned):");
    				int given = input.nextInt();
    				if(given == 0 || given == 1 || given == 2)
    				{
    					type[id] = given;
    					System.out.println("\n\n    Change done!!\n\n ");
    				}
    				else
    				{
    					System.out.println("\n\n Invalid type!!\n\n ");
    				}
    				break;
    
    		  	case 4:
    				System.out.printf("Choose your Payment Method: \n\n 0 - Postal Check\n 1 - Receive check in hands\n 2 - Bank Deposit\n\n  Your choice: ");
    				int dado = input.nextInt();
    				if(dado == 0 || dado == 1 || dado == 2)
    				{
    					payment[id] = dado;
    					System.out.println("\n\n    Change done!!\n\n ");
    				}
    				else
    				{
    					System.out.println("\n\n Invalid Method!!\n\n ");
    				}
    				break;
    
    		  	case 5:
    				System.out.println("\n\n Insert your new Syndicate status: ( 1 - belong to syndicate // 0 - not in syndicate");
    				int alo = input.nextInt();
    				if(alo == 1)
    				{
    					syndicate[id] = 1;
    					System.out.println("\n\n    Change done!!\n\n ");
    				}
    				else if(alo == 0)
    				{
    					syndicate[id] = 0;
    					System.out.println("\n\n    Change done!!\n\n ");
    				}
    				else
    				{
    					System.out.println("\n\n Invalid option!!\n\n ");
    				}
    				break;
    
    		  	case 6:
                    if(syndicate[id] == 1)
                    {
                        System.out.println("\n\n Insert the new Syndicate ID: ");
                        syndicate_id[id] = input.nextInt();
                        System.out.println("\n\n    Change done!!\n\n ");
                    }
                    else
                    {
                        System.out.printf("\n\n    You do not belong to the Syndicate!!\n\n");
                    }
    				
    				break;
    
    		  	case 7:
                    if(syndicate[id] == 1)
                    {
                        System.out.println("\n Insert the new Syndicate Tax: ");
                        syndicate_tax[id] = input.nextDouble();
                        System.out.println("\n\n    Change done!!\n\n ");
                    }
    				else
                    {
                        System.out.printf("\n\n    You do not belong to the Syndicate!!\n\n");
                    }
                    
    				break;
    
    		  	case 8:
    		  		return;

                default:
                    System.out.printf("\n\n   Invalid Option!! / Press enter to try again");
                    input.nextLine();
                    changeDetails(id);
                    return;
    		  }
    		  System.out.printf("\n\n Press anykey to continue...");
        	  input.nextLine();
    		}
    	}
    }

    private static void sellingResult(int id)
    {
    	Scanner input = new Scanner(System.in);
    	System.out.printf("\n\n      Comming soon....\n\n  ");
    	System.out.printf("\n\n    Press anykey to return to menu... ");
        input.nextLine();
    	//if(ID[id] != -1)
    	//{
    	//	if(type[id] == 2)
    	//	{
    	//		System.out.printf("\n\n (dd/mm/aaaa) = d - day / m - month / y - year\n Insert the date: ");
    	//		
    	//	}
    	//}
    	//else
    	//{
    	//	System.out.println("\n\n\n\n    Invalid ID!!\n\n");
    	//	System.out.printf("\n\n    Press anykey to continue... ");
        //	input.nextLine();
    	//}
    }

    private static void serviceTax(int sid)
    {
    	Scanner input = new Scanner(System.in);
    	int flag = 0;
    	for(int i = 0;i<1000;i++)
    	{
    		if(sid == syndicate_id[i])
    		{
    			System.out.printf("\n\n Insert the tax value: ");
    			service_tax[i] = input.nextDouble();
    			System.out.printf("\n\n    Tax added to the user: %s with ID: %d !!!", name[i], ID[i]);
    			System.out.printf("\n\n Press anykey to continue...");
        		input.nextLine();
        		input.nextLine();
    			flag = 1;
    		}
    	}
    	if(flag == 0)
    	{
    		System.out.printf("\n\n     Typed Syndicate ID not found in the System!!\n\n\n     Press any key to return to menu... ");
    		input.nextLine();
    	}
    }

	private static void consoleClear()
	{
		for(int i = 0; i < 20;i++)
		{
			System.out.printf("\n\n");
		}
	}

	private static void showDetails(int id) 
    {
    	Scanner input = new Scanner(System.in);
        if(ID[id] != -1)
        {
            System.out.printf("\n\n Name: %s\n Address: %s\n Type:", name[id], address[id]);
            switch(type[id])
            {
            	case 0:
            		System.out.println(" Hourly");
            		System.out.printf(" Salary: %.2f\n Extra Salary: %.2f\n Payment: ", salary[id], extra_hour[id]);
            		break;

            	case 1:
            		System.out.println(" Salaried");
            		System.out.printf(" Salary: %.2f\n Payment: ", salary[id]);
   	            	break;

            	case 2:
            		System.out.println(" Commissioned");
            		System.out.printf(" Salary: %.2f\n Selling Results: %.2f\n Payment: ", salary[id], selling_result[id]);
            		break;

            	default:
            	System.out.printf(" NULL");break;
            }
            switch(payment[id])
            {
            	case 0:
            	System.out.println("Postal Check");break;
            	case 1:
            	System.out.println("Postal Check in hands");break;
            	case 2:
            	System.out.println("Bank Deposit");break;
            	default:
            	System.out.printf("NULL");break;
            }
            if(syndicate[id] == 1)
            {
            	System.out.printf(" Belong to Syndicate\n Syndicate ID: %d\n Syndicate Tax: %.2f\n Service Tax: %.2f\n Hours: %d\n Date Start: %s\n Date End: %s\n\n ", syndicate_id[id], syndicate_tax[id], service_tax[id], hours[id], date_start[id], date_end[id]);
            }
            else
            {
            	System.out.printf(" Not in Syndicate\n Hours: %d\n Date Start: %s\n Date End: %s\n\n", hours[id], date_start[id], date_end[id]);
            }
            System.out.printf("\n\n Press anykey to continue...");
            input.nextLine();
        }
        else
    	{
    		System.out.println("\n\n\n\n    Invalid ID!!\n\n");
    		System.out.printf("\n\n    Press anykey to continue... ");
        	input.nextLine();
    	}
    }

    private static int dayUtil(int mth)
    {
    	int result = last_day_month[mth];
    	int week = d_w;
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

    private static void todayPayments()
    {
        Scanner input = new Scanner(System.in);
    	int flag = 0;
    	int cont = 1;

    	System.out.printf("\n\n//////////////////////////////////////////////////////////////////////\n");
    	System.out.printf("///                                                                ///\n");
    	System.out.printf("///                        Today Payments                          ///\n");
    	System.out.printf("///                                                                ///\n");

    	for(int i = 0;i<1000;i++)
    	{
    		if(ID[i] != -1)//achou empregado
    		{
    			switch(type[i])
    			{
    				case 0: //hourly
    					if(d_w == 4)// pagos dia de sexta
    					{
    						System.out.printf("/// %d - Name: %s   Type: Hourly   Salary: %.2f  Payment Method: ", cont, name[i] , salary[i] + extra_hour[i]);
    						switch(payment[i])
    						{
    							case 0: System.out.printf("Postal Check\n");break;
    							case 1: System.out.printf("Check in Hands\n");break;
    							case 2: System.out.printf("Bank Deposit\n");break;
    						}
                            extra_hour[i] = 0;
    						cont++;
                            flag = 1;
    					}
    					break;

    				case 1: //salaried
    					if(day == dayUtil(month))
    					{
                            System.out.printf("/// %d - Name: %s   Type: Salaried   Salary: %.2f  Payment Method: ", cont, name[i], salary[i]);
                            switch(payment[i])
                            {
                                case 0: System.out.printf("Postal Check\n");break;
                                case 1: System.out.printf("Check in Hands\n");break;
                                case 2: System.out.printf("Bank Deposit\n");break;
                            }
                            cont++;
                            flag = 1;
    					}
    					break;

                    case 3: // commissioned
                        if(two_friday[i] == 2)
                        { 
                            System.out.printf("/// %d - Name: %s   Type: Commissioned   Salary: %.2f  Payment Method: ", cont, name[i], salary[i] + selling_result[i]);
                            switch(payment[i])
                            {
                                case 0: System.out.printf("Postal Check\n");break;
                                case 1: System.out.printf("Check in Hands\n");break;
                                case 2: System.out.printf("Bank Deposit\n");break;
                            }
                            selling_result[i] = 0;
                            cont++;
                            two_friday[i] = 0;
                            flag = 1;
                        }
                        break;
    			}
    		}
    	}

        if(flag == 0)
        {
            System.out.printf("///                     No Payments Today!!!                       ///\n");
        }
        System.out.printf("///                                                                ///\n");
        System.out.printf("///        Press any key to continue...                            ///\n");
        System.out.printf("///                                                                ///\n");
        System.out.printf("//////////////////////////////////////////////////////////////////////\n\n");
        input.nextLine();
    }

    private static void spendDay()
    {
    	day++;
    	d_w++;
        if(d_w == 4)
        {
            for(int i = 0; i < 1000 ; i++)
            {
                if(ID[i] != -1)
                {
                    if(type[i] == 2)
                    {
                        two_friday[i]++;
                    }
                }
            }
        }
    	if(d_w == 7)// day of week
    	{
    		d_w = 0;
    	}
    	switch(month)
    	{
    		case 1:
    			if(day == 31)
    			{
    				day = 1;
    				month++;
    				if(month == 13)
    				{
    					month = 1;
    				}
    			}
    			break;

    		case 2:
    			if(day == 28)
    			{
    				day = 1;
    				month++;
    				if(month == 13)
    				{
    					month = 1;
    				}
    			}
    			break;

    		case 3:
    			if(day == 31)
    			{
    				day = 1;
    				month++;
    				if(month == 13)
    				{
    					month = 1;
    				}
    			}
    			break;

    		case 4:
    			if(day == 30)
    			{
    				day = 1;
    				month++;
    				if(month == 13)
    				{
    					month = 1;
    				}
    			}
    			break;

    		case 5:
    			if(day == 31)
    			{
    				day = 1;
    				month++;
    				if(month == 13)
    				{
    					month = 1;
    				}
    			}
    			break;

    		case 6:
    			if(day == 30)
    			{
    				day = 1;
    				month++;
    				if(month == 13)
    				{
    					month = 1;
    				}
    			}
    			break;

    		case 7:
    			if(day == 31)
    			{
    				day = 1;
    				month++;
    				if(month == 13)
    				{
    					month = 1;
    				}
    			}
    			break;

    		case 8:
    			if(day == 31)
    			{
    				day = 1;
    				month++;
    				if(month == 13)
    				{
    					month = 1;
    				}
    			}
    			break;

    		case 9:
    			if(day == 30)
    			{
    				day = 1;
    				month++;
    				if(month == 13)
    				{
    					month = 1;
    				}
    			}
    			break;

    		case 10:
    			if(day == 31)
    			{
    				day = 1;
    				month++;
    				if(month == 13)
    				{
    					month = 1;
    				}
    			}
    			break;

    		case 11:
    			if(day == 30)
    			{
    				day = 1;
    				month++;
    				if(month == 13)
    				{
    					month = 1;
    				}
    			}
    			break;

    		case 12:
    			if(day == 31)
    			{
    				day = 1;
    				month++;
    				if(month == 13)
    				{
    					month = 1;
    				}
    			}
    			break;

    		case 13: month = 1;break;
    	}
    }

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        String system_password = "admin";
        String password_given;
        int o = 0;

        while(true)
        {
        	System.out.printf("\n Insert the System Password (default: 'admin', leave: 'exit'): ");
        	password_given = input.nextLine();
        	if(password_given.equals(system_password))
        	{
        		int i;
        		int flag = 0;
        		int id;
        		for(i=0;i<1000;i++)
        		{
        			ID[i] = -1;
                    two_friday[i] = 0;
        		}
		
        		while(o == 0)
        		{
        			System.out.printf("//////////////////////////////////////////////////////////////////////\n");
        			System.out.printf("///                                                                ///\n");
        			System.out.printf("///        Insert the operation you want followed by a enter:      ///\n");
        			System.out.printf("///                                                                ///\n");
        			System.out.printf("/// 1 : Add - adds a new employee                                  ///\n");
        			System.out.printf("/// 2 : Del - Removes a employee                                   ///\n");
        			System.out.printf("/// 3 : TCard - Launch a Time Card                                 ///\n");
        			System.out.printf("/// 4 : SResult - Launch Selling Result                            ///\n");
        			System.out.printf("/// 5 : Tax - Launch a tax of service                              ///\n");
        			System.out.printf("/// 6 : EChange - Change employee details                          ///\n");
        			System.out.printf("/// 7 : TPayment - Today Payments                                  ///\n");
        			System.out.printf("/// 8 : UR - Undo / Redo                                           ///\n");
        			System.out.printf("/// 9 : Payment Schedule                                           ///\n");
        			System.out.printf("/// 10 : Create New Payment Schedule                               ///\n");
        			System.out.printf("/// 11 : Spend the Day                                             ///\n");
        			System.out.printf("/// 12 : Shows a Employee Detail                                   ///\n");
        			System.out.printf("/// 13 : Change System Password                                    ///\n");
        			System.out.printf("/// 14 : Exit 		                                              ///\n");
        			System.out.printf("///                                                                ///\n");
        			/////gambiarra
        			if(day < 10 && month < 10)
        			{
        				System.out.printf("/// Date : 0g / 0%d / 2019                                        ///\n");
        			}
        			else if(day < 10)
        			{
        				System.out.printf("/// Date : 0%d / %d / 2019                                         ///\n");
        			}
        			else if(month < 10)
        			{
        				System.out.printf("/// Date : %d / 0%d / 2019                                         ///\n");
        			}
        			else
        			{
        				System.out.printf("/// Date : %d / %d / 2019                                          ///\n");
        			}
        			///
        			System.out.printf("///                                                                ///\n");
        			System.out.printf("//////////////////////////////////////////////////////////////////////\n");
        		    System.out.printf("\n Operation: ");
        		    int option = input.nextInt();
        		    System.out.println("\n\n ");
					consoleClear();
        		    switch(option)
        		    {
		
        	    	    case 1:
        	    	        for(i = 0;i<1000;i++)
        	    	        {
        	    	            if(ID[i] == -1)//procura uma posicao vaga//a posicao tambem eh o id
        	    	            {
        	    	                addEmployee(i);
        	    	                flag++;
        	    	                System.out.println("\n\n  Employee added in the system!\n\n");
        	    	                break;
        	    	            }
        	    	        }
        	    	        if(flag > 1000)
        	    	        {
        	    	            System.out.println("\n\n No vacancy!");
        	    	        }
        	    	        break;
		
        	    	    case 2:
        	    	        int q = 0;
        	    	        System.out.printf("\n\n Insert the ID from the Employee you want to remove: ");
        	    	        id = input.nextInt();
        	    	        q = delEmployee(id);
        	    	        if(q == 1)
        	    	        {
        	    	            flag--;
        	    	        }
        	    	        break;
		
        	    	    case 3:
        	    	        System.out.printf("\n\n Insert the your ID: ");
        	    	        id = input.nextInt();
        	    	        timeCard(id);
        	    	        break;
		
        	    	    case 4:
							System.out.printf("\n\n Insert your ID: ");
        	    	        id = input.nextInt();
        	    	        sellingResult(id);
        	    	        break;
		
        	    	    case 5:
        	    	    	System.out.printf("\n\n Insert the Syndicate ID: ");
        	    	        id = input.nextInt();
        	    	        serviceTax(id);
        	    	        break;
		
        	    	    case 6:
							System.out.printf("\n\n Insert your ID: ");
        	    	        id = input.nextInt();
        	    	        if(id >= 0)
        	    	        {
        	    	        	changeDetails(id);
        	    	        }
        	    	        else
        	    	        {
        	    	        	System.out.println("\n\n\n\n    Invalid ID!!\n\n");
        	    	        }
        	    	        break;
		
        	    	    case 7:
        	    	    	//todayPayments();
        	    	        break;
		
        	    	    case 8:
        	    	        break;
		
        	    	    case 9:
        	    	        break;
		
        	    	    case 10:
        	    	        break;
		
        	    	    case 11:
        	    	    	spendDay();
        	    	    	break;
	
        	    	    case 12:
        	    	    	System.out.printf("\n\n Insert your ID: ");
        	    	        id = input.nextInt();
        	    	        showDetails(id);
        	    	        break;
        	    	    
        	    	    case 13:
        	    	    	System.out.printf("\n\n Insert the new System Password: ");
        	    	    	input.nextLine();
        	    	    	system_password = input.nextLine();
        	    	    	System.out.printf("\n\n     System Password Changed With Sucess!!!");
        	    	    	System.out.printf("\n\n Press anykey to continue...");
                        	input.nextLine();
                        	break;

                        case 14:
                        	o = 1;
                        	break;

        	    	    default:
        	    	        System.out.println("\n\n Invalid Option! - Try Again");
        	    	        break;
        	    	}
        		}
        	}
        	else if(password_given.equals("exit"))
        	{
        		System.exit(0);
        	}
        	else
        	{
        		System.out.println("\n\n\n              Invalid Password!!\n                  Try again!!!\n\n\n\n   ");
        	}
        }     
    }
}