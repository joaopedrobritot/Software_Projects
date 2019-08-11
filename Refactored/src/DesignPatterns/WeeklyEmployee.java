package DesignPatterns;

public class WeeklyEmployee implements weeklyEmployeeStrategy{

	private int payment_week;// can be one day of the week
	
	@Override
	public int getPayment_date() {
		
		return payment_week;
	}

	@Override
	public void setPayment_date(int payment_week) {
		if(payment_week < 5 && payment_week >= 0)
			this.payment_week = payment_week;
		
	}

	
	
}
