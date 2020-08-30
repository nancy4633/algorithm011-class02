package com.xunlianying7;

public class FindCircleNum547 {

    // 并查集
    public int findCircleNum(int[][] M) {
        int[] parent = new int[M.length];
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                if (M[i][j] == 1 && i != j) unionAll(parent, i, j);
            }
        }
        int count = 0;
        for (int j = 0; j < parent.length; j++) {
            if (parent[j] == -1) count++;
        }
        return count;
    }

    private int findRoot(int parent[], int i) {
        if (parent[i] == -1) return i;
        return findRoot(parent, parent[i]);
    }

    private void unionAll(int parent[], int x, int y) {
        int xset = findRoot(parent, x);
        int yset = findRoot(parent, y);
        if (xset != yset) parent[xset] = yset;
    }
}
