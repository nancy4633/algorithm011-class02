package com.xunlianying1;

public class MinStack {
    Node head;

    public void push(int x) {
        if (head == null) {
            head = new Node(x, x);
        } else {
            Node node = new Node(x, Math.min(x, head.min));
            node.next = head;
            head = node;
        }
    }

    public void pop() {
        if (head != null) head = head.next;
    }

    public int top() {
        if (head != null) return head.value;
        return -1;
    }

    public int getMin() {
        if (head != null) return head.min;
        return -1;
    }
}

class Node {
    int value;
    int min;
    Node next;

    public Node() {
        this.value = 0;
        this.min = 0;
        next = null;
    }

    public Node(int value, int min) {
        this.value = value;
        this.min = min;
        next = null;
    }
}
