package com.xunlianying3;

class Node {
    /**
     * 当前得到的字符串
     */
    public String res;
    /**
     * 剩余左括号数量
     */
    public int left;
    /**
     * 剩余右括号数量
     */
    public int right;

    public Node(String str, int left, int right) {
        this.res = str;
        this.left = left;
        this.right = right;
    }
}