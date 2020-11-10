class Program extends ExampleToken implements Token
{
  StmtList fnm;
  StmtList stmtList;
  public Program(StmtList f, StmtList sl)
  {
    fnm = f;
    stmtList = sl;
    table = new VarTable();
  }

  public String toString(int t)
  {
    return ("Program:\n" + fnm.toString(t+1) + stmtList.toString(t+1));
  }

  public void typeCheck() throws ExampleException
  {
    fnm.typeCheck();
    stmtList.typeCheck();
  }

  public void execute()
  {
    fnm.execute();
    stmtList.execute();
  }
}
