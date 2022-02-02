//Name: Wasin Heesawat
//ID: 6288077
//Section 3
public class Player {

	public enum PlayerType {Healer, Tank, Samurai, BlackMage, Phoenix, Cherry}

	
	private PlayerType type; 	//Type of this player. Can be one of either Healer, Tank, Samurai, BlackMage, or Phoenix
	private double maxHP;		//Max HP of this player
	private double currentHP;	//Current HP of this player 
	private double atk;		//Attack power of this player
	private Player CursedTarget; //check cursed target 
	
	public Arena.Team team; //check TeamA or TeamB
	public Arena.Row row; // check Front or back
	public int position; // check position
	
	private int numSpecialTurns; // turn is player can use skill
	private int CurrentTurns = 0; //count turn
 	private boolean IsSleeping; //create to check sleep or not
	private boolean IsCursed; //create to check cursed or not
	private boolean IsAlive; //create to check alive or not
	private boolean IsTaunting; //create to check taunt or not

	/**
	 * Constructor of class Player, which initializes this player's type, maxHP, atk, numSpecialTurns, 
	 * as specified in the given table. It also reset the internal turn count of this player. 
	 * @param _type
	 */
	
	//setting character in Team
	public Player(PlayerType _type)
	{	
		//INSERT YOUR CODE HERE
		this.type = _type;
		atk = 0;
		maxHP = 0;
		currentHP = 0;
		IsAlive = true;
		IsTaunting = false;
		IsSleeping = false;
		IsCursed = false;
		if(this.type.equals(PlayerType.Healer))
		{
			this.maxHP = 4790;
			this.currentHP = 4790;
			this.atk = 238;
			this.numSpecialTurns = 4;
		}
		else if (this.type.equals(PlayerType.Tank))
		{
			this.maxHP = 5340;
			this.currentHP =5340;
			this.atk = 255;
			this.numSpecialTurns = 4;
		}
		else if (this.type.equals(PlayerType.Samurai))
		{
			this.maxHP = 4005;
			this.currentHP =4005;
			this.atk = 368;
			this.numSpecialTurns = 3;
		}
		else if (this.type.equals(PlayerType.BlackMage))
		{
			this.maxHP = 4175;
			this.currentHP =4175;
			this.atk = 303;
			this.numSpecialTurns = 4;
		}
		else if (this.type.equals(PlayerType.Phoenix))
		{
			this.maxHP = 4175;
			this.currentHP =4175;
			this.atk = 209;
			this.numSpecialTurns = 8;
		}
		else if (this.type.equals(PlayerType.Cherry))
		{
			this.maxHP = 3560;
			this.currentHP =3560;
			this.atk = 198;
			this.numSpecialTurns = 4;
		}
	}
	//set team , row , position
	public void settrp(Arena.Team team , Arena.Row row , int pos){
		this.team = team;
		this.row = row;
		this.position = pos;
	}
	/**
	 * Returns the current HP of this player
	 * @return
	 */
	public double getCurrentHP()
	{
		
		
		return currentHP;
	}
	
	/**
	 * Returns type of this player
	 * @return
	 */
	public Player.PlayerType getType()
	{
		//INSERT YOUR CODE HERE
		return type;
	}
	
	/**
	 * Returns max HP of this player. 
	 * @return
	 */
	public double getMaxHP()
	{
		//INSERT YOUR CODE HERE
		
		return maxHP;
	}
	
	/**
	 * Returns whether this player is sleeping.
	 * @return
	 */
	public boolean isSleeping()
	{
		if(IsSleeping)
		{
			return true;
		}
		else {
		return false ;
	}
	}
	
	/**
	 * Returns whether this player is being cursed.
	 * @return
	 */
	public boolean isCursed()
	{
		if(IsCursed)
		{
			return true;
		}
		else {
		return false ;
	}
	}
	
	/**
	 * Returns whether this player is alive (i.e. current HP > 0).
	 * @return
	 */
	public boolean isAlive()
	{
		if(IsAlive)
		{
			return true;
		}
		else {
		return false ;
	}
	}
	
	/**
	 * Returns whether this player is taunting the other team.
	 * @return
	 */
	public boolean isTaunting()
	{
		if(IsTaunting)
		{
			return true;
		}
		else {
		return false ;
	}
	}
	
