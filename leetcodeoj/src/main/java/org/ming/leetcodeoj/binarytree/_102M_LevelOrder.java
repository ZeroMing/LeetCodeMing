package org.ming.leetcodeoj.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的层序遍历
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _102M_LevelOrder {

    public static void main(String[] args) {

    }


    /**
     * 迭代遍历 + 队列
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);
        }
        while (!queue.isEmpty()) {
            List<Integer> itemList = new ArrayList<>();
            int len = queue.size();
            while (len > 0) {
                TreeNode node = queue.poll();
                itemList.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                len--;
            }
            ans.add(itemList);
        }
        return ans;
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
