class Program extends ExampleToken implements Token
{
  StmtList stmtList;
  public Program(StmtList sl)
  {
    stmtList = sl;
    table = new VarTable();
  }

  public String toString(int t)
  {
    return ("Program:\n" + stmtList.toString(t+1));
  }

  public void typeCheck() throws ExampleException
  {
    stmtList.typeCheck();
  }
}
