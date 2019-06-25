package paymentSystemOO;

public class Hourly extends Employee implements GeneralInterface{
	
	private double extra_salary;
	private int payment_week;
	private int total_hours;
	
	public Hourly(int iD, String name, String address, double salary, boolean syndicate, int payment_method, String arrival_time, double extra, int payment, int hour)
	{
		super(iD, name, address, salary, syndicate, payment_method, arrival_time);
		this.extra_salary = extra;
		this.payment_week = payment;
		this.total_hours = hour;
	}
	
	public Hourly()
	{
		super(0, null, null, 0, false, 0, null);
		this.extra_salary = 0;
		this.payment_week = 4;
		this.total_hours = 0;
	}
	
	public void setExtra_salary(double extra_hour)
	{
		this.extra_salary = extra_hour;
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
		this.extra_salary += extra;
	}
	public void addHours(int hours)
	{
		this.total_hours += hours;
	}
	public void setTotal_hours(int hours)
	{
		this.total_hours = hours;
	}
	
}
