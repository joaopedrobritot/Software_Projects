import java.util.Scanner;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Head {

	public static void consoleClear()
	{
		for(int i = 0; i < 20;i++)
		{
			System.out.printf("\n\n");
		}
	}

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String system_password = "admin";
        System.out.println("\n Insert the System Password (default: 'admin'): ");
        String password_given = input.nextLine();

        while(true)
        {
        	if(password_given.equals(system_password))
        	{
        		Employee list[] = new Employee[9999999];//acredito que seja um array da classe employee
        		int i;
        		int flag = 0;
        		int id;
        		int employess_num = 0;
        		for(i = 0;i<9999999;i++)// inicializa a lista de funcionários
        		{
        		    list[i] = new Employee();
        		    list[i].ID = -1;
        		    list[i].hours = 0;
        		    list[i].days_worked = 0;
        		}
		
        		while(1 == 1)
        		{
        		    System.out.printf("\n Insert the operation you want followed by a enter: \n\n 1 : Add - adds a new employee\n\n 2 : Del - Removes a employee\n\n 3 : TCard - Launch a Time Card\n\n 4 : SResult - Launch Selling Result\n\n 5 : Tax - Launch a tax of service\n\n 6 : EChange - Change employee details\n\n 7 : TPayment - Today Payments\n\n 8 : UR - Undo / Redo\n\n 9 : Payment Schedule\n\n 10 : Create New Payment Schedule\n\n 11 : Exit\n\n ");
        		    System.out.printf("\n Operation: ");
        		    int option = input.nextInt();
        		    System.out.println("\n\n ");
					consoleClear();
        		    switch(option)
        		    {
		
        	    	    case 1:
        	    	        for(i = 0;i<9999999;i++)
        	    	        {
        	    	            if(list[i].ID < 0)//procura uma posicao vaga//a posicao tambem eh o id
        	    	            {
        	    	                list[i].addEmployee(i);
        	    	                flag++;
        	    	                System.out.println("\n\n  Employee added in the system!\n\n");
        	    	                break;
        	    	            }
        	    	        }
        	    	        if(flag > 9999999)
        	    	        {
        	    	            System.out.println("\n\n No vacancy!");
        	    	        }
        	    	        break;
		
        	    	    case 2:
        	    	        int q = 0;
        	    	        System.out.printf("\n\n Insert the ID from the Employee you want to remove: ");
        	    	        id = input.nextInt();
        	    	        q = list[i].delEmployee(list, id);
        	    	        if(q == 1)
        	    	        {
        	    	            flag--;
        	    	        }
        	    	        break;
		
        	    	    case 3:
        	    	        System.out.printf("\n\n Insert the your ID: ");
        	    	        id = input.nextInt();
        	    	        list[0].timeCard(list, id);
        	    	        break;
		
        	    	    case 4:
		
        	    	        break;
		
        	    	    case 5:
        	    	        break;
		
        	    	    case 6:
							System.out.printf("\n\n Insert your ID: ");
        	    	        id = input.nextInt();
        	    	        if(id >= 0)
        	    	        {
        	    	        	list[id].changeDetails(list, id);
        	    	        }
        	    	        else
        	    	        {
        	    	        	System.out.println("\n\n\n\n    Invalid ID!!\n\n");
        	    	        }
        	    	        break;
		
        	    	    case 7:
        	    	        break;
		
        	    	    case 8:
        	    	        break;
		
        	    	    case 9:
        	    	        break;
		
        	    	    case 10:
        	    	        break;
		
        	    	    case 11:
        	    	    	System.exit(0);
	
        	    	    case 12:
        	    	    	System.out.printf("\n\n Insert your ID: ");
        	    	        id = input.nextInt();
        	    	        list[id].showDetails();
        	    	        break;
        	    	    
        	    	    default:
        	    	        System.out.println("\n\n Invalid Option! - Try Again");
        	    	        break;
        	    	}
        		}
        	}
        	else
        	{
        		System.out.println("\n\n\n              Invalid Password!!\n                  Try again!!!\n\n\n\n   ");
        	}
        }     
    }
}