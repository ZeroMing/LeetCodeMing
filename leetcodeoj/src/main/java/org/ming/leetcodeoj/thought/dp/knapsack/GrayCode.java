package org.ming.leetcodeoj.thought.dp.knapsack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 生成格雷码
 * @author: LeoLee
 * @date: 2019/10/18 18:43
 */
public class GrayCode {

    public static void main(String[] args) {
        GrayCode grayCode = new GrayCode();
        String[] recursiveCode = grayCode.getGrayCodeRecursive(2);
        System.out.println(Arrays.asList(recursiveCode));
        List<Integer> list = grayCode.getGrayCodeNoRecursive(4);
        System.out.println(list);
        StringBuffer[] code = grayCode.getBinary(list, 4);
        System.out.println(Arrays.asList(code));
    }

    /**
     * 递归生成格雷码
     * @param number
     * @return
     */
    public String[] getGrayCodeRecursive(int number){
        // 递归求取n个不同物品的所有子集
        int rows = (int)Math.pow(2,number);
        String[] result = new String[rows];
        if(number == 1){
            result[0] = "0";
            result[1] = "1";
            return result;
        }
        // 递归求取 n-1 个不同物品的所有子集
        String[] temp = getGrayCodeRecursive(number-1);
        // 根据格雷码去掉最高位,前一半和后一半二进制数完全一样的对称性
        for(int i = 0;i < temp.length;i++){
            // 前一半格雷码，最高位为 0
            result[i] = "0" + temp[i];
            // 后一半格雷码，最高位为 1
            result[result.length-1-i] = "1" + temp[i];
        }
        return result;
    }


    /**
     * 是使用非递归来实现，运用异或运算，其构造非常巧妙
     * @param number
     * @return
     */
    public List<Integer> getGrayCodeNoRecursive(int number){
        List<Integer> result = new ArrayList<>();
        if(number >= 0){
            // 第一为 000
            result.add(0);
            // 进行异或运算的因子
            int top = 1;
            for(int i = 0;i < number;i++){
                for(int j = result.size()-1;j >= 0;j--){
                    // 符号‘^’是异或运算（使用具体数字的二进制进行运算），即 1^0=1,0^1=1,0^0=0,1^1=0
                    // 相同为 0，不同为 1
                    result.add(result.get(j)^top);
                }
                System.out.println();
                // 左移1位，等于 top * 2
                top <<= 1;
                System.out.println("top = "+top);
            }
        }
        return result;
    }


    /**
     * 把十进制数转换成长度为n的二进制数
     * @param array
     * @param n
     * @return
     */
    public StringBuffer[] getBinary(List<Integer> array,int n){
        StringBuffer[] result = new StringBuffer[array.size()];
        for(int i = 0;i < array.size();i++){
            int temp1 = array.get(i);
            int judge = n;
            // 用于存放 temp1 的 n位二进制数
            char[] tempBinary = new char[n];
            while(judge > 0){
                int temp2 = temp1 % 2;
                // 对照 char 的 unicode编码，把 int 型数字转换为 char型
                tempBinary[judge-1] = (char) (temp2+48);
                temp1 = temp1 / 2;
                judge--;
            }
            result[i] = new StringBuffer(String.valueOf(tempBinary));
        }
        return result;
    }


}
