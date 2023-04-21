package org.ming.day1q1;

/**
 * @author liming53
 * @date 2023/4/21
 * @company 58房产 · 交易技术部
 * @since 1.0
 */
public class _2413_S_SmallestEvenMultiple {

    public static void main(String[] args) {

    }

    public static int smallestEvenMultiple(int n) {
        // 2 和 n的最小公倍数
        if(n<=1){
            return 2;
        }
        // 偶数
        if(n % 2 == 0){
            return n;
        }else{
            // 奇数 2*n
            return 2*n;
        }
    }
}
