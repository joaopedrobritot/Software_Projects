package paymentSystemOO;

public class Employee extends Syndicate{ // coloquei extends aqui (testar depois)
	
	private int ID;
	private String name;
	private String address;
	private double salary;
	private boolean syndicate;
	private int payment_method;
	private String arrival_time;
	
	public String getArrival_time() {
		return arrival_time;
	}
	public void setArrival_time(String arrival_time) {
		this.arrival_time = arrival_time;
	}
	public int getID() {
		return ID;
	}
	public void setID(int id) {
		this.ID = id;
	}
	public String getAddress() {
		return this.address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public void addSalary(double salary)
	{
		this.salary += salary;
	}
	public boolean isSyndicate() {
		return syndicate;
	}
	public void setSyndicate(boolean syndicate) {
		this.syndicate = syndicate;
	}
	public int getPayment_method() {
		return payment_method;
	}
	public void setPayment_method(int payment_method) {
		this.payment_method = payment_method;
	}
	
}
