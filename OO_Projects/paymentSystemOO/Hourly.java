package paymentSystemOO;

public class Hourly extends Employee{

	
	private double extra_hour;
	private int payment_week;
	private int total_hours;
	
	public double getExtra_hour() {
		return extra_hour;
	}
	public int getPayment_week() {
		return payment_week;
	}
	public void setPayment_week(int payment_week) {
		this.payment_week = payment_week;
	}
	public int getTotal_hours() {
		return total_hours;
	}
	public void addExtra(double extra)
	{
		this.extra_hour += extra;
	}
	public void addHours(int hours)
	{
		this.total_hours += hours;
	}
}
