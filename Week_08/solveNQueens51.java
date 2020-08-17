package com.xunlianying8.zuoye1;

import java.util.*;

public class solveNQueens51 {
    /**
     * 位运算 + 回溯
     * 时间复杂度:O() - %
     * 空间复杂度:O() - %
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
        boolean[] ocp90 = new boolean[n],
                ocp45 = new boolean[2 * n - 1],
                ocp135 = new boolean[2 * n - 1];
        char[][] map = new char[n][n];
        for (char[] temp : map) {
            Arrays.fill(temp, '.');
        }
        solve(0, n, map, results, ocp45, ocp90, ocp135);
        return results;
    }

    private void solve(int depth, int n, char[][] map, List<List<String>> results
            , boolean[] ocp45, boolean[] ocp90, boolean[] ocp135) {
        if (depth == n) {
            addSolution(map, results);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!ocp90[i] && !ocp45[depth + i] && !ocp135[i - depth + n - 1]) {
                ocp90[i] = true;
                ocp45[depth + i] = true;
                ocp135[i - depth + n - 1] = true;
                map[depth][i] = 'Q';
                solve(depth + 1, n, map, results, ocp45, ocp90, ocp135);
                ocp90[i] = false;
                ocp45[depth + i] = false;
                ocp135[i - depth + n - 1] = false;
                map[depth][i] = '.';
            }
        }
    }

    private void addSolution(char[][] bitMap, List<List<String>> results) {
        List<String> result = new ArrayList<String>();
        for (char[] bits : bitMap) {
            result.add(String.valueOf(bits));
        }
        results.add(result);
    }
}
