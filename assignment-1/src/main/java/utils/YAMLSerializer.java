package utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

import models.Activity;
import models.User;
import net.sourceforge.yamlbeans.YamlReader;
import net.sourceforge.yamlbeans.YamlWriter;

public class YAMLSerializer implements Serializer
{
  private Map<String, User>   Tmap      = new HashMap<>();
  private Stack stack = new Stack();
  private File file;

  public YAMLSerializer(File file)
  {
    this.file = file;
  }
  
  public void push(Object o)
  {
    stack.push(o);
  }
  
  public Object pop()
  {
    return stack.pop(); 
  }
  
  @SuppressWarnings("unchecked")
  public void read() throws Exception
  {
    YamlReader reader = null;

    try
    {
      reader = new YamlReader(new FileReader(file));
      Object object = reader.read();
      Stack tempStack = (Stack)object;
      
      //System.out.println(object);
      /*Tmap = (Map<String, User>) object;
      System.out.println(Tmap.get("User"));*/
      
      Map tempActivityIndex = (Map<String, Activity>) tempStack.pop();
      Map tempEmailIndex = (Map<String, User>) tempStack.pop();
      Map tempUserIndex = (Map<String, User>) tempStack.pop();

      Map<String, Activity> newActivityIndex = new HashMap<String, Activity>();
      Map<String, User> newEmailIndex = new HashMap<String, User>();
      Map<String, User> newUserIndex = new HashMap<String, User>();
      
      Iterator<String> oAI = tempActivityIndex.keySet().iterator();
      while(oAI.hasNext()) {
        String k = oAI.next();
        Activity act = (Activity)tempActivityIndex.get(k);
        newActivityIndex.put(String.valueOf(k), act);
      }

      Iterator<String> oEI = tempEmailIndex.keySet().iterator();
      while(oEI.hasNext()) {
        String k = oEI.next();
        User usr = (User)tempEmailIndex.get(k);
        newEmailIndex.put(k, usr);
      }

      Iterator<String> oUI = tempUserIndex.keySet().iterator();
      while(oUI.hasNext()) {
        String k = oUI.next();
        User usr = (User)tempUserIndex.get(k);
        newUserIndex.put(String.valueOf(k), usr);
      }

      stack.push(newUserIndex);
      stack.push(newEmailIndex);
      stack.push(newActivityIndex);

    }
    finally
    {
      if (reader != null)
      {
        reader.close();
      }
    }
  }

  public void write() throws Exception
  {
    YamlWriter writer = null;

    try
    {
      writer = new YamlWriter(new FileWriter(file));
      writer.write(stack);
    }
    finally
    {
      if (writer != null)
      {
        writer.close();
      }
    }
  }

}
