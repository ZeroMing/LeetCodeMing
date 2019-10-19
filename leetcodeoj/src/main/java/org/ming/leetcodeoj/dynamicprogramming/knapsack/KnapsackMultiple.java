package org.ming.leetcodeoj.dynamicprogramming.knapsack;

/**
 * 多重背包
 * @author: LeoLee
 * @date: 2019/10/19 18:31
 */
public class KnapsackMultiple {

    public static void main(String[] args) {
        KnapsackMultiple knapsackMultiple = new KnapsackMultiple();
        // N表示物体的个数，V表示背包的载重
        int maxWeight = 10;
        //用于存储每个物体的重量，下标从1开始
        int[] weight = {1,3,4,10};
        //存储每个物体的收益，下标从1开始
        int[] value = {3,4,2,6};
        // 每种物品的数量
        int[] count = {1,3,4,5};
        int maxValue = knapsackMultiple.knapsackMultipleTwo(weight, value, count, maxWeight);
        System.out.println("二维多重数据:" +maxValue);
        maxValue = knapsackMultiple.knapsackMultipleOne(weight, value, count, maxWeight);
        System.out.println("一维多重背包: "+ maxValue);

    }


    public int knapsackMultipleOne(int[] weight,int[]  val,int[] count,int maxWeight) {
        //降成一维数组，用来保存每种状态下的最大收益
        int[] dpOne = new int[maxWeight+1];
        for (int i=1;i<weight.length;i++){
            for (int j=maxWeight;j>=weight[i];j--){
                for (int k=0;k<=count[i];k++){
                    dpOne[j]=(j-k*weight[i]>=0 && dpOne[j]<dpOne[j-k*weight[i]]+k*val[i])? dpOne[j-k*weight[i]]+k*val[i]:dpOne[j];
                }
            }
        }
        return dpOne[maxWeight];
    }

    public int knapsackMultipleTwo(int[] weight,int[]  val,int[] count,int maxWeight) {
        int[][] dp=new int[weight.length][maxWeight+1];
        for (int i=1;i<weight.length;i++){
            for (int j=weight[i];j<=maxWeight;j++){
                for (int k=0;k<=count[i];k++){
                    dp[i][j]=(j-k*weight[i]>=0)&&(dp[i][j]<dp[i-1][j-k*weight[i]]+k*val[i])?dp[i-1][j-k*weight[i]]+k*val[i]:dp[i][j];
                }
            }
        }
        return dp[weight.length-1][maxWeight];
    }



}
