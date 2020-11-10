class FunStmt extends Stmt implements Token
{
  FunCall call;
  public FunStmt(FunCall f)
  {
    call = f;
  }

  public String toString(int t)
  {
    return T(t) + call.toString(t) + ";" + super.toString(t);
  }

  public void typeCheck() throws ExampleException
  {
    call.typeCheck();
  }

  public void execute()
  {
    call.execute();
  }
}
