package org.ming.leetcodeoj.backtracking;

import java.util.List;

/**
 * @description:  Binary Watch
 * @author: 李明
 * @company: 朴新教育
 * @version:
 * @date: 2019/7/17 17:44
 */
public class Leetcodeoj_401_20190717 {
    /**
     * 二进制手表顶部有4个LED，代表小时（0-11），底部的6个LED代表分钟（0-59）。
     *
     * 每个LED代表零或一，右侧的最低有效位。
     * [0] [0] [1] [1]
     * [0] [1] [1] [0] [0] [1]
     * 32  16  8   4   2   1
     * 例如，上面的二进制表看起来是“3:25”。
     *
     * 给定非负整数n表示当前打开的LED数量，返回手表可能代表的所有可能时间。
     * 例：
     * 输入：n = 1
     * 返回：[“1:00”，“2:00”，“4:00”，“8:00”，“0:01”，“0:02”，“0:04”， “0:08”，“0:16”，“0:32”]
     * 注意：
     * 输出顺序无关紧要。
     * 小时不得​​包含前导零，例如“01:00”无效，应为“1:00”。
     * 分钟必须由两个数字组成，并且可以包含前导零，例如“10：2”无效，它应该是“10:02”。
     */

    public List<String> readBinaryWatch(int num) {
        int[] hours = new int[]{8,4,2,1};
        int[] minutes = new int[]{32,16,8,4,2,1};

        /**
         * hours 中取 i 个
         *
         * minutes 中取 num -i
         */

        if(num <= hours.length){

        }else{

        }



        for(int i=0;i < num ; i ++){

            for(int j = num - i -1; ){

            }

        }














        return null;
    }


}
