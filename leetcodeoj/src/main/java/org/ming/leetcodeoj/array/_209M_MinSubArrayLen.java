package org.ming.leetcodeoj.array;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _209M_MinSubArrayLen {
    public static void main(String[] args) {
        int[] nums = new int[]{
                10, 5, 13, 4, 8, 4, 5, 11, 14, 9, 16, 10, 20, 8};
        System.out.println(minSubArrayLen(80, nums));
    }

    /**
     * 给定一个含有n个正整数的数组和一个正整数 target 。
     * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组[numsl, numsl+1, ..., numsr-1, numsr] ，
     * 并返回其长度。如果不存在符合条件的子数组，返回 0 。
     * <p>
     * 示例 1：
     * 输入：target = 7, nums = [2,3,1,2,4,3]
     * 输出：2
     * 解释：子数组[4,3]是该条件下的长度最小的子数组。
     * 示例 2：
     * <p>
     * 输入：target = 4, nums = [1,4,4]
     * 输出：1
     * 示例 3：
     * <p>
     * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
     * 输出：0
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= target <= 109
     * 1 <= nums.length <= 105
     * 1 <= nums[i] <= 105
     * <p>
     * 进阶：
     * <p>
     * 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
     */
    public static int minSubArrayLen(int target, int[] nums) {
        int len = nums.length;
        int sum = 0;
        int minLen = len + 1;
        int left = 0, right = 0;
        for (int i = 0; i < len; i++) {
            sum = sum + nums[i];
            while (sum >= target) {
                minLen = Math.min(right - left + 1, minLen);
                sum = sum - nums[left];
                left++;
            }
            right++;
        }
        return minLen > len?0:minLen;
    }

}
