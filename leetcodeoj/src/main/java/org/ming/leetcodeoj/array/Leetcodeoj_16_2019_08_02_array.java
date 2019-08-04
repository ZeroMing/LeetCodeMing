package org.ming.leetcodeoj.array;

import java.util.Arrays;

/**
 * @description: 3Sum Closest
 * @author: 李明
 * @company: 朴新教育
 * @version:
 * @date: 2019/8/2 17:49
 */
public class Leetcodeoj_16_2019_08_02_array {
    // 参考链接: https://blog.csdn.net/qq_20404903/article/details/80928130

    /**
     * Given an array nums of n integers and an integer target,
     * find three integers in nums such that the sum is closest to target.
     * Return the sum of the three integers.
     * You may assume that each input would have exactly one solution.
     *
     * Example:
     *
     * Given array nums = [-1, 2, 1, -4], and target = 1.
     *
     * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
     */


    /**
     * TODO 待优化
     * Runtime: 6 ms, faster than 44.43% of Java online submissions for 3Sum Closest.
     * Memory Usage: 37 MB, less than 99.49% of Java online submissions for 3Sum Closest.
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        int closest = nums[0] + nums[1] + nums[2];
        // 取差的绝对值
        int diff = Math.abs(closest - target);
        // 排序
        Arrays.sort(nums);
        // 用3sum的思路，先选取其中一个数。再选取最小值和最大值
        for(int i=0;i<nums.length -2;i++){
            //
            int left = i +1,right = nums.length -1;
            while (left < right){
                // 求新的值的和
                int newSum = nums[i] + nums[left] + nums[right];
                // 求新的差的绝对值
                int newDiff = Math.abs(newSum-target);
                // 比原来的差小，说明更接近
                if(newDiff <= diff){
                    diff = newDiff;
                    closest = newSum;
                }
                // 继续执行，如果比目标值小，左 ++
                if(newSum < target){
                    left ++;
                }else{
                    // 比目标值大，右 --
                    right --;
                }
            }
        }
        return closest;
    }

    public static void main(String[] args) {
        Leetcodeoj_16_2019_08_02_array leetcodeoj1620190802Array = new Leetcodeoj_16_2019_08_02_array();
        int[] nums = new int[]{0, 2, 1, -3};
        int i = leetcodeoj1620190802Array.threeSumClosest(nums, 1);
        System.out.println(i);
    }






}
