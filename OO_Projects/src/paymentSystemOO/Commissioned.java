package paymentSystemOO;

import systemUtilities.GeneralInterface;

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
		super(-1, null, null, 0, false, -1, 0, 0, false, false, -1, null);
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
	
	public Employee clone()
	{
		Employee new_copy = new Commissioned(super.getID(), super.getName(), super.getAddress(), super.getSalary(), super.isSyndicate(), super.getSyndicate_id(), super.getSyndicate_tax(), super.getService_taxes(), super.isReceived_tax(), super.isCard_submit(), super.getPayment_method(), super.getArrival_time(), this.sellings, this.payment_week, this.two_week, this.days_worked);
		return new_copy;
	}

	@Override
	public String toString() {
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
	
}
