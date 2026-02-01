package tests;
/**
 * Test suite for lists.
 *
 * @author Algorithms Collection
 */

import List.*;


import java.util.ArrayList;
import java.util.Random;

public class TestList {
  public static void main(String[] args)
    {
        LinkedList llist = new LinkedList();
        java.util.List<Integer> list = new ArrayList<Integer>();
    Random rand = new Random();
    
    for (int i =0; i< 20; i++){
      Integer num = rand.nextInt(1000);
      llist.append(num);
      list.add(num);
    }
 
    System.out.println(llist);
 
        llist.printList();
        
        llist.deleteValue(list.get(10)); 
        System.out.println("\nLength: "+llist.length());
        System.out.println("\nLinked List after Deletion at position 10 or: " + list.get(10));
        llist.printList();
        
        llist.removeAt(0);
        System.out.println("\nLength: "+llist.length());
        llist.removeAt(3);
        System.out.println("\nLength: "+llist.length());
        System.out.println("\nLinked List after Deletion at position 0 and 3 ");
        llist.printList();
        System.out.println("\n at 0: "+llist.get(0));
        System.out.println("\n at 4: "+llist.get(4));
        System.out.println("\n at 6: "+llist.get(6));

        llist.reverse();
        System.out.println("\n reverse list ");
        llist.printList();
            
    }
}
