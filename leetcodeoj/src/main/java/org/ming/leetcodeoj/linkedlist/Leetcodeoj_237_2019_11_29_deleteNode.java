package org.ming.leetcodeoj.linkedlist;

/**
 * @author: LeoLee
 * @date: 2019/11/29 10:26
 */
public class Leetcodeoj_237_2019_11_29_deleteNode {
    /*

    请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。

    现有一个链表 -- head = [4,5,1,9]，它可以表示为:

    示例 1:

    输入: head = [4,5,1,9], node = 5
    输出: [4,1,9]
    解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
    示例 2:

    输入: head = [4,5,1,9], node = 1
    输出: [4,5,9]
    解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
     

    说明:

    链表至少包含两个节点。
    链表中所有节点的值都是唯一的。
    给定的节点为非末尾节点并且一定是链表中的一个有效节点。
    不要从你的函数中返回任何结果。

     */

    public static void main(String[] args) {
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(5);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(9);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        Leetcodeoj_237_2019_11_29_deleteNode leetcodeoj = new Leetcodeoj_237_2019_11_29_deleteNode();
        leetcodeoj.deleteNode(node2);
        System.out.println(node1);
    }

    // -1,4,5,6

    public void deleteNode(ListNode node,int value) {
        ListNode prev = new ListNode(-1);
        prev.next = node;
        while (node != null){
          if(node.val == value){
              prev.next = node.next;
              break;
          }else{
              prev = node;
          }
          node = node.next;
        }
    }

    /**
     * 原地删除
     * @param node
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }



}
