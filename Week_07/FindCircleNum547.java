package com.xunlianying7.zuoye1;

public class FindCircleNum547 {

    // 并查集
    public int findCircleNum(int[][] M) {
        int[] parent = new int[M.length];
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                if (M[i][j] == 1 && i != j) unionAll();
            }
        }
        int count = 0;
        for (int j = 0; j < parent.length; j++) {
            if (parent[j] == -1) count++;
        }
        return count;
    }

    private void unionAll() {
    }
}
