package org.ming.leetcodeoj.sort;

import org.ming.common.BaseKit;

/**
 * 选择排序
 * 算法名称	最好时间复杂度	最坏时间复杂度	平均时间复杂度	空间复杂度	是否稳定
 * 简单选择排序	O(n^2)	O(n^2)	O(n^2)	O(1)	不稳定
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _4_SelectSort {

    public static void main(String[] args) {
        int[] array = new int[]{2, 1, 3, 4, 1, 0, -1, 8, 7, 1, -9};
        selectSort0(array);
        BaseKit.print(array);
    }


    public static void selectSort0(int[] array) {
        int len = array.length;
        for (int i = 0; i < len; i++) {
            int min = i;
            // 遍历到最小值
            for (int j = i + 1; j < len; j++) {
                if (array[i] > array[j]) {
                    min = j;
                }
            }
            if (min != i) {
                BaseKit.swap(array, i, min);
            }
        }
    }

}
