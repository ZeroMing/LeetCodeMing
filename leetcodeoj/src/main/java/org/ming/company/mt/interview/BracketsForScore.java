package org.ming.company.mt.interview;

/**
 * 来自美团
 * () 分值为2
 * (()) 分值为3
 * ((())) 分值为4
 * 也就是说，每包裹一层，分数就是里面的分值+1
 * ()() 分值为2 * 2
 * (())() 分值为3 * 2
 * 也就是说，每连接一段，分数就是各部分相乘，以下是一个结合起来的例子
 * (()())()(()) -> (2 * 2 + 1) * 2 * 3 -> 30
 * 给定一个括号字符串str，已知str一定是正确的括号结合，不会有违规嵌套
 * 返回分数
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class BracketsForScore {
    public static void main(String[] args) {
        String brackets = "(()())()(())";
        System.out.println(bracketsForScore(brackets));
    }

    /**
     * 来自美团
     * () 分值为2
     * (()) 分值为3
     * ((())) 分值为4
     * 也就是说，每包裹一层，分数就是里面的分值+1
     * ()() 分值为2 * 2
     * (())() 分值为3 * 2
     * 也就是说，每连接一段，分数就是各部分相乘，以下是一个结合起来的例子
     * (()())()(()) -> (2 * 2 + 1) * 2 * 3 -> 30
     * ((())())()(())
     * 给定一个括号字符串str，已知str一定是正确的括号结合，不会有违规嵌套
     * 返回分数
     */

    public static int bracketsForScore(String brackets) {
        // ((()))()(())
        char[] chars = brackets.toCharArray();
        if (chars[0] == ')') {
            return 0;
        }
        int ans = 1;
        return ans;
    }

    public static int bracketsForScore2(String brackets) {
        return compute(brackets.toCharArray(), 0)[0];
    }

    // s[i.....] 遇到 ')' 或者 终止位置  停！
    // 返回值：int[]  长度就是2
    // 0 ：分数是多少
    // 1 : 来到了什么位置停的！
    public static int[] compute(char[] s, int i) {
        if (s[i] == ')') {
            return new int[]{1, i};
        }
        int ans = 1;
        while (i < s.length && s[i] != ')') {
            int[] info = compute(s, i + 1);
            ans *= info[0] + 1;
            i = info[1] + 1;
        }
        return new int[]{ans, i};
    }


}
