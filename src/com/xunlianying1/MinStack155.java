package com.xunlianying1;

// 第一遍
// 理解： 最上面的节点是head，head的next指向它下面的节点。head节点的min是所有stack中的最小值
// 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
// push(x) —— 将元素 x 推入栈中。
// pop() —— 删除栈顶的元素。
// top() —— 获取栈顶元素。
// getMin() —— 检索栈中的最小元素。
// 时间复杂度:O() - 99.88%
// 空间复杂度:O() - 13.39%
// 优点:
// 缺点:
class MinStack155 {
    Node155 head;

    public void push(int x) {
        if (null == head) {
            head = new Node155(x, x);
        } else {
            Node155 n = new Node155(x, Math.min(x, head.min));
            n.next = head;
            head = n;
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
        if (null != head) return head.min;
        return -1;
    }
}

class Node155 {
    int value;
    int min;
    Node155 next;

    Node155(int x, int min) {
        this.value = x;
        this.min = min;
        next = null;
    }
}