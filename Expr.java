class Expr implements Token
{
  int thisId;
  BinaryOp binOp;
  int intLit;
  String id;
  public Expr(BinaryOp b)
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

  public String toString(int t)
  {
    String ret = "";
    if (thisId == 0)
      ret = "(" + binOp.toString(t) + ")";
    else if (thisId == 1)
      ret = Integer.toString(intLit);
    else
      ret = id;
    return ret;
  }
}
