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
        // terminator
        if (head == null || head.next == null) return head;
        ListNode cur = reverseList2(head.next);
        head.next.next = head; // 不明白为什么head.next不写成cur， 明白了，因为cur.next在每次递归的时候都写成了null，原因是head为真正的head的时候，head.next就是null
        // reverse state，因为head的条件没办法判断，所以每次都要把当前处理节点的next指向null，然后利用cur.next.next来做指针转换。
        head.next = null;
        return cur;
    }
}
