package paymentSystemOO;

import systemUtilities.GeneralInterface;
import systemUtilities.Schedule;
import systemUtilities.UndoRedo;

public class Hourly extends Employee implements GeneralInterface{
	
	private double extra_salary;
	private int payment_week;
	private int total_hours;
	
	public Hourly(int iD, String name, String address, double salary, boolean syndicate, int syndicate_id, double syndicate_tax, double service_taxes, boolean received_tax, boolean card_submit, int payment_method, String arrival_time, double extra, int payment, int hour)
	{
		super(iD, name, address, salary, syndicate, syndicate_id, syndicate_tax, service_taxes, received_tax, card_submit, payment_method, arrival_time);
		this.extra_salary = extra;
		this.payment_week = payment;
		this.total_hours = hour;
	}
	
	public Hourly() // 'nullHourly'
	{
		this(-1, null, null, 0, false, -1, 0, 0, false, false, -1, null, 0, 4, 0);
	}
	
	public void setExtra_salary(double extra_hour)
	{
		if(extra_hour >= 0)
		{
			this.extra_salary = extra_hour;
		}
	}
	public double getExtra_salary() {
		return extra_salary;
	}
	public int getPayment_date() {
		return payment_week;
	}
	public void setPayment_date(int payment_week) {
		this.payment_week = payment_week;
	}
	public int getTotal_hours() {
		return total_hours;
	}
	public void addExtra(double extra)
	{
		if(extra >= 0)
		{
			this.extra_salary += extra;
		}
	}
	public void addHours(int hours)
	{
		if(hours >= 0)
		{
			this.total_hours += hours;
		}
		
	}
	public void setTotal_hours(int hours)
	{
		if(hours >= 0)
		{
			this.total_hours = hours;
		}
		
	}
	
	public String gatherData()
	{
		String extra = "\n  Type: Hourly\n  Extra Salary: " + this.extra_salary;
		String payment_date;
		switch(this.payment_week)
		{
			case 0: payment_date = "\n  Week day to be payed: Monday";
			case 1: payment_date = "\n  Week day to be payed: Tuesday";
			case 2: payment_date = "\n  Week day to be payed: Wednesday";
			case 3: payment_date = "\n  Week day to be payed: Thursday";
			case 4: payment_date = "\n  Week day to be payed: Friday";
			default: payment_date = null;
		}
		return super.toString() + extra + payment_date + "\n  Total hours worked: " + this.total_hours + "\n\n\n";
	}

	@Override
	public String toString() 
	{
		return gatherData();
	}
	
	public Employee copy()
	{
		Employee new_copy = new Hourly(super.getID(), super.getName(), super.getAddress(), super.getSalary(), super.isSyndicate(), super.getSyndicate_id(), super.getSyndicate_tax(), super.getService_taxes(), super.isReceived_tax(), super.isCard_submit(), super.getPayment_method(), super.getArrival_time(), this.extra_salary, this.payment_week, this.total_hours);
		return new_copy;
	}

	@Override
	public void receivePayment(int day_of_week, UndoRedo system_state, int index, int ID) {
		
		if(day_of_week == this.getPayment_date())// pagos dia de sexta por default
        {
			double salaryresult;
            if(this.isSyndicate() && !this.isReceived_tax())
            {
            	salaryresult =  (this.getSalary() + this.getExtra_salary() - this.getSyndicate_tax() - this.getService_taxes());
                if(salaryresult < 0)
                	salaryresult = 0;
      
            	System.out.printf("       /// %d - ID: %d   Name: %s   Type: Hourly   Salary ( - Syndicate Taxes + Extra Salary): %.2f   Syndicate: Yes   Syndicate ID: %d  Payment Method: ", index, ID, this.getName() , salaryresult, this.getSyndicate_id());
                this.setReceived_tax(true);
                this.setService_taxes(0);
            }
            else
            {
                System.out.printf("       /// %d - ID: %d   Name: %s   Type: Hourly   Salary ( + Extra Salary): %.2f   Syndicate: No  Payment Method: ", index, ID, this.getName(), this.getSalary() + this.getExtra_salary());
            }
            
            switch(this.getPayment_method())
            {
                case 1: System.out.printf("Postal Check\n");break;
                case 2: System.out.printf("Check in Hands\n");break;
                case 3: System.out.printf("Bank Deposit\n");break;
            }
            this.setExtra_salary(0);
            this.setSalary(0);
            system_state.setChange_made(true);
        }
	}

	public void myDetails() {
		System.out.printf("\n\n ID: %d\n Name: %s\n Address: %s\n Type:", this.getID(), this.getName(), this.getAddress());
		System.out.println(" Hourly");
        System.out.printf(" Salary per hour worked: 30,00\n Salary (No Extra): %.2f\n Extra Salary: %.2f\n Payment: ", this.getSalary(), this.getExtra_salary());
        switch(this.getPayment_method())
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
        if(this.isSyndicate())
        {
        	System.out.printf(" Belong to Syndicate\n Syndicate ID: %d\n Syndicate Tax: %.2f\n Syndicate Service Tax: %.2f\n Total Hours Worked: %d\n\n ", this.getSyndicate_id(), this.getSyndicate_tax(), this.getService_taxes(), this.getTotal_hours());
        	
        }
        else
        {
        	System.out.printf(" Not in Syndicate\n Total Hours Worked: %d\n\n", this.getTotal_hours());
        	
        }
        System.out.printf("\n\n  Arrival Time: %s\n\n", this.getArrival_time());
	}
	
	@Override
	public void addDay()
	{
		this.total_hours += 24;
	}
	
	@Override
	public void applySchedule(Schedule target, ExtraFunctions extra_func)
	{
		if(target.getSchedule_type() == 1)
		{
			switch(target.getSchedule_option())
	        {
	            case "Monday":
	                this.setPayment_date(0);
	                break;

	            case "Tuesday":
	            	this.setPayment_date(1);
	                break;

	            case "Wednesday":
	            	this.setPayment_date(2);
	                break;

	            case "Thursday":
	            	this.setPayment_date(3);
	                break;

	            case "Friday":
	            	this.setPayment_date(4);
	                break;
	        }
	        System.out.printf("\n\n\n\n  Change Done!!\n\n");
		}
		else
			System.out.printf("\n\n\n  This Employee is not an Hourly OR Commissioned!!\n\n  Please move to 'Change an Employee Details' on menu.\n\n\n");
		
	}
}
