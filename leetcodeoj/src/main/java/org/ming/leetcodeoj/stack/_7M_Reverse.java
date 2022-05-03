package org.ming.leetcodeoj.stack;

import java.util.ArrayList;
import java.util.List;

/**
 * 整数运算判断溢出处理
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _7M_Reverse {

    public static void main(String[] args) {
        int x = 569;
        System.out.println(reverse(x));
        System.out.println(reverse2(x));
    }

    public static int reverse(int x) {
        int ans = 0;
        List<Integer> nums = new ArrayList<>();
        boolean tag = false;
        if (x < 0) {
            x = 0 - x;
            tag = true;
        }
        int s = -1, yu = 0;
        do {
            yu = x % 10;
            x = x / 10;
            nums.add(yu);
        } while (x != 0);
        for (int i = 0; i < nums.size(); i++) {
            ans += (nums.get(i) * Math.pow(10, nums.size() - i - 1));
        }
        if (tag) {
            ans = 0 - ans;
            if (ans < Integer.MIN_VALUE) {
                return 0;
            }
        } else {
            if (ans >= Integer.MAX_VALUE) {
                return 0;
            }
        }
        return ans;
    }


    public static int reverse2(int x) {
        int ans = 0;
        while (x != 0) {
            int pop = x % 10;
            if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            if (ans < Integer.MIN_VALUE / 10 || (ans == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            ans = ans * 10 + pop;
            x /= 10;
        }
        return ans;
    }
}
