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
    String tabs = "";
    for (int i = 0; i < t; ++i)
      tabs += "\t";
    return tabs + id + " = " + expr.toString(t) + ";" + super.toString(t);
  }
}
