package org.ming.leetcodeoj.binarytree;

/**
 * 110. 平衡二叉树
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _110S_IsBalanced {
    public static void main(String[] args) {

    }

    /**
     * 110. 平衡二叉树
     * 给定一个二叉树，判断它是否是高度平衡的二叉树。
     * 本题中，一棵高度平衡二叉树定义为：
     * 一个二叉树每个节点的左右两个子树的高度差的绝对值不超过 1
     * <p>
     * 示例 1：
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：true
     * <p>
     * 示例 2：
     * 输入：root = [1,2,2,3,3,null,null,4,4]
     * 输出：false
     * <p>
     * 示例 3：
     * 输入：root = []
     * 输出：true
     * <p>
     * 提示：
     * 树中的节点数在范围 [0, 5000] 内
     * -104 <= Node.val <= 104
     */
    public boolean isBalanced(TreeNode root) {
        // 平衡二叉树的定义，左右子树的高度，不好超过1
        // 求高度: 后序遍历
        // 求深度: 前序遍历
        // 我们可以使用层序遍历来求深度，但是就不能直接用层序遍历来求高度了，这就体现出求高度和求深度的不同
        return getHeight(root) != -1;
    }

    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 左子树高度
        int leftHeight = getHeight(root.left);
        if (leftHeight == -1) {
            return -1;
        }
        // 右子树高度
        int rightHeight = getHeight(root.right);
        if (rightHeight == -1) {
            return -1;
        }
        // 左右子树高度差大于1，return -1表示已经不是平衡树了
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        // 返回最大的高度
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /*
                1
               2  3
              4 5
             6
     */
}
