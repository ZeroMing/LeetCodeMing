package org.ming.leetcodeoj.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 222. 完全二叉树的节点个数
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _222M_CountNodes {
    public static void main(String[] args) {

    }


    /**
     * 深度优先遍历
     * 时间复杂度：O(n)
     *
     * @param root
     * @return
     */
    public static int countNodes0(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = countNodes0(root.left);
        int right = countNodes0(root.right);
        return left + right + 1;
    }

    /**
     * 层次遍历
     *
     * @param root
     * @return
     */
    public static int countNodes4(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int count = 1;
        // 层次遍历标准代码
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode poll = queue.poll();
                if (poll == null) {
                    break;
                }
                count++;
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
        }
        return count;
    }


    /**
     * 利用完全二叉树的特性计算节点个数，递归
     *
     * @param root
     * @return
     */
    public static int countNodes1(TreeNode root) {
        if (root == null) {
            return 0;
        }

        TreeNode left = root;
        TreeNode right = root;
        int height = 0;
        while (right != null) {
            left = left.left;
            right = right.right;
            height++;
        }
        // 代表为满二叉树
        if (left == null) {
            return (1 << height) - 1;
        }
        return 1 + countNodes1(root.left) + countNodes1(root.right);
    }

    /**
     * 利用完全二叉树的特性计算节点个数
     *
     * @param root
     * @return
     */
    public static int countNodes2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        TreeNode left = root;
        int level = 0;
        while (left != null) {
            left = left.left;
            level++;
        }
        //
        int low = 1 << level, high = (1 << (level + 1)) - 1;
        // 代表为满二叉树
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (exists(root, level, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public static boolean exists(TreeNode root, int level, int k) {
        int bits = 1 << (level - 1);
        TreeNode node = root;
        while (node != null && bits > 0) {
            if ((bits & k) == 0) {
                node = node.left;
            } else {
                node = node.right;
            }
            bits >>= 1;
        }
        return node != null;
    }

}
