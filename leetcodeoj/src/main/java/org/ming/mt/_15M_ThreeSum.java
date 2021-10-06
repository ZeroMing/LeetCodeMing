package org.ming.mt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class _15M_ThreeSum {
    /*

    15. 三数之和
    给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。

    注意：答案中不可以包含重复的三元组

    [-1, -1, -4, 0, 1, 2]


    示例 1：

    输入：nums = [-1,0,1,2,-1,-4]
    输出：[[-1,-1,2],[-1,0,1]]
    示例 2：

    输入：nums = []
    输出：[]
    示例 3：

    输入：nums = [0]
    输出：[]


    提示：

    0 <= nums.length <= 3000
    -105 <= nums[i] <= 105
     */
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, -2, -1};
        System.out.println(threeSum1(nums));
    }


    public static List<List<Integer>> threeSum1(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList<>(0);
        }
        return threeSumTarget(nums, 0);
    }

    public static List<List<Integer>> threeSumTarget(int[] nums, int target) {
        // 排序
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> tuples = twoSumTarget(nums, i + 1, target - nums[i]);
            ListIterator<List<Integer>> iterator = tuples.listIterator();
            while (iterator.hasNext()) {
                List<Integer> tuple = iterator.next();
                tuple.add(nums[i]);
                ans.add(tuple);
            }
            while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                i++;
            }
        }
        return ans;
    }


    public static List<List<Integer>> twoSumTarget(int[] nums, int start, int target) {
        // 排序
        // Arrays.sort(nums);
        // 慢指针
        int low = start;
        // 快指针
        int hi = nums.length - 1;
        List<List<Integer>> ans = new ArrayList<>();
        while (low < hi) {
            int left = nums[low];
            int right = nums[hi];
            int sum = left + right;
            if (sum < target) {
                while (low < hi && nums[low] == left) {
                    low++;
                }
            } else if (sum > target) {
                while (low < hi && nums[hi] == right) {
                    hi--;
                }
            } else {
                List<Integer> tuple = new ArrayList<>();
                tuple.add(nums[low]);
                tuple.add(nums[hi]);
                ans.add(tuple);
                // 优化： 跳过所有重复的元素
                while (low < hi && nums[low] == left) {
                    low++;
                }
                while (low < hi && nums[hi] == right) {
                    hi--;
                }
            }
        }
        return ans;
    }

    public static List<List<Integer>> threeSum2(int[] nums) {

        return null;
    }


    public static List<List<Integer>> threeSum3(int[] nums) {

        return null;
    }


}
