

public class MyDate {
	private int year;
	private int month;
	private int day;
	private int objectNumber;
	
	public static int objectCounter=0;
	public static String[] strMonths = {"January","February","March","April","May","June","July","August","September","October","November","December"};

	public MyDate() {
		year=1900;
		month=1;
		day=1;
		objectCounter++;
		objectNumber=objectCounter;
		
	}
	public MyDate(int aYear, int aMonth, int aDay)
	{
		year=aYear;
		month=aMonth;
		day=aDay;
		objectCounter++;
		objectNumber=objectCounter;
		
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public void setDate(int aYear,int aMonth,int aDay)
	{
		year = aYear;
		month=aMonth;
		day=aDay;
	}
	public int getObjectNumber() {
		return objectNumber;
	}
	public String toString(){
		return day+strMonths[month-1]+year;
	}
	public static boolean isLeapYear(int year)
	{
		if(year%4!=0)
		{
			return false;
		}
		else if(year%100==0)
		{
			return true;
		}
		else if (year%400==0) {
			return false;
		}
	else {
		return true;
	}
	}
		public MyDate nextDay()
	{	
		if(month==12 && day==31)
		{
			year=year+1;
			month=1;
			day=1;
		}
		else {
			if(month ==4 || month==6 || month==9 || month==11)
		{
			if(day==30)
			{
				month=month+1;
				day=1;
			}
			else 
				day=day+1;
		}
		else if(month!=2)
		{
			if(day==31) {
				month=month+1;
			day=1;
			}
			else {
				day=day+1;
		}
		}
		else {
			if(isLeapYear(year)&& day ==29)
			{
				month=month+1;
				day=1;
			}
			else if(isLeapYear(year) && day ==28)
			{
				day=29;
			}
			else if(!isLeapYear(year)&&day==28)
			{
				month=month+1;
				day=1;
			}
			else day=day+1;
		}
			
	}
		return this;
	}
		public MyDate nextMonth()
		{
			if(month==12)
			{
				month=1;
				year++;
			}else if((month==3||month==6||month==9||month==11)&&day==30)
			{month=month+1;
			day=31;
			}
			else if(month==1&&day==31)
			{
				if(isLeapYear(year))
				{
					month=month+1;
					day=29;
				
				}
				else if(isLeapYear(year))
				{
					month=month+1;
					day=28;
				
				}
			}else 
				month=month+1;
			return this;
		}
		public MyDate nextYear()
		{
			year=year+1;
			return this;
		}
		public MyDate previousDay()
		{
			if(month==1&&day==1)
			{
				year=year-1;
				month=12;
				day=31;
			}
			else {
				if(month==5||month==7||month==10||month==12)
				{
					if(day==1)
					{
						month=month-1;
						day=30;
					}
				
					else
					{
						day=day-1;
					}
				}
				
					else if(month!=3)
					{
						if (day==1)
								{
							month=month-1;
							day=31;
								}
						else {
							day=day-1;
							
						}
						}
						else {
							if(isLeapYear(year)&&day==1)
							{
								month=month-1;
								day=29;
							}
							else if((!isLeapYear(year))&&day==1) {
								month=month-1;
							day=28;
							}
							else 
							{
								day=1;
							}
						}
						}
			return this;	
		}
		public MyDate previousMonth()
		{
			if(month==1)
			{
				month=12;
				year=year-1;
			}else if((month==5||month==7||month==10||month==12)&day==31) {
				month--;
			day=30;
		} else if(month==3&day==31)
		{
			if(isLeapYear(year))
			{
				month--;
				day=29;
			}
			else if (!isLeapYear(year))
				month=month-1;
			day=28;
				
		}else month--;
		return this;
		}
		public MyDate previousYear()
		{
			if(month==2&&day==29)
			{
				day=28;
				year=year-1;
			}
			else
			{
				year=year-1;
			}
			return this;
		}
}
						
	
	