package org.ming.leetcodeoj.array;

/**
 * @description: Non-decreasing Array
 * @author: LeoMee
 * @date: 2019年08月11 11时22分
 */
public class Leetcodeoj_665_2019_08_11_array_easy {
    /**
     * Given an array with n integers, your task is to check if it
     * could become non-decreasing by modifying at most 1 element.
     *
     * We define an array is non-decreasing if array[i] <= array[i + 1] holds for every i (1 <= i < n).
     *
     * Example 1:
     * Input: [4,2,3]
     * Output: True
     * Explanation: You could modify the first 4 to 1 to get a non-decreasing array.
     * Example 2:
     * Input: [4,2,1]
     * Output: False
     * Explanation: You can't get a non-decreasing array by modify at most one element.
     * Note: The n belongs to [1, 10,000].
     */
    public boolean checkPossibility(int[] nums) {
        // [4,3,4]
        // [1,4,2,3]
        // [2,5,3,2,4]
        int cnt = 1,length = nums.length;
        for (int i = 1; i < length; ++i) {
            if (nums[i] < nums[i - 1]) {
                if (cnt == 0) {
                    return false;
                }

                // 首先如果再前面的数不存在，我们直接修改前面的数字为当前的数字即可。较小数为准。
                // 当再前面的数字存在，并且小于当前数时，我们还是需要修改前面的数字为当前数字小值。
                if (i == 1 || nums[i] >= nums[i - 2]) {
                    nums[i - 1] = nums[i];
                } else {
                    // 如果再前面的数大于当前数，我们需要修改当前数为前面的数大值
                    nums[i] = nums[i - 1];
                }
                --cnt;
            }
        }
        return true;
    }

    public void printArray(int[] nums){
        for (int num:nums){
            System.out.print(num + " ");
        }
    }


    public static void main(String[] args) {
        Leetcodeoj_665_2019_08_11_array_easy array_easy = new Leetcodeoj_665_2019_08_11_array_easy();
        int[] nums = new int[]{1,4,-2,3};
        System.out.println(array_easy.checkPossibility(nums));
    }
}
