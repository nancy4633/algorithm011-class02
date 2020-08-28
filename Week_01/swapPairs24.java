package com.xunlianying1;

// 第一遍
// 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
public class swapPairs24 {

    /**
     * 遍历
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 34.27%
     * 优点:
     * 缺点:
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fir, sec, cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            fir = cur.next;
            sec = cur.next.next;
            fir.next = sec.next;
            cur.next = sec;
            cur.next.next = fir;
            cur = cur.next.next;
        }
        return dummy.next;
    }

    /**
     * 递归 - 重点
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 10.57%
     * 优点:
     * 缺点:
     *
     * @param head
     * @return
     */
    public static ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode swap = head.next;
        head.next = swapPairs2(head.next.next); // head.next = swapPairs2(swap.next);
        swap.next = head;
        return swap;
    }
}
