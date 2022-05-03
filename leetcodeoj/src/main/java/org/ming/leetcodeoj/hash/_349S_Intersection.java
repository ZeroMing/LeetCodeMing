package org.ming.leetcodeoj.hash;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _349S_Intersection {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 2, 1};
        int[] nums2 = new int[]{2, 2};
        int[] intersection = intersection(nums1, nums2);
        System.out.println(Arrays.asList(intersection));
    }


    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums1) {
            set.add(n);
        }
        Set<Integer> reSet = new HashSet<>();
        for (int n : nums2) {
            if (set.contains(n)) {
                reSet.add(n);
            }
        }
        int[] ans = new int[reSet.size()];
        int index = 0;
        for (int a : reSet) {
            ans[index] = a;
        }
        return ans;
    }
}
