package org.ming.leetcodeoj.binarytree;

import java.util.*;

/**
 * 二叉树的层序遍历
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _ZigZag_LevelOrder {

    public static void main(String[] args) {
        TreeNode node7 = new TreeNode(7, null, null);
        TreeNode node8 = new TreeNode(8, null, null);
        TreeNode node5 = new TreeNode(5, node7, node8);
        TreeNode node6 = new TreeNode(6, null, null);
        TreeNode node4 = new TreeNode(4, null, null);
        TreeNode node3 = new TreeNode(3, node5, node6);
        TreeNode node2 = new TreeNode(2, node4, null);
        TreeNode root = new TreeNode(1, node2, node3);
        levelOrderByZigZag(root);
    }

    public static void printLevelAndOrientation(int level, boolean lr) {
        System.out.print("Level " + level + " from ");
        System.out.print(lr ? "left to right: " : "right to left: ");
    }


    /**
     * 迭代遍历 + 队列
     *
     * @param root
     * @return
     */
    public static void levelOrderByZigZag(TreeNode root) {
        if(root == null){
            return;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offerFirst(root);
        int level = 1;
        // 从左到右打印
        boolean left2right = true;
        // 当前行的最右节点
        TreeNode curLast = root;
        // 下一行的最右节点
        TreeNode  nextLast = null;
        printLevelAndOrientation(level++,left2right);
        while (!queue.isEmpty()) {
            if(left2right){
                root = queue.pollFirst();
                if (root.left != null) {
                    // 区别于"按层打印"，此处的"zigzag打印"的特点为：下一层最后打印的节点是当前层有孩子节点的节点中最先进入dq的节点
                    // 如果nlast非空，则保持其为"最先"进入dq的节点，不更新
                    nextLast = (nextLast == null ? root.left : nextLast);
                    queue.offerLast(root.left);
                }
                if (root.right != null) {
                    nextLast = (nextLast == null ? root.right : nextLast);
                    queue.offerLast(root.right);
                }
            }else{
                root = queue.pollLast();
                // 如果当前节点有孩子节点，先让右孩子从头部进入dq，再让左孩子节点从头部进入dq
                if (root.right != null) {
                    nextLast = (nextLast == null ? root.right : nextLast);
                    queue.offerFirst(root.right);
                }
                if (root.left != null) {
                    nextLast = (nextLast == null ? root.left : nextLast);
                    queue.offerFirst(root.left);
                }
            }
            System.out.print(root.val + " ");
            // 如何确定切换【原则1】和【原则2】的时机？这其实还是如何确定每一层最后一个节点的问题
            // 下一层最后打印的节点是当前层有孩子节点的节点中最先进入dq的节点
            if (root == curLast && !queue.isEmpty()) {
                curLast = nextLast;
                // 换行之后置空nLast，与上面的非空判断配合，保证其为"最先"进入dq的节点
                nextLast = null;
                left2right = !left2right;
                System.out.println();
                printLevelAndOrientation(level++, left2right);
            }
        }
        System.out.println();
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
