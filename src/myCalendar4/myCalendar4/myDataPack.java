package myCalendar4;

import static java.util.Calendar.YEAR;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;


public class myDataPack {
	
	public class myDataLine
	{ 	String 	Name;
		long 	Wert;
		
		public myDataLine()
		{	this.Name= "    ";
			this.Wert= 0;
		}
		public void setName(String s) 	{	this.Name = s;}
		public void setWert(long w) 	{	this.Wert = w;}
		public String getName() 		{ 	return this.Name; }
		public long getWert() 			{ 	return this.Wert; }
	}

	public 	static final String HOLYDAYS_PATH 	= "./Feiertage.txt";
	
	final  	String[] 	AttributNames = { "Tage","Werktage","Wochenende", "Feiertage","Urlaub","Fortbildung", "Arbeitstage","Stunden", "Alter"};
	static 	String[] 	Holydays ;
	GregorianCalendar 	Cal;
	Date 				date;
	myDataLine[] 		dataSet = new myDataLine[8];
	static int	[]		way2go = new int[3];
	
	public static void setway2go(int x,int i)
	{ 	way2go[i]=x;}
	
	public  int getway2go(int i)
	{ 	return way2go[i];}
	
	static void calcDiff(int d, int m, int y) 
    {  
    	LocalDate first_date = LocalDate.now();   
    	LocalDate second_date = LocalDate.of(y, m, d);  
       
        Period difference = Period.between(first_date, second_date);   // Get period between the first and the second date      
        setway2go(difference.getDays(),0);
        setway2go(difference.getMonths(),1);  
        setway2go(difference.getYears(), 2);
    }   
	
	public static long BetweenDates(Date firstDate, Date secondDate) 
	{	
		long days =0L;
    	days = ChronoUnit.DAYS.between(firstDate.toInstant(), secondDate.toInstant()); 
    	return days;
	}
	
	public static Calendar CreateCalObj( String c)
	{	
		Calendar Cal= new GregorianCalendar();
		String[] tokens= c.split("\\.");
		int d = Integer.parseInt(tokens[0]);
       	int m = Integer.parseInt(tokens[1]);
       	int y = Integer.parseInt(tokens[2]);

       	Cal.set(Calendar.YEAR, y);
    	Cal.set(Calendar.MONTH, m-1);
    	Cal.set(Calendar.DAY_OF_MONTH, d);
    	return Cal;
	}
	
	public static boolean CheckWeekend(Calendar Cal)
	{
		if (Cal.get(Calendar.DAY_OF_WEEK )== Calendar.SATURDAY )
				return true;
		if (Cal.get(Calendar.DAY_OF_WEEK )== Calendar.SUNDAY )
				return true;
		return false;
	}
	
	public static int NumberOfHolydays(Calendar start,Calendar end) 
	{	
		Calendar CalObj= new GregorianCalendar();
		int holydays=0;
		
		for(int i=0;i<Holydays.length;i++)
		{ 	CalObj= CreateCalObj(Holydays[i]);
			if( CalObj.getTimeInMillis() < end.getTimeInMillis())
				if(CheckWeekend(CalObj)==false)
					holydays++;
		}
		return holydays;
	}
	
	public static int NumberOfDays(Calendar start,Calendar end, int DAY)
	{
		int numberOfDays = 0;
        while (start.before(end)) {
             if (start.get(Calendar.DAY_OF_WEEK) == DAY) 
             {	numberOfDays++;
                start.add(Calendar.DATE, 7);
             } 
             else 
             {	start.add(Calendar.DATE, 1);
             }
        }
        return numberOfDays;
	}
	
