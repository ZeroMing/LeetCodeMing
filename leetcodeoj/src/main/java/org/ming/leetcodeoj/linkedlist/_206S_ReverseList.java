package org.ming.leetcodeoj.linkedlist;

import org.ming.common.ListNode;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 单链表反转
 *
 * @author: LeoLee
 * @date: 2019/11/28 18:06
 */
public class _206S_ReverseList {


    public static void main(String[] args) {
        ReentrantReadWriteLock
        ListNode head = new ListNode(1, null);
        ListNode next2 = new ListNode(2, null);
        head.next = next2;
        ListNode next3 = new ListNode(3, null);
        next2.next = next3;
        ListNode next4 = new ListNode(4, null);
        next3.next = next4;
        ListNode listNode = reverseList2(head);
        print(listNode);
    }

    private static void print(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }

    /**
     * 就地反转
     * <p>
     * 1 -> 2 -> 3 -> 4 -> 5
     * dummy -> 1 -> 2 -> 3 -> 4 -> 5
     * p = head
     * q = head.next
     * <p>
     * 1-> 3
     * 2 -> 1 -> 3
     * dummy -> 2 -> 1 -> 3 -> 4 -> 5
     * q = 1
     *
     * @param head
     * @return
     */
    public static ListNode reverseList0(ListNode head) {
        if (head == null) {
            return null;
        }
        // 虚拟节点
        ListNode dummy = new ListNode(-1, null);
        dummy.next = head;
        ListNode cur = dummy.next;
        ListNode next = cur.next;
        while (next != null) {
            // 4
            cur.next = next.next;
            // 3-> 2
            next.next = dummy.next;
            // dummy -> 3
            dummy.next = next;
            // 4
            next = cur.next;
        }
        return dummy.next;
    }


    /**
     * 头插法反转链表 与 就地反转法 一毛一样
     *
     * @param head
     * @return
     */
    public static ListNode reverseList1(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummyHead = new ListNode(-1, null);
        ListNode cur = head;
        while (cur != null) {
            // 下一个节点
            ListNode tempNext = cur.next;
            // 插入
            cur.next = dummyHead.next;
            dummyHead.next = cur;
            // 继续出来下一个节点
            cur = tempNext;
        }
        return dummyHead.next;
    }


    /**
     * 双指针方法
     * <p>
     * 1. 定义pre指针，指向 null
     * 2. 定义cur指针，指向 head
     * 2. 每次将current.next指向 pre，然后将 pre 和 current 前进一位
     *
     * @param head
     */
    public static ListNode reverseList2(ListNode head) {
        if (head == null) {
            return null;
        }
        // 新的头
        ListNode newHead = null;
        ListNode current = head, nextTemp = null;
        while (current != null) {
            // 下一个节点
            nextTemp = current.next;
            // 头节点的下一个节点是null，局部反转
            current.next = newHead;
            // pre和cur节点都前进一位
            // 将p1 赋值给 p0
            newHead = current;
            // 将 p2 赋值给 p1
            current = nextTemp;
        }
        return newHead;
    }


    /**
     * 头删法
     *
     * @param head
     */
    public static ListNode reverseList3(ListNode head) {

        ListNode newHead = null;
        ListNode tempNode;
        while (head != null) {
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
     *
     * @param head
     * @return
     */
    public ListNode reverseList4(ListNode head) {
        return reverse(null, head);
    }

    private static ListNode reverse(ListNode prev, ListNode cur) {
        if (cur == null) {
            return prev;
        }
        // 先保存下一个节点
        ListNode temp = cur.next;
        // 反转
        cur.next = prev;
        // 更新prev、cur位置
        // prev = cur;
        // cur = temp;
        return reverse(cur, temp);
    }

    /**
     * 递归反转
     *
     * @param head
     * @return
     */
    public ListNode reverseList5(ListNode head) {
        // 递归终止条件是当前为空，或者下一个节点为空
        if (head == null || head.next == null) {
            return head;
        }
        // 这里的cur就是最后一个节点
        ListNode cur = reverseList5(head.next);
        // 这里请配合动画演示理解
        // 如果链表是 1->2->3->4->5，那么此时的cur就是5
        // 而head是4，head的下一个是5，下下一个是空
        // 所以head.next.next 就是5->4
        head.next.next = head;
        // 防止链表循环，需要将head.next设置为空
        head.next = null;
        // 每层递归函数都返回cur，也就是最后一个节点
        return cur;
    }
}
