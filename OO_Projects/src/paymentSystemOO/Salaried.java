package paymentSystemOO;

import systemUtilities.GeneralInterface;

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
		super(-1, null, null, 0, false, -1, 0, 0, false, false, -1, null);
		this.payment_day = 0;
		this.salaried_default = true;
		this.days_worked = 0;
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
	
	@Override
	public Employee clone()
	{
		Employee new_copy = new Salaried(super.getID(), super.getName(), super.getAddress(), super.getSalary(), super.isSyndicate(), super.getSyndicate_id(), super.getSyndicate_tax(), super.getService_taxes(), super.isReceived_tax(), super.isCard_submit(), super.getPayment_method(), super.getArrival_time(), this.payment_day, this.salaried_default, this.days_worked);
		return new_copy;
	}

	
	@Override
	public String toString() {
		return super.toString() + "\n  Type: Salaried" + "\n  Day to be payed: " + this.payment_day + "\n  Days Worked: " + this.days_worked + "\n\n\n";
	}
	
}
