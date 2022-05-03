package org.ming.leetcodeoj.sort;

import org.ming.common.BaseKit;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 基数排序
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _9_RadixSort {
    public static void main(String[] args) {
        int[] array = new int[]{21, 11, 32, 44, 19, 180};
        // radixSort0(array);
        // radixSort1(array);
        radixSort2(array);
        BaseKit.print(array);
    }


    /**
     * @param array
     */
    public static void radixSort0(int[] array) {
        // 1. 找出最大值
        int max = Integer.MIN_VALUE;
        for (int i : array) {
            max = Math.max(max, i);
        }

        // 2. 计算最大值的最大位数
        int maxDigitLength = 0;
        while (max != 0) {
            max /= 10;
            maxDigitLength++;
        }
        // 3. 使用计数排序算法对基数进行排序
        int[] counting = new int[10];
        int[] result = new int[array.length];
        // 计算位于在哪个位上，初始为个位
        int position = 1;
        for (int i = 0; i < maxDigitLength; i++) {
            // 计算每个数组元素的当前位数的 数字，写在对应的位置上，比如 9 ，在 counting[9]+1
            for (int value : array) {
                int radix = value / position % 10;
                counting[radix]++;
            }
            // 利用count[i]来确定放置数据的位置
            for (int j = 1; j < counting.length; j++) {
                counting[j] += counting[j - 1];
            }

            // 使用倒序遍历的方式完成计数排序
            for (int j = array.length - 1; j >= 0; j--) {
                int radix = array[j] / position % 10;
                result[--counting[radix]] = array[j];
            }

            // 计数排序完成后，将结果拷贝回 arr 数组
            System.arraycopy(result, 0, array, 0, array.length);
            // 将计数数组重置为 0,防止当前数据影响下一轮循环
            Arrays.fill(counting, 0);
            // 继续下一轮位数
            position *= 10;
        }
    }


    public static void radixSort1(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        // 1. 找出最大值
        int max = array[0];
        for (int i : array) {
            max = Math.max(max, i);
        }

        // 2. 计算最大值的最大位数
        int maxDigitLength = 0;
        while (max != 0) {
            max /= 10;
            maxDigitLength++;
        }
        // 3. 使用计数排序算法对基数进行排序
        // 创建10个桶
        ArrayList<ArrayList<Integer>> bucketList = new ArrayList<>(10);
        // 初始化
        for (int i = 0; i < 10; i++) {
            bucketList.add(new ArrayList<>());
        }

        // 计算位于在哪个位上，初始为个位
        int position = 1;
        for (int i = 0; i < maxDigitLength; i++) {
            // 计算每个数组元素的当前位数的 数字，写在对应的位置上，比如 9 ，在 counting[9]+1
            for (int value : array) {
                int radix = value / position % 10;
                bucketList.get(radix).add(value);
            }
            //合并放回原数组
            int k = 0;
            for (int j = 0; j < 10; j++) {
                for (Integer t : bucketList.get(j)) {
                    array[k++] = t;
                }
                //取出来合并了之后把桶清光数据
                bucketList.get(j).clear();
            }
            position *= 10;
        }
    }


    /**
     * 2. 对包含负数的数组如何进行基数排序
     * 在对基数进行计数排序时，申请长度为 19 的计数数组，用来存储 [-9, 9] 这个区间内的所有整数。
     * 在把每一位基数计算出来后，加上 9，就能对应上 counting 数组的下标了。也
     * 就是说，counting 数组的下标 [0, 18] 对应基数 [-9, 9]
     *
     * @param arr
     */
    public static void radixSort2(int[] arr) {
        if (arr == null) {
            return;
        }
        // 找出最长的数
        int max = 0;
        for (int value : arr) {
            if (Math.abs(value) > max) {
                max = Math.abs(value);
            }
        }
        // 计算最长数字的长度
        int maxDigitLength = 0;
        while (max != 0) {
            maxDigitLength++;
            max /= 10;
        }
        // 使用计数排序算法对基数进行排序，下标 [0, 18] 对应基数 [-9, 9]
        int[] counting = new int[19];
        int[] result = new int[arr.length];
        int dev = 1;
        for (int i = 0; i < maxDigitLength; i++) {
            for (int value : arr) {
                // 下标调整
                int radix = value / dev % 10 + 9;
                counting[radix]++;
            }
            for (int j = 1; j < counting.length; j++) {
                counting[j] += counting[j - 1];
            }

            // 使用倒序遍历的方式完成计数排序
            for (int j = arr.length - 1; j >= 0; j--) {
                // 下标调整
                int radix = arr[j] / dev % 10 + 9;
                result[--counting[radix]] = arr[j];
            }
            // 计数排序完成后，将结果拷贝回 arr 数组
            System.arraycopy(result, 0, arr, 0, arr.length);
            // 将计数数组重置为 0
            Arrays.fill(counting, 0);
            dev *= 10;
        }
    }


}
