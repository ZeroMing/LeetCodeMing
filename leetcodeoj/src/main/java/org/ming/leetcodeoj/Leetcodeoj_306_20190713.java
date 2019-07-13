package org.ming.leetcodeoj;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Additive Number
 * @author: LeoMee
 * @date: 2019年07月13 14时18分
 */
public class Leetcodeoj_306_20190713 extends Leetcodeoj_415_20190713{
    /**
     * 使用回溯法+合理剪枝
     * Additive number is a string whose digits can form additive sequence.
     * A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.
     * Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.
     * Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.
     * 翻译:
     * 【累加数】是一个字符串，它的数字可以形成【累加序列】。
     * 一个有效的累加序列应该包含至少三个数字。除前两个数字外，序列中的每个后续数字都必须是前两个数字的和。
     * 给定一个只包含数字“0”-“9”的字符串，编写一个函数来确定它是否是一个加法数字。
     * 注意:加法序列中的数字不能有前导零，因此序列1、2、03或1、02、3无效。
     *
     * Example 1:
     *
     * Input: "112358"
     * Output: true
     * Explanation: The digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
     *              1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
     * Example 2:
     *
     * Input: "199100199"
     * Output: true
     * Explanation: The additive sequence is: 1, 99, 100, 199.
     *              1 + 99 = 100, 99 + 100 = 199
     *
     *
     *
     */

    public static void main(String[] args) {
        boolean additiveNumber = new Leetcodeoj_306_20190713().isAdditiveNumber("199100199");
        System.out.println(additiveNumber);
//        int size = 10;
//        String format = String.format("%0"+size+"d", 1);
//        System.out.println(format);
    }


    /**
     * 回溯法仍然是对剩余的数字进行切片，看该部分切片能否满足条件
     * 剪枝的方法是判断数组是否长度超过3，如果超过那么判断是否满足费布拉奇数列的规则。
     * 不超过3或者已经满足的条件下继续进行回溯切片
     * 最后当所有的字符串被切片完毕，要判断下数组长度是否大于等于3，
     *
     * 数字字符串加法，DFS。其实一开始选择好两个相加的字符串就已经能确定，它们是否能够一直加到最后。
     *
     * @param str
     * @return
     */
    public boolean isAdditiveNumber(String str) {
        int length = str.length();
        if(length <= 2){
            return false;
        }
        String str1, str2, str3;
        int len1, len2;
        for(len1 = 1; len1 <= length/2;len1 ++){
            str1 = str.substring(0,len1);
            for(len2 = 1; len2 <= length/2;len2 ++){
                str2 = str.substring(len1,len1+len2);
                str3 = str.substring(len1+len2);
                if(dfs(str1,str2,str3)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(String first, String middle, String last) {
        boolean flag = (!first.equals("0") && first.charAt(0) == '0') ||
                (!middle.equals("0") && middle.charAt(0) == '0');
        if(flag){
            return false;
        }
        // 避免字符串精度丢失，大字符串相加
        String addStr = addStrings(first,middle);
        if(addStr.length() > last.length()){
            return false;
        }
        if(addStr.equals(last)){
            return true;
        }
        int addSize = addStr.length();
        if(!addStr.equals(last.substring(0,addSize))){
            return false;
        }
        first = middle;
        middle = addStr;
        last = last.substring(addSize);
        return  dfs(first,middle,last);
    }


}
