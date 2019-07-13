package org.ming.leetcodeoj;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Restore IP Addresses 修复IP地址问题
 * @author: LeoMee
 * @date: 2019年07月13 14时22分
 */
public class Leetcodeoj_93_20190713 {
    /**
     * Given a string containing only digits,
     * restore it by returning all possible valid IP address combinations.
     * Example:
     * Input: "25525511135"
     * Output: ["255.255.11.135", "255.255.111.35"]
     *
     * 翻译:
     * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP地址 组合。
     */

    public static void main(String[] args) {

        List<String> strings = new Leetcodeoj_93_20190713().restoreIpAddresses("25525511135");
        System.out.println("结果: " + strings);

    }

    /**
     * 思路:
     * 一是只要遇到字符串的 【子序列】或 【配准问题】 首先考虑动态规划DP。
     * 二是只要遇到需要求出【所有可能情况】首先考虑用【递归】
     *
     * ::: 使用回溯法+合理剪枝
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        restore(s,4,"",result);
        return result;
    }

    /**
     * 递归进行处理
     * @param input           源字符串
     * @param remainPieces  剩余需要切分的片
     * @param out           切分的字符串
     * @param result        结果集合
     */
    private void restore(String input, int remainPieces, String out, List<String> result) {
        if(remainPieces == 0){
            if(input.isEmpty()){
                result.add(out);
            }
        }

        // 每一段都 分别按 1 2 3 切分，判断是否有效。
        for(int i = 1;i <= 3;++i){
            if(input.length() >= i && isValid(input.substring(0,i))){
                if(remainPieces == 1){
                    restore(input.substring(i),remainPieces-1,out + input.substring(0,i),result);
                }else{
                    restore(input.substring(i),remainPieces-1,out + input.substring(0,i)+".",result);
                }
            }

        }
    }

    /**
     * 判断数字是否有效
     * @param str
     * @return
     */
    public boolean isValid(String str){
        int length = str.length();
        if(length == 0 || length > 3 || (length > 1 && str.charAt(0) == '0')){
            return false;
        }
        int num = Integer.parseInt(str);
        return num >= 0 && num <= 255;
    }

}
