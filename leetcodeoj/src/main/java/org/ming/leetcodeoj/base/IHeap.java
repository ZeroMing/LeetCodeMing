package org.ming.leetcodeoj.base;

import java.util.List;

/**
 * @description: TODO-Eden.Lee
 * @author: 李明
 * @company: 朴新教育
 * @version:
 * @date: 2019/8/8 19:15
 */
public interface IHeap<T extends Comparable<T>> {

    void display();

    void initOriginList(List<T> orginList);

    void makeHeap(int first, int last);

    void popHeap(int first, int last);

    void pushHeap(int first, int last);

    List<T> getHeap();
}

