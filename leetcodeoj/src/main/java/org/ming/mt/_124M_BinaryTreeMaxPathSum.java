package org.ming.mt;

/**
 * 后序遍历
 *
 *
 *
 *
 */
public class _124M_BinaryTreeMaxPathSum {
    /*

    路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
    路径和 是路径中各节点值的总和
    给你一个二叉树的根节点 root ，返回其 最大路径和


    输入：root = [1,2,3]
    输出：6
    解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6


    输入：root = [-10,9,20,null,null,15,7]
    输出：42
    解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42

    提示：

    树中节点数目范围是 [1, 3 * 104]
    -1000 <= Node.val <= 1000


     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /*

    一棵树的情况:
    1. root + left + right
    2. root + left
    3. root + right
    4. root
    5. left
    6. right

     */

    private static int maxSum = Integer.MIN_VALUE;

    public static void main(String[] args) {

        TreeNode root = new TreeNode(-10, null, null);

        TreeNode left1 = new TreeNode(-2, null, null);
        root.left = left1;

        TreeNode right1 = new TreeNode(-3, null, null);
        root.right = right1;

        TreeNode left2_1 = new TreeNode(-10, null, null);
        right1.left = left2_1;

        TreeNode right2_1 = new TreeNode(10, null, null);
        right1.right = right2_1;

        System.out.println(maxPathSum(root));
        System.out.println(maxPathSum2(root));
        System.out.println(maxPathSum3(root));
    }


    public static int maxPathSum(TreeNode root) {
        // 可能是 -1
       getMax(root);
       return maxSum;
    }

    private static int getMax(TreeNode node){
        if(node == null){
            return 0;
        }
        // 剪枝
        int left = Math.max(0,getMax(node.left));
        int right = Math.max(0,getMax(node.right));
        // 最大值
        maxSum = Math.max(maxSum,node.val + left + right);
        return Math.max(left,right) + node.val;
    }


    /*

    最大路径和：根据当前节点的角色，路径和可分为两种情况：
    一：以当前节点为根节点
    1.只有当前节点
    2.当前节点+左子树
    3.当前节点+右子书
    4.当前节点+左子树+右子树
    这四种情况的最大值即为以当前节点为根的最大路径和
    此最大值要和已经保存的最大值比较，得到整个树的最大路径值

    二：当前节点作为父节点的一个子节点
    和父节点连接的话则需取【单端的最大值】
    1.只有当前节点
    2.当前节点+左子树
    3.当前节点+右子书

    这三种情况的最大值

     */

    public static int maxPathSum2(TreeNode root) {
        getMax2(root);
        return maxSum;
    }


    private static int getMax2(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = getMax2(root.left);
        int right = getMax2(root.right);
        int value1 = root.val;
        int value2 = root.val + left;
        int value3 = root.val + right;
        int value4 = root.val + left + right;

        // 以此节点为根节点的最大值
        maxSum = Math.max(Math.max(Math.max(maxSum,root.val + left + right),left),right);
        return Math.max(left,right) + root.val;
    }

    public static int maxPathSum3(TreeNode root) {
        getMax3(root);
        return maxSum;
    }

    private static int getMax3(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = getMax2(root.left);
        int right = getMax2(root.right);

        // 1.只有当前节点
        int value1 = root.val;
        // 2.当前节点+左子树
        int value2 = root.val + left;
        // 3.当前节点+右子书
        int value3 = root.val + right;
        // 4. 当前节点+左子树+右子树
        int value4 = root.val + left + right;
        // 以此节点为根节点的最大值
        int max = Math.max(Math.max(value1, value2), value3);
        int maxValue = Math.max(max,value4);
        // 当前遍历树的最大值
        maxSum = Math.max(maxValue,maxSum);
        return max;
    }


}
