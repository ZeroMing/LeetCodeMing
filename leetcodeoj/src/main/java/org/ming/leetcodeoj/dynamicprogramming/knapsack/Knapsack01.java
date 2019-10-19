package org.ming.leetcodeoj.dynamicprogramming.knapsack;

import java.util.Scanner;

/**
 * 01背包
 * @author: LeoLee
 * @date: 2019/10/17 16:35
 */
public class Knapsack01 {


    public static void main(String[] args) {
        Knapsack01 knapsack01 = new Knapsack01();

        int[] weight = {2,3,4,5};
        int[] value = {3,4,5,6};
        int count = 4;
        int maxWeight = 8;
        // 动态规划记忆数组
        int[][] dp = new int[count+1][maxWeight+1];
        // 一维数组
        int[] knapsack_one = new int[maxWeight+1];
        // int maxValue = knapsack01.pack01ViolenceRecursive(0, 10);
        // System.out.println("最大价值:"+maxValue);
        // 暴力生成
        int[][] array = new int[16][4];
        int maxValue = knapsack01.pack01BruteForce(array, weight, value, 4, maxWeight);
        //System.out.println(maxValue);
        // System.out.println("暴力破解背包: " + maxValue);
        maxValue = knapsack01.knapsack01Dp(dp,weight,value,count,maxWeight);
        // knapsack01.print(dp,count,maxWeight);
        System.out.println("二维数组背包: " + maxValue);
        maxValue = knapsack01.knapsack01One(knapsack_one,weight,value,count,maxWeight);
        System.out.println("一维数组背包: "+ maxValue);
    }


    /**
     * 暴力破解 01 背包
     * @param index 所选物品下标
     * @param remainingCapacity 剩余背包容量
     * @return
     */
    public int   pack01ViolenceRecursive(int index,int[] weight,int[] value,int count,int remainingCapacity){
        int  res;
        // 没有剩余的空间
        if(index == count){
            res = 0;

        } else if(remainingCapacity < weight[index]){
            // 剩余空间小于该物品的体积
            res = pack01ViolenceRecursive(index+1,weight,value,count,remainingCapacity);

        } else {
            // 剩余空间大于该物品的体积
            // 一个物品选还是不选。比较
            res = Math.max(pack01ViolenceRecursive(index+1,weight,value,count,remainingCapacity),pack01ViolenceRecursive(index+1,weight,value,count,remainingCapacity-weight[index])+value[index]);
        }
        return res;
    }



    /**
     * 暴力破解 01 背包
     * 穷举法
     * @param array
     * @param weight
     * @param value
     * @param maxWeight
     * @return
     */
    public int   pack01BruteForce(int[][] array,int[] weight,int[] value,int count,int maxWeight){
        int maxValue = 0;
        // 求所有子集组合
        subArray(array,count);
        // 行数
        int rows = array.length;
        // 列数
        int cols = array[0].length;
        for(int i =0; i < rows;i ++){
            // 暂时计算当前选中背包实例物品的总重量，初始化为0
            int tempWeight = 0;
            // 暂时计算当前选中背包实例物品的总价值，初始化为0
            int tempSumValue = 0;
            for(int j=0;j < cols;j++){
                System.out.print(array[i][j]+" ");
                tempWeight += array[i][j]*weight[j];
                tempSumValue += array[i][j]*value[j];
            }
            System.out.print("总重量为："+tempWeight);
            if(tempWeight <= maxWeight) {
                System.out.print("总价值为：" + tempSumValue);
            } else{
                System.out.print("不可行（超出背包最大承重）");
            }
            if(tempWeight <= maxWeight && tempSumValue > maxValue) {
                maxValue = tempSumValue;
            }
            System.out.println();
        }
        System.out.println("穷举查找得知，最优解的总价值为："+maxValue);
        return maxValue;
    }




    public void subArray(int[][] array,int count){
        // 2 ^ N 个子集
        // 总共有2^n个子集，需要进行2^n次循环，及数组A有2^n行
        for(int i = 0;i < Math.pow(2, count);i++){
            int temp1 = i;
            // 数组A有n列，每一列代表一个物品
            for(int j = 0;j < count;j++){
                int temp2 = temp1 % 2;
                array[i][j] = temp2;
                temp1 = temp1 / 2;
            }
        }

        printArray(array);
    }

    private void printArray(int[][] array) {
        int rows = array.length;
        int cols = array[0].length;
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                System.out.print(array[i][j] + " ");
            }
            System.out.println("");
        }
    }


    /**
     * 二维数据组
     * 动态规划
     */
    public int knapsack01Dp(int[][] dp,int[] weight,int[] value,int count,int maxWeight){

        //初始化第一列和第一行
        for(int i=0;i<dp.length;i++){
            dp[i][0] = 0;
        }
        for(int i=0;i<dp[0].length;i++){
            dp[0][i] = 0;
        }

        // 第 1 个物品 开始
        for(int i = 1;i <= count;i++){
            // 重点: >>>>> 这里 正序与倒序 无区别
            for(int j = 1;j <= maxWeight;j++){
                // 当剩余负载 大于 所选物品的重量
                if(j >= weight[i-1]){
                    // 比较 选该物品与不选该物品的收益大小
                    dp[i][j] = Math.max(dp[i-1][j-weight[i-1]]+value[i-1],dp[i-1][j]);
                }else{
                    // 否则不选该物品
                    dp[i][j] = dp[i-1][j];
                }
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return dp[count][maxWeight];
    }

    /**
     * 一维数组
     *
     */
    public int knapsack01One(int[] knapsack_one,int[] weight,int[] value,int count,int maxCapacity){
        // 第i个物品
        for(int i= 0;i < count;i++){
            // 容量
            for(int j= maxCapacity;j>=1;j--){
                if(j >= weight[i] && knapsack_one[j] < knapsack_one[j-weight[i]]+value[i] ){
                    knapsack_one[j] = knapsack_one[j-weight[i]]+value[i];
                }
            }
        }
        return knapsack_one[maxCapacity];
    }



    public void print(int[][] dp,int count,int maxCapacity){
        for(int i=0;i<=count;i++){
            for(int j=0;j<=maxCapacity;j++){
                System.out.print(dp[i][j] + " ");
            }
            System.out.println("");
        }
    }


//    public void init(){
//        Scanner sc = new Scanner(System.in);
//        count = sc.nextInt();
//        maxCapacity = sc.nextInt();
//        //下标从1开始，表示第1个物品
//        weight = new int[count + 1];
//        value = new int[count + 1];
//
//        for(int i = 1; i <= count; i++) {
//            weight[i] = sc.nextInt();
//        }
//
//        for(int i = 1; i <= count; i++) {
//            value[i] = sc.nextInt();
//        }
//    }
}
