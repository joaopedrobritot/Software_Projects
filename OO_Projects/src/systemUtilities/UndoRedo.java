package systemUtilities;

import java.util.InputMismatchException;
import java.util.Scanner;

import paymentSystemOO.Commissioned;
import paymentSystemOO.Employee;
import paymentSystemOO.ExtraFunctions;
import paymentSystemOO.Hourly;
import paymentSystemOO.Salaried;

public class UndoRedo {
	
	private static Scanner input = new Scanner(System.in);
	private Employee state_list[][] = new Employee[51][1000];
	private int state_index = 0;
	private int states_size = 0;
	protected boolean change;
	
	public boolean isChange_made()
	{
		return change;
	}
	
	public void setChange_made(boolean ch)
	{
		change = ch;
	}
	
	public void saveState(Employee list[])
	{
		int i,j;
		if(states_size < 50)
		{
			state_index++;
			states_size = state_index; // previne redos indesejados
			for(i = 0;i<1000;i++)
			{
				if(list[i] != null)
				{
					if(list[i] instanceof Hourly)
					{
						state_list[state_index][i] = ( (Hourly)list[i]).clone();
					}
					else if( list[i] instanceof Salaried)
					{
						state_list[state_index][i] = ( (Salaried)list[i]).clone();
					}
					else if(list[i] instanceof Commissioned)
					{
						state_list[state_index][i] = ( (Commissioned)list[i]).clone();
					}
				}
				
			}
			
			for(i = states_size + 1;i<51;i++) // cleaning unwanted states (i think this save some memory :p )
			{
				for(j = 0;j<1000;j++)
				{
					state_list[i][j] = null;
				}
			}
		}
		else
		{
			System.out.printf("\n\n\n  Can't add anymore state (undoRedo capacity full!!)\n  Reseting the UndoRedo Configurations...\n\n");
            states_size = 0;
            state_index = 0;
            System.out.printf(" Press enter to continue...\n\n\n");
            input.nextLine();
		}
	}
	
	public Employee[] applyState(Employee list[])
	{
		for(int i = 0;i<1000;i++)
		{
			list[i] = state_list[state_index][i];
		}
		return list;
	}
	
	public Employee[] undoRedo(Employee list[])
	{
		int choice;
		while(true)
		{
			while(true)
			{
				try {
					ExtraFunctions.consoleClear();
				    System.out.printf("\n       ////////////////////////////////////\n");
					System.out.printf("       ///                              ///\n");
					System.out.printf("       ///          Undo / Redo         ///\n");
					System.out.printf("       ///                              ///\n");
					System.out.printf("       /// 1 - Undo                     ///\n");
					System.out.printf("       /// 2 - Redo                     ///\n");
					System.out.printf("       /// 3 - Cancel                   ///\n");
					System.out.printf("       ///                              ///\n");
					System.out.printf("       ////////////////////////////////////\n\n\n       Option: ");
					choice = input.nextInt();
					input.nextLine();
					break;
				}
				catch(InputMismatchException e)
				{
					input.nextLine();
					System.out.printf("\n\n\n  the option is not an integer!!\n\n  Press enter to try again...\n\n\n");
					input.nextLine();
				}
			}
			if(choice == 1)
			{
				if(state_index > 0)
	            {
					ExtraFunctions.consoleClear();
	                state_index--;
	                list = applyState(list);
	                System.out.printf("\n\n\n\n    Settings UN-done!!\n\n    Press enter to return to Undo / Redo Functions..\n\n\n\n");
	                input.nextLine();
	            }
	            else
	            {
	            	System.out.printf("\n\n\n\n    Undo Limit reached!!\n\n    Press enter to return to Undo / Redo Functions...\n\n\n\n");
	            	input.nextLine();
	            }
	                
					
			}
			else if(choice == 2)
			{
				if(state_index < states_size && state_index < 50)
	            {
					ExtraFunctions.consoleClear();
	                state_index++;
	                list = applyState(list);
	                System.out.printf("\n\n\n\n    Settings RE-done!!\n\n    Press enter to return to Undo / Redo Functions..\n\n\n\n");
	                input.nextLine();
	            }
	            else
	            {
	            	System.out.printf("\n\n\n\n    Redo Limit reached!!\n\n    Press enter to return to Undo / Redo Functions...\n\n\n\n");
	            	input.nextLine();
	            }
			}
			else if(choice == 3)
			{
				break;
			}
			else
			{
				System.out.printf("\n\n\n\n  Invalid Option!!\n\n Press enter to try again...\n\n\n");
				input.nextLine();
			}
		}
		return list;
	}
}
