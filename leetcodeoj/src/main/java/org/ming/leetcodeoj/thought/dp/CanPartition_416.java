package org.ming.leetcodeoj.thought.dp;

/**
 * 分割等和子集
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class CanPartition_416 {
    /**
     * 416. 分割等和子集
     * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，
     * 使得两个子集的元素和相等。
     * 示例 1：
     * 输入：nums = [1,5,11,5]
     * 输出：true
     * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
     * 示例 2：
     * 输入：nums = [1,2,3,5]
     * 输出：false
     * 解释：数组不能分割成两个元素和相等的子集。
     * 提示：
     * 1 <= nums.length <= 200
     * 1 <= nums[i] <= 100
     */
    public static void main(String[] args) {
//        int[] nums = new int[]{1, 5, 11, 5};
        int[] nums = new int[]{1, 2, 3, 8};
        System.out.println(canPartition1(nums));
    }


    /**
     * 1. 转变问题: 是否存在子集的元素和为总和的一半，也就是总和等于奇数，直接返回false
     * 2.
     *
     * @param nums
     * @return
     */
    public static boolean canPartition1(int[] nums) {
        int sum = 0, len = nums.length;
        for (int num : nums) {
            sum += num;
        }
        // 特判：如果是奇数，就不符合要求
        if ((sum & 1) == 1) {
            return false;
        }
        // 开始
        int target = sum / 2;
        // 创建二维状态数组，行：物品索引，列：容量（包括 0）
        boolean[][] dp = new boolean[len][target + 1];
        // 先填表格第 0 行，第 1 个数只能让容积为它自己的背包恰好装满
        if (nums[0] <= target) {
            dp[0][nums[0]] = true;
        }
        // 填表格后面几行
        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= target; j++) {
                // 直接从上一行先把结果抄下来，然后再修正
                dp[i][j] = dp[i - 1][j];

                if (nums[i] == j) {
                    dp[i][j] = true;
                    continue;
                }
                // 小于 容量
                if (nums[i] < j) {
                    // 状态转移方程
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }
            }
        }
        return dp[len - 1][target];
    }

    /**
     * 1. 转变问题: 是否存在子集的元素和为总和的一半，也就是总和等于奇数，直接返回false
     *
     * @param nums
     * @return
     */
    public static boolean canPartition2(int[] nums) {
        int sum = 0, len = nums.length;
        for (int num : nums) {
            sum += num;
        }
        // 特判：如果是奇数，就不符合要求
        if ((sum & 1) == 1) {
            return false;
        }
        // 开始
        int target = sum / 2;
        // 一维数组
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        if (nums[0] <= target) {
            dp[nums[0]] = true;
        }
        for (int i = 1; i < len; i++) {
            for (int j = target; j >= 0; j--) {
                if (dp[target]) {
                    return true;
                }
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[target];
    }
}
