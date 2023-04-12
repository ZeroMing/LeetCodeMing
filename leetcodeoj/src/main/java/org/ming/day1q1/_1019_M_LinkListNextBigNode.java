package org.ming.day1q1;

import org.ming.common.ListNode;

import java.util.Arrays;

import static org.ming.App.generateNodeList;

/**
 * @author liming53
 * @date 2023/4/10
 * @company 58房产 · 交易技术部
 * @since 1.0
 */
public class _1019_M_LinkListNextBigNode {
    /*

    1019. 链表中的下一个更大节点
    中等
    288
    相关企业

    给定一个长度为 n 的链表 head
    对于列表中的每个节点，查找下一个 更大节点 的值。也就是说，对于每个节点，找到它旁边的第一个节点的值，这个节点的值 严格大于 它的值。

    返回一个整数数组 answer ，其中 answer[i] 是第 i 个节点( 从1开始 )的下一个更大的节点的值。如果第 i 个节点没有下一个更大的节点，设置 answer[i] = 0 。

    示例 1：
    输入：head = [2,1,5]
    输出：[5,5,0]
    示例 2：


    输入：head = [2,7,4,3,5]
    输出：[7,0,5,5,0]


    提示：
    链表中节点数为 n
    1 <= n <= 104
    1 <= Node.val <= 109

     */



    public static void main(String[] args) {
        ListNode listNode = generateNodeList(new int[]{2, 7, 4, 3, 5});
        System.out.println(listNode);
        int[] ints = nextLargerNodes(listNode);
        Arrays.stream(ints).forEach(item -> System.out.println(item));
    }




    public static int[] nextLargerNodes(ListNode head) {
        if (head == null) {
            return new int[]{};
        }
        ListNode iter = head;
        int size = 0;
        // 计算链表的长度
        while (iter != null) {
            iter = iter.next;
            size++;
        }
        int[] answer = new int[size];
        ListNode cur = head;
        int index = 0;
        // 当前不为空
        // [2,1,5]
        while (cur != null) {
            int curVal = cur.val;
            ListNode innerNext = cur.next;
            while (innerNext != null && curVal >= innerNext.val) {
                innerNext = innerNext.next;
            }

            if (innerNext != null) {
                answer[index] = innerNext.val;
            } else {
                answer[index] = 0;
            }
            cur = cur.next;
            index++;
        }
        return answer;
    }
}
