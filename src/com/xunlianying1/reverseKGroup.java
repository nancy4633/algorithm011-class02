package com.xunlianying1;

// 第二遍 - 重点！！！
// 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
// k 是一个正整数，它的值小于或等于链表的长度。
// 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
public class reverseKGroup {
    /**
     * 递归 - 递归参数= head+tail
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 77.87%
     * 优点:
     * 缺点:
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode tail = head;
        for (int i = 0; i < k; i++) {
            if (tail == null) return head;
            tail = tail.next;
        }
        ListNode newhead = reverse(head, tail);
        head.next = reverseKGroup(tail, k);
        return newhead;
    }

    public ListNode reverse(ListNode head, ListNode tail) {
        ListNode pre = null, nex = null;
        while (head != tail) {
            nex = head.next;
            head.next = pre;
            pre = head;
            head = nex;
        }
        return pre;
    }
}
