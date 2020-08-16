package com.xunlianying8.zuoye1;

import java.util.HashMap;
import java.util.Map;

// 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
// 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
// 写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
// 进阶:
// 你是否可以在 O(1) 时间复杂度内完成这两种操作？
class LRUCache {
    /**
     * 缓存映射表
     */
    private Map<Integer, DLinkNode> cache = new HashMap<>();
    /**
     * 缓存大小
     */
    private int size;
    /**
     * 缓存容量
     */
    private int capacity;
    /**
     * 链表头部和尾部
     */
    private DLinkNode head, tail;

    public LRUCache(int capacity) {
        //初始化缓存大小，容量和头尾节点
        this.size = 0;
        this.capacity = capacity;
        head = new DLinkNode();
        tail = new DLinkNode();
        head.next = tail;
        tail.prev = head;
    }

    /**
     * 获取节点
     *
     * @param key 节点的键
     * @return 返回节点的值
     */
    public int get(int key) {
        DLinkNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        //移动到链表头部
        moveToHead(node);
        return node.value;
    }

    /**
     * 添加节点
     *
     * @param key   节点的键
     * @param value 节点的值
     */
    public void put(int key, int value) {
        DLinkNode node = cache.get(key);
        if (node == null) {
            DLinkNode newNode = new DLinkNode(key, value);
            cache.put(key, newNode);
            //添加到链表头部
            addToHead(newNode);
            ++size;
            //如果缓存已满，需要清理尾部节点
            if (size > capacity) {
                DLinkNode tail = removeTail();
                cache.remove(tail.key);
                --size;
            }
        } else {
            node.value = value;
            //移动到链表头部
            moveToHead(node);
        }
    }

    /**
     * 删除尾结点
     *
     * @return 返回删除的节点
     */
    private DLinkNode removeTail() {
        DLinkNode node = tail.prev;
        removeNode(node);
        return node;
    }

    /**
     * 删除节点
     *
     * @param node 需要删除的节点
     */
    private void removeNode(DLinkNode node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    /**
     * 把节点添加到链表头部
     *
     * @param node 要添加的节点
     */
    private void addToHead(DLinkNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    /**
     * 把节点移动到头部
     *
     * @param node 需要移动的节点
     */
    private void moveToHead(DLinkNode node) {
        removeNode(node);
        addToHead(node);
    }

    /**
     * 链表节点类
     */
    private static class DLinkNode {
        Integer key;
        Integer value;
        DLinkNode prev;
        DLinkNode next;

        DLinkNode() {
        }

        DLinkNode(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }
}
