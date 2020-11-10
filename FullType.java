import java.util.ArrayList;
class FullType
{
  public String baseType;
  public boolean isArray;
  public int arraySize;
  public boolean isFunction;
  public ArrayList<Pair<String,FullType>> args;
  public Object value;
  public FullType(String s)
  {
    baseType = s;
    isArray = false;
    isFunction = false;
  }
  
  public boolean isAble(FullType f)
  {
    if (baseType.equals(f.baseType) &&
        isArray == f.isArray &&
        isFunction == false && f.isFunction == false)
      return true;
    if (isFunction == false && f.isFunction == false &&
        isArray == false && f.isArray == false &&
        (baseType.equals("varf") && f.baseType.equals("var")))
      return true;
    return false;
  }

  public String toString()
  {
    return baseType + (isArray?"[]":"") + (isFunction?"()":"");
  }
}
