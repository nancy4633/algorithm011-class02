package com.xunlianying4.zuoye1;

import javafx.util.Pair;

import java.util.*;

// 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
// 每次转换只能改变一个字母。转换过程中的中间单词必须是字典中的单词。
// 说明:
// 如果不存在这样的转换序列，返回 0。
// ***所有单词具有相同的长度。
// 所有单词只由小写字母组成。
// 字典中不存在重复的单词。
// 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
// 思路：
// 跟最少基因变化，是同一个解法
public class LadderLength127 {

    /***
     * DFS
     * 第二刷
     * 跟基因变化是一类题，两类题都不很熟悉，二刷都没有独立写下来。确切的说，二刷是用来背题解的。努力！加油！有点儿泄气。
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        int length = beginWord.length();
        Map<String, List<String>> allDict = new HashMap<>();
        wordList.forEach(
                word -> {
                    for (int i = 0; i < length; i++) {
                        String newWord = word.substring(0, i) + "*" + word.substring(i + 1, length);
                        List<String> transformations = allDict.getOrDefault(newWord, new ArrayList<>());
                        transformations.add(word);
                        allDict.put(newWord, transformations);
                    }
                });
        Queue<Pair<String, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(beginWord, 1));
        Map<String, Boolean> visited = new HashMap<String, Boolean>();
        visited.put(beginWord, true);
        while (!queue.isEmpty()) {
            Pair<String, Integer> node = queue.remove();// return the head of queue.
            String word = node.getKey();
            int level = node.getValue();
            for (int i = 0; i < length; i++) {
                String newWord = word.substring(0, i) + "*" + word.substring(i + 1, length);
                for (String adjacentWord : allDict.getOrDefault(newWord, new ArrayList<>())) {
                    if (adjacentWord.equals(endWord)) return level + 1;
                    if (!visited.containsKey(adjacentWord)) {
                        visited.put(adjacentWord, true);
                        queue.add(new Pair<>(adjacentWord, level + 1));
                    }
                }
            }
        }
        return 0;
    }

    /***
     * 双向
     * 第二刷
     *
     *
     *
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength22(String beginWord, String endWord, List<String> wordList) {

        return 0;
    }


    /***
     * DFS
     * 第二快
     * 时间复杂度：O(M×N)，其中 MM 是单词的长度 NN 是单词表中单词的总数。找到所有的变换需要对每个单词做 MM 次操作。同时，最坏情况下广度优先搜索也要访问所有的 NN 个单词。
     * 空间复杂度：O(M×N)，要在 all_combo_dict 字典中记录每个单词的 MM 个通用状态。访问数组的大小是 NN。广搜队列最坏情况下需要存储 NN 个单词。
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        int L = beginWord.length();
        Map<String, List<String>> allComboDict = new HashMap<>();
        wordList.forEach(
                word -> {
                    for (int i = 0; i < L; i++) {
                        String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
                        List<String> transformations = allComboDict.getOrDefault(newWord, new ArrayList<>());
                        transformations.add(word);
                        allComboDict.put(newWord, transformations);
                    }
                });
        Queue<Pair<String, Integer>> Q = new LinkedList<>();
        Q.add(new Pair(beginWord, 1));
        Map<String, Boolean> visited = new HashMap<>();
        visited.put(beginWord, true);
        while (!Q.isEmpty()) {
            Pair<String, Integer> node = Q.remove();
            String word = node.getKey();
            int level = node.getValue();
            for (int i = 0; i < L; i++) {
                String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
                for (String adjacentWord : allComboDict.getOrDefault(newWord, new ArrayList<>())) {
                    if (adjacentWord.equals(endWord)) return level + 1;
                    if (!visited.containsKey(adjacentWord)) {
                        visited.put(adjacentWord, true);
                        Q.add(new Pair(adjacentWord, level + 1));
                    }
                }
            }
        }
        return 0;
    }

    /***
     * 双向广度优先搜索
     * 第一快
     *
     * 时间复杂度：O(M×N)，其中 MM 是单词的长度 NN 是单词表中单词的总数。与单向搜索相同的是，找到所有的变换需要M * N次操作。但是搜索时间会被缩小一半，因为两个搜索会在中间某处相遇。
     * 空间复杂度：O(M×N)，要在 all_combo_dict 字典中记录每个单词的 MM 个通用状态，这与单向搜索相同。但是因为会在中间相遇，所以双向搜索的搜索空间变小。
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength11(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;
        this.L = beginWord.length();
        wordList.forEach(
                word -> {
                    for (int i = 0; i < L; i++) {
                        String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
                        List<String> transformations = this.allComboDict.getOrDefault(newWord, new ArrayList<>());
                        transformations.add(word);
                        this.allComboDict.put(newWord, transformations);
                    }
                });
        Queue<Pair<String, Integer>> Q_begin = new LinkedList<>();
        Queue<Pair<String, Integer>> Q_end = new LinkedList<>();
        Q_begin.add(new Pair(beginWord, 1));
        Q_end.add(new Pair(endWord, 1));
        Map<String, Integer> visitedBegin = new HashMap<>();
        Map<String, Integer> visitedEnd = new HashMap<>();
        visitedBegin.put(beginWord, 1);
        visitedEnd.put(endWord, 1);
        while (!Q_begin.isEmpty() && !Q_end.isEmpty()) {
            int ans = visitWordNode(Q_begin, visitedBegin, visitedEnd);
            if (ans > -1) return ans;
            ans = visitWordNode(Q_end, visitedEnd, visitedBegin);
            if (ans > -1) return ans;
        }
        return 0;
    }

    private int L;
    private Map<String, List<String>> allComboDict;

    LadderLength127() {
        this.L = 0;
        this.allComboDict = new HashMap<>();
    }

    private int visitWordNode(Queue<Pair<String, Integer>> Q, Map<String, Integer> visited, Map<String, Integer> othersVisited) {
        Pair<String, Integer> node = Q.remove();
        String word = node.getKey();
        int level = node.getValue();
        for (int i = 0; i < this.L; i++) {
            String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
            for (String adjacentWord : this.allComboDict.getOrDefault(newWord, new ArrayList<>())) {
                if (othersVisited.containsKey(adjacentWord)) return level + othersVisited.get(adjacentWord);
                if (!visited.containsKey(adjacentWord)) {
                    visited.put(adjacentWord, level + 1);
                    Q.add(new Pair(adjacentWord, level + 1));
                }
            }
        }
        return -1;
    }

}
