package com.first.zuoye;

// 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
// 思路：
// 暴力求解 - 这个不是自己想到的
// 递归 - 最先想到的就是递归
public class mergeTwoLists21 {

    /***
     * 暴力求解：迭代
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);

        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
    }

    /**
     * 递归
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n) - 递归调用占用栈空间
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists11(ListNode l1, ListNode l2) {
        // 这里的判断循环结束条件，特别拿不准。一直以来都是短板，多注意！
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val <= l2.val) {
            l1.next = mergeTwoLists11(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists11(l1, l2.next);
            return l2;
        }
    }

    public static void main(String[] args) {

    }
}
