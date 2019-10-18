package org.ming.leetcodeoj.dynamicprogramming.backpack;

import java.util.Scanner;

/**
 * @Description:
 * @Author: LeoLee
 * @Date: 2019年10月17 23时27分
 */
public class BackPackWhole {
    // N表示物体的个数，V表示背包的载重
    int N,V;
    //用于存储每个物体的重量，下标从1开始
    private int[] weight;
    //存储每个物体的收益，下标从1开始
    private int[] value;
    //降成二维数组，用来保存每种状态下的最大收益
    private int[][] F;

    //降成一维数组，用来保存每种状态下的最大收益
    private int[] F1;



//    public static void main(String[] args){
//        int[] weight = {3,4,6,2,5};
//        int[] val = {6,8,7,5,9};
//        int maxw = 10;
//        int[] f = new int[maxw+1];
//        for(int i=0;i<f.length;i++){
//            f[i] = 0;
//        }
//        for(int i=0;i<val.length;i++){
//            for(int j=weight[i];j<f.length;j++){
//                f[j] = Math.max(f[j], f[j-weight[i]]+val[i]);
//            }
//        }
//        System.out.println(f[maxw]);
//    }

    public static void main(String[] args) {
        BackPackWhole backPackWhole = new BackPackWhole();
        backPackWhole.init();
        backPackWhole.CompletePackNonRecursive();
    }

    /**
     * 一维
     */
    public void CompletePackNonRecursive1() {
        //对一维数组F进行初始化
        for(int i = 0; i <= V; i++) {
            F1[i] = 0;
        }

        //注意边界问题，i是从1开始的
        for(int i = 1; i <= N; i++) {
            for(int j = V; j >= 0; j--) {
                for(int k = 1; k <= V/weight[i]; k++) {
                    if(j >= k * weight[i]) {
                        F1[j] = Math.max(F1[j - k * weight[i]] + k * value[i], F1[j]);
                    }else {
                        //可以省略
                        F1[j]= F1[j];
                    }
                }
            }
        }

        //打印所有结果，我们要求的是F[V]
        for(int i = 0; i <= V; i++) {
            System.out.print(F[i] + " ");
        }
        System.out.println();
    }


    public void CompletePackNonRecursive() {
        //对二维数组F进行初始化
        for(int i=0;i<=V;i++){
            F[0][i] =  0;
        }
        // 注意边界问题，i是从1开始的
        for(int i = 1; i <= N; i++) {
            // 容量
            for (int j = 0; j <= V; j++) {
                // 最大容纳的个数
                for (int k = 0; k <= V / weight[i]; k++) {
                    if(j >=  k * weight[i]){
                        //注意：状态转移方程是F[i][j]，而不是F[i - 1][j]
                        //因为这时放k个第i个物品，之后还可能继续放这个物体，所以应是F[i][j]
                        F[i][j] = Math.max(F[i - 1][j - k * weight[i]] + k * value[i], F[i][j]);
                    }else{
                        //可以省略，这里为什么不是F[i - 1][j]
                        //因为刚开始k=0，j >= 0 * weight[i]肯定成立，此时F[i][j] = F[i - 1][j]。
                        F[i][j] = F[i][j];
                    }
                }
            }

        }

        //打印所有结果，我们要求的是F[N][V]
        for(int i = 0; i <= N; i++) {
            for(int j = 0; j <= V; j++) {
                System.out.print(F[i][j] + " ");
            }
            System.out.println();
        }


    }


    public void init() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        V = sc.nextInt();

        //下标从1开始，表示第1个物品
        weight = new int[N + 1];
        value = new int[N + 1];
        F= new int[N + 1][V + 1];

        for(int i = 1; i <= N; i++) {
            weight[i] = sc.nextInt();
        }

        for(int i = 1; i <= N; i++) {
            value[i] = sc.nextInt();
        }
    }




    // 一种O(V*N)的算法（最优的解法）

    public void CompletePackNonRecursiveBest() {
        //对一维数组F进行初始化
        for(int i = 0; i <= V; i++) {
            F1[i] = 0;
        }

        //注意边界问题，i是从1开始的
        for(int i = 1; i <= N; i++) {
            //唯一不同的地方，j是正序遍历
            for(int j = 0; j <= V; j++) {
                if(j >=  weight[i]) {
                    F1[j] = Math.max(F1[j - weight[i]] + value[i], F1[j]);
                }else {
                    //可以省略
                    F1[j]= F1[j];
                }
            }
        }

        //打印所有结果，我们要求的是F[V]
        for(int i = 0; i <= V; i++) {
            System.out.print(F[i] + " ");
        }
        System.out.println();
    }


}
