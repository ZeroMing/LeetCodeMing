package org.ming.leetcodeoj.thought.dp;

import java.util.Arrays;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class CoinChange {

    static int res = Integer.MAX_VALUE;


    public static void main(String[] args) {
        int[] coins = new int[]{1, 2, 5};
        System.out.println(coinChange1(coins, 10));
        // System.out.println(coinChange2(coins, 10));
        System.out.println(coinChange3(coins, 10));
    }

    /**
     * 自底向上
     * 1,2,5
     * 1, 1234, 1234
     * <p>
     * 组成金额i的表达式
     * F(i) = min(n:0...n-1) F(i-cj) + 1
     *
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange1(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[max];
        Arrays.fill(dp, max);
        dp[0] = 0;
        // 金额
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        //和大于金额
        if (dp[amount] > amount) {
            return -1;
        } else {
            return dp[amount];
        }
    }


    /**
     * 递归处理方案
     *
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange2(int[] coins, int amount) {
        if (coins.length == 0) {
            return -1;
        }

        findWay2(coins, amount, 0);

        // 如果没有任何一种硬币组合能组成总金额，返回 -1。
        if (res == Integer.MAX_VALUE) {
            return -1;
        }
        return res;
    }


    public static void findWay2(int[] coins, int amount, int count) {
        if (amount < 0) {
            return;
        }

        if (amount == 0) {
            res = Math.min(res, count);
        }

        for (int i = 0; i < coins.length; i++) {
            findWay2(coins, amount - coins[i], count + 1);
        }
    }

    /**
     * 递归处理方案 + 记忆化搜索
     * 1. 自顶向下
     * 2.
     *
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange3(int[] coins, int amount) {
        if (coins.length == 0) {
            return -1;
        }
        int[] dp = new int[amount];
        return findWay3(coins, amount, dp);
    }


    /**
     * 自顶向下
     *
     * @param coins
     * @param amount
     * @param dp
     * @return
     */
    public static int findWay3(int[] coins, int amount, int[] dp) {
        if (amount < 0) {
            return -1;
        }

        if (amount == 0) {
            return 0;
        }

        // 记忆化的处理，dp[n]用赋予了值，就不用继续下面的循环
        if (dp[amount - 1] != 0) {
            return dp[amount - 1];
        }

        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = findWay3(coins, amount - coin, dp);
            if (res >= 0 && res < min) {
                // 加1，是为了加上得到res结果的那个步骤中，兑换的一个硬币
                min = res + 1;
            }
        }

        dp[amount - 1] = (min == Integer.MAX_VALUE ? -1 : min);
        return dp[amount - 1];
    }
}
