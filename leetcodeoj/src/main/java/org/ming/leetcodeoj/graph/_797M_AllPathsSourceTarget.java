package org.ming.leetcodeoj.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _797M_AllPathsSourceTarget {
    public static void main(String[] args) {
        int[][] graph = new int[][]{{1, 2}, {3}, {3}, {}};
        System.out.println(allPathsSourceTarget(graph));
        System.out.println(allPathsSourceTarget2(graph));
    }


    /**
     * @param graph
     * @return
     */
    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> paths = new ArrayList<>();
        if (graph == null || graph.length == 0) {
            return paths;
        }
        dfs(graph, paths, new ArrayList<>(), 0);
        return paths;
    }


    public static void dfs(int[][] graph, List<List<Integer>> paths, List<Integer> path, int node) {
        path.add(node);
        if (node == graph.length - 1) {
            paths.add(new ArrayList<>(path));
            return;
        }
        int[] nextNodes = graph[node];
        for (int nextNode : nextNodes) {
            dfs(graph, paths, path, nextNode);
            path.remove(path.size() - 1);
        }
    }


    /**
     * bfs解法
     *
     * @param graph
     * @return
     */
    public static List<List<Integer>> allPathsSourceTarget2(int[][] graph) {
        List<List<Integer>> paths = new ArrayList<>();
        if (graph == null || graph.length == 0) {
            return paths;
        }
        Queue<List<Integer>> queue = new LinkedList<>();
        List<Integer> path = new ArrayList<>();
        path.add(0);
        queue.add(path);
        while (!queue.isEmpty()) {
            List<Integer> currentPath = queue.poll();
            int node = currentPath.get(currentPath.size() - 1);
            for (int nextNode : graph[node]) {
                currentPath.add(nextNode);
                if (nextNode == graph.length - 1) {
                    // 找到完整路径，写入结果集
                    paths.add(new ArrayList<>(currentPath));
                } else {
                    // 入队
                    queue.add(new ArrayList<>(currentPath));
                }
                // 选中下一层一个节点后，遍历下一个下层节点，需要先恢复现场
                currentPath.remove(currentPath.size() - 1);
            }
        }
        return paths;
    }

}
