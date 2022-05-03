package org.ming.company.bytedance.interview;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class CompareNumber {
    public static void main(String[] args) {
        // 6,8,11
        System.out.println(compareNumber2(5, 10));

        // 1,6,10
        // 2,8,10
        // 3,11,10
        // 4,11,14
        // 5,


    }

    /*
    来自字节
给定两个数a和b
第1轮，把1选择给a或者b
第2轮，把2选择给a或者b
...
第i轮，把i选择给a或者b
想让a和b的值一样大，请问至少需要多少轮？
     */


    /**
     * 限定补数的最优解
     *
     * @param a
     * @param b
     * @return
     */
    public static int compareNumber1(int a, int b) {
        if (a == b) {
            return 0;
        }
        int limit = 15;
        return process(a, b, 1, limit);
    }

    public static int process(int a, int b, int i, int n) {
        if (i > n) {
            return Integer.MAX_VALUE;
        }
        if (a + i == b || a == b + i) {
            return i;
        }
        return Math.min(process(a + i, b, i + 1, n), process(a, b + i, i + 1, n));
    }


    public static int compareNumber2(int a, int b) {
        if (a == b) {
            return 0;
        }
        int s = Math.abs(a - b);
        int num = 1;
        int sum = 0;
        for (; !(sum >= s && (sum - s) % 2 == 0); num++) {
            sum += num;
        }
        return num - 1;
    }

    /**
     * 5 10
     * 5 5
     * 5 8
     *
     * @param a
     * @param b
     * @return
     */
    public static int compareNumber3(int a, int b) {
        if (a == b) {
            return 0;
        }
        int s = Math.abs(a - b);
        // 找到sum >= s, 最小的i
        int begin = best(s << 1);
        for (; (begin * (begin + 1) / 2 - s) % 2 != 0; ) {
            begin++;
        }
        return begin;
    }

    public static int best(int s2) {
        int L = 0;
        int R = 1;
        for (; R * (R + 1) < s2; ) {
            L = R;
            R *= 2;
        }
        int ans = 0;
        while (L <= R) {
            int M = (L + R) / 2;
            if (M * (M + 1) >= s2) {
                ans = M;
                R = M - 1;
            } else {
                L = M + 1;
            }
        }
        return ans;
    }
}
