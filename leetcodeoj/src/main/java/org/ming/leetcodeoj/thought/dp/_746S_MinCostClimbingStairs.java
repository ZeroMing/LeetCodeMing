package org.ming.leetcodeoj.thought.dp;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _746S_MinCostClimbingStairs {
    public static void main(String[] args) {
        int[] cost = new int[]{0, 1, 2, 2};
        System.out.println(minCostClimbingStairs(cost));

    }

    /**
     * 746. 使用最小花费爬楼梯
     * 数组的每个下标作为一个阶梯，第 i 个阶梯对应着一个非负数的体力花费值 cost[i]（下标从 0 开始）。
     * 每当你爬上一个阶梯你都要花费对应的体力值，一旦支付了相应的体力值，你就可以选择向上爬一个阶梯或者爬两个阶梯。
     * 请你找出达到楼层顶部的最低花费。在开始时，你可以选择从下标为 0 或 1 的元素作为初始阶梯
     * 示例 1：
     * 输入：cost = [10, 15, 20]
     * 输出：15
     * 解释：最低花费是从 cost[1] 开始，然后走两步即可到阶梯顶，一共花费 15
     * 示例 2：
     * 输入：cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
     * 输出：6
     * 解释：最低花费方式是从 cost[0] 开始，逐个经过那些 1 ，跳过 cost[3] ，一共花费 6
     * 提示：
     * cost 的长度范围是 [2, 1000]
     * cost[i] 将会是一个整型数据，范围为 [0, 999]
     *
     * @param cost
     * @return
     */
    public static int minCostClimbingStairs(int[] cost) {

        // 1. 确定dp数组（dp table）以及下标的含义
        int[] minCost = new int[cost.length];

        // 2. 确定推导公式
        // minCost[i] = min(minCost[i-1] + cost[i], minCost[i-2] + cost[i-1])

        // 3. dp数组如何初始化
        minCost[0] = 0;
        minCost[1] = Math.min(cost[0], cost[1]);
        // 4. 确定遍历顺序，确定输出值.
        for (int i = 2; i < cost.length; i++) {
            minCost[i] = Math.min(minCost[i - 1] + cost[i], minCost[i - 2] + cost[i - 1]);
        }

        // 5. 举例推导 dp数组
        return minCost[cost.length - 1];

    }


    public static int minCostClimbingStairs2(int[] cost) {
        //  优化：只与前两项有关，声明两个变量轮动
        // 1. 确定dp数组（dp table）以及下标的含义
        int p = 0, q = 0, temp;

        // 2. 确定推导公式
        // minCost[i] = min(minCost[i-1] + cost[i], minCost[i-2] + cost[i-1])

        // 3. dp数组如何初始化
        // 4. 确定遍历顺序，确定输出值.
        for (int i = 2; i <= cost.length; i++) {
            temp = q;
            q = Math.min(p + cost[i - 2], q + cost[i - 1]);
            p = temp;
        }

        // 5. 举例推导 dp数组
        return q;

    }
}
