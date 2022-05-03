package org.ming.leetcodeoj.sort;

import org.ming.common.BaseKit;

/**
 * 插入排序
 * 算法名称	最好时间复杂度	最坏时间复杂度	平均时间复杂度	空间复杂度	是否稳定
 * 直接插入排序	O(n^2)	O(n^2)	O(n^2)	O(1)	稳定排序
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _6_StraightInsertionSort {
    public static void main(String[] args) {
        int[] array = new int[]{2, 1, 3, 4, 1, 0, -1, 8, 7, 1, -9};
        straightInsertionSort0(array);
        BaseKit.print(array);
    }


    /**
     *
     */
    public static void straightInsertionSort0(int[] array) {
        int len = array.length;
        for (int i = 1; i < len; i++) {
            // 临时变量，否则使用下标，变量会调整
            int temp = array[i];
            int j;
            for (j = i - 1; j >= 0; j--) {
                // 如果仍然大于前面的值，整体向向后移动
                if (temp < array[j]) {
                    array[j + 1] = array[j];
                } else {
                    break;
                }
            }
            //插入到合适位置，这也就是我们没有在 for 循环内定义变量的原因
            array[j + 1] = temp;
        }
    }


}
