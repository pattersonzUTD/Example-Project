class Decl extends Stmt implements Token
{
  Type type;
  String id;
  OptionalAsn asn;
  public Decl(Type t, String i, OptionalAsn o)
  {
    type = t;
    id = i;
    asn = o;
  }

  public String toString(int t)
  {
    return T(t) + type.toString(t) + " " + id + asn.toString(t) + ";" + super.toString(t);
  }

  public void typeCheck() throws ExampleException
  {
    boolean res = table.add(id,type.toString(0));
    if (!res)
      throw new ExampleException("Error: " + id + " can't be redeclared");

    String optType = asn.typeCheck();
    if (type.toString(0).equals("var") && optType.equals("varf"))
      throw new ExampleException("Error: cannot convert varF to var");
  }
}
