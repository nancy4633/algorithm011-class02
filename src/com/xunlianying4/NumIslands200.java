package com.xunlianying4;

import java.util.LinkedList;

// 第一遍 解法一&二的原理一样，只是解法二用了全局变量，所以空间要求更低。
// 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
// 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
// 此外，你可以假设该网格的四条边均被水包围。
public class NumIslands200 {
    /**
     * DFS
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 87.85%
     * 优点:
     * 缺点:
     *
     * @param grid
     * @return
     */
    public int numIslands1(char[][] grid) {
        int count = 0;
        if (grid == null || grid.length == 0) return count;
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    dfs1(i, j, grid);
                    count++;
                }
            }
        }
        return count;
    }

    void dfs1(int x, int y, char[][] grid) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == '0') return;
        grid[x][y] = '0';
        dfs1(x - 1, y, grid);
        dfs1(x + 1, y, grid);
        dfs1(x, y - 1, grid);
        dfs1(x, y + 1, grid);
    }

    /**
     * DFS - dir二维数组更适合扩展，比如不紧紧四个方向。也可以是6个、8个。
     * 时间复杂度:O() - 97.63%
     * 空间复杂度:O() - 96.30%
     * 优点:
     * 缺点:
     *
     * @param grid
     * @return
     */
    public int numIslands2(char[][] grid) {
        int result = 0;
        if (grid.length == 0 || grid[0].length == 0) return result;
        int[][] dir = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs2(grid, i, j, dir);
                    result++;
                }
            }
        }
        return result;
    }

    void dfs2(char[][] grid, int i, int j, int[][] dir) {
        grid[i][j] = '0';
        for (int k = 0; k < 4; k++) {
            int x = dir[k][0] + i;
            int y = dir[k][1] + j;
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == '1') dfs2(grid, x, y, dir);
        }
    }

    /**
     * DFS
     * 时间复杂度:O() - 97.63%
     * 空间复杂度:O() - 98.51%
     * 优点:
     * 缺点:
     *
     * @param grid
     * @return
     */
    public int numIslands3(char[][] grid) {
        rows = grid.length;
        if (rows == 0) return 0;
        cols = grid[0].length;
        this.grid = grid;
        marked = new boolean[rows][cols];
        int count = 0;
        for (int a = 0; a < rows; a++) {
            for (int b = 0; b < cols; b++) {
                if (!marked[a][b] && grid[a][b] == '1') { // 没被标记过 & 是陆地
                    count++;
                    dfs3(a, b);
                }
            }
        }
        return count;
    }

    private static final int[][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    private boolean[][] marked;
    private int rows;
    private int cols;
    private char[][] grid;

    private void dfs3(int a, int b) {
        marked[a][b] = true;
        for (int k = 0; k < 4; k++) {
            int newX = a + directions[k][0];
            int newY = b + directions[k][1];
            if (inArea3(newX, newY) && grid[newX][newY] == '1' && !marked[newX][newY]) dfs3(newX, newY);
        }
    }

    private boolean inArea3(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    /**
     * 广度优先遍历
     * 时间复杂度:O() - 19.14%
     * 空间复杂度:O() - 99.30%
     * 优点:
     * 缺点:
     *
     * @param grid
     * @return
     */
    public int numIslands4(char[][] grid) {
        int[][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        rows = grid.length;
        if (rows == 0) return 0;
        cols = grid[0].length;
        boolean[][] marked = new boolean[rows][cols];
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!marked[i][j] && grid[i][j] == '1') {
                    count++;
                    LinkedList<Integer> queue = new LinkedList<>();
                    queue.addLast(i * cols + j);
                    marked[i][j] = true;
                    while (!queue.isEmpty()) {
                        int cur = queue.removeFirst();
                        int curX = cur / cols;
                        int curY = cur % cols;
                        for (int k = 0; k < 4; k++) {
                            int newX = curX + directions[k][0];
                            int newY = curY + directions[k][1];
                            if (inArea4(newX, newY) && grid[newX][newY] == '1' && !marked[newX][newY]) {
                                queue.addLast(newX * cols + newY);
                                marked[newX][newY] = true;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    private boolean inArea4(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    /**
     * 并查集
     * 时间复杂度:O() - 19.14%
     * 空间复杂度:O() - 99.08%
     * 优点:
     * 缺点:
     *
     * @param grid
     * @return
     */
    public int numIslands5(char[][] grid) {
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
}
