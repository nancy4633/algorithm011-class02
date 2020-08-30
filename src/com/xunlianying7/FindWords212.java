package com.xunlianying7;

import java.util.*;

// 第二遍
// 给定一个二维网格board和一个字典中的单词列表words，找出所有同时在二维网格和字典中出现的单词。
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
// 说明:
// 你可以假设所有输入都由小写字母a-z组成。
// 提示:
// 你需要优化回溯算法以通过更大数据量的测试。你能否早点停止回溯？
// 如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯。
// 什么样的数据结构可以有效地执行这样的操作？散列表是否可行？为什么？前缀树如何？如果你想学习如何实现一个基本的前缀树，请先查看这个问题：实现Trie（前缀树）。
class FindWords212 {

    /**
     * Trie + 数组Trie1[]
     * 时间复杂度:O() - 92.95%
     * 空间复杂度:O() - 51.51%
     * 优点:
     * 缺点:
     *
     * @param board
     * @param words
     * @return
     */
    public List<String> findWords1(char[][] board, String[] words) {
        Trie1 myTrie = new Trie1();
        for (String s : words) {
            myTrie.insert(s);
        }
        Set<String> result = new HashSet<>();
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                find1(board, visited, i, j, m, n, result, myTrie);
            }
        }
        return new LinkedList<String>(result);
    }

    private void find1(char[][] board, boolean[][] visited, int i, int j, int m, int n, Set<String> result, Trie1 cur) {
        // visited[i][j]属于剪枝
        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j]) return;
        // 之后的所有判断都是子节点！！！这里我写错了。
        cur = cur.next[board[i][j] - 'a'];
        visited[i][j] = true;
        if (cur == null) {
            // 谨慎
            visited[i][j] = false;
            return;
        }
        if (cur.isEnd) result.add(cur.val);
        find1(board, visited, i + 1, j, m, n, result, cur);
        find1(board, visited, i, j + 1, m, n, result, cur);
        find1(board, visited, i, j - 1, m, n, result, cur);
        find1(board, visited, i - 1, j, m, n, result, cur);
        // 回溯的必要操作：restore state
        visited[i][j] = false;
    }

    /**
     * Trie tree + HashMap<Character, Trie1> - 没觉得方法特别好，比较晦涩，暂时没有花时间去理解，而且时间复杂度并不好。
     * 时间复杂度:O() - 61.62%
     * 空间复杂度:O() - 38.63%
     * 优点:
     * 缺点:
     *
     * @param board
     * @param words
     * @return
     */
    public List<String> findWords11(char[][] board, String[] words) {
        Trie1 root = new Trie1();
        for (String word : words) {
            Trie1 node = root;
            for (Character letter : word.toCharArray()) {
                if (node.nextMap.containsKey(letter)) {
                    node = node.nextMap.get(letter);
                } else {
                    Trie1 newNode = new Trie1();
                    node.nextMap.put(letter, newNode);
                    node = newNode;
                }
            }
            node.val = word;  // store words in Trie
        }
        this._board = board;
        for (int row = 0; row < board.length; ++row) {
            for (int col = 0; col < board[row].length; ++col) {
                if (root.nextMap.containsKey(board[row][col])) backtracking11(row, col, root);
            }
        }
        return this._result;
    }

    char[][] _board = null;
    ArrayList<String> _result = new ArrayList<String>();

    private void backtracking11(int row, int col, Trie1 parent) {
        Character letter = this._board[row][col];
        Trie1 currNode = parent.nextMap.get(letter);
        if (currNode.val != null) {
            this._result.add(currNode.val);
            currNode.val = null;
        }
        this._board[row][col] = '#';
        int[] rowOffset = {-1, 0, 1, 0};
        int[] colOffset = {0, 1, 0, -1};
        for (int i = 0; i < 4; ++i) {
            int newRow = row + rowOffset[i];
            int newCol = col + colOffset[i];
            if (newRow < 0 || newRow >= this._board.length || newCol < 0 || newCol >= this._board[0].length) continue;
            if (currNode.nextMap.containsKey(this._board[newRow][newCol])) backtracking11(newRow, newCol, currNode);
        }
        this._board[row][col] = letter;
        if (currNode.nextMap.isEmpty()) parent.nextMap.remove(letter);
    }

    public List<String> findWords(char[][] board, String[] words) {
        Set<String> results = new HashSet<>();
        return new ArrayList<>(results);
    }

}

class Trie1 {
    public String val;
    public boolean isEnd;
    public Trie1[] next;
    public HashMap<Character, Trie1> nextMap;

    Trie1() {
        isEnd = false;
        next = new Trie1[26];
        val = null;
        nextMap = new HashMap<Character, Trie1>();
    }

    public void insert(String s) {
        Trie1 cur = this;
        for (char c : s.toCharArray()) {
            if (cur.next[c - 'a'] == null) cur.next[c - 'a'] = new Trie1();
            cur = cur.next[c - 'a'];
        }
        cur.isEnd = true;
        cur.val = s;
    }
}
