package paymentSystemOO;

import systemUtilities.Schedule;
import systemUtilities.Syndicate;
import DesignPatterns.Prototype;
import DesignPatterns.UndoRedoSingleton;

public abstract class Employee extends Syndicate{
	
	private int ID;
	private String name;
	private String address;
	private double salary;
	private boolean syndicate;
	private boolean card_submit;
	private int payment_method;
	private String arrival_time;

	public Employee(int iD, String name, String address, double salary, boolean syndicate, int syndicate_id, double syndicate_tax, double service_taxes, boolean received_tax, boolean card_submit, int payment_method, String arrival_time) 
	{
		super();
		this.ID = iD;
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
		if(this.syndicate)
		{
			this.setSyndicate_id(syndicate_id);
			this.setSyndicate_tax(syndicate_tax);
			this.setService_taxes(service_taxes);
			this.setReceived_tax(received_tax);
		}
		this.card_submit = card_submit;
		this.card_submit = false;
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
	public boolean isCard_submit() {
		return card_submit;
	}
	public void setCard_submit(boolean card_submit) {
		this.card_submit = card_submit;
	}
	
	private String gatherData()
	{
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

	public abstract void myDetails();
	public abstract boolean receivePayment(int date, UndoRedoSingleton system_state, int index, int ID);
	public abstract void addDay();
	public abstract Prototype makeCopy();
	
	@Override
	public String toString() {
		return gatherData();
	}
	
	public void applySchedule(Schedule target, ExtraFunctions extra_func)
	{
		System.out.printf("\n\n  This employee is not instantieted!!\n\n");
	}
}
