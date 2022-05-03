package org.ming.leetcodeoj.thought.dp;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _63M_UniquePathsWithObstacles {

    public static void main(String[] args) {
        int[][] obstacleGrid = new int[][]{
                {0, 1}, {0, 0}
        };
        System.out.println(uniquePathsWithObstacles(obstacleGrid));
    }

    /**
     * 63. 不同路径 II
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
     * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
     * 网格中的障碍物和空位置分别用 1 和 0 来表示。
     * <p>
     * 示例 1：
     * 输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
     * 输出：2
     * 解释：
     * 3x3 网格的正中间有一个障碍物。
     * 从左上角到右下角一共有 2 条不同的路径：
     * 1. 向右 -> 向右 -> 向下 -> 向下
     * 2. 向下 -> 向下 -> 向右 -> 向右
     * 示例 2：
     * 输入：obstacleGrid = [[0,1],[0,0]]
     * 输出：1
     * <p>
     * 提示：
     * m == obstacleGrid.length
     * n == obstacleGrid[i].length
     * 1 <= m, n <= 100
     * obstacleGrid[i][j] 为 0 或 1
     */

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // 1. 定义dp数组 到每一个坐标可能的路径种类
        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        // 2. 确定递推公式 dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        // 3. 初始化
        for (int i = 0; i < obstacleGrid.length && obstacleGrid[i][0] == 0; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < obstacleGrid[0].length && obstacleGrid[0][i] == 0; i++) {
            dp[0][i] = 1;
        }
        // 4. 确定遍历顺序
        for (int i = 1; i < obstacleGrid.length; i++) {
            for (int j = 1; j < obstacleGrid[0].length; j++) {
                // 如果有障碍物，设置为0，不可达
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        // 5. 举例推导dp数组，画图最好
        return dp[dp.length - 1][dp[0].length - 1];
    }
}
