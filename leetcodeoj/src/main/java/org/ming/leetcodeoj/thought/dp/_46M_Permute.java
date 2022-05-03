package org.ming.leetcodeoj.thought.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _46M_Permute {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        System.out.println(permute(nums));
    }


    public static List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        int depth = 0;
        permuteForAll(nums, len, depth, used, ans, path);
        return ans;
    }

    private static void permuteForAll(int[] nums, int len, int depth, boolean[] used, List<List<Integer>> ans, List<Integer> path) {
        if (depth == len) {
            ans.add(new ArrayList<>(path));
            return;
        }
        // 在非叶子结点处，产生不同的分支，这一操作的语义是：在还未选择的数中依次选择一个元素作为下一个位置的元素，这显然得通过一个循环实现。
        for (int i = 0; i < len; i++) {
            // 未使用
            if (!used[i]) {
                path.add(nums[i]);
                used[i] = true;
                // 递归
                permuteForAll(nums, len, depth + 1, used, ans, path);
                // 还原现场
                // 注意：下面这两行代码发生 「回溯」，回溯发生在从 深层结点 回到 浅层结点 的过程，代码在形式上和递归之前是对称的
                path.remove(path.size() - 1);
                used[i] = false;
            }
        }

    }
}
