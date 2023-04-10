package org.ming.calculater;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liming53
 * @date 2023/1/28
 * @company 58房产 · 交易技术部
 * @since 1.0
 */
public class Calculator {

    /*
    我们最终要实现的计算器功能如下：
    1、输入一个字符串，可以包含 + - * /、数字、括号以及空格，你的算法返回运算结果。
    2、要符合运算法则，括号的优先级最高，先乘除后加减。
    3、除号是整数除法，无论正负都向 0 取整（5/2=2，-5/2=-2）。
    4、可以假定输入的算式一定合法，且计算过程不会出现整型溢出，不会出现除数为 0 的意外情况。

    关键在于层层拆解问题，化整为零，逐个击破
     */



    // 字符串 转 整数
    public int convertStrToInt(String str){
        int n = 0;
        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            n = 10 * n + (c - '0');
        }
        return n;
    }


    //



    public static void main(String[] args) {
        long cal = new Calculator().cal("7*(1+2)+3");
        // System.out.println(cal);
        // System.out.println(new Calculator().cal("(2-5)+2*3"));
        System.out.println(new Calculator().cal("(5-3)+(2*3)/2-1"));
    }

    public long cal(String sourceStr) {
        if (sourceStr == null || "".equals(sourceStr.trim()) || " ".equals(sourceStr.trim())) {
            return -1;
        }
        char[] chars = sourceStr.toCharArray();
        List<Character> characters = new ArrayList<>(chars.length);
        for (char c : chars) {
            characters.add(Character.valueOf(c));
        }
        List<String> stringList = convertMidToEnd(characters);
        long ans = cal(stringList);
        return ans;
    }

    /**
     * 中缀 转 后缀 计算
     *
     * @param characters
     * @return
     */
    private List<String> convertMidToEnd(List<Character> characters) {
        // 7  * （1+2）+ 3  = 24
        // []  [7]
        // [*] [7]
        // [*,(] [7]
        // [*,(] [7,1]
        // [*,(] [7,1]
        // [*,(,+] [7,1]
        // [*,(,+] [7,1,2]
        // [*,(,+] [7,1,2]
        // [*] [7,1,2,+]
        // [*] [7,1,2,+,+,3]
        // [] [7,1,2,+,3,*]

        // [7, 1, 2, +, *, 3, +]
        // 24

        // 遇到符号时，符号栈为空，栈顶符号为左括号、栈顶符号优先级 < 当前符号优先级，直接入栈；否则将符号压入链表；遇到其他情况时，停止；
        // 遇到右括号时，将符号栈的符号从栈顶开始压入链表,直到遇见左括号时停止，并且将左括号移除
        // 遇到数字时，往后进行判断是否为多位数进行拼接；
        // 最后将符号栈里所有符号 压入 链表

        int i = 0;
        ArrayDeque<String> stack = new ArrayDeque(characters.size());
        List<String> list = new ArrayList<>(characters.size());
        while (i < characters.size()) {
            Character character = characters.get(0);
            // (
            if (character.charValue() == '(') {
                stack.push(character.toString());
                continue;
            }
            // +-*/
            if (character.charValue() == '+' || character.charValue() == '-' || character.charValue() == '*' || character.charValue() == '/') {
                if (stack.isEmpty() || stack.peek().equals("(") || judge(stack.peek()) <= judge(character.toString())) {
                    stack.push(character.toString());
                    continue;
                } else {
                    while (!stack.isEmpty() && !stack.peek().equals("(") && judge(stack.peek()) > judge(character.toString())) {
                        list.add(stack.pop());
                    }
                    stack.push(character.toString());
                }
            }

            // 右括号
            if (character.charValue() == ')') {
                // 直到遇到 左括号 或者 栈变为空
                while (!stack.peek().equals("(")) {
                    list.add(stack.pop());
                }
                // 移除 左括号
                stack.pop();
            }

            // 数字,拼接多位数字
            if (Character.isDigit(character)) {
                String ss = "";
                while (i < characters.size()) {
                    if (characters.get(i) != '+' && characters.get(i) != '-' && characters.get(i) != '*' && characters.get(i) != '/') {
                        ss = ss + characters.get(i);
                        i++;
                    } else {
                        break;
                    }
                }
                list.add(ss);
            }
        }

        // 所有的符号栈压入列表
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }
        System.out.println(list);
        return list;
    }

    /**
     * @param param
     * @return
     */
    public long cal(List<String> param) {
        ArrayDeque<Integer> stack = new ArrayDeque<>(param.size());
        for (String c : param) {
            // 操作符
            if (c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/")) {
                int cal = cal(c, Integer.valueOf(stack.pop()), Integer.valueOf(stack.pop()));
                stack.push(cal);
            }else{
                stack.push(Integer.parseInt(c));
            }
        }

        return stack.peek();
    }

    private int cal(String c, Integer p1, Integer p2) {
        switch (c) {
            case "+":
                return p1 + p2;
            case "-":
                return p2 - p1;
            case "*":
                return p1 * p2;
            case "/":
                return p2 / p1;
        }
        return 0;
    }

    public int judge(String c) {
        if (c.equals("*") || c.equals("/")) {
            return 1;
        }
        if (c.equals("+") || c.equals("-")) {
            return 0;
        }
        return -1;
    }


}
