import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.io.FileUtils;

public abstract class NameSearcher {

	protected static ArrayList<String> readNames = null;
	protected int number_of_compared = 0; 
	
	NameSearcher(String filename)
	{
		try {
			readNames = (ArrayList<String>) FileUtils.readLines(new File(filename), "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}

		//clean word
        readNames.replaceAll(name -> name.toLowerCase());  
        	
	}

	public int getNumComparisons() {
		return number_of_compared;
	}

	public void resetCompareCounter() {
		number_of_compared = 0;
	}
	
	public void sortWord() {
	       //TODO
	       // Collections.sort(readNames);

	        for (int j=0; j<readNames.size(); j++){ // bubble sort to sort number
	            for (int i= 0; i < readNames.size() - j - 1; i++) {
	                if (readNames.get(i).compareTo(readNames.get(i+1)) > 0){
	                    String temp = readNames.get(i);
	                    readNames.set(i,readNames.get(i+1));
	                    readNames.set(i+1, temp);
	                }
	            }
	        }

	    }
	public abstract String find(String query);
	
	

}
class LinearNameSearcher extends NameSearcher{

	LinearNameSearcher(String filename) {
		super(filename);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String find(String query) {
		// TODO Auto-generated method stub
		for(int i = 0 ; i< readNames.size() ; i++)
		{
			number_of_compared++;
			if(query.compareToIgnoreCase(readNames.get(i))==0)
			{
			return	"Found: '"+query+"' AT_INDEX("+i+")";
			}
			}
			return "Not Found Name: '"+query+"'";
	
	}
}
class BinaryNameSearcher extends NameSearcher
{

	BinaryNameSearcher(String filename) {
		super(filename);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String find(String query) {
		// TODO Auto-generated method stub
		int i = 0, size = readNames.size()-1;
		while(i <= size)
		{
			int mid = (i+size)/2;
			number_of_compared++;
			if(query.compareToIgnoreCase(readNames.get(mid))==0) 
			{
				 return "Found: '"+query+"' AT_INDEX("+mid+")";
			}
			if(query.compareToIgnoreCase(readNames.get(mid))>0)
			{
				i = mid+1;
			}
			else
			{
				size = mid-1;
			}
		}return "Not Found Name: '"+query+"'";
		
	}
	
}
		

