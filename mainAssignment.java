/**
* @student 2014305
* @author Bianca Catalunha
* CCT - Computer Programming - Main Assignment - Dec 2014:
* This program simulates a Lotto draw.
* */
import java.util.Scanner;

class mainAssignment{
	public static void main(String [] args)//MAIN METHOD
	{
		//TOP//
		System.out.println("˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜*****˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜");
		System.out.println("                        LOTTO         ");
		System.out.println("˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜*****˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜");
		System.out.println();
		//END OF TOP//
		
		// DEFINITION //
		int numLines=0;//stores number of lines the user would like to play
		boolean validation= false;//stores if the number of lines is valid
		char option = ' ';//stores if the user would like to enter numbers again
		char option2 = ' ';//stores if the user would like to play again
		int [][] numInput= new int [numLines][5];//multidimensional array to store lines and numbers
		int number= 6;//number of draw random numbers
		int[] draw= new int [number];//array to store lucky numbers
		
		do{//play again
			do{//confirm game
				numLines=validadeLines( );//METHOD 1: validate number of lines entered by the user
	
			
			
				numInput=getInput(numLines);//METHOD 2: gets input from user
				
				System.out.println("\n"+"------------------------------------------");
				System.out.println("|           Your lotto ticket            |");
			
				displayLines(numLines, numInput);//METHOD 3: Displays lines and numbers choosen by the user
	
				option = confirm( 1 );//METHOD 4: Option message (option 1: confirm game <Y>es / <N>o:)
			
			} while( option == 'N' );//While confirm game equal to N
		// END OF DEFINITION //
	
		// PROCESSING //	
		System.out.println("\n"+"˜˜˜˜˜˜˜˜˜˜˜˜˜˜LUCKY NUMBERS˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜");
		draw=drawNumbers( number);//METHOD 5: Generates unique random numbers
		System.out.println("\n" + "˜˜˜˜˜˜˜˜˜˜˜˜˜˜*************˜˜˜˜˜˜˜˜˜˜˜˜˜˜");
		// END OF PROCESSING //

		// RESULT //
		displayResult(numInput, draw);//METHOD 6: Displays result of the game
		
		option2 = confirm( 2 );//METHOD 4: Option message (option 2: play again <Y>es / <N>o:)
		} while( option2 == 'Y' );//While play again equal to Y
		// END OF RESULT //
	}

	public static int validadeLines ()//METHOD 1: validate number of lines entered by the user
	{
		Scanner kb = new Scanner (System.in);
		boolean validation= false;//stores if input is valid or not
		int numLines=0;//stores how many lines user would like to play

		do
		{
			System.out.print("\n"+"How many lines would you like to play? ");//asks how many lines the user would like to play
			try
			{
				numLines = Integer.parseInt(kb.nextLine());//stores input
				validation=true;//turns validation to true
			}
			catch (NumberFormatException e) //if input is not a number
    			{
    				System.out.println("INVALID INPUT, Try again...");//an error message will be sent
        			validation= false;//turns validation to false, so number will be asked again
    			}
		}while(validation == false) ;//will do while validation is false(input is not a number)
    		
    		return numLines;//returns number of lines that will be played
	}

	public static int[][] getInput(int numLines)//METHOD 2: gets input from user
	{
		Scanner kb = new Scanner (System.in);
		boolean Rcn=true;//variable stores false(number already in the list), true (number is unique)
		int pivo=0;//pivo variable to store number just entered by user to be validated before storing in the array
		int [][] numInput= new int [numLines][5];//Multidimentional array, stores all good to go numbers entered by the user
		
		for(int x=1; x<=numLines; x++)//number of lines loop
		{	
			char qp= ' ';//stores if user choosen quick pick or not
			
			System.out.println("\n"+"    < LINE: " +x+ " > ");//prints number of the line
			
			qp= confirm( 3 );//METHOD 4: Option message (option 3: quick pick <Y>es / <N>o:)
					
			if(qp == 'Y')//if user selected quick pick
			{
				int [] qpNum= new int [5];//array to store 5 random numbers
				
				qpNum=drawNumbers(5);//METHOD 5: Generates unique random numbers
				
				int zz=0;
				while(zz < 5)
				{
					numInput[x-1][zz]=qpNum[zz];//stores 5 random numbers generated into multidimentional array
					zz++;
				}	
			}
			else//if user want to pick the numbers
			{	
				System.out.println("\n"+"((( Choose 5 numbers for each line from 1 - 45 )))");
					
				int y=1;
				while(y <= 5)//5 times loop //5 numbers
				{
					System.out.print("\n"+"[ Number ( " +y+ " ) ]: " );//asks for number at position y
					try
					{
						pivo= Integer.parseInt(kb.nextLine());//stores input in a pivo variable
						
						if(pivo > 0 && pivo < 46)//checks if the input is between 1 and 45
						{
							if(y-1 == 0)//if it is the first number to be entered, it wont check itself
							{
								numInput[x-1][y-1]=pivo;//adds num to array
								y++;//increments one to the numbers sentinel variable
							}
							else// if its not the first number to be entered
							{	
								Rcn=checkNumber(numInput, pivo, x-1);//METHOD 7: checks if number is already in the array
	
								if(Rcn == false)//if the return of method 7 is equal to false
								{
									System.out.println("Number already in the list, Try again...");//error message and do NOT add one to sentinel variable so another number at that position will be asked
								}
								else// if the return of method 7 is equal to true, number is validated
								{
									numInput[x-1][y-1]=pivo;//adds number to array 
									y++;//increments one to the numbers sentinel variable
								}
							}
						}
						else// if number is not between 1 and 45
						{
							System.out.println("INVALID INPUT, Try again...");//shows error msg
						}
					}
					catch(NumberFormatException e)// if input is not a number
					{
						System.out.println("INVALID INPUT, Try again...");//prints error msg
					}
				}
			}
		}
		return numInput;//returns multidimentional array with all lines and numbers
	}
	
