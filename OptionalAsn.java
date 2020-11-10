class OptionalAsn extends ExampleToken implements Token
{
  Expr expr;
  boolean exists;
  public OptionalAsn(Expr e)
  {
    expr = e;
    exists = true;
  }
  public OptionalAsn()
  {
    exists = false;
  }
  public String toString(int t)
  {
    if (!exists)
      return "";
    return " = " + expr.toString(t);
  }
  public FullType typeCheck() throws ExampleException
  {
    if (!exists)
      return null;
    return expr.typeCheck();
  }

  public Object execute()
  {
    return expr.execute();
  }
}
