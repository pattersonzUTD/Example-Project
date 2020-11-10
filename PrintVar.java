class PrintVar extends Stmt implements Token
{
  Expr expr;
  public PrintVar(Expr e)
  {
    expr = e;
  }

  public String toString(int t)
  {
    return T(t) + "print " + expr.toString(t) + ";" + super.toString(t);
  }

  public void typeCheck() throws ExampleException
  {
    FullType t = expr.typeCheck();
    if (t.isArray)
      throw new ExampleException("Error: cant print array");
  }

  public void execute()
  {
    System.out.println(expr.execute().toString());
    System.out.flush();
  }
}
