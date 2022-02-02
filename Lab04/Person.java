package week4;

public class Person {
	private String firstname;
	private String lastname;
	private MyDate birthday;
	
	public Person(String aFirstname , String aLastname)
	{
		firstname = aFirstname;
		lastname = aLastname;
		birthday = new MyDate();
	}
	public Person(String aFirstname , String aLastname , int aYear , int aMonth , int aDay)
	{
		firstname = aFirstname;
		lastname = aLastname;
		birthday = new MyDate(aYear , aMonth , aDay);
	}
	public int getAge(MyDate aDate)
	{
		int c = MyDate.yearDiff(birthday , aDate);
		return c;

				}
	public boolean isEligible(MyDate elecDate)
	{
		if(getAge(elecDate)>=18)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public void printPersonInfo()
	{
		System.out.println("Person: " +firstname+ " " +lastname );
		System.out.println("Birthday : " +birthday.getDay()+ " " +birthday.strMonths[birthday.getMonth()]+ " "+birthday.getYear());
	}


}

