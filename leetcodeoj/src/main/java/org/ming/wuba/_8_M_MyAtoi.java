package org.ming.wuba;

import java.util.ArrayList;
import java.util.List;

/**
 * 8. 字符串转换整数 (atoi)
 *
 * @date: 2022/4/27 <br>
 * @author: liming53 <br>
 * @company 58房产 · 交易技术部 <br>
 */
public class _8_M_MyAtoi {

    /*
    请你来实现一个myAtoi(string s)函数，使其能将字符串转换成
    一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
函数myAtoi(string s) 的算法如下：
1. 读入字符串并丢弃无用的前导空格
2. 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。
 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
3. 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
4. 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。
如果没有读入数字，则整数为 0 。
必要时更改符号（从步骤 2 开始）。
如果整数数超过 32 位有符号整数范围 [−2^31, 2^31− 1] ，需要截断这个整数，
使其保持在这个范围内。具体来说，小于 −2^31 的整数应该被固定为 −2^31 ，
大于 2^31− 1 的整数应该被固定为 2^31− 1 。
返回整数作为最终结果。
注意：
本题中的空白字符只包括空格字符 ' ' 。
除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。

示例1：

输入：s = "42"
输出：42
解释：加粗的字符串为已经读入的字符，插入符号是当前读取的字符。
第 1 步："42"（当前没有读入字符，因为没有前导空格）
         ^
第 2 步："42"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
         ^
第 3 步："42"（读入 "42"）
           ^
解析得到整数 42 。
由于 "42" 在范围 [-231, 231 - 1] 内，最终结果为 42 。
示例2：
输入：s = "   -42"
输出：-42
解释：
第 1 步："   -42"（读入前导空格，但忽视掉）
            ^
第 2 步："   -42"（读入 '-' 字符，所以结果应该是负数）
             ^
第 3 步："   -42"（读入 "42"）
               ^
解析得到整数 -42 。
由于 "-42" 在范围 [-231, 231 - 1] 内，最终结果为 -42 。
示例 3：

输入：s = "4193 with words"
输出：4193
解释：
第 1 步："4193 with words"（当前没有读入字符，因为没有前导空格）
         ^
第 2 步："4193 with words"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
         ^
第 3 步："4193 with words"（读入 "4193"；由于下一个字符不是一个数字，所以读入停止）
             ^
解析得到整数 4193 。
由于 "4193" 在范围 [-231, 231 - 1] 内，最终结果为 4193 。
 

提示：

0 <= s.length <= 200
s 由英文字母（大写和小写）、数字（0-9）、' '、'+'、'-' 和 '.' 组成
     */

    public static void main(String[] args) {
        _8_M_MyAtoi myAtoi = new _8_M_MyAtoi();
        System.out.println(myAtoi.convertStr2Integer("42"));
    }

    public Integer convertStr2Integer(String s) {
        // 判断空
        if (s == null || s.length() == 0) {
            return 0;
        }
        int idx = 0, n = s.length();
        char[] chars = s.toCharArray();
        // 从零开始去掉空格
        while (idx < n && chars[idx] == ' ') {
            idx++;
        }
        // 去掉前导空格以后到了末尾了
        if (idx == n) {
            return 0;
        }
        // 读入数字
        boolean firstDigit = false;
        boolean symbol = false;
        boolean negative = false;
        List<Character> list = new ArrayList<>();
        boolean preDigit = false;
        // 遍历处理
        while (idx < chars.length) {
            // 读取到的是数字
            if (chars[idx] >= '0' && chars[idx] <= '9') {
                //.如果是第一个数字，处理
                if (!firstDigit) {
                    firstDigit = true;
                }
                list.add(chars[idx]);
                preDigit = true;
            }
            // 遇到负号,且第一个数字还未出现，定义未负数
            else if ((chars[idx] == '-' && !firstDigit)) {
                if(symbol){
                    return 0;
                }else{
                    symbol = true;
                    negative = true;
                }
            }else if(chars[idx] == '+' && !firstDigit){
                if(symbol){
                    return 0;
                }else{
                    symbol = true;
                    negative = false;
                }
            }
            // 读取到的是非数字
            else {
                if(!firstDigit){
                    return 0;
                }
                // 前面是数字，后面非数字，截断
                if(preDigit){
                    break;
                }
                continue;
            }
            idx++;
        }

        // 遍历完毕之后，仍然没有遇到数字
        if (!firstDigit) {
            return 0;
        }

        int ans = 0;
        // 负数
        if(negative){
            idx = 0;
            while (idx < list.size()) {
                // 负数
                int digit = 0 - (list.get(idx) - '0');
                if (ans < (Integer.MIN_VALUE - digit) / 10) {
                    return Integer.MIN_VALUE;
                }
                // -256 -258
                else if(ans == (Integer.MIN_VALUE - digit) / 10 && Integer.MIN_VALUE % 10 >= digit){
                    return Integer.MIN_VALUE;
                }
                ans = ans * 10 + digit;
                idx++;
            }
        }else{
            idx = 0;
            while (idx < list.size()) {
                int digit = list.get(idx) - '0';
                if (ans > (Integer.MAX_VALUE - digit) / 10) {
                    return Integer.MAX_VALUE;
                }
                // 2458 - 2457
                else if(ans == (Integer.MAX_VALUE - digit) / 10 && Integer.MAX_VALUE % 10 <= digit){
                    return Integer.MAX_VALUE;
                }
                ans = ans * 10 + digit;
                idx++;
            }
        }
        return ans;
    }

}
