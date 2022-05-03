package org.ming.leetcodeoj.sort;

import org.ming.common.BaseKit;

/**
 * 计数排序
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _11_CountSort {
    public static void main(String[] args) {
        int[] array = new int[]{2, 1, 3, 4, 1, 0, -2, -8, 7, 1, 2};
        countSort0(array);
        //
        BaseKit.print(array);
    }

    public static void countSort0(int[] array) {
        int max = array[0];
        int min = array[0];
        // 1. 求最大值
        for (int i = 1; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
            }
            if (min > array[i]) {
                min = array[i];
            }
        }
        // int len = max + 1;
        // 1.1 优化：数组过长，最大值减 - 最小值
        int len = max - min + 1;

        // 1.2 负数问题
        // 每个计数值，减去 min，再次计算的时候，回滚加上 min

        // 2.根据数列最大值确定统计数组的长度
        int[] countArray = new int[len];

        // 3. 遍历原数组，填充
        for (int i = 0; i < array.length; i++) {
            countArray[array[i] - min]++;
        }

        // 4. 循环输出
        int index = 0;
        int[] sortedArray = new int[array.length];
        for (int i = 0; i < countArray.length; i++) {
            for (int j = 0; j < countArray[i]; j++) {
                sortedArray[index++] = i + min;
            }
        }
        System.arraycopy(sortedArray, 0, array, 0, array.length);
    }
}
