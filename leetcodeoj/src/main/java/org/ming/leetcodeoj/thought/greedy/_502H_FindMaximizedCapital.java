package org.ming.leetcodeoj.thought.greedy;

import java.util.*;

/**
 * 502. IPO 项目收益最大化
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _502H_FindMaximizedCapital {

    public static void main(String[] args) {
        int k = 3, w = 0;
        int[] profits = new int[]{1, 2, 3}, capital = new int[]{0, 1, 2};
        System.out.println(findMaximizedCapital2(k, w, profits, capital));
    }

    public static int findMaximizedCapital1(int k, int w, int[] profits, int[] capital) {
        // 规范化一下 k，使其小于等于 n
        k = Math.min(k, profits.length);
        // 检测是否可以加速
        boolean canSpeedUp = canSpeedUp(w, capital);
        if (canSpeedUp) {
            return speedUp(k, w, profits);
        }
        // 不能加速，每次取能力范围内的最大利润，取 k 轮
        return normal(k, w, profits, capital);
    }

    private static int normal(int k, int w, int[] profits, int[] capital) {
        // 共进行 K 轮投资
        for (int i = 0; i < k; i++) {
            int maxProfits = -1;
            int maxIndex = -1;
            for (int j = 0; j < profits.length; j++) {
                // 每次选能力范围内利润最大的
                if (w >= capital[j] && profits[j] > maxProfits) {
                    maxProfits = profits[j];
                    maxIndex = j;
                }
            }
            // 如果这一轮没有可投资的项目了，下一轮肯定也没有，直接退出循环
            if (maxIndex == -1) {
                break;
            }
            // 拥有的资本加上最大利润
            w += profits[maxIndex];
            // 将投资过的项目的成本修改为无限大，这样下次就不会再投资了
            capital[maxIndex] = Integer.MAX_VALUE;
        }
        return w;
    }

    private static int speedUp(int k, int w, int[] profits) {
        // 取前 k 大的利润
        Arrays.sort(profits);
        for (int i = profits.length - 1; i >= profits.length - k; i--) {
            w += profits[i];
        }
        return w;
    }

    private static boolean canSpeedUp(int w, int[] capital) {
        for (int i = 0; i < capital.length; i++) {
            // 如果拥有的资本不能投资所有项目，说明不能加速
            if (w < capital[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 我们先搞清楚题目在说啥，简单用一句话总结：初始时你手里的钱是 w ，要拿这笔钱从 n 个项目中投资 k 个项目，保证最后你手里的钱最多，每个项目有利润 profits 和成本 capital。
     * <p>
     * 我们做两种假设：
     * <p>
     * 假设一：假如你手里的钱有很多，超过了每个项目的成本。
     * 假设二：你手里的钱有限，只能投资有限的几个项目。
     * 对于假设一，因为我手里的钱超过了每个项目的成本，所以，我是不是每次投资利润最大的项目就可以了？是的，没错。因此，针对这种情况，我们直接取前 k 大利润的项目返回即可。
     * <p>
     * 对于假设二，因为我手头有点紧张，所以，我每次都要谨慎选择，我每次都选择在我能力范围内能获得最大利润的项目，才能使得最后我手里的钱最多，对不对？这就是贪心的思想。
     *
     * @param k       最多 k 个不同项目
     * @param w       初始资本为w
     * @param profits 每个项目的收益
     * @param capital 每个项目需要的最小资本
     * @return
     */
    public static int findMaximizedCapital2(int k, int w, int[] profits, int[] capital) {
        List<int[]> projects = new ArrayList<>();
        for (int i = 0; i < profits.length; i++) {
            projects.add(new int[]{capital[i], profits[i]});
        }
        // 按工程费用从小到大排序
        projects.sort(Comparator.comparingInt(a -> a[0]));
        // 大根堆，每次都将最大收益放到堆顶
        PriorityQueue<Integer> bigRootQueue = new PriorityQueue<>((a, b) -> b - a);
        // 最多选k个项目
        int i = 0;
        while (k-- > 0) {
            while (i < capital.length && projects.get(i)[0] <= w) {
                // 大根堆，保持最大值在堆顶
                bigRootQueue.add(projects.get(i)[1]);
            }
            if (bigRootQueue.isEmpty()) {
                break;
            }
            w += bigRootQueue.poll();
        }
        return w;
    }

    /*
    502. IPO
假设 力扣（LeetCode）即将开始 IPO 。为了以更高的价格将股票卖给风险投资公司，力扣 希望在 IPO 之前开展一些项目以增加其资本。 由于资源有限，它只能在 IPO 之前完成最多 k 个不同的项目。帮助 力扣 设计完成最多 k 个不同项目后得到最大总资本的方式。

给你 n 个项目。对于每个项目 i ，它都有一个纯利润 profits[i] ，和启动该项目需要的最小资本 capital[i] 。

最初，你的资本为 w 。当你完成一个项目时，你将获得纯利润，且利润将被添加到你的总资本中。

总而言之，从给定项目中选择 最多 k 个不同项目的列表，以 最大化最终资本 ，并输出最终可获得的最多资本。

答案保证在 32 位有符号整数范围内。



示例 1：

输入：k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
输出：4
解释：
由于你的初始资本为 0，你仅可以从 0 号项目开始。
在完成后，你将获得 1 的利润，你的总资本将变为 1。
此时你可以选择开始 1 号或 2 号项目。
由于你最多可以选择两个项目，所以你需要完成 2 号项目以获得最大的资本。
因此，输出最后最大化的资本，为 0 + 1 + 3 = 4。
示例 2：

输入：k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]
输出：6


提示：

1 <= k <= 105
0 <= w <= 109
n == profits.length
n == capital.length
1 <= n <= 105
0 <= profits[i] <= 104
0 <= capital[i] <= 109
     */
}
