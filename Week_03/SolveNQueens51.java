package com.xunlianying3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 第一遍
// n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
public class SolveNQueens51 {

    /**
     * DFS + 回溯 + 剪枝
     * 时间复杂度:O() - 100.00%
     * 空间复杂度:O() - 98.22%
     * 优点:
     * 缺点:
     *
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens1(int n) {
        chessBoard = new char[n][n];
        for (char[] ch : chessBoard) {
            Arrays.fill(ch, '.');
        }
        cols = new boolean[n];
        mainDiag = new boolean[2 * n + 1];
        deputyDiag = new boolean[2 * n - 1];
        dfs1(n, 0);
        return res;
    }

    List<List<String>> res = new ArrayList<>();
    char[][] chessBoard;
    boolean[] cols;
    boolean[] mainDiag;
    boolean[] deputyDiag;

    public void dfs1(int n, int len) {
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
                dfs1(n, len + 1);
                chessBoard[len][i] = '.';
                cols[i] = false;
                mainDiag[n + len - i] = false;
                deputyDiag[len + i] = false;
            }
        }
    }
}
