package org.ming.leetcodeoj.thought.dp;

/**
 * 完全背包
 * @author: LeoLee
 * @date: 2019/10/17 18:33
 */
public class KnapsackWhole {

    // 备忘录表
    public static int[][] mem;
    // 标记函数表
    public static int[][] mark;

    public static void main(String[] args) {

        int n = 4;
        int d = 10;
        int [] w = {2,3,4,7};
        int [] v = {1,3,5,9};

        mem = new  int[n+1][d+1];
        mark = new int[n+1][d+1];

        int maxValue = completelyBackpack(w,v,n,d);
        System.out.printf("背包最大值为：%d\n",maxValue);
        System.out.printf("备忘录表为：\n");

        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < d + 1; j++) {
                System.out.printf("%d ",mem[i][j]);
            }
            System.out.println();
        }


        System.out.println("标记函数表尾：");
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < d + 1; j++) {
                System.out.printf("%d ",mark[i][j]);
            }
            System.out.println();
        }

        // 追踪解 且 初始化为 0
        int []res = new int[n+1];
        traceSolution(res,n,d,w);
        System.out.println("背包装入各个物品的数量为：");
        for (int i = 1; i < n + 1; i++) {
            System.out.printf("%d ",res[i]);
        }


    }

    public static void traceSolution(int []res,int n,int d,int []w){
        int y = d;
        for (int i = n; i >0 ;) {
            int temp = mark[i][y];
            while(temp == i){
                // i-1 符合w的下标
                y = y-w[i-1];
                res[i]++;
                temp = mark[i][y];
            }
            i = mark[i][y];
        }
    }

    private static int completelyBackpack(int[] w, int[] v, int n, int d) {
        // F_k(y) = max{F_{k-1}(y), F_k(y-w_k)+v_k }
        // i表示 前i个 物品放入背包
        for (int i = 1; i <= n; i++) {
            // j 表示  背包重量为j
            for (int j = 1; j <= d; j++) {
                int not = mem[i-1][j];
                // w[i-1]是因为 w下标从0 开始，而i从1开始
                int in;
                if (j-w[i-1] < 0){
                    in = Integer.MIN_VALUE;
                }
                else{
                    in = mem[i][j-w[i-1]] + v[i-1];
                }

                mem[i][j] = Math.max(not,in);
                // 根据标记函数的定义来写
                if (not > in){
                    mark[i][j] = mark[i-1][j];
                }
                else{
                    mark[i][j] = i;
                }
            }
        }
        return mem[n][d];
    }

}
