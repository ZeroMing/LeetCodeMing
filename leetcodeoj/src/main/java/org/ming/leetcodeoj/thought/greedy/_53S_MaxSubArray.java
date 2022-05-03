package org.ming.leetcodeoj.thought.greedy;

/**
 * 53. 最大子数组和
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _53S_MaxSubArray {

    public static void main(String[] args) {
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums));
        System.out.println(maxSubArray2(nums));
        System.out.println(maxSubArray3(nums));
    }

    /**
     * 53. 最大子数组和
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * <p>
     * 子数组 是数组中的一个连续部分。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出：6
     * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
     * 示例 2：
     * <p>
     * 输入：nums = [1]
     * 输出：1
     * 示例 3：
     * <p>
     * 输入：nums = [5,4,-1,7,8]
     * 输出：23
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= nums.length <= 105
     * -104 <= nums[i] <= 104
     * <p>
     * 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
     */

    /**
     * 超时时间限制
     *
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum = sum + nums[j];
                ans = Math.max(ans, sum);
            }
        }
        return ans;
    }


    /**
     * @param nums
     * @return
     */
    public static int maxSubArray2(int[] nums) {
        int ans = Integer.MIN_VALUE;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += nums[i];
            if (count > ans) {
                ans = count;
            }
            // 相当于重置最大子序起始位置，因为遇到负数一定是拉低总和
            if (count <= 0) {
                count = 0;
            }
        }
        return ans;
    }

    public static int maxSubArray3(int[] nums) {
        int ans = Integer.MIN_VALUE;
        int len = nums.length;
        ans = maxSubArrayHelper(nums, 0, len - 1);
        return ans;
    }

    private static int maxSubArrayHelper(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        int mid = left + (right - left) / 2;
        int lSum = maxSubArrayHelper(nums, left, mid);
        //注意这里应是mid + 1
        int rSum = maxSubArrayHelper(nums, mid + 1, right);
        int midSum = findMaxCrossingSubarray(nums, left, mid, right);
        int result = Math.max(lSum, rSum);
        result = Math.max(result, midSum);
        return result;
    }

    private static int findMaxCrossingSubarray(int[] nums, int left, int mid, int right) {
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid; i >= left; i--) {
            sum += nums[i];
            leftSum = Math.max(leftSum, sum);
        }

        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        //注意这里i = mid + 1，避免重复用到nums[i]
        for (int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            rightSum = Math.max(rightSum, sum);
        }
        return (leftSum + rightSum);
    }


    /**
     * DP解法
     *
     * @param nums
     * @return
     */
    public static int maxSubArray4(int[] nums) {
        // dp[i] = Max(dp[i-1] + nums[i],dp[i-1])
        int pre = 0, ans = nums[0];
        for (int i = 0; i < nums.length; i++) {
            pre = Math.max(pre, pre + nums[i]);
            ans = Math.max(ans, pre);
        }
        return ans;
    }

    /**
     * DP解法
     *
     * @param nums
     * @return
     */
    public static int maxSubArray5(int[] nums) {
        // dp[i] = Max(dp[i-1] + nums[i],dp[i-1])
        int ans = Integer.MIN_VALUE;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        ans = dp[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] + nums[i] > dp[i - 1]) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
