package com.first.zuoye;

// 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

// 示例:
// 给定 1->2->3->4, 你应该返回 2->1->4->3.

// 疑问：
// 1.确定是单链表吗？可能是双链表吗？
// 2.值的交换指的是什么？非值的交换又指的是什么？需要节点的地址也交换吗？
// 3.怎么去改指针呢？
// 4.可以改造linknode类实现吗？加一个prenode节点，这样直接交换nextnode和prenode对象就可以了？

// 思路：
// 是不是利用深度复制呢？
// 暴力求解：直接一个一个复制，也可以

// 两两交换：我理解错了，原来是成对的交换，不是指轮流交换，这个做题之前需要跟需求方讨论，确认里面的每一个名词都理解正确。
// 现在问题是：交换+链表的指针，整个结果就蒙了，完全不知道怎么计算

public class swapPairs24 {

    /***
     * 暴力求解法，直接遍历
     *
     * @param head
     * @return
     */
    public ListNode swapPairs1(ListNode head) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode temp = pre;
        while (temp.next != null && temp.next.next != null) {
            ListNode start = temp.next;
            ListNode end = temp.next.next;
            temp.next = end;
            start.next = end.next;
            end.next = start;
            temp = start;
        }
        return pre.next;
    }

    /***
     * 递归实现，还是有很多不透彻的，需要练习
     * 递归实现的代码，真的很简洁，需要假设，其他步骤已经实现，只要完成当前步骤的逻辑即可，但是我现在思路还没完全转换过来。
     *
     * @param head
     * @return
     */
    public static ListNode swapPairs11(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode swap = head.next;
        head.next = swapPairs11(swap.next);
        swap.next = head;
        head = swap;
        return swap;
    }

    /***
     * 为什么交换后的linknode的值一直打印不对呢，好奇怪啊
     *
     * @param args
     */
    public static void main(String[] args) {
        ListNode test = new ListNode(1);
        test.next = new ListNode(2);
        test.next.next = new ListNode(3);
        test.next.next.next = new ListNode(4);
        test.next.next.next.next = new ListNode(5);
        while (test != null) {
            System.out.println(test.val);
            test = test.next;
        }
        swapPairs11(test);
        while (test != null) {
            System.out.println(test.val);
            test = test.next;
        }
    }
}
