package org.ming.leetcodeoj.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _54M_SpiralOrder {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 2, 3, 4, 5},
                {16, 17, 18, 19, 6},
                {15, 24, 25, 20, 7},
                {14, 23, 22, 21, 8},
                {13, 12, 11, 10, 9}
        };
        System.out.println(spiralOrder2(matrix));
    }


    public static List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> order = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }
        int rows = matrix.length, columns = matrix[0].length;
        // 左，右，上，下
        int left = 0, right = columns - 1, top = 0, bottom = rows - 1;
        while (left <= right && top <= bottom) {
            // 从左往右
            for (int column = left; column <= right; column++) {
                order.add(matrix[top][column]);
            }
            // 从上往下
            for (int row = top + 1; row <= bottom; row++) {
                order.add(matrix[row][right]);
            }
            if (left < right && top < bottom) {
                // 从右往左
                for (int column = right - 1; column > left; column--) {
                    order.add(matrix[bottom][column]);
                }
                // 从下往上
                for (int row = bottom; row > top; row--) {
                    order.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return order;
    }
}
