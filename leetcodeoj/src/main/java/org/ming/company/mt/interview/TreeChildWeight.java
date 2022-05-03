package org.ming.company.mt.interview;

import java.util.HashMap;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class TreeChildWeight {
    public static void main(String[] args) {

    }

    /**
     * 有一棵树，给定头节点 h，和结构数组 m，下标 0弃而不用
     * 比如 h = 1, m = [ [] , [2,3], [4], [5,6], [], [], []]
     * 表示1的孩子是2、3; 2 的孩子是4; 3的孩子是5、6; 4、5和6是叶节点，都不再有孩子
     * 每一个节点都有颜色，记录在c数组里，比如c[i] = 4, 表示节点i的颜色为4
     * 一开始只有叶节点是有权值的，记录在w数组里，
     * 比如，如果一开始就有w[i] = 3, 表示节点i是叶节点、且权值是3
     * 现在规定非叶节点i的权值计算方式：
     * 根据 i的所有直接孩子来计算，假设i的所有直接孩子，颜色只有 a,b,k
     * w[i] = Max {
     * (颜色为a的所有直接孩子个数 + 颜色为a的直接孩子权值之和),
     * (颜色为b的所有直接孩子个数 + 颜色为b的直接孩子权值之和),
     * (颜色为k的所有直接孩子个数 + 颜色k的直接孩子权值之和)
     * }
     * 请计算所有孩子的权值并返回
     */
    public static void treeChildWeight(int h, int[][] m, int[] w, int[] c) {
        if (m.length == 0) {
            return;
        }
        // 有若干个直接孩子
        // 1 7个
        // 3 10个
        HashMap<Integer, Integer> colors = new HashMap<>();
        // 1 20
        // 3 45
        HashMap<Integer, Integer> weihts = new HashMap<>();
        for (int child : m[h]) {
            treeChildWeight(child, m, w, c);
            colors.put(c[child], colors.getOrDefault(c[child], 0) + 1);
            weihts.put(c[child], weihts.getOrDefault(c[child], 0) + w[child]);
        }
        for (int color : colors.keySet()) {
            w[h] = Math.max(w[h], colors.get(color) + weihts.get(color));
        }

    }

}
