package org.ming.leetcodeoj.binarytree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 257. 二叉树的所有路径
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _257S_BinaryTreePaths {
    public static void main(String[] args) {

    }

    /**
     * 257. 二叉树的所有路径
     * 给你一个二叉树的根节点 root ，按任意顺序 ，
     * 返回所有从根节点到叶子节点的路径。
     * 叶子节点 是指没有子节点的节点
     *
     * <p>
     * 示例 1：
     * 输入：root = [1,2,3,null,5]
     * 输出：["1->2->5","1->3"]
     * <p>
     * 示例 2：
     * 输入：root = [1]
     * 输出：["1"]
     * <p>
     * 提示：
     * 树中节点的数目在范围 [1, 100] 内
     * -100 <= Node.val <= 100
     */
    public static List<String> binaryTreePaths1(TreeNode root) {
        List<String> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        dfs(root, root.val + "", ans);
        return ans;
    }

    private static void dfs(TreeNode root, String s, List<String> ans) {
        if (root.left != null) {
            dfs(root.left, s + "->" + root.left.val, ans);
        }
        if (root.right != null) {
            dfs(root.right, s + "->" + root.right.val, ans);
        }
        if (root.left == null && root.right == null) {
            ans.add(s);
        }
    }

    public static List<String> binaryTreePaths2(TreeNode root) {
        List<String> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        List<Integer> paths = new ArrayList<>();
        dfs(root, paths, ans);
        return ans;
    }

    private static void dfs(TreeNode root, List<Integer> paths, List<String> ans) {
        // 添加路径
        paths.add(root.val);
        if (root.left != null) {
            dfs(root.left, paths, ans);
            // 回溯
            paths.remove(paths.size() - 1);
        }

        if (root.right != null) {
            dfs(root.right, paths, ans);
            // 回溯
            paths.remove(paths.size() - 1);
        }
        // 叶子节点
        if (root.left == null && root.right == null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < paths.size() - 1; i++) {
                sb.append(paths.get(i)).append("->");
            }
            sb.append(paths.get(paths.size() - 1));
            ans.add(sb.toString());
        }
    }


    public static List<String> binaryTreePaths3(TreeNode root) {
        List<String> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Deque<Object> stack = new LinkedList<>();
        stack.push(root);
        stack.push(root.val + "");
        while (!stack.isEmpty()) {
            String path = (String) stack.pop();
            TreeNode node = (TreeNode) stack.pop();
            // 叶子节点，直接返回对应的路径
            if (node.left == null && node.right == null) {
                ans.add(path);
            }
            // 左节点不为空
            if (node.left != null) {
                stack.push(node.left);
                stack.push(path + "->" + node.left.val);
            }
            // 右节点不为空
            if (node.right != null) {
                stack.push(node.right);
                stack.push(path + "->" + node.right.val);
            }
        }
        return ans;
    }
}
