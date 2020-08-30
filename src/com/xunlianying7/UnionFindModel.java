package com.xunlianying7;

// 第二遍
// 并查集
public class UnionFindModel {

    public int count = 0;
    public int[] parent;

    public UnionFindModel(int n) {
        count = n;
        parent = new int[n];
        // 初始化parent数组里面的值。
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public void union(int p, int q) {
        int rootP = findRoot(p);
        int rootQ = findRoot(q);
        if (rootP == rootQ) return;
        parent[rootP] = rootQ;
        // 千万记住*2
        count--;
    }

    public int findRoot(int n) {
        while (n != parent[n]) {
            parent[n] = parent[parent[n]];
            n = parent[n];
        }
        return n;
    }
}
