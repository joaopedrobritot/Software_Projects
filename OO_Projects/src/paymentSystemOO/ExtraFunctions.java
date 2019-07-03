package paymentSystemOO;

import java.util.Scanner;

import systemUtilities.UndoRedo;

import java.util.InputMismatchException;

public class ExtraFunctions {
	
	private static Scanner input = new Scanner(System.in);
	
	// Date Settings

		private int day = 26;// starts in 26th June
		private int month = 6; //
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
		double result;
		int i;
		while(true)
		{
			try {
				System.out.printf("\n\n  Insert the ID from the Employee: ");
				i = input.nextInt();
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
		if(list[i] != null)
		{
			System.out.printf("\n\n Name: %s\n Address: %s\n Type:", list[i].getName(), list[i].getAddress());
			if(list[i] instanceof Hourly)
			{
				System.out.println(" Hourly");
	                System.out.printf(" Salary per hour worked: 30,00\n Salary (No Extra): %.2f\n Extra Salary: %.2f\n Payment: ", list[i].getSalary(), ( (Hourly) list[i]).getExtra_salary());
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
		    System.out.printf("\n\n  Arrival Time: %s", list[i].getArrival_time());
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
		            System.out.printf(" Salary per hour worked: 30,00\n Salary (No Extra): %.2f\n Extra Salary: %.2f\n Payment: ", list[i].getSalary(), ( (Hourly) list[i]).getExtra_salary());
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
		System.out.printf("\n\n\n  End!\n\n\n  Press enter to return to Functions...\n\n\n");
	    input.nextLine();
	}
	
	public int dayUtil(int mth)
    {
        int result = this.last_day_month[mth];
        int week = this.day_of_week;
        int d = this.day;
        for(int i = d;i <= this.last_day_month[mth];i++)
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
        this.day++;
        this.day_of_week++;
        if(this.day_of_week == 7)// day of week
        {
            this.day_of_week = 0;
        }
        if(this.day == this.last_day_month[month] + 1)
        {
            this.day = 1;
            this.month++;
            if(this.month == 13)
            {
                this.month = 1;
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
                    if(list[i] instanceof Salaried)
                    {
                    	if(( (Salaried)list[i]).isSalaried_default())
                    	{
                    		( (Salaried)list[i]).setPayment_date(dayUtil(this.month)); // refresh salaried default day of payment for each month
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
                if(list[i] instanceof Commissioned)
                {
                	if(( (Commissioned)list[i]).getPayment_date() == this.day_of_week)
                	{
                		( (Commissioned)list[i]).addWeek();
                	}
                }
            }
        }
    }
	
	protected void todayPayments(Employee list[], UndoRedo system_state)
	{
		double result;
		double salaryresult;
		int flag = 0;
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
        		{
        			if(day_of_week == ( (Hourly)list[i]).getPayment_date())// pagos dia de sexta por default
                    {
                        if(list[i].isSyndicate() && !list[i].isReceived_tax())
                        {
                        	salaryresult =  (list[i].getSalary() + ( (Hourly)list[i]).getExtra_salary() - list[i].getSyndicate_tax() - list[i].getService_taxes());
                            if(salaryresult < 0)
                            {
                            	salaryresult = 0;
                            }
                        	System.out.printf("       /// %d - ID: %d   Name: %s   Type: Hourly   Salary ( - Syndicate Taxes + Extra Salary): %.2f   Syndicate: Yes   Syndicate ID: %d  Payment Method: ", cont, i, list[i].getName() , salaryresult, list[i].getSyndicate_id());
                            list[i].setReceived_tax(true);
                            list[i].setService_taxes(0);
                        }
                        else
                        {
                            System.out.printf("       /// %d - ID: %d   Name: %s   Type: Hourly   Salary ( + Extra Salary): %.2f   Syndicate: No  Payment Method: ", cont, i, list[i].getName(), list[i].getSalary() + ( (Hourly)list[i]).getExtra_salary());
                        }
                        
                        switch(list[i].getPayment_method())
                        {
                            case 1: System.out.printf("Postal Check\n");break;
                            case 2: System.out.printf("Check in Hands\n");break;
                            case 3: System.out.printf("Bank Deposit\n");break;
                        }
                        ( (Hourly)list[i]).setExtra_salary(0);
                        cont++;
                        flag = 1;
                        list[i].setSalary(0);
                        system_state.setChange_made(true);
                    }
        		}
        		else if(list[i] instanceof Salaried)
        		{
        			result = list[i].getSalary() * ( (Salaried)list[i]).getDays_worked();
                    if(( (Salaried)list[i]).isSalaried_default())// receives salary in the last business day
                    {
                        if(day == dayUtil(month))
                        {
                        	salaryresult = (result - list[i].getSyndicate_tax() - list[i].getService_taxes());
                        	if(salaryresult < 0)
                        	{
                        		salaryresult = 0;
                        	}
                            if(list[i].isSyndicate() && !list[i].isReceived_tax())
                            {
                                System.out.printf("       /// %d - ID: %d   Name: %s   Type: Salaried   Salary ( - Syndicate Taxes): %.2f   Syndicate: Yes   Syndicate ID: %d  Payment Method: ", cont, i, list[i].getName(), salaryresult, list[i].getSyndicate_id());
                                list[i].setReceived_tax(true);
                                list[i].setService_taxes(0);
                            }
                            else
                            {
                                System.out.printf("       /// %d - ID: %d   Name: %s   Type: Salaried   Salary: %.2f   Syndicate: No  Payment Method: ", cont, i, list[i].getName(), result);
                            }
                            
                            switch(list[i].getPayment_method())
                            {
                                case 1: System.out.printf("Postal Check\n");break;
                                case 2: System.out.printf("Check in Hands\n");break;
                                case 3: System.out.printf("Bank Deposit\n");break;
                            }
                            cont++;
                            flag = 1;
                            ( (Salaried)list[i]).setDays_worked(0);
                            system_state.setChange_made(true);
                        }
                    }
                    else // receives in the day he choose in the payment agenda
                    {
                        if(day == ( (Salaried)list[i]).getPayment_date())
                        {
                        	if(list[i].isSyndicate() && !list[i].isReceived_tax())
                            {
                        		salaryresult = (result - list[i].getSyndicate_tax() - list[i].getService_taxes());
                            	if(salaryresult < 0)
                            	{
                            		salaryresult = 0;
                            	}
                                System.out.printf("       /// %d - ID: %d   Name: %s   Type: Salaried   Salary ( - Syndicate Taxes): %.2f   Syndicate: Yes   Syndicate ID: %d  Payment Method: ", cont, i, list[i].getName(), salaryresult, list[i].getSyndicate_id());
                                list[i].setReceived_tax(true);
                                list[i].setService_taxes(0);
                            }
                            else
                            {
                                System.out.printf("       /// %d - ID: %d   Name: %s   Type: Salaried   Salary: %.2f   Syndicate: No  Payment Method: ", cont, i, list[i].getName(), result);
                            }
                            
                            switch(list[i].getPayment_method())
                            {
                                case 1: System.out.printf("Postal Check\n");break;
                                case 2: System.out.printf("Check in Hands\n");break;
                                case 3: System.out.printf("Bank Deposit\n");break;
                            }
                            cont++;
                            flag = 1;
                            ( (Salaried)list[i]).setDays_worked(0);
                            system_state.setChange_made(true);
                        }
                    }
                    
        		}
        		else if(list[i] instanceof Commissioned)
        		{
        			result = list[i].getSalary() * ( (Commissioned)list[i]).getDays_worked();
                    if(( (Commissioned)list[i]).getTwo_week() >= 2)
                    {
                        double commissioned_tax = (i/10000) + 0.2;
                        if(list[i].isSyndicate() && !list[i].isReceived_tax())
                        {
                        	salaryresult =  result + ((( Commissioned)list[i]).getSellings() * commissioned_tax) - list[i].getSyndicate_tax() - list[i].getService_taxes();
                        	if(salaryresult < 0)
                        	{
                        		salaryresult = 0;
                        	}
                            System.out.printf("       /// %d - ID: %d   Name: %s   Type: Commissioned   Salary ( - Syndicate Taxes + Commissions): %.2f   Syndicate: Yes   Syndicate ID: %d  Payment Method: ", cont, i, list[i].getName() , salaryresult, list[i].getSyndicate_id());
                            list[i].setReceived_tax(true);
                            list[i].setService_taxes(0);
                        }
                        else
                        {
                            System.out.printf("       /// %d - ID: %d   Name: %s   Type: Commissioned   Salary ( + Commissions): %.2f   Syndicate: No  Payment Method: ", cont, i, list[i].getName(), result + (( (Commissioned)list[i]).getSellings() * commissioned_tax));
                        }
                        
                        switch(list[i].getPayment_method())
                        {
                            case 1: System.out.printf("Postal Check\n");break;
                            case 2: System.out.printf("Check in Hands\n");break;
                            case 3: System.out.printf("Bank Deposit\n");break;
                        }
                        ( (Commissioned)list[i]).setSellings(0);
                        cont++;
                        ( (Commissioned)list[i]).setTwo_week(0);
                        flag = 1;
                        ( (Commissioned)list[i]).setDays_worked(0);
                        system_state.setChange_made(true);
                    }
        		}
        	}
        }
        
        if(flag == 0)
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