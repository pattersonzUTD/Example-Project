import java.util.ArrayList;
class ArgList extends ExampleToken implements Token
{
  ArgList argList;
  Arg arg;
  public ArgList(Arg a, ArgList al)
  {
    arg = a;
    argList = al;
  }

  public String toString(int t)
  {
    return arg.toString(t) + (argList != null ? " " + argList.toString(t) : "");
  }

  public ArrayList<Pair<String,FullType>> typeCheck() throws ExampleException
  {
    ArrayList<Pair<String,FullType>> list = new ArrayList<Pair<String,FullType>>();
    list.add(arg.typeCheck());
    if (argList != null)
      list.addAll(argList.typeCheck());
    return list;
  }

  public void execute(ArrayList<Object> list)
  {
    arg.execute(list.get(0));
    list.remove(0);
    if (argList != null)
      argList.execute(list);
  }
}
