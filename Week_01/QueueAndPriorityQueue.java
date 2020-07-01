package com.first.zuoye;

import java.util.PriorityQueue;
import java.util.Queue;

/***
 * 分析 Queue 和 Priority Queue 的源码
 *
 */
public class QueueAndPriorityQueue {

    // 对比：
    public static void main(String[] args) {

        // 1. Queue是接口 vs PriorityQueue是实现类
        Queue queue = new PriorityQueue();
        // 2. 查看关系图会看到，PriorityQueue类间接实现了Queue接口。
        //    而且Queue和PriorityQueue都实现自Collection接口


    }
}
