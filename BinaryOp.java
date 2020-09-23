class BinaryOp implements Token
{
  Expr lhs, rhs;
  String op;
  public BinaryOp(Expr l, String o, Expr r)
  {
    lhs = l;
    op = o;
    rhs = r;
  }

  public String toString(int t)
  {
    return lhs.toString(t) + " " + op + " " + rhs.toString(t);
  }
}
