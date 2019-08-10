package systemUtilities;

import paymentSystemOO.ExtraFunctions;

public interface GeneralInterface {
	public int getPayment_date();
	public void setPayment_date(int date);
	public String gatherData();
	public void applySchedule(Schedule target, ExtraFunctions extra_func);
}
