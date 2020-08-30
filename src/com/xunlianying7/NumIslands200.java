package com.xunlianying7;

import java.util.LinkedList;
import java.util.Queue;

public class NumIslands200 {

    /**
     * DFS
     * 时间复杂度:O(m*n) - 97.60% - 其中m和n分别为行数和列数
     * 空间复杂度:O(m*n) - 66.52% - 最坏情况下，整个网格均为陆地，深度优先搜索的深度达到m*n
     * 优点:
     * 缺点:
     *
     * @param grid
     * @return
     */
    public int numIslands1(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    ++num_islands;
                    dfs1(grid, r, c);
                }
            }
        }
        return num_islands;
    }

    void dfs1(char[][] grid, int r, int c) {
        int nr = grid.length;
        int nc = grid[0].length;
        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') return;
        grid[r][c] = '0';
        dfs1(grid, r - 1, c);
        dfs1(grid, r + 1, c);
        dfs1(grid, r, c - 1);
        dfs1(grid, r, c + 1);
    }

    /**
     * BFS
     * 时间复杂度:O(m*n) - 21.34% - 其中m,n分别为行数和列数
     * 空间复杂度:O(min(m,n)) - 37.98% - 在最坏情况下，整个网格均为陆地，队列的大小可以达到min(M,N)
     * 优点:
     * 缺点:
     *
     * @param grid
     * @return
     */
    public int numIslands11(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    ++num_islands;
                    grid[r][c] = '0';
                    Queue<Integer> neighbors = new LinkedList<>();
                    neighbors.add(r * nc + c);
                    while (!neighbors.isEmpty()) {
                        int id = neighbors.remove();
                        int row = id / nc;
                        int col = id % nc;
                        if (row - 1 >= 0 && grid[row - 1][col] == '1') {
                            neighbors.add((row - 1) * nc + col);
                            grid[row - 1][col] = '0';
                        }
                        if (row + 1 < nr && grid[row + 1][col] == '1') {
                            neighbors.add((row + 1) * nc + col);
                            grid[row + 1][col] = '0';
                        }
                        if (col - 1 >= 0 && grid[row][col - 1] == '1') {
                            neighbors.add(row * nc + col - 1);
                            grid[row][col - 1] = '0';
                        }
                        if (col + 1 < nc && grid[row][col + 1] == '1') {
                            neighbors.add(row * nc + col + 1);
                            grid[row][col + 1] = '0';
                        }
                    }
                }
            }
        }
        return num_islands;
    }

    class UnionFind {
        int count;
        int[] parent;
        int[] rank;

        public UnionFind(char[][] grid) {
            count = 0;
            int m = grid.length;
            int n = grid[0].length;
            parent = new int[m * n];
            rank = new int[m * n];
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (grid[i][j] == '1') {
                        parent[i * n + j] = i * n + j;
                        ++count;
                    }
                    rank[i * n + j] = 0;
                }
            }
        }

        public int find(int i) {
            if (parent[i] != i) parent[i] = find(parent[i]);
            return parent[i];
        }

        public void union(int x, int y) {
            int rootx = find(x);
            int rooty = find(y);
            if (rootx != rooty) {
                if (rank[rootx] > rank[rooty]) {
                    parent[rooty] = rootx;
                } else if (rank[rootx] < rank[rooty]) {
                    parent[rootx] = rooty;
                } else {
                    parent[rooty] = rootx;
                    rank[rootx] += 1;
                }
                --count;
            }
        }

        public int getCount() {
            return count;
        }
    }

    /**
     * 并查集
     * 时间复杂度:O(m*n*α(m*n)) - 21.34% - 其中m和n分别为行数和列数。
     * 注意当使用路径压缩（见find函数）和按秩合并（见数组rank）实现并查集时，单次操作的时间复杂度为α(MN)，
     * 其中α(x)为反阿克曼函数，当自变量x的值在人类可观测的范围内（宇宙中粒子的数量）时，函数α(x) 的值不会超过5，因此也可以看成是常数时间复杂度。
     * 空间复杂度:O(m*n) - 29.88% - 这是并查集需要使用的空间
     * 优点:
     * 缺点:
     *
     * @param grid
     * @return
     */
    public int numIslands111(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;
        UnionFind uf = new UnionFind(grid);
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    grid[r][c] = '0';
                    if (r - 1 >= 0 && grid[r - 1][c] == '1') uf.union(r * nc + c, (r - 1) * nc + c);
                    if (r + 1 < nr && grid[r + 1][c] == '1') uf.union(r * nc + c, (r + 1) * nc + c);
                    if (c - 1 >= 0 && grid[r][c - 1] == '1') uf.union(r * nc + c, r * nc + c - 1);
                    if (c + 1 < nc && grid[r][c + 1] == '1') uf.union(r * nc + c, r * nc + c + 1);
                }
            }
        }
        return uf.getCount();
    }
}
