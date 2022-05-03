package org.ming.leetcodeoj.hash;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _383S_CanConstruct {
    /**
     * 383. 赎金信
     * 为了不在赎金信中暴露字迹，从杂志上搜索各个需要的字母，组成单词来表达意思。
     * <p>
     * 给你一个赎金信 (ransomNote) 字符串和一个杂志(magazine)字符串，判断 ransomNote 能不能由 magazines 里面的字符构成。
     * <p>
     * 如果可以构成，返回 true ；否则返回 false 。
     * <p>
     * magazine 中的每个字符只能在 ransomNote 中使用一次。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：ransomNote = "a", magazine = "b"
     * 输出：false
     * 示例 2：
     * <p>
     * 输入：ransomNote = "aa", magazine = "ab"
     * 输出：false
     * 示例 3：
     * <p>
     * 输入：ransomNote = "aa", magazine = "aab"
     * 输出：true
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= ransomNote.length, magazine.length <= 105
     * ransomNote 和 magazine 由小写英文字母组成
     */
    public static void main(String[] args) {


        System.out.println(canConstruct("aab", "aab"));
    }


    /**
     * 1. 记录次数
     *
     * @param ransomNote 赎金信
     * @param magazine   magazine
     * @return
     */
    public static boolean canConstruct(String ransomNote, String magazine) {
        int[] set = new int[26];
        for (char c : magazine.toCharArray()) {
            set[c - 'a'] = set[c - 'a'] + 1;
        }
        for (char c : ransomNote.toCharArray()) {
            if (set[c - 'a'] == 0) {
                return false;
            }
            set[c - 'a'] = set[c - 'a'] - 1;
        }
        return true;
    }
}
