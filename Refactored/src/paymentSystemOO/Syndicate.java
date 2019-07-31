package paymentSystemOO;

abstract class Syndicate {

	private int syndicate_id;
	private double syndicate_tax;
	private double service_taxes;
	private boolean received_tax;
	
	public int getSyndicate_id() {
		return syndicate_id;
	}
	public void setSyndicate_id(int syndicate_id) {
		this.syndicate_id = syndicate_id;
	}
	public double getSyndicate_tax() {
		return syndicate_tax;
	}
	public void setSyndicate_tax(double syndicate_tax) {
		this.syndicate_tax = syndicate_tax;
	}
	public double getService_taxes() {
		return service_taxes;
	}
	public void setService_taxes(double service_taxes) {
		if(service_taxes >= 0 && service_taxes <= 10000) // limitation optional
			this.service_taxes = service_taxes;
		else
			System.out.printf("  Insert a correct value!!\n\n");
	}
	public void addService_taxes(double service_taxes) {
		if(service_taxes >= 0 && service_taxes <= 10000) // limitation optional
			this.service_taxes += service_taxes;
		else
			System.out.printf("  Insert a correct value!!\n\n");
	}
	public boolean isReceived_tax() {
		return received_tax;
	}
	public void setReceived_tax(boolean received_tax) {
		this.received_tax = received_tax;
	}
	
}
