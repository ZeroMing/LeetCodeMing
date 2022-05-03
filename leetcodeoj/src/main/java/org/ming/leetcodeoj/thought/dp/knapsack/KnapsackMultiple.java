package org.ming.leetcodeoj.thought.dp.knapsack;

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
        maxValue = knapsackMultiple.knapsackMultipleQueue(weight, value, count, weight.length, maxWeight);
        System.out.println("单调队列优化多重背包: "+ maxValue);

    }


    /**
     * 二维
     * 01拆分
     * @param weight
     * @param val
     * @param count
     * @param maxWeight
     * @return
     */
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


    public int knapsackMultipleQueue(int[] weight,int[]  val,int[] count,int n,int maxWeight) {
        int[] dp = new int[maxWeight+1];
        int[] queue = new int[maxWeight+1];
        for(int i=0;i<=maxWeight;i++){
            dp[i]=0;
        }
        for(int i=1;i<=n;i++) {
            // 小于体积
            if(count[i-1] > maxWeight/weight[i-1]){
                count[i-1] = maxWeight/weight[i-1];
            }

            for (int mo = 0; mo < weight[i-1]; mo++) {
                int head =1, tail = 0;
                for (int t = 0; t <= (maxWeight - mo) / weight[i-1]; t++) {
                    int tmp = dp[t * weight[i-1] + mo] - t * val[i-1];
                    while (head < tail && t - queue[head] > count[i-1]) {
                        //滑动区间长度不大于c，因为dp[t*v+b]-t*w既然存在，那么再加c区间的t*w的值肯定能取到
                        head++;
                    }
                    while (head < tail && queue[tail - 1] <= tmp) {
                        tail--;
                    }
                    queue[tail] = tmp;
                    queue[tail++] = t;
                    //因为dp中的是t*v+b,所以是q[l]+t*w
                    dp[t * weight[i-1] + mo] = queue[head] + t * val[i-1];
                }
            }
        }
        return dp[maxWeight];
    }

}
