package paymentSystemOO;

import systemUtilities.GeneralInterface;
import systemUtilities.Schedule;
import systemUtilities.UndoRedo;

public class Commissioned extends Employee implements GeneralInterface{
	
	private double sellings;
	private int payment_week;
	private int two_week;
	private int days_worked;
	
	public Commissioned(int iD, String name, String address, double salary, boolean syndicate, int syndicate_id, double syndicate_tax, double service_taxes, boolean received_tax, boolean card_submit, int payment_method, String arrival_time, double sell, int payment, int two, int days_w)
	{
		super(iD, name, address, salary, syndicate, syndicate_id, syndicate_tax, service_taxes, received_tax, card_submit, payment_method, arrival_time);
		this.sellings = sell;
		this.payment_week = payment;
		this.two_week = two;
		this.days_worked = days_w;
		
	}
	
	public Commissioned()
	{
		this(-1, null, null, 0, false, -1, 0, 0, false, false, -1, null, 0, 4, 0, 0);
	}
	
	public int getPayment_date() {
		return payment_week;
	}
	public void setPayment_date(int payment_week) {
		this.payment_week = payment_week;
	}
	public int getDays_worked() {
		return days_worked;
	}
	public void setDays_worked(int days_worked) {
		this.days_worked = days_worked;
	}
	public void addDay()
	{
		this.days_worked++;
	}
	public void addSelling(double selling_result)
	{
		this.sellings += selling_result;
	}
	public double getSellings() {
		return sellings;
	}
	public void setSellings(double sellings) {
		this.sellings = sellings;
	}
	public void addWeek()
	{
		this.two_week++;
	}
	public int getTwo_week() {
		return two_week;
	}
	public void setTwo_week(int two_week) {
		this.two_week = two_week;
	}
	
	public Employee copy()
	{
		Employee new_copy = new Commissioned(super.getID(), super.getName(), super.getAddress(), super.getSalary(), super.isSyndicate(), super.getSyndicate_id(), super.getSyndicate_tax(), super.getService_taxes(), super.isReceived_tax(), super.isCard_submit(), super.getPayment_method(), super.getArrival_time(), this.sellings, this.payment_week, this.two_week, this.days_worked);
		return new_copy;
	}
	
	public String gatherData()
	{
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
		return super.toString() + "\n  Sellings: " + this.sellings + payment_date + "\n  Days worked: " + this.days_worked + "\n\n\n";
	}

	@Override
	public String toString() {
		return gatherData();
	}

	@Override
	public void receivePayment(int day, UndoRedo system_state, int index, int ID) {
		
        if(this.getTwo_week() >= 2)
        {
        	double salaryresult;
    		double result = this.getSalary() * this.getDays_worked();
            double commissioned_tax = (ID/10000) + 0.2;
            if(this.isSyndicate() && !this.isReceived_tax())
            {
            	salaryresult =  result + (this.getSellings() * commissioned_tax) - this.getSyndicate_tax() - this.getService_taxes();
            	if(salaryresult < 0)
            	{
            		salaryresult = 0;
            	}
                System.out.printf("       /// %d - ID: %d   Name: %s   Type: Commissioned   Salary ( - Syndicate Taxes + Commissions): %.2f   Syndicate: Yes   Syndicate ID: %d  Payment Method: ", index, ID, this.getName() , salaryresult, this.getSyndicate_id());
                this.setReceived_tax(true);
                this.setService_taxes(0);
            }
            else
            {
                System.out.printf("       /// %d - ID: %d   Name: %s   Type: Commissioned   Salary ( + Commissions): %.2f   Syndicate: No  Payment Method: ", index, ID, this.getName(), result + (this.getSellings() * commissioned_tax));
            }
            
            switch(this.getPayment_method())
            {
                case 1: System.out.printf("Postal Check\n");break;
                case 2: System.out.printf("Check in Hands\n");break;
                case 3: System.out.printf("Bank Deposit\n");break;
            }
            this.setSellings(0);
            this.setTwo_week(0);
            this.setDays_worked(0);
            system_state.setChange_made(true);
        }
	}

	public void myDetails() {
		System.out.printf("\n\n ID: %d\n Name: %s\n Address: %s\n Type:", this.getID(), this.getName(), this.getAddress());
		System.out.println(" Commissioned");
        System.out.printf(" Salary per day worked: %.2f\n Salary: %.2f\n Selling Results: %.2f\n Payment: ", this.getSalary(), (this.getSalary() * this.getDays_worked()), this.getSellings());
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
	    	System.out.printf(" Belong to Syndicate\n Syndicate ID: %d\n Syndicate Tax: %.2f\n Syndicate Service Tax: %.2f\n\n ", this.getSyndicate_id(), this.getSyndicate_tax(), this.getService_taxes());
	    }
	    else
	    {
	    	System.out.printf(" Not in Syndicate\n\n");
	    }
	    System.out.printf("\n\n  Arrival Time: %s\n\n", this.getArrival_time());
	}
	
	@Override
	public void applySchedule(Schedule target, ExtraFunctions extra_func)
	{
		if(target.getSchedule_type() == 1)
		{
			this.setTwo_week(0);
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
