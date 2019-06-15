package paymentSystemOO;

public class Commissioned extends Employee{
	
	
	private double sellings;
	private int payment_week;
	private int two_week;
	private int days_worked;
	
	public int getPayment_week() {
		return payment_week;
	}
	public void setPayment_week(int payment_week) {
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
	
}
