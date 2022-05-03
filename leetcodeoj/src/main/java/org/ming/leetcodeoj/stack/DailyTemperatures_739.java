package org.ming.leetcodeoj.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class DailyTemperatures_739 {

    public static void main(String[] args) {
        int[] temperatures = new int[]{73, 99, 75, 71, 69, 72, 76, 73};
        print(dailyTemperatures1(temperatures));

    }

    private static void print(int[] temperatures) {
        for (int t : temperatures) {
            System.out.print(t + " ");
        }
        System.out.println();
    }

    public static int[] dailyTemperatures1(int[] temperatures) {
        int length = temperatures.length;
        int[] ans = new int[length];
        for (int i = 0; i < length - 1; i++) {
            int dis = 0;
            boolean exit = false;
            for (int j = i + 1; j < length; j++) {
                dis++;
                if (temperatures[j] > temperatures[i]) {
                    exit = true;
                    break;
                }
            }
            if (exit) {
                ans[i] = dis;
            }
        }
        return ans;
    }


    /**
     * 单调栈
     *
     * @param temperatures
     * @return
     */
    public static int[] dailyTemperatures2(int[] temperatures) {
        int[] ans = new int[temperatures.length];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        for (int i = 1; i < temperatures.length; i++) {
            // 当前元素 大于 栈顶温度
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                // 出栈为x天
                int idx = stack.pop();
                // 计算下标距离：当前天 - 下标x天
                ans[idx] = i - idx;
            }
            // 栈中存放下标，第x天
            stack.push(i);
        }
        return ans;
    }

    /**
     * 数组优化
     * <p>
     * 73, 99, 75, 71, 69, 72, 76, 73
     * 1,  1, 0, 0
     *
     * @param temperatures
     * @return
     */
    public static int[] dailyTemperatures3(int[] temperatures) {
        int[] result = new int[temperatures.length];
        // 从后往前确定每个数值
        for (int i = temperatures.length - 2; i >= 0; i--) {
            int j = i + 1;
            while (true) {
                // 当后面的天温度 > 当前的温度，跳出循环
                if (temperatures[j] > temperatures[i]) {
                    result[i] = j - i;
                    break;
                }
                // 当后面某天的温度为0，直接返回0，不可能更低
                if (result[j] == 0) {
                    result[i] = 0;
                    break;
                }
                // 加上 上一天缓存的值，可以复用前面计算的结果
                j += result[j];
            }
        }
        return result;
    }
}
