package com.xunlianying1;

// 第一遍
//设计实现双端队列。
//你的实现需要支持以下操作：
//MyCircularDeque(k)：构造函数,双端队列的大小为k。
//insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true。
//insertLast()：将一个元素添加到双端队列尾部。如果操作成功返回 true。
//deleteFront()：从双端队列头部删除一个元素。 如果操作成功返回 true。
//deleteLast()：从双端队列尾部删除一个元素。如果操作成功返回 true。
//getFront()：从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
//getRear()：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1。
//isEmpty()：检查双端队列是否为空。
//isFull()：检查双端队列是否满了。
// 思路
// 主要还是判断条件，是我的弱势，需要加强
public class MyCircularDeque641 {
    int[] myqueue;
    int front; // 队头指针
    int rear; // 队尾指针
    int size; // 队列当前的大小
    int capacity; // 队列的容量

    public MyCircularDeque641(int k) {
        this.myqueue = new int[k];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
        this.capacity = k;
    }

    public boolean insertFront(int value) {
        if (rear == front && size == capacity) return false; //如果队列满，插入失败，// why &&，队头==队尾 不是满吗？size=capacity不是满吗？
        else {
            front = (front - 1 + capacity) % capacity; // 先 +capacity 再 %capacity，不可以直接用front - 1，这样可能会出来负数，关键是负数还是有效的，
            myqueue[front] = value;
            size++;
            return true;
        }
    }

    public boolean insertLast(int value) {
        if (rear == front && size == capacity) return false; //如果队列满，插入失败，// why &&，队头==队尾 不是满吗？size=capacity不是满吗？
        else {
            myqueue[rear] = value;
            rear = (rear + 1 + capacity) % capacity;
            size++;
            return true;
        }

    }

    public boolean deleteFront() {
        if (rear == front && size == 0) return false; // 所以满和空都要判断 rear == front，单纯判断size是不可以的？
        else {
            front = (front + 1) % capacity;
            size--;
            return true;
        }

    }

    public boolean deleteLast() {
        if (rear == front && size == 0) return false;
        else {
            rear = (rear - 1 + capacity) % capacity; // 防止负数
            size--;
            return true;
        }

    }

    public int getFront() {
        if (rear == front && size == 0) return -1;
        else {
            int frontE = myqueue[front];
            return frontE;
        }
    }

    public int getRear() {
        if (rear == front && size == 0) return -1;
        else {
            int rearE = myqueue[(rear - 1 + capacity) % capacity];
            return rearE;
        }
    }

    /**
     * Checks whether the circular deque is empty or not.
     */
    public boolean isEmpty() {
        return rear == front && size == 0;
    }

    /**
     * Checks whether the circular deque is full or not.
     */
    public boolean isFull() {
        return rear == front && size == capacity;
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */