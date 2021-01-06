/*
Given the array houses and an integer k. where houses[i] is the location of the ith house along a street, your task is to allocate k mailboxes in the street.

Return the minimum total distance between each house and its nearest mailbox.
*/
import java.util.*;

public class AllocateMailbox {
    int MAX = 10000000;

    public int minDistance(int[] houses, int k) {
        int n = houses.length;
        Arrays.sort(houses);
        int[][] dp = new int[n][k];
        int[][] cost = new int[n][n];
		
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                int median = houses[(i + j) / 2];
                int sum = 0;
                for (int l = i; l <= j; ++l) {
                    sum += Math.abs(median - houses[l]);
                }
                cost[i][j] = sum;
            }
        }
        for (int i = 0; i < n; ++i) Arrays.fill(dp[i], -1);
        return solve(houses, k, 0, 0, dp, cost);
    }

    public int solve(int[] houses, int k, int pos, int curK, int[][] dp, int[][] cost) {
        if (pos == houses.length) {
            if (curK == k) {
                return 0;
            }
            return MAX;
        }
        if (curK == k) return MAX;
        if (dp[pos][curK] != -1) return dp[pos][curK];

        int answer = MAX;
        for (int i = pos; i < houses.length; ++i) {
            answer = Math.min(answer, solve(houses, k, i + 1, curK + 1, dp, cost) + cost[pos][i]);
        }

        dp[pos][curK] = answer;
        return answer;
    }
    public static void main(String[] args) {
        AllocateMailbox ob = new AllocateMailbox();
        ob.minDistance(new int[]{1,4,8,10,20}, 3);
    }
}
