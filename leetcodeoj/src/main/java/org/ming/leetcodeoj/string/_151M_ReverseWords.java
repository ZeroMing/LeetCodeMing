package org.ming.leetcodeoj.string;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _151M_ReverseWords {

    public static void main(String[] args) {
        String s = "a good   example";
        System.out.println(reverseWords(s));
    }

    /**
     * 151. 翻转字符串里的单词
     * 给你一个字符串 s ，逐个翻转字符串中的所有 单词 。
     * <p>
     * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
     * <p>
     * 请你返回一个翻转 s 中单词顺序并用单个空格相连的字符串。
     * <p>
     * 说明：
     * <p>
     * 输入字符串 s 可以在前面、后面或者单词间包含多余的空格。
     * 翻转后单词间应当仅用一个空格分隔。
     * 翻转后的字符串中不应包含额外的空格。
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "the sky is blue"
     * 输出："blue is sky the"
     * 示例 2：
     * <p>
     * 输入：s = "  hello world  "
     * 输出："world hello"
     * 解释：输入字符串可以在前面或者后面包含多余的空格，但是翻转后的字符不能包括。
     * 示例 3：
     * <p>
     * 输入：s = "a good   example"
     * 输出："example good a"
     * 解释：如果两个单词间有多余的空格，将翻转后单词间的空格减少到只含一个。
     * 示例 4：
     * <p>
     * 输入：s = "  Bob    Loves  Alice   "
     * 输出："Alice Loves Bob"
     * 示例 5：
     * <p>
     * 输入：s = "Alice does not even like bob"
     * 输出："bob like even not does Alice"
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= s.length <= 104
     * s 包含英文大小写字母、数字和空格 ' '
     * s 中 至少存在一个 单词
     * <p>
     * <p>
     * 进阶：
     * <p>
     * 请尝试使用 O(1) 额外空间复杂度的原地解法。
     */
    public static String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        int wordBegin = 0, wordEnd = 0;
        boolean spaceFlag = false, wordFlag = false, sideFlag = false;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == ' ') {
                // 转为字符串，append到sb
                if (wordFlag) {
                    // "abc def  hgt"
                    //  beign = 11,end = 9
                    for (; wordEnd <= wordBegin; wordEnd++) {
                        sb.append(chars[wordEnd]);
                    }
                    wordBegin = 0;
                    wordEnd = 0;
                    wordFlag = false;
                }

                if (!spaceFlag) {
                    spaceFlag = true;
                    sideFlag = i == chars.length - 1 || i == 0;
                }
            } else {
                // 将多个空格转为1个空格串即可，append到sb
                if (spaceFlag) {
                    spaceFlag = false;
                    if (!sideFlag) {
                        sb.append(' ');
                    }
                }

                if (!wordFlag) {
                    wordBegin = i;
                    wordEnd = i;
                    wordFlag = true;
                } else {
                    wordEnd--;
                }
            }
        }
        if (wordFlag) {
            for (; wordEnd <= wordBegin; wordEnd++) {
                sb.append(chars[wordEnd]);
            }
        }
        return sb.toString();
    }
}
