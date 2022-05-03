package org.ming.leetcodeoj.thought.greedy;

import java.util.Arrays;

/**
 * 455. 分发饼干
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _455S_FindContentChildren {

    public static void main(String[] args) {
        int[] g = new int[]{1, 2};
        int[] s = new int[]{1, 2, 3};
        System.out.println(findContentChildren(g, s));
    }


    public static int findContentChildren(int[] g, int[] s) {
        // 按孩子胃口排序
        Arrays.sort(g);
        // 按饼干排序
        Arrays.sort(s);
        boolean[] used = new boolean[s.length];
        int ans = 0;
        for (int i = 0; i < g.length; i++) {
            boolean has = false;
            for (int j = 0; j < s.length; j++) {
                if (used[j]) {
                    continue;
                }
                // 存在满足的节点，直接退出循环
                if (s[j] >= g[i]) {
                    used[j] = true;
                    has = true;
                    break;
                }
            }
            if (has) {
                ans++;
            }
        }
        return ans;
    }

    public static int findContentChildren2(int[] g, int[] s) {
        // 按孩子胃口排序
        Arrays.sort(g);
            // 按饼干排序
        Arrays.sort(s);
        int child = 0;
        int cookie = 0;
        // 当其中一个遍历完就结束
        while (child < g.length && cookie < s.length) {
            // 饼干 > 孩子胃口, 当用当前饼干可以满足当前孩子的需求，可以满足的孩子数量+1
            if (s[child] >= g[cookie]) {
                child++;
            }
            // 饼干只可以用一次，因为饼干如果小的话，就是无法满足被抛弃，满足的话就是被用了
            cookie++;
        }
        return child;
    }
}
