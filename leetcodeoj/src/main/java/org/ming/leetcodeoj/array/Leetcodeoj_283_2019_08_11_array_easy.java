package org.ming.leetcodeoj.array;

/**
 * @description:  Move Zeroes 移动零元素
 * @author: LeoMee
 * @date: 2019年08月11 10时24分
 */
public class Leetcodeoj_283_2019_08_11_array_easy {

    /**
     * Given an array nums, write a function to move all 0's to the end of it
     * while maintaining the relative order of the non-zero elements.
     * 在保持其他非零元素的顺序的前提下，移动所有的零元素到数组的末尾。
     * Example:
     *
     * Input: [0,1,0,3,12]
     * Output: [1,3,12,0,0]
     * Note:
     *
     * You must do this in-place without making a copy of the array.
     * Minimize the total number of operations.
     */

    public void moveZeroes(int[] nums) {
        int length = nums.length;
        // 移动 0 的下标 上限。每次移动完一个0之后，后续需要移动的上限 -1.
        int topIndex = length;
        for(int i=length-1;i>=0;i--){
            // 等于0，需要移动
            int temp = nums[i];
            if(temp == 0){
                int j = i;
                // 所有非零元素向前移动。
                while (j < topIndex - 1) {
                    nums[j] = nums[j+1];
                    j++;
                }
                // 最后一个 上限 元素 为 0
                nums[topIndex - 1] = temp;
                topIndex --;
            }
        }
    }


    /**
     * 将所有的非零元素，按顺序排到住数组前面。之后，将后面的位置填0即可。
     * @param nums
     */
    public void moveZeroes2(int[] nums) {
        int index = 0;
        for(int i =0;i < nums.length;i++){
            if(nums[i] != 0){
                nums[index++] = nums[i];
            }
        }
        for(;index <nums.length;index++){
            nums[index] = 0;
        }
    }


    public void printArray(int[] nums){
        for (int num:nums){
            System.out.print(num + " ");
        }
    }

    public static void main(String[] args) {
        Leetcodeoj_283_2019_08_11_array_easy array_easy = new Leetcodeoj_283_2019_08_11_array_easy();
        int[] nums = new int[]{0,1,2,3,4,0,9};
        array_easy.moveZeroes(nums);
        array_easy.printArray(nums);

    }
}
