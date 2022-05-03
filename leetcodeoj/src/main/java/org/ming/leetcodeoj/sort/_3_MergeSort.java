package org.ming.leetcodeoj.sort;

import org.ming.common.BaseKit;

/**
 * 归并排序
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _3_MergeSort {

    public static void main(String[] args) {
        int[] array = new int[]{2, 1, 3, 4, 1, 0, -1, 8, 7, 1, -9};
        mergeSort0(array);
        BaseKit.print(array);
    }

    public static void mergeSort0(int[] array) {
        int len = array.length;
        int[] temp = new int[len];
        mergeSort(array, 0, len - 1, temp);
    }

    private static void mergeSort(int[] array, int left, int right, int[] temp) {
        // 1. 递归终止条件
        if (left == right) {
            return;
        }

        // 2. 拆分，对应「分而治之」算法的「分」
        int mid = left + (right - left) / 2;
        mergeSort(array, left, mid, temp);
        mergeSort(array, mid + 1, right, temp);
        // 3. 在递归函数调用完成以后还可以做点事情

        // 【合并两个有序数组】，对应「分而治之」的「合」
        mergeOfTwoSortedArray(array, left, mid, right, temp);
    }

    /**
     * 合并两个有序数组
     *
     * @param array
     * @param left
     * @param mid   mid 是第一个有序数组的最后一个元素的下标，即：[left..mid] 有序，[mid + 1..right] 有序
     * @param right
     * @param temp
     */
    private static void mergeOfTwoSortedArray(int[] array, int left, int mid, int right, int[] temp) {
        for (int i = left; i <= right; i++) {
            temp[i] = array[i];
        }
        // 左边排序数组
        int li = left;
        // 右边排序数组，始终+1
        int ri = mid + 1;

        int index = left;
        while (li <= mid && ri <= right) {
            if (temp[li] <= temp[ri]) {
                // 注意写成 < 就丢失了稳定性（相同元素原来靠前的排序以后依然靠前）
                array[index] = temp[li];
                index++;
                li++;
            } else {
                array[index] = temp[ri];
                index++;
                ri++;
            }
        }
        while (li <= mid) {
            array[index] = temp[li];
            index++;
            li++;
        }
        while (ri <= right) {
            array[index] = temp[ri];
            index++;
            ri++;
        }
    }

}
