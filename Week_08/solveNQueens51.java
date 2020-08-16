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
        this.N = n;
        results = new ArrayList<>();
        chars = new char[n];
        Arrays.fill(chars, '.');
        records = new int[n];
        if (n >= 1 && n <= 32) {
            colMap = new HashMap<>();
            for (int i = 0; i < n; i++) {
                colMap.put(1 << (n - i - 1), i);
            }
            limit = n == 32 ? -1 : ((1 << n) - 1);
            process(0, 0, 0, 0);
        }
        return results;
    }

    private int N;
    private List<List<String>> results;
    private char[] chars;
    private int[] records;
    private Map<Integer, Integer> colMap;
    private int limit;//低N位为1,其余位为0

    public void process(int index, int leftMask, int colMask, int rightMask) {
        if (colMask == limit) {//所有列都已被占，说明放置结束
            List<String> list = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                chars[records[i]] = 'Q';
                list.add(new String(chars));
                chars[records[i]] = '.';
            }
            results.add(list);
        }
        int pos = limit & ~(leftMask | colMask | rightMask);//得到哪些位置可以放皇后
        while (pos != 0) {
            //在mostRightOne的位置放皇后
            int mostRightOne = pos & (~pos + 1);
            records[index] = colMap.get(mostRightOne);
            pos ^= mostRightOne;//pos -= mostRightOne
            process(index + 1, (leftMask | mostRightOne) << 1,
                    colMask | mostRightOne, (rightMask | mostRightOne) >>> 1);
        }
    }

}
