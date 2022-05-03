package org.ming.leetcodeoj.thought.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. 子集
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _78M_Subsets {
    private static int count;
    private static int[] nums;
    private static boolean[] checked;
    private static List<List<Integer>> ans;

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        System.out.println(subsets0(nums));
        System.out.println(subsets1(nums));
        System.out.println(subsets2(nums));
    }


    /**
     * 78. 子集
     * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     * <p>
     * 示例 1：
     * 输入：nums = [1,2,3]
     * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     * <p>
     * 示例 2：
     * 输入：nums = [0]
     * 输出：[[],[0]]
     * <p>
     * 提示：
     * 1 <= nums.length <= 10
     * -10 <= nums[i] <= 10
     * nums 中的所有元素 互不相同
     */

    /**
     * 递归+回溯
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets0(int[] nums) {
        // 最大长度
        List<List<Integer>> ans = new ArrayList<>();
        backTracking(0, nums, ans, new ArrayList<Integer>());
        return ans;
    }

    /**
     * 递归+回溯
     *
     * @param startIndex
     * @param nums
     * @param ans
     * @param paths
     */
    private static void backTracking(int startIndex, int[] nums, List<List<Integer>> ans, ArrayList<Integer> paths) {
        ans.add(new ArrayList<>(paths));
        for (int i = startIndex; i < nums.length; i++) {
            paths.add(nums[i]);
            backTracking(i + 1, nums, ans, paths);
            paths.remove(paths.size() - 1);
        }
    }


    /**
     * 迭代，二进制
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets1(int[] nums) {
        List<Integer> path = new ArrayList<Integer>();
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 最大长度
        int n = nums.length;
        for (int mask = 0; mask < (1 << n); ++mask) {
            path.clear();
            for (int i = 0; i < n; ++i) {
                if ((mask & (1 << i)) != 0) {
                    path.add(nums[i]);
                }
            }
            ans.add(new ArrayList<>(path));
        }
        return ans;
    }


    /**
     * [1,2,3]
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets2(int[] nums) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>(0));
        // 最大长度
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int size = ans.size();
            for (int j = 0; j < size; j++) {
                List<Integer> newPath = new ArrayList<>(ans.get(j));
                newPath.add(nums[i]);
                ans.add(newPath);
            }
        }
        return ans;
    }


}
