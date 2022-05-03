package org.ming.leetcodeoj.string;

/**
 * 409. 最长回文串
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _409M_LongestPalindrome {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("aabbccddeefgg"));
    }

    public static int longestPalindrome(String s) {
        int len = s.length();
        // 统计每个字符的频率
        int i = 0, res = 0, oddMark = 0;
        int[] hash = new int[52];
        for (i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c <= 'z' && c >= 'a') {
                hash[c - 'a'] = hash[c - 'a'] + 1;
            } else {
                hash[c - 'A'] = hash[c - 'A' + 26] + 1;
            }
        }
        for (i = 0; i < 52; i++) {
            res += hash[i];
            if (hash[i] % 2 != 0) {
                res--;
                oddMark = 1;
            }
        }
        if (oddMark == 1) {
            res++;
        }
        return res;
    }
}
