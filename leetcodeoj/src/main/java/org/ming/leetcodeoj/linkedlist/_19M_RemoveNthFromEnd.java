package org.ming.leetcodeoj.linkedlist;

import org.ming.common.ListNode;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @date: 2020年07月22日 22时00分
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _19M_RemoveNthFromEnd {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        head.next = listNode2;
        ListNode listNode3 = new ListNode(3);
        listNode2.next = listNode3;
        ListNode listNode4 = new ListNode(4);
        listNode3.next = listNode4;
        ListNode listNode5 = new ListNode(5);
        listNode4.next = listNode5;
        System.out.println(head);
        _19M_RemoveNthFromEnd leetcodeoj = new _19M_RemoveNthFromEnd();
        ListNode listNode = leetcodeoj.removeNthFromEnd(head, 4);
        System.out.println(listNode);

    }


    /**
     * 5
     * 3
     * 1-> 2-> 3-> 4 -> 5
     *
     * @param head 链表
     * @param last 倒数
     */
    public ListNode removeNthFromEnd(ListNode head, int last) {
        ListNode lowNode = head;
        ListNode fastNode = head;
        // 快指针，先走n步
        int i = 0;
        while (i++ < last) {
            fastNode = fastNode.next;
        }
        // 已经到头，说明要删除原head节点，新节点为head.next
        if (fastNode == null) {
            return head.next;
        }
        //
        while (fastNode.next != null) {
            fastNode = fastNode.next;
            lowNode = lowNode.next;
        }

        lowNode.next = lowNode.next.next;
        return head;
    }

}
