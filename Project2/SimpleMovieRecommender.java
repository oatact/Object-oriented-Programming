import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.util.regex.Pattern;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class SimpleMovieRecommender implements BaseMovieRecommender{
	public Map<Integer, Movie> movies;
	public Map<Integer, User> users;
	public BiMap<Integer, Integer> userid;
	public BiMap<Integer, Integer> movieid;
	public int nummovie;
	public int numuser;
	public double ratingofmatrix[][];
	public double usersimmatrix[][];
	
	
	@Override
	public Map<Integer, Movie> loadMovies(String movieFilename) {
		// TODO Auto-generated method stub
		BufferedReader read = null;
		movies = new HashMap<>();
		Movie Moviesname;
		try
		{
		 read = new BufferedReader(new InputStreamReader (new FileInputStream(movieFilename)));
		Pattern pattern1 = Pattern.compile("(\\d+),((.+) \\((\\d+)\\)),(.+)");
		Pattern pattern2 = Pattern.compile("(\\d+),\"((.+) \\((\\d+)\\))\",(.+)");
		String load = null;
		  read.readLine();
		
			while((load = read.readLine()) != null)
			{
				load = load.trim();
				Matcher m1 = pattern1.matcher(load);
				
			    if(load.matches("(\\d+),((.+) \\((\\d+)\\)),(.+)")) {
			    	while(m1.find())
			    	{
			    		Moviesname = new Movie(Integer.parseInt(m1.group(1)),m1.group(3),Integer.parseInt(m1.group(4)));
			    		if(!m1.group(5).equalsIgnoreCase("(no genres listed)"));
			    		{
			    			String tag [] = m1.group(5).split("\\|");
			    			  for(String tags: tag)
			    			{
			    				Moviesname.addTag(tags);
			    			}
			    		}
			    		movies.put(Integer.parseInt(m1.group(1)), Moviesname);
			    	}
			    }
			    else
			    {
			    	Matcher m2 = pattern2.matcher(load);
			    	while(m2.find())
			    	{
			    		Moviesname = new Movie(Integer.parseInt(m2.group(1)),m2.group(3),Integer.parseInt(m2.group(4)));
			    		if(!m2.group(5).equalsIgnoreCase("(no genres listed)"));
			    		{
			    			String tag [] = m2.group(5).split("\\|");
			    			for(String tags: tag)
			    			{
			    				Moviesname.addTag(tags);
			    			}
			    		}
			    		movies.put(Integer.parseInt(m2.group(1)), Moviesname);
			    	}
			    }
			    if(load.isEmpty())
			    {
			    	continue;
			    }
			}
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally {
			try {
				if(read!=null)
				{
					read.close();
				}
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		
		
		return movies;
	}

	@Override
	public Map<Integer, User> loadUsers(String ratingFilename) {
		// TODO Auto-generated method stub
		users = new HashMap<Integer,User>();
		BufferedReader reader = null;
		Movie mov;
		User use;
		try
		{
			reader = new BufferedReader(new InputStreamReader (new FileInputStream(ratingFilename)));
			String st = null;
			reader.readLine();
			
			while((st = reader.readLine()) != null)
			{
				String[] rates = st.split(",");
				int uid = Integer.parseInt(rates[0]);
				int mid = Integer.parseInt(rates[1]);
				double rate = Double.parseDouble(rates[2]);
				long time = Long.parseLong(rates[3]);
				if(!users.containsKey(uid))
				{
					mov = movies.get(mid);
					use = new User(uid);
					use.addRating(mov, rate , time);
					users.put(uid, use);
				}
				else
				{
					mov = movies.get(mid);
					use = users.get(uid);
					use.addRating(mov, rate, time);
					users.put(uid,use);
				}
				if(st.isEmpty())
				{
					continue;
				}
			}
			
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
			return null;
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return null;
		}
		finally {
			try {
				if(reader!=null)
				{
					reader.close();
				}
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		
		
		return users;
	}

	@Override
	public void loadData(String movieFilename, String userFilename) {
		// TODO Auto-generated method stub
		movies = loadMovies(movieFilename);
		users = loadUsers(userFilename);
	}

	@Override
	public Map<Integer, Movie> getAllMovies() {
		// TODO Auto-generated method stub
		if(movies.isEmpty())
		{
			movies = Collections.emptyMap();
		}
		return movies;
	}

	@Override
	public Map<Integer, User> getAllUsers() {
		// TODO Auto-generated method stub
		if(users.isEmpty())
		{
			users = Collections.emptyMap();
		}
		return users;
	}

	@Override
	public void trainModel(String modelFilename) {
		// TODO Auto-generated method stub
		User user;
		int index = 0;
		int numuser = users.size();
		int nummovie = movies.size();
		int User[] = new int[numuser];
		int Movie[] = new int[nummovie];
		Set<Integer> Setuser =  new TreeSet<Integer>();
		Set<Integer> Setmovie = new TreeSet<Integer>();
		double rating[][] = new double[numuser][nummovie+1];
		
		for(int use : users.keySet())
		{
			Setuser.add(use);
		}
		for(Movie mov : movies.values())
		{
			Setmovie.add(mov.getID());
		}
		for(int Tempuser : Setuser)
		{
			User[index] = Tempuser;
			index++;
		}
		index = 0;
		for(int Tempmov : Setmovie)
		{
			Movie[index] = Tempmov;
			index++;
		}
		System.out.println("@@@ Computing user rating matrix");
		for(int i =0;i<rating.length ; i++)
		{
		user = 	users.get(User[i]);
		for(int j = 0 ; j<rating[i].length;j++)
		{
			if(j==nummovie)
			{
				rating[i][j] = user.getMeanRating();
			}
			else
			{
				if(user.ratings.get(Movie[j])==null)
				{
					rating[i][j] = 0.0;
				}
				else
				{
					rating[i][j] = user.ratings.get(Movie[j]).rating;
				}
			}
		}
	}
		System.out.println("@@@ Computing user sim matrix");
		double usersimmattrix[][] = new double[numuser][numuser];
		for(int i = 0 ; i<numuser;i++)
		{
			for(int j  = 0 ; j<numuser; j++)
			{
				double allrate = 0;
				double allrateUser1 = 0;
				double allrateUser2 = 0;
				double allrateMovie1 = 0;
				double allrateMovie2 = 0;
				double allratesum = 0;
				
				if(i==j)
				{
					usersimmattrix[i][j] = 1.0;
				}
				else
				{
					for(int z = 0; z<nummovie ; z++)
					{
						if(rating[i][z]!=0 && rating[j][z] !=0)
						{
							allrateUser1 = rating[i][z] - rating[i][nummovie];
							allrateMovie1 = rating[j][z] - rating[j][nummovie];
							allrate += allrateUser1*allrateMovie1;
							allrateUser2 += Math.pow(allrateUser1, 2);
							allrateMovie2 += Math.pow(allrateMovie1, 2);		
						}
					}
					if(allrateUser2 ==0 || allrateMovie2 ==0 || allrate==0)
					{
						allratesum = 0.0;
					}
					else
					{
						allratesum = allrate / (Math.sqrt(allrateUser2)* Math.sqrt(allrateMovie2));
						if(Math.sqrt(allrateUser2)*Math.sqrt(allrateMovie2)==0||Math.sqrt(allrateUser2)==0|| Math.sqrt(allrateMovie2)==0)
						{
							allratesum = 0.0;
						}
						usersimmattrix[i][j] = allratesum;
					}
				}
				
			}
		}
		System.out.println("@@@ Writing out model file");
		BufferedWriter writer = null;
		
		try {
			StringBuilder userMap = new StringBuilder("@USER_MAP {");
			StringBuilder movieMap = new StringBuilder("@MOVIE_MAP {");
			for(int i=0; i<User.length ; i++) {
			    userMap.append(i+"="+User[i]+", "); 
			    }
			    
			    userMap.deleteCharAt(userMap.length()-1);
			    userMap.deleteCharAt(userMap.length()-1);
			    userMap.append("}");
			   
			   for(int i=0; i<Movie.length ; i++){ 
			    movieMap.append(i+"="+Movie[i]+", "); 
			    }
			    
			    movieMap.deleteCharAt(movieMap.length()-1);
			    movieMap.deleteCharAt(movieMap.length()-1);
			    movieMap.append("}");
			
			writer = new BufferedWriter(new FileWriter(modelFilename));
			writer.write("@NUM_USERS "+ numuser + "\n");
			writer.write(userMap.toString());
			writer.newLine();
			writer.write("@NUM_MOVIES "+ nummovie + "\n");
			writer.write(movieMap.toString());
			writer.newLine();
			writer.write("@RATING_MATRIX \n");
			
			for(int i = 0 ; i<rating.length;i++)
			{
				for(int j = 0 ; j < rating[i].length ; j++)
				{
					writer.append(rating[i][j]+" ");
				}
				writer.newLine();
				
				}
			writer.write("@USERSIM_MATRIX \n");
			
			for(int i= 0 ;i<usersimmattrix.length;i++)
			{
				for(int j = 0; j <usersimmattrix[i].length;j++)
				{
					writer.append(usersimmattrix[i][j]+" ");
				}
				writer.newLine();
			}
			
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally {
			try {
				if(writer!=null)
				{
					writer.close();
				}
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		
	}
	@Override
	 public  void loadModel(String modelFilename) {
	  
	  BufferedReader reader = null;
	  File file = new File(modelFilename);
	  
	  try {
	   reader = new BufferedReader (new InputStreamReader (new FileInputStream(file)));
	   
	   String numUser = "(@NUM_USERS )(\\d+)";
	   String numMovie = "(@NUM_MOVIES )(\\d+)";
	   String userMap = "(@USER_MAP \\{)(.+)\\}";
	   String movieMap = "(@MOVIE_MAP \\{)(.+)\\}";   
	   String line = null;
	   
	   while((line=reader.readLine()) != null) {
	    if(line.matches(numUser)) {
	     Pattern p = Pattern.compile(numUser);
	     line = line.trim();
	     Matcher m = p.matcher(line);
	     
	     while(m.find()) {
	      this.numuser = Integer.parseInt(m.group(2));
	     }
	    }
	    else if(line.matches(userMap)) {
	     line = line.trim();
	     line = line.replaceAll("[\\s]*[\\d]+=", "");
	     Pattern p = Pattern.compile(userMap);
	     Matcher m = p.matcher(line);
	     while(m.find()) {
	      String map[] = m.group(2).split(",");
	      this.userid = HashBiMap.create(map.length);
	      
	      for(int i=0;i<map.length;i++) {
	       this.userid.put(i, Integer.parseInt(map[i]));
	      }
	     }
	    }
	    
	    else if(line.matches(numMovie)) {
	     Pattern p = Pattern.compile(numMovie);
	     line = line.trim();
	     Matcher m = p.matcher(line);
	     
	     while(m.find()) {
	      this.nummovie = Integer.parseInt(m.group(2));
	     }
	    }
	    
	    else if(line.matches(movieMap)) {
	     line = line.trim();
	     line = line.replaceAll("[\\s]*[\\d]+=", "");
	     Pattern p = Pattern.compile(movieMap);
	     Matcher m = p.matcher(line);
	     
	     while(m.find()) {
	      String map[] = m.group(2).split(",");
	      this.movieid = HashBiMap.create(map.length);
	      
	      for(int i=0;i<map.length;i++) {
	       this.movieid.put(i, Integer.parseInt(map[i]));
	      }
	     }
	    }
	    else if(line.matches("@USERSIM_MATRIX\\s*")) {
	     this.usersimmatrix = new double[this.numuser][this.numuser];
	     
	     for(int i=0;i<this.numuser;i++) {
	      line = reader.readLine();
	      line = line.trim();
	      String aray[] = line.split(" ");
	      
	      for(int j=0 ; j<aray.length ; j++) {
	    	  usersimmatrix[i][j] = Double.parseDouble(aray[j]); 
	      }
	     }
	    }
	    else if(line.matches("@RATING_MATRIX\\s*")) {
	     this.ratingofmatrix = new double[this.numuser][this.nummovie+1];
	     
	     for(int i=0;i< this.numuser;i++) {
	      line = reader.readLine();
	      line = line.trim();
	      String aray[] = line.split(" ");
	      
	      for(int j=0;j<aray.length;j++) {
	       ratingofmatrix[i][j] = Double.parseDouble(aray[j]);
	      }
	     }
	    }
	    if(line.isEmpty()) {
	     continue;
	    }
	   }
	  }
	  catch(FileNotFoundException e){
	   e.printStackTrace();  
	   }
	  catch(IOException e){  
	   e.printStackTrace();  
	   }
	  finally{
	   try{ 
	    if(reader != null) {
	     reader.close(); 
	     }
	    }
	   catch (IOException e){ 
	    e.printStackTrace(); 
	    }
	  }
	 }
	 
	 //-----------------------------------------------------------------------
	 
	 @Override
	 public double predict(Movie m, User u) {
	  double predictRate = 0;
	  BiMap<Integer,Integer> inUserUid = this.userid.inverse();
	  BiMap<Integer,Integer> inMovieMid = this.movieid.inverse();
	  if(!inUserUid.containsKey(u.uid)) {
	   predictRate = u.getMeanRating();
	  }
	  else {
	   int indexUser = inUserUid.get(u.uid);
	   int indexMovie = inMovieMid.get(m.getID());
	   double sumUp = 0;
	   double sumDown = 0;
	   int i;
	   for(i=0;i<numuser;i++) {
	    if(i!=indexUser) {
	     double simu = 0;
	     double meanRating = 0;
	     if(this.ratingofmatrix[i][indexMovie] != 0.0) {
	      simu = this.usersimmatrix[indexUser][i];
	      meanRating = this.ratingofmatrix[i][indexMovie] - this.ratingofmatrix[i][nummovie];
	      sumUp += simu * meanRating;
	      sumDown += Math.abs(simu);
	     }
	    }
	   }
	   
	   predictRate = ratingofmatrix[indexUser][nummovie] + (sumUp / sumDown);
	   if(sumUp == 0 || sumDown == 0) {
	    predictRate = ratingofmatrix[indexUser][nummovie];
	   }
	  }
	  
	  if(predictRate <0) {
	   predictRate = 0;
	  }
	  else if(predictRate > 5) {
	   predictRate = 5;
	  }
	  return predictRate;
	 }
	

	@Override
	public List<MovieItem> recommend(User u, int fromYear, int toYear, int K) {
		// TODO Auto-generated method stub
		List<MovieItem> listofmovie = new ArrayList<MovieItem>();
		for(Movie i : movies.values())
		{
			if(i.year>=fromYear && i.year<=toYear)
			{
				MovieItem item = new MovieItem(i,predict(i,u));
				listofmovie.add(item);
				
			}
		}
		Collections.sort(listofmovie);
		
		if(listofmovie.size()>K)
		{
			listofmovie = listofmovie.subList(0, K);
		}
		return listofmovie;
	}
	
}
