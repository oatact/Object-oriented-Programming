package w5;
// Name: Wasin Heesawat
// ID: 6288077
import java.util.ArrayList;
public class Playlist {

	private String name;
	private int duration;
	private ArrayList<Song> songs = new ArrayList<Song>() ; 
	
	
	
	public Playlist(String name) {
		// Your code goes her
		this.name = name;
		
	}
	
	public void addSong(Song aSong) {
		// Your code goes here
		songs.add(aSong);
	}
	
	public void addSongAtIndex(Song aSong, int index) {
		// Your code goes here
		if(index> songs.size())
		{
			System.out.println("Eror : Couldn't add song at index : "+index);
			
		}
		else {
		songs.add(index,aSong);
	}
	}
	
	public boolean removeSongByIndex(int index) {
		// Your code goes here
	
			if(index>songs.size())
			{
				System.out.println("Error : The index is invalid");
				return false;
			}
			else
		songs.remove(index);
		return true;		
	}	
	
	public boolean removeSongByTitle(String title) {
		// Your code goes here
		int x= -1;
		
		for(int i = 0;i<songs.size();i++)
		{
			if(songs.get(i).getTitle()==title)
			{
				x=i;
			}
			
		}
		if(x!=-1)
		{
			songs.remove(x);
		}
		else {
			System.out.println("Error : The title is not found");
			return false;
		}
		return true;		
	}
	
	public void moveUp(int current) {
		// Your code goes here
		songs.add(current-1,songs.get(current));
		songs.remove(current+1);
	}	
	
	public void moveDown(int current) {
		// Your code goes here
		songs.add(current+2,songs.get(current));
		songs.remove(current);
	}	
	
	public int getPlaylistDuration() {
		// Your code goes here
		duration = 0;
		for(int i=0;i<songs.size();i++)
		{
			duration += songs.get(i).getDurationInSec();
		}
		return duration;
		
	}	
	
	public void showPlaylist() {
		// Your code goes here
		System.out.println(name);
		for(int i =0;i<songs.size();i++)
		{
			System.out.println("["+i+"]"+songs.get(i));
		}
		System.out.println("Total duration is "+ (double)( (getPlaylistDuration()/60) + (double) (getPlaylistDuration()%60)/100) +" minutes");
	}
}



