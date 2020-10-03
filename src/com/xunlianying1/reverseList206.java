package com.xunlianying1;

// 第二遍 - 重点 递归重点
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
        ListNode pre = null, cur = head, tmp;
        while (cur != null) { // terminator
            tmp = cur.next;
            cur.next = pre; // process
            pre = cur; // drill down
            cur = tmp; // drill down
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
        ListNode cur = reverseList2(head.next);
        head.next.next = head; // 死记硬背！！！
        head.next = null;// 死记硬背！！！
        return cur;
    }
}
