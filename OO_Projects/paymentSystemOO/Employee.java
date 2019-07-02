package paymentSystemOO;

public class Employee extends Syndicate{
	
	private int ID;
	private String name;
	private String address;
	private double salary;
	private boolean syndicate;
	private int payment_method;
	private String arrival_time;

	public Employee(int iD, String name, String address, double salary, boolean syndicate, int payment_method, String arrival_time) 
	{
		super();
		ID = iD;
		this.name = name;
		this.address = address;
		if(salary < 0)
		{
			this.salary = 0;
		}
		else
		{
			this.salary = salary;
		}
		
		this.syndicate = syndicate;
		this.payment_method = payment_method;
		this.arrival_time = arrival_time;
	}
	
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
		if(salary < 0)
		{
			System.out.printf("\n\n\n  Negative numbers are not allowed!!\n\n  Setting to default: 0\n\n\n");
			this.salary = 0;
		}
		else
		{
			this.salary = salary;
		}
	}
	public void addSalary(double salary)
	{
		if(salary >= 0)
		{
			this.salary += salary;
		}
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

	
	@Override
	public String toString() {
		String normal_info = "\n  ID: " + this.ID + "\n  Name: " + this.name + "\n  Address: " + this.address + "\n  Salary: " + this.salary;
		String payment_info = null;
		switch(this.payment_method)
		{
			case 1: payment_info = "\n  Payment method: Postal Check";
			case 2: payment_info = "\n  Payment method: Receive Check in Hands";
			case 3: payment_info = "\n  Payment method: Bank Deposit";
		}
		String syndicate_info = "\n  Does not belong to Syndicate!";
		if(this.syndicate)
		{
			syndicate_info = "  Syndicate ID: " + this.getSyndicate_id() + "\n  Syndicate tax: " + this.getSyndicate_tax() + "Service taxes: " + this.getService_taxes();
		}
		return normal_info + payment_info + syndicate_info;
	}
	
	
}
