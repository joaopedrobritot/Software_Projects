package systemUtilities;

public abstract class Syndicate {

	private int syndicate_id;
	private double syndicate_tax;
	private double service_taxes;
	private boolean received_tax;
	
	public int getSyndicate_id() {
		return syndicate_id;
	}
	public void setSyndicate_id(int syndicate_id) {
		if(syndicate_id >= 0)
			this.syndicate_id = syndicate_id;
		else
			this.syndicate_id = -1;
	}
	public double getSyndicate_tax() {
		return syndicate_tax;
	}
	public void setSyndicate_tax(double syndicate_tax) {
		if(syndicate_tax >= 0)
			this.syndicate_tax = syndicate_tax;
		else
			this.syndicate_tax = 0;
	}
	public double getService_taxes() {
		return service_taxes;
	}
	public void setService_taxes(double service_taxes) {
		if(service_taxes >= 0)
			this.service_taxes = service_taxes;
	}
	public void addService_taxes(double service_taxes) {
		if(service_taxes >= 0)
			this.service_taxes += service_taxes;
	}
	public boolean isReceived_tax() {
		return received_tax;
	}
	public void setReceived_tax(boolean received_tax) {
		this.received_tax = received_tax;
	}
	
}
