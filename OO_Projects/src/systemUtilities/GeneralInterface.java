package systemUtilities;

import paymentSystemOO.Employee;

public interface GeneralInterface {
	public int getPayment_date();
	public void setPayment_date(int date);
	public Employee clone();
}
