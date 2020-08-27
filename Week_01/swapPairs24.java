package com.xunlianying1;

// 第一遍
// 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
public class swapPairs24 {

    /**
     * 暴力求解：
     * 时间复杂度:O() -
     * 空间复杂度:O() -
     * 优点:
     * 缺点:
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode temp = pre;
        while (temp.next != null && temp.next.next != null) {
            ListNode start = temp.next;
            ListNode end = temp.next.next;
            temp.next = end;
            start.next = end.next;
            end.next = start;
            temp = start;
        }
        return pre.next;
    }

    /**
     * 递归
     * 时间复杂度:O() -
     * 空间复杂度:O() -
     * 优点:
     * 缺点:
     *
     * @param head
     * @return
     */
    public static ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode swap = head.next;
        head.next = swapPairs2(swap.next);
        swap.next = head;
        head = swap;
        return swap;
    }
}
