package org.ming.leetcodeoj.string;

import java.util.Arrays;

/**
 * 剑指 Offer 58 - II. 左旋转字符串
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _J58S_ReverseLeftWords {


    public static void main(String[] args) {
        String s = "lrloseumgh";
        int k = 6;
        System.out.println(reverseLeftWords(s, k));
        System.out.println(reverseLeftWords2(s, k));
        System.out.println(reverseLeftWords3(s, k));
        System.out.println(reverseLeftWords4(s, k));
    }

    /**
     * 剑指 Offer 58 - II. 左旋转字符串
     * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。
     * 请定义一个函数实现字符串左旋转操作的功能。
     * 比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入: s = "abcdefg", k = 2
     * 输出: "cdefgab"
     * 示例 2：
     * <p>
     * 输入: s = "lrloseumgh", k = 6
     * 输出: "umghlrlose"
     * <p>
     * <p>
     * 限制：
     * <p>
     * 1 <= k < s.length <= 10000
     */


    public static String reverseLeftWords(String s, int n) {
        if (s.length() == 0) {
            return s;
        }
        if (n < 1 || n >= s.length()) {
            return s;
        }
        char[] newChars = new char[s.length()];
        char[] chars = s.toCharArray();
        int index = 0;
        for (int i = n; i <= chars.length - 1; i++) {
            newChars[index] = chars[i];
            index++;
        }
        for (int i = 1; i <= n; i++) {
            newChars[index] = chars[i - 1];
            index++;
        }
        return new String(newChars);
    }


    /**
     * abcdefg
     * ab cdefg
     * ba gfedc
     * cdefgab
     *
     * @param s
     * @param n
     * @return
     */
    public static String reverseLeftWords2(String s, int n) {
        if (s.length() == 0) {
            return s;
        }
        if (n < 1 || n >= s.length()) {
            return s;
        }
        char[] chars = s.toCharArray();
        for (int l = 0, r = n - 1; l < r; l++, r--) {
            char temp = chars[l];
            chars[l] = chars[r];
            chars[r] = temp;
        }
        for (int l = n, r = chars.length - 1; l < r; l++, r--) {
            char temp = chars[l];
            chars[l] = chars[r];
            chars[r] = temp;
        }
        for (int l = 0, r = chars.length - 1; l < r; l++, r--) {
            char temp = chars[l];
            chars[l] = chars[r];
            chars[r] = temp;
        }
        return new String(chars);
    }

    public static String reverseLeftWords3(String s, int n) {
        return s.substring(n, s.length()) + s.substring(0, n);
    }

    public static String reverseLeftWords4(String s, int n) {
        if (s.length() == 0) {
            return s;
        }
        if (n < 1 || n >= s.length()) {
            return s;
        }
        char[] chars = s.toCharArray();
        String s1 = new String(Arrays.copyOfRange(chars, n, chars.length));
        String s2 = new String(Arrays.copyOfRange(chars, 0, n));
        return s1 + s2;
    }
}