	//Attack team
	public void attack(Player target)
	{	
		//INSERT YOUR CODE HERE
		target.currentHP -=this.atk;
		if(target.currentHP <=0)
		{
			target.currentHP = 0;
			target.IsAlive = false;
			target.IsCursed = false;
			target.IsSleeping = false;
			target.IsTaunting = false;

		}
		
	}
	//use specialAbility in each team
	public void useSpecialAbility(Player[][] myTeam, Player[][] theirTeam)
	{	
		switch(this.type)
		{
		case Healer :
			Player targetheal = healPy(myTeam); 
			
			if(targetheal == null )
			{
				;
			}
			double gainHP = targetheal.getMaxHP()/4;
			if(targetheal.IsCursed == true)
			{
				;
			}
			targetheal.currentHP +=gainHP;
			targetheal.currentHP = Math.min(targetheal.maxHP , targetheal.currentHP);
			System.out.println("# " +this.playerInfo()+ " Heals " +targetheal.playerInfo());
			break;
		case Tank :
			this.IsTaunting = true;
			System.out.println("# " +this.playerInfo()+ " is Taunting");
			break;
		case Samurai : 
			Player targetsmr = checkTarget(theirTeam);
			if(targetsmr != null)
			{
				attack(targetsmr);
				attack(targetsmr);
				System.out.println("# "+this.playerInfo() + " Double-Slashes "+targetsmr.playerInfo());
			}
			break;
			
		case BlackMage:
			Player targetBM = checkTarget(theirTeam);
			
			if(targetBM != null)
			{
				this.CursedTarget = targetBM;
				targetBM.IsCursed =true;
				System.out.println("# "+this.playerInfo()+ " Curses "+targetBM.playerInfo());
			}
			break;
		case Phoenix :
			Player targetPN = revivepy(myTeam);
			
			if(targetPN !=null)
			{
				targetPN.IsAlive = true;
				targetPN.currentHP = 0.30 * targetPN.maxHP;
				targetPN.CurrentTurns =0;
				System.out.println("# "+this.playerInfo()+ " Revives " +targetPN.playerInfo());
			}
			break;
		case Cherry :
			for(Player[] player : theirTeam)
			{
				for(Player play : player )
				{
					
					if(play.IsAlive)
					{
						play.IsSleeping = true;
						System.out.println("# "+this.playerInfo()+ " Feeds a Fortune Cookie to "+play.playerInfo());
					}
					else
					{
						;
					}
				}
			}
			break;
			
		}
	}
	//Function to find to heal player
	public Player healPy(Player[][] team)
	{
		Player target = null;
		double max = 101.0;
		for(int i = 0 ; i<2 ; i++)
		{
			for(Player count : team[i])
			{
				if(count.isAlive()==false)
				{
					continue;
				}
				double lowest = (count.getCurrentHP()/count.getMaxHP()*100);
				if(lowest<max)
				{
					target = count;
					max = lowest;
				}
			}
		}
		return target; 
	}
	
	//Function to check target to attack
	public static Player checkTarget(Player[][] Enermy)

	{
		Player target = null ;
		if(Tauntedhero(Enermy) != null)
		{
			target = Tauntedhero(Enermy);
		}
		else if(checkDead(Enermy) == false)
		{
			for(int i = 0 ; i<  Enermy[0].length ; i++)
			{
				if(Enermy[0][i].IsAlive)
				{
					target = Enermy[0][i];
					break;
				}
				else
				{
					;
				}
				
			}
			for(int i = 0;i <Enermy[0].length;i++)
			{
				if(Enermy[0][i].isAlive() && target.getCurrentHP() > Enermy[0][i].getCurrentHP())
				{
					target = Enermy[0][i];
				}
				else
				{
					;
				}
			}
		}
		else
		{
			for(int i = 0;i<Enermy[1].length ; i++)
			{
				if(Enermy[1][i].isAlive())
				{
					target = Enermy[1][i];
					break;
				}
				else 
				{
					;
				}
			}
			for(int i = 0 ;i<Enermy[1].length ; i++)
			{
				if(Enermy[1][i].isAlive() && target.getCurrentHP() > Enermy[1][i].getCurrentHP())
				{
					target = Enermy[1][i];
				}
				else
				{
					;
				}
			}
		}
		return target;
	}
	
	//Function to use to TauntedHero in each team
	public static Player Tauntedhero (Player[][] team)
	
	{
		Player target = null;
		boolean meettaunt = false;
		
		for(int i = 0; i< 2; i++)
		{
			for(int j = 0; j<team[i].length ; j++)
			{
			if(team[i][j].isTaunting() && team[i][j].isAlive() == true) {
				target = team[i][j];
				meettaunt =true;
				break;
			}
			else
			{
				;
			}
		}
			if(meettaunt == true)
			{
				break;
			}
			else
			{
				;
			}
		}
			return target;
	}
	
		//Function to check in front Dead or not
		public static boolean checkDead(Player[][] team)
		{
			for(int i = 0 ;i<team[0].length ; i++)
			{
			if(team[0][i].isAlive()==true)
			{
				return false;
			}
			else
			{
				;
			}
		}
			return true;
		}
		
