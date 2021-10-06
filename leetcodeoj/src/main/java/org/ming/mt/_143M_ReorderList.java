package org.ming.mt;

/**
 * 重排链表
 *
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _143M_ReorderList {
    /*
    关于链表，你该了解这些！
    203.移除链表元素
    707.设计链表
    206.翻转链表
    24.两两交换链表中的节点
    19.删除链表的倒数第N个节点
    面试题02.07.链表相交
    142.环形链表II
    链表：总结篇！


     */
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        head.next = node2;
        ListNode node3 = new ListNode(3);
        node2.next = node3;
        ListNode node4 = new ListNode(4);
        node3.next = node4;
        ListNode node5 = new ListNode(5);
        node4.next = node5;
        reorderList2(head);
        printNode(head);
    }

    /*
    给定一个单链表 L 的头节点 head ，单链表 L 表示为：
    1 -> 2 -> 3 -> 4 ...... 5 -> 6
    请将其重新排列后变为：
    1 -> 6 -> 2 -> 5 ...... 4 ->
    不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换

    示例 1:
    输入: head = [1,2,3,4]
    输出: [1,4,2,3]

    示例 2:
    输入: head = [1,2,3,4,5]
    输出: [1,5,2,4,3]

    提示：
    链表的长度范围为 [1, 5 * 104]
    1 <= node.val <= 1000
     */


    /**
     * 1. 快慢指针找到中间节点
     * 2. 反转后半段链表
     * 3. 合并前半段链表和后半段链表
     *
     * @param head
     */
    public static void reorderList2(ListNode head) {
        // 快慢指针找中点
        if (head == null || head.next == null) {
            return;
        }

        // 1. 快慢指针寻找中间节点

        // 慢指针
        ListNode slow = head;
        // 快指针
        ListNode fast = head;
        //
        while (fast.next != null && fast.next.next != null) {
            // 慢指针 跳一次
            slow = slow.next;
            // 快指针 跳两次
            fast = fast.next.next;
        }

        // 2. 翻转后半段链表
        // 需要翻转的链表，后半段
        ListNode needReverser = slow.next;
        slow.next = null;
        needReverser = reverse(needReverser);

        // 3. 插入前端缝隙
        ListNode cur = head;
        while (cur != null && needReverser != null) {
            // 下一个 第二节点
            ListNode curSecond = needReverser;
            // 循环下一节点
            needReverser = needReverser.next;

            // 下一个 第一节点
            ListNode nextCur = cur.next;
            // 下一个 第二节点 的 next = 下一个 第一节点
            curSecond.next = nextCur;
            // 当前节点的 next = 下一个 第二节点
            cur.next = curSecond;
            // 当前节点 = 下一个 第一节点
            cur = nextCur;
        }
    }

    /**
     * 反转链表
     *
     * @param head
     * @return
     */
    static ListNode reverse(ListNode head) {

        ListNode p0 = null;
        ListNode p1 = head;
        ListNode p2;
        while (p1 != null) {
            p2 = p1.next;
            p1.next = p0;
            p0 = p1;
            p1 = p2;
        }
        return p0;
    }


    // Definition for singly-linked list.
    static class ListNode {
        int val;
        ListNode next;


        ListNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return val + "";
        }
    }


    public static void printNode(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }
}
