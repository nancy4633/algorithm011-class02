package com.xunlianying8.zuoye1;

import java.util.HashMap;
import java.util.Map;

// 第一遍
// 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
// 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
// 写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
// 进阶:
// 你是否可以在 O(1) 时间复杂度内完成这两种操作？
// 这里有一点我混淆了
// LRUcache是一整条链，它只有一个head和一个tail
// 和DLinkedNode不一样，每一个节点的前后叫做：prev和next，这个有n个，
// 而LRUcache的head和tail有其特殊含义，它不存key-value值
// 就算LRUcache为空时，它依然有head和tail，且此时size=0
// 也就是说head和tail不计入长度
class LRUCache146 {
    // 为了存取时间高效，key还是key，value是DLinkedNode对象
    // 一定要记得初始化hashmap。。。
    private Map<Integer, DLinkNode> cache = new HashMap<>();
    private int size;
    private int capacity;
    private DLinkNode head, tail;

    /**
     * 构造方法 - 创建一个大小为capacity的空LRUcache
     * 里面的数据类型都是DLinkedNode，保存了前后的DLinkedNode对象
     * 还有key value值。
     *
     * @param capacity
     */
    public LRUCache146(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head = new DLinkNode();
        tail = new DLinkNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkNode node = cache.get(key);
        if (node == null) return -1;
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DLinkNode node = cache.get(key);
        if (node == null) {
            DLinkNode newNode = new DLinkNode(key, value);
            cache.put(key, newNode);
            addToHead(newNode);
            ++size;
            if (size > capacity) {
                DLinkNode tail = removeTail();
                cache.remove(tail.key);
                --size;
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }

    private DLinkNode removeTail() {
        DLinkNode node = tail.prev;
        removeNode(node);
        return node;
    }

    private void removeNode(DLinkNode node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    private void addToHead(DLinkNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void moveToHead(DLinkNode node) {
        removeNode(node);
        addToHead(node);
    }
}

class DLinkNode {
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