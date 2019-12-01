package org.ming.leetcodeoj.linkedlist;

import javax.xml.soap.Node;

/**
 *
 * 单链表反转
 * @author: LeoLee
 * @date: 2019/11/28 18:06
 */
public class ReverseList {


    public static void main(String[] args) {
        ReverseList reverseList = new ReverseList();
        ListNode head = new ListNode(1,null);
        ListNode next2 = new ListNode(2,null);
        head.next = next2;
        ListNode next3 = new ListNode(3,null);
        next2.next = next3;
        ListNode next4 = new ListNode(4,null);
        next3.next = next4;

        ListNode listNode = reverseList.reverseList_3(head);

    }


    /**
     * 【1->next】【2->next】【3-next】【4-next】
     *
     *
     * @param head
     */
    public ListNode reverseList(ListNode head){
        if(head == null){
            return head;
        }
        ListNode p0 = null,p1 = head,p2 = head.next;
        while (p1 != null) {
            p1.next = p0;
            p0 = p1;
            p1 = p2;
            if(p2 != null) {
                p2 = p2.next;
            }
        }
        return p0;
    }



    /**
     * 【1->next】【2->next】【3-next】【4-next】
     *
     *
     * @param head
     */
    public ListNode reverseList_2(ListNode head){

        ListNode newHead = null;
        ListNode tempNode;
        while (head != null){
            // 做头删除
            tempNode = head;
            head = head.next;

            // newHead一直在变化
            tempNode.next = newHead;
            newHead = tempNode;

        }
        return newHead;
    }

    /**
     * 递归反转
     * @param head
     * @return
     */
    public ListNode reverseList_3(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode reHead = reverseList_3(head.next);
        head.next.next = head;
        head.next = null;
        return reHead;
    }






    static class ListNode {
        int value;
        ListNode next;

        public ListNode(int value, ListNode next) {
            this.value = value;
            this.next = next;
        }


    }
}
