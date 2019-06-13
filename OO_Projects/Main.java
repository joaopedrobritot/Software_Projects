package paymentSystemOO;

import java.util.Scanner;
//import java.text.SimpleDateFormat;
//import java.util.Date;

public class Main {

	static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		String system_pass = "admin";
		String employee_pass = "0000";
		String given_pass;
		int option;
		boolean choice = true;
		while(true)
		{
			consoleClear();
			System.out.printf("\n       //////////////////////////////////\n");
			System.out.printf("       ///                            ///\n");
			System.out.printf("       ///          Main Menu         ///\n");
			System.out.printf("       ///                            ///\n");
			System.out.printf("       /// 1 - Admin Functions        ///\n");
			System.out.printf("       /// 2 - Employee Functions     ///\n");
			System.out.printf("       /// 3 - Exit                   ///\n");
			System.out.printf("       ///                            ///\n");
			System.out.printf("       //////////////////////////////////\n\n");
			try {
				System.out.printf("\n\n Option: ");option = input.nextInt();input.nextLine();System.out.printf("\n\n");
				if(option == 1)
				{
					while(choice)
					{
						System.out.printf("\n\n Insert the System Password (Default: 'admin'): ");given_pass = input.nextLine();System.out.printf("\n");
						if(given_pass.equalsIgnoreCase(system_pass))
						{
							choice = false;
						}
						else
						{
							System.out.printf("\n\n Incorrect Password!!\n Insert 'back' to go back to menu or simply press enter to try again\n\n Option :");
							if(input.nextLine().equalsIgnoreCase("back"))
							{
								break;
							}
						}
					}
					while(!choice)
					{
						consoleClear();
						System.out.printf("\n       ////////////////////////////////////////////////////\n");
						System.out.printf("       ///                                              ///\n");
						System.out.printf("       ///           *Administrator Functions*          ///\n");
						System.out.printf("       ///                                              ///\n");
						System.out.printf("       /// 1 - Add a Employee                           ///\n");
						System.out.printf("       /// 2 - Delete a Employee                        ///\n");
						System.out.printf("       /// 3 - Change a Employee Details                ///\n");
						System.out.printf("       /// 4 - Payments for Today                       ///\n");
						System.out.printf("       /// 5 - Launch a Tax of Service                  ///\n");
						System.out.printf("       /// 6 - Undo / Redo                              ///\n");
						System.out.printf("       /// 7 - Create a new Payment Schedule            ///\n");
						System.out.printf("       /// 8 - Show Details of an Employee              ///\n");
						System.out.printf("       /// 9 - Show Details of All Employees            ///\n");
						System.out.printf("       /// 10 - Change System Password                  ///\n");
						System.out.printf("       /// 11 - Back to Main Menu                       ///\n");
						System.out.printf("       ///                                              ///\n");
						System.out.printf("       ////////////////////////////////////////////////////\n\n\n Option: ");
						try {
							switch(input.nextInt())
							{
								case 1:
									input.nextLine();
									System.out.printf("\n\n Comming soon...\n\n Press enter to continue...");
									input.nextLine();
									break;
									
								case 2:
									input.nextLine();
									System.out.printf("\n\n Comming soon...\n\n Press enter to continue...");
									input.nextLine();
									break;
									
								case 3:
									input.nextLine();
									System.out.printf("\n\n Comming soon...\n\n Press enter to continue...");
									input.nextLine();
									break;
									
								case 4:
									input.nextLine();
									System.out.printf("\n\n Comming soon...\n\n Press enter to continue...");
									input.nextLine();
									break;
									
								case 5:
									input.nextLine();
									System.out.printf("\n\n Comming soon...\n\n Press enter to continue...");
									input.nextLine();
									break;
									
								case 6:
									input.nextLine();
									System.out.printf("\n\n Comming soon...\n\n Press enter to continue...");
									input.nextLine();
									break;
									
								case 7:
									input.nextLine();
									System.out.printf("\n\n Comming soon...\n\n Press enter to continue...");
									input.nextLine();
									break;
									
								case 8:
									input.nextLine();
									System.out.printf("\n\n Comming soon...\n\n Press enter to continue...");
									input.nextLine();
									break;
									
								case 9:
									input.nextLine();
									System.out.printf("\n\n Comming soon...\n\n Press enter to continue...");
									input.nextLine();
									break;
									
								case 10:
									input.nextLine();
									System.out.printf("\n\n Comming soon...\n\n Press enter to continue...");
									input.nextLine();
									break;
									
								case 11:
									choice = true;
									break;
									
								default:
									input.nextLine();
									System.out.printf("\n\n Invalid Option!!\n Press enter to try again...");
									input.nextLine();
									break;
							}
						}
						catch(Exception e)
						{
							input.nextLine();
							System.out.printf("\n\n The typed value is not an integer!!\n Press enter to try again...\n\n\n\n");
							input.nextLine();
						}
					}
				}
				else if(option == 2)
				{
					while(choice)
					{
						System.out.printf("\n\n Insert the Employee Password (Default: '0000'): ");given_pass = input.nextLine();System.out.printf("\n");
						if(given_pass.equalsIgnoreCase(employee_pass))
						{
							choice = false;
						}
						else
						{
							System.out.printf("\n\n Incorrect Password!!\n Insert 'back' to go back to menu or simply press enter to try again");
							if(input.nextLine().equalsIgnoreCase("back"))
							{
								break;
							}
						}
					}
					while(!choice)
					{
						consoleClear();
						System.out.printf("\n       ////////////////////////////////////////////////////\n");
						System.out.printf("       ///                                              ///\n");
						System.out.printf("       ///             *Employee Functions*             ///\n");
						System.out.printf("       ///                                              ///\n");
						System.out.printf("       /// 1 - Launch a TimeCard                        ///\n");
						System.out.printf("       /// 2 - Launch a Selling                         ///\n");
						System.out.printf("       /// 3 - Change a Employee Details                ///\n");
						System.out.printf("       /// 4 - Payment Schedules                        ///\n");
						System.out.printf("       /// 5 - Undo / Redo                              ///\n");
						System.out.printf("       /// 6 - Payment Schedules                        ///\n");
						System.out.printf("       /// 7 - Show Details of an Employee              ///\n");
						System.out.printf("       /// 8 - Back to Main Menu                        ///\n");
						System.out.printf("       ///                                              ///\n");
						System.out.printf("       ////////////////////////////////////////////////////\n\n\n Option: ");
						try {
							switch(input.nextInt())
							{
								case 1:
									input.nextLine();
									System.out.printf("\n\n Comming soon...\n\n Press enter to continue...");
									input.nextLine();
									break;
									
								case 2:
									input.nextLine();
									System.out.printf("\n\n Comming soon...\n\n Press enter to continue...");
									input.nextLine();
									break;
									
								case 3:
									input.nextLine();
									System.out.printf("\n\n Comming soon...\n\n Press enter to continue...");
									input.nextLine();
									break;
									
								case 4:
									input.nextLine();
									System.out.printf("\n\n Comming soon...\n\n Press enter to continue...");
									input.nextLine();
									break;
									
								case 5:
									input.nextLine();
									System.out.printf("\n\n Comming soon...\n\n Press enter to continue...");
									input.nextLine();
									break;
									
								case 6:
									input.nextLine();
									System.out.printf("\n\n Comming soon...\n\n Press enter to continue...");
									input.nextLine();
									break;
									
								case 7:
									input.nextLine();
									System.out.printf("\n\n Comming soon...\n\n Press enter to continue...");
									input.nextLine();
									break;
									
								case 8:
									choice = true;
									break;
									
								default:
									input.nextLine();
									System.out.printf("\n\n Invalid Option!!\n Press enter to try again...");
									input.nextLine();
									break;
							}
						}
						catch(Exception e)
						{
							input.nextLine();
							System.out.printf("\n\n The typed value is not an integer!!\n Press enter to try again...\n\n\n\n");
							input.nextLine();
						}
					}
				}
				else if(option == 3)
				{
					System.out.printf("\n\n\n Program Stopped!!\n\n");
					input.close();
					System.exit(0);
				}
				else
				{
					System.out.printf("\n\n Invalid operation !!\n Press enter to try again...");
					input.nextLine();
				}
			}
			catch(Exception e)
			{
				input.nextLine();
				System.out.printf("\n\n The typed value is not a Integer!!\n Press enter to try again...\n\n\n\n");
				input.nextLine();
			}
		}
	}

	public static void consoleClear()
	{
		for(int i = 0;i<20;i++)
		{
			System.out.printf("\n");
		}
	}
}
