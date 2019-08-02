package org.ming.leetcodeoj.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: 4Sum
 * @author: 李明
 * @company: 朴新教育
 * @version:
 * @date: 2019/8/2 17:48
 */
public class Leetcodeoj_18_2019_08_02_array {
    /**
     * Given an array nums of n integers and an integer target,
     * are there elements a, b, c, and d in nums such that a
     * + b + c + d = target?
     * Find all unique quadruplets in the array which gives the sum of target.
     *
     * Note:
     * The solution set must not contain duplicate quadruplets. 不能重复
     *
     * Example:
     *
     * Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
     *
     * A solution set is:
     * [
     *   [-1,  0, 0, 1],
     *   [-2, -1, 1, 2],
     *   [-2,  0, 0, 2]
     * ]
     */


    // 1. 暴露4层。去重。
    // 2. 先遍历第一个数，然后固定第一个数之后，转化为剩下元素的3SUM问题
    // 3. 先遍历两个数，求和，然后转化为剩下元素的2SUM问题




    public List<List<Integer>> threeSum(int[] nums, int target,int from) {
        int length = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        for(int i = from + 1; i < length - 2; i++) {
            if(nums[i] + nums[i+1] + nums[i+2] > target){break;}
            if(nums[i] + nums[length-1] + nums[length-2] < target){continue;}
            if(i > from + 1 && nums[i] == nums[i - 1]) {continue;}
            int left = i + 1;
            int right = length - 1;
            while(left < right){
                int diff = target - nums[i] - nums[left] - nums[right];
                if (diff == 0){
                    result.add(new ArrayList<Integer>(Arrays.asList(nums[from], nums[i], nums[left], nums[right])));
                    while(left < right && nums[left] == nums[left + 1]) {left++;}
                    while(left < right && nums[right] == nums[right - 1]) {right--;}
                    left++;
                    right--;
                }else if (diff > 0){
                    left++;
                }else {
                    right--;
                }
            }
        }
        return result;
    }


    public List<List<Integer>> fourSum(int[] nums, int target) {
        int length = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0; i <length - 3; i++){
            if(nums[i] + nums[i+1] + nums[i+2] + nums[i+3] > target){
                continue;
            }
            if(nums[i] + nums[length-1]+nums[length-2] + nums[length-3] < target){
                continue;
            }
            if(i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            int complement = target - nums[i];
            result.addAll(threeSum(nums,complement,i));

        }

        return result;

    }

    public static void main(String[] args) {
        int[] ints = {1, 0, -1, 0, -2, 2};
        Leetcodeoj_18_2019_08_02_array leetcodeoj_18_2019_08_02_array = new Leetcodeoj_18_2019_08_02_array();
        List<List<Integer>> lists = leetcodeoj_18_2019_08_02_array.fourSum(ints, 0);
        System.out.println(lists);

    }

}
