class Program implements Token
{
  StmtList stmtList;
  public Program(StmtList sl)
  {
    stmtList = sl;
  }

  public String toString(int t)
  {
    return ("Program:\n" + stmtList.toString(t+1));
  }
}
