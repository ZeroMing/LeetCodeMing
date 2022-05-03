package org.ming.company.mt;

public class _4H_FindMedianSortedArrays {


    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3, 4};
        int[] nums2 = new int[]{3, 4, 7, 10, 18, 19, 20, 21};
        System.out.println(findMedianSortedArrays(nums1, nums2));
        System.out.println(findMedianSortedArrays2(nums1, nums2));
    }


    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // [1,2,3,4] [2,3,4,5]
        int i = 0, j = 0, k = 0;
        // 新数组，两数组长度之和
        int[] newNums = new int[nums1.length + nums2.length];
        // 合并数组
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                newNums[k] = nums1[i];
                i += 1;
            } else {
                newNums[k] = nums2[j];
                j += 1;
            }
            k += 1;
        }
        // 补全新数组
        while (i < nums1.length) {
            newNums[k++] = nums1[i++];
        }
        while (j < nums2.length) {
            newNums[k++] = nums2[j++];
        }

        // 计算中位数
        // 为偶数
        if (newNums.length % 2 == 0) {
            return (double) (newNums[newNums.length / 2] + newNums[newNums.length / 2 - 1]) / 2;
        } else {
            return (double) newNums[newNums.length / 2];
        }
    }

    /**
     * 双指针
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        // [1,2,3,4] [2,3,4,5]
        int i = 0, j = 0, k = 0, minMid = 0;
        boolean odd = false;
        // 新数组，两数组长度之和
        int[] newNums = new int[nums1.length + nums2.length];
        // 提前计算中位数的位置
        if (newNums.length % 2 == 0) {
            minMid = newNums.length / 2 - 1;
        } else {
            odd = true;
            minMid = newNums.length / 2;
        }
        // 合并数组
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                newNums[k] = nums1[i];
                i += 1;
            } else {
                newNums[k] = nums2[j];
                j += 1;
            }
            // 找到中位数下标,直接返回
            if (k == minMid && odd) {
                return (double) newNums[k];
            }
            if (k == minMid + 1 && !odd) {
                return (double) (newNums[k] + newNums[k - 1]) / 2;
            }
            k += 1;
        }
        // 补全新数组
        while (i < nums1.length) {
            newNums[k] = nums1[i];
            // 找到中位数下标,直接返回
            if (k == minMid && odd) {
                return (double) newNums[k];
            }
            if (k == minMid + 1 && !odd) {
                return (double) (newNums[k] + newNums[k - 1]) / 2;
            }
            k++;
            i++;
        }
        while (j < nums2.length) {
            newNums[k] = nums2[j];
            if (k == minMid && odd) {
                return (double) newNums[k];
            }
            if (k == minMid + 1 && !odd) {
                return (double) (newNums[k] + newNums[k - 1]) / 2;
            }
            k++;
            j++;
        }
        return 0.0d;
    }


    /**
     * 二分查找方法
     *
     * 1. 统一奇数偶数的case: (m+n+1)/2
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays3(int[] nums1, int[] nums2) {
        // [1,2,3,4] [2,3,4,5]
        int i = 0, j = 0, k = 0, minMid = 0;
        boolean odd = false;
        // 新数组，两数组长度之和
        int[] newNums = new int[nums1.length + nums2.length];
        // 提前计算中位数的位置
        if (newNums.length % 2 == 0) {
            minMid = newNums.length / 2 - 1;
        } else {
            odd = true;
            minMid = newNums.length / 2;
        }
        // 合并数组
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                newNums[k] = nums1[i];
                i += 1;
            } else {
                newNums[k] = nums2[j];
                j += 1;
            }
            // 找到中位数下标,直接返回
            if (k == minMid && odd) {
                return (double) newNums[k];
            }
            if (k == minMid + 1 && !odd) {
                return (double) (newNums[k] + newNums[k - 1]) / 2;
            }
            k += 1;
        }
        // 补全新数组
        while (i < nums1.length) {
            newNums[k] = nums1[i];
            // 找到中位数下标,直接返回
            if (k == minMid && odd) {
                return (double) newNums[k];
            }
            if (k == minMid + 1 && !odd) {
                return (double) (newNums[k] + newNums[k - 1]) / 2;
            }
            k++;
            i++;
        }
        while (j < nums2.length) {
            newNums[k] = nums2[j];
            if (k == minMid && odd) {
                return (double) newNums[k];
            }
            if (k == minMid + 1 && !odd) {
                return (double) (newNums[k] + newNums[k - 1]) / 2;
            }
            k++;
            j++;
        }
        return 0.0d;
    }
}
