package org.ming.leetcodeoj.linkedlist;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _203S_RemoveElements {
    public static void main(String[] args) {

    }

    /*
    [1,2,6,3,4,5,6], val = 6
    [1,2,3,4,5]
    dummy -> current
     */
    public ListNode removeElements1(ListNode head, int val) {
        //
        if (head == null) {
            return head;
        }
        // 因为删除可能涉及到头节点，所以设置dummy节点，统一操作
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy;
        ListNode current = head;
        while (current != null) {
            // 移除元素
            if (current.val == val) {
                pre.next = current.next;
            } else {
                pre = current;
            }
            // 迭代
            current = current.next;
        }
        return dummy.next;
    }


    /**
     * 不添加虚拟节点方式
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     *
     * @param head
     * @param val
     * @return
     */
    public static ListNode removeElements2(ListNode head, int val) {
        // 检查head是否为目标节点
        while (head != null && head.val == val) {
            head = head.next;
        }
        // 已经为null，提前退出
        if (head == null) {
            return null;
        }
        // 已确定当前head.val != val
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }


    static class ListNode {
        int val;
        ListNode next;

        public ListNode(Integer val, ListNode next) {
            this.val = val;
            this.next = next;
        }

    }
}
