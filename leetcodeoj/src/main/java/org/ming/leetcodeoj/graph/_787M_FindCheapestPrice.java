package org.ming.leetcodeoj.graph;

import java.util.Arrays;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _787M_FindCheapestPrice {
    public static void main(String[] args) {
        int n = 3;
        int[][] flights = new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        int src = 0, dst = 2, k = 1;
        System.out.println(findCheapestPrice(n, flights, src, dst, k));
    }

    /**
     * 时间复杂度：共进行 k + 1k+1 次迭代，每次迭代备份数组复杂度为 O(n)O(n)，然后遍历所有的边进行松弛操作，复杂度为 O(m)O(m)。整体复杂度为 O(k * (n + m))O(k∗(n+m))
     * 空间复杂度：O(n)O(n)
     */
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // 默认的最短距离全部为最大值，0的最短距离为0
        int max = 0x3f3f3f3f;
        int[] dist = new int[n];
        Arrays.fill(dist, max);
        dist[src] = 0;
        for (int limit = 0; limit < k + 1; limit++) {
            // 克隆数组
            int[] clone = dist.clone();
            // 航班信息
            for (int[] f : flights) {
                int x = f[0], y = f[1], w = f[2];
                dist[y] = Math.min(dist[y], clone[x] + w);
            }
        }
        return dist[dst] > max / 2 ? -1 : dist[dst];
    }


    public static int findCheapestPrice2(int n, int[][] flights, int src, int dst, int k) {
        if (src == dst) {
            return 0;
        }
        int[] previous = new int[n];
        int[] current = new int[n];
        for (int i = 0; i < n; i++) {
            previous[i] = Integer.MAX_VALUE;
            current[i] = Integer.MAX_VALUE;
        }
        // 初始化为0
        previous[src] = 0;
        for (int i = 1; i < k + 2; i++) {
            current[src] = 0;
            for (int[] flight : flights) {
                int previous_flight = flight[0];
                int current_flight = flight[1];
                int cost = flight[2];
                if (previous[previous_flight] < Integer.MAX_VALUE) {
                    current[current_flight] = Math.min(current[current_flight], previous[previous_flight] + cost);
                }
            }
            previous = current.clone();
        }
        return current[dst] == Integer.MAX_VALUE ? -1 : current[dst];
    }
}
