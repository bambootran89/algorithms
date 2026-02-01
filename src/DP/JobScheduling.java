package DP;

/**
 * Job scheduling with profit maximization.
 *
 * @author Algorithms Collection
 */

import java.util.Arrays;

class Job implements Comparable<Job> {
  double start;
  double end;
  double profit;

  public Job(double d, double e, double f) {
    start = d;
    end = e;
    profit = f;
  }

  @Override
  public int compareTo(Job j) {
    if (this.start > j.start)
      return 1;
    else if (this.start < j.start)
      return -1;
    else
      return 0;
  }

  @Override
  public String toString() {
    return "(" + start + "," + end + "," + profit + ")";
  }

}

public class JobScheduling {

  public double jSheduling(int n, Job[] jobs) {

    Arrays.sort(jobs);

    /* 
     * 
     */

    double[] dp = new double[n + 1];
    double[] starts = new double[n + 1];
    dp[n] = 0.0;
    starts[n] = Double.MAX_VALUE;
    for (int i = n - 1; i >= 0; i--) {
      dp[i] = jobs[i].profit;
      starts[i] = jobs[i].start;
      for (int j = i + 1; j < n; j++) {
        double profit = dp[j];
        double start = starts[j];
        if (jobs[i].end <= starts[j]) {
          profit += jobs[i].profit;
          start = jobs[i].start;
        }

        if (dp[i] < profit) {
          dp[i] = profit;
          starts[i] = start;

        }
      }
    }

    /*
     * 
     */

    System.out.println("Jobs should be selected: ");
    for (int i = 0; i < n; i++) {
      if (dp[i] != dp[i + 1]) {
        System.out.println(jobs[i]);
      }

    }

    return dp[0];

  }

  public static void main(String[] args) {
    Job[] jobs = new Job[4];
    jobs[0] = new Job(1, 2, 50);
    jobs[1] = new Job(3, 5, 20);
    jobs[2] = new Job(6, 19, 100);
    jobs[3] = new Job(2, 100, 200);

    JobScheduling jS = new JobScheduling();
    Double max = jS.jSheduling(jobs.length, jobs);
    System.out.println("max profit is " + max);

  }

}
