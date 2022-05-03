package org.ming.leetcodeoj.string;

/**
 * 剑指 Offer 05. 替换空格
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _J05S_ReplaceSpace {
    public static void main(String[] args) {
        String s = "We are happy";
        System.out.println(replaceSpace(s));
    }

    /**
     * 剑指 Offer 05. 替换空格
     * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "We are happy."
     * 输出："We%20are%20happy."
     * <p>
     * <p>
     * 限制：
     * <p>
     * 0 <= s 的长度 <= 10000
     */

    public static String replaceSpace(String s) {
        //扩充空间，空格数量2倍
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                str.append("  ");
            }
        }
        if (str.length() == 0) {
            return s;
        }
        // 左指针：指向原始字符串最后一个位置
        int left = s.length() - 1;
        s += str.toString();
        // 右指针：指向扩展字符串的最后一个位置
        int right = s.length() - 1;
        char[] chars = s.toCharArray();
        while (left >= 0) {
            if (chars[left] == ' ') {
                chars[right--] = '0';
                chars[right--] = '2';
                chars[right] = '%';
            } else {
                chars[right] = chars[left];
            }
            left--;
            right--;
        }
        return new String(chars);
    }
}
