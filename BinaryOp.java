class BinaryOp extends ExampleToken implements Token
{
  Expr lhs, rhs;
  String op;
  public BinaryOp(Expr l, Expr r)
  {
    lhs = l;
    rhs = r;
  }
  
  public BinaryOp(Expr l, String o, Expr r)
  {
    lhs = l;
    op = o;
    rhs = r;
  }

  public void setSym(String o)
  {
    op = o;
  }

  public String toString(int t)
  {
    return lhs.toString(t) + " " + op + " " + rhs.toString(t);
  }

  public String typeCheck() throws ExampleException
  {
    String l,r;
    l = lhs.typeCheck();
    r = rhs.typeCheck();
    if (l.equals("varf") || r.equals("varf"))
      return "varf";
    return "var";
  }
  
}
