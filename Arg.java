class Arg extends ExampleToken implements Token
{
  Type type;
  String id;
  boolean isArray;
  FullType datum;
  public Arg(Type t, String i, boolean a)
  {
    id = i;
    type = t;
    isArray = a;
  }

  public String toString(int t)
  {
    return type.toString(t) + " " + id + (isArray ? "[]" : "");
  }

  public Pair<String,FullType> typeCheck() throws ExampleException
  {
    FullType ft = new FullType(type.toString(0));
    ft.isArray = isArray;
    datum = ft;
    boolean res = table.add(id,ft);
    if (!res)
      throw new ExampleException("Error: " + id + " can't be redeclared");
    
    return new Pair<String,FullType>(id,ft);
  }

  public void execute(Object o)
  {
    datum.value = o;
  }
}
