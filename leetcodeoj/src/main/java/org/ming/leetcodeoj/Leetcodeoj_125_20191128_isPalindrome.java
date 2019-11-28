package org.ming.leetcodeoj;

import java.util.Stack;

/**
 * 验证回文串
 * @author: LeoLee
 * @date: 2019/11/28 17:00
 */
public class Leetcodeoj_125_20191128_isPalindrome {

    public static void main(String[] args) {
        String str = "Amannama";
        Leetcodeoj_125_20191128_isPalindrome leetcodeoj = new Leetcodeoj_125_20191128_isPalindrome();
        boolean palindrome = leetcodeoj.isSimplePalindrome(str);
        System.out.println(palindrome);
    }


    /**
     * 干扰回文串
     * 对撞指针
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        int length = s.length();
        int left = 0,right = length -1;
        while (left < right) {
            if(!isValidStr(chars[left])){
                left ++;
                continue;
            }

            if(!isValidStr(chars[right])){
                right --;
                continue;
            }
            if(chars[left] != chars[right] && !(chars[left]+"").equalsIgnoreCase((chars[right]+""))){
                return false;
            }
            left ++;
            right --;

        }
        return true;
    }


    public boolean isValidStr(char c){
        return  (c >= 'a' & c <= 'z' ) || (c >='A' && c <= 'Z') || (c >= 0 & c <= 9);
    }


    /**
     * 水仙花
     * Amannama
     * @param s
     * @return
     */
    public boolean isSimplePalindrome(String s) {

        char[] chars = s.toCharArray();
        int length = chars.length;
        char[] reverseChars = new char[chars.length];
        for(int i =0;i <chars.length;i++){
            reverseChars[length-i-1] = chars[i];
        }

        for(int i =0;i <chars.length;i++){
            if(!isValidStr(chars[i]) || !isValidStr(reverseChars[i])){
                continue;
            }
            if(!(chars[i] == reverseChars[i] ||
                    (chars[i]+"").equalsIgnoreCase(reverseChars[i]+""))){
                return false;
            }
        }
        return true;
    }



}
