package org.ming.leetcodeoj.stack;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.Queue;

/**
 * Implement Stack using Queues
 * @Author: LeoLee
 * @Date: 2019年09月08 15时47分
 */
public class Leetcodeoj_225_20190908_stack_easy {


    static class MyStack {

        private ArrayQueue<Integer> queue;

        /** Initialize your data structure here. */
        public MyStack() {
            queue = new ArrayQueue<>(10);
        }

        /** Push element x onto stack. */
        public void push(int x) {
            queue.add(x);
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            int size = queue.size();
            if(size > 0){
                Integer result = queue.get(size - 1);
                queue.remove(size-1);
                size --;
                return result;
            }
            return -999;
        }

        /** Get the top element. */
        public int top() {
            int size = queue.size();
            if(size > 0){
                Integer result = queue.get(size - 1);
                return result;
            }
            return -999;
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            int size = queue.size();
            return size == 0;
        }
    }




    public static void main(String[] args) {
         MyStack stack = new MyStack();
         stack.push(1);
         stack.push(2);
         System.out.println(stack.top());// returns 2
         System.out.println(stack.pop());// returns 2
         System.out.println(stack.empty());// returns false

    }

    /**
     * Implement the following operations of a stack using queues.
     * push(x) -- Push element x onto stack.
     * pop() -- Removes the element on top of the stack.
     * top() -- Get the top element.
     * empty() -- Return whether the stack is empty.
     * Example:
     *
     * MyStack stack = new MyStack();
     * stack.push(1);
     * stack.push(2);
     * stack.top();   // returns 2
     * stack.pop();   // returns 2
     * stack.empty(); // returns false
     * Notes:
     *
     * You must use only standard operations of a queue --
     * which means only push to back, peek/pop from front, size,
     * and is empty operations are valid.
     * Depending on your language, queue may not be supported natively.
     * You may simulate a queue by using a list or deque (double-ended queue),
     * as long as you use only standard operations of a queue.
     * You may assume that all operations are valid
     * (for example, no pop or top operations will be called on an empty stack).
     */


}
