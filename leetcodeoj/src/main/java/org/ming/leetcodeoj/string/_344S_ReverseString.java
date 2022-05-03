package org.ming.leetcodeoj.string;

/**
 * 344. 反转字符串
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _344S_ReverseString {
    public static void main(String[] args) {
        char[] s = new char[]{'h', 'e', 'h', 'l', 'o'};
        reverseString(s);
        System.out.println();
    }

    public static void reverseString(char[] s) {
        int left = 0, right = s.length - 1;
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
//        if (len % 2 == 1) {
//            int mid = len / 2 + 1;
//            s[mid] = s[++left];
//        }
    }
}
