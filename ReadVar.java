class ReadVar extends Stmt implements Token
{
  String id;
  public ReadVar(String i)
  {
    id = i;
  }

  public String toString(int t)
  {
    String tabs = "";
    for (int i = 0; i < t; ++i)
      tabs += "\t";
    return tabs + "read " + id + ";" + super.toString(t);
  }
}
