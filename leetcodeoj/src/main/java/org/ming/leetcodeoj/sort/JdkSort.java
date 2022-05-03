package org.ming.leetcodeoj.sort;

import java.util.*;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class JdkSort {
    public static void main(String[] args) {
        int[] array = new int[]{2, 1, 3, 4, 1, 0, -1, 8, 7, 1, -9};
        Arrays.sort(array);
        List<Integer> list = new ArrayList<Integer>() {{
            add(2);
            add(1);
            add(3);
            add(4);
            add(1);
            add(0);
            add(-1);
            add(-9);
        }};
        Object[] objects = list.toArray();
        // 底层也是基于Arrays.sort(array);
        Collections.sort(list);
    }
}
