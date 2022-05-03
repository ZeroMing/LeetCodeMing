package org.interview;

/**
 * 数字转中文
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class NumberToChinese {
    public static void main(String[] args) {
        System.out.println(convert(90009090));
    }

    static final String[] tenHash = new String[]{"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖", "拾"};

    /**
     * 0 ~ 1亿以内的
     * 11 100 001
     * 一千一百一十万零一
     *
     * @param num
     * @return
     */
    public static String convert(Integer num) {
        StringBuilder sb = new StringBuilder("");
        int a = 0;
        boolean[] used = new boolean[4];
        while (num >= 10) {
            if (num > 10000) {
                a = num / 10000;
                num = num % 10000;
                String convert = convert0(a);
                sb.append(convert).append("万");
                used[0] = true;
            } else if (num > 1000) {
                a = num / 1000;
                num = num % 1000;
                if (!used[0]) {
                    sb.append("零").append(tenHash[a]).append("千");
                } else {
                    sb.append(tenHash[a]).append("千");
                }
                used[1] = true;
            } else if (num > 100) {
                a = num / 100;
                num = num % 100;
                if (!used[1]) {
                    sb.append("零").append(tenHash[a]).append("百");
                } else {
                    sb.append(tenHash[a]).append("百");
                }
                used[2] = true;
            } else if (num > 10) {
                a = num / 10;
                num = num % 10;
                if (!used[2]) {
                    sb.append("零").append(tenHash[a]).append("拾");
                } else {
                    sb.append(tenHash[a]).append("拾");
                }
                used[3] = true;
            }
        }

        if (num > 0) {
            if (!used[3]) {
                sb.append("零").append(tenHash[a]);
            } else {
                sb.append(tenHash[a]);
            }
        }
        return sb.toString();
    }

    public static String convert0(Integer num) {
        StringBuilder sb = new StringBuilder("");
        int a = 0;
        boolean[] used = new boolean[4];
        while (num >= 10) {
            if (num > 1000) {
                a = num / 1000;
                num = num % 1000;
                sb.append(tenHash[a]).append("千");
                used[0] = true;
            } else if (num > 100) {
                a = num / 100;
                num = num % 100;
                sb.append(tenHash[a]).append("百");
                used[1] = true;
            } else if (num > 10) {
                a = num / 10;
                num = num % 10;
                if (!used[1]) {
                    sb.append("零").append(tenHash[a]).append("拾");
                } else {
                    sb.append(tenHash[a]).append("拾");
                }
                used[2] = true;
            }
        }

        if (num > 0) {
            if (!used[2]) {
                sb.append("零").append(tenHash[num]);
            } else {
                sb.append(tenHash[num]);
            }
        }
        return sb.toString();
    }

}
