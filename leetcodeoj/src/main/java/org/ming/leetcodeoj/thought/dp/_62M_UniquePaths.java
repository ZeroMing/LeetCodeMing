package org.ming.leetcodeoj.thought.dp;

/**
 * 62. 不同路径
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _62M_UniquePaths {
    public static void main(String[] args) {
        int m = 3;
        int n = 3;
        System.out.println(uniquePaths1(m, n));
    }

    /**
     * 62. 不同路径
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
     * 问总共有多少条不同的路径？
     * <p>
     * 示例 1：
     * 输入：m = 3, n = 7
     * 输出：28
     * <p>
     * 示例 2：
     * 输入：m = 3, n = 2
     * 输出：3
     * 解释：
     * 从左上角开始，总共有 3 条路径可以到达右下角。
     * 1. 向右 -> 向下 -> 向下
     * 2. 向下 -> 向下 -> 向右
     * 3. 向下 -> 向右 -> 向下
     * <p>
     * 示例 3：
     * 输入：m = 7, n = 3
     * 输出：28
     * <p>
     * 示例 4：
     * 输入：m = 3, n = 3
     * 输出：6
     * <p>
     * 提示：
     * 1 <= m, n <= 100
     * 题目数据保证答案小于等于 2 * 109
     */


    /**
     * 动态规划
     * 1. 确定dp数组下标含义 dp[i][j] 到每一个坐标可能的路径种类
     * 2. 递推公式 dp[i][j] = dp[i-1][j] dp[i][j-1]
     * 3. 初始化 dp[i][0]=1 dp[0][i]=1 初始化横竖就可
     * 4. 遍历顺序 一行一行遍历
     * 5. 推导结果
     *
     * @param m 行
     * @param n 列
     * @return
     */
    public static int uniquePaths1(int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }
        // 到每一个坐标可能的路径种类
        int[][] array = new int[m][n];
        // 初始化 dp[i][0]=1 dp[0][i]=1 初始化横竖就可
        // 初始化，行首和列首
        for (int i = 0; i < m; i++) {
            array[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            array[0][i] = 1;
        }
        // 遍历顺序 一行一行遍历
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                array[i][j] = array[i - 1][j] + array[i][j - 1];
            }
        }
        return array[m - 1][n - 1];
    }


}
