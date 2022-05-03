package org.ming.leetcodeoj.thought.greedy;

/**
 * 376. 摆动序列
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _376M_WiggleMaxLength {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 17, 5, 10, 13, 15, 10, 5, 16, 8};
        System.out.println(wiggleMaxLength(nums));
    }

    /**
     * 给你一个整数数组 nums ，返回 nums 中作为 摆动序列 的 最长子序列的长度
     * <p>
     * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为 摆动序列 。
     * 第一个差（如果存在的话）可能是正数或负数。仅有一个元素或者含两个不等元素的序列也视作摆动序列。
     * <p>
     * 例如，[1, 7, 4, 9, 2, 5] 是一个 摆动序列 ，因为差值 (6, -3, 5, -7, 3)是正负交替出现的。
     * <p>
     * 相反，[1, 4, 7, 2, 5]和[1, 7, 4, 5, 5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
     * 子序列 可以通过从原始序列中删除一些（也可以不删除）元素来获得，剩下的元素保持其原始顺序。
     * <p>
     * 给你一个整数数组 nums ，返回 nums 中作为 摆动序列 的 最长子序列的长度 。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,7,4,9,2,5]
     * 输出：6
     * 解释：整个序列均为摆动序列，各元素之间的差值为 (6, -3, 5, -7, 3) 。
     * 示例 2：
     * <p>
     * 输入：nums = [1,17,5,10,13,15,10,5,16,8]
     * 输出：7
     * 解释：这个序列包含几个长度为 7 摆动序列。
     * 其中一个是 [1, 17, 10, 13, 10, 16, 8] ，各元素之间的差值为 (16, -7, 3, -3, 6, -8) 。
     * 示例 3：
     * <p>
     * 输入：nums = [1,2,3,4,5,6,7,8,9]
     * 输出：2
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= nums.length <= 1000
     * 0 <= nums[i] <= 1000
     * <p>
     * <p>
     * >>>>>>>>>> 进阶：你能否用O(n) 时间复杂度完成此题 ??????????
     */


    /**
     * 动态规划
     * 输入：nums = [1,17,5,10,13,15,10,5,16,8]
     * 1, 17,  5, 10, 13, 15, 10,  5, 16,  8
     * 升   降  升  升  升   降   降  升  降
     * 降序结尾 0  0   2   2   2   2   4    4   4   6
     * 升序结尾 0  1   1   3   3   3   3    5   5   5
     * if 升 {
     * dp[i][降] = dp[i-1][降]
     * dp[i][升] = dp[i-1][降]+1
     * }
     * if 降{
     * dp[i][降] = dp[i-1][升]+1
     * dp[i][升] = dp[i-1][升]
     * }
     *
     * @param nums
     * @return
     */
    public static int wiggleMaxLength(int[] nums) {
        // 1. 定义dp数组
        // dp[i][0]表示下降, dp[i][0]表示，到当前位置，以降序结尾的摆动数组的最长子序列的长度
        // dp[i][1]表示上升, dp[i][1]表示，到当前位置，以升序结尾的摆动数组的最长子序列的长度
        int[][] dp = new int[nums.length][2];

        // 2. 确定递推公式,状态转移方程
        // dp[i][1] = dp[j][0] + 1
        // dp[i][0] = dp[j][1] + 1

        // 3. 初始状态
        dp[0][0] = 1;
        dp[0][1] = 1;
        int ans = 1;
        // 4. 确定遍历顺序，确定输出值
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                dp[i][0] = dp[i - 1][1] + 1;
                dp[i][1] = dp[i - 1][1];
            } else if (nums[i - 1] < nums[i]) {
                dp[i][1] = dp[i - 1][0] + 1;
                dp[i][0] = dp[i - 1][0];
            } else {
                dp[i][0] = dp[i - 1][0];
                dp[i][1] = dp[i - 1][1];
            }
            ans = Math.max(ans, Math.max(dp[i][0], dp[i][1]));
        }
        return ans;
    }


    /**
     * 动态规划，状态压缩
     *
     * @param nums
     * @return
     */
    public int wiggleMaxLength2(int[] nums) {
        // 滚动变量
        int len = nums.length;
        if (len < 2) {
            return len;
        }
        int up = 1, down = 1;
        for (int i = 1; i < len; i++) {
            if (nums[i] > nums[i - 1]) {
                up = Math.max(down + 1, up);
            } else if (nums[i] < nums[i - 1]) {
                down = Math.max(up + 1, down);
            }
        }
        return Math.max(up, down);
    }

    /**
     * 贪心算法
     *
     * @param nums
     * @return
     */
    public int wiggleMaxLength3(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        int prevDiff = nums[1] - nums[0];
        int ans = prevDiff != 0 ? 2 : 1;
        for (int i = 2; i < n; i++) {
            int diff = nums[i] - nums[i - 1];
            if ((diff > 0 && prevDiff <= 0) || (diff < 0 && prevDiff >= 0)) {
                ans++;
                prevDiff = diff;
            }
        }
        return ans;
    }
}
