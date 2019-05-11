import java.util.Scanner;

public class Employee {
	
	String name;
	String address;
	int type;
	double salary;
	double hours;
	int days_worked;
	int ID;
	
	public void addEmployee(int id) {
		
		Scanner input = new Scanner(System.in);
		System.out.printf("\n Insert your name followed by a enter: ");
		this.name = input.nextLine();
		System.out.printf("\n\n Insert you address followed by a enter: ");
		this.address = input.nextLine();
		System.out.printf("\n\n Insert the type:\n hourly - 0\n salaried - 1\n commissioned - 2\n\n Type: ");
		this.type = input.nextInt();
		System.out.printf("\n\n Insert your salary: ");
		this.salary = input.nextDouble();
		this.ID = id;
		//////printando
		System.out.printf("\n\n Employee ID: %d\n Name: %s\n Address: %s\n Type: ", this.ID, this.name, this.address);
		switch(this.type) 
		{
			case 0:
				System.out.println("Hourly");
				break;
			case 1:
				System.out.println("Salaried");
				break;
			case 2:
				System.out.println("Commissioned");
				break;
		}
		System.out.printf(" Salary: %.2f\n",this.salary);
	}
	
	public int delEmployee(Employee list[], int id) {
		int i;
		int flag = 0;
		if(list[id].ID != -1)
		{
			list[id] = new Employee();
			list[id].ID = -1;
			flag = 1;
			System.out.println("\n\n Employee removed with sucess!\n\n\n Save your ID number!!!!");
			return 1;
		}
		else
		{
			System.out.println("\n\n Employee not found!\n");
			return 0;
		}
	}
	
	public void timeCard(Employee list[], int id) {
		
		double hours;
		Scanner input = new Scanner(System.in);
		if(list[id].ID != -1)
		{
			System.out.printf("\n\n Insert the hours you've worked today: ");
			hours = input.nextDouble();
			list[id].hours += hours;
			hours = 0;
			list[id].days_worked++;
			System.out.println("\n\n Today's check is complete!!\n\n");
		}
		else
		{
			System.out.println("\n\n Employee not found");
		}
	}
	
	public void showDetails() {
		
		if(this.ID != -1)
		{
			System.out.printf("\n\n Employee ID: %d"
					+ "\n Name: %s"
					+ "\n Address: %s"
					+ "\n Type number: %d"
					+ "\n Salary: %.2f"
					+ "\n Hours worked: %.2f"
					+ "\n Days Worked: %d\n\n", this.ID, this.name, this.address, this.type, this.salary, this.hours, this.days_worked);
		}
	}
}
