package org.ming.leetcodeoj.base;

/**
 * @description:
 * @author: LeoMee
 * @date: 2019年08月09 00时38分
 */
public class LeoLeeMinHeap {

    private int[] heap;
    private int size;

    // [1,2,3,4]
    public static void Adjust(int a[]){
        int len = a.length;
        // 新的数组
        int[] b = new int[len];
        // 父节点
        int FatherNodeNum = len / 2;
        //如果数组a的长度为偶数。偶数 情况下 父节点下标 = len / 2 -1
        if(len % 2 == 0){
            //父节点 大于 子节点
            if(a[len - 1] < a[FatherNodeNum - 1]){
                // 交换位置 swap
                int tmp = a[len - 1];
                a[len - 1] = a[FatherNodeNum - 1];
                a[FatherNodeNum - 1] = tmp;
            }
            --FatherNodeNum;
        }

        //下面的每个父节点都有两个子节点
        while (FatherNodeNum > 0) {
            // k一直跟着当前的节点，直到此节点被换到叶子结点上
            int k = FatherNodeNum;
            // len/2 - 1 是第一个叶节点的下标
            while(k <= len / 2){
                // 左子节点下标
                int lNode = 2 * k - 1;
                // 右子节点下标
                int rNode = 2 * k;
                // 如果左子节点小于等于右子节点
                if(a[lNode] <= a[rNode]){
                    // 同时左子节点小于父节点
                    if (a[lNode] < a[k - 1]) {
                        int tmp = a[lNode];
                        a[lNode] = a[k - 1];
                        a[k - 1] = tmp;
                        //记下此节点是数组中第几个值
                        k = 2 * k;
                    } else {
                        // 如果父节点不小于最小的子节点的值 跳出循环
                        break;
                    }
                } else {
                    // 若右节点更小一点
                    // 如果父节点不小于最小的子节点的值 跳出循环
                    if (a[rNode] < a[k - 1]) {
                        int tmp = a[rNode];
                        a[rNode] = a[k - 1];
                        a[k - 1] = tmp;
                        // 记下此节点是数组中第几个值
                        k = 2 * k + 1;
                    } else {
                        // 如果父节点不小于最小的子节点的值 跳出循环
                        break;
                    }
                }
            }
            -- FatherNodeNum;
        }
    }

}
