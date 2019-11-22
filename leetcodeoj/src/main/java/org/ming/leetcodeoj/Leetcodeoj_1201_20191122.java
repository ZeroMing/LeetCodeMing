package org.ming.leetcodeoj;

/**
 * 丑数III
 *
 *
 * @author: LeoLee
 * @date: 2019/11/22 09:50
 */
public class Leetcodeoj_1201_20191122 {

    /*

    请你帮忙设计一个程序，用来找出第 n 个丑数。
    丑数是可以被 a 或 b 或 c 整除的 正整数。

    示例 1：
    输入：n = 3, a = 2, b = 3, c = 5
    输出：4
    解释：丑数序列为 2, 3, 4, 5, 6, 8, 9, 10… 其中第 3 个是 4。

    示例 2：
    输入：n = 4, a = 2, b = 3, c = 4
    输出：6
    解释：丑数序列为 2, 3, 4, 6, 8, 9, 12… 其中第 4 个是 6。

    示例 3：
    输入：n = 5, a = 2, b = 11, c = 13
    输出：10
    解释：丑数序列为 2, 4, 6, 8, 10, 11, 12, 13… 其中第 5 个是 10。

    示例 4：
    输入：n = 1000000000, a = 2, b = 217983653, c = 336916467
    输出：1999999984

    提示：
    1 <= n, a, b, c <= 10^9
    1 <= a * b * c <= 10^18
    本题结果在 [1, 2 * 10^9] 的范围内

    前言：
    这是我第一次遇见 丑数 这个概念。

    如果之前见过丑数的同学可能会发现，此题的丑数定义和【丑数 - 百度百科】的定义是不同的。

    此题的丑数定义：丑数是可以被 a 或 b 或 c 整除的正整数。

    题目比较坑的一点是示例 2 中的丑数序列是错的，它缺少 10。因为 10 能被 2 整除，所以 10 是丑数。

    本来就没见过丑数，它还弄个错误的示例，导致写题的时候我的思路偏离。

    题解：
    知道丑数的定义，那么可以采用暴力搜索的方法找到第 n 个丑数。但是测试示例 4 的时候会 TLE。

    因此需另寻他法。

    给定一个数 n，和三个数 2，3，5，那么区间 [1, n] 有多少个丑数呢？

    根据定义：我们知道 2 的倍数肯定是丑数，有多少个 2 的倍数呢？当然是 n / 2 个啦（全部向下取整）。
    同理 3 的倍数也是，有 n / 3 个。
    5 的倍数有 n / 5 个。

    现在你会发现另一个问题，比如 6 是 2 的倍数，也是 3 的倍数。那岂不是计算了两遍？没错，确实算了两遍。因此我们需要知道 【容斥原理 - 百度百科】。说起来陌生，但是我相信大家都用过。

    因此，我们可以知道区间 [1, n] 的丑数个数了。即

    代码表示为：num = n / a + n / b + n / c - n / bc - n / ac - n / ab + n / abc

    需要注意的是，题目没有说给的三个数是互质的，因此需要计算最小公倍数和最大公约数。

    然后再用【二分查找 - 百度百科】逼近第 n 个丑数
     。

    时间复杂度： 二分查找时间复杂度为 O(log2n)
    空间复杂度： O(1)

     */

    public static void main(String[] args) {
        System.out.println(new Leetcodeoj_1201_20191122().nthUglyNumber(1000000000,2,217983653,336916467));

    }



    public int nthUglyNumber(int n, int a, int b, int c) {

        long left = 1,right = 2000000000;
        // a,b的最小公倍数
        long ab = lcm(a, b);
        long ac = lcm(a, c);
        long bc = lcm(b, c);
        long abc = lcm(ab, c);

        while (left < right){
            long mid = left + right >> 1;
            long num = mid / a + mid / b + mid /c - mid /ab - mid/ac - mid/bc + mid/abc ;
            if(num < n){
                // 取区间[mid + 1, right]
                left  = mid + 1;
            }else{
                // 取区间[left, mid]
                right = mid;
            }
        }
        return (int)left;
    }


    /**
     * Lowest Common Multiple
     * 最小公倍数
     * @param x
     * @param y
     * @return
     */
    public long lcm(long x,long y){
        return x / gcd(x,y) * y;
    }

    /**
     * greatest common divisor
     * 计算最大公约数
     * @param x
     * @param y
     * @return
     */
    public long gcd(long x,long y){
        return y > 0 ? gcd(y, x % y) : x;
    }






}
