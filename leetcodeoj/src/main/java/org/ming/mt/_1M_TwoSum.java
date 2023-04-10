package org.ming.mt;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class _1M_TwoSum {
    /*
    给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标
    你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
    你可以按任意顺序返回答案。

    示例 1：
    输入：nums = [2,7,11,15], target = 9
    输出：[0,1]
    解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1]

    示例 2：
    输入：nums = [3,2,4], target = 6
    输出：[1,2]

    示例 3：
    输入：nums = [3,3], target = 6
    输出：[0,1]

    提示：
    2 <= nums.length <= 104
    -109 <= nums[i] <= 109
    -109 <= target <= 109
    只会存在一个有效答案
    进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？
     */
    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        int[] ans = twoSum1(nums, target);
        System.out.println(ans[0] + " , " + ans[1]);
        int[] ans2 = twoSum2(nums, target);
        System.out.println(ans2[0] + " , " + ans2[1]);
        int[] ans3 = twoSum3(nums, target);
        System.out.println(ans3[0] + " , " + ans3[1]);
        int[] ans4 = twoSum4(nums, target);
        System.out.println(ans4[0] + " , " + ans4[1]);
    }


    /**
     * 暴力破解
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        int div = 0;
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(div = target - nums[i])) {
                Integer index2 = map.get(div);
                return new int[]{i, index2};
            }
        }
        return new int[]{-1, -1};
    }


    /**
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum3(int[] nums, int target) {
        int[] ans = new int[2];
        // 建立k-v ，一一对应的哈希表
        HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            // 如果找到了对应的补数，说明存在
            if (hash.containsKey(nums[i])) {
                ans[0] = i;
                ans[1] = hash.get(nums[i]);
                return ans;
            } else {
                // 将数据存入 key为补数 ，value为下标
                hash.put(target - nums[i], i);
            }
        }
        return ans;
    }


    /**
     * 先排序，再用快慢指针处理
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum4(int[] nums, int target) {
        // 排序
        Arrays.sort(nums);
        // 慢指针
        int low = 0;
        // 快指针
        int hi = nums.length - 1;
        while (low < hi) {
            int sum = nums[low] + nums[hi];
            if (sum == target) {
                return new int[]{low, hi};
            } else if (sum < target) {
                low++;
            } else {
                hi--;
            }
        }
        return new int[]{-1, -1};
    }


}
