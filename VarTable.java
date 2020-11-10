import java.util.ArrayList;

class VarTable
{
  ArrayList<ArrayList<Pair<String,FullType>>> table;
  public VarTable()
  {
    table = new ArrayList<ArrayList<Pair<String,FullType>>>();
    table.add(new ArrayList<Pair<String,FullType>>());
  }
  public FullType getType(String s)
  {
    for (int i = table.size()-1; i >= 0; --i)
      for (Pair<String,FullType> p : table.get(i))
      {
        if (p.getKey().equals(s))
          return p.getValue();
      }
    return null;
  }
  
  public boolean add(String id, FullType t)
  {
    for (Pair<String,FullType> p : table.get(table.size()-1))
      {
        if (p.getKey().equals(id))
          return false;
      }
    table.get(table.size()-1).add(new Pair<String,FullType>(id,t));
    
    return true;
  }
  
  public boolean addFun(String id, FullType t)
  {
    for (Pair<String,FullType> p : table.get(table.size()-2))
      {
        if (p.getKey().equals(id))
          return false;
      }
    table.get(table.size()-2).add(new Pair<String,FullType>(id,t));
    
    return true;
  }
  
  public void enterScope()
  {
    table.add( new ArrayList<Pair<String,FullType>>());
  }
  public void exitScope()
  {
    table.remove(table.size()-1);
  }
  public String toString()
  {
    String ret = "";
    String t = "";
    for (ArrayList<Pair<String,FullType>> v : table)
      {
        for (Pair<String,FullType> p : v)
          ret += t + p.getKey() + " " + p.getValue().toString() + "\n";
        t += "\t";
      }
    return ret;
  }
}
