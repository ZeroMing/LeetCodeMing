package org.ming.leetcodeoj.binarytree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 404. 左叶子之和
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _404S_SumOfLeftLeaves {
    public static void main(String[] args) {

    }

    /*
    404. 左叶子之和
计算给定二叉树的所有左叶子之和
示例：

    3
   / \
  9  20
    /  \
   15   7

在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24

     */

    /**
     * 递归，核心在于：如何判断是否为左叶子，需要有父亲，且孩子为空
     *
     * @param root
     * @return
     */
    public static int sumOfLeftLeaves0(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 左叶子 : 是父节点的左孩子，且 该节点的 孩子节点为空
        int midValue = 0;
        // 核心点:::
        if (root.left != null && root.left.left == null && root.left.right == null) {
            midValue = root.left.val;
        }
        return midValue + sumOfLeftLeaves0(root.left) + sumOfLeftLeaves0(root.right);
    }


    /**
     * 先序遍历
     *
     * @param root
     * @return
     */
    public static int sumOfLeftLeaves1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        int sum = 0;
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            if (pop.left != null && pop.left.left == null && pop.left.right == null) {
                sum += pop.left.val;
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
            if (pop.right != null) {
                stack.push(pop.right);
            }
        }
        return sum;
    }

}
