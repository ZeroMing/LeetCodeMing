package org.ming.common;

/**
 *
 * @author: LeoLee
 * @date: 2019/11/26 09:12
 */
public class TimeComplexAnalysis {/**
 * 从数组中查找指定元素的下标位置
 * @param array  数组
 * @param target 目标元素
 * @return
 */
public int findIndex(int[] array,int target){
    if(array == null || array.length == 0) {
        return -1;
    }
    for(int i=0;i<array.length;i++){
        if(array[i] == target){
            return i;
        }
    }
    return -1;
}




}
