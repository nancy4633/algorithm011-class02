package com.first.zuoye;

import java.util.Deque;
import java.util.LinkedList;

/***
 * 用 add first 或 add last 这套新的 API 改写 Deque 的代码
 *
 * 思路：看到这个题目的时候完全没思路
 *
 * 感想：即使看了其他同学的题解，也很费解，答案和问题匹配么。。。至今蒙圈
 * 周五开始写作业，第一题就卡壳，致使整个作业延迟了五天天。包括之后的每日一题都完全搁置了。
 * 第一周的结束没想到如此崩溃。。。狼狈
 *
 */
public class OverWriteDeque {
    public static void main(String[] args) {
        Deque<String> deque = new LinkedList();
        deque.addFirst("a");
        deque.addFirst("b");
        deque.addFirst("c");

        String first = deque.peekFirst();
        String last = deque.peekLast();
        System.out.println(first);
        System.out.println(last);
        System.out.println(deque);

        deque.addLast("g");
        deque.addLast("f");
        deque.addLast("e");

        first = deque.peekFirst();
        last = deque.peekLast();
        System.out.println(first);
        System.out.println(last);
        System.out.println(deque);
        while (deque.size() > 0) {
            System.out.println(deque.removeLast());
        }
        System.out.println(deque);
    }
}
