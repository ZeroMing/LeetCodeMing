package org.ming.leetcodeoj.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Min Stack - 最小栈
 * @author: LeoLee
 * @date: 2019年09月08 15时48分
 */
public class Leetcodeoj_155_20190908_stack_easy {
    /**
     * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
     * push(x) -- Push element x onto stack.
     * pop() -- Removes the element on top of the stack.
     * top() -- Get the top element.
     * getMin() -- Retrieve the minimum element in the stack.
     * Example:
     * MinStack minStack = new MinStack();
     * minStack.push(-2);
     * minStack.push(0);
     * minStack.push(-3);
     * minStack.getMin();   --> Returns -3.
     * minStack.pop();
     * minStack.top();      --> Returns 0.
     * minStack.getMin();   --> Returns -2.
     */

    public static void main(String[] args) throws Exception {
        MinStack1 minStack = new MinStack1();
        minStack.push(2);
        minStack.push(0);
        minStack.push(3);
        minStack.push(0);
        // minStack.push(11);
        // -3.
        System.out.println("栈最小值:" + minStack.getMin());
        minStack.pop();
        System.out.println("栈最小值:" + minStack.getMin());
        minStack.pop();
        System.out.println("栈最小值:" + minStack.getMin());
        minStack.pop();
        System.out.println("栈最小值:" + minStack.getMin());
    }


    /**
     * 优先级队列 + 栈
     */
    static class MinStack1 {
        PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>();
        Stack<Integer> stack = new Stack<Integer>();

        public MinStack1() {}

        public void push(int x) {
            pQueue.add(x);
            stack.push(x);
        }

        public void pop() {
            int trmp = stack.pop();
            pQueue.remove(trmp);
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return pQueue.peek();
        }
    }

    /**
     * 双栈实现。注意：如果自己实现，需要实现数组扩容。
     */
    static class MinStack2 {
        private Stack<Integer> s1 = new Stack<>();
        private Stack<Integer> s2 = new Stack<>();

        public MinStack2() {}

        public void push(int x) {
            s1.push(x);
            // 为空，或者栈顶值大于或等于新值
            if (s2.isEmpty() || s2.peek() >= x) {
                s2.push(x);
            }
        }

        public void pop() {
            int x = s1.pop();
            if (s2.peek() == x) {
                s2.pop();
            }
        }

        public int top() {
            return s1.peek();
        }

        public int getMin() {
            return s2.peek();
        }
    }

    /**
     * 整型数组和ArrayList作为栈
     */
    static class MinStack3{
        private List<int[]> stack ;
        private int min ;

        public MinStack3() {
            stack = new ArrayList<int[]>();
        }

        public void push(int x){
            int[] array = new int[2];
            array[0]=x;
            array[1] = stack.isEmpty()?x:Math.min(x, min);
            min = array[1];
            stack.add(array);
        }

        public void pop() {
            if(!stack.isEmpty()){
                stack.remove(stack.size()-1);
                min = stack.isEmpty()?0:stack.get(stack.size()-1)[1];
            }
        }

        public int top() {
            return stack.get(stack.size()-1)[0];
        }

        public int getMin() {
            return min;
        }


    }

    /**
     * 入栈，多压栈一个最小值。出栈多一次判断。如果当前移除元素是最小值，
     * 那么再一次出栈的值，就是移除之后的最小值。
     * 此方法与 MinStack4(整型数组和ArrayList作为栈)有很大相似的地方。
     */
    static class MinStack4 {
        int min = Integer.MAX_VALUE;
        Stack<Integer> stack = new Stack<Integer>();

        public void push(int x) {
            if(x <= min){
                stack.push(min);
                min = x;
            }
            stack.push(x);
        }

        public void pop() {
            if(stack.pop() == min) {
                min=stack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min;
        }
    }

}
