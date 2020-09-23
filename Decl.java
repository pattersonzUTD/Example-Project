class Decl extends Stmt implements Token
{
  String id;
  OptionalAsn asn;
  public Decl(String i, OptionalAsn o)
  {
    id = i;
    asn = o;
  }

  public String toString(int t)
  {
    String tabs = "";
    for (int i = 0; i < t; ++i)
      tabs += "\t";
    return tabs + "var " + id + asn.toString(t) + ";" + super.toString(t);
  }
}
