class Asn extends Stmt implements Token
{
  String id;
  Expr expr;
  public Asn(String i, Expr e)
  {
    id = i;
    expr = e;
  }

  public String toString(int t)
  {
    return T(t) + id + " = " + expr.toString(t) + ";" + super.toString(t);
  }

  public void typeCheck() throws ExampleException
  {
    String thisType = table.getType(id);
    if (thisType.equals(""))
      throw new ExampleException("Error: " + id + " not declared");
      
    String exprType = expr.typeCheck();
    if (thisType.equals("var") && exprType.equals("varf"))
      throw new ExampleException("Error: cannot convert varF to var");
  }
}
