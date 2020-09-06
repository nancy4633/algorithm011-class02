package com.xunlianying1;

import java.util.HashSet;
import java.util.Set;

// 第一遍 - 递归是重点，太优秀的尾递归了，想法实在是奇妙啊！
// 给定一个链表，判断链表中是否有环。
// 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
// 如果 pos 是 -1，则在该链表中没有环。
// 注意
// == 比 equals 快了好多
public class hasCycle141 {
    /**
     * 快慢指针 + 遍历
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 79.85%
     * 优点:
     * 缺点:
     *
     * @param head
     * @return
     */
    public boolean hasCycle1(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) { // 记得判断next非空的情况
            if (slow == fast) return true; // == 比 equals 快了好多！！！
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }

    /**
     * 标记法 + 遍历
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 91.31%
     * 优点:
     * 缺点:
     *
     * @param head
     * @return
     */
    public boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) return false;
        while (head != null) {
            if (head.val == 0xaaaaaaaa) return true;
            head.val = 0xaaaaaaaa;
            head = head.next;
        }
        return false;
    }

    /**
     * 标记法 + 递归 - 每一个遍历过的节点值都置为 特定值
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 41.19%
     * 优点:
     * 缺点:
     *
     * @param head
     * @return
     */
    public boolean hasCycle3(ListNode head) {
        if (head == null) return false;
        if (head.val == 0xcafebabe) return true;
        head.val = 0xcafebabe;
        return hasCycle3(head.next);
    }

    /**
     * 递归 + 遍历过的节点next重定向为自己
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 10.95%
     * 优点:
     * 缺点:
     *
     * @param head
     * @return
     */
    public boolean hasCycle4(ListNode head) {
        if (head == null || head.next == null) return false;
        if (head == head.next) return true;
        ListNode nex = head.next;
        head.next = head;
        return hasCycle4(nex); // 这个
    }

    /**
     * hashset + 遍历
     * 时间复杂度:O() - 22.22%
     * 空间复杂度:O() - 5.05%
     * 优点:
     * 缺点:
     *
     * @param head
     * @return
     */
    public boolean hasCycle5(ListNode head) {
        Set<ListNode> s = new HashSet<ListNode>();
        while (head != null) {
            if (s.contains(head)) return true;
            s.add(head);
            head = head.next;
        }
        return false;
    }
}
