package org.ming.leetcodeoj.thought.dp;

import java.util.LinkedList;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _121S_MaxProfitI {


    public static void main(String[] args) {

    }


    public static int maxProfit1(int[] prices) {
        int len = prices.length;
        int max = 0, temp = 0;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                // 将来某一天大于入手日的价格，卖出
                if (prices[j] > prices[i]) {
                    temp = prices[j] - prices[i];
                    max = Math.max(temp, max);
                }
            }
        }
        return max;
    }

    /**
     * 记录入手最低的天
     * <p>
     * [7,1,5,3,6,4]
     * [0,0,4,2,5,3]
     * [4,10,7,]
     *
     * @param prices
     * @return
     */
    public static int maxProfit2(int[] prices) {
        int len = prices.length;
        int min = prices[0], max = 0;
        // 计算最大值
        for (int i = 1; i < len; i++) {
            max = Math.max(prices[i] - min, max);
            min = Math.min(prices[i], min);
        }
        return max;
    }


    /**
     * [7,1,5,3,6,4]
     * 7
     * 1
     * 1,5;4;4
     * 1,3;2;4;
     * 1,3,6;5;5;
     * 1,3,4;3;5;
     *
     * @param prices
     * @return
     */
    public static int maxProfit3(int[] prices) {
        LinkedList<Integer> stack = new LinkedList<>();
        int res = 0;
        for (int i = 0; i < prices.length; i++) {
            // 大的元素的弹栈
            while (!stack.isEmpty() && stack.peek() > prices[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                // 将要入栈的栈顶元素-栈底元素，计算出，从栈底元素的价格买，到栈顶元素价格卖出，能赚多少钱，然后和之前的盈利比较，保存最大值。
                int temp = prices[i] - stack.peekLast();
                res = Math.max(res, temp);
            }
            stack.push(prices[i]);
        }
        return res;
    }
}
