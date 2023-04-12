package org.ming.day1q1;

import org.ming.common.ListNode;

import static org.ming.App.generateNodeList;

/**
 * @author liming53
 * @date 2023/4/12
 * @company 58房产 · 交易技术部
 * @since 1.0
 */
public class _1669_M_MergeInBetween {
    public static void main(String[] args) {

        ListNode listNode1 = generateNodeList(new int[]{2, 7, 4, 3, 5});
        ListNode listNode2 = generateNodeList(new int[]{0, 0, 0, 0, 0});
        System.out.println(mergeInBetween(listNode1, 2, 3, listNode2));


    }

    /*
    1669. 合并两个链表
提示
中等
96
相关企业
给你两个链表 list1 和 list2 ，它们包含的元素分别为 n 个和 m 个。

请你将 list1 中下标从 a 到 b 的全部节点都删除，并将list2 接在被删除节点的位置。

下图中蓝色边和节点展示了操作后的结果：


请你返回结果链表的头指针。



示例 1：

输入：list1 = [0,1,2,3,4,5], a = 3, b = 4, list2 = [1000000,1000001,1000002]
输出：[0,1,2,1000000,1000001,1000002,5]
解释：我们删除 list1 中下标为 3 和 4 的两个节点，并将 list2 接在该位置。上图中蓝色的边和节点为答案链表。

示例 2：

输入：list1 = [0,1,2,3,4,5,6], a = 2, b = 5, list2 = [1000000,1000001,1000002,1000003,1000004]
输出：[0,1,1000000,1000001,1000002,1000003,1000004,6]
解释：上图中蓝色的边和节点为答案链表。
     */

    public static ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        // 1. 先跑到a的位置（preA.next = list2; a）
        // 2. 继续跑到b的位置,(lastList2.next = b.next)
        if(list1 == null){
            return null;
        }

        int i = 0;
        ListNode cur = list1, pre = cur;
        while (i < a) {
            pre = cur;
            cur = cur.next;
            i++;
        }
        pre.next = list2;

        ListNode cur2 = list2, last = cur2;
        while (cur2 != null) {
            last = cur2;
            cur2 = cur2.next;
        }

        while (i <= b) {
            cur = cur.next;
            i++;
        }
        last.next = cur;

        return list1;
    }

}
