package org.ming.leetcodeoj.backtracking;

import org.omg.CORBA.INTERNAL;

import java.util.ArrayList;
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

    public static void main(String[] args) {
        Leetcodeoj_401_20190717 leetcodeoj_401_20190717 = new Leetcodeoj_401_20190717();


//        List<String> strings1 = leetcodeoj_401_20190717.readBinaryWatch1(8);
//        System.out.println("readBinaryWatch1: "+strings1);

        System.out.println("------------------------------------");

        List<String> strings2 = leetcodeoj_401_20190717.readBinaryWatch2(8);
        System.out.println("readBinaryWatch2: "+strings2);

        System.out.println("------------------------------------");

//        List<String> strings3 = leetcodeoj_401_20190717.readBinaryWatch3(8);
//        System.out.println("readBinaryWatch3: "+strings3);

    }

    /**
     * 基本还是一道DFS的题目，
     * 分别在小时和分钟上做DFS，给定几个灯亮，然后把这些亮的灯枚举分给小时和分钟．
     * 需要注意的是<剪枝>，即小时必须小于１２，分钟小于６０．然后将小时和分钟组合即可．
     * 还有一个需要注意的是如果分钟只有１位数，还要补０．
     * @param num
     * @return
     */
    public List<String> readBinaryWatch3(int num) {
        List<String> results = new ArrayList<>();
        if(num == 0){
            results.add("0:00");
            return results;
        }
        if(num > 10){
            return results;
        }

        for(int i = 0; i < 4;i++){
            List<Integer> hourVals = new ArrayList<>();
            List<Integer> minuteVals = new ArrayList<>();
            //
            dfs(4,i,0,0,hourVals);
            dfs(6,num -i,0,0,minuteVals);

            for(Integer hour:hourVals){
                for(Integer minute:minuteVals){
                    String result = hour + ":" + (minute < 10 ? "0":"") + minute;
                    results.add(result);
                }
            }
        }
        return results;
    }

    public  void dfs(int len,int index,int currentIndex,int val,List<Integer> vals){
        if(index == 0 && len == 4 && val < 12){
            vals.add(val);
        }
        if(index == 0 && len == 6 && val < 60){
            vals.add(val);
        }
        if(currentIndex == len || index == 0){
            return;
        }
        dfs(len,index,currentIndex+1,val,vals);
        val += Math.pow(2,currentIndex);
        index --;
        currentIndex ++;
        dfs(len,index,currentIndex,val,vals);
    }


    /**
     * 将数字放在一个数组中，进行处理
     * @param num
     * @return
     */
    public List<String> readBinaryWatch2(int num) {
        List<String> results = new ArrayList<>();
        if(num == 0){
            results.add("0:00");
            return results;
        }

        if(num > 10){
            return results;
        }
        // 在一个数组中
        int[] temp = {8,4,2,1,32,16,8,4,2,1};
        boolean[] index = new boolean[10];
        helperFun(results, temp, index, num, 0);
        return results;
    }

    /**
     * 递归
     * @param list
     * @param temp
     * @param index
     * @param num
     * @param start
     */
    private void helperFun(List<String> list, int[] temp, boolean[] index, int num, int start) {
        // 判断边界条件
        if(num == 0){
            int hour = 0;
            int minute = 0;
            for(int k =0;k < 10; k ++){
                // 标志位 为1
                if(index[k] && k <= 3){
                    hour += temp[k];
                }
                // 标志位 为1
                if(index[k] && k > 3){
                    minute += temp[k];
                }
            }
            // 剪枝
            if(hour > 12 || minute >= 60){
                return;
            }else{
                String answer = "";
                if(minute < 10){
                    answer = hour + ":0" + minute;
                }else{
                    answer = hour + ":" + minute;
                }
                list.add(answer);
                return;
            }
        }

        for(int i = start; i < temp.length;i++){
            // 依次去设置某一位为 1,直到为1的个数超过 num
            index[i] = true;
            helperFun(list,temp,index,num-1,i+1);
            // 还原，回溯
            index[i] = false;
        }

    }




    /**
     *
     * BitsCount 方案
     *
     * 代表小时的有四个灯，代表分钟的有六个灯，传入的参数num就是小时加分钟所代表的亮灯数之和，所以只要小时加分钟的亮灯数等于num，就代表可能的一种时间。可以联想到使用位操作，亮就表示1，
     * 不亮就表示0，因为只要计算1的位数等于num即可。
     * 使用两层循环，外层循环是小时，从0到11，内层循环是分钟，从0到59，如果小时加分钟的二进制1位计数之和等于num，就将其添加进list中。
     * @param num
     * @return
     */
    public List<String> readBinaryWatch1(int num) {
        List<String> results = new ArrayList<>();
        for(int i =0;i < 12; i++){
            for(int j=0;j < 60;j++ ){
                // 数字包含的bit为1的个数
                if(Integer.bitCount(i) + Integer.bitCount(j) == num){
                    String result = "";
                    if(j < 10){
                        result = i + ":0" + j;
                    }else{
                        result = i + ":" + j;
                    }
                    results.add(result);
                }
            }
        }
        return results;
    }

}
