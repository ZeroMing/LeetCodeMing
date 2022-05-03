package org.ming.leetcodeoj.binarytree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 144. 二叉树的前序遍历
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _144S_PreorderTraversal {
    public static void main(String[] args) {

    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        pfs(ans, root);
        return ans;
    }

    /**
     * 前序遍历，递归
     *
     * @param ans
     * @param root
     */
    private void pfs(List<Integer> ans, TreeNode root) {
        if (root == null) {
            return;
        }
        // 先中
        ans.add(root.val);
        // 后左
        pfs(ans, root.left);
        // 再右
        pfs(ans, root.right);
    }


    /**
     * 前序遍历，迭代，栈，后进先出
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        if (root == null) {
            return new ArrayList<>(0);
        }
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode top = stack.pop();
            ans.add(top.val);
            // 先右
            if (top.right != null) {
                stack.push(top.right);
            }
            // 后左
            if (top.left != null) {
                stack.push(top.left);
            }
        }
        return ans;
    }


}
