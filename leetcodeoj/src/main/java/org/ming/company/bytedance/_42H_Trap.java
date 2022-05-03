package org.ming.company.bytedance;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _42H_Trap {
    /*

    给定n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
    示例 1：

    输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
    输出：6
    解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）

    示例 2：
    输入：height = [4,2,0,3,2,5]
    输出：9

    提示：

    n == height.length
    0 <= n <= 3 * 104
    0 <= height[i] <= 105

     */

    public static void main(String[] args) {
        int[] array1 = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
//        System.out.println(trap1(array1));

        // int[] array2 = new int[]{4, 2, 0, 3, 2, 5};
        //System.out.println(trap1(array1));
        //System.out.println(trap2(array1));
        //System.out.println(trap3(array1));
        System.out.println(trap4(array1));

    }

    /**
     * 拆分： 转为每个节点上可以接到的水滴
     * 暴力破甲
     *
     * @param height
     * @return
     */
    public static int trap1(int[] height) {
        // 循环
        int len = height.length;
        // 从1 开始
        int res = 0;
        for (int i = 1; i < len - 1; i++) {
            int lMax = 0, rMax = 0;
            // 右
            for (int r = i; r < len; r++) {
                rMax = Math.max(rMax, height[r]);
                // System.out.println(" i= "+i + " right=" + r + " rMax=" + rMax);
            }
            // 左
            for (int l = i; l >= 0; l--) {
                lMax = Math.max(lMax, height[l]);
                // System.out.println(" i= "+i + " left=" + l + " lMax=" + lMax);
            }
            // System.out.println(" i=" + i + " max=" + (Math.min(lMax,rMax) - height[i]));
            // 左右的最小值 - 当前的高度
            res += Math.min(lMax, rMax) - height[i];
        }
        return res;

    }


    /**
     * 动态规划，备忘录
     *
     * @param height
     * @return
     */
    public static int trap2(int[] height) {
        // 循环
        int len = height.length;
        // 备忘录
        int[] leftMax = new int[len];
        int[] rightMax = new int[len];
        // 初始化 base case
        leftMax[0] = height[0];
        rightMax[len - 1] = height[len - 1];
        int ans = 0;
        // 从左向右算
        for (int i = 1; i < len; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        // 从右向左算
        for (int i = len - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        // 计算答案
        for (int i = 1; i < len - 1; i++) {
            int min = Math.min(leftMax[i], rightMax[i]);
            if (min > height[i]) {
                ans = ans + (min - height[i]);
            }
        }
        return ans;
    }


    /**
     * 最终的目的是找到每个节点能接的水滴，加起来就是总和
     *
     * @param height
     * @return
     */
    public static int trap3(int[] height) {
        if (height.length == 0) {
            return 0;
        }
        // 长度
        int len = height.length;
        int left = 0, right = len - 1, lmax = height[0], rmax = height[len - 1];
        int res = 0;
        // 双指针: 0 - n-1
        while (left < right) {
            lmax = Math.max(lmax, height[left]);
            rmax = Math.max(rmax, height[right]);
            // 木桶效应 -> 最低的
            if (lmax < rmax) {
                res += lmax - height[left];
                left++;
            } else {
                res += rmax - height[right];
                right--;
            }
        }
        return res;
    }


    /**
     * 双指针，
     *
     * @param height
     * @return
     */
    public static int trap31(int[] height) {
        int sum = 0;
        int max_left = 0;
        int max_right = 0;
        int left = 1;
        // 加右指针进去
        int right = height.length - 2;
        for (int i = 1; i < height.length - 1; i++) {
            // 从左到右更
            if (height[left - 1] < height[right + 1]) {
                max_left = Math.max(max_left, height[left - 1]);
                int min = max_left;
                if (min > height[left]) {
                    sum = sum + (min - height[left]);
                }
                left++;

            } else {
                // 从右到左更
                max_right = Math.max(max_right, height[right + 1]);
                int min = max_right;
                if (min > height[right]) {
                    sum = sum + (min - height[right]);
                }
                right--;
            }
        }
        return sum;
    }

    /**
     * 单调栈解法
     *
     * @param height
     * @return
     */
    public static int trap4(int[] height) {
        if (height.length == 0) {
            return 0;
        }
        int sum = 0;
        // 栈存数组下标，方便计算
        Deque<Integer> stack = new LinkedList<>();
        int len = height.length;
        int current = 0;
        // 遍历
        while (current < len) {
            // 大于栈顶元素，破坏了单调栈
            while (!stack.isEmpty() && height[current] > height[stack.peek()]) {
                // 取出要出栈的元素
                int h = height[stack.peek()];
                // 出栈
                stack.pop();
                // 说明只有一个元素存在，直接返回，单调递减栈
                if (stack.isEmpty()) {
                    break;
                }
                // 两堵墙之前的距离
                int distance = current - stack.peek() - 1;
                int min = Math.min(height[stack.peek()], height[current]);
                sum = sum + distance * (min - h);
            }
            // push单调栈
            stack.push(current);
            current++;
        }
        return sum;
    }

}
