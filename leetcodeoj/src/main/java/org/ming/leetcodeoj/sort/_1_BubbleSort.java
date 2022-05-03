package org.ming.leetcodeoj.sort;

import org.ming.common.BaseKit;

/**
 * 冒泡排序
 * <p>
 * 算法名称	最好时间复杂度	最坏时间复杂度	平均时间复杂度	空间复杂度	是否稳定
 * 冒泡排序	O(n)	O(n^2)	O(n^2)	O(1)	稳定
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _1_BubbleSort {

    public static void main(String[] args) {
        int[] array = new int[]{2, 1, 3, 4, 1, 0, -1, 8, 7, 1, -9};
        // bubbleSort0(array);
        bubbleSort1(array);
        //
        BaseKit.print(array);
    }


    /**
     * @param array
     */
    public static void bubbleSort0(int[] array) {
        //
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    BaseKit.swap(array, i, j + 1);
                }
            }
        }
    }

    /**
     * 冒泡排序优化
     *
     * @param array
     */
    public static void bubbleSort1(int[] array) {
        //
        for (int i = 0; i < array.length - 1; i++) {
            boolean swap = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    BaseKit.swap(array, i, j + 1);
                    swap = true;
                }
            }
            if (!swap) {
                break;
            }
        }
    }

    public static int[] bubbleSort2(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; ++i) {
            for (int j = i + 1; j < len; ++j) {
                if (nums[i] > nums[j]) {
                    BaseKit.swap(nums, i, j);
                }
            }
        }
        return nums;

    }

}
