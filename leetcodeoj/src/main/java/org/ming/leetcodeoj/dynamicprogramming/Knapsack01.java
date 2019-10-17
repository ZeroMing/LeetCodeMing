package org.ming.leetcodeoj.dynamicprogramming;

/**
 * @author: LeoLee
 * @date: 2019/10/17 16:35
 */
public class Knapsack01 {


    int count = 4;
    int maxCapacity = 7;
    int[] capacity = new int[]{1,2,3,4,5};
    int[] weight = new int[]{1,2,3,4,5};
    int[][] knapsack = new int[5][8];
    // 一维数组
    int[] knapsack_one = new int[8];



    public static void main(String[] args) {
        Knapsack01 knapsack01 = new Knapsack01();
        knapsack01.knapsack01Simple();
        knapsack01.print();

    }


    public void knapsack01Simple(){
        // 第i个物品
        for(int i=1;i<=count;i++){
            // 容量
            for(int j=maxCapacity;j>=0;j--){
                if(j >= capacity[i]){
                    knapsack[i][j] = Math.max(knapsack[i-1][j-capacity[i]]+weight[i],knapsack[i-1][j]);
                }else{
                    knapsack[i][j] = knapsack[i-1][j];
                }
            }
        }
    }


    public void knapsack01_one(){
        // 第i个物品
        for(int i=1;i<=count;i++){
            // 容量
            for(int j=maxCapacity;j>=0;j--){
                if(knapsack_one[j] <= knapsack_one[j-capacity[i]]+weight[i] && j >= capacity[i]){
                    //如果值得改变并且j的空间还装得下就赋新值
                    knapsack_one[j]=knapsack_one[j-capacity[i]]+weight[i];
                }
            }
        }
    }





    public void print(){
        for(int i=0;i<count;i++){
            for(int j=0;j<maxCapacity;j++){
                System.out.print(knapsack[i][j] + " ");
            }
            System.out.println("");
        }
    }



}
