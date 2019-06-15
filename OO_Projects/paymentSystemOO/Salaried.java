package paymentSystemOO;

public class Salaried extends Employee{
	
	private int payment_day;
	private boolean salaried_default;
	private int days_worked;
	
	public int getPayment_day() {
		return payment_day;
	}
	public void setPayment_day(int payment_day) {
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
