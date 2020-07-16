package com.fourth.zongjie;

public class Node {
    /**
     * 当前得到的字符串
     */
    String res;
    /**
     * 剩余左括号数量
     */
    int left;
    /**
     * 剩余右括号数量
     */
    int right;

    int x;
    int y;

    public Node(String str, int left, int right) {
        this.res = str;
        this.left = left;
        this.right = right;
    }

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}