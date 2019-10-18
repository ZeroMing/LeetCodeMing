package org.ming.leetcodeoj.dynamicprogramming;

/**
 * @Description:
 * @Author: LeoLee
 * @Date: 2019年10月16 22时25分
 */
public class Bag01 {
    // 体积
    static int[]  w = new int[]{0,2,3,4,5};
    // 价值
    static int[]  v = new int[]{0,3,4,5,6};
    // 容量
    static int bagC = 8;
    static int[][] dp = new int[5][9];
    //
    static int[] item = new int[5];

    public static void main(String[] args) {

        findMax();
        findWhat(4, 8);
        print();


    }


    static void findMax() {
        for(int i = 1;i < w.length;i++){
            for(int j = 1;j <= bagC;j++){
                if(j < w[i]){
                    dp[i][j] =dp[i-1][j];
                }
                else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-w[i]]+v[i]);
                }

            }

        }
    }


    static void findWhat(int i, int j) {				//最优解情况
        if (i > 0) {
            if (dp[i][j] == dp[i - 1][j]) {
                item[i] = 0;
                findWhat(i - 1, j);
            }
            else if (j - w[i] >= 0 && dp[i][j] == dp[i - 1][j - w[i]] + v[i]) {
                item[i] = 1;
                findWhat(i - 1, j - w[i]);
            }
        }
    }


    static void print() {
        for (int i = 0; i < 5; i++) {			//动态规划表输出
            for (int j = 0; j < 9; j++) {
                System.out.print(dp[i][j] +" ");
            }
            System.out.println("");
        }
        System.out.println("-------------------------");
        for (int i = 0; i < 5; i++) {        //最优解输出
            System.out.println(item[i]);
        }
    }


}
