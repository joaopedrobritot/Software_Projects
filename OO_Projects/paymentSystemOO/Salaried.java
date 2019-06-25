package paymentSystemOO;

public class Salaried extends Employee implements GeneralInterface{
	
	private int payment_day;
	private boolean salaried_default;
	private int days_worked;
	
	public Salaried(int iD, String name, String address, double salary, boolean syndicate, int payment_method, String arrival_time, int payment, boolean salaried, int days_w)
	{
		super(iD, name, address, salary, syndicate, payment_method, arrival_time);
		this.payment_day = payment;
		this.salaried_default = salaried;
		this.days_worked = days_w;
		
	}
	
	public Salaried()
	{
		super(0, null, null, 0, false, 0, null);
		this.payment_day = 0;
		this.salaried_default = true;
		this.days_worked = 0;
	}
	
	public int getPayment_date() {
		return payment_day;
	}
	public void setPayment_date(int payment_day) {
		this.payment_day = payment_day;
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
		this.days_worked = days_worked;
	}
	
}
