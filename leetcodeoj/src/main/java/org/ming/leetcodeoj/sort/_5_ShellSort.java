package org.ming.leetcodeoj.sort;

import org.ming.common.BaseKit;

/**
 * 希尔排序
 * <p>
 * 希尔排序是插入排序的一种，又称“缩小增量排序”（Diminishing Increment Sort），是直接插入排序的高级变形，
 * 其思想简单点说就是有跨度的插入排序，这个跨度会逐渐变小，直到变为 1，变为 1 时记录也就基本有序，这时用到的也就是我们之前讲的直接插入排序了
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _5_ShellSort {
    public static void main(String[] args) {
        int[] array = new int[]{2, 1, 3, 4, 1, 0, -1, 8, 7, 1, -9};
        shellSort0(array);
        BaseKit.print(array);
    }

    /**
     * 先逐步分组进行粗调，在进行直接插入排序的思想就是希尔排序。我们刚才的分组跨度（4，2，1）被称为希尔排序的增量，我
     * 们上面用到的是逐步折半的增量方法，这也是在发明希尔排序时提出的一种朴素方法，被称为希尔增量，
     * 我们的希尔增量因为每一轮之间是等比的，所以会有盲区，这里增量的选取就非常关键了。
     * <p>
     * 下面给大家介绍两个比较有代表性的 Sedgewick 增量和 Hibbard 增量
     * Sedgewick 增量序列如下：
     * 1，5，19，41，109.。。。
     * 通项公式 94^k - 92^
     * 利用此种增量方式的希尔排序，最坏时间复杂度是 O(n^(4/3))
     * Hibbard 增量序列如下
     * 1，3，7，15......
     * 通项公式 2 ^ k-1
     * 利用此种增量方式的希尔排序，最坏时间复杂度为 O(n^(3/2))
     * 上面是两种比较具有代表性的增量方式，可究竟应该选取怎样的增量才是最好，目前还是一个数学难题。不过我们需要注意的一点，就是增量序列的最后一个增量值必须等于 1 才行
     */

    public static void shellSort0(int[] array) {
        int increment = array.length;
        while (increment > 1) {
            increment = increment / 2;
            for (int i = 0; i < increment; i++) {
                for (int j = i + increment; j < array.length; j += increment) {
                    int temp = array[j];
                    int k;
                    for (k = j - increment; k >= 0; k--) {
                        if (temp > array[k]) {
                            array[k + 1] = array[k];
                        }
                    }
                    array[k + increment] = temp;
                }
            }
        }
    }

}
