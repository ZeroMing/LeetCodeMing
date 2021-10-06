package org.ming.leetcodeoj.linkedlist;

/**
 * 1. 利用外部空间
 * 2. 双指针迭代
 * 3. 递归解法
 * 4. 妖魔化的双指针迭代
 *
 * @description: Reverse Linked List
 * @author: LeoMee
 * @date: 2019年08月11 15时59分
 */
public class Leetcodeoj_206_2019_08_11_linkedlist_easy {
    /**
     * 链表反转。
     */

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        head.next = node2;
        ListNode node3 = new ListNode(3);
        node2.next = node3;
        ListNode node4 = new ListNode(4);
        node3.next = node4;
        // printNode(reverseLinkedList1(head));
        printNode(reverseLinkedList2(head));
    }

    public static void printNode(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }


    /**
     * 假设链表为 1 \rightarrow 2 \rightarrow 3 \rightarrow \varnothing1→2→3→∅，我们想要把它改成 \varnothing \leftarrow 1 \leftarrow 2 \leftarrow 3∅←1←2←3。
     * <p>
     * 在遍历链表时，将当前节点的 \textit{next}next 指针改为指向前一个节点。由于节点没有引用其前一个节点，因此必须事先存储其前一个节点。在更改引用之前，还需要存储后一个节点。最后返回新的头引用
     * <p>
     * 复杂度分析：
     * 时间复杂度：O(n)O(n)，假设 nn 是列表的长度，时间复杂度是 O(n)O(n)。
     * 空间复杂度：O(1)O(1)。
     *
     * @param head
     * @return
     */
    public static ListNode reverseLinkedList1(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;

    }

    /**
     * 递归解法
     * <p>
     * 递归的意思：我子节点下的所有节点都已经反转好了，现在就剩我和我的子节点 没有完成最后的反转了，所以反转一下我和我的子节点。
     *
     * @param head
     * @return
     */
    public static ListNode reverseLinkedList3(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseLinkedList3(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }


    /**
     * 空间倒腾算法
     *
     * @param head
     * @return
     */
    public static ListNode reverseLinkedList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 前指针
        ListNode pre = null;
        // 当前指针
        ListNode cur = head;
        // 后指针
        ListNode next = head.next;
        while (cur != null) {

            cur.next = pre;
            // 将 p0设置为 p1
            pre = cur;
            // 将 p1 设置为 p2
            cur = next;
            // next后面没有节点的情况就不需要再往下找节点了
            if (next != null) {
                next = next.next;
            }
        }
        return pre;
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


}
