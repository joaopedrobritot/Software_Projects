import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Head {

//     Constants

    final static double tax_hourly = 30.0;

/////////////////////////////////////////////////////////////////////////////////////////////////////

//     Schedules

    private static String agenda_type[] = new String[1000];// Weekly / Monthly / Biweekly
    private static String agenda_option[] = new String[1000];// day of week OR day of month
    private static int agenda_id[] = new int[1000];// to select the correct Schedule in function 9

/////////////////////////////////////////////////////////////////////////////////////////////////////

//     Employees

    private static String name[] = new String[1000];
    private static String address[] = new String[1000];
    private static String date_start[] = new String[1000];// timecard for comming
    private static String date_end[] = new String[1000];// timecard for leaving
    private static String type[] = new String[1000];// hourly // salaried // commissioned
    private static double salary[] = new double[1000];
    private static double extra_hour[] = new double[1000];// displays extra salary of employee (hourly only)
    private static double selling_result[] = new double[1000];
    private static double syndicate_tax[] = new double[1000];
    private static double service_tax[] = new double[1000];
    private static int payment_day[] = new int[1000];// displays a salaried day of payment (refreshed every month)
    private static int payment_week[] = new int[1000]; // displays a hourly or commissioned day of week of payment
    private static int salaried_default[] = new int[1000];// displays if a salaried employee receives salary in last business day of the month
    private static int two_week[] = new int[1000];// verify if a commissioned is in his second week (to pay him)
    private static int payment[] = new int[1000];// 0 - Postal Check // 1 - Receive check in hands // 2 - Bank Deposit
    private static int hours[] = new int[1000];// hours worked in the last day timecard was registered
    private static int ID[] = new int[1000];// indicates what's position of the employee
    private static int syndicate[] = new int[1000];// 1 belong to syndicate // 0 - not in syndicate
    private static int syndicate_id[] = new int[1000];
    private static int days_worked[] = new int[1000]; // displays the worked days of an employee
    private static boolean received_tax[] = new boolean[1000]; // true if a employee has been taxed by syndicate in that month
    
/////////////////////////////////////////////////////////////////////////////////////////////////////

//     States Saves

    private static int state_index = 0; // display the current state in use
    private static int states_size = 0; // display the size of the states

    private static String STATE_name[][] = new String[51][1000];
    private static String STATE_address[][] = new String[51][1000];
    private static String STATE_date_start[][] = new String[51][1000];
    private static String STATE_date_end[][] = new String[51][1000];
    private static String STATE_type[][] = new String[51][1000];                      //////////////////
    private static double STATE_salary[][] = new double[51][1000];                    //              //
    private static double STATE_extra_hour[][] = new double[51][1000];                //     Undo     //
    private static double STATE_selling_result[][] = new double[51][1000];            //     Redo     //
    private static double STATE_syndicate_tax[][] = new double[51][1000];             //              //
    private static double STATE_service_tax[][] = new double[51][1000];               //////////////////
    private static int STATE_payment_day[][] = new int[51][1000];
    private static int STATE_payment_week[][] = new int[51][1000];
    private static int STATE_salaried_default[][] = new int[51][1000];
    private static int STATE_two_week[][] = new int[51][1000];
    private static int STATE_payment[][] = new int[51][1000];
    private static int STATE_hours[][] = new int[51][1000];
    private static int STATE_ID[][] = new int[51][1000];
    private static int STATE_syndicate[][] = new int[51][1000];
    private static int STATE_syndicate_id[][] = new int[51][1000];
    private static int STATE_days_worked[][] = new int[51][1000];
    private static boolean STATE_received_tax[][] = new boolean[51][1000];

/////////////////////////////////////////////////////////////////////////////////////////////////////

//     Date Settings

    private static int day = 1;// starting the year
    private static int month = 1;// ^^^^^^^^^^^^
    private static String week_name[] = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};// aux for printing the day of week
    private static int day_of_week = 1;// this controls the day of week
    private static int last_day_month[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};// last day of every month (not considering bisect years)

