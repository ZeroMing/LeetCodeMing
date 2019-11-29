package org.ming.leetcodeoj.linkedlist;

/**
 * 合并有序链表
 * @author: LeoLee
 * @date: 2019/11/29 10:27
 */
public class Leetcodeoj_21_20191129_MergeTwoSortedLists {

    /**
        将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
        示例：
        输入：1->2->4, 1->3->4
        输出：1->1->2->3->4->4

        Node ->

        1 -> 1 -> 2
     */

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(3);
        ListNode node6 = new ListNode(4);
        node4.next = node5;
        node5.next = node6;

        Leetcodeoj_21_20191129_MergeTwoSortedLists leetcodeoj = new Leetcodeoj_21_20191129_MergeTwoSortedLists();
        ListNode listNode = leetcodeoj.mergeTwoLists(node1, node4);
        System.out.println(listNode);


    }

    /**
     * 头指针方法
     * 复杂度分析
     * 时间复杂度：O(n + m)O(n+m) 。因为每次循环迭代中，l1 和 l2 只有一个元素会被放进合并链表中， while 循环的次数等于两个链表的总长度。所有其他工作都是常数级别的，所以总的时间复杂度是线性的。
     * 空间复杂度：O(1)O(1) 。迭代的过程只会产生几个指针，所以它所需要的空间是常数级别的。
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);
        ListNode prev = prehead;
        while (l1 != null && l2 != null){
            if(l1.val <= l2.val){
                prev.next = l1;
                l1 = l1.next;
            }else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        prev.next = l1 == null ? l2 : l1;
        return prehead.next;
    }


    /**
     * 递归方法
     * 时间复杂度：O(n + m)O(n+m)。 因为每次调用递归都会去掉 l1 或者 l2 的头元素（直到至少有一个链表为空），函数 mergeTwoList 中只会遍历每个元素一次。所以，时间复杂度与合并后的链表长度为线性关系。
     * 空间复杂度：O(n + m)O(n+m)。调用 mergeTwoLists 退出时 l1 和 l2 中每个元素都一定已经被遍历过了，所以 n + mn+m 个栈帧会消耗 O(n + m)O(n+m) 的空间。
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists_2(ListNode l1, ListNode l2) {
        if(l1 == null){
            return l2;
        }

        if(l2 == null){
            return l1;
        }

        if(l1.val <= l2.val){
            l1.next = mergeTwoLists(l1.next,l2);
            return l1;
        }else{
            l2.next = mergeTwoLists(l1,l2.next);
            return l2;
        }
    }



    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

}
