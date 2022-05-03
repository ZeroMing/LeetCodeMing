package org.ming.leetcodeoj.array;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class BinaryFind {
    public static void main(String[] args) {

    }


    /**
     * 左闭右闭[]
     *
     * @param array
     * @param target
     * @return
     */
    public int binaryFind1(int[] array, int target) {
        int len = array.length;
        int left = 0, right = len - 1;
        while (left <= right) {
            int middle = left + ((right - left) / 2);
            if (array[middle] > target) {
                right = middle - 1;
            } else if (array[middle] < target) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    /**
     * 左闭右开
     *
     * @param array
     * @param target
     * @return
     */
    public int binaryFind2(int[] array, int target) {
        int len = array.length;
        // 右开，那就是不会等于right
        int left = 0, right = len;
        // while (left < right)，这里使用 < ,因为left == right在区间[left, right)是没有意义的
        while (left < right) {
            int middle = left + ((right - left) / 2);
            if (array[middle] > target) {
                // target 在左区间，在[left, middle)中
                right = middle;
            } else if (array[middle] < target)  {
                // target 在右区间，在[middle + 1, right)中
                left = middle + 1;
            }else {
                return middle;
            }
        }
        return -1;
    }

}
