package org.ming.leetcodeoj.string;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class BigNumberPlus {

    public static void main(String[] args) {
        String s1 = "999991";
        String s2 = "2221";
        System.out.println(bigNumberPlus(s1, s2));
    }

    public static String bigNumberPlus(String s1, String s2) {
        if (s1 == null || s1.length() == 0) {
            return s2;
        }
        if (s2 == null || s2.length() == 0) {
            return s1;
        }
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        int n1 = chars1.length - 1, n2 = chars2.length - 1;
        int num1 = 0, num2 = 0;
        // 当前位上要相加的数,余数，当前值
        int m = 0;
        // 进位，商
        int n = 0;
        StringBuilder sb = new StringBuilder();
        while (n1 >= 0 || n2 >= 0) {
            if (n1 >= 0) {
                num1 = chars1[n1] - '0';
            } else {
                num1 = 0;
            }
            if (n2 >= 0) {
                num2 = chars2[n2] - '0';
            } else {
                num2 = 0;
            }
            int sum = num1 + num2 + n;
            m = sum % 10;
            n = sum / 10;
            sb.append(((char) (m + '0')));
            n1--;
            n2--;
        }
        if (n != 0) {
            sb.append('1');
        }
        return sb.reverse().toString();
    }
}
