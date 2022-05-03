package org.ming.leetcodeoj.thought.greedy;

/**
 * 55. 跳跃游戏
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _55M_CanJumpI {

    /*
    55. 跳跃游戏
给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。

数组中的每个元素代表你在该位置可以跳跃的最大长度。

判断你是否能够到达最后一个下标。



示例 1：

输入：nums = [2,3,1,1,4]
输出：true
解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
示例 2：

输入：nums = [3,2,1,0,4]
输出：false
解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。


提示：

1 <= nums.length <= 3 * 104
0 <= nums[i] <= 105
     */

    public static void main(String[] args) {
        int[] nums = new int[]{0};
        System.out.println(canJump1(nums));
        System.out.println(canJump2(nums));
    }

    /**
     * 1. 如果某一个作为 起跳点 的格子可以跳跃的距离是 3，那么表示后面 3 个格子都可以作为 起跳点
     * 2. 可以对每一个能作为 起跳点 的格子都尝试跳一次，把 能跳到最远的距离 不断更新
     * 3. 如果可以一直跳到最后，就成功了
     *
     * @param nums
     * @return
     */
    public static boolean canJump1(int[] nums) {
        if (nums == null) {
            return false;
        }
        // 前n-1个元素能够跳到的最远距离
        int lastMaxDistance = 0;
        for (int i = 0; i <= lastMaxDistance; i++) {
            //第i个元素能够跳到的最远距离
            int temp = i + nums[i];
            lastMaxDistance = Math.max(lastMaxDistance, temp);
            if (lastMaxDistance >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }


    /**
     * 动态规划：
     * 1. 设定一个dp数组，dp[i]表示在下标i处能够跳跃的最大值。
     * 2. 对于dp[i]，它等于dp[i-1]跳一格到达i处后剩余的步数，和nums[i]的最大值。因此得出状态转移方程为：dp[i]=max(dp[i-1]-1,nums[i])
     * 3. 边界条件：dp[0]=nums[0]
     * 4. 在每次循环开始，我们判断dp[i-1]是否等于0，若是，则不可能到达下标i处，因此直接返回false。循环结束后 返回true
     * 5. 因为转移状态数组dp只和前一位有关，因此可以用滚动数组简化空间复杂度
     *
     * @param nums
     * @return
     */
    public static boolean canJump2(int[] nums) {
        if (nums == null) {
            return false;
        }
        // a=dp[i-1],b=dp[i]
        int lastMaxDistance = nums[0], rightMax = 0;
        for (int i = 1; i < nums.length; i++) {
            if (lastMaxDistance == 0) {
                return false;
            } else {
                rightMax = Math.max(lastMaxDistance - 1, nums[i]);
                lastMaxDistance = rightMax;
            }

        }
        return true;
    }
}
