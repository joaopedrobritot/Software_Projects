import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Employee {

    String name;
    String address;
    int type;// 0 - hour // 1 - salaried // 2 - commissioned
    double salary;
    int payment;// 0 - Postal Check // 1 - Receive check in hands // 2 - Bank Deposit
    int hours;
    int days_worked;
    int ID;
    boolean syndicate;
    int syndicate_id;
    int syndicate_tax;
    int service_tax;
    String date_start;
    String date_end;

    public void addEmployee(int id) 
    {

        Scanner input = new Scanner(System.in);
        System.out.printf("\n Insert your name followed by a enter: ");
        this.name = input.nextLine();
        System.out.printf("\n\n Insert you address followed by a enter: ");
        this.address = input.nextLine();
        System.out.printf("\n\n Insert the type:\n hourly - 0\n salaried - 1\n commissioned - 2\n\n Type: ");
        this.type = input.nextInt();
        System.out.printf("\n\n Choose your Payment Method: \n\n 0 - Postal Check\n 1 - Receive check in hands\n 2 - Bank Deposit\n\n  Your choice: ");
        this.payment = input.nextInt();
        System.out.printf("\n\n Insert your salary: ");
        this.salary = input.nextDouble();
        System.out.println("\n\n Do you belong to the syndicate?\n\n 0 - No\n 1 - Yes\n\n");
        int a = input.nextInt();
        if(a == 1)
        {
        	this.syndicate = true;
        	System.out.println("\n Insert your Syndicate ID (Numbers Only): ");
        	this.syndicate_id = input.nextInt();
        	System.out.println("\n Insert your Syndicate Tax: ");
        	this.syndicate_tax = input.nextInt();
        }
        else
        {
        	this.syndicate = false;
        }
        this.ID = id;
        //////printando
        System.out.printf("\n\n Employee ID: %d\n Name: %s\n Address: %s\n Type: ", this.ID, this.name, this.address);
        switch(this.type)
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
        	System.out.println(" Belong to Syndicate: Yes\n Syndicate ID: " + this.syndicate_id);
        }
        else
        {
        	System.out.println("\n Belong to Syndicate: No\n");
        }
        System.out.printf(" Salary: %.2f\n",this.salary);
        System.out.println("\n\n\n Save your ID number!!!!\n\n");
        System.out.printf("\n\n Press anykey to continue...");
        input.nextLine();
        input.nextLine();
    }

    public int delEmployee(Employee list[], int id) 
    {
    	Scanner input = new Scanner(System.in);
        int i;
        int flag = 0;
        if(list[id].ID != -1)
        {
            list[id] = new Employee();
            list[id].ID = -1;
            flag = 1;
            System.out.println("\n\n Employee removed with sucess!\n\n");
            System.out.printf(" Press anykey to continue...");
            input.nextLine();
            input.nextLine();
        }
        else
        {
            System.out.println("\n\n Employee not found!\n");
        }
        return flag;
    }

    public void timeCard(Employee list[], int id) 
    {
        Scanner input = new Scanner(System.in);
    	int hour;
        if(list[id].ID != -1)
        {
        	System.out.println("\n\n Insert the hours in the format : 'HH:mm' where 'HH' is hours and 'mm' minutes, followed by enter\n\n\n");
        	System.out.println("\n\n Are you coming? (1 - yes / 0 - no)   ");
        	int choice = input.nextInt();
        	if(choice == 0)
        	{
        		System.out.printf("\n\n\n Insert the hours (end): ");
        		input.nextLine();
        		list[id].date_end = input.nextLine();
        		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        		Date start = null;
       			Date end = null;
        		try 
        		{
					start = format.parse(list[id].date_start);
					end = format.parse(list[id].date_end);

                    long diff = end.getTime() - start.getTime();
                    int diffHours = (int)diff / (60 * 60 * 1000) % 24;
                    diffHours = Math.abs(diffHours);
                    System.out.printf("\n\n alou: %d\n\n ",diffHours);
                    if(start.getTime() > end.getTime())
                    {
                    	diffHours = 24 % diffHours;
                    }
                    System.out.print("\n\n\n\n  " + diffHours + " hours today\n\n ");
                    hour = diffHours;
				    
					list[id].hours = hour;
					
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
                list[id].date_start = input.nextLine();
                
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

    public void showDetails() 
    {
    	Scanner input = new Scanner(System.in);
        if(this.ID != -1)
        {
            System.out.printf("\n\n Name: %s\n Address: %s\n Type:", this.name, this.address);
            switch(this.type)
            {
            	case 0:
            	System.out.println(" Hourly");break;
            	case 1:
            	System.out.println(" Salaried");break;
            	case 2:
            	System.out.println(" Commissioned");break;
            }
            System.out.printf(" Salary: %.2f\n Payment: ", this.salary);
            switch(this.payment)
            {
            	case 0:
            	System.out.println("Postal Check");break;
            	case 1:
            	System.out.println("Postal Check in hands");break;
            	case 2:
            	System.out.println("Bank Deposit");break;
            }
            if(this.syndicate)
            {
            	System.out.printf(" Belong to Syndicate\n Syndicate ID: %d\n Syndicate Tax: %d\n Hours: %d\n Date Start: %s\n Date End: %s\n\n ", this.syndicate_id, this.syndicate_tax, this.hours, this.date_start, this.date_end);
            }
            else
            {
            	System.out.printf(" Not in Syndicate\n Hours: %d\n Date Start: %s\n Date End: %s\n\n", this.hours, this.date_start, this.date_end);
            }
            System.out.printf("\n\n Press anykey to continue...");
            input.nextLine();
        }
    }

    public void changeDetails(Employee list[], int id)
    {
    	if(this.ID == id && this.ID != -1)
    	{
    		Scanner input = new Scanner(System.in);
    		while(true)
    		{

    		  System.out.println("\n\n Insert what will you change:\n\n 1 - Name\n 2 - Address\n 3 - Type\n 4 - Payment Method\n 5 - Syndicate Status\n 6 - Syndicate ID\n 7 - Syndicate Tax\n 8 - Return to menu\n\n ");
    		  int choice = input.nextInt();
    		  switch(choice)
    		  {
    		  	case 1:
    				System.out.println("Insert the new name followed by a enter: ");
    				input.nextLine();
    				this.name = input.nextLine();
    				System.out.println("\n\n    Change done!!\n\n ");
    				break;
    
    		  	case 2:
    				System.out.println("Insert the new address followed by a enter: ");
    				input.nextLine();
    				this.address = input.nextLine();
    				System.out.println("\n\n    Change done!!\n\n ");
    				break;
    
    		  	case 3:
    				System.out.println("Insert the new type followed by a enter (0 - hourly / 1 - salaried / 2 - commissioned):");
    				int given = input.nextInt();
    				if(given == 0 || given == 1 || given == 2)
    				{
    					this.type = given;
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
    					this.payment = dado;
    					System.out.println("\n\n    Change done!!\n\n ");
    				}
    				else
    				{
    					System.out.println("\n\n Invalid Method!!\n\n ");
    				}
    				break;
    
    		  	case 5:
    				System.out.println("\n\n Insert your new status: ( 1 - belong to syndicate // 2 - not in syndicate");
    				int alo = input.nextInt();
    				if(alo == 1)
    				{
    					this.syndicate = true;
    					System.out.println("\n\n    Change done!!\n\n ");
    				}
    				else if(alo == 2)
    				{
    					this.syndicate = false;
    					System.out.println("\n\n    Change done!!\n\n ");
    				}
    				else
    				{
    					System.out.println("\n\n Invalid option!!\n\n ");
    				}
    				break;
    
    		  	case 6:
                    if(this.syndicate)
                    {
                        System.out.println("\n\n Insert the new Syndicate ID: ");
                        this.syndicate_id = input.nextInt();
                        System.out.println("\n\n    Change done!!\n\n ");
                    }
                    else
                    {
                        System.out.printf("\n\n    You do not belong to the Syndicate!!\n\n");
                        System.out.printf("\n\n Press anykey to continue...");
                        input.nextLine();
                    }
    				
    				break;
    
    		  	case 7:
                    if(this.syndicate)
                    {
                        System.out.println("\n Insert the new Syndicate Tax: ");
                        this.syndicate_tax = input.nextInt();
                        System.out.println("\n\n    Change done!!\n\n ");
                    }
    				else
                    {
                        System.out.printf("\n\n    You do not belong to the Syndicate!!\n\n");
                        System.out.printf("\n\n Press anykey to continue...");
                        input.nextLine();
                    }
                    
    				break;
    
    		  	case 8:
    		  		return;

                default:
                    System.out.printf("\n\n   Invalid Option!! / Press enter to try again");
                    input.nextLine();
                    list[id].changeDetails(list, id);
    		  }
    		}
    	}
    }
}
