package controllers;

import java.io.File;
import java.util.ArrayList;
//import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.base.Optional;
//import com.thoughtworks.xstream.XStream;
//import com.thoughtworks.xstream.io.xml.DomDriver;

import models.Activity;
import models.Location;
import models.User;
import utils.BinarySerializer;
import utils.JSONSerializer;
import utils.Print;
import utils.Serializer;
import utils.XMLSerializer;
import utils.YAMLSerializer;

public class PacemakerAPI
{
  private Map<String, User>   emailIndex      = new HashMap<>();
  private Map<String, User>   userIndex       = new HashMap<>();
  private Map<String, Activity> activitiesIndex = new HashMap<>();
  private File  datastore = null;
  private Serializer serializer;
  
  public PacemakerAPI(Serializer serializer)
  {
    this.serializer = serializer;
  }
  
  @SuppressWarnings("unchecked")
  void load() throws Exception
  {
    serializer.read();
    activitiesIndex = (Map<String, Activity>) serializer.pop();
    emailIndex      = (Map<String, User>)   serializer.pop();
    userIndex       = (Map<String, User>)   serializer.pop();
  }
  
  void store() throws Exception
  {
    serializer.push(userIndex);
    serializer.push(emailIndex);
    serializer.push(activitiesIndex);
    serializer.write(); 
  }
  
  public Collection<User> getUsers ()
  {
    return userIndex.values();
  }
  
  public void deleteUsers() 
  {
    userIndex.clear();
    emailIndex.clear();
  }
  
  public void changefileformat(String format)
  {
    switch(format) {
    case "json":
      datastore = new File("datastore.json");
      serializer = new JSONSerializer(datastore);
      break;
    case "binary":
      datastore = new File("datastore.txt");
      serializer = new BinarySerializer(datastore);
      break;
    case "yaml":
      datastore = new File("datastore.yaml");
      serializer = new YAMLSerializer(datastore);
      break;
    case "xml":
      datastore = new File("datastore.xml");
      serializer = new XMLSerializer(datastore);
      break;
    }
    System.out.println(format + " selected");
  }
  
  public User createUser(String firstName, String lastName, String email, String password) 
  {
    System.out.println("OK");
    User user = new User (firstName, lastName, email, password);
    userIndex.put(user.id, user);
    emailIndex.put(email, user);
    return user;
  }
  
  public User getUserByEmail(String email) 
  {
    System.out.println("OK");
    return emailIndex.get(email);
  }

  public User getUserByID(String id) 
  {
    System.out.println("OK");
    return userIndex.get(id);
  }
  
  // Param: User ID
  public User getUser(String id)
  {
    System.out.println("OK");
    return userIndex.get(id);
  }

  public void deleteUser(String id) 
  {
    User user = userIndex.remove(id);
    emailIndex.remove(user.email);
  }
  
  public Activity createActivity(String id, String type, String location, double distance, String date, String duration)
  {
    Activity activity = null;
    Optional<User> user = Optional.fromNullable(userIndex.get(id));
    if (user.isPresent())
    {
      activity = new Activity (type, location, distance, date, duration);
      user.get().activities.put(activity.id, activity);
      activitiesIndex.put(activity.id, activity);
    }
    return activity;
  }
  
  public Activity getActivity (String id)
  {
    return activitiesIndex.get(id);  
  }
    
  public User getSortedActivities(String id, String sortBy)
  {

    User u = userIndex.get(id);
    this.sortActivities(u, sortBy);
    if (u !=null && u.activities != null && u.activities.size() > 0)
    {
      System.out.println(u.activities.size() + "," + u.activities.get(0).id);
      
      System.out.println("Calling sortActivities");
      this.sortActivities(u, sortBy);
      
    } else {
      //System.out.println("either null or 0 size");
    }
    return u;
  }

  private void sortActivities(User u, String sortBy) 
  {
    //List<Activity> activities = new ArrayList<>(u.activities.values());
    /*try {
    List<Activity> activities = new ArrayList<>();
    for (List<Activity> value : u.activities.values())
    {                
      activities.addAll(value);
    }
    }catch (Exception e) {
      System.err.println(e);
    }
    //System.out.println(activities);

    if("type".equals(sortBy)) {
      Collections.sort(u.activities, Activity.getSortedOnType());
    } else if("date".equalsIgnoreCase(sortBy)) {
      Collections.sort(u.activities, Activity.getSortedOnDate());
    } else if("location".equalsIgnoreCase(sortBy)) {
      Collections.sort(u.activities, Activity.getSortedOnLocation());
    } else if("distance".equalsIgnoreCase(sortBy)) {
      Collections.sort(u.activities, Activity.getSortedOnDistance());
    } else if("duration".equalsIgnoreCase(sortBy)) {
      Collections.sort(u.activities, Activity.getSortedOnDuration());
    } else {
      Print.printNoData("Sort By Parameter is not correct.");
    }*/
  }
  
  public void addLocation (String id, float latitude, float longitude)
  {
    try 
    {
      Optional<Activity> activity = Optional.fromNullable(activitiesIndex.get(id));
      if (activity.isPresent())
      {
        activity.get().route.add(new Location(latitude, longitude));
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
}
