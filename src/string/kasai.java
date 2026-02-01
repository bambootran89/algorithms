package string;
/**
 * Kasai LCP array algorithm.
 *
 * @author Algorithms Collection
 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class kasai {

  public static void main(String[] args) {
    String str = "banana";
    ArrayList<Integer> suffixArr = new ArrayList<>(Arrays.asList(5, 3, 1 ,0, 4, 2));
    ArrayList<Integer> lcp = kasai(str, suffixArr);
    System.out.println("suffix is " + suffixArr);
    System.out.println("lcp is " + lcp);
  }

  private static ArrayList<Integer> kasai(String str, ArrayList<Integer> suffixArr) {
    int n = suffixArr.size();
    ArrayList<Integer> lcp = new ArrayList<Integer>(Collections.nCopies(n, 0));
    ArrayList<Integer> invSuff = new ArrayList<Integer>(Collections.nCopies(n, 0));
    
    
    for (int i = 0; i< n; i++){
      invSuff.set(suffixArr.get(i), i);
    }
    

    int k = 0;
    for (int i =0; i<n; i++){
      if(invSuff.get(i) == n-1){
        k = 0;
        continue;
      }
      
      int j = suffixArr.get(invSuff.get(i) + 1);
      while (j+k<n && i+k<n && str.charAt(i+k) == str.charAt(j+k)){
        k++;
      }
      
      lcp.set(invSuff.get(i), k);
      
      if (k>0){
        k--;
      }
      
    }
    return lcp;
  }

}
