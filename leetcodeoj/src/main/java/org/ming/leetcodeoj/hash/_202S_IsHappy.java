package org.ming.leetcodeoj.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _202S_IsHappy {

    public static void main(String[] args) {
        System.out.println(isHappy(19));
    }


    public static boolean isHappy(int n) {
        Set<Integer> numSet = new HashSet<>();
        numSet.add(n);
        if (n == 1) {
            return true;
        }
        while (true) {
            n = getSum(n);
            if (n == 1) {
                return true;
            }
            if (numSet.contains(n)) {
                return false;
            }
            numSet.add(n);
        }
    }


    public static int getSum(int n) {
        int res = 0;
        // 计算每一位上的数字的平方和
        while (n > 0) {
            res += (n % 10) * (n % 10);
            n = n / 10;
        }
        return res;
    }
}
