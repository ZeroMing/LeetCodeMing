package org.ming.leetcodeoj.array;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _59M_GenerateMatrix {
    public static void main(String[] args) {
        int[][] ints = generateMatrix(3);
        System.out.println(ints);
    }

    public static int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        int startX = 0, startY = 0;
        int loop = n / 2;
        int mid = n / 2;
        int value = 1;
        int offset = 1;
        int i, j;
        while (loop > 0) {
            i = startX;
            j = startY;
            // 模拟上侧从左到右
            for (; j < startY + n - offset; j++) {
                ans[startX][j] = value++;
            }

            // 模拟右侧从上到下
            for (; i < startX + n - offset; i++) {
                ans[i][j] = value++;
            }

            // 模拟下侧从右到左
            for (; j > startY; j--) {
                ans[i][j] = value++;
            }

            // 模拟左侧从下到上
            for (; i > startX; i--) {
                ans[i][j] = value++;
            }

            loop--;
            startX += 1;
            startY += 1;
            offset += 2;
        }
        if (n % 2 == 1) {
            ans[mid][mid] = value;
        }
        return ans;
    }
}
