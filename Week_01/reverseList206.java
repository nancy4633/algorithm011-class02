package com.xunlianying1;

// 第三遍 - 重点 递归重点
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
        ListNode pre = null;
        ListNode cur = head;
        ListNode tmp = null;
        while (cur != null) {
            tmp = cur.next; // 这个遍历吧，其实就是一个接一个，前一个公式的后面接后一个公式的前面。
            cur.next = pre;
            pre = cur;
            cur = tmp;
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
        ListNode cur = reverseList2(head.next); // 遍历到最后，实际返回的是原始节点链表的队尾的节点。
        head.next.next = head; // 实际上就是 掉转箭头
        head.next = null;
        return cur;
    }
}