	public static void displayLines(int numLines, int[][] numInput)//METHOD 3: Displays lines and numbers choosen by the user
	{
		for ( int r = 0 ; r < numLines ; r++)// outter loop - Rows
		{
			System.out.println("|                                        |");
			System.out.print("| Line "+(r+1)+": ");//prints line position
			for ( int c = 0; c < 5 ; c++ )// inner loop - columns
			{	
				int length = String.valueOf(numInput[r][c]).length();//stores in length how many digits the number has, for indentation purpose
				if(length == 1)//only 1 digit
				{
					System.out.print("[ "+ numInput[r][c] + "  ]"); //prints number and spaces
				}
				else//2 digits
				{
					System.out.print("[ "+ numInput[r][c] + " ]");//prints number and less spaces		
				}
			}
			System.out.println(" |");
		}
		System.out.println("|                                        |");
		System.out.println("------------------------------------------");
	}

	public static char confirm(int position)//METHOD 4: Option message
	{
		Scanner kb = new Scanner (System.in);
		char option = ' ';//stores option selected by user
		
		do{//3 different messages
			if(position == 1)
			{
				System.out.print("\n"+"Confirm game <Y>es / <N>o: ");
			}
			else if (position == 3)
			{
				System.out.print("\n"+"Quick Pick <Y>es / <N>o: ");
			}
			else
			{
				System.out.print("\n"+"Play again <Y>es / <N>o: ");
			}

			try
			{
				option = kb.nextLine().toUpperCase().charAt(0);//converts to char, stores input into option
			}
			catch( NumberFormatException e )//if input is no a number
			{
				System.out.println("Invalid input, Try again ...");//error msg
			}

			if ( option != 'N' && option != 'Y' )//if input different than N/Y
			{
				System.out.println("Invalid input, Try again ... ");//error msg
			}

		} while ( option != 'N' && option != 'Y' );//will do while input different than N/Y

		return option;//returns users choice
	}

	
	public static int[] drawNumbers( int number )//METHOD 5: Generates unique random numbers
	{//THIS METHOD IS USED TO DRAW NUMBERS AND QUICK PICK
		int [] numbers= new int [number];//creates array, 5 numbers quick pick or 6 numbers draw

		for(int x=0; x<number; x++)//x times loop
		{
			numbers[x]=(int)(Math.random()*45)+1;//creates a random number between 45 and 1

			if(x!=0)//if it is the first number generated it doesn't check itself
			{
				for(int y=0; x>y; y++)//while the number of indexes are greater than zero. Checks all previous numbers
				{
					if(numbers[x] == numbers[y])//checks if the recently generated number is equal to any previous number
					{
						int pivo=(int)(Math.random()*45)+1;//generates a new random number

						if(pivo == numbers[x])//if this new number is equal to the old and duplicated one
						{
							y=0;//send back to second for loop
						}
						else//if the new numbers is not equal to the previous one
						{
							numbers[x]=pivo;//allows to update number
							y=0;//and send it back to second for loop to make sure it is not equal to any previous one
						}
					}
				}
			}
			if(number != 5)//if its the quick pick mode, don't display numbers
			{
				if(x == 5)//if its the last number (extra number)
				{
					System.out.print("{{ " +  numbers[x]+" }} ");//prints in a different style
				}
				else
				{
					System.out.print("( " +  numbers[x]+" ) ");//prints number
				}
			}
		}	
		return numbers;//returns array
	}

	public static void displayResult(int [][] list, int [] draw)//METHOD 6: Displays result of the game
	{
		System.out.println("---------------GAME RESULTS---------------");
		for ( int r = 0 ; r < list.length ; r++)// outter loop - Rows
		{
			int matches=0;//stores how many numbers matches the result
			
			System.out.print("\n"+"Line "+(r+1)+": ");//number os the line
			
			for ( int c = 0; c < list[r].length ; c++ )// inner loop - columns
			{
				String pNum= " ";//will store output
				pNum = String.valueOf(list[r][c]) ;//stores output first in this string for indentation purpose
				
				for(int x = 0; x < draw.length; x++)//5 times loop
				{	
					if(draw[x] == list[r][c])//if any lucky number is equal to chosen by the user
					{
						pNum= pNum+ "*";//adds a star to output	
						matches=matches+1;//adds one to number os matches
					}	
				}
				
			 	int length = String.valueOf(pNum).length();//checks how many digits there is in the output variable
			 	if(length == 1)//1 digit
			 	{
			 		System.out.print("[ "+ pNum+" ]  ");//prints number and amount of spaces
			 	}
			 	else if(length == 2)//2 digits
			 	{
			 		System.out.print("[ "+ pNum+" ] ");//prints number and amount of spaces
			 	}
			 	else//3 digits
			 	{
			 		System.out.print("[ "+ pNum+" ]");//prints number and amount of spaces
			 	}
			}
			System.out.println(" --- > " + matches + " match(es)");//prints number of matches for each line
		}
		System.out.println("------------------------------------------");
	}

	public static boolean checkNumber( int[][] list, int num, int line )//METHOD 7: checks if number is already in the array
	{//NOTE: I found less complicated to send the multidimentional array and line to be checked
		for(int z=0; z <= list.length+1; z++)//rows loop
		{
			if(list[line][z] == num)//if number is already in this line of the array
			{
				return false;//returns false
			}
		}
		return true;//returns true
	}
}
