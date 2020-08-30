package com.xunlianying8;

import java.util.HashMap;

// 第二遍
// 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
// 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
// 写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
// 进阶:
// 你是否可以在 O(1) 时间复杂度内完成这两种操作？
public class LRUCache {
    DLinkedNode2 head;
    DLinkedNode2 tail;
    Integer capacity;
    Integer size;
    HashMap<Integer, DLinkedNode2> hashMap = new HashMap<Integer, DLinkedNode2>();

    public LRUCache(int capacity) {
        head = new DLinkedNode2(); // 因为head和tail，所以DLinkedNode的构造函数一定有一个
        tail = new DLinkedNode2();
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
        this.size = 0;
    }

    public int get(int key) {
        DLinkedNode2 node2 = hashMap.get(key);
        if (node2 == null) return -1;
        movetohead(node2); // 这个方法总忘记写！！！！
        return node2.value;
    }

    public void put(int key, int value) {
        DLinkedNode2 node2 = hashMap.get(key);
        if (node2 == null) {
            DLinkedNode2 newnode = new DLinkedNode2(key, value);
            hashMap.put(key, newnode);
            addtohead(newnode);
            size++;
            if (size > capacity) {
                DLinkedNode2 tail = removetail();
                hashMap.remove(tail.key);
                size--;
            }
        } else {
            node2.value = value;
            movetohead(node2);
        }
    }

    private DLinkedNode2 removetail() {
        DLinkedNode2 node2 = tail.prev;
        removenode(node2);
        return node2;
    }

    private void removenode(DLinkedNode2 node2) {
        node2.prev.next = node2.next;
        node2.next.prev = node2.prev;
    }

    private void addtohead(DLinkedNode2 node2) {
        node2.next = head.next;
        node2.prev = head;
        head.next = node2;
        head.next.prev = node2;
    }

    private void movetohead(DLinkedNode2 node2) {
        removenode(node2);
        addtohead(node2);
    }
}

class DLinkedNode2 {
    Integer key;
    Integer value;
    DLinkedNode2 prev;
    DLinkedNode2 next;

    public DLinkedNode2() {

    }

    public DLinkedNode2(Integer key, Integer value) {
        this.key = key;
        this.value = value;
    }
}