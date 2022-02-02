package w5;
import java.util.Scanner;
public class Palindrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			Scanner scan = new Scanner(System.in);
			System.out.print("Enter a word or phase to check if it a palindrome : ");
			String word = scan.nextLine()		;	
			String replace = word.replaceAll(" ", "");
			replace.toLowerCase();
			String wOrPhase;
			if(word.contains (""))
			{
				wOrPhase = "pharse";
				
			}
			else 
				{
				wOrPhase ="word";
				}
			int c=0;
			for(int i =0 ;i<replace.length()/2;i++)
			{
				if(replace.charAt(i) != replace.charAt(replace.length()-i-1))
				{
					c++;
				}
			}if(c==0)
				{
					System.out.println("The input "+wOrPhase +" \"" +word + " \"" +"is a palindrome");
				}
				else 
					System.out.println("The input "+wOrPhase +"\"" +word + "\"" +"is not a palindrome");
			}
			
	}
