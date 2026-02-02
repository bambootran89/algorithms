package DP;
/**
 * BlackJack game strategy using DP.
 *
 * @author Algorithms Collection
 */



import java.util.Arrays;

public class BlackJack {
  
  /* https://en.wikipedia.org/wiki/Blackjack
   * This is Perfect-informatin Blackjack
   * 
   * given entire deck order: C0,C1, ..., Cn-1
   * 1-Player game against stand-on-17 dealer 
   * when should you hit or stand? Guess?
   * Goal: maximize winnings for fixed bet $1
   * 
   */
  
  
  int n;
  int[] C;
  int[] BJ_Cache;
  
  public BlackJack(int n, int[] C){
    this.n = n;
    this.C = C;
    BJ_Cache = new int[n+1];
    Arrays.fill(BJ_Cache, -1);
    
  }
  
  /* 1. subproblem BJ(i) = best play of Ci, ... , Cn-1 (remaining cards)
   * i number cards "already played" -> subproblems = n
   * 2. guess how many times plays "hits" draws another card.
   *    number of choices <= n
   * 3. recurrence BJ(i) = max(
   *                     O(n)-> outcome(1,0,-1) + BJ(i + cards used),
   *                           )
   *    time/subproblem -> O(n^2)
   * 4. order: for i in reversed(range(n))
   * total time O(n^3)
   * 5. solution BJ[0]
   * 
   */
  
  public int BJ(int i){
    
    if(BJ_Cache[i] != -1){
      return BJ_Cache[i];
    }
    
    if ((n-i) < 4){
      return 0;
    }
    
    
    int vmax = 0;
    
    for (int p = 2; p< n-i-2; p++){
      int player = 0;
      for(int k = i; k<i+p+2; k++,k++){
        player+=C[k];
      }
      if (player>21) {
        int v = -1+BJ(i+p+2);
        if (v>vmax)
          vmax = v;
      }
      int d = 2;
      int dealer = 0;
      for (; d< n-i-p; d++ ){
        dealer = 0;
        for(int k = i+1; k< i+p+d; k++,k++ ){
          dealer+=C[k];
        }
        if (dealer >= 17)
          break;
      }
      
      if (dealer > 21)
        dealer = 0;
      int score;
      if (player>dealer)
        score = 1;
      else if(player < dealer)
        score = -1;
      else 
        score = 0;
      int v = score + BJ(i+p+d);
      if(v>vmax)
        vmax = v;
    }
    
    BJ_Cache[i] = vmax;
    
    
    return vmax;
    
  }
  
  public static void main(String[] args) {
    int[] C = new int[] {6, 1, 10, 6, 7, 11, 2, 1, 7, 9, 13, 7,
                     8, 12, 6, 5, 12, 12, 11, 11, 1, 5,
                     13, 6, 12, 4, 8, 3, 2, 4, 9, 3, 11, 
                     13, 8, 4, 4, 5, 3, 2, 10, 9, 13, 2,
                     1, 8, 10, 7, 10, 5, 9, 3};
    
    BlackJack bj = new BlackJack(C.length,C);
    
    System.out.println("optimal money can win is : " + bj.BJ(0) + "$");
    //System.out.println(Arrays.toString(bj.BJ_Cache));
  }
}
