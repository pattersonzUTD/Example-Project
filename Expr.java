class Expr extends ExampleToken implements Token
{
  int thisId;
  BinaryOp binOp;
  int intLit;
  double floatLit;
  String id;
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

  public String toString(int t)
  {
    String ret = "";
    if (thisId == 0)
      ret = "(" + binOp.toString(t) + ")";
    else if (thisId == 1)
      ret = Integer.toString(intLit);
    else if (thisId == 2)
      ret = id;
    else
      ret = Double.toString(floatLit);
    return ret;
  }

  public String typeCheck() throws ExampleException
  {
    String ret = "";
    if (thisId == 0)
      ret = binOp.typeCheck();
    else if (thisId == 1)
      ret = "var";
    else if (thisId == 2)
      {
        String thisType = table.getType(id);
        if (thisType.equals(""))
          throw new ExampleException("Error: " + id + " not declared");
        ret = thisType;
      }
    else
      ret = "varf";
  return ret;

}
}
