class StmtList implements Token
{
  StmtList stmtList;
  Stmt stmt;
  public StmtList(Stmt s)
  {
    stmtList = null;
    stmt = s;
  }
  public StmtList(Stmt s, StmtList sl)
  {
    stmtList = sl;
    stmt = s;
  }

  public String toString(int t)
  {
    if (stmtList != null)
      return stmt.toString(t) + stmtList.toString(t);
    else
      return stmt.toString(t);
  }
}
