package org.ming.leetcodeoj.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _454M_FourSumCount {
    /**
     * 454. 四数相加 II
     * 给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：
     * <p>
     * 0 <= i, j, k, l < n
     * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
     * 输出：2
     * 解释：
     * 两个元组如下：
     * 1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
     * 2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
     * 示例 2：
     * <p>
     * 输入：nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
     * 输出：1
     * <p>
     * <p>
     * 提示：
     * <p>
     * n == nums1.length
     * n == nums2.length
     * n == nums3.length
     * n == nums4.length
     * 1 <= n <= 200
     * -228 <= nums1[i], nums2[i], nums3[i], nums4[i] <= 228
     */
    public static void main(String[] args) {

    }


    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        if (nums1.length == 0 || nums2.length == 0 || nums3.length == 0 || nums4.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int n1 : nums1) {
            for (int n2 : nums2) {
                int temp = n1 + n2;
                if (map.containsKey(temp)) {
                    map.put(temp, map.get(temp) + 1);
                } else {
                    map.put(temp, 1);
                }
            }
        }
        int ans = 0;
        for (int n3 : nums3) {
            for (int n4 : nums4) {
                int temp = n3 + n4;
                if (map.containsKey(-temp)) {
                    ans += map.get(-temp);
                }
            }
        }
        return ans;
    }
}
