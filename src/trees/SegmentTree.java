package trees;

/**
 * Segment Tree for range queries.
 *
 * @author Algorithms Collection
 */

import java.util.ArrayList;
import java.util.Collections;

public class SegmentTree {
  ArrayList<Integer> st;

  int getMid(int s, int e) {
    return s + (e - s) / 2;
  }

  int getSumUtil(int ss, int se, int qs, int qe, int si) {

    if (qs <= ss && qe >= se)
      return st.get(si);
    if (se < qs || ss > qe)
      return 0;
    int mid = getMid(ss, se);

    return getSumUtil(ss, mid, qs, qe, 2 * si + 1) +
        getSumUtil(mid + 1, se, qs, qe, 2 * si + 2);
  }

  void updateValueUtil(int ss, int se, int i, int diff, int si) {
    if (i < ss || i > se)
      return;

    st.set(si, st.get(si) + diff);
    if (se != ss) {
      int mid = getMid(ss, se);
      updateValueUtil(ss, mid, i, diff, 2 * si + 1);
      updateValueUtil(mid + 1, se, i, diff, 2 * si + 2);

    }
  }

  public void updateValue(int arr[], int n, int i, int new_val) {

    if (i < 0 || i > n - 1) {
      System.out.println("Invalid Input");
      return;
    }

    int diff = new_val - arr[i];
    updateValueUtil(0, n - 1, i, diff, 0);
  }

  public int getSum(int n, int qs, int qe) {
    if (qs < 0 || qe > n - 1 || qs > qe) {
      System.out.println("Invalid Output");
      return -1;
    }
    return getSumUtil(0, n - 1, qs, qe, 0);
  }

  int constructSTUtil(int arr[], int ss, int se, int si) {

    if (ss == se) {
      st.set(si, arr[ss]);
      return arr[ss];
    }
    int mid = getMid(ss, se);
    st.set(si, constructSTUtil(arr, ss, mid, 2 * si + 1) +
        constructSTUtil(arr, mid + 1, se, 2 * si + 2));

    return st.get(si);
  }

  public void constructST(int arr[], int n) {
    int x = (int) (Math.ceil((Math.log(n) / Math.log(2))));

    int max_size = 2 * (int) Math.pow(2, x) - 1;

    st = new ArrayList<>(Collections.nCopies(max_size, 0));
    constructSTUtil(arr, 0, n - 1, 0);
  }
}
