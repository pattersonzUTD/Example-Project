import java.util.Vector;

class VarTable
{
  class Pair
  {
    String key, value;
    public Pair(String a, String b)
    {
      key = a;
      value = b;
    }
    public String getKey()
    {
      return key;
    }
    public String getValue()
    {
      return value;
    }
  }
  Vector<Pair> table;
  public VarTable()
  {
    table = new Vector<Pair>();
  }
  public String getType(String s)
  {
    for (Pair p : table)
      {
        if (p.getKey().equals(s))
          return p.getValue();
      }
    return "";
  }
  public boolean add(String id, String t)
  {
    for (Pair p : table)
      {
        if (p.getKey().equals(id))
          return false;
      }
    table.add(new Pair(id,t));
    return true;
  }
  
}
