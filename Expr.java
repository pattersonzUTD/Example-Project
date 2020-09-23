class Expr implements Token
{
  int thisId;
  Expr lhs, rhs;
  BinaryOp binOp;
  int intLit;
  String id;
  public Expr(Expr l, BinaryOp b, Expr r)
  {
    lhs = l;
    binOp = b;
    rhs = r;
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
      ret = lhs.toString(t) + " " + binOp.toString(t) + " " + rhs.toString(t);
    else if (thisId == 1)
      ret = Integer.toString(intLit);
    else
      ret = id;
    return ret;
  }
}
