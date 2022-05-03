package org.ming.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public ListNode(int x, ListNode next) {
        val = x;
        this.next = next;
    }

    @Override
    public String toString() {
        List<Integer> list = new ArrayList<>();
        list.add(val);
        ListNode tempNode = next;
        while (tempNode != null) {
            list.add(tempNode.val);
            tempNode = tempNode.next;
        }
        // Collections.reverse(list);
        return list.toString();
    }
}
