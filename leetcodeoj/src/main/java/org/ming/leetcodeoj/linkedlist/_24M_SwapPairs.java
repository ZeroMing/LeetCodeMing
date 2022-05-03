package org.ming.leetcodeoj.linkedlist;

import org.ming.common.ListNode;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _24M_SwapPairs {
    public static void main(String[] args) {

    }

    /**
     * 1,2,3,4
     * dummy,1,2,3,4
     * <p>
     * temp.nex = s2;
     * s1.nex = s2.next;
     * s2.next = s1;
     * <p>
     * temp = s1;
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        // 前节点
        ListNode pre = dummyHead;
        while (pre.next != null && pre.next.next != null) {
            ListNode s1 = pre.next;
            ListNode s2 = pre.next.next;
            pre.next = s2;
            s1.next = s2.next;
            s2.next = s1;
            pre = s1;
        }
        return dummyHead.next;
    }
}
