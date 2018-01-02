package utils;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.text.SimpleDateFormat;
import java.util.Collection;

import com.bethecoder.ascii_table.ASCIITable;

import models.Activity;
import models.User;

public class Print
{

  public static void printUser(User obj)
  {
    
    String[] header = { "Id", "First Name", "Last Name", "Email", "Password" };
    if (obj == null)
    {
      printNoData("No Users Found");
      return;
    }

    String[][] data = new String[1][5];
    
    data[0][0] = obj.id.toString();
    data[0][1] = obj.firstName;
    data[0][2] = obj.lastName;
    data[0][3] = obj.email;
    data[0][4] = obj.password;
    
    ASCIITable.getInstance().printTable(header, data);
  }
  
  public static void printActivity(Activity activity)
  {
    String[] header = { "Id", "Type", "Location", "Distance", "Date", "Duration", "Route" };
    String[][] data = new String[1][7];

    if (activity != null)
    {
      data[0][0] = String.valueOf(activity.id);
      data[0][1] = activity.type;
      data[0][2] = activity.location;
      data[0][3] = Double.toString(activity.distance);
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy:MM:dd.HH:mm:ss");
      data[0][4] = formatter.format(activity.date);
      data[0][5] = Integer.toString(activity.duration);
      //data[0][6] = String.valueOf(activity.route);
      data[0][6] = String.valueOf(activity.getRoute());
      
      ASCIITable.getInstance().printTable(header, data);
    } else {
      printNoData("No Record Found.");
    }
  }
  
  public static void printUsers(Collection<User> users)
  {
    String[] header = { "Id", "First Name", "Last Name", "Email", "Password" };
    if (users == null || users.size() == 0)
    {
      printNoData("No Users Found");
      return;
    }
    String[][] data = new String[users.size()][5];
    int index = 0;

    for (User u : users)
    {
      data[index][0] = String.valueOf(u.id);
      data[index][1] = u.firstName;
      data[index][2] = u.lastName;
      data[index][3] = u.email;
      data[index][4] = u.password;
      index++;
    }
    ASCIITable.getInstance().printTable(header, data);
  }
  
  public static void printNoData(String msg)
  {

    String[] header = { "Message" };
    String[][] data = new String[1][1];

    data[0][0] = msg;

    ASCIITable.getInstance().printTable(header, data);
  }
  
}
