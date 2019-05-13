import java.util.Scanner;
import java.util.Calendar;
public class Head {


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int employees[] = new int[1000];
        Employee list[] = new Employee[1000];//acredito que seja um array da classe employee
        int i;
        int flag = 0;
        int id;
        int employess_num = 0;
        for(i = 0;i<1000;i++)// inicializa a lista de funcionários
        {
            employees[i] = -1;
            list[i] = new Employee();
            list[i].ID = -1;
            list[i].hours = 0;
            list[i].days_worked = 0;
        }

        while(1 == 1)
        {
            System.out.printf("\n Insert the operation you want followed by a enter: \n\n 1 : Add - adds a new employee\n\n 2 : Del - Removes a employee\n\n 3 : TCard - Launch a Time Card\n\n 4 : SResult - Launch Selling Result\n\n 5 : Tax - Launch a tax of service\n\n 6 : EChange - Change employee details\n\n 7 : TPayment - Today Payments\n\n 8 : UR - Undo / Redo\n\n 9 : Payment Schedule\n\n 10 : Exit");
            System.out.printf("\n Operation: ");
            int option = input.nextInt();
            System.out.println("\n\n ");

            switch(option)
            {

                case 1:
                    for(i = 0;i<1000;i++)
                    {
                        if(employees[i] == -1)//procura uma posicao vaga//a posicao tambem eh o id
                        {
                            list[i].addEmployee(i);
                            employees[i] = 1;
                            flag++;
                            System.out.println("\n\n  Employee added in the system!\n\n");
                            break;
                        }
                    }
                    if(flag > 1000)
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
                        employees[id] = -1;
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

                    break;

                case 7:
                    break;

                case 8:
                    break;

                case 9:
                    break;

                case 10:
                    System.exit(0);

                case 11:
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


}
