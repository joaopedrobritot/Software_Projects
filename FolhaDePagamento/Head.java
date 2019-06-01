import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Head {

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

/////////////////////////////////////////////////////////////////////////////////////////////////////

//     Date Settings

    private static int day = 1;// starting the year
    private static int month = 1;// ^^^^^^^^^^^^
    private static String week_name[] = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};// aux for printing the day of week
    private static int day_of_week = 1;// this controls the day of week
    private static int last_day_month[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};// last day of every month (not considering bisect years)

/////////////////////////////////////////////////////////////////////////////////////////////////////
    /*

            CODE BEFORE UNDO / REDO
        
    */

    private static void addEmployee(int id) 
    {

        Scanner input = new Scanner(System.in);
        System.out.printf("\n Insert your name followed by a enter: ");
        name[id] = input.nextLine();
        System.out.printf("\n\n Insert you address followed by a enter: ");
        address[id] = input.nextLine();
        System.out.printf("\n\n Insert the type:\n 'hourly'\n 'salaried'\n 'commissioned'\n\n Type: ");
        type[id] = input.nextLine();
        if("hourly".equalsIgnoreCase(type[id].toLowerCase()) )
        {
            payment_week[id] = 4; 
        }
        else if("salaried".equalsIgnoreCase(type[id].toLowerCase()) )
        {
            salaried_default[id] = 1;
            payment_day[id] = dayUtil(month);
        }
        else if("commissioned".equalsIgnoreCase(type[id].toLowerCase()) )
        {
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
        System.out.printf("\n\n Insert your salary: ");
        salary[id] = input.nextDouble();
        System.out.printf("\n\n Do you belong to the syndicate?\n\n 0 - No\n 1 - Yes\n\n Your Choice: ");
        int a = input.nextInt();
        if(a == 1)
        {
            syndicate[id] = 1;
            System.out.printf("\n Insert your Syndicate ID (Numbers Only): ");
            syndicate_id[id] = input.nextInt();
            System.out.printf("\n Insert your Syndicate Tax: ");
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
        System.out.printf("\n\n Employee ID: %d\n Name: %s\n Address: %s\n Type: %s\n", ID[id], name[id], address[id], type[id]);
        System.out.printf(" Salary: %.2f\n",salary[id]);
        if(a == 1)
        {
            System.out.println(" Belong to Syndicate: Yes\n Syndicate ID: " + syndicate_id[id]);
            System.out.println("\n\n\n Save your Syndicate ID number!!!!\n\n");
        }
        else
        {
            System.out.printf("\n Belong to Syndicate: No\n");
        }
        
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
                    if(hours[id] > 8 && type[id].equalsIgnoreCase("hourly"))// hourly employees only
                    {
                        int limit = (hours[id] - 8);
                        double bonus = (salary[id] * 1.5) / 100;
                        for(int i = 0;i < limit;i++)
                        {
                            extra_hour[id] += bonus;
                        }
                        System.out.printf("\n\n  Extra salary: %.2f\n\n ", extra_hour[id]);
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
        }
        else
        {
            System.out.println("\n\n Employee not found");
            System.out.printf("\n\n\n      Press any key to continue...\n\n ");
            input.nextLine();
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
                    break;
    
                case 2:
                    System.out.println("Insert the new address followed by a enter: ");
                    input.nextLine();
                    address[id] = input.nextLine();
                    System.out.println("\n\n    Change done!!\n\n ");
                    break;
    
                case 3:
                    System.out.println("Insert the new type followed by a enter ('hourly' / 'salaried' / 'commissioned'):");
                    input.nextLine();
                    String given = input.nextLine();
                    if("hourly".equalsIgnoreCase(given))
                    {
                        payment_week[id] = 4; 
                    }
                    else if("salaried".equalsIgnoreCase(given))
                    {
                        salaried_default[id] = 1;
                        payment_day[id] = dayUtil(month);
                    }
                    else if("commissioned".equalsIgnoreCase(given))
                    {
                        payment_week[id] = 4;
                    }
                    else
                    {
                        System.out.printf("\n\n Invalid Type...Setting to Default: 'hourly'\n\n");
                        given = "hourly";
                        payment_week[id] = 4;
                    }
                    type[id] = given;
                    System.out.println("\n\n    Change done!!\n\n ");
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
              System.out.printf("\n\n Press enter to continue...");
              input.nextLine();
            }
        }
    }

    private static void sellingResult(int id)
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
            System.out.printf("\n\n    Press enter to continue... ");
            input.nextLine();
        }
        consoleClear();
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
                case "hourly":
                    System.out.println(" Hourly");
                    System.out.printf(" Salary: %.2f\n Extra Salary: %.2f\n Payment: ", salary[id], extra_hour[id]);
                    break;

                case "salaried":
                    System.out.println(" Salaried");
                    System.out.printf(" Salary: %.2f\n Payment: ", salary[id]);
                    break;

                case "commissioned":
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
        for(int i = 0; i < 1000; i++)
        {
            if(ID[i] != -1)
            {
                System.out.printf("\n\n Name: %s\n Address: %s\n Type:", name[i], address[i]);
                switch(type[i])
                {
                    case "hourly":
                        System.out.println(" Hourly");
                        System.out.printf(" Salary: %.2f\n Extra Salary: %.2f\n Payment: ", salary[i], extra_hour[i]);
                        break;
    
                    case "salaried":
                        System.out.println(" Salaried");
                        System.out.printf(" Salary: %.2f\n Payment: ", salary[i]);
                        break;
    
                    case "commissioned":
                        System.out.println(" Commissioned");
                        System.out.printf(" Salary: %.2f\n Selling Results: %.2f\n Payment: ", salary[i], selling_result[i]);
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
                    System.out.printf(" Not in Syndicate\n Hours: %d\n Date Start: %s\n Date End: %s\n\n", hours[i], date_start[i], date_end[i]);
                }
                System.out.printf("\n\n Press enter to go to next Employee...");
                input.nextLine();
            }
            System.out.printf("\n\n\n\n");
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

    private static void todayPayments()
    {
        Scanner input = new Scanner(System.in);
        int flag = 0;
        int cont = 1;

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
                            if(syndicate[i] == 1)
                            {
                                System.out.printf("/// %d - ID: %d   Name: %s   Type: Hourly   Salary: %.2f   Syndicate: Yes   Syndicate ID: %d  Payment Method: ", cont, i, name[i] , (salary[i] + extra_hour[i] - syndicate_tax[i] - service_tax[i]), syndicate_id[i]);
                            }
                            else
                            {
                                System.out.printf("/// %d - ID: %d   Name: %s   Type: Hourly   Salary: %.2f   Syndicate: No  Payment Method: ", cont, i, name[i] , salary[i] + extra_hour[i]);
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
                        }
                        break;

                    case "salaried": //salaried
                        if(salaried_default[i] == 1)// receives salary in the last business day
                        {
                            if(day == dayUtil(month))
                            {
                                if(syndicate[i] == 1)
                                {
                                    System.out.printf("/// %d - ID: %d   Name: %s   Type: Salaried   Salary: %.2f   Syndicate: Yes   Syndicate ID: %d  Payment Method: ", cont, i, name[i] , (salary[i] - syndicate_tax[i] - service_tax[i]), syndicate_id[i]);
                                }
                                else
                                {
                                    System.out.printf("/// %d - ID: %d   Name: %s   Type: Salaried   Salary: %.2f   Syndicate: No  Payment Method: ", cont, i, name[i] , salary[i]);
                                }
                                
                                switch(payment[i])
                                {
                                    case 0: System.out.printf("Postal Check\n");break;
                                    case 1: System.out.printf("Check in Hands\n");break;
                                    case 2: System.out.printf("Bank Deposit\n");break;
                                }
                                cont++;
                                flag = 1;
                            }
                        }
                        else // receives in the day he choose in the payment agenda
                        {
                            if(day == payment_day[i])
                            {
                                if(syndicate[i] == 1)
                                {
                                    System.out.printf("/// %d - ID: %d   Name: %s   Type: Salaried   Salary: %.2f   Syndicate: Yes   Syndicate ID: %d  Payment Method: ", cont, i, name[i] , (salary[i] - syndicate_tax[i] - service_tax[i]), syndicate_id[i]);
                                }
                                else
                                {
                                    System.out.printf("/// %d - ID: %d   Name: %s   Type: Salaried   Salary: %.2f   Syndicate: No  Payment Method: ", cont, i, name[i] , salary[i]);
                                }
                                
                                switch(payment[i])
                                {
                                    case 0: System.out.printf("Postal Check\n");break;
                                    case 1: System.out.printf("Check in Hands\n");break;
                                    case 2: System.out.printf("Bank Deposit\n");break;
                                }
                                cont++;
                                flag = 1;
                            }
                        }
                        
                        break;

                    case "commissioned": // commissioned
                        if(two_week[i] >= 2)
                        {
                            if(syndicate[i] == 1)
                            {
                                System.out.printf("/// %d - ID: %d   Name: %s   Type: Commissioned   Salary: %.2f   Syndicate: Yes   Syndicate ID: %d  Payment Method: ", cont, i, name[i] , salary[i] + selling_result[i] - syndicate_tax[i] - service_tax[i], syndicate_id[i]);
                            }
                            else
                            {
                                System.out.printf("/// %d - ID: %d   Name: %s   Type: Commissioned   Salary: %.2f   Syndicate: No  Payment Method: ", cont, i, name[i] , salary[i] + selling_result[i]);
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
        System.out.printf("///////////////////////////////////////////\n\n");
        input.nextLine();
        consoleClear();
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
        switch(month)
        {
            case 1:
                if(day == 32)
                {
                    day = 1;
                    month++;
                    if(month == 13)
                    {
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
                break;

            case 2:
                if(day == 29)
                {
                    day = 1;
                    month++;
                    if(month == 13)
                    {
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
                break;

            case 3:
                if(day == 32)
                {
                    day = 1;
                    month++;
                    if(month == 13)
                    {
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
                break;

            case 4:
                if(day == 31)
                {
                    day = 1;
                    month++;
                    if(month == 13)
                    {
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
                break;

            case 5:
                if(day == 32)
                {
                    day = 1;
                    month++;
                    if(month == 13)
                    {
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
                break;

            case 6:
                if(day == 31)
                {
                    day = 1;
                    month++;
                    if(month == 13)
                    {
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
                break;

            case 7:
                if(day == 32)
                {
                    day = 1;
                    month++;
                    if(month == 13)
                    {
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
                break;

            case 8:
                if(day == 32)
                {
                    day = 1;
                    month++;
                    if(month == 13)
                    {
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
                break;

            case 9:
                if(day == 31)
                {
                    day = 1;
                    month++;
                    if(month == 13)
                    {
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
                break;

            case 10:
                if(day == 32)
                {
                    day = 1;
                    month++;
                    if(month == 13)
                    {
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
                break;

            case 11:
                if(day == 31)
                {
                    day = 1;
                    month++;
                    if(month == 13)
                    {
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
                break;

            case 12:
                if(day == 32)
                {
                    day = 1;
                    month++;
                    if(month == 13)
                    {
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
                break;

            case 13: 
                month = 1;
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
                break;
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
        int cont = 1;
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

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        String system_password = "admin";
        String password_given;
        initiateSchedule();
        int o = 0;

        while(true)
        {
            System.out.printf("\n Insert the System Password (default: 'admin', leave: 'exit'): ");
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
                    /////gambiarra
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
                    ///
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
                                flag--;
                            }
                            consoleClear();
                            break;
        
                        case 3:
                            System.out.printf("\n\n Insert the your ID: ");
                            id = input.nextInt();
                            timeCard(id);
                            consoleClear();
                            break;
        
                        case 4:
                            System.out.printf("\n\n Insert your ID: ");
                            id = input.nextInt();
                            sellingResult(id);
                            consoleClear();
                            break;
        
                        case 5:
                            System.out.printf("\n\n If have two or more employee with the same Syndicate ID\n you will have to add service tax to both of them\n");
                            System.out.printf("\n\n Insert the Syndicate ID: ");
                            id = input.nextInt();
                            serviceTax(id);
                            consoleClear();
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
                                System.out.printf("\n\n Press enter to continue...");
                                input.nextLine();
                                input.nextLine();
                            }
                            consoleClear();
                            break;
        
                        case 7:
                            todayPayments();
                            consoleClear();
                            break;
        
                        case 8:
                            System.out.printf("\n\n Comming Soon...\n\n");
                            System.out.printf("\n\n Press enter to continue...");
                            input.nextLine();
                            input.nextLine();
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
