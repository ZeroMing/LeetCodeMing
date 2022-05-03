package org.ming.leetcodeoj.linkedlist;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class MergeTwoLists {
    /*
    将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的
     */
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l3 = new ListNode(3);
        ListNode l5 = new ListNode(5);
        l1.next = l3;
        l3.next = l5;


        ListNode l2 = new ListNode(2);
        ListNode l4 = new ListNode(4);
        ListNode l6 = new ListNode(6);
        l2.next = l4;
        l4.next = l6;

        printNode(l1);
        System.out.println();
        printNode(l2);
        System.out.println();
        ListNode listNode = mergeTwoLists(l1, l2);
        printNode(listNode);

    }

    public static void printNode(ListNode node) {
        while (node != null) {
            System.out.print(node.val + ",");
            node = node.next;
        }
        System.out.println();
    }


    /**
     * 1 -> 3 -> 5
     * 2 -> 4 -> 6
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                // 修改 next节点
                cur.next = l1;
                cur = cur.next;
                l1 = l1.next;
            } else {
                cur.next = l2;
                cur = cur.next;
                l2 = l2.next;
            }
        }
        // 任一为空，直接连接另一条链表
        if (l1 == null) {
            cur.next = l2;
        } else {
            cur.next = l1;
        }
        return dummyHead.next;
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
