package org.interview;

/**
 * 大数相加、字符串相加
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _415S_AddStrings {
    public static void main(String[] args) {
        System.out.println(addStrings("11","123"));
    }


    public static String addStrings(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();
        int i1 = len1 -1,i2 = len2 -1;
        // 遍历
        int sum = -1;
        int lastAdd = 0,nowVal = 0;
        StringBuilder sb = new StringBuilder("");

        while(i1 >= 0 && i2 >= 0){
            sum = (num1.charAt(i1) -'0') + (num2.charAt(i2) - '0') + lastAdd;
            if(sum >= 10){
                lastAdd = 1;
                nowVal = sum % 10;
                sb.insert(0,nowVal);
            }else{
                lastAdd = 0;
                sb.insert(0,sum);
            }
            i1--;
            i2--;
        }

        while(i1 >= 0){
            sum = (num1.charAt(i1) -'0') + lastAdd;
            if(sum >= 10){
                lastAdd = 1;
                nowVal = sum % 10;
                sb.insert(0,nowVal);
            }else{
                lastAdd = 0;
                sb.insert(0,sum);
            }
            i1--;
        }

        while(i2 >= 0){
            sum = (num2.charAt(i2) -'0') + lastAdd;
            if(sum >= 10){
                lastAdd = 1;
                nowVal = sum % 10;
                sb.insert(0,nowVal);
            }else{
                lastAdd = 0;
                sb.insert(0,sum);
            }
            i2--;
        }
        return sb.toString();
    }


}
