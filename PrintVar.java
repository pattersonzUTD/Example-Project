class PrintVar extends Stmt implements Token
{
  String id;
  public PrintVar(String i)
  {
    id = i;
  }

  public String toString(int t)
  {
    return T(t) + "print " + id + ";" + super.toString(t);
  }

  public void typeCheck() throws ExampleException
  {
    String thisType = table.getType(id);
    if (thisType.equals(""))
      throw new ExampleException("Error: " + id + " not declared");
  }
}
