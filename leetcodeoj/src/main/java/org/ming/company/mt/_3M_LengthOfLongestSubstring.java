package org.ming.company.mt;

import java.util.*;

/**
 * https://blog.csdn.net/a1111116/article/details/
 * <p>
 * set也可以判断字符串是否含有重复元素。相比两次循环每个元素都比较更优。
 * 滑动窗口：左指针固定，右指针不断右移。不满足条件时右指针固定，左指针右移动一位
 */
public class _3M_LengthOfLongestSubstring {

    public static void main(String[] args) {
        String str = "abcabcbb";
        System.out.println("暴力求解: " + lengthOfLongestSubstring0(str));
        // System.out.println("滑动窗口list: " + lengthOfLongestSubstring1(str));
        System.out.println("滑动窗口set: " + lengthOfLongestSubstring2(str));
        System.out.println("滑动窗口map: " + lengthOfLongestSubstring3(str));
        System.out.println("数组记录下标方法: " + lengthOfLongestSubstring4(str));
    }


    /**
     * 暴力求解
     * 优化，提前退出
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring0(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int max = 0;
        Set<Character> set = new HashSet<>();
        // 双层for循环拼接不重复子串
        outer:
        for (int i = 0; i < s.length(); i++) {
            // 将首字符放进去
            set.add(s.charAt(i));
            for (int j = i + 1; j < s.length(); j++) {
                // 优化: 已经走到头了，说明就是最长的了，不需要再循环了
                if (j == s.length() - 1 && !set.contains(s.charAt(j))) {
                    // 直接返回
                    set.add(s.charAt(j));
                    max = Math.max(max, set.size());
                    break outer;
                }
                // 包含
                if (set.contains(s.charAt(j))) {
                    break;
                } else {
                    set.add(s.charAt(j));
                }
            }
            // 每次终止的时候，计算最大值
            max = Math.max(max, set.size());
            // 清空
            set.clear();
        }
        return max;
    }


    /**
     * 滑动窗口
     * 基于List，判重需要循环，性能不高
     * 不推荐
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring1(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }
        // 右指针，字符不重复就不断添加
        int right = -1;
        // 最大长度
        int ans = 0;
        List<Character> set = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (i != 0) {
                set.remove((Character) s.charAt(i - 1));
            }
            // 包含重复子串
            // 没超出字符串长度
            while (right + 1 < s.length() && !set.contains(s.charAt(right + 1))) {
                set.add(s.charAt(right + 1));
                right++;
            }

            // 遇到重复元素，记录最大长度
            ans = Math.max(ans, right - i + 1);
        }
        return ans;
    }


    /**
     * 滑动窗口
     * set集合判重
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        // 右指针，字符不重复就不断添加
        int right = -1;
        // 最大长度
        int ans = 0;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (i != 0) {
                set.remove(s.charAt(i - 1));
            }
            // 包含重复子串
            // 没超出字符串长度
            while (right + 1 < s.length() && !set.contains(s.charAt(right + 1))) {
                set.add(s.charAt(right + 1));
                right++;
            }

            // 遇到重复元素，记录最大长度
            ans = Math.max(ans, right - i + 1);
        }
        return ans;
    }


    /**
     * 滑动窗口优化版本
     * 基于Map存储
     * map集合，存放字符和字符最后一次出现的位置
     * 每次出现相同元素就把start移动到 start和end中相同元素出现的 下一位
     * abcabcbb
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int ans = 0, left = 0;
        // 最近一次出现的位置，+1
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            char c = chars[i];
            // 如果出现重复字符
            if (map.containsKey(c)) {
                // 相同字符出现位置出现的下一位，left不能比当前的 left小
                left = Math.max(map.get(c) + 1, left);
            }
            map.put(c, i);
            ans = Math.max(ans, i - left + 1);
        }
        return ans;
    }


    /**
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring4(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int i = 0, ans = 0;
        // 记录ASCII 码字符出现的位置，以字符为下标
        int[] dict = new int[128];
        Arrays.fill(dict, -1);
        int ASCII;
        int repeatValue = -1;
        while (i < s.length()) {
            // char 字符
            ASCII = s.charAt(i);
            // 如果当前值 > repeatValue ,证明当前位置已经赋值过一次了，字符重复
            if (dict[ASCII] > repeatValue) {
                // 重复的值被设置为新的值
                repeatValue = dict[ASCII];
            } else {
                // 最后一次出现的位置
                dict[ASCII] = i;
                ans = Math.max(ans, i - repeatValue);
                if (ans >= s.length() - repeatValue - 1) {
                    return ans;
                }
                i++;
            }
        }
        return ans;
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


    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int end = 0, start = 0; end < n; end++) {
            char alpha = s.charAt(end);
            if (map.containsKey(alpha)) {
                start = Math.max(map.get(alpha), start);
            } else {
                ans = Math.max(ans, end - start + 1);
                map.put(s.charAt(end), end + 1);
            }
        }
        return ans;
    }

}