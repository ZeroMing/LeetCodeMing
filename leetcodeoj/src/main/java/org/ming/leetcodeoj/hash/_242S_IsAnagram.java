package org.ming.leetcodeoj.hash;

/**
 * 有效的字母异位词
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _242S_IsAnagram {
    public static void main(String[] args) {
        String s = "anagram", t = "nagaram";
        System.out.println(isAnagram(s, t));
    }
    
    /**
     * a - z，ASII码 ，相对位置， 26
     *
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram(String s, String t) {
        int[] array = new int[26];
        for (char c : s.toCharArray()) {
            array[c - 'a'] = array[c - 'a'] + 1;
        }
        for (char c : t.toCharArray()) {
            array[c - 'a'] = array[c - 'a'] - 1;
        }
        for (int a : array) {
            if (a > 0) {
                return false;
            }
        }
        return true;
    }
}
