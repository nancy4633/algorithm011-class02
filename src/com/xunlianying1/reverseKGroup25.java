package com.xunlianying1;

// 第二遍
// 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
// k 是一个正整数，它的值小于或等于链表的长度。
// 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
public class reverseKGroup25 {
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
            if (tail == null) return head; // 长度不到k的段落不需要反转
            tail = tail.next; // 每一组的反转不包含tail。tail是下一组的head
        }
        ListNode newHead = reverse(head, tail);
        head.next = reverseKGroup(tail, k); // 每一组的反转不包含tail。tail是下一组的head
        return newHead;
    }

    private ListNode reverse(ListNode head, ListNode tail) { // 反转不包含tail
        ListNode pre = null, next = null;
        while (head != tail) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 递归 + 哨兵(dummy) - 递归参数 = head - 不看，写的很啰嗦
     * 时间复杂度:O(n*K) - 100.00% - 最好的情况为O(n) ， 最差的情况为O(n^2)
     * 空间复杂度:O(1) - 82.02% - 自定义的几个临时节点。
     * 优点:
     * 缺点:
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup1(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy, end = dummy;
        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) break;
            ListNode start = pre.next, next = end.next;
            end.next = null; // 这是后面reverse只需要传一个参数的关键，第k个元素的next=null做为循环的结束条件
            pre.next = reverse(start);
            start.next = next;
            pre = start;
            end = pre;
        }
        return dummy.next;
    }

    private ListNode reverse(ListNode head) {
        ListNode pre = null, curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }
}
