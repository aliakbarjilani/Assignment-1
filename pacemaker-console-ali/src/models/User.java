package models;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.google.common.base.Objects;

public class User implements Serializable
{
  //static Long   counter = 0l;
  
  //public Long id;
  public String id;
  public String firstName;
  public String lastName;
  public String email;
  public String password;
  
  public Map<String, Activity> activities = new HashMap<>();
  
  public User()
  {
  }
  
  public User(String firstName, String lastName, String email, String password)
  {
    //this.id        = counter++;
    this.id = UUID.randomUUID().toString();
    this.firstName = firstName;
    this.lastName  = lastName;
    this.email     = email;
    this.password  = password;
  }
  
  @Override
  public String toString()
  {
    return toStringHelper(this).addValue(id)
                               .addValue(firstName)
                               .addValue(lastName)
                               .addValue(email)
                               .addValue(password)
                               .addValue(activities)
                               .toString();
  }
  
  @Override  
  public int hashCode()  
  {  
     return Objects.hashCode(this.id, this.lastName, this.firstName, this.email, this.password);  
  }  
  
  @Override
  public boolean equals(final Object obj)
  {
    if (obj instanceof User)
    {
      final User other = (User) obj;
      return Objects.equal(firstName,   other.firstName) 
          &&  Objects.equal(lastName,    other.lastName)
          &&  Objects.equal(email,       other.email)
          &&  Objects.equal(password,    other.password)
          &&  Objects.equal(activities,  other.activities);      
    }
    else
    {
      return false;
    }
  }
  
}