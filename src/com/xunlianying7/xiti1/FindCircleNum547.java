package com.xunlianying7.xiti1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class FindCircleNum547 {
    /**
     * DFS
     * 时间复杂度:O(n^2) - 99.96% - 整个矩阵都要被遍历，大小为n^2
     * 空间复杂度:O(n) - 54.13% - visited数组的大小
     * 优点: 我最擅长～没想到是最快的～
     * 缺点: 没有缺点，太完美
     *
     * @param M
     * @return
     */
    public int findCircleNum(int[][] M) {
        int[] visited = new int[M.length];
        int count = 0;
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 0) {
                dfs(M, visited, i);
                count++;
            }
        }
        return count;
    }

    public void dfs(int[][] M, int[] visited, int i) {
        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1 && visited[j] == 0) {
                visited[j] = 1;
                dfs(M, visited, j);
            }
        }
    }

    /**
     * BFS
     * 时间复杂度:O(n^2) - 19.46% - 整个矩阵都要被访问
     * 空间复杂度:O(n) - 75.21% - queue和visited数组的大小
     * 优点:
     * 缺点:
     *
     * @param M
     * @return
     */
    public int findCircleNum11(int[][] M) {
        int[] visited = new int[M.length];
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 0) {
                queue.add(i);
                while (!queue.isEmpty()) {
                    int s = queue.remove();
                    visited[s] = 1;
                    for (int j = 0; j < M.length; j++) {
                        if (M[s][j] == 1 && visited[j] == 0) queue.add(j);
                    }
                }
                count++;
            }
        }
        return count;
    }

    /**
     * 并查集
     * 时间复杂度:O(n^3) - 10.69% - 访问整个矩阵一次，并查集操作需要最坏O(n)的时间
     * 空间复杂度:O(n) - 33.05% - parent大小为n
     * 优点: 一旦记住并查集模版，解题就很简单
     * 缺点: 原以为速度会很快，居然是3次方，也是出乎意料
     *
     * @param M
     * @return
     */
    public int findCircleNum111(int[][] M) {
        int[] parent = new int[M.length];
        Arrays.fill(parent, -1);
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                if (M[i][j] == 1 && i != j) union111(parent, i, j);
            }
        }
        int count = 0;
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == -1) count++;
        }
        return count;
    }

    int find111(int parent[], int i) {
        if (parent[i] == -1) return i;
        return find111(parent, parent[i]);
    }

    void union111(int parent[], int x, int y) {
        int xset = find111(parent, x);
        int yset = find111(parent, y);
        if (xset != yset) parent[xset] = yset;
    }

}
