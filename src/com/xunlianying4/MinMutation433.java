package com.xunlianying4;

import java.util.HashSet;
import java.util.LinkedList;

// 第三遍 解法一
// 一条基因序列由一个带有8个字符的字符串表示，其中每个字符都属于 "A", "C", "G", "T"中的任意一个。
// 假设我们要调查一个基因序列的变化。一次基因变化意味着这个基因序列中的一个字符发生了变化。
// 例如，基因序列由"AACCGGTT" 变化至 "AACCGGTA" 即发生了一次基因变化。
// ***与此同时，每一次基因变化的结果，都需要是一个合法的基因串，即该结果属于一个基因库。
// 现在给定3个参数 — start, end, bank，分别代表起始基因序列，目标基因序列及基因库，
// ***请找出能够使起始基因序列变化为目标基因序列所需的最少变化次数。如果无法实现目标变化，请返回 -1
public class MinMutation433 {
    /**
     * DFS + 回溯
     * 时间复杂度：O(n) - 100%
     * 空间复杂度：O(h) 树的深度 - 95.35%
     * 优点：
     * 缺点：
     *
     * @param start
     * @param end
     * @param bank
     * @return
     */
    public int minMutation1(String start, String end, String[] bank) {
        if (start.equals(end)) return 0;
        dfs1(start, end, bank, 0, new HashSet<>());
        return count1 == Integer.MAX_VALUE ? -1 : count1; // 这个很重要
    }

    private int count1 = Integer.MAX_VALUE; // 这个初始化的值很重要，会影响结果的对错。

    private void dfs1(String start, String end, String[] bank, int num, HashSet<String> visited) {
        if (start.equals(end)) {
            count1 = Math.min(count1, num);
            return;
        }
        int dif;
        for (int a = 0; a < bank.length; a++) { // 遍历基因库
            if (visited.contains(bank[a])) continue;
            dif = 0;
            for (int b = 0; b < 8; b++) {
                if (bank[a].charAt(b) != start.charAt(b)) dif++; // 这里比较的是start，切记！！！ // 误写成相等了，小地方多留意，还好可以测试。
                if (dif > 1) break;
            }
            if (dif == 1) {
                visited.add(bank[a]);
                dfs1(bank[a], end, bank, num + 1, visited);
                visited.remove(bank[a]);
            }
        }
    }

    /**
     * @param start
     * @param end
     * @param bank
     * @return
     */
    public int minMutation0(String start, String end, String[] bank) {
        trans0 = new boolean[bank.length];
        count0 = Integer.MAX_VALUE;
        boolean contain = false;
        for (String s : bank) {
            if (s.equals(end)) {
                contain = true;
                break;
            }
        }
        if (!contain) return -1;
        backtrack(start, end, bank, trans0, 0);
        return count0 == Integer.MAX_VALUE ? -1 : count0;
    }

    boolean[] trans0;
    int count0;

    public void backtrack(String s, String e, String[] bank, boolean[] trans, int times) {
        if (s.equals(e)) {
            count0 = Math.min(times, count0);
            return;
        }
        int dif;
        int len = s.length();
        for (int i = 0; i < bank.length; ++i) {
            if (trans[i] == true) continue;
            dif = 0;
            for (int j = 0; j < len; ++j) {
                if (dif > 1) break;
                if (s.charAt(j) == bank[i].charAt(j)) continue;
                dif++;
            }
            if (dif == 1) {
                trans[i] = true;
                backtrack(bank[i], e, bank, trans, times + 1);
                trans[i] = false;
            }
        }
        return;
    }

    /***
     * 回溯 + BFS - Queue(LinkedList)
     * 时间复杂度：O() - 100%
     * 空间复杂度：O() - 72.17%
     * 优点：
     * 缺点：
     *
     * @param start
     * @param end
     * @param bank
     * @return
     */
    public int minMutation3(String start, String end, String[] bank) {
        int result3 = 0, index = 1, level = 1;
        HashSet<String> visited = new HashSet<>();
        LinkedList<String> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            start = queue.poll();
            bfs3(start, queue, visited, bank);
            for (int i = 0; i < queue.size(); i++) {
                if (queue.get(i).equals(end)) {//因为linkedList支持按照index获取元素，所以此处不使用queue或者deque
                    result3 = level;
                    return result3 == 0 ? -1 : result3;
                }
            }
            if (--index == 0) {
                index = queue.size();
                level++;
            }
        }
        return result3 == 0 ? -1 : result3;
    }

    private void bfs3(String start, LinkedList<String> linkedList, HashSet<String> visited, String[] bank) {
        for (int i = 0; i < bank.length; i++) {
            if (visited.contains(bank[i])) continue;
            if (match3(bank[i], start)) {
                linkedList.add(bank[i]);
                visited.add(bank[i]);
            }
        }
    }

    private boolean match3(String a, String b) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            if (a.charAt(i) != b.charAt(i)) count++;
            if (count > 1) return false;
        }
        return count == 1;
    }


    /**
     * DFS + 回溯
     * 时间复杂度：O(n) - 100%
     * 空间复杂度：O(h) 树的深度 - 14.09%
     * 优点：代码更简洁
     * 缺点：
     *
     * @param start
     * @param end
     * @param bank
     * @return
     */
    public int minMutation2(String start, String end, String[] bank) {
        HashSet<String> visited = new HashSet<>();
        dfs2(start, end, bank, visited, 0);
        return count2 == Integer.MAX_VALUE ? -1 : count2;
    }

    private int count2 = Integer.MAX_VALUE;

    private void dfs2(String start, String end, String[] bank, HashSet<String> visited, int count) {
        if (start.equals(end)) {                                    // terminator
            count2 = Math.min(count2, count);
            return;
        }
        for (int i = 0; i < bank.length; i++) {                     // process
            if (match2(bank[i], start) && !visited.contains(bank[i])) {
                visited.add(bank[i]);                                // subprocess
                dfs2(bank[i], end, bank, visited, count + 1); // drill down
                visited.remove(bank[i]);                             // reverse state
            }
        }
    }

    private boolean match2(String a, String b) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            if (a.charAt(i) != b.charAt(i)) count++;
            if (count > 1) return false;
        }
        return count == 1;
    }


}
