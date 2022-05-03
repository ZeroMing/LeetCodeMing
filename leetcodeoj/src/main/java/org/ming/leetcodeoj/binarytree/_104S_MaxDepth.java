package org.ming.leetcodeoj.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 104. 二叉树的最大深度
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _104S_MaxDepth {

    public static void main(String[] args) {

    }

    /**
     * 104. 二叉树的最大深度
     * 给定一个二叉树，找出其最大深度。
     * <p>
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * <p>
     * 说明: 叶子节点是指没有子节点的节点。
     * <p>
     * 示例：
     * 给定二叉树 [3,9,20,null,null,15,7]，
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回它的最大深度 3
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return depth(root.left, root.right, 1);
    }

    private int depth(TreeNode left, TreeNode right, int depth) {
        if (left == null && right == null) {
            return depth;
        }
        int leftDepth = -1;
        if (left != null) {
            leftDepth = depth(left.left, left.right, depth + 1);
        }
        int rightDepth = -1;
        if (right != null) {
            rightDepth = depth(right.left, right.right, depth + 1);
        }
        return Math.max(leftDepth, rightDepth);
    }

    public static int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int left = maxDepth2(root.left);
            int right = maxDepth2(root.right);
            return Math.max(left, right) + 1;
        }
    }


    /**
     * 广度优先遍历
     *
     * @param root
     * @return
     */
    public int maxDepth3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode poll = queue.poll();
                if (poll == null) {
                    continue;
                }
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
                size--;
            }
            depth++;
        }
        return depth;
    }

}
