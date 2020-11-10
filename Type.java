class Type extends ExampleToken implements Token
{
  boolean isF;
  
  public Type(boolean i)
  {
    isF = i;
  }

  public String toString(int t)
  {
    if (isF)
      return "varf";
    else
      return "var";
  }
}
