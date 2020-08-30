package com.xunlianying4;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

// 一条基因序列由一个带有8个字符的字符串表示，其中每个字符都属于 "A", "C", "G", "T"中的任意一个。
// 假设我们要调查一个基因序列的变化。一次基因变化意味着这个基因序列中的一个字符发生了变化。
// 例如，基因序列由"AACCGGTT" 变化至 "AACCGGTA" 即发生了一次基因变化。
// ***与此同时，每一次基因变化的结果，都需要是一个合法的基因串，即该结果属于一个基因库。
// 现在给定3个参数 — start, end, bank，分别代表起始基因序列，目标基因序列及基因库，
// ***请找出能够使起始基因序列变化为目标基因序列所需的最少变化次数。如果无法实现目标变化，请返回 -1
// 思路：
// 回溯不论是单独使用，还是配合DFS、BFS都可以提速。
public class MinMutation433 {

    /***
     * DFS + 回溯
     * 时间复杂度：O(n) - 100%
     * 空间复杂度：O(h) 树的深度 - 20%
     * 优点：
     * 缺点：
     * @param start
     * @param end
     * @param bank
     * @return
     */
    public int minMutation4(String start, String end, String[] bank) {
        if (start.equals(end)) return 0;
        HashSet<String> visited = new HashSet<String>();
        dfs4(start, end, bank, 0, visited);
        return count4 == Integer.MAX_VALUE ? -1 : count4;
    }

    private void dfs4(String start, String end, String[] bank, int count, HashSet<String> visited) {
        // terminator
        if (start.equals(end)) {
            count4 = Math.min(count4, count);
            return;
        }
        // process
        for (int i = 0; i < bank.length; i++) {
            int diff = 0;
            for (int j = 0; j < 8; j++) {
                if (bank[i].charAt(j) != start.charAt(j)) diff++; // 这里比较的是start，切记！！！
                if (diff > 1) break;
            }
            if (diff == 1 && !visited.contains(bank[i])) {
                //drill down
                visited.add(bank[i]);
                dfs4(bank[i], end, bank, count + 1, visited);
                // reverse state
                visited.remove(bank[i]);
            }
        }
    }

    private int count4 = Integer.MAX_VALUE;

    /***
     * DFS + 回溯
     * 时间复杂度：O(n) - 100%
     * 空间复杂度：O(h) 树的深度 - 20%
     * 优点：代码更简洁
     * 缺点：
     * @param start
     * @param end
     * @param bank
     * @return
     */
    public int minMutation3(String start, String end, String[] bank) {
        HashSet<String> visited = new HashSet<>();
        dfs3(start, end, bank, visited, 0);
        return count3 == Integer.MAX_VALUE ? -1 : count3;
    }

    private int count3 = Integer.MAX_VALUE;

    private void dfs3(String start, String end, String[] bank, HashSet<String> visited, int count) {
        if (start.equals(end)) {                                    // terminator
            count3 = Math.min(count3, count);
            return;
        }
        for (int i = 0; i < bank.length; i++) {                     // process
            int diff = 0;
            if (match(bank[i], start) && !visited.contains(bank[i])) {
                visited.add(bank[i]);                                // subprocess
                dfs3(bank[i], end, bank, visited, count + 1); // drill down
                visited.remove(bank[i]);                             // reverse state
            }
        }
    }

    /**
     * BFS - 遍历
     * 时间复杂度：O() - 100%
     * 空间复杂度：O() - 20%
     * 优点：
     * 缺点：
     *
     * @param start
     * @param end
     * @param bank
     * @return
     */
    public int minMutation33(String start, String end, String[] bank) {
        if (start.equals(end)) return 0;
        Stack<String> stack = new Stack<>();

        int count = -1;

        while (!stack.isEmpty()) {

        }
        return -1;
    }

    /***
     * 回溯 - LinkedList
     * 时间复杂度：O() - 100%
     * 空间复杂度：O() - 20%
     * 优点：
     * 缺点：
     *
     * @param start
     * @param end
     * @param bank
     * @return
     */
    public int minMutation11(String start, String end, String[] bank) {
        int result11 = 0, index = 1, level = 1;
        HashSet<String> visited = new HashSet<>();
        LinkedList<String> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            start = queue.poll();
            bfs11(start, queue, visited, bank);
            for (int i = 0; i < queue.size(); i++) {
                if (queue.get(i).equals(end)) {//因为linkedList支持按照index获取元素，所以此处不使用queue或者deque
                    result11 = level;
                    return result11 == 0 ? -1 : result11;
                }
            }
            if (--index == 0) {
                index = queue.size();
                level++;
            }
        }
        return result11 == 0 ? -1 : result11;
    }

    /***
     * 遍历bank，得到与start只差一个字符的字符串，然后继续遍历
     * @param start
     * @param linkedList
     * @param visited
     * @param bank
     */
    private void bfs11(String start, LinkedList<String> linkedList, HashSet<String> visited, String[] bank) {
        for (int i = 0; i < bank.length; i++) {
            if (visited.contains(bank[i])) continue;
            if (match(bank[i], start)) {
                linkedList.add(bank[i]);
                visited.add(bank[i]);
            }
        }
    }

    /***
     * 回溯 - 数组
     * 时间复杂度：O() - 100%
     * 空间复杂度：O() - 20%
     * 优点：
     * 缺点：
     * @param start
     * @param end
     * @param bank
     * @return
     */
    public int minMutation111(String start, String end, String[] bank) {
        int res = bfs111(start, end, bank, new boolean[bank.length]);
        if (res > bank.length) return -1;
        return res;
    }

    private int bfs111(String current, String end, String[] bank, boolean[] used) {
        if (current.equals(end)) return 0;
        int min = bank.length + 1;
        for (int i = 0; i < bank.length; i++) {
            if (!used[i] && match(current, bank[i])) {
                used[i] = true;
                min = Math.min(1 + bfs111(bank[i], end, bank, used), min);
                used[i] = false;
            }
        }
        return min;
    }

    private boolean match(String a, String b) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            if (a.charAt(i) != b.charAt(i)) count++;
            if (count > 1) return false;
        }
        return count == 1;
    }
}
