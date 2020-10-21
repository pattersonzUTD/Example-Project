import java.util.*;

class ExampleException extends Exception
{
  String fault;
  public ExampleException(String s)
  {
    fault = s;
  }
  
  public String toString()
  {
    return fault;
  }
}
