package Hash;
/**
 * Subarray with distinct elements.
 *
 * @author Algorithms Collection
 */



import java.util.LinkedHashSet;
public class SubarrayDistinctElements {
  
  public static int sumoflength(int arr[], int n)
  {
      
    LinkedHashSet<Integer> s = new LinkedHashSet<>();
   
      int j = 0, ans = 0;
   
      // Fix starting point
      for (int i=0; i<n; i++)
      {
          // Find ending point for current subarray with
          // distinct elements.
          while (j < n &&  !s.contains(arr[j]))
          {
              s.add(arr[j]);
              j++;
          }
          ans += ((j - i) * (j - i + 1))/2;
          s.remove(arr[i]);
      }
   
      return ans;
  }

  public static void main(String[] args) {
    int arr[] = {1, 2, 1};
      int n = arr.length;
    System.out.println("length = " + sumoflength(arr, arr.length));

  }

}
