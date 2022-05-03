package org.ming.leetcodeoj.thought.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 51. N 皇后
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _51H_SolveNQueens {
    public static void main(String[] args) {
        System.out.println(solveNQueens(4));
    }

    /**
     * 51. N 皇后
     * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
     * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
     * <p>
     * 示例 1：
     * 输入：n = 4
     * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
     * 解释：如上图所示，4 皇后问题存在两个不同的解法。
     * <p>
     * 示例 2：
     * 输入：n = 1
     * 输出：[["Q"]]
     * <p>
     * 提示：
     * 1 <= n <= 9
     */

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        char[][] chessBoard = new char[n][n];
        for (char[] c : chessBoard) {
            Arrays.fill(c, '.');
        }
        backtracking(n, 0, ans, chessBoard);
        return ans;
    }

    /**
     * 回溯
     *
     * @param n
     * @param row
     * @param ans
     * @param chessBoard
     */
    private static void backtracking(int n, int row, List<List<String>> ans, char[][] chessBoard) {
        if (row == n) {
            ans.add(Arrays2List(chessBoard));
        }
        // 某一行，进行 迭代列
        for (int col = 0; col < n; col++) {
            // 不合法，直接跳过
            if (isValid(row, col, n, chessBoard)) {
                // 选择处理
                chessBoard[row][col] = 'Q';
                backtracking(n, row + 1, ans, chessBoard);
                // 回溯处理
                chessBoard[row][col] = '.';
            }
        }
    }

    /**
     * 判断棋子的合法性
     *
     * @param row
     * @param col
     * @param n
     * @param chessBoard
     * @return
     */
    private static boolean isValid(int row, int col, int n, char[][] chessBoard) {

        // 新的一行的第一个元素，自然不需要检查

        // 检查列是否存在皇后
        for (int i = 0; i < row; i++) {
            if (chessBoard[i][col] == 'Q') {
                return false;
            }
        }

        // 检查45度对角线
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (chessBoard[i][j] == 'Q') {
                return false;
            }
        }

        // 检查 135度角是否有皇后
        for (int i = row - 1, j = col + 1; i >= 0 && j <= n - 1; i--, j++) {
            if (chessBoard[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    private static List<String> Arrays2List(char[][] chessBoard) {
        List<String> list = new ArrayList<>(chessBoard.length);
        for (char[] c : chessBoard) {
            list.add(String.copyValueOf(c));
        }
        return list;
    }

}
