package org.ming.beautifulCode;

/**
 *
 * @Description:
 * @Author: LeoLee
 * @Date: 2019年10月15 23时10分
 */
public class DianTi {


    public static int compute(int[] persons){
        int T = 0 , len = persons.length;
        int N1 = 0, N2 = persons[1], N3 = 0;
        int floor =  1 ;
        // 计算电梯在1层停的时候的层数
        for (int i=2;i<= len;i++){
            T += persons[i] * (i-1);
            N3 += persons[i];
        }

        for(int i = 2;i <= len; i++){
            // 说明最优解还没有出现
            if(N1 + N2 <= N3){
                T += N1 + N2 - N3;
                N1 += N2;
                N2 = persons[i];
                N3 -= persons[i];
                floor = i;
            }else {
                break;
            }

        }

        return floor;
    }

}
