import java.util.*;

import java.io.*;

	
public class Sorting {
	public static List<String> aname;
		public static List<String> importfile(String filename){
			try
			{
				BufferedReader reader = new BufferedReader(new FileReader(filename));
				String name = "";
				while((name = reader.readLine()) != null)
				{
				String[] t = name.split(",");
				aname = new LinkedList<String>(Arrays.asList(t));
			}
				reader.close();
			
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			return aname;
		}
		
		//use array
//			BufferedReader reader = new BufferedReader(new FileReader(filename));
//			String s = "";
//			while((s = reader.readLine())!=null)
//			{
//				String[] a = s.split("\\s");
//				return a;
//			
//		}
//			return null;
		public static void printString(List<String> name)
		{
		System.out.println("Original : "+name);
		}
		
		
		
		public static void Sort(List<String> arr)
		{
			for(int i=1; i < aname.size(); i++){        
				String temp = aname.get(i);        
				int j = i-1;         
				while(j >= 0 && (aname.get(j).compareTo(temp) < 0)){         
					aname.set(j+1, aname.get(j));          
					j--;       
					}         
				aname.set(j+1, temp);        
				System.out.println("Pass "+i+": "+arr);      } // insertion sort

		}	
		//use array
//			int x = 1;
//			String temp ;
//			for(int i = 0; i< arr.length ; i++)
//			{
//				for(int j = i+1 ; j< arr.length ; j++)
//				{
//					if( arr[j].compareTo(arr[i])>0)
//					{
//					 temp = arr[j];
//						arr[j] = arr[i];
//						arr[i] = temp;
//					}		
//					
//				}
//			if(x<arr.length) {
//				System.out.printf("Pass "+(i+1)+" :[", x);
//				for(int a = 0 ; a<arr.length ; a++)
//				{
//					if(a==arr.length-1)
//					{
//					System.out.print(arr[a]);
//					}
//					else {
//							System.out.print(arr[a]);
//					System.out.print(", ");
//					}
//				
//				}
//				System.out.print("]");
//				System.out.println();
//				
//			}
//			x++;
//			}

		public static void main(String[]  args)
		{
			List<String> myfile=  importfile("java.txt");
			printString(myfile);
			Sort(myfile);
		}
		}