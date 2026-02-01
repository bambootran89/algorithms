package tests;
/**
 * Test suite for heap implementations.
 *
 * @author Algorithms Collection
 */

import java.util.ArrayList;
import java.util.Random;
import trees.*;

public class TestHeap {

  public static void main(String[] args) {
    
    java.util.List<Integer> list = new ArrayList<Integer>();
    Random rand = new Random();
    Heap mHeap = new MaxHeap();
    Heap minHeap = new MinHeap();
    for (int i =0; i< 20; i++){
      Integer num = rand.nextInt(1000);
      list.add(num);
      mHeap.insert(num);
      minHeap.insert(num);
    }
    
    System.out.println(list);
    System.out.print("Big to Small: ");
    while (true){
      int max = mHeap.pop();
      if (max == Integer.MIN_VALUE)
        break;
      System.out.print(max +" ");  
    }
    System.out.print("\nSmall to Big: ");
    while (true){
      int min = minHeap.pop();
      if (min == Integer.MAX_VALUE)
        break;
      System.out.print(min +" ");  
    }
    
    for (int i: list){
      mHeap.insert(i);
      minHeap.insert(i);
    }
    mHeap.modify(10, 90);
    minHeap.modify(10, 30000);
    System.out.print("\nBig to Small: ");
    while (true){
      int max = mHeap.pop();
      if (max == Integer.MIN_VALUE)
        break;
      System.out.print(max +" ");  
    }
    
    System.out.print("\nSmall to Big: ");
    while (true){
      int min = minHeap.pop();
      if (min == Integer.MAX_VALUE)
        break;
      System.out.print(min +" ");  
    }
  }

}
