package org.ming.leetcodeoj.thought.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 131. 分割回文串
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _131M_分割回文串 {

    public static void main(String[] args) {
        System.out.println(partition("aab"));
    }


    /**
     * @param s
     * @return
     */
    public static List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        int len = s.length();
        dfs(0, len, ans, new ArrayList<>(), s);
        return ans;
    }

    /**
     * @param startIndex
     * @param n
     * @param ans
     * @param path
     * @param s
     */
    private static void dfs(int startIndex, int n, List<List<String>> ans, List<String> path, String s) {
        if (startIndex >= n) {
            ans.add(new ArrayList(path));
            return;
        }
        for (int i = startIndex; i < n; i++) {
            // 回文检查
            if (isPalindrome(s, startIndex, i)) {
                String s1 = s.substring(startIndex, i + 1);
                path.add(s1);
            } else {
                continue;
            }
            // 寻找i+1为起始位置的子串
            dfs(i + 1, n, ans, path, s);
            path.remove(path.size() - 1);
        }
    }

    public static boolean isPalindrome(String s, int startIndex, int endIndex) {
        char[] chars = s.toCharArray();
        int left = startIndex, right = endIndex;
        while (left <= right) {
            if (chars[left] == chars[right]) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }
}
