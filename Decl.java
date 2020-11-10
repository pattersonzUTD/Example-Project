import java.util.ArrayList;
class Decl extends Stmt implements Token
{
  Type type;
  String id;
  OptionalAsn asn;
  boolean isArray;
  int arraySize;
  boolean isFunction;
  ArgList argList;
  StmtList fnm;
  StmtList stmtList;
  Expr returnExpr;
  FullType datum;
  public Decl(Type t, String i, OptionalAsn o)
  {
    type = t;
    id = i;
    asn = o;
    isArray = false;
    isFunction = false;
  }

  public Decl(Type t, String i, int n)
  {
    type = t;
    id = i;
    isArray = true;
    arraySize = n;
    asn = null;
    isFunction = false;
  }

  public Decl(Type t, String i, ArgList a, StmtList f, StmtList s, Expr e)
  {
    type = t;
    id = i;
    isArray = false;
    isFunction = true;
    asn = null;
    argList = a;
    fnm = f;
    stmtList = s;
    returnExpr = e;
  }

  public Decl(String i, ArgList a, StmtList f, StmtList s)
  {
    type = null;
    id = i;
    isArray = false;
    isFunction = true;
    asn = null;
    argList = a;
    fnm = f;
    stmtList = s;
    returnExpr = null;
  }
  
  public String toString(int t)
  {
    String ret = T(t);
    if (type != null)
      ret += type.toString(t);
    else
      ret += "void";
    ret += " ";
    ret += id;
    if (asn != null)
      ret += asn.toString(t);
    if (isArray)
      ret += "[" + Integer.toString(arraySize) + "]";
    if (isFunction)
      {
        ret += "(";
        if (argList != null)
          ret += argList.toString(t);
        ret += ")\n" + T(t) + "{\n";
        if (fnm != null)
          ret += fnm.toString(t+1);
        ret += stmtList.toString(t+1);
        if (returnExpr != null)
          ret += T(t+1) + "return " + returnExpr.toString(t+1) + "\n";
        ret += T(t) + "}";
      }
    else
      ret += ";";
    ret += super.toString(t);
    return ret;
  }

  public void typeCheck() throws ExampleException
  {
    FullType t;
    if (type != null)
      t = new FullType(type.toString(0));
    else
      t = new FullType("void");
    t.isArray = isArray;
    if (isArray)
      {
        t.value = new ArrayList<Object>();
        for (int x = 0; x < arraySize; ++x)
          if (t.baseType.equals("var"))
            ((ArrayList<Object>)t.value).add(new Integer(0));
          else
            ((ArrayList<Object>)t.value).add(new Float(0));
      }
    t.isFunction = isFunction;
    if (isFunction)
      {
        table.enterScope();
        if (argList != null)
          t.args = argList.typeCheck();
        else
          t.args = new ArrayList<Pair<String,FullType>>();
        t.value = this;
      }
    if (asn != null)
      {
        FullType optType = asn.typeCheck();
        if (!t.isAble(optType))
          throw new ExampleException("Error: cannot convert varF to var");
      }
    boolean res;
    if (!isFunction)
      {
        res = table.add(id,t);
        datum = t;
      }
    else
      {
        res = table.addFun(id,t);
        if (fnm != null)
          fnm.typeCheck();
        stmtList.typeCheck();
      }
    if (!res)
      throw new ExampleException("Error: " + id + " can't be redeclared");
    if (returnExpr != null)
      {
        FullType rt = returnExpr.typeCheck();
        FullType x = new FullType(type.toString(0));
        if (!x.isAble(rt))
          throw new ExampleException("Error: bad return");
      }
    if (isFunction)
      table.exitScope();
  }

  public void execute()
  {
    if (asn != null)
      {
        datum.value = asn.execute();
      }
  }

  public Object call(ArrayList<Object> params)
  {
    argList.execute(params);
    if (fnm != null)
      fnm.execute();
    stmtList.execute();
    if (type == null)
      return null;
    else
      return returnExpr.execute();
  }
}
