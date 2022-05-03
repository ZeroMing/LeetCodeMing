package org.ming.leetcodeoj.thought.dp;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class LongestValidParentheses {

    /**
     * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "(()"
     * 输出：2
     * 解释：最长有效括号子串是 "()"
     * 示例 2：
     * <p>
     * 输入：s = ")()())"
     * 输出：4
     * 解释：最长有效括号子串是 "()()"
     * 示例 3：
     * <p>
     * 输入：s = ""
     * 输出：0
     *  
     * <p>
     * 提示：
     * <p>
     * 0 <= s.length <= 3 * 104
     * s[i] 为 '(' 或 ')'
     */

    public static void main(String[] args) {
        String s = "())()(((()))))";
        System.out.println("正逆向结合解法:" + longestValidParentheses4(s));
        System.out.println(longestValidParentheses4(s));
        System.out.println(longestValidParentheses4(s));
        System.out.println(longestValidParentheses4(s));
    }

    /**
     * 1、暴力破解
     *
     * @param s
     * @return
     */
    public static int longestValidParentheses1(String s) {
        int maxAns = 0;
        int[] dp = new int[s.length()];
        // 求每个两个字符的子串是否为有效
        for (int i = 1; i < s.length(); i++) {
            // 只有第二个位等于 )
            if (s.charAt(i) == ')') {
                // 前一个为 (
                if (s.charAt(i - 1) == '(') {
                    // 计算下标
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxAns = Math.max(maxAns, dp[i]);
            }
        }
        return maxAns;
    }

    /**
     * 2、动态规划
     *
     * @param s
     * @return
     */
    public static int longestValidParentheses2(String s) {
        int maxAns = 0;
        int[] dp = new int[s.length()];
        // 求每个两个字符的子串是否为有效
        for (int i = 1; i < s.length(); i++) {
            // 只有第二个位等于 )
            if (s.charAt(i) == ')') {
                // 前一个为 (
                if (s.charAt(i - 1) == '(') {
                    // 计算下标
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxAns = Math.max(maxAns, dp[i]);
            }
        }
        return maxAns;
    }


    /**
     * @param s
     * @return
     */
    public static int longestValidParentheses3(String s) {
        int maxAns = 0;
        // 栈
        Deque<Integer> stack = new LinkedList<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxAns = Math.max(maxAns, i - stack.peek());
                }
            }
        }
        return maxAns;
    }


    /**
     * 正向逆向结合
     *
     * @param s
     * @return
     */
    public static int longestValidParentheses4(String s) {
        int maxAns = 0, left = 0, right = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxAns = Math.max(maxAns, 2 * right);
            } else if (right > left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxAns = Math.max(maxAns, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return maxAns;
    }


}
