import java.util.Calendar;
import java.util.GregorianCalendar;
/**
 * @author Zamakhshari Abd Al-Ahad (500751542)
 *
 */
public class Appointment implements Comparable<Appointment>{
	
	private Calendar date;
	private String description;
	
	/**
	 * Constructor
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @param description
	 */
	public Appointment (int year, int month, int day, int hour, int minute, String description)
	{
		this.description = description;
		this.date = new GregorianCalendar(year, month, day, hour, minute);
		
	}
	
	
	/**
	 * @return description
	 */
	public String getDescription()
	{
		return description;
	}
	/**
	 * @return date
	 */
	public Calendar getDate()
	{
		return date;
	}
	/**
	 * @return year
	 */
	public int getYear()
	{
		return date.get(Calendar.YEAR);
	}
	/**
	 * @return month
	 */
	public int getMonth()
	{
		return date.get(Calendar.MONTH);
	}
	/**
	 * @return day
	 */
	public int getDay()
	{
		return date.get(Calendar.DAY_OF_MONTH);
	}
	/**
	 * @return hour
	 */
	public int getHour()
	{
		return date.get(Calendar.HOUR_OF_DAY);
	}
	/**
	 * @return minute
	 */
	public int getMinute()
	{
		return date.get(Calendar.MINUTE);
	}
	
	
	/**
	 * sets date
	 * @param newYear
	 * @param newMonth
	 * @param newDay
	 * @param newHour
	 * @param newMinute
	 */
	public void setDate(int newYear, int newMonth, int newDay, int newHour, int newMinute)
	{
		this.date = new GregorianCalendar(newYear, newMonth, newDay, newHour, newMinute);
	}
	
	/**
	 * @return a string to be displayed as an appointment
	 */
	public String print()
	{
		return (String.format("%02d", getHour()) + ":" + String.format("%02d", getMinute()) + " " + getDescription());
	}
	
	
	
	/**
	 * @param checkYear
	 * @param checkMonth
	 * @param checkDay
	 * @param checkHour
	 * @param checkMinute
	 * @return true if appointment occurs at given date and time, false if not
	 */
	public boolean occursOn(int checkYear, int checkMonth, int checkDay, int checkHour, int checkMinute)
	{
		if (getYear() == checkYear && getMonth() == checkMonth && getDay() == checkDay && getHour() == checkHour && getMinute() == checkMinute)
		{
			return true;
		}
		
		else return false;
	}
	
	/**
	 * @param checkYear
	 * @param checkMonth
	 * @param checkDay
	 * @return true if an appointment happens on said date, false if it does not
	 */
	public boolean occursToday(int checkYear, int checkMonth, int checkDay)
	{
		if (getYear() == checkYear && getMonth() == checkMonth && getDay() == checkDay)
		{
			return true;
		}
		
		else return false;
	}
	
	
	/**
	 * Sets the overwrites the description of an Appointment
	 * @param newDescription
	 */
	public void setDescription(String newDescription)
	{
		this.description = newDescription;
	}
	
	/** Compares the date of two appointment objects
	 * @return -1 if the appointment is before the other appointment, 1 if it is after and 0 if they are at the same time
	 */
	public int compareTo(Appointment other)
	{
		if (getDate().before(other.getDate()))
		{
			return -1;
		}
		else if (getDate().after(other.getDate())) 
		{
			return 1;
		}
		else return 0;
	}

}
