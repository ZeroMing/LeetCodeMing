package org.ming.leetcodeoj.binarytree;

import org.ming.common.TreeNode;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class DiameterOfBinaryTree {
    /**
     * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
     * 示例 :
     * 给定二叉树
     * 1
     * / \
     * 2   3
     * / \   \
     * 4   5   10
     * / \  /    \
     * 6  7  8    11
     * 返回3, 它的长度是路径 [4,2,1,3] 或者[5,2,1,3]。
     * <p>
     * 注意：两结点之间的路径长度是以它们之间边的数目表示。
     */
    static int max = 0;

    public static void main(String[] args) {

    }

    public int diameterOfBinaryTree1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);
        return max;
    }

    private static int dfs(TreeNode root) {
        if (root.left == null && root.right == null) {
            return 0;
        }
        int leftHeight = root.left == null ? 0 : dfs(root.left) + 1;
        int rightHeight = root.right == null ? 0 : dfs(root.right) + 1;
        max = Math.max(max, leftHeight + rightHeight);
        return Math.max(leftHeight, rightHeight);
    }


    /**
     * 单线程（接受任务）  -> 丢到到内存队列里（生产者） -> （消费者）使用线程池
     * 多次一举？直接使用线程池异步就可以吧
     */
    public int diameterOfBinaryTree2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);
        return max;
    }
}
