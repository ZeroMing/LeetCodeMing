package org.ming.leetcodeoj.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _111S_MinDepth {
    public static void main(String[] args) {

    }

    /**
     * 111. 二叉树的最小深度
     * 给定一个二叉树，找出其最小深度。
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     * 说明：叶子节点是指没有子节点的节点。
     * <p>
     * 示例 1：
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：2
     * <p>
     * 示例 2：
     * 输入：root = [2,null,3,null,4,null,5,null,6]
     * 输出：5
     * <p>
     * 提示：
     * 树中节点数的范围在 [0, 105] 内
     * -1000 <= Node.val <= 1000
     */

    /**
     * 广度优先遍历
     *
     * @param root
     * @return
     */
    public int minDepth0(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<QueueNode> queue = new LinkedList<>();
        queue.offer(new QueueNode(root, 1));
        while (!queue.isEmpty()) {
            QueueNode queueNode = queue.poll();
            TreeNode node = queueNode.node;
            int depth = queueNode.depth;
            if (node.left == null && node.right == null) {
                return depth;
            }
            if (node.left != null) {
                queue.offer(new QueueNode(node.left, depth + 1));
            }
            if (node.right != null) {
                queue.offer(new QueueNode(node.right, depth + 1));
            }
        }
        return 0;
    }

    public int minDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<Object> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(1);
        while (!queue.isEmpty()) {
            TreeNode node = (TreeNode) queue.poll();
            int depth = (int) queue.poll();
            ;
            if (node.left == null && node.right == null) {
                return depth;
            }
            if (node.left != null) {
                queue.offer(node.left);
                queue.offer(depth + 1);
            }
            if (node.right != null) {
                queue.offer(node.right);
                queue.offer(depth + 1);
            }
        }
        return 0;
    }

    static class QueueNode {
        TreeNode node;
        int depth;

        public QueueNode(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }


    public int minDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        // 左最小值
        int ans = Integer.MAX_VALUE;
        if (root.left != null) {
            ans = Math.min(minDepth2(root.left), ans);
        }
        // 右树最小是
        if (root.right != null) {
            ans = Math.min(minDepth2(root.right), ans);
        }
        // 每次递归 + 1
        return ans + 1;
    }
}
