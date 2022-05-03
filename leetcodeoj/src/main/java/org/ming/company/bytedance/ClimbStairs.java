package org.ming.company.bytedance;

/**
 * 爬楼梯
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class ClimbStairs {
    /*
    假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
    每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
    注意：给定 n 是一个正整数。
    示例 1：

    输入： 2
    输出： 2
    解释： 有两种方法可以爬到楼顶。
    1.  1 阶 + 1 阶
    2.  2 阶
    示例 2：

    输入： 3
    输出： 3
    解释： 有三种方法可以爬到楼顶。
    1.  1 阶 + 1 阶 + 1 阶
    2.  1 阶 + 2 阶
    3.  2 阶 + 1 阶
     */

    public static void main(String[] args) {
        System.out.println(climbStairs1(8));
        System.out.println(climbStairs2(8));
    }


    /**
     * <
     * 1
     * 2
     *
     * @param n
     * @return
     */
    public static int climbStairs1(int n) {
        if (n == 1 || n == 2) {
            System.out.println();
            return n;
        }
        // System.out.print("走1步台阶");
        int step1 = climbStairs1(n - 1);
        // System.out.print("走2步台阶");
        int step2 = climbStairs1(n - 2);
        return step1 + step2;
    }


    /**
     * @param n
     * @return
     */
    public static int climbStairs2(int n) {
        if(n ==1 || n == 2){
            return n;
        }
        int[] array = new int[n + 1];
        array[1] = 1;
        array[2] = 2;
        return climb(n, array);
    }

    public static int climb(int n, int[] array) {
        // 将每一步记录下来,没有缓存
        if (array[n] == 0) {
            array[n] = climb(n - 1, array) + climb(n - 2, array);
        }
        return array[n];
    }

}
