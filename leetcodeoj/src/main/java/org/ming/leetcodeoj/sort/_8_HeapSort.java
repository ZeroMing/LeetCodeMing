package org.ming.leetcodeoj.sort;

import org.ming.common.BaseKit;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _8_HeapSort {
    public static void main(String[] args) {
        int[] array = new int[]{2, 1, 3, 4, 1, 0, -1, 8, 7, 1, -9};
        heapSort0(array);
        BaseKit.print(array);
    }


    /**
     * 堆
     * 首先是完全二叉树
     *
     * @param array
     */
    public static void heapSort0(int[] array) {
        // 构建初始大顶堆
        buildMaxHeap(array);
        for (int i = array.length - 1; i > 0; i--) {
            // 将最大值交换到数组最后
            BaseKit.swap(array, 0, i);
            // 调整剩余数组，使其满足大顶堆
            maxHeapify(array, 0, i);
        }
    }

    public static void buildMaxHeap(int[] arr) {
        // 从最后一个非叶子结点开始调整大顶堆，最后一个非叶子结点的下标就是 arr.length / 2-1
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            maxHeapify(arr, i, arr.length);
        }
    }


    /**
     * 调整大顶堆，第三个参数表示剩余未排序的数字的数量，也就是剩余堆的大小
     *
     * @param arr
     * @param i        下标从0开始
     * @param heapSize
     */
    private static void maxHeapify(int[] arr, int i, int heapSize) {
        // 左子结点下标
        int l = 2 * i + 1;
        // 右子结点下标
        int r = l + 1;
        // 记录根结点、左子树结点、右子树结点三者中的最大值下标
        int largest = i;
        // 与左子树结点比较
        if (l < heapSize && arr[l] > arr[largest]) {
            largest = l;
        }
        // 与右子树结点比较
        if (r < heapSize && arr[r] > arr[largest]) {
            largest = r;
        }

        if (largest != i) {
            // 将最大值交换为根结点
            BaseKit.swap(arr, i, largest);
            // 再次调整交换数字后的大顶堆
            maxHeapify(arr, largest, heapSize);
        }
    }
}
