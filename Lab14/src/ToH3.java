import java.util.Stack;
import java.lang.Math; 
public class ToH3 {
		public static Stack<Integer>[] tower = new Stack[4];
	
		public static void main(String[] args)
		{
			tower[1] = new Stack<Integer>();
			tower[2] = new Stack<Integer>();
			tower[3] = new Stack<Integer>();
			
			tower[1].push(3);
			tower[1].push(2);
			tower[1].push(1);
			
			System.out.println("          MY TOWER OF HANOI ");
			TowerofHanoi(3,1,2,3);
			System.out.println("Step of move disk are "+(int)(Math.pow(2,3)-1) + " Steps");
		}
		
		public static void TowerofHanoi(int n , int s1 , int s2 , int s3)
		{
			if(n>0)
			{
				TowerofHanoi(n-1, s1, s3, s2);
				int temp = tower[s1].pop();
				tower[s3].push(temp);
				 System.out.println("Move disk " + n 
	                        + " from tower "+ s1 + " to top of tower " + s3);
				TowerofHanoi(n-1,s2,s1,s3);

			}
		}
		
}

