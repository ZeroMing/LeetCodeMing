package org.ming.leetcodeoj.array;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _977S_SortedSquares {
    /*

    给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。

     

    示例 1：

    输入：nums = [-4,-1,0,3,10]
    输出：[0,1,9,16,100]
    解释：平方后，数组变为 [16,1,0,9,100]
    排序后，数组变为 [0,1,9,16,100]
    示例 2：

    输入：nums = [-7,-3,2,3,11]
    输出：[4,9,9,49,121]
     

    提示：

    1 <= nums.length <= 104
    -104 <= nums[i] <= 104
    nums 已按 非递减顺序 排序
     

    进阶：
    请你设计时间复杂度为 O(n) 的算法解决本问题


     */
    public static void main(String[] args) {
        int[] nums = new int[]{-4,-1,0,3,10};
        int[] ints = sortedSquares(nums);
        print(ints);

    }

    public static void print(int[] args) {
        for (int a:args) {
            System.out.println(a);
        }
    }
    /**
     * [][]
     * @param nums
     * @return
     */
    public static int[] sortedSquares(int[] nums) {
        int[] ans = new int[nums.length];
        int left = 0, right = nums.length-1;
        int index = nums.length - 1;
        while (left <= right){
            // 左边 小于 右边的
            if(nums[left] * nums[left] < nums[right] * nums[right]){
                // 右边插入
                ans[index--] = nums[right] * nums[right];
                // 右边--
                right--;
            }else{
                ans[index--] = nums[left] * nums[left];
                left++;
            }
        }
        return ans;
    }
}
