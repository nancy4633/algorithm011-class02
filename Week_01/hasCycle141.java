package com.xunlianying1;

import java.util.HashSet;
import java.util.Set;

// 第一遍 - 递归 + 快慢指针 - 没看懂！！！需要再看一遍
// 给定一个链表，判断链表中是否有环。
// 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
// 如果 pos 是 -1，则在该链表中没有环。
// 注意
// == 比 equals 快了好多
public class hasCycle141 {
    /**
     * 快慢指针
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
        ListNode i = head;
        ListNode j = head.next;
        while (j != null && j.next != null) {
            if (i == j) return true; // == 比 equals 快了好多！！！
            i = i.next;
            j = j.next.next;
        }
        return false;
    }

    /**
     * 标记法(每一个遍历过的节点值都置为 特定值) + 递归
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
     * 递归 + 快慢指针 - 没看懂
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 5.42%
     * 优点:
     * 缺点:
     *
     * @param head
     * @return
     */
    public boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) return false;
        if (head == head.next) return true;
        ListNode breaker = head.next;
        head.next = head;
        return hasCycle2(breaker);
    }

    /**
     * 利用set求解
     * 时间复杂度:O() - 16.82%
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
