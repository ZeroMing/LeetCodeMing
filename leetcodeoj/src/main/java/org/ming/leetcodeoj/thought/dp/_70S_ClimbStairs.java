package org.ming.leetcodeoj.thought.dp;

/**
 * 70. 爬楼梯
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _70S_ClimbStairs {

    public static void main(String[] args) {

    }

    /**
     * 70. 爬楼梯
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * <p>
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * <p>
     * 注意：给定 n 是一个正整数。
     * <p>
     * 示例 1：
     * <p>
     * 输入： 2
     * 输出： 2
     * 解释： 有两种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶
     * 2.  2 阶
     * 示例 2：
     * <p>
     * 输入： 3
     * 输出： 3
     * 解释： 有三种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶 + 1 阶
     * 2.  1 阶 + 2 阶
     * 3.  2 阶 + 1 阶
     */

    /**
     * 斐波那契数列数列
     *
     * @param n 台阶
     * @return
     */
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        // 1. 确定dp数组（dp table）以及下标的含义
        // dp[i]： 爬到第i层楼梯，有dp[i]种方法
        int[] dp = new int[n + 1];
        // 2. 确定递推公式
        // int[i][0]

        // 3. dp数组如何初始化
        dp[1] = 1;
        dp[2] = 2;

        // 4. 确定遍历顺序，确定输出值
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        // 5. 举例推导dp数组
        return dp[n];
    }


    public int climbStairs2(int n) {
        int prev1 = 0, prev2 = 0, now = 1;
        for (int i = 1; i <= n; i++) {
            prev1 = prev2;
            prev2 = now;
            now = prev1 + prev2;
        }
        return now;
    }
}
