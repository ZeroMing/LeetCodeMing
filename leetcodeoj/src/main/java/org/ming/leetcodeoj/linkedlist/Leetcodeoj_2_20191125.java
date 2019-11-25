package org.ming.leetcodeoj.linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * 链表操作
 * @author: LeoLee
 * @date: 2019/11/25 13:45
 */
public class Leetcodeoj_2_20191125 {

    /*
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     *
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     *
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *
     * 示例：
     *
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     *
     */
    public static void main(String[] args) {
        Leetcodeoj_2_20191125 leetcodeoj = new Leetcodeoj_2_20191125();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(4);
        node1.next =  node2;
        ListNode node3 = new ListNode(5);
        node2.next = node3;
        node3.next = new ListNode(9);

        // 9541
        //  999
        // 10540

        ListNode node4 = new ListNode(9);
        ListNode node5 = new ListNode(9);
        node4.next =  node5;
        ListNode node6 = new ListNode(9);
        node5.next =  node6;

        System.out.println(leetcodeoj.addTwoNumbers(node1,node4));


    }


    /**
     * 1 > 2 > 3
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }
        if(l1 == null && l2 == null){
            return new ListNode(0);
        }
        ListNode rootNode = null;
        // 记录最后一个节点
        ListNode  lastNode = null;
        // 记录临时节点
        ListNode  tempNode = null;
        int tempValue = 0,tempHigh=0,tempLow=0;
        while (l1 != null && l2 != null) {
            tempValue = l1.val + l2.val + tempHigh;
            tempHigh = tempValue /10;
            tempLow = tempValue % 10;
            if(rootNode == null){
                rootNode = new ListNode(tempLow);
                lastNode = rootNode;
            }else{
                tempNode =  new ListNode(tempLow);
                lastNode.next = tempNode;
                lastNode = tempNode;
            }
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null){
            tempValue = l1.val+tempHigh;
            tempHigh = tempValue / 10;
            tempLow = tempValue % 10;
            // 需要加上进位
            tempNode = new ListNode(tempLow);
            lastNode.next = tempNode;
            lastNode = tempNode;
            l1 = l1.next;
        }

        while (l2 != null){
            tempValue = l2.val+tempHigh;
            tempHigh = tempValue /10;
            tempLow = tempValue % 10;
            tempNode = new ListNode(tempLow);
            lastNode.next = tempNode;
            lastNode = tempNode;
            l2 = l2.next;
        }

        if(tempHigh > 0){
            lastNode.next = new ListNode(tempHigh);
        }
        return rootNode;
    }

}

class ListNode{
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

    @Override
    public String toString() {
        List<Integer> list = new ArrayList<>();
        list.add(val);
        ListNode tempNode = next;
        while (tempNode != null){
            list.add(tempNode.val);
            tempNode = tempNode.next;
        }
        // Collections.reverse(list);
        return list.toString();
    }
}


