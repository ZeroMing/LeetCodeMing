package org.ming.leetcodeoj.array;

/**
 * @description: Median of Two Sorted Arrays
 * @author: 李明
 * @company: 代码梦工厂
 * @version:
 * @date: 2019/8/8 16:27
 */
public class Leetcodeoj_4_2019_08_08_array {
    /*
        There are two sorted arrays nums1 and nums2 of size m and n respectively.
        Find the median of the two sorted arrays.
        The overall run time complexity should be O(log (m+n)).
        You may assume nums1 and nums2 cannot be both empty.
        确保时间复杂度为 O(log(m+n))
        Example 1:

        nums1 = [1, 3]
        nums2 = [2]

        The median is 2.0
        Example 2:

        nums1 = [1, 2]
        nums2 = [3, 4]

        The median is (2 + 3)/2 = 2.5


     */

    public static void main(String[] args) {
        Leetcodeoj_4_2019_08_08_array oj = new Leetcodeoj_4_2019_08_08_array();
        int[]  nums1 = new int[]{1, 2};
        int[]  nums2 = new int[]{3, 4};
        double medianSortedArrays = oj.findMedianSortedArrays(nums1, nums2);
        System.out.println();
        System.out.println("中位数:" + medianSortedArrays);

    }


    /**
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        int lAll = l1 + l2;
        int i=0 ,j=0;
        int[] numsAll = new int[lAll];
        int index = 0;
        while (i < l1 && j < l2){
            // 小的值先插入新的数组。下标 ++
            if(nums1[i] < nums2[j]){
                numsAll[index++] = nums1[i++];
            }else{
                numsAll[index++] = nums2[j++];
            }
        }
        // 将剩余没有处理完的数据插入新的数组
        while (i < l1){
            numsAll[index++] = nums1[i++];
        }
        while (j < l2){
            numsAll[index++] = nums2[j++];
        }

        if(lAll % 2 == 0){
            return (numsAll[(lAll/2)-1] + numsAll[(lAll/2)])/2d;
        }else{
            return numsAll[lAll/2];
        }
    }

}
