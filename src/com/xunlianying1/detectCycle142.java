package com.xunlianying1;

import java.util.HashSet;
import java.util.Set;

// 第一遍
// 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
// 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
// 说明：不允许修改给定的链表！！！！
// 进阶：
// 你是否可以不用额外空间解决此题？
public class detectCycle142 {
    /**
     * 双指针
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 89.24%
     * 优点:
     * 缺点:
     *
     * @param head
     * @return
     */
    public ListNode detectCycle1(ListNode head) {
        ListNode slow = head, fast = head; // 这里的fast必须用head，否则会超时。
        while (true) { // 学会巧妙使用while(true)
            if (fast == null || fast.next == null) return null;
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) break; // ！！！因为初始值是相等的，所以这个要放在后面判断
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }

    /**
     * Floyd 算法 - 不看
     * 时间复杂度:O(n) - 100.00%
     * 空间复杂度:O(1) - 33.61%
     * 优点:
     * 缺点:
     *
     * @param head
     * @return
     */
    public ListNode detectCycle2(ListNode head) {
        if (head == null) return null;
        ListNode intersect = getIntersect(head);
        if (intersect == null) return null;
        ListNode ptr1 = head;
        ListNode ptr2 = intersect;
        while (ptr1 != ptr2) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }
        return ptr1;
    }

    private ListNode getIntersect(ListNode head) {
        ListNode tortoise = head;
        ListNode hare = head;
        while (hare != null && hare.next != null) {
            tortoise = tortoise.next;
            hare = hare.next.next;
            if (tortoise == hare) return tortoise;
        }
        return null;
    }

    /**
     * set
     * 时间复杂度:O(n) - 20.60%
     * 空间复杂度:O(n) - 6.49%
     * 优点:
     * 缺点:
     *
     * @param head
     * @return
     */
    public ListNode detectCycle3(ListNode head) {
        Set<ListNode> visited = new HashSet<ListNode>();
        ListNode node = head;
        while (node != null) {
            if (visited.contains(node)) return node;
            visited.add(node);
            node = node.next;
        }
        return null;
    }
}
