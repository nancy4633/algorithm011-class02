package com.xunlianying1;

// 第三遍
// 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
public class swapPairs24 {

    /**
     * 遍历
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 84.17%
     * 优点:
     * 缺点:
     *
     * @param head
     * @return
     */
    public ListNode swapPairs1(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy, fir, sec;
        while (cur.next != null && cur.next.next != null) { //拿每一个循环的前一个节点：这样才可以保证一整个list的串联，如果每次只是内部逻辑，那么list就断了
            // init
            fir = cur.next;
            sec = cur.next.next;
            // process
            cur.next = sec;
            fir.next = sec.next;
            sec.next = fir; // cur.next.next = fir 同样
            // drill down
            cur = fir; // cur = cur.next.next; 同样
        }
        return dummy.next;
    }

    /**
     * 递归 - 重点
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 35.92%
     * 优点:
     * 缺点:
     *
     * @param head
     * @return
     */
    public static ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode sec = head.next;
        head.next = swapPairs2(head.next.next); // head.next发生变化;
        sec.next = head; // 这是的head.next已经变了 所以不能用 head.next.next = head;
        return sec; // 返回变化前的head.next
    }
}
