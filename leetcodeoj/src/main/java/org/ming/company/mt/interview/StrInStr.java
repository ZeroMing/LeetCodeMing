package org.ming.company.mt.interview;

/**
 * [美团]
 * 给定两个字符串s1和s2
 * 返回在s1中有多少个子串等于s2
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class StrInStr {

    public static void main(String[] args) {

    }

    /**
     * 来自美团
     * 给定两个字符串s1和s2
     * 返回在s1中有多少个子串等于s2
     */
    public static int countStrInStr(String s1, String s2) {
        // 排除特殊情况
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0 || s1.length() < s2.length()) {
            return 0;
        }
        // 1212112121
        // 121
        return -1;
    }

}
