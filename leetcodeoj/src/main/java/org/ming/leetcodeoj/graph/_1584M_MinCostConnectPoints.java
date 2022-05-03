package org.ming.leetcodeoj.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 力扣 1584. 连接所有点的最小费用
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _1584M_MinCostConnectPoints {

    public static void main(String[] args) {
        int[][] points = new int[][]{{3, 12}, {-2, 5}, {-4, 1}};
        // int[][] points = new int[][]{{0, 0}, {1, 1}, {1, 0}, {-1, 1}};
        // int[][] points = new int[][]{{3, 12}, {-2, 5}, {-4, 1}};
        System.out.println(minCostConnectPoints(points));
        System.out.println(minCostConnectPoints2(points));
        System.out.println(minCostConnectPoints3(points));
        System.out.println(minCostConnectPoints4(points));
    }


    public static int minCostConnectPoints(int[][] points) {
        // 被访问节点
        List<int[]> visitedList = new ArrayList<>();
        visitedList.add(points[0]);
        // 未访问节点
        List<int[]> unVisitedList = new ArrayList<>();
        for (int i = 1; i < points.length; i++) {
            unVisitedList.add(points[i]);
        }
        boolean[] visited = new boolean[points.length];
        int ans = 0;
        while (!unVisitedList.isEmpty()) {
            int minDistance = Integer.MAX_VALUE;
            int[] selected = null;
            for (int i = 0; i < visitedList.size(); i++) {
                for (int j = 0; j < unVisitedList.size(); j++) {
                    int xD = Math.abs(unVisitedList.get(j)[0] - visitedList.get(i)[0]);
                    int yD = Math.abs(unVisitedList.get(j)[1] - visitedList.get(i)[1]);
                    int d = xD + yD;
                    if (d < minDistance) {
                        selected = unVisitedList.get(j);
                        minDistance = d;
                    }
                }
            }
            // 减少未访问的节点
            ans += minDistance;
            unVisitedList.remove(selected);
            // 新增被访问的节点
            visitedList.add(selected);
            minDistance = Integer.MAX_VALUE;
            selected = null;
        }
        return ans;
    }

    public static int minCostConnectPoints2(int[][] points) {
        // 被访问节点
        List<int[]> visitedList = new ArrayList<>();
        visitedList.add(points[0]);
        boolean[] visited = new boolean[points.length];
        visited[0] = true;
        int ans = 0;
        while (visitedList.size() < points.length) {
            int minDistance = Integer.MAX_VALUE;
            int[] selected = null;
            int selectIndex = -1;
            for (int i = 0; i < visitedList.size(); i++) {
                for (int j = 0; j < points.length; j++) {
                    if (visited[j]) {
                        continue;
                    }
                    int xD = Math.abs(points[j][0] - visitedList.get(i)[0]);
                    int yD = Math.abs(points[j][1] - visitedList.get(i)[1]);
                    int d = xD + yD;
                    if (d < minDistance) {
                        selected = points[j];
                        selectIndex = j;
                        minDistance = d;
                    }
                }
            }
            if (selectIndex != -1) {
                // 减少未访问的节点
                ans += minDistance;
                visited[selectIndex] = true;
                // 新增被访问的节点
                visitedList.add(selected);
                minDistance = Integer.MAX_VALUE;
                selected = null;
                selectIndex = -1;
            }
        }
        return ans;
    }

    /**
     * Prim  最小生成树
     *
     * @param points
     * @return
     */
    public static int minCostConnectPoints4(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        int size = points.length;
        // 小根堆
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(x -> x.cost));
        // Add all edges from points[0] vertexs
        for (int j = 1; j < size; j++) {
            // Calculate the distance between two coordinates.
            int[] coordinate1 = points[0];
            int[] coordinate2 = points[j];
            int cost = Math.abs(coordinate1[0] - coordinate2[0]) + Math.abs(coordinate1[1] - coordinate2[1]);
            Edge edge = new Edge(0, j, cost);
            priorityQueue.add(edge);
        }
        boolean[] visited = new boolean[size];
        int result = 0;
        int count = size - 1;
        visited[0] = true;
        while (priorityQueue.size() > 0 && count > 0) {
            Edge edge = priorityQueue.poll();
            int point2 = edge.point2;
            int cost = edge.cost;
            if(!visited[point2]){
                result += cost;
                visited[point2] = true;
                for (int j = 0; j < size; j++ ) {
                    if ( !visited[j] ) {
                        int distance = Math.abs(points[point2][0] - points[j][0]) + Math.abs(points[point2][1] - points[j][1]);
                        priorityQueue.add(new Edge(point2, j, distance));
                    }
                }
                count--;
            }
        }
        return result;
    }


    /**
     * Kruskal  最小生成树
     *
     * @param points
     * @return
     */
    public static int minCostConnectPoints3(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        int size = points.length;
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.cost));
        UnionFind uf = new UnionFind(size);

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                int[] coordinate1 = points[i];
                int[] coordinate2 = points[j];
                // Calculate the distance between two coordinates.
                int cost = Math.abs(coordinate1[0] - coordinate2[0]) + Math.abs(coordinate1[1] - coordinate2[1]);
                Edge edge = new Edge(i, j, cost);
                pq.add(edge);
            }
        }
        int result = 0;
        int count = size - 1;
        while (pq.size() > 0 && count > 0) {
            Edge e = pq.poll();
            if (!uf.connected(e.point1, e.point2)) {
                uf.union(e.point1, e.point2);
                result += e.cost;
                count--;
            }
        }
        return result;
    }

    static class Edge {
        int point1;
        int point2;
        int cost;

        Edge(int point1, int point2, int cost) {
            this.point1 = point1;
            this.point2 = point2;
            this.cost = cost;
        }
    }

    static class UnionFind {
        int root[];
        int rank[];

        public UnionFind(int size) {
            root = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                root[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int x) {
            if (x == root[x]) {
                return x;
            }
            return root[x] = find(root[x]);
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY]) {
                    root[rootY] = rootX;
                } else if (rank[rootX] < rank[rootY]) {
                    root[rootX] = rootY;
                } else {
                    root[rootY] = rootX;
                    rank[rootX] += 1;
                }
            }
        }

        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }
    }

}