/////////////////////////////////////////////////////////////////////////////////////////////////////

    private static void addEmployee(int id) 
    {

        Scanner input = new Scanner(System.in);

        System.out.printf("\n Insert your name followed by a enter: ");
        name[id] = input.nextLine();
        System.out.printf("\n\n Insert your address followed by a enter: ");
        address[id] = input.nextLine();
        System.out.printf("\n\n Insert the type:\n 1 - 'hourly'\n 2 - 'salaried'\n 3 - 'commissioned'\n\n Type: ");
        int choice = input.nextInt();
        input.nextLine();
        if(choice == 1)
        {
            type[id] = "hourly";
            payment_week[id] = 4; 
        }
        else if(choice == 2)
        {
            type[id] = "salaried";
            salaried_default[id] = 1;
            payment_day[id] = dayUtil(month);
        }
        else if(choice == 3)
        {
            type[id] = "commissioned";
            payment_week[id] = 4;
        }
        else
        {
            System.out.printf("\n\n Invalid Type...Setting to Default: 'hourly'\n\n");
            type[id] = "hourly";
            payment_week[id] = 4;
        }
        System.out.printf("\n\n Choose your Payment Method: \n\n 0 - Postal Check\n 1 - Receive check in hands\n 2 - Bank Deposit\n\n  Your choice: ");
        payment[id] = input.nextInt();
        if("hourly".equalsIgnoreCase(type[id].toLowerCase()) )
        {
            salary[id] = 0;
        }
        else
        {
            System.out.printf("\n\n Insert the salary per day: ");
            salary[id] = input.nextDouble();
        }
        
        System.out.printf("\n\n Do you belong to the syndicate?\n\n 0 - No\n 1 - Yes\n\n Your Choice: ");
        int a = input.nextInt();
        if(a == 1)
        {
            syndicate[id] = 1;
            System.out.printf("\n Insert your Syndicate ID (Numbers Only): ");
            syndicate_id[id] = input.nextInt();
            System.out.printf("\n Insert your Syndicate Tax: ");
            syndicate_tax[id] = input.nextDouble();
            service_tax[id] = 0;
        }
        else
        {
            syndicate[id] = 0;
            syndicate_id[id] = -1;
            syndicate_tax[id] = 0;
        }
        ID[id] = id;
        extra_hour[id] = 0;
        //////printando
        System.out.printf("\n\n Employee ID: %d\n Name: %s\n Address: %s\n Type: %s\n", ID[id], name[id], address[id], type[id]);
        if(type[id].equalsIgnoreCase("hourly"))
            System.out.printf(" Salary per hour worked: 30.0\n Salary: %.2f\n", salary[id], salary[id]);
        else
            System.out.printf(" Salary per day worked: %.2f\n Salary: %.2f\n", salary[id], salary[id] * days_worked[id]);
        if(a == 1)
        {
            System.out.println(" Belong to Syndicate: Yes\n Syndicate ID: " + syndicate_id[id]);
            System.out.println("\n\n\n Save your Syndicate ID number!!!!\n\n");
        }
        else
            System.out.printf("\n Belong to Syndicate: No\n");
        
        System.out.printf("\n\n\n Save your ID number!!!!\n\n");
        System.out.printf("\n\n    Employee added in the system!\n\n");
        System.out.printf("\n\n Press enter to continue...\n\n\n\n");
        input.nextLine();
        input.nextLine();
        System.out.printf("\n\n\n"); 
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
            salaried_default[id] = -1;
            payment[id] = -1;
            syndicate_id[id] = -1;
            syndicate_tax[id] = 0;
            syndicate[id] = -1;
            salary[id] = 0;
            extra_hour[id] = 0;
            selling_result[id] = 0;
            service_tax[id] = 0;
            type[id] = null;
            payment_week[id] = -1;
            payment_day[id] = -1;
            hours[id] = 0;
            days_worked[id] = 0;
            two_week[id] = 0;
            /////////////////////
            /////
            flag = 1;
            System.out.println("\n\n Employee removed with sucess!\n\n");
            System.out.printf(" Press enter to continue...");
            input.nextLine();
        }
        else
        {
            System.out.println("\n\n Employee not found!\n");
        }
        
        return flag;
    }

    private static boolean timeCard(int id) 
    {
        Scanner input = new Scanner(System.in);
        int hour;
        if(ID[id] != -1)
        {
            System.out.println("\n\n Insert the hours in the format : 'HH:mm' where 'HH' is hours and 'mm' minutes, followed by enter\n\n Be sure to check this: if hour/minutes < 10 put '01', '09'.!!\n\n ");
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
                    
                    hours[id] += hour; // total worked hours
                    if(hour > 8 && type[id].equalsIgnoreCase("hourly"))// hourly employees only
                    {
                        int limit = (hour - 8);
                        double bonus = (limit * 1.5 * tax_hourly);
                        extra_hour[id] += bonus;
                        System.out.printf("\n\n  Extra salary: %.2f\n\n ", extra_hour[id]);
                    }
                    if(type[id].equalsIgnoreCase("hourly"))
                    {
                        System.out.printf("\n\n Current Salary: %.2f\n Today salary: %.2f\n Total (No Extra included) : %.2f\n\n ", salary[id], (hour * tax_hourly), salary[id] + (hour * tax_hourly));
                        salary[id] += (hour * tax_hourly);
                    }
                    else
                    {
                        if(day_of_week != 5 && day_of_week != 6)
                        {
                            days_worked[id]++;
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
                date_start[id] = input.nextLine();
                
                System.out.println("\n\n    Time Card Approved!\n\n");
                System.out.printf("\n\n\n      Press any key to continue...\n\n ");
                input.nextLine();
            }
            
            return true;   
        }
        else
        {
            System.out.println("\n\n Employee not found");
            System.out.printf("\n\n\n      Press any key to continue...\n\n ");
            input.nextLine();
            
            return false;
        }
    }

    private static boolean changeDetails(int id)
    {
        Scanner input = new Scanner(System.in);
        boolean change = false;
        if(ID[id] != -1)
        {
            while(true)
            {
              System.out.printf("//////////////////////////////////////////////////////////////////////\n");
              System.out.printf("///                                                                ///\n");
              System.out.printf("///                   Insert what will you change:                 ///\n");
              System.out.printf("///                                                                ///\n");
              System.out.printf("/// 1 - Name                                                       ///\n");
              System.out.printf("/// 2 - Address                                                    ///\n");
              System.out.printf("/// 3 - Type                                                       ///\n");
              System.out.printf("/// 4 - Payment Method                                             ///\n");
              System.out.printf("/// 5 - Syndicate Status                                           ///\n");
              System.out.printf("/// 6 - Syndicate ID                                               ///\n");
              System.out.printf("/// 7 - Syndicate Tax                                              ///\n");
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
                    change = true;
                    break;
    
                case 2:
                    System.out.println("Insert the new address followed by a enter: ");
                    input.nextLine();
                    address[id] = input.nextLine();
                    System.out.println("\n\n    Change done!!\n\n ");
                    change = true;
                    break;
    
                case 3:
                    System.out.println("Insert the new type followed by a enter ('hourly' / 'salaried' / 'commissioned'):");
                    input.nextLine();
                    String given = input.nextLine();
                    if("hourly".equalsIgnoreCase(given))
                    {
                        two_week[id] = 0;
                        days_worked[id] = 0;
                        selling_result[id] = 0;
                        extra_hour[id] = 0;
                        System.out.printf("\n\n Insert the salary per hour: ");
                        salary[id] = input.nextDouble();
                        input.nextLine();
                        System.out.printf("\n\n ");
                        payment_week[id] = 4; 
                    }
                    else if("salaried".equalsIgnoreCase(given))
                    {
                        two_week[id] = 0;
                        salaried_default[id] = 1;
                        days_worked[id] = 0;
                        extra_hour[id] = 0;
                        selling_result[id] = 0;
                        System.out.printf("\n\n Insert the salary per day: ");
                        salary[id] = input.nextDouble();
                        input.nextLine();
                        System.out.printf("\n\n ");
                        payment_day[id] = dayUtil(month);
                    }
                    else if("commissioned".equalsIgnoreCase(given))
                    {
                        two_week[id] = 0;
                        days_worked[id] = 0;
                        extra_hour[id] = 0;
                        selling_result[id] = 0;
                        System.out.printf("\n\n Insert the salary per day: ");
                        salary[id] = input.nextDouble();
                        input.nextLine();
                        System.out.printf("\n\n ");
                        payment_week[id] = 4;
                    }
                    else
                    {
                        System.out.printf("\n\n Invalid Type...Setting to Default: 'hourly'\n\n");
                        days_worked[id] = 0;
                        selling_result[id] = 0;
                        extra_hour[id] = 0;
                        System.out.printf("\n\n Insert the salary per hour: ");
                        salary[id] = input.nextDouble();
                        input.nextLine();
                        System.out.printf("\n\n ");
                        given = "hourly";
                        payment_week[id] = 4;
                    }
                    type[id] = given;
                    System.out.println("\n\n    Change done!!\n\n ");
                    change = true;
                    break;
    
                case 4:
                    System.out.printf("Choose your Payment Method: \n\n 0 - Postal Check via Mail\n 1 - Receive Postal Check in hands\n 2 - Bank Deposit\n\n  Your choice: ");
                    int dado = input.nextInt();
                    if(dado == 0 || dado == 1 || dado == 2)
                    {
                        payment[id] = dado;
                        System.out.println("\n\n    Change done!!\n\n ");
                        change = true;
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
                        System.out.printf("\n Insert your Syndicate ID (Numbers Only): ");
                        syndicate_id[id] = input.nextInt();
                        System.out.printf("\n\n Insert your Syndicate Tax (Numbers Only): ");
                        syndicate_tax[id] = input.nextDouble();
                        input.nextLine();
                        service_tax[id] = 0;
                        System.out.println("\n\n\n    Change done!!\n\n ");
                        change = true;
                    }
                    else if(alo == 0)
                    {
                        syndicate[id] = -1;
                        service_tax[id] = 0;
                        syndicate_tax[id] = 0;
                        System.out.println("\n\n    Change done!!\n\n ");
                        change = true;
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
                        change = true;
                    }
                    else
                    {
                        System.out.printf("\n\n    You do not belong to the Syndicate!!\n\n");
                    }
                    
                    break;
    
                case 7:
                    if(syndicate[id] == 1)
                    {
                        System.out.println("\n Insert the new Syndicate Tax (Numbers Only): ");
                        syndicate_tax[id] = input.nextDouble();
                        System.out.println("\n\n    Change done!!\n\n ");
                        change = true;
                    }
                    else
                    {
                        System.out.printf("\n\n    You do not belong to the Syndicate!!\n\n");
                    }
                    
                    break;
    
                case 8:
                    
                    return false;

                default:
                    System.out.printf("\n\n   Invalid Option!! / Press enter to try again");
                    input.nextLine();
                    
                    return changeDetails(id);

              }
              System.out.printf("\n\n Press enter to continue...");
              input.nextLine();
              
              return change;
            }
        }
        else
        {
            System.out.printf("\n\n\n Invalid ID!!!\n\n\n");
            System.out.printf("\n\n Press enter to return to menu...\n\n");
            input.nextLine();
            
            return change;
        }
    }

    private static boolean sellingResult(int id)
    {
        Scanner input = new Scanner(System.in);
        if(ID[id] != -1)
        {
            if(type[id].equalsIgnoreCase("commissioned"))// must be commissioned
            {
                System.out.printf("\n\n Insert the Selling Value: ");
                selling_result[id] += input.nextDouble();
                System.out.printf("\n\n\n       Selling added to user: %s with the ID: %d\n\n", name[id], ID[id]);
                System.out.printf("\n\n    Press enter to return to menu... ");
                input.nextLine();
                input.nextLine();
                consoleClear();
                
                return true;   
            }
            else
            {
                System.out.printf("\n\n This Employee is not commissioned!!\n\n");
                System.out.printf("\n\n    Press enter to return to menu... ");
                input.nextLine();
            }
        }
        else
        {
            System.out.printf("\n\n\n\n    Invalid ID!!\n\n");
            System.out.printf("\n\n    Press enter to return to menu... ");
            input.nextLine();
        }
        consoleClear();
        
        return false;
    }

    private static boolean serviceTax(int sid)
    {
        Scanner input = new Scanner(System.in);
        int flag = 0;
        for(int i = 0;i<1000;i++)
        {
            if(sid == syndicate_id[i])
            {
                System.out.printf("\n\n Insert the tax value: ");
                service_tax[i] += input.nextDouble();
                System.out.printf("\n\n    Tax added to the user: %s with ID: %d !!!", name[i], ID[i]);
                System.out.printf("\n\n Press enter to continue...");
                input.nextLine();
                input.nextLine();
                flag = 1;
            }
        }
        if(flag == 0)
        {
            System.out.printf("\n\n     Typed Syndicate ID not found in the System!!\n\n\n     Press any key to return to menu... ");
            input.nextLine();
            
            return false;
        }
        else
        {
            
            return true;
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
        double result;
        if(ID[id] != -1)
        {
            System.out.printf("\n\n Name: %s\n Address: %s\n Type:", name[id], address[id]);
            switch(type[id])
            {
                case "hourly":
                    System.out.println(" Hourly");
                    System.out.printf(" Salary per hour worked: 30.0\n Salary (No Extra): %.2f\n Extra Salary: %.2f\n Payment: ", salary[id], extra_hour[id]);
                    break;

                case "salaried":
                    result = salary[id] * days_worked[id];
                    System.out.println(" Salaried");
                    System.out.printf(" Salary per day worked: %.2f\n Salary: %.2f\n Payment: ", salary[id], result);
                    break;

                case "commissioned":
                    result = salary[id] * days_worked[id];
                    System.out.println(" Commissioned");
                    System.out.printf(" Salary per day worked: %.2f\n Salary: %.2f\n Selling Results: %.2f\n Payment: ", salary[id], result, selling_result[id]);
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
                System.out.printf(" Not in Syndicate\n Total Hours Worked (Hourly): %d\n Date Start: %s\n Date End: %s\n\n", hours[id], date_start[id], date_end[id]);
            }
            System.out.printf("\n\n Press enter to continue...");
            input.nextLine();
        }
        else
        {
            System.out.println("\n\n\n\n    Invalid ID!!\n\n");
            System.out.printf("\n\n    Press enter to continue... ");
            input.nextLine();
        }  
    }

    private static void listAllEmployees()
    {
        Scanner input = new Scanner(System.in);
        double result;
        int flag = 0;
        for(int i = 0; i < 1000; i++)
        {
            if(ID[i] != -1)
            {
                System.out.printf("\n\n Name: %s\n Address: %s\n Type:", name[i], address[i]);
                switch(type[i])
                {
                    case "hourly":
                        System.out.println(" Hourly");
                        System.out.printf(" Salary per hour worked: 30.0\n Salary (No Extra): %.2f\n Extra Salary: %.2f\n Payment: ", salary[i], extra_hour[i]);
                        break;
    
                    case "salaried":
                        result = salary[i] * days_worked[i];
                        System.out.println(" Salaried");
                        System.out.printf(" Salary per day worked: %.2f\n Salary: %.2f\n Payment: ", salary[i], result);
                        break;
    
                    case "commissioned":
                        result = salary[i] * days_worked[i];
                        System.out.println(" Commissioned");
                        System.out.printf(" Salary per day worked: %.2f\n Salary: %.2f\n Selling Results: %.2f\n Payment: ", salary[i], result, selling_result[i]);
                        break;
    
                    default:
                    System.out.printf(" NULL");break;
                }
                switch(payment[i])
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
                if(syndicate[i] == 1)
                {
                    System.out.printf(" Belong to Syndicate\n Syndicate ID: %d\n Syndicate Tax: %.2f\n Service Tax: %.2f\n Hours: %d\n Date Start: %s\n Date End: %s\n\n ", syndicate_id[i], syndicate_tax[i], service_tax[i], hours[i], date_start[i], date_end[i]);
                }
                else
                {
                    System.out.printf(" Not in Syndicate\n Total Hours Worked (Hourly): %d\n Date Start: %s\n Date End: %s\n\n", hours[i], date_start[i], date_end[i]);
                }
                System.out.printf("\n\n Press enter to go to next Employee...");
                input.nextLine();
                flag = 1;
            }
            System.out.printf("\n\n\n\n");
        }
        if(flag == 0)
        {
            System.out.printf("\n\n\n No Employees found in the System!!\n\n");
            System.out.printf("\n\n\n Press enter to return to menu...\n\n");
            input.nextLine();
        } 
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

    private static boolean todayPayments()
    {
        Scanner input = new Scanner(System.in);
        double result;
        int flag = 0;
        int cont = 1;
        boolean change = false;

        System.out.printf("\n\n//////////////////////////////////////////////////////////////////////\n");
        System.out.printf("///                                                                ///\n");
        System.out.printf("///                        Today Payments                          ///\n");
        System.out.printf("///                                                                ///\n");
        System.out.printf("//////////////////////////////////////////////////////////////////////\n\n\n");

        for(int i = 0;i<1000;i++)
        {
            if(ID[i] != -1)//achou empregado
            {
                switch(type[i])
                {
                    case "hourly": //hourly
                        if(day_of_week == payment_week[i])// pagos dia de sexta por default
                        {
                            if(syndicate[i] == 1 && !received_tax[i])
                            {
                                System.out.printf("/// %d - ID: %d   Name: %s   Type: Hourly   Salary ( - Syndicate Taxes + Extra Salary): %.2f   Syndicate: Yes   Syndicate ID: %d  Payment Method: ", cont, i, name[i] , (salary[i] + extra_hour[i] - syndicate_tax[i] - service_tax[i]), syndicate_id[i]);
                                received_tax[i] = true;
                            }
                            else
                            {
                                System.out.printf("/// %d - ID: %d   Name: %s   Type: Hourly   Salary ( + Extra Salary): %.2f   Syndicate: No  Payment Method: ", cont, i, name[i] , salary[i] + extra_hour[i]);
                            }
                            
                            switch(payment[i])
                            {
                                case 0: System.out.printf("Postal Check\n");break;
                                case 1: System.out.printf("Check in Hands\n");break;
                                case 2: System.out.printf("Bank Deposit\n");break;
                            }
                            extra_hour[i] = 0;
                            cont++;
                            flag = 1;
                            change = true;
                            salary[i] = 0;
                        }
                        break;

                    case "salaried": //salaried
                        result = salary[i] * days_worked[i];
                        if(salaried_default[i] == 1)// receives salary in the last business day
                        {
                            if(day == dayUtil(month))
                            {
                                if(syndicate[i] == 1 && !received_tax[i])
                                {
                                    System.out.printf("/// %d - ID: %d   Name: %s   Type: Salaried   Salary ( - Syndicate Taxes): %.2f   Syndicate: Yes   Syndicate ID: %d  Payment Method: ", cont, i, name[i] , (result - syndicate_tax[i] - service_tax[i]), syndicate_id[i]);
                                    received_tax[i] = true;
                                }
                                else
                                {
                                    System.out.printf("/// %d - ID: %d   Name: %s   Type: Salaried   Salary: %.2f   Syndicate: No  Payment Method: ", cont, i, name[i] , result);
                                }
                                
                                switch(payment[i])
                                {
                                    case 0: System.out.printf("Postal Check\n");break;
                                    case 1: System.out.printf("Check in Hands\n");break;
                                    case 2: System.out.printf("Bank Deposit\n");break;
                                }
                                cont++;
                                flag = 1;
                                change = true;
                                days_worked[i] = 0;
                            }
                        }
                        else // receives in the day he choose in the payment agenda
                        {
                            if(day == payment_day[i])
                            {
                                if(syndicate[i] == 1 && !received_tax[i])
                                {
                                    System.out.printf("/// %d - ID: %d   Name: %s   Type: Salaried   Salary ( - Syndicate Taxes): %.2f   Syndicate: Yes   Syndicate ID: %d  Payment Method: ", cont, i, name[i] , (result - syndicate_tax[i] - service_tax[i]), syndicate_id[i]);
                                    received_tax[i] = true;
                                }
                                else
                                {
                                    System.out.printf("/// %d - ID: %d   Name: %s   Type: Salaried   Salary: %.2f   Syndicate: No  Payment Method: ", cont, i, name[i] , result);
                                }
                                
                                switch(payment[i])
                                {
                                    case 0: System.out.printf("Postal Check\n");break;
                                    case 1: System.out.printf("Check in Hands\n");break;
                                    case 2: System.out.printf("Bank Deposit\n");break;
                                }
                                cont++;
                                flag = 1;
                                change = true;
                                days_worked[i] = 0;
                            }
                        }
                        
                        break;

                    case "commissioned": // commissioned
                        result = salary[i] * days_worked[i];
                        if(two_week[i] >= 2)
                        {
                            double commissioned_tax = (i/10000) + 0.2;
                            if(syndicate[i] == 1 && !received_tax[i])
                            {
                                System.out.printf("/// %d - ID: %d   Name: %s   Type: Commissioned   Salary ( - Syndicate Taxes + Commissions): %.2f   Syndicate: Yes   Syndicate ID: %d  Payment Method: ", cont, i, name[i] , result + (selling_result[i] * commissioned_tax) - syndicate_tax[i] - service_tax[i], syndicate_id[i]);
                                received_tax[i] = true;
                            }
                            else
                            {
                                System.out.printf("/// %d - ID: %d   Name: %s   Type: Commissioned   Salary ( + Commissions): %.2f   Syndicate: No  Payment Method: ", cont, i, name[i] , result + (selling_result[i] * commissioned_tax));
                            }
                            
                            switch(payment[i])
                            {
                                case 0: System.out.printf("Postal Check\n");break;
                                case 1: System.out.printf("Check in Hands\n");break;
                                case 2: System.out.printf("Bank Deposit\n");break;
                            }
                            selling_result[i] = 0;
                            cont++;
                            two_week[i] = 0;
                            flag = 1;
                            change = true;
                            days_worked[i] = 0;
                        }

                        break;
                }
            }
        }

        if(flag == 0)
        {
            System.out.printf("///                     No Payments Today!!!                       \n");
        }
        System.out.printf("///                                                                \n");
        System.out.printf("///        Press any key to continue...                            \n");
        System.out.printf("///                                                                \n");
        System.out.printf("///________________________________________________________________\n\n");
        input.nextLine();
        consoleClear();
        
        return change;
    }

    private static void spendDay()
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
                    received_tax[i] = false;
                }
                month = 1;
            }
            for(i = 0;i<1000;i++)// refresh payment days for default salaried
            {
                if(ID[i] != -1)
                {
                    if(type[i].equals("salaried") && salaried_default[i] == 1)
                    {
                        payment_day[i] = dayUtil(month);
                    }
                }
            }
        }
        for(i = 0; i < 1000 ; i++)
        {
            if(ID[i] != -1)
            {
                if(type[i].equals("commissioned") && day_of_week == payment_week[i])
                {
                    two_week[i]++;
                }
            }
        }
    }

    private static void initiateSchedule()
    {
        for(int i = 0;i<1000;i++)
        {
            agenda_id[i] = -1;
            agenda_option[i] = null;
            agenda_type[i] = null;
        }
    }

    private static void printSchedule(int id)
    {
        if(agenda_id[id] != -1)
        {
            System.out.printf(" Schedule ID: %d   / Schedule Type: %s   / Schedule Option: %s\n", agenda_id[id], agenda_type[id], agenda_option[id]);
        }
    }

    private static int addSchedule()
    {
        Scanner input = new Scanner(System.in);
        int i;
        int choice;
        int flag = 0;
        int week;
        consoleClear();
        for(i = 0;i<1000;i++)
        {
            if(agenda_id[i] == -1)
            {
                System.out.printf("\n\n//////////////////////////////////////////////////////////////////////\n");
                System.out.printf("///                                                                ///\n");
                System.out.printf("///    Insert the type of the new Schedule:                        ///\n");
                System.out.printf("///                                                                ///\n");
                System.out.printf("///  1 - Weekly                                                    ///\n");
                System.out.printf("///  2 - Monthly                                                   ///\n");
                System.out.printf("///  3 - Biweekly                                                  ///\n");
                System.out.printf("///                                                                ///\n");
                System.out.printf("//////////////////////////////////////////////////////////////////////\n");
                System.out.printf("\n\n Your choice: ");
                choice = input.nextInt();
                input.nextLine();
                consoleClear();
                switch(choice)
                {
                    case 1:
                        agenda_id[i] = i;
                        agenda_type[i] = "Weekly";
                        System.out.printf("\n\n//////////////////////////////////////////////////////////////////////\n");
                        System.out.printf("///                                                                ///\n");
                        System.out.printf("///    Insert the day of the week from the new Schedule:           ///\n");
                        System.out.printf("///                                                                ///\n");
                        System.out.printf("///  0 - Monday                                                    ///\n");
                        System.out.printf("///  1 - Tuesday                                                   ///\n");
                        System.out.printf("///  2 - Wednesday                                                 ///\n");
                        System.out.printf("///  3 - Thursday                                                  ///\n");
                        System.out.printf("///  4 - Friday                                                    ///\n");
                        System.out.printf("///                                                                ///\n");
                        System.out.printf("//////////////////////////////////////////////////////////////////////\n");
                        System.out.printf("\n\n Your choice: ");
                        week = input.nextInt();
                        input.nextLine();
                        if(week >= 0 && week < 5)
                        {
                            agenda_option[i] = week_name[week];

                        }
                        else
                        {
                            System.out.printf("\n\n Invalid option, setting to default day: 'Friday'\n\n");
                            agenda_option[i] = week_name[4];
                            System.out.printf("\n Press enter to continue...\n\n\n\n");
                            input.nextLine();
                            
                        }
                        System.out.printf("\n\n\n\n");
                        printSchedule(i);
                        System.out.printf("\n\n Schedule added in the System!!\n");
                        System.out.printf("\n Press enter to continue...\n\n\n\n");
                        input.nextLine();
                        break;

                    case 2:
                        agenda_id[i] = i;
                        agenda_type[i] = "Monthly";
                        System.out.printf("\n\n//////////////////////////////////////////////////////////////////////\n");
                        System.out.printf("///                                                                ///\n");
                        System.out.printf("///      Insert the day of the month from the new Schedule:        ///\n");
                        System.out.printf("///                                                                ///\n");
                        System.out.printf("///         (  Must be between or equal to 1 and 28!!  )           ///\n");
                        System.out.printf("///  (  Insert 'last' to put on last business day of the month )   ///\n");
                        System.out.printf("///                                                                ///\n");
                        System.out.printf("//////////////////////////////////////////////////////////////////////\n");
                        System.out.printf("\n\n Your choice: ");
                        String day = input.nextLine();
                        if(day.equalsIgnoreCase("last"))
                        {
                            agenda_option[i] = day;
                        }
                        else if(Integer.parseInt(day) >= 1 && Integer.parseInt(day) < 29)
                        {
                            agenda_option[i] = day;
                        }
                        else
                        {
                            System.out.printf("\n\n\n\n Invalid option!!, setting to default: 'last'...");
                            agenda_option[i] = "last";
                            System.out.printf("\n Press enter to continue...\n\n\n\n");
                            input.nextLine();
                        }
                        System.out.printf("\n\n\n\n");
                        printSchedule(i);
                        System.out.printf("\n\n Schedule added in the System!!\n");
                        System.out.printf("\n Press enter to continue...\n\n\n\n");
                        input.nextLine();
                        break;

                    case 3:
                        agenda_id[i] = i;
                        agenda_type[i] = "Biweekly";
                        System.out.printf("\n\n//////////////////////////////////////////////////////////////////////\n");
                        System.out.printf("///                                                                ///\n");
                        System.out.printf("///    Insert the day of the week from the new Schedule:           ///\n");
                        System.out.printf("///                                                                ///\n");
                        System.out.printf("///  0 - Monday                                                    ///\n");
                        System.out.printf("///  1 - Tuesday                                                   ///\n");
                        System.out.printf("///  2 - Wednesday                                                 ///\n");
                        System.out.printf("///  3 - Thursday                                                  ///\n");
                        System.out.printf("///  4 - Friday                                                    ///\n");
                        System.out.printf("///                                                                ///\n");
                        System.out.printf("//////////////////////////////////////////////////////////////////////\n");
                        System.out.printf("\n\n Your choice: ");
                        week = input.nextInt();
                        input.nextLine();
                        if(week >= 0 && week < 5)
                        {
                            agenda_option[i] = week_name[week];
                        }
                        else
                        {
                            System.out.printf("\n\n Invalid option, setting to default day: 'Friday'\n\n");
                            agenda_option[i] = week_name[4];
                            System.out.printf("\n Press enter to continue...\n\n\n\n");
                            input.nextLine();
                        }
                        System.out.printf("\n\n\n\n");
                        printSchedule(i);
                        System.out.printf("\n\n Schedule added in the System!!\n");
                        System.out.printf("\n Press enter to continue...\n\n\n\n");
                        input.nextLine();
                        break;

                    default :
                        System.out.printf("\n\n Invalid option, executing default action: 'return to menu'.\n\n\n");
                        System.out.printf("\n Press enter to continue...\n\n\n\n");
                        input.nextLine();
                        
                        return 0;
                }
                flag = 1;
                break;
            }
        }
        
        return flag;
    }

    private static void listAllSchedules(int id)
    {
        Scanner input = new Scanner(System.in);
        System.out.printf("\n\n//////////////////////////////////////////////////////////////////////\n");
        System.out.printf("///                                                                ///\n");
        System.out.printf("///                      Payment Schedules                         ///\n");
        System.out.printf("///                                                                ///\n");
        System.out.printf("//////////////////////////////////////////////////////////////////////\n\n\n\n\n");
        for(int i = 0;i<1000;i++)
        {
            printSchedule(i);
        }
        while(true)
        {
            System.out.printf("\n\n Insert the Schedule ID you want to change to: ");
            int choice = input.nextInt();
            input.nextLine();
            if(agenda_id[choice] != -1)
            {
                switch(agenda_type[choice])
                {
                    case "Weekly":
                        if(type[id].equalsIgnoreCase("hourly"))
                        {
                            //extra_hour[id] = 0;
                            //salary[id] = 0;
                            switch(agenda_option[choice])
                            {
                                case "Monday":
                                    payment_week[id] = 0;
                                    break;

                                case "Tuesday":
                                    payment_week[id] = 1;
                                    break;

                                case "Wednesday":
                                    payment_week[id] = 2;
                                    break;

                                case "Thursday":
                                    payment_week[id] = 3;
                                    break;

                                case "Friday":
                                    payment_week[id] = 4;
                                    break;
                            }
                            System.out.printf("\n\n\n Changes done!!");
                        }
                        else
                        {
                            System.out.printf("\n\n\n This Employee is not an Hourly!!, Please move to 'change settings' on menu.\n\n\n");
                        }
                        break;

                    case "Monthly":
                        if(type[id].equalsIgnoreCase("salaried"))
                        {
                            //days_worked[id] = 0;
                            if(agenda_option[choice].equalsIgnoreCase("last"))
                            {
                                payment_day[id] = dayUtil(month);
                                salaried_default[id] = 1;
                            }
                            else
                            {
                                salaried_default[id] = 0;
                                payment_day[id] = Integer.parseInt(agenda_option[choice]);
                            }
                            System.out.printf("\n\n\n Changes done!!");
                        }
                        else
                        {
                            System.out.printf("\n\n\n This Employee is not an Salaried!!, Please move to 'change settings' on menu.\n\n\n");
                        }
                        break;

                    case "Biweekly":
                        if(type[id].equalsIgnoreCase("commissioned"))
                        {
                            //days_worked[id] = 0;
                            two_week[id] = 0;
                            switch(agenda_option[choice])
                            {
                                case "Monday":
                                    payment_week[id] = 0;
                                    break;

                                case "Tuesday":
                                    payment_week[id] = 1;
                                    break;

                                case "Wednesday":
                                    payment_week[id] = 2;
                                    break;

                                case "Thursday":
                                    payment_week[id] = 3;
                                    break;

                                case "Friday":
                                    payment_week[id] = 4;
                                    break;
                            }
                            System.out.printf("\n\n\n Changes done!!");
                        }
                        else
                        {
                            System.out.printf("\n\n\n This Employee is not an Commissioned!!, Please move to 'change settings' on menu.\n\n\n");
                        }
                        break;

                }
                System.out.printf("\n\n Press enter to continue...\n\n\n\n");
                input.nextLine();
                break;
            }
            else
            {
                System.out.printf("\n\n\n\n Invalid ID!!, Try Again!!..");
                System.out.printf("\n\n Press enter to continue...\n\n\n\n");
                input.nextLine();
                System.out.printf("\n\n\n\n");
            }
        }   
    }

    private static void initiateStates()
    {
        for(int i = 0;i < 1000;i++)
        {
            STATE_name[0][i] = null;
            STATE_address[0][i] = null;
            STATE_date_start[0][i] = null;
            STATE_date_end[0][i] = null;
            STATE_type[0][i] = null;
            STATE_salary[0][i] = 0;
            STATE_extra_hour[0][i] = 0;
            STATE_selling_result[0][i] = 0;
            STATE_syndicate_tax[0][i] = 0;
            STATE_service_tax[0][i] = 0;
            STATE_payment_day[0][i] = -1;
            STATE_payment_week[0][i] = -1;
            STATE_salaried_default[0][i] = -1;
            STATE_two_week[0][i] = 0;
            STATE_payment[0][i] = -1;
            STATE_hours[0][i] = 0;
            STATE_ID[0][i] = -1;
            STATE_syndicate[0][i] = -1;
            STATE_syndicate_id[0][i] = -1;
            STATE_received_tax[0][i] = true;
            STATE_days_worked[0][i] = 0;
        }
    }

    private static void saveState()
    {
        int i;
        if(states_size < 50)
        {
            state_index++;
            states_size = state_index;
            for(i = 0 ; i < 1000 ; i++)
            {
                STATE_name[state_index][i] = name[i];
                STATE_address[state_index][i] = address[i];
                STATE_date_start[state_index][i] = date_start[i];
                STATE_date_end[state_index][i] = date_end[i];
                STATE_type[state_index][i] = type[i];
                STATE_salary[state_index][i] = salary[i];
                STATE_extra_hour[state_index][i] = extra_hour[i];
                STATE_selling_result[state_index][i] = selling_result[i];
                STATE_syndicate_tax[state_index][i] = syndicate_tax[i];
                STATE_service_tax[state_index][i] = service_tax[i];
                STATE_payment_day[state_index][i] = payment_day[i];
                STATE_payment_week[state_index][i] = payment_week[i];
                STATE_salaried_default[state_index][i] = salaried_default[i];
                STATE_two_week[state_index][i] = two_week[i];
                STATE_payment[state_index][i] = payment[i];
                STATE_hours[state_index][i] = hours[i];
                STATE_ID[state_index][i] = ID[i];
                STATE_syndicate[state_index][i] = syndicate[i];
                STATE_syndicate_id[state_index][i] = syndicate_id[i];
                STATE_received_tax[state_index][i] = received_tax[i];
                STATE_days_worked[state_index][i] = days_worked[i];
            }
        }
        else
        {
            System.out.printf("\n\n\n Can't add anymore state (undoRedo capacity full!!)\n\n\n");
            System.out.printf(" Press enter to continue...\n\n\n");
            Scanner input = new Scanner(System.in);
            input.nextLine();
            
        }
    }

    private static void applyState()
    {
        for(int i = 0 ; i < 1000 ; i++)
        {
            name[i] = STATE_name[state_index][i];
            address[i] = STATE_address[state_index][i];
            date_start[i] = STATE_date_start[state_index][i];
            date_end[i] = STATE_date_end[state_index][i];
            type[i] = STATE_type[state_index][i];
            salary[i] = STATE_salary[state_index][i];
            extra_hour[i] = STATE_extra_hour[state_index][i];
            selling_result[i] = STATE_selling_result[state_index][i];
            syndicate_tax[i] = STATE_syndicate_tax[state_index][i];
            service_tax[i] = STATE_service_tax[state_index][i];
            payment_day[i] = STATE_payment_day[state_index][i];
            payment_week[i] = STATE_payment_week[state_index][i];
            salaried_default[i] = STATE_salaried_default[state_index][i];
            two_week[i] = STATE_two_week[state_index][i];
            payment[i] = STATE_payment[state_index][i];
            hours[i] = STATE_hours[state_index][i];
            ID[i] = STATE_ID[state_index][i];
            syndicate[i] = STATE_syndicate[state_index][i];
            syndicate_id[i] = STATE_syndicate_id[state_index][i];
            received_tax[i] = STATE_received_tax[state_index][i];
            days_worked[i] = STATE_days_worked[state_index][i];
        }
    }

    private static void undoRedo()
    {
        Scanner input = new Scanner(System.in);
        System.out.printf("\n\n Insert the option you want:\n    1 - UNDO (undone the previous action)\n    0 - REDO (if a undone was made, then you can step back to before the undone)\n\n\n Choice: ");
        int choice = input.nextInt();
        input.nextLine();
        if(choice == 1)
        {
            if(state_index > 0)
            {
                state_index--;
                applyState();
                System.out.printf("\n\n\n\n    Settings UN-done!!\n\n\n");
            }
            else
                System.out.printf("\n\n\n\n    Undo Limit reached!!\n\n\n");
        }
        else if(choice == 0)
        {
            if(state_index < states_size && state_index < 50)
            {
                state_index++;
                applyState();
                System.out.printf("\n\n\n\n    Settings RE-done!!\n\n\n");
            }
            else
                System.out.printf("\n\n\n\n    Redo Limit reached!!\n\n\n");
        }
        else
            System.out.printf("\n\n\n\n Invalid option!!! , executing default action: 'return to menu'...");

        System.out.printf("\n\n\n Press enter to continue...\n\n");
        input.nextLine();  
    }

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        String system_password = "admin";
        String password_given;
        initiateSchedule();
        initiateStates();
        int o = 0;
        boolean change = false;

        while(true)
        {
            System.out.printf("\n\n\n Insert the System Password (default: 'admin', leave: 'exit'): ");
            password_given = input.nextLine();
            System.out.printf("\n\n\n\n\n");
            if(password_given.equalsIgnoreCase(system_password))
            {
                int i;
                int flag = 0;
                int id;
                for(i=0;i<1000;i++)
                {
                    ID[i] = -1;
                    two_week[i] = 0;
                    selling_result[i] = 0;
                    salaried_default[i] = -1;
                    days_worked[i] = 0;
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
                    System.out.printf("/// 12 : Shows a Employee Details                                  ///\n");
                    System.out.printf("/// 13 : Show all Employees                                        ///\n");
                    System.out.printf("/// 14 : Change System Password                                    ///\n");
                    System.out.printf("/// 15 : Exit                                                      ///\n");
                    System.out.printf("///                                                                ///\n");

                    if(day < 10 && month < 10)
                    {
                        System.out.printf("/// Date : 0%d / 0%d / 2019                                          ///\n", day, month);
                    }
                    else if(day < 10)
                    {
                        System.out.printf("/// Date : 0%d / %d / 2019                                          ///\n", day, month);
                    }
                    else if(month < 10)
                    {
                        System.out.printf("/// Date : %d / 0%d / 2019                                          ///\n", day, month);
                    }
                    else
                    {
                        System.out.printf("/// Date : %d / %d / 2019                                          ///\n", day, month);
                    }
                    
                    System.out.printf("///                                                                ///\n");
                    System.out.printf("/// %s\n", week_name[day_of_week]);
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
                                    change = true;
                                    break;
                                }
                            }
                            if(flag > 1000)
                            {
                                System.out.println("\n\n No vacancy!");
                            }
                            consoleClear();
                            break;
        
                        case 2:
                            int q = 0;
                            System.out.printf("\n\n Insert the ID from the Employee you want to remove: ");
                            id = input.nextInt();
                            q = delEmployee(id);
                            if(q == 1)
                            {
                                change = true;
                                flag--;
                            }
                            consoleClear();
                            break;
        
                        case 3:
                            System.out.printf("\n\n Insert the your ID: ");
                            id = input.nextInt();
                            change = timeCard(id);
                            consoleClear();
                            break;
        
                        case 4:
                            System.out.printf("\n\n Insert your ID: ");
                            id = input.nextInt();
                            change = sellingResult(id);
                            consoleClear();
                            break;
        
                        case 5:
                            System.out.printf("\n\n If have two or more employee with the same Syndicate ID\n you will have to add service tax to both of them\n");
                            System.out.printf("\n\n Insert the Syndicate ID: ");
                            id = input.nextInt();
                            change = serviceTax(id);
                            consoleClear();
                            break;
        
                        case 6:
                            System.out.printf("\n\n Insert your ID: ");
                            id = input.nextInt();
                            if(id >= 0)
                            {
                                change = changeDetails(id);
                            }
                            else
                            {
                                System.out.println("\n\n\n\n    Invalid ID!!\n\n");
                                System.out.printf("\n\n Press enter to continue...");
                                input.nextLine();
                                input.nextLine();
                            }
                            consoleClear();
                            break;
        
                        case 7:
                            change = todayPayments();
                            consoleClear();
                            break;
        
                        case 8:
                            undoRedo();
                            consoleClear();
                            break;
        
                        case 9:
                            System.out.printf("\n\n Insert your ID: ");
                            id = input.nextInt();
                            if(ID[id] != -1)
                            {
                                listAllSchedules(id);
                            }
                            else
                            {
                                System.out.println("\n\n\n\n    Invalid ID!!\n\n");
                                System.out.printf("\n\n Press enter to continue...");
                                input.nextLine();
                                input.nextLine();
                            }
                            consoleClear();
                            break;
        
                        case 10:
                            int z = addSchedule();
                            if(z == 0)
                            {
                                System.out.printf("\n\n Limit exceeded!!\n\n");
                                System.out.printf("\n\n Press enter to continue...");
                                input.nextLine();
                                input.nextLine();
                            }
                            consoleClear();
                            break;
        
                        case 11:
                            spendDay();
                            break;
    
                        case 12:
                            System.out.printf("\n\n Insert your ID: ");
                            id = input.nextInt();
                            
                            showDetails(id);
                            
                            consoleClear();
                            break;
                        
                        case 13:
                            listAllEmployees();
                            System.out.printf("\n\n\n Last Employee!\n\n Press enter to return to menu...\n\n\n\n\n");
                            input.nextLine();
                            consoleClear();
                            break;

                        case 14:
                            System.out.printf("\n\n Insert the new System Password: ");
                            input.nextLine();
                            system_password = input.nextLine();
                            System.out.printf("\n\n     System Password Changed With Sucess!!!");
                            
                            System.out.printf("\n\n Press enter to continue...");
                            input.nextLine();
                            
                            consoleClear();
                            break;

                        case 15:
                            input.nextLine();
                            o = 1;
                            break;

                        default:
                            System.out.println("\n\n Invalid Option! - Try Again");
                            System.out.printf("\n\n Press enter to continue...");
                            input.nextLine();
                            input.nextLine();
                            break;
                    }
                    if(change) // if any change was made, the program save the current state of the employees
                    {
                        saveState();
                        change = false;
                    }
                }
            }
            else if(password_given.equalsIgnoreCase("exit"))
            {
                System.exit(0);
            }
            else
            {
                System.out.printf("\n\n\n              Invalid Password!!\n                  Try again!!!\n\n\n\n   ");
            }
        }
    }
}