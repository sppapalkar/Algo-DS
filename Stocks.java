/*
You are given an integer array prices where prices[i] is the price of a given stock on the ith day.

Design an algorithm to find the maximum profit. You may complete at most k transactions.
*/
public class Stocks {
    public int maxProfit(int k, int[] prices) {
        if(prices.length == 0)
            return 0;
        int[][] dp = new int[k+1][prices.length];
        int max = Integer.MIN_VALUE;
        int i, j;
        
        for(i = 1; i<=k; i++){
            max = Integer.MIN_VALUE;
            for(j=1; j<prices.length; j++){
                max =  Math.max(dp[i-1][j-1] - prices[j-1], max);
                dp[i][j] = Math.max(dp[i][j-1], max + prices[j]);
            }
        }
        return dp[k][prices.length-1];
    }
}
