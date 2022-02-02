public class Movie implements Comparable<Movie> {
	public int mid = -1;
	public String title = null;
	public int year = -1;
	
	public Movie(int _mid, String _title, int _year)
	{
		mid = _mid;
		title = _title;
		year = _year;
	}
	
	
	public String toString()
	{
		return "[mid:"+mid+" |"+title+" |"+year+"]";
	}
	
	
	public int compareTo(Movie m) {
	        // YOUR CODE GOES HERE
	        if(this.equals(m)) {
	            return 0;
	        }else if(this.title.compareTo(m.title) == 0) {
	            if(this.year < m.year) {
	                return -1;
	            }else if(this.year > m.year ) {
	                return 1;
	            }else {
	                if(this.mid < m.mid) {
	                    return -1;
	                }else if(this.mid > m.mid) {
	                    return 1;
	                }
	            }
	        }else if(this.title.compareTo(m.title) < 0) {
	            return -1;
	        }else {
	            return 1;
	        }
	        return -1;
	    }
}