package paymentSystemOO;

public class Commissioned extends Employee implements GeneralInterface{
	
	private double sellings;
	private int payment_week;
	private int two_week;
	private int days_worked;
	
	public Commissioned(int iD, String name, String address, double salary, boolean syndicate, int payment_method, String arrival_time, double sell, int payment, int two, int days_w)
	{
		super(iD, name, address, salary, syndicate, payment_method, arrival_time);
		this.sellings = sell;
		this.payment_week = payment;
		this.two_week = two;
		this.days_worked = days_w;
		
	}
	
	public Commissioned()
	{
		super(-1, null, null, 0, false, 0, null);
		this.sellings = 0;
		this.payment_week = 4;
		this.two_week = 0;
		this.days_worked = 0;
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
	
}
