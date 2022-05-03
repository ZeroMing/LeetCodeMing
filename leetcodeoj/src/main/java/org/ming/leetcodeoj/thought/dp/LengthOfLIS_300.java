package org.ming.leetcodeoj.thought.dp;

import java.util.Arrays;

/**
 * 最长递增子序列 300
 * 在线动态规划表格:
 * https://alchemist-al.com/algorithms/longest-increasing-subsequence
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class LengthOfLIS_300 {

    /**
     * 300. 最长递增子序列
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
     * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [10,9,2,5,3,7,101,18]
     * 输出：4
     * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
     * 示例 2：
     * <p>
     * 输入：nums = [0,1,0,3,2,3]
     * 输出：4
     * 示例 3：
     * <p>
     * 输入：nums = [7,7,7,7,7,7,7]
     * 输出：1
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= nums.length <= 2500
     * -104 <= nums[i] <= 104
     * <p>
     * <p>
     * 进阶：
     * <p>
     * 你可以设计时间复杂度为 O(n2) 的解决方案吗？
     * 你能将算法的时间复杂度降低到 O(n log(n)) 吗?
     */

    public static void main(String[] args) {
        // int[] nums = new int[]{0,1,0,3,2};
        // int[] nums = new int[]{0,1,0,3,2};
        // int[] nums = new int[]{7,7,7,7,7,7,7};
        int[] nums = new int[]{0, 1, 0, 1, 3, 2, 3};
        System.out.println(lengthOfLIS1(nums));
    }

    /**
     * 单调栈
     *
     * @param nums
     * @return
     */
    public static int lengthOfLIS1(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int max = 1;
        for (int j : dp) {
            max = Math.max(max, j);
        }
        return max;
    }


    /**
     * 贪心算法 + 二分查找
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS2(int[] nums) {
        // 记录当前位置的最小的尾部元素
        int[] tails = new int[nums.length];
        int res = 0;
        for (int num : nums) {
            int i = 0, j = res;
            while (i < j) {
                int m = (i + j) / 2;
                if (tails[m] < num) {
                    i = m + 1;
                } else {
                    j = m;
                }
            }
            tails[i] = num;
            if (res == j) {
                res++;
            }
        }
        return res;
    }


    public int lengthOfLIS3(int[] nums) {

        return 0;
    }


    public int lengthOfLIS4(int[] nums) {

        return 0;
    }
}
