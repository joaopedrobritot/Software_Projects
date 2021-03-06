package paymentSystemOO;

import systemUtilities.GeneralInterface;

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
	
	public Hourly()
	{
		super(-1, null, null, 0, false, -1, 0, 0, false, false, -1, null);
		this.extra_salary = 0;
		this.payment_week = 4;
		this.total_hours = 0;
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

	@Override
	public String toString() 
	{
		String result;
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
		result = super.toString() + extra + payment_date + "\n  Total hours worked: " + this.total_hours + "\n\n\n";
		return result;
	}
	
	public Employee clone()
	{
		Employee new_copy = new Hourly(super.getID(), super.getName(), super.getAddress(), super.getSalary(), super.isSyndicate(), super.getSyndicate_id(), super.getSyndicate_tax(), super.getService_taxes(), super.isReceived_tax(), super.isCard_submit(), super.getPayment_method(), super.getArrival_time(), this.extra_salary, this.payment_week, this.total_hours);
		return new_copy;
	}
}
