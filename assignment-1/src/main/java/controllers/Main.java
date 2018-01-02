package controllers;

import java.io.File;
import java.util.Collection;

import utils.BinarySerializer;
import utils.JSONSerializer;
import utils.Print;
import utils.Serializer;
import utils.XMLSerializer;
import utils.YAMLSerializer;
import models.Activity;
import models.User;
import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.Shell;
import asg.cliche.ShellFactory;

import com.google.common.base.Optional;
//import com.thoughtworks.xstream.XStream;

public class Main
{
  public PacemakerAPI paceApi;
  public File datastore = null;
  public Serializer serializer = null;
  
  public Main() throws Exception
  {
    
    // Set default Serializer as XML-Serializer.
    File datastore = new File("datastore.xml");
    Serializer serializer = new XMLSerializer(datastore);
    
    // Load pacemaker with pulled data.
    paceApi = new PacemakerAPI(serializer);
    if (datastore.isFile())
    {
      paceApi.load();
    }
    // Stand by for further command.
  }
  
  // Abbreviation: cff (xml/ json/ yaml/ binary)
  @Command(description="Change output file format")
  public void ChangeFileFormat (@Param(name="new format") String newformat) throws Exception
  {
    paceApi.changefileformat(newformat);
  }

  // Load format specific user data ; Abbreviation: l
  @Command(description = "Load all users data")
  public void Load() throws Exception
  {
    paceApi.load();
    System.out.println("Data successfully loaded.");
  }
  
  // Store format specific user data ; Abbreviation: s
  @Command(description = "Store all users data")
  public void Store() throws Exception
  {
    paceApi.store();
    System.out.println("Data successfully stored.");
  }
  
  // Abbreviation: cu
  @Command(description="Create a new User")
  public void createUser (@Param(name="first name") String firstName, @Param(name="last name") String lastName, 
                          @Param(name="email")      String email,     @Param(name="password")  String password)
  {
    try {
    Print.printUser(paceApi.createUser(firstName, lastName, email, password));
    }
    catch(Exception e)
    {
      System.err.println(e.getMessage());
    }
    finally{}
  }
  
  @Command(description = "Get all users listing")
  public void getUsers()
  {
    Collection<User> users = paceApi.getUsers();
    Print.printUsers(users);
  }  
  
  @Command(description = "Get User by Email")
  public void getUser(@Param(name = "email") String email)
  {
    User user = paceApi.getUserByEmail(email);
    Print.printUser(user);
  }
  
  @Command(description = "Get User by User id")
  public void listUser(@Param(name = "id") String id)
  {
    User user = paceApi.getUserByID(id);
    Print.printUser(user);
  }
  
  @Command(description = "Get activity by activity id")
  public void listActivities(@Param(name = "id") String id)
  {
    try 
    {
        Activity activity = paceApi.getActivity(id);
        if (activity != null)
        {
        Print.printActivity(activity);
        }
        else
        {
          Print.printNoData("No Activity found for id:" + id );
        }
    }
    catch(Exception e)
    {
      System.err.println("Bad Command..");
    }
  }
  
  @Command(description="Delete a User")
  public void deleteUser (@Param(name="email") String email)
  {
    try 
    {
      Optional<User> user = Optional.fromNullable(paceApi.getUserByEmail(email));
      if (user.isPresent())
      {
        paceApi.deleteUser(user.get().id);
        getUsers();
      }
      else
      {
        Print.printNoData("No User found for email:" + email );
      }
    }
    catch(Exception e)
    {
      System.err.println("Bad Command..");
    }
  }
  
  @Command(description="Add an activity")
  public void addActivity (@Param(name="user-id")  String   id,     @Param(name="type") String type, 
                           @Param(name="location") String location, @Param(name="distance") double distance,
                           @Param(name = "date (yyyy:MM:dd.HH:mm:ss)") String date, @Param(name = "duration (HH:mm:ss)") String duration)
  {
    try 
    {
      Optional<User> user = Optional.fromNullable(paceApi.getUser(id));
      if (user.isPresent())
      {
        Activity activity = paceApi.createActivity(id, type, location, distance, date, duration);
        Print.printActivity(activity);
      }
      else { 
        Print.printNoData("No User found for id:" + id );
      }
        
    }
    catch(Exception e)
    {
      System.err.println("Activity not successfully created");
    }
  }
  
  @Command(description="Add Location to an activity")
  public void addLocation (@Param(name="activity-id")  String  id,    
                           @Param(name="latitude")     float latitude, @Param(name="longitude") float longitude)
  {
    Optional<Activity> activity = Optional.fromNullable(paceApi.getActivity(id));
    if (activity.isPresent())
    {
      paceApi.addLocation(activity.get().id, latitude, longitude);
      Print.printActivity(paceApi.getActivity(id));
    }
  }

  @Command(description = "Get all activities in sorted order")
  public void listActivities(@Param(name = "id") String Id, @Param(name = "SortBy") String sortBy)
  {
    User user = paceApi.getSortedActivities(Id, sortBy);
    //System.out.println(user);
    /*if(user != null) {
      Print.printSortedActivities(user);
    } else { 
      Print.printNoData("Either No User Found for id:"+userId+" or NO activities for this user.");
    }*/
  }
  
  public static void main(String[] args) throws Exception
  {
    Main main = new Main();
    Shell shell = ShellFactory.createConsoleShell("pm", "Welcome to pacemaker-console - ?help for instructions", main);
    shell.commandLoop();
    
    main.paceApi.store();
  }
}