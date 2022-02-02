package w5;

public class Song {
	private String title;
	private double duration;
	
	public Song(String title, double duration) {
		// Your code goes here
		this.title = title;
		this.duration = duration;
	}
	
	public String getTitle() {
		// Your code goes here
		return this.title;
	}
	
	public double getDuration() {
		// Your code goes here
		return this.duration;
	}
	
	public int getDurationInSec() {
		// Your code goes here
		int min = (int)duration;
		int sec = (int) Math.round((duration - min) *100);
		int second = (min*60)+sec;
		
		return second;
	}
	
	public String toString() {
		// Your code goes here
		String data = ""+title+","+duration+" minutes("+getDurationInSec()+" second)";
		
		return data;
	}
}
