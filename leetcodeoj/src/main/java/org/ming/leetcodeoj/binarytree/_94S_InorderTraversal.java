package org.ming.leetcodeoj.binarytree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 中序遍历
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _94S_InorderTraversal {
    public static void main(String[] args) {

    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>(0);
        }
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            // 先将cur和左节点都入栈
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                // 弹出最左的节点
                cur = stack.pop();
                ans.add(cur.val);
                // 将右节点 设置为当前节点
                cur = cur.right;
            }
        }
        return ans;
    }


    public static void inorderTraversal2(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorderTraversal2(root.left, list);
        list.add(root.val);
        inorderTraversal2(root.right, list);
    }
}
