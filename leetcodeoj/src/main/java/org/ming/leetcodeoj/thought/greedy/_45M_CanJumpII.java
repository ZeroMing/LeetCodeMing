package org.ming.leetcodeoj.thought.greedy;

/**
 * 45. 跳跃游戏 II
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _45M_CanJumpII {
    public static void main(String[] args) {

    }


    /*
    45. 跳跃游戏 II
给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
数组中的每个元素代表你在该位置可以跳跃的最大长度。
你的目标是使用最少的跳跃次数到达数组的最后一个位置。
假设你总是可以到达数组的最后一个位置。

示例 1:
输入: nums = [2,3,1,1,4]
输出: 2
解释: 跳到最后一个位置的最小跳跃数是 2。
     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
示例 2:
输入: nums = [2,3,0,1,4]
输出: 2

示例 2:
输入: nums = [2,3,0,1,4,6,7,8]
输出: 2

提示:

1 <= nums.length <= 104
0 <= nums[i] <= 1000
     */

    public static int jump0(int[] nums) {
        // 结尾
        int end = 0;
        // 能达到的最远的距离
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }


    /**
     * 反向查找出发位置
     *
     * @param nums
     * @return
     */
    public static int jump1(int[] nums) {
        int position = nums.length - 1;
        int steps = 0;
        while (position > 0) {
            // 找最远的一个位置，要从左往右遍历，找到第一个满足条件的位置
            for (int i = 0; i < position; i++) {
                if (i + nums[i] >= position) {
                    position = i;
                    steps++;
                    break;
                }
            }
        }
        return steps;
    }
}
