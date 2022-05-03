package org.ming.leetcodeoj.string;

import java.util.Arrays;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _459S_RepeatedSubstringPattern {

    public static void main(String[] args) {
        String s = "ab";
        System.out.println(repeatedSubstringPattern3(s));
    }


    public static boolean repeatedSubstringPattern1(String s) {
        return (s + s).indexOf(s, 1) != s.length();
    }


    public static boolean repeatedSubstringPattern2(String s) {
        return kmp(s + s, s);
    }

    private static boolean kmp(String query, String pattern) {

        return false;
    }

    public static boolean repeatedSubstringPattern3(String s) {
        int n = s.length();
        int[] next = new int[n];
        Arrays.fill(next, -1);
        char[] chars = s.toCharArray();
        for (int i = 1; i < n; i++) {
            int j = next[i - 1];
            while (j >= 0 && chars[i] != chars[j + 1]) {
                j = next[j];
            }
            if (chars[i] == chars[j + 1]) {
                next[i] = j + 1;
            }
        }
        return next[n - 1] != -1 && n % (n - next[n - 1] - 1) == 0;
    }
}
