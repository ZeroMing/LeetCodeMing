package org.ming.leetcodeoj.heap;

import java.util.Arrays;

/**
 * @description: 二叉堆构建
 * @author: LeoMee
 * @date: 2019年08月09 00时38分
 */
public class LeoLeeMinHeap {

    private void maxHeapSort(Integer[] array) {
        // 暂存交换元素
        Integer tmp;
        //执行初始建大根堆，并调整
        makeMaxHeap(array);

        for (int i=0;i<array.length;i++){
            // 交换堆顶元素array[0]和堆中最后一个元素array[array.length-1-i]
            tmp=array[0];
            array[0]=array[array.length-i-1];
            array[array.length-i-1]=tmp;
            // 每次交换堆顶元素和堆中最后一个元素之后，都要对堆进行调整
            adjustHeap(array,0,array.length-i-1);
        }
    }

    public void minHeapSort(Integer a[]){
        int temp = 0;
        makeMinHeap(a);
        System.out.println("小根堆构建完成: "+Arrays.asList(a));
        int length = a.length;
        for(int i=length-1;i>0;i--){
            temp = a[0];
            a[0] = a[i];
            a[i] = temp;
            minHeapFixdown(a,0,i);
        }
    }



    // 先是一个无序的数组， 填入后，找到最后一个结点，从它的父节点。开始调整。根据性质，小的数字往上移动；依次类推
    // 注意，被调整的节点，还有子节点的情况，需要递归进行调整。
    /**
     * 构建堆的过程
     * @param array
     */
    public  void makeMaxHeap(Integer[] array){
        int length = array.length;
        // 求出当前堆中最后一个存在孩子结点的索引
        int parentIndex = (length - 1) / 2;
        for(int i = parentIndex;i>=0;i--){
            // 在建堆过程中，及时调整堆中索引为 i 的结点
            adjustHeap(array,i,array.length);
        }
    }

    private  void adjustHeap(Integer[] array, int i, int length) {
        // 当前待调整节点
        Integer temp = array[i];
        //当前待调整节点的左孩子索引（右孩子索引= 2 * i+ 2.即 childLeft + 1）
        int childLeft = 2 * i + 1;
        while(childLeft < length){
            if(childLeft + 1 < length && array[childLeft] < array[childLeft+1]){
                childLeft ++;
            }
            //
            if(array[i] < array[childLeft]){
                // 孩子结点大于当前待调整结点，将孩子结点放到当前待调整结点的位置上
                array[i] = array[childLeft];
                // 重新设置待调整的下一个结点的索引
                i = childLeft;
                childLeft = 2 * i  + 1;
            }else{
                // 如果当前待调整结点大于它的左右孩子，则不需要调整，直接退出
                break;
            }
            // 当前待调整的结点放到比其大的孩子结点位置上
            array[i]= temp;

        }


    }


    public static void makeMinHeap(Integer[] array){
        int length = array.length;
        // 求出当前堆中最后一个存在孩子结点的索引
        int parentIndex = (length - 1) / 2;
        for(int i= parentIndex ; i>=0 ; i--){
            minHeapFixdown(array,i,length);
        }
    }

    private static void minHeapFixdown(Integer[] array, int i, int length) {
        // 当前待调整节点
        Integer temp = array[i];
        //当前待调整节点的左孩子索引（右孩子索引= 2 * i+ 2.即 childLeft + 1）
        int childLeft = 2 * i + 1;
        while(childLeft < length){
            // 在左右子节点中寻找最小的
            if(childLeft + 1 < length && array[childLeft] >= array[childLeft+1]){
                childLeft ++;
            }
            //
            if(array[i] < array[childLeft]){
                break;
            }

            //较大节点下移
            temp = array[i];
            array[i] = array[childLeft];
            array[childLeft] = temp;
            i = childLeft;
            childLeft = 2 * i +1;

        }
    }


    public static void main(String[] args) {

        Integer[] array=new Integer[]{20,10,60,1,2,10,9,0,8,50};
        LeoLeeMinHeap leoLeeMinHeap = new LeoLeeMinHeap();
        //leoLeeMinHeap.maxHeapSort(array);
        //System.out.println(Arrays.asList(array));
        //array =new Integer[]{1,6,9,4,789,123};
        leoLeeMinHeap.minHeapSort(array);
        System.out.println(Arrays.asList(array));

    }

}
