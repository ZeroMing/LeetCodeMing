package org.ming.leetcodeoj.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 * 回溯法
 * 递归与回溯是相辅相成的
 *
 * @description: Letter Case Permutation
 * @author: 李明
 * @company: 朴新教育
 * @version:
 * @date: 2019/7/17 15:35
 */
public class Leetcodeoj_784_20190717 {

    /**
     * Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.
     * Return a list of all possible strings we could create.
     *
     * Examples:
     * Input: S = "a1b2"
     * Output: ["a1b2", "a1B2", "A1b2", "A1B2"]
     *
     * Input: S = "3z4"
     * Output: ["3z4", "3Z4"]
     *
     * Input: S = "12345"
     * Output: ["12345"]
     * Note:
     *
     * S will be a string with length between 1 and 12.
     * S will consist only of letters or digits.
     */


    /**
     *
     * @param source
     * @return
     */
    public List<String> letterCasePermutation(String source) {

        // "1234";
        List<String> result = new ArrayList<>();
        dfs(0,new StringBuilder(source),result);
        return result;
    }

    /**
     * 递归实现回溯
     * 回溯法是对解空间的深度优先搜索，在一般情况下使用递归函数来实现回溯法比较简单，其中i为搜索的深度，
     * @param index
     * @param source
     * @param result
     */
    private void dfs(int index,StringBuilder source,List<String> result){
        if(index == source.length()){
            result.add(source.toString());
            return ;
        }

        char tempStr = source.charAt(index);
        if(Character.isLetter(tempStr)){
            source.setCharAt(index,Character.toLowerCase(tempStr));
            dfs(index +1,source,result);
            source.setCharAt(index,Character.toUpperCase(tempStr));
            dfs(index + 1,source,result);
        }else{
            dfs(index + 1,source,result);
        }
    }


    /**
     * 非递归实现
     * @param source
     */
    private LinkedList<String> letterCasePermutation2(String source){
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add(source);
        // "aabc"
        int index = 0 ;
        while (index < source.length()) {
            char temp = source.charAt(index);
            if(Character.isLetter(temp)) {
                int j = linkedList.size();
                while (j > 0) {
                    String poll = linkedList.poll();
                    String left = poll.substring(0, index);
                    String right = poll.substring(index + 1);
                    linkedList.add(left + Character.toLowerCase(temp) + right);
                    linkedList.add(left + Character.toUpperCase(temp) + right);
                    j--;
                }
            }
            index ++;
        }
        return linkedList;
    }



    public static void main(String[] args) {
        String source = "aA1";
        System.out.println(new Leetcodeoj_784_20190717().letterCasePermutation(source));
        System.out.println(new Leetcodeoj_784_20190717().letterCasePermutation2(source));
    }
}
