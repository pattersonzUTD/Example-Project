class BinaryOp implements Token
{
  String op;
  public BinaryOp(String o)
  {
    op = o;
  }

  public String toString(int t)
  {
    return op;
  }
}
