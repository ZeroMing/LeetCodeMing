package org.ming.leetcodeoj.thought.greedy;

import java.util.Arrays;

/**
 * 1005. K 次取反后最大化的数组和
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _1005S_LargestSumAfterKNegations {
    public static void main(String[] args) {
        int[] nums = new int[]{-4, -2, -3};
        int k = 4;
        System.out.println(largestSumAfterKNegations(nums, k));
    }

    /**
     * 1005. K 次取反后最大化的数组和
     * <p>
     * 给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组：
     * 选择某个下标 i 并将 nums[i] 替换为 -nums[i] 。
     * 重复这个过程恰好 k 次。可以多次选择同一个下标 i 。
     * 以这种方式修改数组后，返回数组 可能的最大和 。
     * 示例 1：
     * 输入：nums = [4,2,3], k = 1
     * 输出：5
     * 解释：选择下标 1 ，nums 变为 [4,-2,3] 。
     * 示例 2：
     * 输入：nums = [3,-1,0,2], k = 3
     * 输出：6
     * 解释：选择下标 (1, 2, 2) ，nums 变为 [3,1,0,2] 。
     * 示例 3：
     * 输入：nums = [2,-3,-1,5,-4], k = 2
     * 输出：13
     * 解释：选择下标 (1, 4) ，nums 变为 [2,3,-1,5,4] 。
     * 提示：
     * 1 <= nums.length <= 104
     * -100 <= nums[i] <= 100
     * 1 <= k <= 104
     */
    public static int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);
        int lessIndex = -1;
        int count = 0;
        int sum = 0;
        // 2,-3,-1,5,-4
        // -4,-3,-1,2,5  2
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                lessIndex = i;
                // 小于k
                if (count < k) {
                    count++;
                    sum += (-nums[i]);
                } else {
                    sum += nums[i];
                }
            } else {
                sum += nums[i];
            }
        }
        // 小于k
        if (count < k) {
            int sub = k - count;
            if (sub % 2 == 0) {
                return sum;
            } else {
                // 找绝对值最小的，使得值为-1
                int min = -1;
                if (lessIndex == -1) {
                    min = Math.abs(nums[0]);
                } else if (lessIndex == nums.length - 1) {
                    min = Math.abs(nums[lessIndex]);
                } else {
                    min = Math.min(Math.abs(nums[lessIndex]), Math.abs(nums[lessIndex + 1]));
                }
                sum = sum - 2 * min;
                return sum;
            }
        } else {
            return sum;
        }
    }
}
