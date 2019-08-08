package org.ming.leetcodeoj.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: TODO-Eden.Lee
 * @author: 李明
 * @company: 朴新教育
 * @version:
 * @date: 2019/8/8 19:16
 */
public class HeapImpl<T extends Comparable<T>>{

    private List<T> heap;

    private List<T> orginList;

    /**
     * 插入对应上浮
     *
     * @param start
     */
    protected void adjustUp(int start) {
        // 当前下标
        int currentIndex = start;
        // 父节点
        int parentIndex = (currentIndex - 1) / 2;
        T tmp = heap.get(currentIndex);
        while (currentIndex > 0) {
            // 父节点 大于 当前节点
            int cmp = heap.get(parentIndex).compareTo(tmp);
            if (cmp >= 0) {
                break;
            } else {
                // 小于当前节点
                heap.set(currentIndex, heap.get(parentIndex));
                currentIndex = parentIndex;
                parentIndex = (parentIndex - 1) / 2;
            }
        }
        heap.set(currentIndex, tmp);
    }

    
    public void display() {
        System.out.println(heap);
    }

    
    public void initOriginList(List<T> orginList) {
        this.orginList = orginList;
    }

    public HeapImpl() {
        this.heap = new ArrayList<>();
    }

    
    public void makeHeap(int first, int last) {
        for (int i = first; i < last; i++) {
            int size = heap.size();
            // 将"数组"插在表尾
            heap.add(orginList.get(i));
            // 向上调整堆
            adjustUp(size);
        }
    }


    public void popHeap(int first) {
        int size = heap.size();
        // 将 最后一个值 设置到 第一个位置
        heap.set(first, heap.get(size - 1));
        // 最后一个元素删除
        heap.remove(size - 1);
        adjustDown(first);
    }


    public void pushHeap(int first, int last) {
        adjustUp(last - 1);
    }

    /**
     * 删除对应下沉
     *
     * @param index
     */
    private void adjustDown(int index) {
        // 下沉
        int currentIndex = index;
        // 左子节点
        int leftChildIndex = index * 2 + 1;
        // 右子节点
        int rightChildIndex = index * 2 + 2;
        // 取临时值
        T tmp = heap.get(currentIndex);
        int size = heap.size();
        // 左孩子小于长度
        while (leftChildIndex < size) {
            T left = null;
            T right = null;
            if (leftChildIndex < size) {
                left = heap.get(leftChildIndex);
            }
            if (rightChildIndex < size) {
                right = heap.get(rightChildIndex);
            }
            int maxIndex = right == null ? leftChildIndex : (left.compareTo(right) >= 0 ? leftChildIndex : rightChildIndex);
            T max = heap.get(maxIndex);
            if(tmp.compareTo(max)>=0){
                break;
            }else{
                heap.set(currentIndex, max);
                heap.set(maxIndex, tmp);
                leftChildIndex = maxIndex * 2 +1;
                rightChildIndex = maxIndex +1;
            }
        }
    }



    
    public List<T> getHeap() {
        return heap;
    }



    public static void main(String[] args) {
        HeapImpl<Integer> heap = new HeapImpl<>();
        heap.initOriginList(Arrays.asList(10, 20, 30, 5, 15));
        System.out.println("初始构建堆：");
        heap.makeHeap(0,5);
        heap.display();
        System.out.println("弹出堆顶：");
        heap.popHeap(0);
        heap.display();
        System.out.println("弹出堆顶：");
        heap.popHeap(0);
        heap.display();
        System.out.println("插入堆尾：");
        heap.getHeap().add(90);
        heap.display();
        System.out.println("重新排列：");
        heap.pushHeap(0, 4);
        heap.display();
    }

}
