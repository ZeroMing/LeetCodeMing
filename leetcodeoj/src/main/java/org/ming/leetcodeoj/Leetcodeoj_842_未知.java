package org.ming.leetcodeoj;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Split Array into Fibonacci Sequence
 * @author: LeoMee
 * @date: 2019年07月13 12时41分
 */
public class Leetcodeoj_842_未知 {

    /**
     * Given a string S of digits, such as S = "123456579",
     * we can split it into a Fibonacci-like sequence [123, 456, 579].
     * Formally, a Fibonacci-like sequence is a list F of non-negative integers such that:
     * 0 <= F[i] <= 2^31 - 1, (that is, each integer fits a 32-bit signed integer type);
     * F.length >= 3;
     * and F[i] + F[i+1] = F[i+2] for all 0 <= i < F.length - 2.
     * Also, note that when splitting the string into pieces, each piece must not have extra leading zeroes,
     * except if the piece is the number 0 itself.
     * Return any Fibonacci-like sequence split from S, or return [] if it cannot be done.
     *
     * 翻译:
     * 给一串数字，例如：S = 123456789，我们可以将其分割为一个类Fibonacci的序列。如:[123, 456, 579]
     * 通常，一个类 Fibonacci 序列，是一个非负数的集合，例如:
     * 0 <= F[i] <= 2^31 - 1, (that is, each integer fits a 32-bit signed integer type);
     * F.length >= 3;
     * and F[i] + F[i+1] = F[i+2] for all 0 <= i < F.length - 2.
     * 也就是意味着，当我们分割字符串成片时候，每一片不能有额外的 零开头的数字。
     * 返回任何一组 类Fibonacci 序列从给定的字符串S，如果它无法实现，返回一个空数组即可
     *
     * Example 1:
     * Input: "123456579"
     * Output: [123,456,579]
     *
     * Example 2:
     * Input: "11235813"
     * Output: [1,1,2,3,5,8,13]
     *
     * Example 3:
     * Input: "112358130"
     * Output: []
     * Explanation: The task is impossible.
     *
     * Example 4:
     * Input: "0123"
     * Output: []
     * Explanation: Leading zeroes are not allowed, so "01", "2", "3" is not valid.
     * Example 5:
     *
     * Input: "1101111"
     * Output: [110, 1, 111]
     * Explanation: The output [11, 0, 11, 11] would also be accepted.
     * Note:
     *
     * 1 <= S.length <= 200
     * S contains only digits.
     *
     */


    public List<Integer> splitIntoFibonacci(String S) {
        if(S == null || S.length() == 0){
            return new ArrayList<>();
        }
        int one = 0, two = 0, three = 0, i = 0;













        return null;
    }
}
