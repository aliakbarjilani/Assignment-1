package models;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.google.common.base.Objects;

public class Activity implements Serializable
{ 
  public String id;
  public String type;
  public String location;
  public double distance;
  public Date date;
  public int duration;
  
  public List<Location> route = new ArrayList<>();
  
  public List<Location> getRoute() {
    return this.route;
  }
 
  public Activity()
  {
  }
  
  public Activity(String type, String location, double distance, String date, String duration)
  {
    this.id = UUID.randomUUID().toString();
    this.type      = type;
    this.location  = location;
    this.distance  = distance;
    this.date = convertStringToDate(date);
    this.duration = convertStringTimeToSeconds(duration);
  }
  
  public static Comparator<Activity> getSortedOnType()
  {
    return new Comparator<Activity>()
    {
      public int compare(Activity one, Activity two)
      {
          return one.type.compareTo(two.type);
      }
    };
  }

  public static Comparator<Activity> getSortedOnLocation()
  {
    return new Comparator<Activity>()
    {
      public int compare(Activity one, Activity two)
      {
        return one.location.compareTo(two.location);
      }
    };
  }

  public static Comparator<Activity> getSortedOnDistance()
  {
    return new Comparator<Activity>()
    {
      public int compare(Activity one, Activity two)
      {
        Double d1 = one.distance;
        Double d2 = two.distance;
        return d1.compareTo(d2);
      }
    };
  }

  public static Comparator<Activity> getSortedOnDate()
  {
    return new Comparator<Activity>()
    {
      public int compare(Activity one, Activity two)
      {
        return one.date.compareTo(two.date);
      }
    };
  }

  public static Comparator<Activity> getSortedOnDuration()
  {
    return new Comparator<Activity>()
    {
      public int compare(Activity one, Activity two)
      {
        Integer d1 = one.duration;
        Integer d2 = two.duration;
        return d1.compareTo(d2);
      }
    };
  }
  
  private Date convertStringToDate(String strdate) 
  {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy:MM:dd.HH:mm:ss");
    try {
      Date date = formatter.parse(strdate);
      return date;
    } catch (ParseException e) {
      //e.printStackTrace();
      System.out.println("Date/Time could not be parsed. For now setting the current date/time instead.");
      return new Date();
    }
  }
  
  private static int convertStringTimeToSeconds(String duration) 
  {
    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
    try {
      Date date = formatter.parse(duration);
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      return (calendar.get(Calendar.HOUR_OF_DAY)*3600
          + calendar.get(Calendar.MINUTE)*60
          + calendar.get(Calendar.SECOND));
    } catch (ParseException e) {
      //e.printStackTrace();
      System.out.println("Time could not be parsed. For now setting the current as 0.");
      return 0;
    }
  }
  
  @Override
  public boolean equals(final Object obj)
  {
    if (obj instanceof Activity)
    {
      final Activity other = (Activity) obj;
      return Objects.equal(type, other.type) 
          && Objects.equal(location,  other.location)
          && Objects.equal(distance,  other.distance)
          && Objects.equal(route,     other.route);    
    }
    else
    {
      return false;
    }
  }
  
  @Override
  public String toString()
  {
    DateFormat writeFormat = new SimpleDateFormat("yyyy:dd:MM HH:mm:ss");
    return toStringHelper(this).addValue(id)
                               .addValue(type)
                               .addValue(location)
                               .addValue(distance)
                               .addValue(date)
                               .addValue(duration)
                               .addValue(route)
                               .toString();
  }
  
  @Override  
  public int hashCode()  
  {  
     return Objects.hashCode(this.id, this.type, this.location, this.distance);  
  } 
}