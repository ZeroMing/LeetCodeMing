package org.ming.leetcodeoj.stack;

import java.util.ArrayList;
import java.util.List;

/**
 * 括号生成
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class GenerateParenthesis {

    // https://www.cnblogs.com/grandyang/p/4444160.html

    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }


    /**
     * n对括号，意味有n个（）
     * 1 2 3 4 5 6 7 8 9
     *
     * @param n
     * @return
     */
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        helper(n, n, "", res);
        return res;
    }

    /**
     * 生成括号
     *
     * @param left  左边界
     * @param right 有边界
     * @param out   括号串
     * @param res   尾递归，保存结果
     */
    private static void helper(int left, int right, String out, List<String> res) {
        if (left < 0 || right < 0 || left > right) {
            return;
        }
        if (left == 0 && right == 0) {
            res.add(out);
        }
        helper(left - 1, right, out + "(", res);
        helper(left, right - 1, out + ")", res);
    }
}
