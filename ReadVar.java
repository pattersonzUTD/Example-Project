class ReadVar extends Stmt implements Token
{
  String id;
  public ReadVar(String i)
  {
    id = i;
  }

  public String toString(int t)
  {
    return T(t) + "read " + id + ";" + super.toString(t);
  }

  public void typeCheck() throws ExampleException
  {
    String thisType = table.getType(id);
    if (thisType.equals(""))
      throw new ExampleException("Error: " + id + " not declared");
  }
}
