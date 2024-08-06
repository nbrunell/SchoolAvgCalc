//P1, Nate Brunell, CIS 340, T/TH 1:30pm
import java.util.Scanner;

public class P1 {

	static Scanner inputDevice = new Scanner(System.in);
	static String[] nameArray;
	static double[][] scoreArray;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//variable declaration and initialization
		final int NUMBER_OF_SCORES;
		
		System.out.print("How many scores per student? ");
		NUMBER_OF_SCORES = Integer.parseInt(inputDevice.nextLine());
		
		//initialize arrays
		nameArray = new String[3];
		scoreArray = new double[3][NUMBER_OF_SCORES];
		
		//loops for name and score input 
		for(int i = 0; i < nameArray.length; i++)
		{
			System.out.print("\n\nEnter name for student " + (i+1) + ": ");
			nameArray[i] = inputDevice.nextLine().toUpperCase();
			
			System.out.printf("\nEntering scores for %s\n", nameArray[i]);
			
				for(int j =0; j < scoreArray[i].length; j++)
				{
					System.out.print("Quiz " + (j + 1) + ": ");
					scoreArray[i][j] = Double.parseDouble(inputDevice.nextLine());
				}
		}
		
		//loop for menu
		char option; 
		do {
			//print menu options
			System.out.println("\n\n\n\n");
            System.out.println("\t\t\tMenu:");
            System.out.println("1. Class Average");
            System.out.println("2. Student Average");
            System.out.println("3. Quiz Average");
  
            System.out.print("Enter choice number, or x to exit: ");
			option = inputDevice.next().charAt(0);
			inputDevice.nextLine();
			
			switch(option)
			{
			case '1':
				System.out.printf("\nClass average for all quizzes is %.2f",
						calculateClassAverage());
				break;
				
			case '2':
				System.out.println("\nCalculating average by student.");
				System.out.print("\n\nEnter Student name: ");
				String studentName = inputDevice.nextLine();
				calculateStudentAverage(studentName);
				break;
				
			case '3':
				System.out.println("\nCalculating average by Quiz Number");
				System.out.print("\n\nEnter Quiz Number: ");
				int quizNumber = Integer.parseInt(inputDevice.nextLine());
				calculateQuizAverage(quizNumber);
				break;
				
			case 'x':
                System.out.println("Exiting the program. Goodbye!");
                System.exit(0);
                break;
                
			default:
                System.out.println("Invalid choice. Please try again.");
                break;
			}
			
		} while (option != 'x');
		
	}
	
	
	private static double calculateClassAverage()
	{
		double total = 0;
	    int count = 0;
	    for (int i = 0; i < scoreArray.length; i++) 
	    {
	        for (int j = 0; j < scoreArray[i].length; j++) 
	        {
	            total += scoreArray[i][j];
	            count++;
	        }
	    }
	    return (total/count);
	}
	
	private static void calculateStudentAverage(String studentName)
	{
		double total = 0;
	    int count = 0;
		boolean found = false;
	    for (int i = 0; i < nameArray.length; i++) 
	    {
	        if (nameArray[i].equalsIgnoreCase(studentName)) 
	        {
	            found = true;
	            System.out.print(studentName + "'s scores are: ");
	            for (double score : scoreArray[i]) 
	            {
	                System.out.printf("%.2f ", score);
	            }
	        
	            for(int j = 0; j < scoreArray[i].length; j++)
	            {
	            	total += scoreArray[i][j];
	 	            count++;
	            }
	            double studentAverage = total/count;
	            
	            System.out.printf("\n%s's average is %.2f", 
	            		studentName, studentAverage);
	        }
	    }
	    
	    if (!found) 
	    {
	        System.out.println("Student not found.");
	    }
	}
	    
	
	private static void calculateQuizAverage(int quizNumber)
	{
		if (quizNumber <= 0 || quizNumber > scoreArray[0].length)
		{
	        System.out.println("Invalid quiz number.");
		}
		
		double total = 0;
	    int count = 0;
	    for (int i = 0; i < scoreArray.length; i++) 
	    {
	        if (quizNumber <= scoreArray[i].length) 
	        { 
	            total += scoreArray[i][quizNumber - 1]; 
	            count++;
	        }
	    }
	    
	    double quizAverage = total/count;
	    System.out.printf("\nQuiz %d average is %.2f",
	    		(quizNumber -1),quizAverage );
	}


}