	public void Calculate(myPreferences Einstellungen)
	{
		Date heute = GregorianCalendar.getInstance().getTime();
		int saturdays, sundays, years, months, day, month, year;
		int daysBetween,businessdays,workdays,holydays,age;
		int	vacations_YE, training_YE, vacation, training;
		float x,y;
		int[] Preferences = Einstellungen.Preferences;
		
		daysBetween 	= (int) BetweenDates(heute, date);
		saturdays 		= NumberOfDays(Calendar.getInstance(),Cal,Calendar.SATURDAY);
		sundays			= NumberOfDays(Calendar.getInstance(),Cal,Calendar.SUNDAY);
		businessdays	= daysBetween-(saturdays+sundays);
		vacations_YE	= Preferences[2];
		training_YE		= Preferences[4];
		holydays		= NumberOfHolydays(Calendar.getInstance(),Cal);
		day				= Cal.get(Calendar.DAY_OF_MONTH);
		month			= Cal.get(Calendar.MONTH)+1;
		year 			= Cal.get(Calendar.YEAR);
		months 			= Cal.get(Calendar.MONTH)+1;
		years 			= (Cal.get(YEAR) - Calendar.getInstance().get(YEAR))-1 ;		
		vacation 		= years * Preferences[3] ;
		x				= (float) months/12 *Preferences[3];
		vacation		+=(int) x;
		training		= years * Preferences[5];
		y				= (float) months/12 *Preferences[5];
		training 		+=(int) y;
		workdays		= businessdays-(holydays+vacations_YE+vacation+ + training_YE+training );
		age				= year-Preferences[7];
		
		calcDiff(day, month, year);
	    dataSet[0].setWert(daysBetween);
	    dataSet[1].setWert(businessdays);
	    dataSet[2].Wert	= (saturdays+sundays);
	    dataSet[3].Wert	= holydays;
	    dataSet[4].Wert	= vacations_YE+vacation;
	    dataSet[5].Wert	= training_YE+training;
	    dataSet[6].Wert	= workdays; 
	    dataSet[7].Wert	= age;   
	}
	
	public void Print()
	{	final SimpleDateFormat format1 = new SimpleDateFormat("EEE d MMM yyyy"); 
		System.out.println("\nDatum        "  + format1.format(this.date) );
		for(int i=0;i<8;i++)
				System.out.println(dataSet[i].getName() + dataSet[i].Wert);
	}
	
	public int CountLinesFile(String filename)
	{	
		int i=0;
		try 
		{	Scanner myReader = new Scanner(new File(filename));
    		while (myReader.hasNextLine()) 
    		{	myReader.nextLine();
    			i++;
    		}
    		myReader.close();
		} 
		catch (FileNotFoundException e) 
		{	System.out.println("An error occurred reading " + filename);
    		e.printStackTrace();
		}
		return i;
	}
	
	public void ReadFile (String filename)
	{  	
		int i =0;
		Holydays = new String[CountLinesFile(filename)];
		try 
		{	File myObj = new File(filename);
		    Scanner myReader = new Scanner(myObj);
		    while (myReader.hasNextLine()) 
		    { 	Holydays[i] = myReader.nextLine();
		        i++;
		    }
		    myReader.close();
		} 
		catch (FileNotFoundException e) 
		{	System.out.println("An error occurred reading " + filename);
		    e.printStackTrace();
		}
	}
	
	public void init(myPreferences Einstellungen) 
	{
		int 	m	= Einstellungen.Preferences[0];
		int 	y 	= Einstellungen.Preferences[1];
		int 	d	= Main.endOfMonth(m,y);
		
		Cal.set(Calendar.YEAR, y);
    	Cal.set(Calendar.MONTH, m-1);
    	Cal.set(Calendar.DAY_OF_MONTH, d);
		this.date = Cal.getTime();	
	}
	
	public myDataPack(int y, int m, int d)  			//  Konstruktor
	{	
		GregorianCalendar calendar = new GregorianCalendar(y, m-1 , d);
		calendar.set(Calendar.YEAR, y);
    	calendar.set(Calendar.MONTH, m-1);
    	calendar.set(Calendar.DAY_OF_MONTH, d);
		this.Cal  = calendar;
		this.date = calendar.getTime();
	
		for(int i = 0;i<8;i++)
		{	dataSet[i]= new myDataLine();
			dataSet[i].setWert(0L);
			dataSet[i].setName(AttributNames[i]);
		}
		ReadFile(HOLYDAYS_PATH);
	}
}
