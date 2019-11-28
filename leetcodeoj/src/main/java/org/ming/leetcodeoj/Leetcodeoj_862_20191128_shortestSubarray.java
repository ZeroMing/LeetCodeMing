package org.ming.leetcodeoj;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author: LeoLee
 * @date: 2019/11/28 11:04
 */
public class Leetcodeoj_862_20191128_shortestSubarray {

    /*
    返回 A 的最短的非空连续子数组的长度，该子数组的和至少为 K 。
    如果没有和至少为 K 的非空子数组，返回 -1 。
    示例 1：

    输入：A = [1], K = 1
    输出：1
    示例 2：

    输入：A = [1,2], K = 4
    输出：-1
    示例 3：

    输入：A = [2,-1,2], K = 3
    输出：3
     
    提示：
    1 <= A.length <= 50000
    -10 ^ 5 <= A[i] <= 10 ^ 5
    1 <= K <= 10 ^ 9

     */

    public static void main(String[] args) {
        int[] A = new int[]{1,-3,4,5,-1,-2,6,8};
        int K = 12;
        Leetcodeoj_862_20191128_shortestSubarray leetcodeoj = new Leetcodeoj_862_20191128_shortestSubarray();
        int i = leetcodeoj.shortestSubarray_2(A, K);
        System.out.println(i);
    }

    public int shortestSubarray(int[] A, int K) {
        int  sum = 0,begin=0,minLen = -1;

        // [2,-1,2,3,4,5,5,6,6]
        for(int i=0;i<A.length;i++) {
            System.out.println("-------------第"+(i+1)+"轮");
            if(A[i] >= K){
                return 1;
            }
            sum += A[i];
            if(sum < 1) {
                System.out.println("sum="+sum);
                sum = 0;
                begin = i + 1;
                continue;
            }
            System.out.println();
            print(A);
            System.out.println("移位...");
            // 将后面的负数迁移，和也前移
            for(int j= i-1;A[j+1] < 0;j--) {
                A[j] = A[j+1] + A[j];
                A[j+1] = 0;
                print(A);
            }

            if (sum > K) {
                while (sum - A[begin]>=K || A[begin] <=0) {
                    sum -= A[begin++];
                }
                int len = i - begin + 1;
                if(minLen < 0 || minLen < len) {
                    minLen = len;
                }
            }
        }
        return minLen;

    }


    /**
     * 滑动窗口问题
     * 【】
     * @param A
     * @param K
     * @return
     */
    public int shortestSubarray_2(int[] A, int K) {
        int N = A.length;
        long[] P = new long[N+1];
        for (int i = 0; i < N; ++i) {
            P[i + 1] = P[i] + (long) A[i];
        }
        print(P);
        // Want smallest y-x with P[y] - P[x] >= K
        int ans = N+1; // N+1 is impossible
        Deque<Integer> monoq = new LinkedList(); //opt(y) candidates, as indices of P

        for (int y = 0; y < P.length; ++y) {
            // Want opt(y) = largest x with P[x] <= P[y] - K;
            while (!monoq.isEmpty() && P[y] <= P[monoq.getLast()]) {
                monoq.removeLast();
            }
            while (!monoq.isEmpty() && P[y] >= P[monoq.getFirst()] + K) {
                ans = Math.min(ans, y - monoq.removeFirst());
            }
            // 将元素扔进队列
            monoq.addLast(y);
            print(monoq);
        }
        return ans < N+1 ? ans : -1;
    }


    public void print(Deque<Integer> deque){
        Iterator<Integer> iterator = deque.iterator();
        while (iterator.hasNext()){
            Integer next = iterator.next();
            System.out.print(next + ",");
        }
        System.out.println();
    }


    /**
     * 脑子不好，打印来凑
     * @param array
     */
    public void print(int[] array){
        for(int i=0;i<array.length;i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }


    /**
     * 脑子不好，打印来凑
     * @param array
     */
    public void print(long[] array){
        for(int i=0;i<array.length;i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }


}
