package utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Stack;

public class BinarySerializer implements Serializer
{

  private Stack stack = new Stack();
  private File file;

  public BinarySerializer(File file)
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
    ObjectInputStream is = null;

    try
    {
      XStream xstream = new XStream(new DomDriver());
      is = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
      stack = (Stack) is.readObject();
    }
    finally
    {
      if (is != null)
      {
        is.close();
      }
    }
  }

  public void write() throws Exception
  {
    ObjectOutputStream os = null;

    try
    {
      XStream xstream = new XStream(new DomDriver());
      os = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
      os.writeObject(stack);
    }
    finally
    {
      if (os != null)
      {
        os.close();
      }
    }
  }
  
}
