package org.ming.leetcodeoj.heap;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * @description: Find Median from Data Stream 从数据流中找到中值
 * @author: 李明
 * @company: 代码梦工厂
 * @version:
 * @date: 2019/8/8 16:27
 */
public class Leetcodeoj_295_2019_08_08_heap {

    /*
    Median is the middle value in an ordered integer list.
    If the size of the list is even, there is no middle value.
    偶数个元素的列表是不存在中位数的。
    So the median is the mean of the two middle value.
    For example,
    [2,3,4], the median is 3
    [2,3], the median is (2 + 3) / 2 = 2.5

    Design a data structure that supports the following two operations:
    设计一个数据结构，支持一下操作：
    void addNum(int num) - Add a integer number from the data stream to the data structure.
    添加元素到数据结构
    double findMedian() - Return the median of all elements so far.
    查找中值元素

    Example:
    addNum(1)
    addNum(2)
    findMedian() -> 1.5
    addNum(3)
    findMedian() -> 2

    Follow up: 继续探索
    如果所有来自数据流的元素都处于 0 到 100 之间。如何优化 ？
    If all integer numbers from the stream are between 0 and 100, how would you optimize it?
    如果百分之九十九来自数据流的元素都处于 0 到 100 之间，如何优化 ?
    If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
     */


    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
    }
}


/**
 * 自定义实现 大顶堆（大根堆） 和 小顶堆（小根堆）
 *
 * 大根堆；
 *
 *
 *
 */


/**
 * 借助JDK自带的优先级队列
 */
class MedianFinder {

    /**
     * store the smaller half of the input numbers
     * 大顶堆，下面的元素都比堆顶小
     */
    private PriorityQueue<Integer> maxHeap;

    /**
     *  store the larger half of the input numbers
     *  小顶堆，下面的元素都比堆顶值大
     */
    private PriorityQueue<Integer> minHeap;


    /** initialize your data structure here. */
    public MedianFinder() {
        // Should provide comparator to support max heap
        maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());


        // by default, PriorityQueue is a min heap
        // 默认是小顶堆
        minHeap = new PriorityQueue<Integer>();
    }

    public void addNum(int num) {

        maxHeap.add(num);
        // balancing
        minHeap.add(maxHeap.peek());
        maxHeap.poll();
        // maintain size property
        // 如果大顶堆的长度 小于 小顶堆
        if(maxHeap.size() < minHeap.size()) {
            maxHeap.add(minHeap.peek());
            minHeap.poll();
        }

    }

    public double findMedian() {
        if((maxHeap.size() + minHeap.size()) % 2 == 0) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        else {
            return maxHeap.peek();
        }
    }


}

