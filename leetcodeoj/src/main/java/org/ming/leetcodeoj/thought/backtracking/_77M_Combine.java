package org.ming.leetcodeoj.thought.backtracking;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 77. 组合
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _77M_Combine {

    public static void main(String[] args) {

    }


    /**
     * [1,2,3,4,5,6,7] 7
     * / | | | \
     * 1 2 3 4 5
     * <p>
     * backtracking
     *
     * @param n
     * @param k
     * @return
     */
    public static List<List<Integer>> combine(int n, int k) {
        // 1. 回溯函数模板返回值以及参数 backtracking
        List<List<Integer>> ans = new ArrayList<>();
        if (k <= 0 || n < k) {
            return ans;
        }
        // 从 1 开始是题目的设定
        Deque<Integer> path = new LinkedList<>();
        backtracking(n, k, 1, path, ans);
        return ans;
    }


    public static void backtracking(int n, int k, int startIndex, Deque<Integer> path, List<List<Integer>> ans) {
        // 递归终止条件是：path 的长度等于 k
        if (path.size() == k) {
            ans.add(new ArrayList<>(path));
            return;
        }
        // 遍历可能的搜索起点
        // for (int i = startIndex; i <= n; i++) {
        // 剪枝
        for (int i = startIndex; i <= n - (k - path.size()) + 1; i++) {
            // 向路径变量里添加一个数
            path.addLast(i);
            // 下一轮搜索，设置的搜索起点要加 1，因为组合数理不允许出现重复的元素
            backtracking(n, k, i + 1, path, ans);
            // 重点理解这里：深度优先遍历有回头的过程，因此递归之前做了什么，递归之后需要做相同操作的逆向操作
            path.removeLast();
        }
    }


    public static List<List<Integer>> combine2(int n, int k) {
        // 1. 回溯函数模板返回值以及参数 backtracking
        List<List<Integer>> ans = new ArrayList<>();
        if (k <= 0 || n < k) {
            return ans;
        }
        // 从 1 开始是题目的设定
        Deque<Integer> path = new LinkedList<>();
        backtracking1(1, n, k, path, ans);
        return ans;
    }

    public static void backtracking1(int cur, int n, int k, Deque<Integer> path, List<List<Integer>> ans) {
        // 剪枝：temp 长度加上区间 [cur, n] 的长度小于 k，不可能构造出长度为 k 的 temp
        if (path.size() + (n - cur + 1) < k) {
            return;
        }
        // 记录合法的答案
        if (path.size() == k) {
            ans.add(new ArrayList<>(path));
            return;
        }
        // 考虑选择当前位置
        path.add(cur);
        backtracking1(cur + 1, n, k, path, ans);
        path.remove(path.size() - 1);
        // 考虑不选择当前位置
        backtracking1(cur + 1, n, k, path, ans);
    }

    public static List<List<Integer>> combine3(int n, int k) {
        // 1. 回溯函数模板返回值以及参数 backtracking
        List<List<Integer>> ans = new ArrayList<>();
        // 从 1 开始是题目的设定
        List<Integer> path = new ArrayList<>();
        for (int i = 1; i <= k; i++) {
            path.add(i);
        }
        path.add(n + 1);
        int j = 0;
        while (j < k) {
            ans.add(new ArrayList<>(path.subList(0, k)));
            j = 0;


        }
        return ans;
    }
}
