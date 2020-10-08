package com.xunlianying1;

// 第三遍 - 重点 递归：指向关系重定向后，之前的无用的指向关系要删除，否则就会有环：Error - Found cycle in the ListNode
// 反转一个单链表。
// 进阶:
// 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
public class reverseList206 {

    /**
     * 双指针 + 迭代
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 30.90%
     * 优点:
     * 缺点:
     *
     * @param head
     * @return
     */
    public ListNode reverseList1(ListNode head) {
        ListNode pre = null, cur = head, nxt;
        while (cur != null) { // terminator
            nxt = cur.next; // init
            cur.next = pre; // process
            pre = cur; // drill down
            cur = nxt;
        }
        return pre;
    }

    /**
     * 递归 - 重点
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 5.19%
     * 优点:
     * 缺点:
     *
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = reverseList2(head.next);
        head.next.next = head; // head.next指回head
        head.next = null;// 删除head指向head.next的关系！！！
        return newHead;
    }
}
