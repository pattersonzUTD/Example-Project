class BinaryOp extends ExampleToken implements Token
{
  Expr lhs, rhs;
  String op;
  public BinaryOp(Expr l, Expr r)
  {
    lhs = l;
    rhs = r;
  }
  
  public BinaryOp(Expr l, String o, Expr r)
  {
    lhs = l;
    op = o;
    rhs = r;
  }

  public void setSym(String o)
  {
    op = o;
  }

  public String toString(int t)
  {
    return lhs.toString(t) + " " + op + " " + rhs.toString(t);
  }

  public FullType typeCheck() throws ExampleException
  {
    FullType l,r;
    l = lhs.typeCheck();
    r = rhs.typeCheck();
    if (l.isArray || r.isArray || l.isFunction || r.isFunction)
      throw new ExampleException("Error bad type for binary op");
    if (l.baseType.equals("varf"))
      return l;
    return r;
  }
  public Object execute()
  {
    Object l;
    Object r;
    l = lhs.execute();
    r = rhs.execute();
    float lF, rF;
    if (l instanceof Integer)
      lF = (Integer)l;
    else
      lF = (Float)l;
    
    if (r instanceof Integer)
      rF = (Integer)r;
    else
      rF = (Float)r;
    float res;
    if (op.equals("+"))
      res = lF + rF;
    else if (op.equals("-"))
      res = lF - rF;
    else if (op.equals("*"))
      res = lF * rF;
    else
      res = lF / rF;
    if (l instanceof Float || r instanceof Float)
      return new Float(res);
    return new Integer((int)res);
  }
}
