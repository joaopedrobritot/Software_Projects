package DesignPatterns;

public interface weeklyEmployeeStrategy {
	
	int getPayment_date();
	void setPayment_date(int payment_date);
	// receives payment based in weekly days // or day of the month
	// this prevents you from having to change all new classes (implement getpayment, setpayment etc...)
	// just for weekly employees, this code has 2 of this type but if we want more of this same type of weekly employees?
	// WeeklyEmployeeStrategy!!
}
