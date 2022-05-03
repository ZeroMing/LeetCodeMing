package org.ming.leetcodeoj.string;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _10H_IsMatch {

    public static void main(String[] args) {
        char[] a = new char[1];
        System.out.println(a[0]);

        String s = "aaab";
        String p = "aa*";
        System.out.println(isMatch(s, p));
    }

    public static boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        // 空串可以匹配到
        dp[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                // 模式串末位是*
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 2];
                    // 模式串*的前一个字符能够跟文本串的末位匹配上
                    if (matches(s, p, i, j - 1)) {
                        // *匹配0次的情况
                        // *匹配1次或多次的情况
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                } else if (matches(s, p, i, j)) {
                    // 文本串和模式串末位字符能匹配上
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }

    public static boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }
}
