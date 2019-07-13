package org.ming.leetcodeoj;

/**
 * @description: Add Strings
 * @author: LeoMee
 * @date: 2019年07月13 17时20分
 */
public class Leetcodeoj_415_20190713 {
    /**
     * Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.
     *
     * Note:
     * The length of both num1 and num2 is < 5100.
     * Both num1 and num2 contains only digits 0-9.
     * Both num1 and num2 does not contain any leading zero.
     * You must not use any built-in BigInteger library or convert the inputs to integer directly.
     *
     * 翻译:
     * 给定两个用字符串表示的非负整数num1和num2，返回num1和num2的和。
     * - num1和num2的长度都小于5100。
     * - num1和num2都只包含0-9位。
     * - num1和num2都不包含任何前导零。
     * - 您不能使用任何内置的BigInteger库或直接将输入转换为integer。
     * 大数字的加法。
     *
     *
     */
    public static void main(String[] args) {
        String s = new Leetcodeoj_415_20190713().addStrings("", "");
        System.out.println(s);
    }


    /**
     * 简单的高精度，可以将字符串翻转之后从头开始加，这样看起来比较自然，当然也可以从尾部开始加．
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for(int i = num1.length()-1,j = num2.length()-1;i >= 0 || j >= 0; i--,j--){
            int x = i < 0 ? 0 : num1.charAt(i) - '0';
            int y = j < 0 ? 0 : num2.charAt(j) - '0';
            // 相加字符
            sb.append((x + y + carry) % 10);
            // 进位
            carry = (x + y + carry) / 10;
        }

        if(carry != 0){
          sb.append(carry);
        }
        String result = sb.reverse().toString();
        return "".equals(result)?"0":result;
    }




}
