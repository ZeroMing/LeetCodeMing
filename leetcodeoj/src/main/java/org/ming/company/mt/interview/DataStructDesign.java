package org.ming.company.mt.interview;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class DataStructDesign {

    public static void main(String[] args) {

    }

    /**
     * 给定一个数组 arr，长度为 N，做出一个结构，可以高效的做如下的查询
     * 1) int querySum(L,R) : 查询 arr[L...R]上的累加和
     * 2) int queryAim(L,R) : 查询arr[L...R]上的目标值，目标值定义如下：
     * 假设arr[L...R]上的值为[a,b,c,d]，a+b+c+d = s
     * 目标值为 : (s-a)^2 + (s-b)^2 + (s-c)^2 + (s-d)^2
     * 3) int queryMax(L,R) : 查询arr[L...R]上的最大值
     * 要求：
     * 1) 初始化该结构的时间复杂度不能超过 O(N*logN)
     * 2) 三个查询的时间复杂度不能超过 O(logN)
     * 3) 查询时，认为arr的下标从1开始，比如 :
     * arr = [ 1, 1, 2, 3 ];
     * querySum(1, 3) -> 4
     * queryAim(2, 4) -> 50
     * queryMax(1, 4) -> 3
     */

    public static void dataStructDesign() {

    }
}
