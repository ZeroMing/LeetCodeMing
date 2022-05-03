package org.ming.leetcodeoj.sort;

import org.ming.common.BaseKit;

/**
 * 快速排序
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _2_QuickSort {
    public static void main(String[] args) {
        int[] array = new int[]{3, 1, 2, 0, 5, 7, 2};
        quickSort0(array);
        BaseKit.print(array);
    }


    public static void quickSort1(int[] array) {
        // 1. 分区
        // 基数 pivot的选择：
        // 1. 第一个 2. 最后一个 3. 随机一个
        // 如果区域内的数字少于 2 个，退出递归
        quickSort1_1(array, 0, array.length - 1);
    }

    private static void quickSort1_1(int[] array, int start, int end) {
        if (start <= end) {
            return;
        }
        int middle = partition1(array, start, end);
        quickSort1_1(array, start, middle - 1);
        quickSort1_1(array, middle + 1, end);
    }

    private static int partition1(int[] array, int start, int end) {
        int pivot = array[start];
        int left = start + 1;
        int right = end;
        while (left < right) {
            while (left < right && array[left] <= pivot) {
                left++;
            }

            while (left < right && array[right] >= pivot) {
                right--;
            }

            // 交换
            if (left < right) {
                BaseKit.swap(array, left, right);
                left++;
                right--;
            }
        }
        if (left == right && array[right] > pivot) {
            right--;
        }
        // 将基数和轴交换
        BaseKit.swap(array, start, right);
        // 返回右值
        return right;
    }


    public static void quickSort0(int[] array) {
        // 1. 分区
        // 基数 pivot的选择：
        // 1. 第一个 2. 最后一个 3. 随机一个
        // 如果区域内的数字少于 2 个，退出递归
        quickSort(array, 0, array.length - 1);
    }

    public static void quickSort(int[] arr, int start, int end) {
        // 如果区域内的数字少于 2 个，退出递归
        if (start >= end) {
            return;
        }
        // 将数组分区，并获得中间值的下标
        int middle = partition(arr, start, end);
        // 对左边区域快速排序
        quickSort(arr, start, middle - 1);
        // 对右边区域快速排序
        quickSort(arr, middle + 1, end);
    }

    /**
     * 将 arr 从 start 到 end 分区，左边区域比基数小，右边区域比基数大，然后返回中间值的下标
     *
     * @param array
     * @param start
     * @param end
     * @return 中间值的下标
     */
    private static int partition(int[] array, int start, int end) {
        // 取第一个数为基数
        int pivot = array[start];
        // 从第二个数开始分区
        int left = start + 1;
        // 右边界
        int right = end;
        // 双指针分区算法
        while (left < right) {
            // 左边小
            while (left < right && array[left] <= pivot) {
                left++;
            }
            // 右边大
            while (left < right && array[right] >= pivot) {
                right--;
            }
            // 交换
            if (left < right) {
                BaseKit.swap(array, left, right);
                left++;
                right--;
            }
        }
        // 如果 left 和 right 相等，单独比较 arr[right] 和 pivot
        if (left == right && array[right] > pivot) {
            right--;
        }
        // 将基数和轴交换
        BaseKit.swap(array, start, right);
        // 返回右值
        return right;
    }


}
