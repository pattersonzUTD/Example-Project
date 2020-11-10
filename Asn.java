class Asn extends Stmt implements Token
{
  String id;
  Expr expr;
  FullType datum;
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
    FullType thisType = table.getType(id);
    datum = thisType;
    if (thisType == null)
      throw new ExampleException("Error: " + id + " not declared");
      
    FullType exprType = expr.typeCheck();
    if (!thisType.isAble(exprType))
      throw new ExampleException("Error: incompatible types");
  }

  public void execute()
  {
    datum.value = expr.execute();
  }
}
