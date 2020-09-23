class PrintVar extends Stmt implements Token
{
  String id;
  public PrintVar(String i)
  {
    id = i;
  }

  public String toString(int t)
  {
    String tabs = "";
    for (int i = 0; i < t; ++i)
      tabs += "\t";
    return tabs + "print " + id + ";" + super.toString(t);
  }
}
