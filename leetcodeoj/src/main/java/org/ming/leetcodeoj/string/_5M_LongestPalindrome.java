package org.ming.leetcodeoj.string;

/**
 * 5. 最长回文子串
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _5M_LongestPalindrome {

    public static void main(String[] args) {
        String s = "cbbd";
        // System.out.println(longestPalindrome1(s));
        // System.out.println(longestPalindrome2(s));
        System.out.println(longestPalindrome3(s));
    }


    /**
     * 暴力匹配
     *
     * @param s
     * @return
     */
    public static String longestPalindrome1(String s) {
        int strLength = s.length();
        // 一层循环子串
        int idx = 0, maxLen = 0;
        for (int i = 0; i < strLength; i++) {
            // 双层循环打印所有的
            for (int j = strLength - 1; j > i; j--) {
                // TODO 判断每个串是否为回文串
                // System.out.println(s.substring(i, j));
                // 判断是否为回文串
                boolean flag = true;
                // 双指针检查是否为回文串
                for (int left = i, right = j; left < right; right--, left++) {
                    // 不是回文串，标志为false
                    if (s.charAt(left) != s.charAt(right)) {
                        flag = false;
                        break;
                    }
                }
                // 打印回文串
                if (flag) {
                    System.out.println("回文串: " + s.substring(i, j + 1));
                }
                // 是回文串，且长度大于之前的长度
                if (flag && j - i > maxLen) {
                    maxLen = j - i;
                    idx = i;
                }
            }
        }
        return maxLen > -1 ? s.substring(idx, idx + maxLen + 1) : "";
    }


    /**
     * 动态规划
     * 暴力匹配，优化版本
     *
     * @param s
     * @return
     */
    public static String longestPalindrome2(String s) {
        int strLength = s.length();
        // 备忘录,初始化
        int[][] dp = new int[strLength][strLength];
        for (int i = 0; i < strLength; i++) {
            for (int j = 0; j < strLength; j++) {
                dp[i][j] = -2;
            }
        }
        int idx = 0, maxLen = 0;
        for (int i = 0; i < strLength; i++) {
            // 双层循环打印所有的
            for (int j = strLength - 1; j > i; j--) {
                // System.out.println(s.substring(i, j));
                // 查看备忘录，不为空,说明已经处理过
                if (dp[i][j] != -2) {
                    if (dp[i][j] > maxLen) {
                        maxLen = j - i;
                        idx = i;
                    }
                } else {
                    // 判断是否为回文串
                    boolean flag = true;
                    // 检查是否为回文串
                    for (int left = i, right = j; left < right; right--, left++) {
                        // 不是回文串，标志为false
                        if (s.charAt(left) != s.charAt(right)) {
                            flag = false;
                            break;
                        }
                    }
                    // 打印回文串
                    if (flag) {
                        // System.out.println("回文串: " + s.substring(i, j + 1));
                        dp[i][j] = j - i;
                    } else {
                        dp[i][j] = -1;
                    }
                    // 是回文串，且长度大于之前的长度
                    if (flag && j - i > maxLen) {
                        maxLen = j - i;
                        idx = i;
                    }
                }

            }
        }
        return maxLen > -1 ? s.substring(idx, idx + maxLen + 1) : "";

    }

    /**
     * 中心扩散
     *
     * @param s
     * @return
     */
    public static String longestPalindrome3(String s) {
        int strLen = s.length();
        if (strLen < 2) {
            return s;
        }
        // a,b,c,d,e,f
        int maxLen = 0;
        String maxStr = "";
        String res = s.substring(0, 1);
        // 寻找中心串，从第2个开始，到倒2个结束
        for (int i = 0; i < strLen - 1; i++) {
            // 寻找基数串
            String evenStr = findLongestPalindrome(s, i, i);
            // 寻找偶数串
            String oddStr = findLongestPalindrome(s, i, i + 1);
            String maxLenStr = oddStr.length() > evenStr.length() ? oddStr : evenStr;
            if (maxLenStr.length() > maxLen) {
                maxLen = maxLenStr.length();
                res = maxLenStr;
            }
        }
        return res;
    }


    private static String findLongestPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length()) {
            // 是回文
            if (s.charAt(left) != s.charAt(right)) {
                break;
            }
            left--;
            right++;
        }
        return s.substring(left + 1, right);
    }

}
