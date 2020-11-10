import java.util.Scanner;
import java.util.ArrayList;
class ReadVar extends Stmt implements Token
{
  String id;
  Expr inner;
  FullType datum;
  public ReadVar(String i)
  {
    id = i;
    inner = null;
  }

  public ReadVar(String i, Expr e)
  {
    id = i;
    inner = e;
  }

  public String toString(int t)
  {
    return T(t) + "read " + id + (inner != null ? "[" + inner.toString(t) + "]" : "") + ";" + super.toString(t);
  }

  public void typeCheck() throws ExampleException
  {
    FullType thisType = table.getType(id);
    datum = thisType;
    if (thisType == null)
      throw new ExampleException("Error: " + id + " not declared");
    if (thisType.isFunction)
      throw new ExampleException("Error: functions aren't readable");
    if (thisType.isArray)
      {
        FullType innerType = inner.typeCheck();
        if (!innerType.baseType.equals("var"))
          throw new ExampleException("Error: varf in array access");
      }
    
  }
  public void execute()
  {
    Integer i = new Integer(-1);
    if (inner != null)
      i = (Integer)inner.execute();
    Scanner inp = new Scanner(System.in);
    if (datum.baseType.equals("var"))
      {
        Integer in = new Integer(inp.nextInt());
        if (inner != null)
          ((ArrayList<Object>)datum.value).set(i,in);
        else
          datum.value = in;
      }
    else
      {
        Float in = new Float(inp.nextFloat());
        if (inner != null)
          ((ArrayList<Object>)datum.value).set(i,in);
        else
          datum.value = in;
      }
  }
}
