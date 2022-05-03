package org.ming.leetcodeoj.binarytree;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _226S_InvertTree {

    public static void main(String[] args) {

    }


    public static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = tmp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }


    /**
     * @param root
     * @return
     */
    public static TreeNode invertTree1(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree1(root.left);
        TreeNode right = invertTree1(root.right);
        root.right = left;
        root.left = right;
        return root;
    }


}
