package paymentSystemOO;

import java.util.Scanner;

import systemUtilities.UndoRedo;

import java.util.InputMismatchException;

public class ExtraFunctions {
	
	private static Scanner input = new Scanner(System.in);
	
	// Date Settings

		private int day = 31;// starts in 26th June
		private int month = 7; //
		private String week_name[] = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};// aux for printing the day of week
		private int day_of_week = 2;// this controls the day of week // starts in Wednesday
		private int last_day_month[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};// last day of every month (not considering bisect years)
		
		public int getDay() {
			return day;
		}
		public void setDay(int day) {
			if(day >= 1 && day <= 31)
			{
				this.day = day;
			}
		}
		public void addDay()
		{
			this.day++;
		}
		public int getMonth() {
			return month;
		}
		public void setMonth(int month) {
			if(month >= 1 && month <= 12)
			{
				this.month = month;
			}
			
		}
		public String getWeek_name(int i) {
			if(i >= 0 && i <= 6)
			{
				return this.week_name[i];
			}
			else
			{
				return null;
			}
		}
		public String[] getWeek_name()
		{
			return this.week_name;
		}
		public int getDay_of_week() {
			return day_of_week;
		}
		public void setDay_of_week(int day_of_week) {
			if(day_of_week >= 0 && day_of_week <= 6)
				this.day_of_week = day_of_week;
		}
		public void addDay_of_week()
		{
			this.day_of_week++;
		}
		public int getLast_day_month(int month) {
			if(month >= 1 && month <= 12)
				return last_day_month[month];
			else
				return -1;
		}

	/////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
	// Functions
	
	public static void consoleClear()
	{
		for(int i = 0;i<30;i++)
		{
			System.out.printf("\n");
		}
	}
	
	protected void showDetails(Employee list[])
	{
		int id;
		while(true)
		{
			try {
				System.out.printf("\n\n  Insert the ID from the Employee: ");
				id = input.nextInt();
				input.nextLine();
				break;
			}
			catch(InputMismatchException e)
			{
				input.nextLine();
				System.out.printf("\n\n\n  the typed ID is not an integer!!\n\n  Press enter to try again...\n\n\n\n");
				input.nextLine();
			}
		}
		if(list[id] != null)
		{
			list[id].myDetails();
		    System.out.printf("\n\n Press enter to return to Functions...\n\n\n");
		    input.nextLine();
		}
		else
		{
		    System.out.println("\n\n\n\n    Invalid ID!!\n\n");
		    System.out.printf("\n\n    Press enter to return to Functions...\n\n\n");
		    input.nextLine();
		}
		
	}
	
	protected void showAllEmployees(Employee list[])
	{
		boolean flag = false;
		for(int i = 0;i<1000;i++)
		{
			if(list[i] != null)
			{
				list[i].myDetails();
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
		System.out.printf("\n\n\n  End!\n\n\n  Press enter to return to Functions...\n\n\n");
	    input.nextLine();
	}
	
	public int dayUtil(int mth)
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
	
	protected void spendDay(Employee list[])
    {
        int i;
        day++;
        day_of_week++;
        if(this.day_of_week == 7)// day of week
        {
            this.day_of_week = 0;
        }
        if(day == last_day_month[month] + 1)
        {
            day = 1;
            month++;
            if(month == 13)
            {
                month = 1;
            }
            
            for(i=0;i<1000;i++)// refresh the received syndicate tax (mensal tax)
            {
                if(list[i] != null)
                {
                	list[i].setReceived_tax(false);
                }
            }
            
            for(i = 0;i<1000;i++)// refresh payment days for default salaried
            {
                if(list[i] != null)
                {
                    if(list[i] instanceof Salaried)// this instanceof is necessary
                    {
                    	if(( (Salaried)list[i]).isSalaried_default())
                    	{
                    		( (Salaried)list[i]).setPayment_date(dayUtil(month)); // refresh salaried default day of payment for each month
                    	}
                    }
                }
            }
        }
        for(i = 0; i < 1000 ; i++)
        {
            if(list[i] != null)
            {
            	list[i].setCard_submit(false);
                if(list[i] instanceof Commissioned)// this instanceof is necessary too
                {
                	if(( (Commissioned)list[i]).getPayment_date() == day_of_week)
                	{
                		( (Commissioned)list[i]).addWeek();
                	}
                }
            }
        }
    }
	
	protected void todayPayments(Employee list[], UndoRedo system_state)
	{
		boolean flag = false;
		int cont = 1;
		System.out.printf("\n\n       //////////////////////////////////////////////////////////////////////\n");
        System.out.printf("       ///                                                                ///\n");
        System.out.printf("       ///                        Today Payments                          ///\n");
        System.out.printf("       ///                                                                ///\n");
        System.out.printf("       //////////////////////////////////////////////////////////////////////\n\n\n");
        
        for(int i = 0;i<1000;i++)
        {
        	if(list[i] != null)
        	{
        		
        		if(list[i] instanceof Hourly)
        			list[i].receivePayment(day_of_week, system_state, cont, i);
        		else
        			list[i].receivePayment(day, system_state, cont, i);
        		
        		flag = true;
        		cont++;
        	}
        }
        
        if(!flag)
        {
            System.out.printf("       ///                     No Payments Today!!!                       \n");
        }
        System.out.printf("       ///                                                                \n");
        System.out.printf("       ///        Press any key to continue...                            \n");
        System.out.printf("       ///                                                                \n");
        System.out.printf("       ///________________________________________________________________\n\n");
        input.nextLine();
        consoleClear();
	}
	
	////////////////////////////////////////////////////////

}