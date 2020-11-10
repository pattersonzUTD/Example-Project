abstract class Stmt extends ExampleToken implements Token
{
  public String toString(int t)
  {
    return "\n";
  }
  public abstract void typeCheck() throws ExampleException;
  public abstract void execute();
}
