package org.ming.leetcodeoj.dynamicprogramming.knapsack;

import java.util.Scanner;

/**
 * 完全背包
 * @Description:
 * @Author: LeoLee
 * @Date: 2019年10月17 23时27分
 */
public class KnapsackWhole {

    public static void main(String[] args) {
        KnapsackWhole backPackWhole = new KnapsackWhole();
        // N表示物体的个数，V表示背包的载重
        int count = 4;
        int maxWeight = 10;
        //用于存储每个物体的重量，下标从1开始
        int[] weight = {2,3,4,5};
        //存储每个物体的收益，下标从1开始
        int[] value = {3,4,5,6};
        // 二维数组，用来保存每种状态下的最大收益
        int[][] dp = new int[count+1][maxWeight+1];
        //降成一维数组，用来保存每种状态下的最大收益
        int[] dpOne = new int[maxWeight+1];

        backPackWhole.completePackNonRecursive(dp,weight,value,count,maxWeight);
        System.out.println("-----------------------");

        // backPackWhole.completePackNonRecursiveOne(dpOne,weight,value,count,maxWeight);

        // backPackWhole.completePackNonRecursiveBest(dpOne,weight,value,count,maxWeight);
        // System.out.println("完全背包最优解: "+ dpOne[dpOne.length-1]);

    }

    /**
     * 完全背包的二维数组解法
     */
    public void completePackNonRecursive(int[][] dp,int[] weight,int[] value,int count,int maxWeight) {
        //对二维数组F进行初始化
        for(int i=0;i <= maxWeight;i++){
            dp[0][i] =  0;
        }
        // 注意边界问题，i是从1开始的
        for(int i = 1; i <= count; i++) {
            // 容量
            for (int j = 0; j <= maxWeight; j++) {
                // 最大容纳的个数
                for (int k = 0; k <= maxWeight / weight[i-1]; k++) {
                    // 容量大于被放置的物品体积
                    if(j >=  k * weight[i-1]){
                        //注意：状态转移方程是F[i][j]，而不是F[i - 1][j]
                        //因为这时放k个第i个物品，之后还可能继续放这个物体，所以应是F[i][j]
                        dp[i][j] = Math.max(dp[i][j - k * weight[i-1]] + k * value[i-1], dp[i][j]);
                    }else{
                        //可以省略，这里为什么不是F[i - 1][j]
                        //因为刚开始k=0，j >= 0 * weight[i]肯定成立，此时F[i][j] = F[i - 1][j]。
                        dp[i][j] = dp[i][j];
                    }
                }
            }

        }

        //打印所有结果，我们要求的是F[N][V]
        for(int i = 0; i <= count; i++) {
            for(int j = 0; j <= maxWeight; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 一维
     * 顺序枚举
     */
    public void completePackNonRecursiveOne(int[] dpOne,int[] weight,int[] value,int count,int maxWeight) {
        //对一维数组F进行初始化
        for(int i = 0; i <= maxWeight; i++) {
            dpOne[i] = 0;
        }
        //注意边界问题，i是从1开始的
        for(int i = 1; i <= count; i++) {
            for(int j = 1; j <= maxWeight; j++) {
                for(int k = 1; k <= maxWeight/weight[i-1]; k++) {
                    if(j >= k * weight[i-1]) {
                        dpOne[j] = Math.max(dpOne[j - k * weight[i-1]] + k * value[i-1], dpOne[j]);
                    }else {
                        // 可以省略
                        dpOne[j]= dpOne[j];
                    }
                }
            }
        }

        //打印所有结果，我们要求的是F[V]
        for(int i = 0; i <= maxWeight; i++) {
            System.out.print(dpOne[i] + " ");
        }
        System.out.println();
    }


    /**
     * 一种O(V*N)的算法（最优的解法）
     */
    public void completePackNonRecursiveBest(int[] dpOne,int[] weight,int[] value,int count,int maxWeight) {
        //对一维数组F进行初始化
        for(int i = 0; i < maxWeight; i++) {
            dpOne[i] = 0;
        }

        for(int i = 1; i <= count; i++) {
            // 唯一不同的地方，j是正序遍历
            // 当剩余容量小于物体的容量不进行处理
            for(int j = weight[i-1]; j <= dpOne.length; j++) {
                dpOne[j] = Math.max(dpOne[j - weight[i-1]] + value[i-1], dpOne[j]);
            }
        }

        //打印所有结果，我们要求的是F[V]
        for(int i = 0; i < dpOne.length; i++) {
            System.out.print(dpOne[i] + " ");
        }
        System.out.println();
    }
}
