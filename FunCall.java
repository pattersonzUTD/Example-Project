import java.util.ArrayList;
class FunCall extends ExampleToken implements Token
{
  String id;
  Params params;
  FullType datum;
  public FunCall(String i)
  {
    id = i;
    params = null;
  }
  public FunCall(String i, Params p)
  {
    id = i;
    params = p;
  }

  public String toString(int t)
  {
    return id + "(" + (params != null ? params.toString(t) : "") + ")";
  }

  public FullType typeCheck() throws ExampleException
  {
    FullType thisType = table.getType(id);
    datum = thisType;
    if (thisType == null || !thisType.isFunction)
      throw new ExampleException("Error: " + id + " not declared as function");
    ArrayList<FullType> ps = params.typeCheck();
    if (thisType.args.size() != ps.size())
      throw new ExampleException("Error: wrong number of arguments");
    for (int i = 0; i < thisType.args.size(); ++i)
      if (!thisType.args.get(i).getValue().isAble(ps.get(i)))
        throw new ExampleException("Error: bad param");
    
    return new FullType(thisType.baseType);
  }

  public Object execute()
  {
    return ((Decl)(datum.value)).call(params.execute());
  }
}
