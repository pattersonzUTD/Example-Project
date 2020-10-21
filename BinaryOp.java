class BinaryOp implements Token
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
}
