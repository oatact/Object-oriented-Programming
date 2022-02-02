import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.io.*;

//import org.apache.commons.io.FileUtils;

public class SimpleMovieDatabase {
	public Map<Integer, Movie> movies = new HashMap<Integer,Movie>();
	
	public void importMovies(String movieFilename)
	{	
		BufferedReader reader = null;
		File myFile = new File(movieFilename);
		try	{
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(myFile)));	
		String s = null;
		while((s =reader.readLine()) != null)
		{
			s=s.trim(); //to cut space from front and black
			if(s.isEmpty()==false)
			{
				String[] word = s.split(","); //check(139385,The Revenant,   Adventure|Drama)
				String checknum = "[0-9]+"; // 0-9
				if(word[0].matches(checknum)==true && word[1].charAt(0) != ' ')
				{
					int num = Integer.parseInt(word[0]); // change String to int
					Movie m = new Movie(num,word[1]);
					movies.put(num, m);
					String[] word1 = word[2].split("\\|");
					m.tags.addAll(Arrays.asList(word1));
					
				}
			}
		}
		reader.close();
		
	}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
{
			e.printStackTrace();
}
	}
	
	
	//-------------------BONUS----------------------
	public List<Movie> searchMovies(String query) 
	{
		List<Movie> search = new ArrayList<Movie>();
		
		for(Movie movie : movies.values() )
		{
		String a = movie.title.toLowerCase();
		String b = query.toLowerCase();
		if(a.contains(b))
		{
			search.add(movie);
		}
		
		}
		return search;
	}
	
	public List<Movie> getMoviesByTag(String tag)
	{
List<Movie> check = new ArrayList<Movie>();
		
		for(Movie movie : movies.values() )
		{
			for(String s : movie.tags)
		{
		String a = s.toLowerCase();
		String b = tag.toLowerCase();
		if(a.contains(b))
		{
			check.add(movie);
		}
		
		}
		}
		return check;
	}
	
	
	public static void main(String[] args)
	{
		SimpleMovieDatabase mdb = new SimpleMovieDatabase();
		mdb.importMovies("lab10_movies.txt");
		System.out.println("Done importing "+mdb.movies.size()+" movies");
		int[] mids = new int[]{139747, 141432, 139415, 139620, 141305};
		for(int mid: mids)
		{
			System.out.println("Retrieving movie ID "+mid+": "+mdb.movies.get(mid));
		}
		
		
		System.out.println("\n////////////////////////// BONUS ///////////////////////////////");
		String[] queries = new String[]{"america", "thai", "thailand"};
		for(String query: queries)
		{
			System.out.println("Results for movies that match: "+query);
			for(Movie m: mdb.searchMovies(query))
			{
				System.out.println("\t"+m);
			}
			System.out.println();
		}
		
		String[] tags = new String[]{"Musical", "Action", "Thriller"};
		for(String tag: tags)
		{
			System.out.println("Results for movies in category: "+tag);
			for(Movie m: mdb.getMoviesByTag(tag))
			{
				System.out.println("\t"+m);
			}
			System.out.println();
		}
		
		
	}

}
