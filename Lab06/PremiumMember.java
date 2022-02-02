import java.util.ArrayList;
public class PremiumMember extends Member{
	

	private double fee ;
	private double Family_FEE = 80.00;
	private ArrayList<String> family =new ArrayList<String>();
	
	
	public PremiumMember(String email, String password,double fee) {
		super(email, password);
		this.fee = fee;

	}
	@Override
	public void printMemberInfo()
	{
		System.out.println("----PREMIUM MEMBER----");
		System.out.println("Member fee : " +fee);
		super.printMemberInfo();
		System.out.println("List of Family");
		for(int i = 0;i<family.size() ; i++)
		{
			System.out.println(family.get(i)+",");
		}
	}
	public boolean addFamily(String username)
	{
		
		if(family.size()>=2)
		{
			System.out.println("user : "+username+ "  cannot be added, the Family user is reached the limit" );
			return false;
		}
		else{
			family.add(username);
			System.out.print(username);
			System.out.println(" is added successfully");
			return true;
		}
	}
		public boolean removeFamily(String username) {
			for(int i =0;i<family.size();i++)
			{
				if(family.get(i)==username)
				{
					family.remove(username);
					System.out.print(username);
					System.out.println("   is removed successfully.");
					return true;
			}
			}
					System.out.println("user: " + username + " does not exits and cannot be removed." ); 
					return false;
				
		}
		public double getMonthlyBill()
		{
			return (fee +Family_FEE);
		}
	}
	
