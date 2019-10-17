package org.ming.leetcodeoj.base;

import java.util.Arrays;

/**
 * @description: 堆
 * @author: LeoMee
 * @date: 2019年08月09 01时29分
 */
public class Heap {
    //最大堆-> 所有子树的根节点大于子节点的完全二叉树
    //最小堆-> 所有子树的根节点小于子节点的完全二叉树

    private int[] heap = new int[1024];
    private int length = -1;
    public void insert(int data){
        heap[++length] = data;
        //reBuidMaxHeap(heap,length);
    }


    //构建最大堆
    public void buildMaxHeap(){
        //叶子节点没有子节点不用重构堆
        for(int i=length/2;i>=0;i--){
            maxHeapFixed(heap,i,length);
        }
    }

    //从i节点开始调整,n为节点总数 从0开始计算 i节点的子节点为 2*i+1, 2*i+2
    //删除节点时，把根元素和最后的一个元素交换，并根据新交换的新根元素调整整个堆(从上到下沉降调整)，所以此时i为0
    public void minHeapFixed(int arr[], int i, int n){
        //获取要调整的节点的值
        int temp = arr[i];
        int minChildIndex = 2*i+1;
        while(minChildIndex < n){
            //保证有右孩子并且右孩子比左孩子小
            if(minChildIndex+1 < n && arr[minChildIndex+1] < arr[minChildIndex]){
                minChildIndex++;
            }
            //根比左右孩子都小则结束循环
            if(arr[minChildIndex] >= temp){
                break;
            }
            //根比左和右大，用左、右的最小值覆盖根值，并定义新的根和孩子向下沉降递归
            arr[i] = arr[minChildIndex];
            i = minChildIndex;
            minChildIndex = 2*i+1;
        }
        //沉降找到了位置
        arr[i] = temp;
    }

    /**
     * 大根堆
     * @param arr
     * @param i
     * @param n
     */
    public void maxHeapFixed(int arr[], int i, int n){
        //获取要调整的节点的值
        int temp = arr[i];
        int minChildIndex = 2*i+1;
        while(minChildIndex < n){
            if(minChildIndex+1 < n && arr[minChildIndex+1] > arr[minChildIndex]){
                minChildIndex++;
            }
            if(arr[minChildIndex] <= temp){
                break;
            }
            arr[i] = arr[minChildIndex];
            i = minChildIndex;
            minChildIndex = 2*i+1;
        }
        arr[i] = temp;
    }

    public void heapSort(){
        for(int i = length;i>=0;i--){
            int temp = heap[i];
            heap[i] = heap[0];
            heap[0] = temp;
            maxHeapFixed(heap,0,i);
        }
    }

    //添加新的元素后重排序成最小堆
    public void reBuidMinHeap(int[] heap,int index){
        //保存将要插入的值
        int temp = heap[index];
        //保存插入值的下标
        int tempIndex = index;
        //获取当前位置插入的根节点
        int rootIndex = (index-1)/2;
        while(rootIndex >=0 && tempIndex!=0){
            //要插入的值大于等于根节点->满足最小堆
            if(temp >= heap[rootIndex]){
                break;
            }
            //要插入的值比当前插入位置换算的根节点值小的话
            //根的值给当前位置
            heap[tempIndex] = heap[rootIndex];
            //在往上走，循环检测
            tempIndex = rootIndex;
            rootIndex = (tempIndex-1)/2;
        }
        //找到了应在的位置
        heap[tempIndex] = temp;
    }

    //添加新的元素后重排序成最大堆
    public void reBuidMaxHeap(int[] heap,int index){
        int temp = heap[index];
        int tempIndex = index;
        int rootIndex = (index-1)/2;
        while(rootIndex >= 0 && tempIndex!=0){
            if(heap[rootIndex] >= temp){
                break;
            }
            heap[tempIndex] = heap[rootIndex];
            tempIndex = rootIndex;
            rootIndex = (tempIndex-1)/2;
        }
        heap[tempIndex] = temp;
    }



    public int[] returnHeap(){
        int[] arr = new int[length+1];
        System.arraycopy(heap, 0, arr, 0, length+1);
        return arr;
    }
    public static void main(String[] args) {
        Heap heap = new Heap();
        int[] arr = {1,4,5,7,8,9,2,3,6};
        for(int i:arr){
            heap.insert(i);
        }
        //把加入的无序数组构建成最大堆
        heap.buildMaxHeap();
//        heap.heapSort();
//        System.out.println(Arrays.toString(heap.returnHeap()));
    }
}

