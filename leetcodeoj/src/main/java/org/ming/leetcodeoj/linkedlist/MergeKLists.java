package org.ming.leetcodeoj.linkedlist;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class MergeKLists {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l4 = new ListNode(4);
        ListNode l7 = new ListNode(7);
        l1.next = l4;
        l4.next = l7;


        ListNode l2 = new ListNode(2);
        ListNode l5 = new ListNode(5);
        ListNode l8 = new ListNode(8);
        l2.next = l5;
        l5.next = l8;

        ListNode l3 = new ListNode(3);
        ListNode l6 = new ListNode(6);
        ListNode l9 = new ListNode(9);
        l3.next = l6;
        l6.next = l9;

        printNode(l1);
        System.out.println();
        printNode(l2);
        System.out.println();
        printNode(l3);
        System.out.println();
        // printNode(mergeTwoLists(l1,l3));
        System.out.println();
        ListNode[] lists = new ListNode[]{l1, l2, l3};
        printNode(mergeKLists1(lists));
    }

    public static void printNode(ListNode node) {
        while (node != null) {
            System.out.print(node.val + ",");
            node = node.next;
        }
    }

    public static ListNode mergeKLists1(ListNode[] lists) {
        // 先合并
        ListNode listNode = null;
        for (int i = 0; i < lists.length; i++) {
            listNode = mergeTwoLists(listNode, lists[i]);
        }
        return listNode;
    }

    /**
     * 归并排序（递归处理）
     * 1. 先拆
     * 2. 再合并
     *
     * @param lists
     * @return
     */
    public static ListNode mergeKLists2(ListNode[] lists) {
        // 先合并
        return merge(lists, 0, lists.length - 1);
    }

    private static ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        return mergeTwoLists(merge(lists, left, mid), merge(lists, mid + 1, right));
    }


    /**
     * ? -> current -> m1 -> n
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 虚拟节点
        ListNode dummyHead = new ListNode(0);
        // 用来移动节点的current
        ListNode current = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                current.next = l1;
                current = current.next;
                l1 = l1.next;
            } else {
                current.next = l2;
                current = current.next;
                l2 = l2.next;
            }
        }

        if (l1 == null) {
            current.next = l2;
        } else {
            current.next = l1;
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
