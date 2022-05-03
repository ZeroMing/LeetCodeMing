package org.ming.leetcodeoj.heap;

import org.ming.common.BaseKit;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class MaxHeap {

    /**
     * 使用数组创建完全二叉树的结构，然后使用二叉树构建一个「堆」
     */
    int[] maxHeap;
    /**
     * heapSize用于数组的大小，因为数组在创建的时候至少需要指明数组的元素个数
     */
    int heapSize;
    /**
     * 堆中元素个数
     */
    int realSize;

    public MaxHeap(int heapSize) {
        this.heapSize = heapSize;
        maxHeap = new int[heapSize + 1];
        // 为了便于完全二叉树的管理，数组的第 0个索引的元素我们不会使用，请随便设置设置成任何一个值。
        maxHeap[0] = Integer.MIN_VALUE;
    }

    public void add(int element) {
        if (realSize == heapSize) {
            System.out.println("Add too many elements!");
            return;
        }
        realSize++;
        // 新元素位置
        maxHeap[realSize] = element;
        int index = realSize;
        int parent = realSize / 2;
        while (maxHeap[index] > maxHeap[parent] && index > 1) {
            BaseKit.swap(maxHeap, index, parent);
            index = parent;
            parent = index / 2;
        }
    }

    /**
     * 获取堆顶元素函数
     *
     * @return
     */
    public int peek() {
        return maxHeap[1];
    }

    /**
     * 删除堆顶元素函数
     */
    public int pop() {
        // 如果当前「堆」的元素个数为0， 则返回「Don't have any element」
        if (realSize < 1) {
            System.out.println("Don't have any element!");
            return Integer.MIN_VALUE;
        }
        // 堆顶元素
        int removeElement = maxHeap[1];
        // 将「堆」中的最后一个元素赋值给堆顶元素
        maxHeap[1] = maxHeap[realSize];
        realSize--;
        int index = 1;
        while (index < realSize && index <= realSize / 2) {
            // 被删除节点的左孩子节点
            int left = index * 2;
            // 被删除节点的右孩子节点
            int right = (index * 2) + 1;
            // 当删除节点的元素小于 左孩子节点或者右孩子节点，代表该元素的值小，此时需要将该元素与左、右孩子节点中最大的值进行交换
            if (maxHeap[index] < maxHeap[left] || maxHeap[index] < maxHeap[right]) {
                if (maxHeap[left] > maxHeap[right]) {
                    BaseKit.swap(maxHeap, left, index);
                    index = left;
                } else {
                    // maxHeap[left] <= maxHeap[right]
                    BaseKit.swap(maxHeap, right, index);
                    index = right;
                }
            } else {
                break;
            }
        }
        return removeElement;
    }

    /**
     * 返回「堆」的元素个数
     *
     * @return
     */
    public int size() {
        return realSize;
    }

    @Override
    public String toString() {
        if (realSize == 0) {
            return "No element!";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            for (int i = 1; i <= realSize; i++) {
                sb.append(maxHeap[i]);
                sb.append(',');
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(']');
            return sb.toString();
        }
    }
}
