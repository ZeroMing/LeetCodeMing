package org.ming.leetcodeoj.linkedlist;

import org.ming.common.ListNode;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class _1836M_DeleteDuplicatesUnsorted {
    public static void main(String[] args) {

    }

    void remove_duplicates(ListNode head) {
        ListNode ptr1 = null, ptr2 = null, dup = null;
        ptr1 = head;
        /* Pick elements one by one */
        while (ptr1 != null && ptr1.next != null) {
            ptr2 = ptr1;
            /* Compare the picked element with rest
                of the elements */
            while (ptr2.next != null) {
                /* If duplicate then delete it */
                if (ptr1.val == ptr2.next.val) {
                    /* sequence of steps is important here */
                    dup = ptr2.next;
                    ptr2.next = ptr2.next.next;
                    System.gc();
                } else /* This is tricky */ {
                    ptr2 = ptr2.next;
                }
            }
            ptr1 = ptr1.next;
        }
    }
}
