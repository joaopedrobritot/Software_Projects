package paymentSystemOO;

import systemUtilities.GeneralInterface;
import systemUtilities.Schedule;
import systemUtilities.UndoRedo;

public class Salaried extends Employee implements GeneralInterface{
	
	private int payment_day;
	private boolean salaried_default;
	private int days_worked;
	
	public Salaried(int iD, String name, String address, double salary, boolean syndicate, int syndicate_id, double syndicate_tax, double service_taxes, boolean received_tax, boolean card_submit, int payment_method, String arrival_time, int payment, boolean salaried, int days_w)
	{
		super(iD, name, address, salary, syndicate, syndicate_id, syndicate_tax, service_taxes, received_tax, card_submit, payment_method, arrival_time);
		this.payment_day = payment;
		this.salaried_default = salaried;
		this.days_worked = days_w;
		
	}
	
	public Salaried()
	{
		this(-1, null, null, 0, false, -1, 0, 0, false, false, -1, null, 0, true, 0);
	}
	
	public int getPayment_date() {
		return payment_day;
	}
	public void setPayment_date(int payment_day) {
		if(payment_day < 0)
		{
			this.payment_day = 0;
		}
		else
		{
			this.payment_day = payment_day;
		}
	}
	public boolean isSalaried_default() {
		return salaried_default;
	}
	public void setSalaried_default(boolean salaried_default) {
		this.salaried_default = salaried_default;
	}
	public void addDay()
	{
		this.days_worked++;
	}
	public int getDays_worked() {
		return days_worked;
	}
	public void setDays_worked(int days_worked) {
		if(days_worked < 0)
		{
			this.days_worked = 0;
		}
		else
		{
			this.days_worked = days_worked;
		}
	}
	
	public String gatherData()
	{
		return super.toString() + "\n  Type: Salaried" + "\n  Day to be payed: " + this.payment_day + "\n  Days Worked: " + this.days_worked + "\n\n\n";
	}
	
	public Employee copy()
	{
		Employee new_copy = new Salaried(super.getID(), super.getName(), super.getAddress(), super.getSalary(), super.isSyndicate(), super.getSyndicate_id(), super.getSyndicate_tax(), super.getService_taxes(), super.isReceived_tax(), super.isCard_submit(), super.getPayment_method(), super.getArrival_time(), this.payment_day, this.salaried_default, this.days_worked);
		return new_copy;
	}

	
	@Override
	public String toString() {
		return gatherData();
	}

	@Override
	public void receivePayment(int day, UndoRedo system_state, int index, int ID) {
		
        if(day == this.getPayment_date())
        {
        	double salaryresult;
    		double result = this.getSalary() * this.getDays_worked();
        	if(this.isSyndicate() && !this.isReceived_tax())
            {
        		salaryresult = (result - this.getSyndicate_tax() - this.getService_taxes());
            	if(salaryresult < 0)
            	{
            		salaryresult = 0;
            	}
                System.out.printf("       /// %d - ID: %d   Name: %s   Type: Salaried   Salary ( - Syndicate Taxes): %.2f   Syndicate: Yes   Syndicate ID: %d  Payment Method: ", index, ID, this.getName(), salaryresult, this.getSyndicate_id());
                this.setReceived_tax(true);
                this.setService_taxes(0);
            }
            else
            {
                System.out.printf("       /// %d - ID: %d   Name: %s   Type: Salaried   Salary: %.2f   Syndicate: No  Payment Method: ", index, ID, this.getName(), result);
            }
            
            switch(this.getPayment_method())
            {
                case 1: System.out.printf("Postal Check\n");break;
                case 2: System.out.printf("Check in Hands\n");break;
                case 3: System.out.printf("Bank Deposit\n");break;
            }
            this.setDays_worked(0);
            system_state.setChange_made(true);
        }
	}

	public void myDetails() {
		System.out.printf("\n\n ID: %d\n Name: %s\n Address: %s\n Type:", this.getID(), this.getName(), this.getAddress());
        System.out.println(" Salaried");
        System.out.printf(" Salary per day worked: %.2f\n Salary: %.2f\n Payment: ", this.getSalary(), (this.getSalary() * this.getDays_worked()));
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
	
	public void applySchedule(Schedule target, ExtraFunctions extra_func)
	{
		if(target.getSchedule_type() == 2)
		{
			if(target.getSchedule_option().equalsIgnoreCase("last"))
	        {
	            this.setPayment_date(extra_func.dayUtil(extra_func.getMonth()));
	            this.setSalaried_default(true);
	        }
	        else
	        {
	        	this.setSalaried_default(false);
	        	int aux = Integer.parseInt(target.getSchedule_option());
	        	this.setPayment_date(aux);
	            
	        }
	        System.out.printf("\n\n\n  Change Done!!");
		}
		else
			System.out.printf("\n\n\n  This Employee is not an Salaried!!\n\n  Please move to 'Change an Employee Details' on menu.\n\n\n");
		
	}
}
