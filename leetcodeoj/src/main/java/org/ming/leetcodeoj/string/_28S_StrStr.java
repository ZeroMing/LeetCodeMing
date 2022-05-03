package org.ming.leetcodeoj.string;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _28S_StrStr {
    public static void main(String[] args) {
        String haystack = "abeababeabf", needle = "abeabf";
        System.out.println(strStr(haystack, needle));
        System.out.println(strStr2(haystack, needle));
        System.out.println(strStr3(haystack, needle));

    }

    /**
     * @param haystack 匹配串
     * @param needle   模式串
     * @return
     */
    public static int strStr(String haystack, String needle) {
        // KMP算法：如果已经匹配的字符串包含相同的前缀和后缀，遇到下一个不匹配的位置时，
        // 指向needle的指针跳转到前缀的后一个位置，还是不匹配的话，
        // 再往前跳转后继续比较；先构造一个next数组来记录needle指针跳转的位置
        int hl = haystack.length(), nl = needle.length();
        if (nl == 0) {
            return 0;
        }
        // 先构造next数组，next数组中的元素表示当前两个元素不匹配时，needle指针要跳转的位置
        // haystack: [a, b, e, a, b, a, b, e, a, b, f]
        // needle:   [a, b, e, a, b, f]
        // next:     [0, 0, 0, 1, 2, 0]
        int[] next = new int[nl];
        char[] nChars = needle.toCharArray();
        char[] hChars = haystack.toCharArray();
        // 前缀表要统一减一的操作仅仅是其中的一种实现，我们这里选择j初始化为-1
        int bj = -1;
        // 初始化为 -1
        next[0] = -1;
        // j 指向前缀起始位置，i 指向后缀起始位置
        // 注意i从1开始
        for (int fi = 1; fi < nl; fi++) {
            // 如果 s[i] 与 s[j+1]不相同，也就是遇到 前后缀末尾不相同的情况，就要向前回退
            while (bj >= 0 && nChars[fi] != nChars[bj + 1]) {
                // 一直和前一位置的值比较，直到遇到相等的字符或者j=0；j通过next[j]来回退
                bj = next[bj];
            }
            // 如果s[i] 与 s[j + 1] 相同，那么就同时向后移动i 和j 说明找到了相同的前后缀，
            if (nChars[fi] == nChars[bj + 1]) {
                bj++;
            }
            // 同时还要将j（前缀的长度）赋给next[i], 因为next[i]要记录相同前后缀的长度
            next[fi] = bj;
        }
        // 定义两个下标 j 指向模式串起始位置，i 指向文本串起始位置,在文本串s里 找是否出现过模式串t
        // 那么j初始值依然为-1，为什么呢？ 依然因为next数组里记录的起始位置为-1。
        int nj = -1;
        for (int hi = 0; hi < hl; hi++) {
            while (nj >= 0 && hChars[hi] != nChars[nj + 1]) {
                nj = next[nj];
            }
            // 匹配不成功，needle指针j回退并继续比较
            if (hChars[hi] == nChars[nj + 1]) {
                nj++;
            }
            if (nj == (nl - 1)) {
                return hi - nl + 1;
            }
        }
        return -1;
    }


    /**
     * BF算法 Brute Force
     *
     * @param haystack
     * @param needle
     * @return
     */
    public static int strStr2(String haystack, String needle) {
        int hl = haystack.length();
        int nl = needle.length();
        if (hl < nl) {
            return -1;
        }
        if (nl <= 0) {
            return 0;
        }
        // abcdbcdeabcdef
        // abcded
        for (int i = 0; i < hl - nl + 1; i++) {
            int j;
            for (j = 0; j < nl; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
            }
            if (j == nl) {
                return i;
            }
        }
        return -1;
    }


    /**
     * BM算法 Boyer-Moore
     * 1. 坏字符原则
     *
     * @param haystack
     * @param needle
     * @return
     */
    public static int strStr3(String haystack, String needle) {
        char[] hay = haystack.toCharArray();
        char[] need = needle.toCharArray();
        int hl = haystack.length();
        int nl = need.length;
        return bm(hay, hl, need, nl);
    }


    // 用来求坏字符情况下移动位数
    private static void badChar(char[] b, int m, int[] bc) {
        //初始化
        for (int i = 0; i < 256; ++i) {
            bc[i] = -1;
        }
        //m 代表模式串的长度，如果有两个 a,则后面那个会覆盖前面那个
        for (int i = 0; i < m; ++i) {
            int ascii = (int) b[i];
            bc[ascii] = i;//下标
        }
    }

    //用来求好后缀条件下的移动位数
    private static void goodSuffix(char[] b, int m, int[] suffix, boolean[] prefix) {
        //初始化
        for (int i = 0; i < m; ++i) {
            suffix[i] = -1;
            prefix[i] = false;
        }
        for (int i = 0; i < m - 1; ++i) {
            int j = i;
            int k = 0;
            while (j >= 0 && b[j] == b[m - 1 - k]) {
                --j;
                ++k;
                suffix[k] = j + 1;
            }
            if (j == -1) {
                prefix[k] = true;
            }
        }
    }

    public static int bm(char[] a, int n, char[] b, int m) {
        // 创建一个数组用来保存最右边字符的下标
        int[] bc = new int[256];
        badChar(b, m, bc);
        // 用来保存各种长度好后缀的最右位置的数组
        int[] suffixIndex = new int[m];
        // 判断是否是头部，如果是头部则true
        boolean[] isPre = new boolean[m];
        goodSuffix(b, m, suffixIndex, isPre);
        // 第一个匹配字符
        int i = 0;
        //注意结束条件
        while (i <= n - m) {
            int j;
            // 从后往前匹配，匹配失败，找到坏字符
            for (j = m - 1; j >= 0; --j) {
                if (a[i + j] != b[j]) {
                    break;
                }
            }
            // 模式串遍历完毕，匹配成功
            if (j < 0) {
                return i;
            }
            //下面为匹配失败时，如何处理
            //求出坏字符规则下移动的位数，就是我们坏字符下标减最右边的下标
            int x = j - bc[(int) a[i + j]];
            int y = 0;
            // 好后缀情况，求出好后缀情况下的移动位数,如果不含有好后缀的话，则按照坏字符来
            if (y < m - 1 && m - 1 - j > 0) {
                y = move(j, m, suffixIndex, isPre);
            }
            //移动
            i = i + Math.max(x, y);

        }
        return -1;
    }

    // j代表坏字符的下标
    private static int move(int j, int m, int[] suffix_index, boolean[] ispre) {
        // 好后缀长度
        int k = m - 1 - j;
        //如果含有长度为 k 的好后缀，返回移动位数，
        if (suffix_index[k] != -1) {
            return j - suffix_index[k] + 1;
        }
        //找头部为好后缀子串的最大长度，从长度最大的子串开始
        for (int r = j + 2; r <= m - 1; ++r) {
            //如果是头部
            if (ispre[m - r] == true) {
                return r;
            }
        }
        //如果没有发现好后缀匹配的串，或者头部为好后缀子串，则移动到 m 位，也就是匹配串的长度
        return m;
    }


}
