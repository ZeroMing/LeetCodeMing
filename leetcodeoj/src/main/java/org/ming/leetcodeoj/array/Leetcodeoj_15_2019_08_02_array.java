package org.ming.leetcodeoj.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: 3Sum-3个数的和
 * @author: 李明
 * @company: 朴新教育
 * @version:
 * @date: 2019/8/2 16:18
 */
public class Leetcodeoj_15_2019_08_02_array {

    /**
     * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
     * Find all unique triplets in the array which gives the sum of zero.
     *
     * Note:
     *
     * The solution set must not contain duplicate triplets.
     * 三连音不可重复。
     *
     * Example:
     *
     * Given array nums = [-1, 0, 1, 2, -1, -4],
     *
     * A solution set is:
     * [
     *   [-1, 0, 1],
     *   [-1, -1, 2]
     * ]
     *
     * triplets - 三连音
     *
     */

    // 思路1: 取出所有三个元素的集合。遍历判断。去重（暴力）
    // 思路2: 先固定一个数，然后找另外两个数的的和为另一个数的相反数。

    //要我们找出三个数且和为0，那么除了三个数全是0的情况之外，肯定会有负数和正数，我们还是要先确定一个数，然后去找另外两个数，我们只要找到两个数且和为第一个确定数的相反数就行了。
    //
    //我们对原数组进行排序，然后开始遍历排序后的数组，这里注意不是遍历到最后一个停止，而是到倒数第三个就可以了。
    //
    //对于遍历到的数，用0减去这个确定的数得到一个target，然后只需要再之后找到两个数之和等于target即可。我们用两个指针分别指向fix数字之后开始的数组首尾两个数，如果两个数和正好为target，则将这两个数和fix的数一起存入结果中。然后就是跳过重复数字的步骤了，两个指针都需要检测重复数字。如果两数之和小于target，则我们将左边那个指针i右移一位，使得指向的数字增大一些。同理，如果两数之和大于target，则我们将右边那个指针j左移一位，使得指向的数字减小一些。

    /**
     * 分治
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return result;
        }
        // 1. 排序
        Arrays.sort(nums);
        int i =0;
        for(;i<nums.length - 2;i++){
            // 2. 去重
            if(i >0 && nums[i] == nums[i-1]){
                continue;
            }
            // 3. 寻找两个数与num[i]的和为 0
            find(nums, i+1, nums.length-1, nums[i],result);
        }
        return result;
    }

    /**
     * 双指针处理
     * @param nums
     * @param begin
     * @param end
     * @param target
     * @param result
     */
    private void find(int[] nums, int begin, int end, int target,List<List<Integer>> result) {
        int l = begin ,r = end;
        while (l < r) {
            if(nums[l] + nums[r] + target == 0){
                List<Integer> temp = new ArrayList<>();
                temp.add(nums[l]);
                temp.add(nums[r]);
                temp.add(target);
                result.add(temp);
                // 4. 去重
                while (l < r && nums[l] == nums[l+1]){l++;}
                while (l < r && nums[r] == nums[r-1]){r--;}
                l ++;
                r --;
            }else if(nums[l] + nums[r] + target < 0){
                l ++;
            }else{
                r --;
            }
        }
    }


    public static void main(String[] args) {
        Leetcodeoj_15_2019_08_02_array leetcodeoj_15_2019_08_02_array = new Leetcodeoj_15_2019_08_02_array();
        int[] array = new int[]{-1, 0, 1, 2, -1, -4};
        System.out.println(leetcodeoj_15_2019_08_02_array.threeSum(array));
    }



}