		//Function to Revive player in each Team
		public static Player revivepy(Player[][] team)
		{
			Player target= null;
			boolean Deadfoundinteam = false;
			for(int i =0 ;i<2 ;i++)
			{
				for(int j= 0; j<team[i].length;j++)
				{
					if(team[i][j].isAlive()==false)
					{
					target = team[i][j];
					Deadfoundinteam = true;
					break;
					}
				}
				
				if(Deadfoundinteam == true)
				{
					break;
				}
					
			}
				
				
		
			return target;
		}
		

	
	
	/**
	 * This method is called by Arena when it is this player's turn to take an action. 
	 * By default, the player simply just "attack(target)". However, once this player has 
	 * fought for "numSpecialTurns" rounds, this player must perform "useSpecialAbility(myTeam, theirTeam)"
	 * where each player type performs his own special move. 
	 * @param arena
	 */
 	public void takeAction(Arena arena)
	{	
		//INSERT YOUR CODE HERE
		Player target = null;
			if(this.type == PlayerType.Tank)
			{
				this.IsTaunting = false;
			}
			if(this.type == PlayerType.BlackMage && this.CursedTarget !=null)
			{
				this.CursedTarget.IsCursed =false;
				this.CursedTarget =null;
			}
			if((!this.IsSleeping)&& this.IsAlive)
			{
				this.CurrentTurns++;
				switch(this.team)
				{
				case A:
					if(this.CurrentTurns == this.numSpecialTurns)
					{
						switch(this.type)
						{
						case Healer:
							useSpecialAbility(arena.getTeam(Arena.Team.A),arena.getTeam(Arena.Team.B));
							break;
						case Tank:
							useSpecialAbility(arena.getTeam(Arena.Team.A),arena.getTeam(Arena.Team.B));
							break;
						case Samurai :
							useSpecialAbility(arena.getTeam(Arena.Team.A),arena.getTeam(Arena.Team.B));
							break;
						case BlackMage :
							useSpecialAbility(arena.getTeam(Arena.Team.A),arena.getTeam(Arena.Team.B));
							break;
						case Phoenix :
							useSpecialAbility(arena.getTeam(Arena.Team.A),arena.getTeam(Arena.Team.B));
							break;
						case Cherry :
							useSpecialAbility(arena.getTeam(Arena.Team.A),arena.getTeam(Arena.Team.B));
							break;
							
						default : 
						break;
						}
						this.CurrentTurns = 0;
					}
					else
					{
						target = checkTarget(arena.getTeam(Arena.Team.B));
						
						if(target!=null)
						{
							attack(target);
						
							System.out.println("# "+this.playerInfo()+" Attacks "+target.playerInfo());
						}
					}
					break;
				case B : 
					if(this.CurrentTurns == this.numSpecialTurns)
					{
						switch(this.type)
						{
						case Healer:
							useSpecialAbility(arena.getTeam(Arena.Team.B),arena.getTeam(Arena.Team.A));
							break;
						case Tank:
							useSpecialAbility(arena.getTeam(Arena.Team.B),arena.getTeam(Arena.Team.A));
							break;
						case Samurai :
							useSpecialAbility(arena.getTeam(Arena.Team.B),arena.getTeam(Arena.Team.A));
							break;
						case BlackMage :
							useSpecialAbility(arena.getTeam(Arena.Team.B),arena.getTeam(Arena.Team.A));
							break;
						case Phoenix :
							useSpecialAbility(arena.getTeam(Arena.Team.B),arena.getTeam(Arena.Team.A));
							break;
						case Cherry :
							useSpecialAbility(arena.getTeam(Arena.Team.B),arena.getTeam(Arena.Team.A));
							break;
							
						default : 
						break;
						}
						this.CurrentTurns = 0;
					}
					else
					{
						target = checkTarget(arena.getTeam(Arena.Team.A));
						
						if(target!=null)
						{
							attack(target);
							System.out.println("# "+this.playerInfo()+" Attacks "+target.playerInfo());
						}
					}
					break;
						}
					}
			if(this.IsSleeping == true)
			{
				this.IsSleeping = false;
			}
			else
			{
				;
			}
			
				}
		

	
	/**
	 * This method overrides the default Object's toString() and is already implemented for you. 
	 */
	@Override
	public String toString()
	{
		return "["+this.type.toString()+" HP:"+this.currentHP+"/"+this.maxHP+" ATK:"+this.atk+"]["
				+((this.isCursed())?"C":"")
				+((this.isTaunting())?"T":"")
				+((this.isSleeping())?"S":"")
				+"]";
	
	
	}
	//print row , position , type
	private String playerInfo()
	{
		
		return this.team+"["+this.row+"]["+this.position+"] {"+this.type+"}";
	}
}
