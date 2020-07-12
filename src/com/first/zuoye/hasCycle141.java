package com.first.zuoye;

import java.util.HashSet;
import java.util.Set;

// 给定一个链表，判断链表中是否有环。
// 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
// 如果 pos 是 -1，则在该链表中没有环。
// 思路
public class hasCycle141 {
    /****
     * set求解：因为set可以去重
     *
     * @param head
     * @return
     */
    public boolean hasCycle1(ListNode head) {
        Set s = new HashSet();
        //定义一个set，然后不断遍历
        while (head != null) {
            //只要某个节点在set中出现过，说明遍历到重复元素了
            if (s.contains(head)) {
                return true;
            }
            s.add(head);
            head = head.next;
        }
        return false;
    }

    /****
     * 快慢指针：
     *
     * @param head
     * @return
     */
    public boolean hasCycle11(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        //定义两个指针i和j，i是慢指针，j是快指针
        ListNode i = head;
        ListNode j = head.next;
        while (j != null && j.next != null) {
            if (i == j) {
                return true;
            }
            //i每次走一步，j每次走两步
            i = i.next;
            j = j.next.next;
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
