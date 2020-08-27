package com.xunlianying1;

import java.util.PriorityQueue;
import java.util.Queue;

/***
 * 分析 Queue 和 Priority Queue 的源码
 *
 */
public class QueueAndPriorityQueue {
    /***
     * Queue源码分析
     */
    public static void Queue() {
        // 1. Queue是接口 vs PriorityQueue是实现类
        // 2. 自身定义了6个方法：add offer remove poll element peek，又从collect继承了一部分方法。
    }

    /***
     * PriorityQueue源码分析
     */
    public static void PriorityQueue() {
        // 1. PriorityQueue是实现类，
        // 2. 查看关系图会看到，PriorityQueue类间接实现了Queue接口。
        Queue queue = new PriorityQueue();
        // 3. 有7个构造方法：
        //    支持自定义大小 - Capacity
        //    支持自定义排序 - Comparator，不指定Comparator时默认为最小堆，通过传入自定义的Comparator函数可以实现大顶堆
        //    支持Collection创建priorityqueue以及自带的优先级排序
        //    支持SortedSet创建priorityqueue以及自带的优先级排序
        // 4. 用数组存储二叉树结构。通过数组下标实现排序。
        // 5. 出队、入队操作后无法保证整个队列的序列，而且时间复杂度还可以优化
        // 6. 不可以存储null
    }
}
