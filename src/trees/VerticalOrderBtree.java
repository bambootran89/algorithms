package trees;
/**
 * Vertical order traversal of binary tree.
 *
 * @author Algorithms Collection
 */



import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

public class VerticalOrderBtree extends BST{
  public void printVerticalOrder()
    {
        
        TreeMap<Integer,Vector<Integer>> m = new TreeMap<>();
        int hd =0;
        getVerticalOrder(root,hd,m);
        
        Set<Integer> keys = m.keySet();
        for(Integer key: keys){
            System.out.println(key + " : " + m.get(key).toString());
            
        }
//        for (Entry<Integer, Vector<Integer>> entry : m.entrySet())
//        {
//            System.out.println(entry.getValue());
//        }
    }

  private void getVerticalOrder(Node root, int hd, 
      TreeMap<Integer, Vector<Integer>> m) {
    if (root == null)
      return;
    Vector<Integer> vec = m.get(hd);
    if (vec == null){
      vec = new Vector<Integer>();
      vec.add(root.data);
    }else{
      vec.add(root.data);
    }
    
    m.put(hd, vec);
    
    getVerticalOrder(root.left, hd-1, m);
    getVerticalOrder(root.right, hd+1, m);
  }

}
