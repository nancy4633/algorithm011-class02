package com.xunlianying8;

import java.util.*;

// 第三遍
// n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
// 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
// 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
public class solveNQueens51 {

    /**
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 43.74%
     * 优点:
     * 缺点:
     *
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens2(int n) {
        List<List<String>> results = new ArrayList<>();
        boolean[] ocp90 = new boolean[n];
        boolean[] ocp45 = new boolean[2 * n - 1];
        boolean[] ocp135 = new boolean[2 * n - 1];
        char[][] map = new char[n][n];
        // 记得要初始化
        for (char[] temp : map) {
            Arrays.fill(temp, '.');
        }
        getResults(0, n, map, results, ocp90, ocp45, ocp135);
        return results;
    }

    private void getResults(int depth, int n, char[][] bitMap, List<List<String>> results
            , boolean[] ocp90, boolean[] ocp45, boolean[] ocp135) {
        if (depth == n) {
            addSubResult(bitMap, results);
            return;
        }
        for (int i = 0; i < n; i++) {
            // 手动计算一下斜线的个数，尤其是ocp135
            if (!ocp90[i] && !ocp45[depth + i] && !ocp135[n - (depth + 1) + i]) {
                ocp90[i] = true;
                ocp45[depth + i] = true;
                ocp135[n - depth + i - 1] = true;
                bitMap[depth][i] = 'Q';
                getResults(depth + 1, n, bitMap, results, ocp90, ocp45, ocp135);
                ocp90[i] = false;
                ocp45[depth + i] = false;
                ocp135[n - depth + i - 1] = false;
                bitMap[depth][i] = '.';
            }
        }
    }

    /**
     * 位运算 + 回溯
     * 时间复杂度:O() - 63.30%
     * 空间复杂度:O() - 75.28%
     * 优点:
     * 缺点:
     *
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens1(int n) {
        num = n;
        result = new ArrayList<>();
        if (n == 0) return result;
        int col = 0, master = 0, slave = 0;
        Stack<Integer> stack = new Stack<>();
        backtrack(0, col, master, slave, stack);
        return result;
    }

    private List<List<String>> result;
    private int num;

    private void backtrack(int row, int col, int master, int slave, Stack<Integer> stack) {
        if (row == num) {
            List<String> board = convert2board(stack, num);
            result.add(board);
            return;
        }
        for (int i = 0; i < num; i++) {
            if (((col >> i) & 1) == 0 && ((master >> (row + i)) & 1) == 0 && ((slave >> (row - i + num - 1)) & 1) == 0) {
                stack.add(i);
                col ^= (1 << i);
                master ^= (1 << (row + i));
                slave ^= (1 << (row - i + num - 1));
                backtrack(row + 1, col, master, slave, stack);
                slave ^= (1 << (row - i + num - 1));
                master ^= (1 << (row + i));
                col ^= (1 << i);
                stack.pop();
            }
        }
    }

    private List<String> convert2board(Stack<Integer> stack, int n) {
        List<String> board = new ArrayList<>();
        for (Integer num : stack) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < n; i++) {
                stringBuilder.append(".");
            }
            stringBuilder.replace(num, num + 1, "Q");
            board.add(stringBuilder.toString());
        }
        return board;
    }


    private void addSubResult(char[][] bitMap, List<List<String>> results) {
        List<String> result = new ArrayList<String>();
        for (char[] bits : bitMap) {
            result.add(String.valueOf(bits));
        }
        results.add(result);
    }

    /**
     * DFS
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 45.42%
     * 优点:
     * 缺点:
     *
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens3(int n) {
        chessBoard = new char[n][n];
        for (char[] ch : chessBoard) {
            Arrays.fill(ch, '.');
        }
        cols = new boolean[n];
        mainDiag = new boolean[2 * n + 1];
        deputyDiag = new boolean[2 * n - 1];
        dfs(n, 0);
        return res;
    }

    List<List<String>> res = new ArrayList<>();
    char[][] chessBoard;
    boolean[] cols;
    boolean[] mainDiag;
    boolean[] deputyDiag;

    public void dfs(int n, int len) {
        if (n == len) {
            List<String> oneRes = new ArrayList<>();
            for (char[] ch : chessBoard) {
                oneRes.add(new String(ch));
            }
            res.add(oneRes);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!cols[i] && !mainDiag[n + len - i] && !deputyDiag[len + i]) {
                chessBoard[len][i] = 'Q';
                cols[i] = true;
                mainDiag[n + len - i] = true;
                deputyDiag[len + i] = true;
                dfs(n, len + 1);
                chessBoard[len][i] = '.';
                cols[i] = false;
                mainDiag[n + len - i] = false;
                deputyDiag[len + i] = false;
            }
        }
    }
}
