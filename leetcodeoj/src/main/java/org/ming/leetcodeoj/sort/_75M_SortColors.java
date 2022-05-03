package org.ming.leetcodeoj.sort;

import org.ming.common.BaseKit;

/**
 * 75. 颜色分类
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _75M_SortColors {

    public static void main(String[] args) {
        int[] array = new int[]{2, 0, 2, 1, 1, 0};
        sortColors(array);
        BaseKit.print(array);
    }

    /**
     * 75. 颜色分类
     * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
     * <p>
     * 示例 1：
     * 输入：nums = [2,0,2,1,1,0]
     * 输出：[0,0,1,1,2,2]
     * <p>
     * 示例 2：
     * 输入：nums = [2,0,1]
     * 输出：[0,1,2]
     * <p>
     * 示例 3：
     * 输入：nums = [0]
     * 输出：[0]
     * <p>
     * 示例 4：
     * 输入：nums = [1]
     * 输出：[1]
     * <p>
     * 提示：
     * n == nums.length
     * 1 <= n <= 300
     * nums[i] 为 0、1 或 2
     * <p>
     * 进阶：
     * 1. 你可以不使用代码库中的排序函数来解决这道题吗？
     * 2. 你能想出一个仅使用常数空间的一趟扫描算法吗？
     */

    public static void sortColors(int[] nums) {
        int left = 0, right = nums.length - 1;
        for (int i = left; i <= right; i++) {
            int ni = nums[i];
            if (ni == 0) {
                nums[i] = nums[left];
                nums[left] = ni;
                left++;
            } else if (ni == 2) {
                nums[i] = nums[right];
                nums[right] = ni;
                right--;
                i--;
            }
        }
    }

}
