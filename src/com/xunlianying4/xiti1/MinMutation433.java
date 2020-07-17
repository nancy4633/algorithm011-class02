package com.xunlianying4.xiti1;

import java.util.HashSet;
import java.util.LinkedList;

// 一条基因序列由一个带有8个字符的字符串表示，其中每个字符都属于 "A", "C", "G", "T"中的任意一个。
// 假设我们要调查一个基因序列的变化。一次基因变化意味着这个基因序列中的一个字符发生了变化。
// 例如，基因序列由"AACCGGTT" 变化至 "AACCGGTA" 即发生了一次基因变化。
// ***与此同时，每一次基因变化的结果，都需要是一个合法的基因串，即该结果属于一个基因库。
// 现在给定3个参数 — start, end, bank，分别代表起始基因序列，目标基因序列及基因库，
// ***请找出能够使起始基因序列变化为目标基因序列所需的最少变化次数。如果无法实现目标变化，请返回 -1
// 思路：
// 回溯 -
// 动态规划 -
// 贪心算法 - 容易找不到最优解
public class MinMutation433 {

    /***
     * DFS
     * 二刷，艰难，还不是自己写出来的，第一遍相当于白做了，气个半死。
     *
     * @param start
     * @param end
     * @param bank
     * @return
     */
    public int minMutation2(String start, String end, String[] bank) {
        HashSet<String> visited = new HashSet<>();
        dfs2(0, visited, start, end, bank);
        // 这个要好好记住，比写公式简单多了
        return count2 == Integer.MAX_VALUE ? -1 : count2;
    }

    int count2 = Integer.MAX_VALUE;

    public void dfs2(int minStep, HashSet<String> visited, String start, String end, String[] bank) {
        if (start.equals(end)) {
            // 忘记赋值了，整个过程就为了count2。结果我忘写了，真尴尬。
            count2 = Math.min(count2, minStep);
            return;
        }
        for (int i = 0; i < bank.length; i++) {
            int diff = 0;
            for (int j = 0; j < 8; j++) {
                // 虽然很简单，还是不回写charat，我去，我自己真想不出来写charat，缺点儿化学反应。
                // 自己写的时候总觉得，写一个diff变量，只判断0-1太傻了，其实傻就傻吧，大牛也这么写怕啥丢人呢。
                if (bank[i].charAt(j) != start.charAt(j)) diff++;
                // break写成return了，多加注意。
                if (diff > 1) break;
            }
            if (diff == 1 && !visited.contains(bank[i])) {
                visited.add(bank[i]);
                // start参数传错了，下次注意。
                dfs2(minStep + 1, visited, bank[i], end, bank);
                // 没remove是为啥呢？我就没想起来。
                visited.remove(bank[i]);
            }
        }
    }

    /***
     * BFS
     *
     * @param start
     * @param end
     * @param bank
     * @return
     */
    public int minMutation22(String start, String end, String[] bank) {
        HashSet<String> bank_library = new HashSet<>();
        for (String temp : bank) {
            bank_library.add(temp);
        }
        char[] chars = {'A', 'C', 'G', 'T'};
        LinkedList<String> deque = new LinkedList<>();
        deque.offer(start);
        int count = 0;
        HashSet<String> visited = new HashSet<>();
        while (!deque.isEmpty()) {
            int size = deque.size();
            while (size-- > 0) {
                String poll = deque.poll();
                if (poll.equals(end)) return count;
                char[] curr = poll.toCharArray();
                for (int i = 0; i < curr.length; i++) {
                    char old = curr[i];
                    for (char b : chars) {
                        curr[i] = b;
                        String newString = new String(curr);
                        if (!visited.contains(newString) && bank_library.contains(newString)) {
                            visited.add(newString);
                            deque.offer(newString);
                        }
                    }
                    curr[i] = old;
                }
            }
            count++;
        }
        // 这里写错了，应该是-1。。。
        return -1;
    }

    /***
     * DFS + 回溯
     * 第一快
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(h) 树的深度
     *
     * @param start
     * @param end
     * @param bank
     * @return
     */
    public int minMutation1(String start, String end, String[] bank) {
        HashSet<String> vistied = new HashSet<>();
        dfs1(0, start, end, bank, vistied);
        return count1 == Integer.MAX_VALUE ? -1 : count1;
    }

    int count1 = Integer.MAX_VALUE;

    private void dfs1(int minStepCount, String start, String end, String[] bank, HashSet<String> vistied) {
        if (start.equals(end)) {
            count1 = Math.min(count1, minStepCount);
            return;
        }
        for (int i = 0; i < bank.length; i++) {
            int diff = 0;
            for (int j = 0; j < 8; j++) {
                if (bank[i].charAt(j) != start.charAt(j)) diff++;
                if (diff > 1) break;
            }
            if (diff == 1 && !vistied.contains(bank[i])) {
                vistied.add(bank[i]);
                dfs1(minStepCount + 1, bank[i], end, bank, vistied);
                vistied.remove(bank[i]);
            }
        }
    }

    /***
     * BFS
     * 第二快
     *
     * @param start
     * @param end
     * @param bank
     * @return
     */
    public int minMutation11(String start, String end, String[] bank) {
        HashSet<String> bank_library = new HashSet<>();
        for (String library : bank) {
            bank_library.add(library);
        }
        char[] banks = {'A', 'C', 'G', 'T'};
        LinkedList<String> dequeue = new LinkedList<>();
        dequeue.offer(start);
        int count = 0;
        HashSet<String> visited = new HashSet<>();
        while (!dequeue.isEmpty()) {
            int size = dequeue.size();
            while (size-- > 0) {
                String poll = dequeue.poll();
                if (poll.equals(end)) return count;
                char[] curr = poll.toCharArray();
                for (int i = 0; i < curr.length; i++) {
                    char old = curr[i];
                    for (char b : banks) {
                        curr[i] = b;
                        String newString = new String(curr);
                        if (!visited.contains(newString) && bank_library.contains(newString)) {
                            visited.add(newString);
                            dequeue.offer(newString);
                        }
                    }
                    curr[i] = old;
                }
            }
            count++;
        }
        return -1;
    }
}
