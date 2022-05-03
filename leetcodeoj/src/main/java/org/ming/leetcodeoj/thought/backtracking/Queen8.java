package org.ming.leetcodeoj.thought.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 显然，每个皇后必须位于不同行和不同列，因此将 NN 个皇后放置在 N \times NN×N 的棋盘上，一定是每一行有且仅有一个皇后，每一列有且仅有一个皇后，且任何两个皇后都不能在同一条斜线上。基于上述发现，可以通过回溯的方式寻找可能的解。
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class Queen8 {

    public static void main(String[] args) {
        System.out.println(solveNQueens(8).size());
    }

    /**
     * 棋盘大小
     *
     * @param n 8
     * @return
     */
    public static List<List<String>> solveNQueens(int n) {
        //生成N*N的棋盘
        char[][] arr = new char[n][n];
        //填充棋盘，每个格子默认是“。”表示没有放置皇后
        for (int i = 0; i < n; ++i) {
            Arrays.fill(arr[i], '.');
        }
        List<List<String>> res = new ArrayList<>();
        dfs(arr, 0, n, res);
        return res;
    }

    private static void dfs(char[][] arr, int x, int n, List<List<String>> res) {
        //x是从0开始计算的，当x==n时所有行的皇后都放置完毕，此时记录结果
        if (x == n) {
            List<String> ans = new ArrayList<>();
            for (int i = 0; i < n; ++i) {
                StringBuilder tmp = new StringBuilder();
                for (int j = 0; j < n; ++j) {
                    if (arr[i][j] == '.') {
                        tmp.append(".");
                    } else {
                        tmp.append("Q");
                    }
                }
                ans.add(tmp.toString());
            }
            res.add(ans);
            return;
        }
        // 遍历每一列
        for (int y = 0; y < n; ++y) {
            // 检查[x,y]这一坐标是否可以放置皇后
            // 如果满足条件，就放置皇后，并继续检查下一行
            if (check(arr, x, y, n)) {
                arr[x][y] = 'Q';
                // 递归 next 行
                dfs(arr, x + 1, n, res);
                // 撤销，可能会失败
                arr[x][y] = '.';
            }
        }
    }


    /**
     * 检查当前的行和列是否可以放置皇后
     *
     * @param arr
     * @param x
     * @param y
     * @param n
     * @return
     */
    private static boolean check(char[][] arr, int x, int y, int n) {
        //检查竖着的一列是否有皇后
        for (int i = 0; i < x; ++i) {
            if (arr[i][y] == 'Q') {
                return false;
            }
        }

        //检查左上到右下的斜边是否有皇后
        int i = x - 1, j = y - 1;
        while (i >= 0 && j >= 0) {
            if (arr[i][j] == 'Q') {
                return false;
            }
            --i;
            --j;
        }

        //检查左下到右上的斜边是否有皇后
        i = x - 1;
        j = y + 1;
        while (i >= 0 && j < n) {
            if (arr[i][j] == 'Q') {
                return false;
            }
            --i;
            ++j;
        }
        return true;
    }


}
