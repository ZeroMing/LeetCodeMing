package org.interview;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class Ips {
    public static void main(String[] args) {
        String[] ss = new String[]{"192.168.1.100", "192.168.2.100", "192.168.3.100"};
        int mask = 16, step = 8;
        HashSet<Integer> set = new HashSet<>();
        for (String s : ss) {
            String[] ips = s.split("\\.");
            int index = 0, ipIndex = 0, sum = 0;
            while (index + step <= mask) {
                sum += (Integer.parseInt(ips[ipIndex]) & (int)(Math.pow(2,step)-1));
                ipIndex++;
                index += step;
            }
            if (index < mask) {
                ipIndex++;
                sum += (Integer.parseInt(ips[ipIndex]) >> (step - mask + index)) & (int)(Math.pow(2,step)-1);
            }
            set.add(sum);
        }
        System.out.println(set.size());
    }
}
