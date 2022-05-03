package org.ming.leetcodeoj.graph;

import java.util.Arrays;

/**
 * 力扣 743. 网络延迟时间
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _743M_NetworkDelayTime {

    public static void main(String[] args) {
        int[][] times = new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        System.out.println(networkDelayTime(times, 4, 2));
    }

    /**
     * 从 k 点出发，所有点都被访问到的最短时间，将问题转换一下其实就是求「从 kk 点出发，到其他点 xx 的最短距离的最大值」
     * <p>
     * 存图方式
     * 1. 邻接矩阵
     * 2. 邻接表
     * 3. 类
     * dijkstra
     * <p>
     * <p>
     * Floyd
     *
     * @param times
     * @param n
     * @param k
     * @return
     */
    public static int networkDelayTime(int[][] times, int n, int k) {
        if (times.length == 0 || n < k) {
            return -1;
        }
        final int max = Integer.MAX_VALUE / 2;
        int[][] g = new int[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(g[i], max);
        }
        for (int[] t : times) {
            int x = t[0] - 1, y = t[1] - 1;
            g[x][y] = t[2];
        }
        int[] dist = new int[n];
        Arrays.fill(dist, max);
        dist[k - 1] = 0;
        boolean[] used = new boolean[n];
        for (int i = 0; i < n; ++i) {
            int x = -1;
            for (int y = 0; y < n; ++y) {
                if (!used[y] && (x == -1 || dist[y] < dist[x])) {
                    x = y;
                }
            }
            used[x] = true;
            for (int y = 0; y < n; ++y) {
                dist[y] = Math.min(dist[y], dist[x] + g[x][y]);
            }
        }
        int ans = Arrays.stream(dist).max().getAsInt();
        return ans == max ? -1 : ans;
    }

}
