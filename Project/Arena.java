//Name: Wasin Heesawat
//ID: 6288077
//Section 3
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class Arena {

	public enum Row {Front, Back};	//enum for specifying the front or back row
	public enum Team {A, B};		//enum for specifying team A or B
	
	private Player[][] teamA = null;	//two dimensional array representing the players of Team A
	private Player[][] teamB = null;	//two dimensional array representing the players of Team B
	private int numRowPlayers = 0;		//number of players in each row
	
	public static final int MAXROUNDS = 100;	//Max number of turn
	public static final int MAXEACHTYPE = 3;	//Max number of players of each type, in each team.
	private final Path logFile = Paths.get("battle_log.txt");
	
	private int numRounds = 0;	//keep track of the number of rounds so far
	
	/**
	 * Constructor. 
	 * @param _numRowPlayers is the number of players in each row.
	 */
	public Arena(int _numRowPlayers)
	{	
		teamA = new Player[2][_numRowPlayers];
		teamB = new Player[2][_numRowPlayers];
		this.numRowPlayers = _numRowPlayers;
		
		////Keep this block of code. You need it for initialize the log file. 
		////(You will learn how to deal with files later)
		try {
			Files.deleteIfExists(logFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		/////////////////////////////////////////
		
	}
	
	/**
	 * Returns true if "player" is a member of "team", false otherwise.
	 * Assumption: team can be either Team.A or Team.B
	 * @param player
	 * @param team
	 * @return
	 */
	
	//check player each team
	public boolean isMemberOf(Player player, Team team)
	{
		switch(team) {
		case A:
			for(int i = 0 ; i<2 ; i++)
			{
				for(int j = 0 ; j<numRowPlayers ; j++)
				{
					if(player.equals(teamA[i][j]) == true) {
						return true;
					}
				}
			}
			break;
		case B:
			for(int i = 0 ; i<2 ; i++)
			{
				for(int j = 0 ; j<numRowPlayers ; j++)
				{
					if(player.equals(teamB[i][j]) == true) {
						return true;
					}
					
				}
			}
			
			break;
		}
		
		return false;
	}
	
	
	
	/**
	 * This methods receives a player configuration (i.e., team, type, row, and position), 
	 * creates a new player instance, and places him at the specified position.
	 * @param team is either Team.A or Team.B
	 * @param pType is one of the Player.Type  {Healer, Tank, Samurai, BlackMage, Phoenix}
	 * @param row	either Row.Front or Row.Back
	 * @param position is the position of the player in the row. Note that position starts from 1, 2, 3....
	 */
	//add player in each team
	public void addPlayer(Team team, Player.PlayerType pType, Row row, int position)
	{	
		//INSERT YOUR CODE HERE

		if(team.equals(Team.A)){
			if(row.equals(Row.Front)){
				teamA[0][position-1] = new Player(pType);
				teamA[0][position-1].settrp(team,row,position);
			}else if(row.equals(Row.Back)){
				teamA[1][position-1] = new Player(pType);
				teamA[1][position-1].settrp(team,row,position);
			}
		}
		if(team.equals(Team.B)){
			if(row.equals(Row.Front)){
				teamB[0][position-1] = new Player(pType);
				teamB[0][position-1].settrp(team,row,position);
			}else if(row.equals(Row.Back)){
				teamB[1][position-1] = new Player(pType);
				teamB[1][position-1].settrp(team,row,position);
			}
		}
	}
	

	
	
	/**
	 * Validate the players in both Team A and B. Returns true if all of the following conditions hold:
	 * 
	 * 1. All the positions are filled. That is, there each team must have exactly numRow*numRowPlayers players.
	 * 2. There can be at most MAXEACHTYPE players of each type in each team. For example, if MAXEACHTYPE = 3
	 * then each team can have at most 3 Healers, 3 Tanks, 3 Samurais, 3 BlackMages, and 3 Phoenixes.
	 * 
	 * Returns true if all the conditions above are satisfied, false otherwise.
	 * @return
	 */
		//check player from all the position are filled and not more than 3
	public boolean validatePlayers()
	{
		 int CountHealerA = 0, CountTankA = 0 ,CountSamuraiA = 0,  CountBlackMageA = 0,   CountPhoenixA = 0  ,CountCherryA = 0;
		  
		 int CountHealerB = 0, CountTankB = 0 ,CountSamuraiB = 0,  CountBlackMageB = 0,   CountPhoenixB = 0  ,CountCherryB = 0;
		  
		  
		  
		  for(int i = 0 ; i < 2 ; i++)
		  {
		   for(int j = 0 ; j < numRowPlayers ; j++)
		   {
		    if(teamA[i][j] == null)
		    {
		     return false;
		    }
		    else
		    {
		     switch(teamA[i][j].getType())
		     {
		     case Healer:
		      CountHealerA++;
		      break;
		   
		     case Tank:
		      CountTankA++;
		      break;
		      
		     case Samurai:
		      CountSamuraiA++;
		      break;
		      
		     case BlackMage:
		      CountBlackMageA++;
		      break;
		      
		     case Phoenix:
		      CountPhoenixA++;
		      break;
		      
		     case Cherry:
		      CountCherryA++;
		      break;
		      
		     default:
		      break;
		      
		     }
		    }
		   }
		  }
		  
		  for(int i = 0 ; i < 2 ; i++)
		  {
		   for(int j = 0 ; j < numRowPlayers ; j++)
		   {
		    if(teamB[i][j] == null)
		    {
		     return false;
		    }
		    else
		    {
		     switch(teamB[i][j].getType())
		     {
		     case Healer:
			      CountHealerB++;
			      break;
			   
			     case Tank:
			      CountTankB++;
			      break;
			      
			     case Samurai:
			      CountSamuraiB++;
			      break;
			      
			     case BlackMage:
			      CountBlackMageB++;
			      break;
			      
			     case Phoenix:
			      CountPhoenixB++;
			      break;
			      
			     case Cherry:
			      CountCherryB++;
			      break;
			      
			     default:
			      break;
		      
		     }
		    }
		   }
		  }
		  
		  if(CountHealerA > MAXEACHTYPE || CountTankA > MAXEACHTYPE || CountSamuraiA > MAXEACHTYPE || CountBlackMageA > MAXEACHTYPE || CountPhoenixA > MAXEACHTYPE || CountCherryA > MAXEACHTYPE || CountHealerB > MAXEACHTYPE || CountTankB > MAXEACHTYPE || CountSamuraiB > MAXEACHTYPE || CountBlackMageB > MAXEACHTYPE || CountPhoenixB > MAXEACHTYPE || CountCherryB > MAXEACHTYPE)
		  {
		   return false;
		  }
		  else
		   ;
		  
		  return true;
	}
	
	
	/**
	 * Returns the sum of HP of all the players in the given "team"
	 * @param team
	 * @return
	 */
	
		//get all HP in Each team
	public static double getSumHP(Player[][] team)
	{
		double sumHP  = 0;
		for (int i = 0; i < 2; i++) {
			for(int j = 0 ; j < team[i].length ; j++){
				sumHP += team[i][j].getCurrentHP();
			}
		}
		
		return sumHP;
	}
	
	/**
	 * Return the team (either teamA or teamB) whose number of alive players is higher than the other. 
	 * 
	 * If the two teams have an equal number of alive players, then the team whose sum of HP of all the
	 * players is higher is returned.
	 * 
	 * If the sums of HP of all the players of both teams are equal, return teamA.
	 * @return
	 */
		//check team Win if teamA is higher than TeamB return TeamA , if teamB is higher than TeamA return TeamB
	public Player[][] getWinningTeam()
	{
		int countA = 0;
		int countB = 0 ;
			for(int i = 0 ;i<2;i++)//Count TeamA is Alive
			{
				for(int j = 0; j<numRowPlayers ; j++)
				{
					if(teamA[i][j].isAlive() == true)
					{
						countA++;
					}
					
				}
			}
			for(int i = 0 ;i<2;i++)//Count TeamB is Alive
			{
				for(int j = 0; j<numRowPlayers ; j++)
				{
					if(teamB[i][j].isAlive() == true)
					{
						countB++;
					}
					
				}
			}
			
			if(countA>countB)//teamA higher than team B return TeamA
			{
				System.out.println("@@@ Team A won.");
				return teamA;
			}
			else if(countA<countB)//teamB higher than team A return TeamB
			{
				System.out.println("@@@ Team B won.");
				return teamB;
			}
			else if(countA==countB)//teamA equal team B return TeamA
			{
				double bloodA = 0.0;
				double bloodB = 0.0;
				for(int i = 0 ;i<2;i++)//check blood
				{
					for(int j = 0; j<numRowPlayers ; j++)
					{
						bloodA += teamA[i][j].getCurrentHP();
						bloodB += teamB[i][j].getCurrentHP();
					}
				}
				if(bloodA>bloodB)
				{
					System.out.println("@@@ Team A won.");
					return teamA;
				}
				if(bloodA<bloodB)
				{
					System.out.println("@@@ Team B won.");
					return teamB;
				}
			}
		
		
		return teamA;
	}
	
	/**
	 * This method simulates the battle between teamA and teamB. The method should have a loop that signifies
	 * a round of the battle. In each round, each player in teamA invokes the method takeAction(). The players'
	 * turns are ordered by its position in the team. Once all the players in teamA have invoked takeAction(),
	 * not it is teamB's turn to do the same. 
	 * 
	 * The battle terminates if one of the following two conditions is met:
	 * 
	 * 1. All the players in a team has been eliminated.
	 * 2. The number of rounds exceeds MAXROUNDS
	 * 
	 * After the battle terminates, report the winning team, which is determined by getWinningTeam().
	 */
	public void startBattle()
	{
		//INSERT YOUR CODE HERE
		numRounds = 0; 
		while(numRounds <= MAXROUNDS && Alive(Team.A) && Alive(Team.B)){
			numRounds += 1;
			System.out.println("@ Round "+numRounds);
			for (int i = 0; i < teamA.length; i++) {
				for (int j = 0; j < numRowPlayers; j++) {
					teamA[i][j].takeAction(this);
				}
			}
			for (int i = 0; i < teamB.length; i++) {
				for (int j = 0; j < numRowPlayers; j++) {
					teamB[i][j].takeAction(this);
				}
			}



			
			displayArea(this, true);
			logAfterEachRound();
			
		}
		getWinningTeam();
	}
		//check player in team Alive or not
	public boolean Alive(Team team) {
		switch(team) {
		case A :
			for(int i = 0 ;i<2;i++)
			{
				for(int j = 0 ; j < numRowPlayers ; j++)
				{
					if(teamA[i][j].isAlive() ==true)
					{
						return true;
					}
					
				}
			}
			break;
		case B :
			for(int i = 0 ;i<2;i++)
			{
				for(int j = 0 ; j < numRowPlayers ; j++)
				{
					if(teamB[i][j].isAlive() ==true)
					{
						return true;
					}
					
				}
			}
			break;
		}
		return false;
	}
		//send to player
	public Player[][] getTeam(Team name){
		if(name.equals(Team.A) == true) {
			return teamA;
		}
		else if(name.equals(Team.B) == true) {
			return teamB;
		}
		return null;
	}

	
	
	
	/**
	 * This method displays the current area state, and is already implemented for you.
	 * In startBattle(), you should call this method once before the battle starts, and 
	 * after each round ends. 
	 * 
	 * @param arena
	 * @param verbose
	 */
	public static void displayArea(Arena arena, boolean verbose)
	{
		StringBuilder str = new StringBuilder();
		if(verbose)
		{
			str.append(String.format("%43s   %40s","Team A","")+"\t\t"+String.format("%-38s%-40s","","Team B")+"\n");
			str.append(String.format("%43s","BACK ROW")+String.format("%43s","FRONT ROW")+"  |  "+String.format("%-43s","FRONT ROW")+"\t"+String.format("%-43s","BACK ROW")+"\n");
			for(int i = 0; i < arena.numRowPlayers; i++)
			{
				str.append(String.format("%43s",arena.teamA[1][i])+String.format("%43s",arena.teamA[0][i])+"  |  "+String.format("%-43s",arena.teamB[0][i])+String.format("%-43s",arena.teamB[1][i])+"\n");
			}
		}
	
		str.append("@ Total HP of Team A = "+getSumHP(arena.teamA)+". @ Total HP of Team B = "+getSumHP(arena.teamB)+"\n\n");
		System.out.print(str.toString());
		
		
	}
	
	/**
	 * This method writes a log (as round number, sum of HP of teamA, and sum of HP of teamB) into the log file.
	 * You are not to modify this method, however, this method must be call by startBattle() after each round.
	 * 
	 * The output file will be tested against the auto-grader, so make sure the output look something like:
	 * 
	 * 1	47415.0	49923.0
	 * 2	44977.0	46990.0
	 * 3	42092.0	43525.0
	 * 4	44408.0	43210.0
	 * 
	 * Where the numbers of the first, second, and third columns specify round numbers, sum of HP of teamA, and sum of HP of teamB respectively. 
	 */
	private void logAfterEachRound()
	{
		try {
			Files.write(logFile, Arrays.asList(new String[]{numRounds+"\t"+getSumHP(teamA)+"\t"+getSumHP(teamB)}), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}