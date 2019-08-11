package DesignPatterns;

public class NonWeeklyEmployee implements weeklyEmployeeStrategy{
	
	private int payment_day;// can be one day of the month
	
	@Override
	public int getPayment_date() {
		return payment_day;
	}
	
	@Override
	public void setPayment_date(int payment_day) {
		if(payment_day >= 1 && payment_day <= 28)
		{
			this.payment_day = payment_day;
		}
	}
	
}
