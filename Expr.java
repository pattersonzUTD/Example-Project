import java.util.ArrayList;
class Expr extends ExampleToken implements Token
{
  int thisId;
  BinaryOp binOp;
  int intLit;
  double floatLit;
  String id;
  Expr inner;
  FunCall funCall;
  FullType datum;
  public Expr( BinaryOp b)
  {
    binOp = b;
    thisId = 0;
  }
  public Expr(int i)
  {
    intLit = i;
    thisId = 1;
  }
  public Expr(String i)
  {
    id = i;
    thisId = 2;
  }
  public Expr(double f)
  {
    floatLit = f;
    thisId = 3;
  }
  public Expr(String i, Expr e)
  {
    id = i;
    inner = e;
    thisId = 4;
  }
  public Expr(FunCall f)
  {
    funCall = f;
    thisId = 5;
  }

  public String toString(int t)
  {
    String ret = "";
    if (thisId == 0)
      ret = "(" + binOp.toString(t) + ")";
    else if (thisId == 1)
      ret = Integer.toString(intLit);
    else if (thisId == 2)
      ret = id;
    else if (thisId == 3)
      ret = Double.toString(floatLit);
    else if (thisId == 4)
      ret = id + "[" + inner.toString(t) + "]";
    else
      ret = funCall.toString(t);
    return ret;
  }

  public FullType typeCheck() throws ExampleException
  {
    FullType ret;
    if (thisId == 0)
      ret = binOp.typeCheck();
    else if (thisId == 1)
      ret = new FullType("var");
    else if (thisId == 2)
      {
        FullType thisType = table.getType(id);
        datum = thisType;
        if (thisType == null)
          throw new ExampleException("Error: " + id + " not declared");
        ret = thisType;
      }
    else if (thisId == 3)
      ret = new FullType("varf");
    else if (thisId == 4)
      {
        FullType thisType = table.getType(id);
        datum = thisType;
        FullType innerType = inner.typeCheck();
        if (thisType == null || !thisType.isArray)
          throw new ExampleException("Error: " + id + " not declared");
        if (!innerType.baseType.equals("var"))
          throw new ExampleException("Error: varf in array access");
        ret = new FullType(thisType.baseType);
      }
    else
      {
        FullType thisType = funCall.typeCheck();
        if (thisType == null || thisType.baseType.equals("void"))
          throw new ExampleException("Error: void function in expression or undeclared");
        ret = thisType;
      }
  return ret;
  
  }

  public Object execute()
  {
    Object ret;
    if (thisId == 0)
      ret = binOp.execute();
    else if (thisId == 1)
      ret = new Integer(intLit);
    else if (thisId == 2)
      ret = datum.value;
    else if (thisId == 3)
      ret = new Float(floatLit);
    else if (thisId == 4)
      ret = ((ArrayList<Object>)datum.value).get((Integer)inner.execute());
    else
        ret = funCall.execute();
    return ret;

  }
}
