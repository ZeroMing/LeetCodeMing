package org.ming.mt;

import java.util.*;

/**
 * https://blog.csdn.net/a1111116/article/details/
 *
 * set也可以判断字符串是否含有重复元素。相比两次循环每个元素都比较更优。
 * 滑动窗口：左指针固定，右指针不断右移。不满足条件时右指针固定，左指针右移动一位
 */
public class _3_LengthOfLongestSubstring {

    public static void main(String[] args) {
        String str = " ";
//        System.out.println(lengthOfLongestSubstring1(str));
//        System.out.println(lengthOfLongestSubstring2(str));
        // System.out.println(lengthOfLongestSubstring3(str));
        System.out.println(lengthOfLongestSubstring4(str));
//        System.out.println(lengthOfLongestSubstring5(str));
    }

    public static int lengthOfLongestSubstring0(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int max = 0;
        // 双层for循环拼接不重复子串

        // 判断不重复子串的最大值

        return max;
    }


    public static int lengthOfLongestSubstring1(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int left = 0, right = 0, max = 0;
        char[] chars = str.toCharArray();
        List<Character> list = new ArrayList<>();
        // abcddddda
        while (left < chars.length && right < chars.length) {
            // 已经包含 该字符,不能删除，删除就没了
            if (list.contains((Character) chars[right])) {
                // 找出第一次出现字符位置
                list.remove((Character) chars[right]);
                left++;
            } else {
                list.add(chars[right]);
                right++;
                max = Math.max(max, right - left);
            }
            // System.out.println("list : " + list + " right:" + right + " left:" + left);
        }
        return max;
    }


    /**
     * set集合判重
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int left = 0, right = 0, max = 0,len = s.length();
        Set<Character> set = new HashSet<>();
        while (left < len && right < len) {
            if (!set.contains(s.charAt(right))) {
                set.add(s.charAt(right++));
                max = Math.max(max, right - left);
            } else {
                set.remove(s.charAt(left++));
            }
        }
        return max;
    }


    /**
     * Map映射
     *
     * @param str
     * @return
     */
    public static int lengthOfLongestSubstring3(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int left = 0, right = 0, max = 0;
        char[] chars = str.toCharArray();
        // 最近一次出现的位置
        Map<Character, Integer> map = new HashMap<>();
        //
        while (right < chars.length) {
            // 如果包含key
            if (map.containsKey(chars[right])) {
                left = Math.max(map.get(chars[right]), left);
            } else {
                max = Math.max(max, right - left);
                map.put(chars[right], right++);
            }
        }
        return max;
    }


    /**
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring4(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int left = 0, i = 0, max = 0;
        int[] array = new int[128];
        Arrays.fill(array,-1);
        //
        while (i < s.length()) {
            // char 字符
            int ascii = s.charAt(i);
            left = Math.max(left, array[ascii] + 1);
            max = Math.max(max, i - left + 1);
            // 当前下标赋值到数组的相应位置，比如：a的值为10
            array[ascii] = i;
            i++;
        }
        return max;
    }


    /**
     * 滑动窗口解法
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring5(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        // 无重复的最长子串的长度
        int res = 0;
        // 滑动窗口
        Map<Character, Integer> window = new HashMap<>();
        int left = 0;
        int right = 0;
        while (right < s.length()) {
            char newChar = s.charAt(right);
            // 扩大窗口
            right++;
            // 处理窗口中新加入的元素 newChar
            window.put(newChar, window.getOrDefault(newChar, 0) + 1);
            // 如果窗口中该元素重复，则缩小窗口直至不重复
            while (window.get(newChar) > 1) {
                //
                char removeChar = s.charAt(left);
                // 缩小窗口
                left++;
                // 处理下窗口被移除的元素 removeChar
                window.put(removeChar, window.get(removeChar) - 1);
            }
            System.out.println("滑动窗口 ： " + window);
            // 我们的窗口是左开右闭的，所以这里窗口的长度不要 +1 !!!
            res = Math.max(res, right - left);
        }
        return res;
    }

    /*

    滑动窗口万能模板
    class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s.length()==0) return 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max = 0;
        int left = 0;
        for(int i = 0; i < s.length(); i ++){
            if(map.containsKey(s.charAt(i))){
                // + 1
                left = Math.max(left,map.get(s.charAt(i)) + 1);
            }
            // 把自己放进去
            map.put(s.charAt(i),i);
            // 计算最大值
            max = Math.max(max,i-left+1);
        }
        return max;

    }
}

     */

}