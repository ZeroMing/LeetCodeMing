package org.ming.leetcodeoj.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Restore IP Addresses 修复IP地址问题
 * @author: LeoMee
 * @date: 2019年07月13 14时22分
 */
public class _93M_RestoreIpAddresses {
    /**
     * Given a string containing only digits,
     * restore it by returning all possible valid IP address combinations.
     * Example:
     * Input: "25525511135"
     * Output: ["255.255.11.135", "255.255.111.35"]
     * <p>
     * 翻译:
     * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP地址 组合。
     */

    public static void main(String[] args) {

        List<String> strings = new _93M_RestoreIpAddresses().restoreIpAddresses("25525511135");
        List<String> strings1 = new _93M_RestoreIpAddresses().restoreIpAddresses2("25525511135");
        System.out.println("结果: " + strings);
        System.out.println("结果: " + strings1);

    }

    /**
     * 思路:
     * 一是只要遇到字符串的 【子序列】或 【配准问题】 首先考虑动态规划DP。
     * 二是只要遇到需要求出【所有可能情况】首先考虑用【递归】
     * <p>
     * ::: 使用回溯法+合理剪枝
     *
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        restore(s, 4, "", result);
        return result;
    }

    /**
     * 递归进行处理
     *
     * @param input        源字符串
     * @param remainPieces 剩余需要切分的片
     * @param out          切分的字符串
     * @param result       结果集合
     */
    private void restore(String input, int remainPieces, String out, List<String> result) {
        if (remainPieces == 0) {
            if (input.isEmpty()) {
                result.add(out);
            }
        }

        // 每一段都 分别按 1 2 3 切分，判断是否有效。
        for (int i = 1; i <= 3; ++i) {
            if (input.length() >= i && isValid(input.substring(0, i))) {
                if (remainPieces == 1) {
                    restore(input.substring(i), remainPieces - 1, out + input.substring(0, i), result);
                } else {
                    restore(input.substring(i), remainPieces - 1, out + input.substring(0, i) + ".", result);
                }
            }

        }
    }

    /**
     * 判断数字是否有效
     *
     * @param str
     * @return
     */
    public boolean isValid(String str) {
        int length = str.length();
        if (length == 0 || length > 3 || (length > 1 && str.charAt(0) == '0')) {
            return false;
        }
        int num = Integer.parseInt(str);
        return num >= 0 && num <= 255;
    }


    public List<String> restoreIpAddresses2(String s) {
        List<String> ans = new ArrayList<>();
        if (s.length() > 12) {
            return ans; // 算是剪枝了
        }
        backTrack(s, 0, 0,ans);
        return ans;
    }

    // startIndex: 搜索的起始位置， pointNum:添加逗点的数量
    private void backTrack(String s, int startIndex, int pointNum,List<String> ans) {
        if (pointNum == 3) { // 逗点数量为3时，分隔结束
            // 判断第四段⼦字符串是否合法，如果合法就放进result中
            if (isValid(s,startIndex,s.length()-1)) {
                ans.add(s);
            }
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            if (isValid(s, startIndex, i)) {
                s = s.substring(0, i + 1) + "." + s.substring(i + 1);    //在str的后⾯插⼊⼀个逗点
                pointNum++;
                backTrack(s, i + 2, pointNum,ans);// 插⼊逗点之后下⼀个⼦串的起始位置为i+2
                pointNum--;// 回溯
                s = s.substring(0, i + 1) + s.substring(i + 2);// 回溯删掉逗点
            } else {
                break;
            }
        }
    }

    // 判断字符串s在左闭⼜闭区间[start, end]所组成的数字是否合法
    private Boolean isValid(String s, int start, int end) {
        if (start > end) {
            return false;
        }
        if (s.charAt(start) == '0' && start != end) { // 0开头的数字不合法
            return false;
        }
        int num = 0;
        for (int i = start; i <= end; i++) {
            if (s.charAt(i) > '9' || s.charAt(i) < '0') { // 遇到⾮数字字符不合法
                return false;
            }
            num = num * 10 + (s.charAt(i) - '0');
            if (num > 255) { // 如果⼤于255了不合法
                return false;
            }
        }
        return true;
    }

}
