package org.ming.leetcodeoj.string;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _541S_ReverseStr {
    public static void main(String[] args) {
        String s = new String("abcd");
        int k = 4;
        System.out.println(reverseStr1(s, k));
    }


    public static String reverseStr(String s, int k) {
        int len = s.length();
        char[] chars = s.toCharArray();
        // 1. 每隔 2k 个字符的前 k 个字符进行反转
        for (int i = 0; i < len; i += (2 * k)) {
            // 2. 剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符
            if (i + k <= len) {
                reverse(chars, i, i + k - 1);
                continue;
            }
            reverse(chars, i, len - 1);
        }
        return new String(chars);
    }

    private static void reverse(char[] chars, int i, int j) {
        for (; i < j; i++, j--) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
    }


    public static String reverseStr1(String s, int k) {
        int len = s.length();
        char[] chars = s.toCharArray();
        // 1. 每隔 2k 个字符的前 k 个字符进行反转
        for (int i = 0; i < len; i += (2 * k)) {
            int start = i;
            int end = Math.min(len - 1, start + k - 1);
            // 用异或运算反转
            while (start < end) {
                chars[start] ^= chars[end];
                chars[end] ^= chars[start];
                chars[start] ^= chars[end];
                start++;
                end--;
            }
        }
        return new String(chars);
    }
}
