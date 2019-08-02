package org.ming.leetcodeoj.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: Two Sum
 * @author: 李明
 * @company: 朴新教育
 * @version:
 * @date: 2019/8/2 17:50
 */
public class Leetcodeoj_1_2019_08_02_array {
    /**
     * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
     *
     * You may assume that each input would have exactly one solution, and you may not use the same element twice.
     *
     * Example:
     *
     * Given nums = [2,2,7,11,7 15], target = 9,
     *
     * Because nums[0] + nums[1] = 2 + 7 = 9,
     * return [0, 1].
     *
     */


    // 1、暴力搜索
    // 2、双指针。排序，双指针查找，查找数组在原数组的位置。返回。
    // 3、Map求解。


    /**
     * 双指针
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        // 备份原数组
        int[] sources = new int[nums.length];
        for(int i=0;i < nums.length;i++){
            sources[i] = nums[i];
        }
        int l =0,r = nums.length - 1;
        // 排除，进行查找
        Arrays.sort(nums);
        while (l < r){
            if(nums[l] + nums[r] == target){
                break;
            }else if(nums[l] + nums[r] < target){
                l++;
            }else{
                r--;
            }
        }

        // 查找到目前的下标值

        int[] res = new int[2];
        int i = 0;
        for(;i < sources.length;i++){
            if(sources[i] == nums[l]){
                res[0] = i;
                break;
            }
        }

        if(nums[l]== nums[r]){
            i ++;
        }else{
            i = 0;
        }

        for(;i<sources.length;i++){
            if(sources[i] == nums[r]){
                res[1] = i;
                break;
            }
        }

        return res;
    }

    /**
     * Map
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer,Integer> map = new HashMap<>(nums.length);
        for(int i=0;i<nums.length;i++){
            // Key为数组值，Value 为数组下标
            map.put(nums[i],i);
        }

        for(int i=0;i<nums.length;i++){
            // 目标值 - 数组元素值
            int temp = target - nums[i];
            // 存在 key 且 下标不等于 当前下标
            if(map.containsKey(temp) && map.get(temp) != i){
                res[0] = i;
                res[i] = map.get(temp);
                break;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        Leetcodeoj_1_2019_08_02_array leetcodeoj_1_2019_08_02_array = new Leetcodeoj_1_2019_08_02_array();
        int[] nums = new int[]{3,3};
        int[] ints = leetcodeoj_1_2019_08_02_array.twoSum(nums, 6);
        System.out.println(ints[0]+"与"+ints[1]);
    }


}
