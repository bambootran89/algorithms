package DP;

/**
 * Subset sum problem.
 *
 * @author Algorithms Collection
 */

import java.util.ArrayList;

public class SubsetSum {
  private class Pair {
    int first;
    ArrayList<Integer> second;

    public Pair(int f, ArrayList<Integer> s) {
      first = f;
      second = s;
    }

    @Override
    public String toString() {
      return "(" + first + "," + second.toString() + ")";
    }
  }

  public Pair subsetsum(int n, int S, int s[]) {
    /*
     * a variant of the knapsack problem.
     * Can you find a subset of the gold bars whose weights add up to exactly S?
     * 
     * dp[i][j] = true if there is a subset of the gold bars i, ..., n-1 (last n-i
     * bars)
     * which weights exactly j pounds. when computing dp[i][j],
     * we need to consider all the possible values of di (the decision at step i,
     * which is whether or not to include bar i):
     * 1. add bar i to the snapsack. In this case, we need to choose a subset of the
     * bars i+1 ... n-1
     * that weights exactly j - si pounds. dp[i+1][j-si] indicates whether such a
     * subset exists.
     * 2. don't add item i to the knapsack. In this case, the solution rests on a
     * subset of the bars
     * i+1 ... n-1 that weights exactly j pounds. the answer of whether such a
     * subset exists
     * is in dp[i+1][j].
     * 
     * dp[i][j] = max(dp[i+1][j], dp[i+1][j-si] if j> si)
     * 
     */

    /*
     * Solve problem
     * 
     */

    int[][] dp = new int[n + 1][S + 1];
    for (int i = n; i >= 0; i--) {
      for (int j = 0; j <= S; j++) {
        if (i == n) {
          if (j == 0) {
            dp[i][j] = 1;
          } else {
            dp[i][j] = 0;
          }
        } else {
          int[] choices = new int[2];
          choices[0] = dp[i + 1][j];
          if (j >= s[i]) {
            choices[1] = dp[i + 1][j - s[i]];
          }
          dp[i][j] = Integer.max(choices[0], choices[1]);
        }
      }

    }

    /*
     * back track
     */

    ArrayList<Integer> selects = new ArrayList<>();

    int ss = S;
    if (dp[0][S] == 0)
      return new Pair(dp[0][S], selects);
    for (int i = 1; i <= n; i++) {
      if (dp[i][ss] == 0) {
        selects.add(s[i - 1]);
        ss -= s[i - 1];
      }
    }

    return new Pair(dp[0][S], selects);
  }

  public static void main(String[] args) {
    SubsetSum sss = new SubsetSum();

    int s[] = new int[] { 12, 1, 3, 8, 20, 50 };
    int S = 44;
    int n = s.length;

    System.out.println("Being able to find a subset bars whose "
        + "weights add up to exactly " + S + " ? " + sss.subsetsum(n, S, s));
  }

}
