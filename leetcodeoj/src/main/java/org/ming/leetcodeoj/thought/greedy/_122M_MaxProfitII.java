package org.ming.leetcodeoj.thought.greedy;

/**
 * 122. 买卖股票的最佳时机 II
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _122M_MaxProfitII {

    private int ans;

    public static void main(String[] args) {

    }

    /**
     * 122. 买卖股票的最佳时机 II
     * 给定一个数组 prices ，其中 prices[i] 是一支给定股票第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * 示例 1:
     * 输入: prices = [7,1,5,3,6,4]
     * 输出: 7
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     * 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
     * 示例 2:
     * 输入: prices = [1,2,3,4,5]
     * 输出: 4
     * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     * 注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
     * 示例 3:
     * 输入: prices = [7,6,4,3,1]
     * 输出: 0
     * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     * 提示：
     * 1 <= prices.length <= 3 * 104
     * 0 <= prices[i] <= 104
     */

    /**
     * 暴力递归
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }
        this.ans = 0;
        dfs(prices, 0, len, 0, ans);
        return this.ans;
    }


    /**
     * 1. 暴力递归
     *
     * @param prices 股价数组
     * @param index  当前是第几天，从 0 开始
     * @param len    股价数组长度
     * @param status 0 表示不持有股票，1表示持有股票
     * @param profit 当前收益
     */
    private void dfs(int[] prices, int index, int len, int status, int profit) {

        if (index == len) {
            this.ans = Math.max(this.ans, profit);
            return;
        }
        // 计算不持有股票
        dfs(prices, index + 1, len, status, profit);

        if (status == 0) {
            // 可以尝试转向 1，持有股票
            dfs(prices, index + 1, len, 1, profit - prices[index]);

        } else {
            // 此时 status == 1，可以尝试转向 0,不持有股票
            dfs(prices, index + 1, len, 0, profit + prices[index]);
        }
    }


    /**
     * 贪心算法
     * 由于没有设置最大的交易次数或者没有设置手续费，所以我们只要保证出现正收益的情况，就叠加计算即可
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            res += Math.max(prices[i] - prices[i - 1], 0);
        }
        return res;
    }


    /**
     * 动态规划，二维数组
     * 复杂度分析：
     * 时间复杂度：O(N)O(N)，这里 NN 表示股价数组的长度；
     * 空间复杂度：O(N)O(N)，虽然是二维数组，但是第二维是常数，与问题规模无关
     *
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }

        // [i] 表示下标为 i 的那一天（ 具有前缀性质，即考虑了之前天数的交易 ）；
        // [j] 表示下标为 i 的那一天是持有股票，还是持有现金。
        // 这里 0 表示持有现金（cash），1 表示持有股票（stock）
        int[][] dp = new int[prices.length][2];

        // 递推公式
        // 1.   状态从持有现金（cash）开始，到最后一天我们关心的状态依然是持有现金（cash）；
        // 2.   dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
        //      dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);

        // 初始化
        // 起始的时候
        //如果什么都不做，dp[0][0] = 0；
        //如果持有股票，当前拥有的现金数是当天股价的相反数，即 dp[0][1] = -prices[i]；
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        // 确定输出值 dp[len - 1][0] ,终止的时候，上面也分析了，输出 dp[len - 1][0]，因为一定有 dp[len - 1][0] > dp[len - 1][1]。
        for (int i = 1; i < prices.length; i++) {
            // 卖出股票。持有现金
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 买进，持有股票
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[prices.length - 1][0];
    }


    /**
     * 动态规划，分开设置
     * 复杂度分析：
     * 时间复杂度：O(N)O(N)，这里 NN 表示股价数组的长度；
     * 空间复杂度：O(N)O(N)，虽然是二维数组，但是第二维是常数，与问题规模无关
     *
     * @param prices
     * @return
     */
    public int maxProfit4(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }
        // cash：持有现金
        // hold：持有股票
        // 状态数组
        // 状态转移：cash → hold → cash → hold → cash → hold → cash
        // 现金
        int[] crash = new int[prices.length];
        // 持股
        int[] hold = new int[prices.length];
        crash[0] = 0;
        hold[0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            crash[i] = Math.max(crash[i - 1], hold[i - 1] + prices[i]);
            hold[i] = Math.max(hold[i - 1], crash[i - 1] - prices[i]);
        }
        return crash[prices.length - 1];
    }

    /**
     * 滚动变量，状态压缩
     * 复杂度分析：
     * 时间复杂度：O(N)，这里 NN 表示股价数组的长度；
     * 空间复杂度：O(1)，分别使用两个滚动变量，将一维数组状态优化到常数大小。
     *
     * @param prices
     * @return
     */
    public int maxProfit5(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }
        // cash：持有现金
        // hold：持有股票
        // 状态数组
        // 状态转移：
        // cash → hold → cash → hold → cash → hold → cash 或者
        // hold → cash → hold → cash → hold → cash → hold
        // 现金
        int crash = 0;
        // 持股
        int hold = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            crash = Math.max(crash, hold + prices[i]);
            hold = Math.max(hold, crash - prices[i]);
        }
        return ans;
    }

}
