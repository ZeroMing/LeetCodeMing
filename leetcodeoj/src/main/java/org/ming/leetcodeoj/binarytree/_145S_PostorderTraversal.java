package org.ming.leetcodeoj.binarytree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _145S_PostorderTraversal {
    public static void main(String[] args) {

    }

    public static List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>(0);
        }
        // 结果
        List<Integer> ans = new ArrayList<>();
        // 栈
        Deque<TreeNode> stack = new LinkedList<>();
        // 临时保存已经处理过的节点
        TreeNode prevHandleNode = null;
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            // 将左侧全部放进去
            // 弹出节点，判断
            cur = stack.pop();
            if (cur.right == null || cur.right == prevHandleNode) {
                ans.add(cur.val);
                prevHandleNode = cur;
                cur = null;
            } else {
                // 节点右节点不为空 且 右节点没访问过，重新入栈，处理右节点
                stack.push(cur);
                // 使节点等于→右子节节点
                cur = cur.right;
            }
        }
        return ans;
    }


    public static void postorderTraversal2(TreeNode root,List<Integer> list) {
        if (root == null) {
            return;
        }
        postorderTraversal2(root.left, list);
        postorderTraversal2(root.right, list);
        list.add(root.val);
    }
}
