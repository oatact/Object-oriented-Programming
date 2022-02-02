package week4;
import java.util.Scanner;


public class ElectionTester {
	public static void main(String[] args) {
		MyDate election = new MyDate(2019, 3, 24);
		
		Person a = new Person("Lalisa", "Manoban", 1997, 3, 27);
		printPersonElectionInfo(a, election);
		
		Person b = new Person("Nuda", "Inter", 2012, 1, 16);
		printPersonElectionInfo(b, election);
		
		// Create another Person object with your information
		// Print your information, age, and election eligibility.
		Person c = new Person("Wasin", "Heesawat" , 2000 , 7 , 29);
		printPersonElectionInfo(c, election);
		
		/*
		 * YOUR CODE GOES HERE
		 */
		
		// Write a program to take 3 persons information from the user
		// (Hint: Use scanner and for loop to get input)
		String firstname;
		String lastname;
		int yob;
		int mob;
		int dob;
		Scanner scan = new Scanner(System.in);
		for(int i=1;i<=3;i++)
		{
			System.out.print("Enter firstname : ");
			firstname = scan.next();
			System.out.print("Enter lastname : ");
			lastname = scan.next();
			System.out.print("Enter year of birthday ");
			yob = scan.nextInt();
			System.out.print("Enter month of birthday ");
			mob = scan.nextInt();
			System.out.print("Enter day of birthday ");
			dob = scan.nextInt();
			
			Person d = new Person(firstname,lastname,yob,mob,dob);
			printPersonElectionInfo(d,election);
			
		}
		/*
		 * YOUR CODE GOES HERE
		 */
		
		
		
		// Challenge Bonus
		// Instead of taking 3 persons' information, write a program to continuously take input from the user
		// until the user types 'q' to quite the program.
		
		/*
		 * YOUR CODE GOES HERE
		 */
		while(true)
		{
			System.out.println("Enter firstname or type 'q' to exit : ");
			firstname = scan.next();
			if(firstname.equals("q"))
			{
				System.out.print("Exit the program");
				break;
			}
			System.out.print("Enter lastname : ");
			lastname = scan.next();
			System.out.print("Enter year of birthday ");
			yob = scan.nextInt();
			System.out.print("Enter month of birthday ");
			mob = scan.nextInt();
			System.out.print("Enter day of birthday ");
			dob = scan.nextInt();
			
			Person d = new Person(firstname,lastname,yob,mob,dob);
			printPersonElectionInfo(d,election);
		}
		
	}
	
	public static void printPersonElectionInfo(Person p, MyDate election) {
		p.printPersonInfo();
		System.out.println("Age: " + p.getAge(election));
		if(p.isEligible(election))
			System.out.println("This person is eligible to vote.");
		else
			System.out.println("This person is NOT eligible to vote");
		
		System.out.println("-----------------------------------");
	}
}
