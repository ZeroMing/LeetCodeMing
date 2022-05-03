package org.ming.leetcodeoj.binarytree;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 二叉树的层序遍历
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _Offer_32M_LevelOrder {

    public static void main(String[] args) {
        TreeNode node7 = new TreeNode(7, null, null);
        TreeNode node8 = new TreeNode(8, null, null);
        TreeNode node5 = new TreeNode(5, node7, node8);
        TreeNode node6 = new TreeNode(6, null, null);
        TreeNode node4 = new TreeNode(4, null, null);
        TreeNode node3 = new TreeNode(3, node5, node6);
        TreeNode node2 = new TreeNode(2, node4, null);
        TreeNode root = new TreeNode(1, node2, node3);
        levelOrder(root);
    }


    /**
     * 迭代遍历 + 队列
     *
     * @param root
     * @return
     */
    public static void levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);
        }
        int level = 1;
        TreeNode curLast = root, nextLast = null;
        System.out.print("Level " + (level++) + " : ");
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val + " ");
            if (node.left != null) {
                queue.offer(node.left);
                nextLast = node.left;
            }
            if (node.right != null) {
                queue.offer(node.right);
                nextLast = node.right;
            }
            if (node == curLast && !queue.isEmpty()) {
                System.out.print("\nLevel " + (level++) + " : ");
                curLast = nextLast;
            }
        }
    }


    /**
     * 递归
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }
        dfs(1, root, ans);
        return ans;
    }

    private void dfs(int level, TreeNode root, List<List<Integer>> ans) {
        // 新的一行，需要新建一个集合
        if (ans.size() < level) {
            ans.add(new ArrayList<>());
        }

        //将当前节点的值加入到res中，index代表当前层，假设index是3，节点值是99
        //res是[ [1],[2,3] [4] ]，加入后res就变为 [ [1],[2,3] [4,99] ]
        ans.get(level - 1).add(root.val);
        //递归的处理左子树，右子树，同时将层数index+1
        if (root.left != null) {
            dfs(level + 1, root.left, ans);
        }
        if (root.right != null) {
            dfs(level + 1, root.right, ans);
        }
    }

}
