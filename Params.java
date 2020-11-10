import java.util.ArrayList;

class Params extends ExampleToken implements Token
{
  Expr expr;
  Params params;
  public Params(Expr e)
  {
    expr = e;
    params = null;
  }

  public Params(Expr e, Params p)
  {
    expr = e;
    params = p;
  }

  public String toString(int t)
  {
    return expr.toString(t) + (params != null ? ", " + params.toString(t) : "");
  }

  public ArrayList<FullType> typeCheck() throws ExampleException
  {
    ArrayList<FullType> list = new ArrayList<FullType>();
    list.add(expr.typeCheck());
    if (params != null)
      list.addAll(params.typeCheck());
    return list;
  }

  public ArrayList<Object> execute()
  {
   ArrayList<Object> list = new ArrayList<Object>();
    list.add(expr.execute());
    if (params != null)
      list.addAll(params.execute());
    return list;
  }
}
