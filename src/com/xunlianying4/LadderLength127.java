package com.xunlianying4;

import javafx.util.Pair;

import java.util.*;

// 第二遍 wordSet为什么不是每一次都remove(报错)，非要一次removeall呢，
// 第二遍 原理搞清楚！！！再写一遍熟悉一下。然后再写别的。
// 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
// 每次转换只能改变一个字母。转换过程中的中间单词必须是字典中的单词。
// 说明:
// 如果不存在这样的转换序列，返回 0。
// 所有单词具有相同的长度。
// 所有单词只由小写字母组成。
// 字典中不存在重复的单词。
// 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
public class LadderLength127 {
    /**
     * 双向BFS
     * 时间复杂度:O() - 99.97%
     * 空间复杂度:O() - 79.49%
     * 优点:
     * 缺点:
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0;
        Set<String> beginSet = new HashSet<>(), endSet = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);
        return bfs1(wordSet, beginSet, endSet, 2);// 为什么默认是2
        //return bfs1(new HashSet<>(Collections.singleton(beginWord)), new HashSet<>(Collections.singleton(endWord)), wordSet, 2);
    }

    private int bfs1(Set<String> wordSet, Set<String> beginSet, Set<String> endSet, int depth) {
        // 这里交换完endSet和beginSet之后用的是，return！！！难道这里就是所说的双向？？？不是很理解双向在哪里？？？
        // 这里面的关键就是return，达到双向的目的！！！
        if (beginSet.size() > endSet.size()) return bfs1(endSet, beginSet, wordSet, depth);// 剪枝？？？但是还没弄明白双向在哪里？？？
        wordSet.removeAll(beginSet); // 切记！！！把字典中的begin全部删除（如果begin不包含在字典中其实也没关系）
        HashSet<String> newBeginSet = new HashSet<>(); // 切记！！！
        char[] chars;
        char temp;
        String newword;
        for (String begin : beginSet) { // 得到begin集合中的所有begin
            chars = begin.toCharArray();
            for (int a = 0; a < chars.length; a++) { // 从左开始遍历begin的每一个char
                temp = chars[a]; // 保留每一个原始char，后面回退用。
                for (char b = 'a'; b <= 'z'; b++) {
                    chars[a] = b; // 用26个字母分别替换begin中的字母。
                    newword = new String(chars);
                    if (wordSet.contains(newword)) { // 切记！！！
                        if (endSet.contains(newword)) return depth; // 切记！！！
                        newBeginSet.add(newword); // 新的beginSet中加入的是重新判断后跟begin只差一个char的所有词。
                    }
                }
                chars[a] = temp; // 回退成原始数组
            }
        }
        if (beginSet.isEmpty()) return 0; // 切记！！！
        return bfs1(wordSet, newBeginSet, endSet, depth + 1);
    }

    /**
     * 双向广度优先遍历
     * 时间复杂度:O() - 97.03%
     * 空间复杂度:O() - 76.82%
     * 优点:
     * 缺点:
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (wordSet.size() == 0 || !wordSet.contains(endWord)) return 0;
        Set<String> visited = new HashSet<>();
        Set<String> beginVisited = new HashSet<>();
        beginVisited.add(beginWord);
        Set<String> endVisited = new HashSet<>();
        endVisited.add(endWord);
        int len = beginWord.length();
        int step = 1;
        while (!beginVisited.isEmpty() && !endVisited.isEmpty()) {
            if (beginVisited.size() > endVisited.size()) {
                Set<String> temp = beginVisited;
                beginVisited = endVisited;
                endVisited = temp;
            }
            Set<String> nextLevelVisited = new HashSet<>();
            for (String word : beginVisited) {
                char[] charArray = word.toCharArray();
                for (int i = 0; i < len; i++) {
                    char originChar = charArray[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (originChar == c) continue;
                        charArray[i] = c;
                        String nextWord = String.valueOf(charArray);
                        if (wordSet.contains(nextWord)) {
                            if (endVisited.contains(nextWord)) return step + 1;
                            if (!visited.contains(nextWord)) {
                                nextLevelVisited.add(nextWord);
                                visited.add(nextWord);
                            }
                        }
                    }
                    charArray[i] = originChar;
                }
            }
            beginVisited = nextLevelVisited;
            step++;
        }
        return 0;
    }

    /**
     * 双向 广度+深度 优先搜索
     * 时间复杂度：O(M×N) - 84.29% - 其中M是单词的长度N是单词表中单词的总数。与单向搜索相同的是，找到所有的变换需要M*N次操作。但是搜索时间会被缩小一半，因为两个搜索会在中间某处相遇。
     * 空间复杂度：O(M×N) - 22.23% - 要在all_combo_dict字典中记录每个单词的M个通用状态，这与单向搜索相同。但是因为会在中间相遇，所以双向搜索的搜索空间变小。
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength3(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;
        this.allComboDict3 = new HashMap<>();
        this.L3 = beginWord.length();
        wordList.forEach(
                word -> {
                    for (int i = 0; i < L3; i++) {
                        String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L3);
                        List<String> transformations = this.allComboDict3.getOrDefault(newWord, new ArrayList<>());
                        transformations.add(word);
                        this.allComboDict3.put(newWord, transformations);
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
            int ans = visitWordNode3(Q_begin, visitedBegin, visitedEnd);
            if (ans > -1) return ans;
            ans = visitWordNode3(Q_end, visitedEnd, visitedBegin);
            if (ans > -1) return ans;
        }
        return 0;
    }

    private int L3;
    private Map<String, List<String>> allComboDict3;

    private int visitWordNode3(Queue<Pair<String, Integer>> Q, Map<String, Integer> visited, Map<String, Integer> othersVisited) {
        Pair<String, Integer> node = Q.remove();
        String word = node.getKey();
        int level = node.getValue();
        for (int i = 0; i < this.L3; i++) {
            String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L3);
            for (String adjacentWord : this.allComboDict3.getOrDefault(newWord, new ArrayList<>())) {
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
