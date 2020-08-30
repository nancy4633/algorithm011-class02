package com.xunlianying4;

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
public class LadderLength {

    /***
     * 双向广度优先遍历
     * 时间复杂度:O() - 97.23%
     * 空间复杂度:O() - 68.26%
     * 优点:
     * 缺点:
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength222(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (wordSet.size() == 0 || !wordSet.contains(endWord)) return 0;
        Set<String> visited = new HashSet<>(), begin_visited = new HashSet<>(), end_visited = new HashSet<>();
        begin_visited.add(beginWord);
        end_visited.add(endWord);
        int length = beginWord.length(), step = 1;
        while (!begin_visited.isEmpty() && !end_visited.isEmpty()) {
            if (begin_visited.size() > end_visited.size()) {
                Set<String> temp = begin_visited;
                begin_visited = end_visited;
                end_visited = temp;
            }
            Set<String> nextLevelVisited = new HashSet<>();
            for (String word : begin_visited) {
                char[] chars = word.toCharArray();
                for (int i = 0; i < length; i++) {
                    char originChar = chars[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == originChar) continue;
                        chars[i] = c;
                        String nextWord = String.valueOf(chars);
                        if (wordSet.contains(nextWord)) {
                            if (end_visited.contains(nextWord)) return step + 1;
                            if (!visited.contains(nextWord)) {
                                nextLevelVisited.add(nextWord);
                                visited.add(nextWord);
                            }
                        }
                    }
                    chars[i] = originChar;
                }
            }
            begin_visited = nextLevelVisited;
            step++;
        }
        return 0;
    }

    /***
     * DFS
     * 时间复杂度:O() - 77.78%
     * 空间复杂度:O() - 10.91%
     * 优点:
     * 缺点:
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
     * 双向广度+深度优先遍历
     * 使用了构造函数，构造函数看起来简单，但是一般情况下，我自己是想不到要写构造函数的，这点要记住，初始默认值之类的可以用构造函数。
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength22(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;
        this.length22 = beginWord.length();
        wordList.forEach(
                word -> {
                    for (int i = 0; i < length22; i++) {
                        String newWord = word.substring(0, i) + "*" + word.substring(i + 1, length22);
                        List<String> transformation = this.allDict22.getOrDefault(newWord, new ArrayList<>());
                        transformation.add(word);
                        allDict22.put(newWord, transformation);
                    }
                });
        Queue<Pair<String, Integer>> queue_begin = new LinkedList<>();
        Queue<Pair<String, Integer>> queue_end = new LinkedList<>();
        queue_begin.add(new Pair<>(beginWord, 1));
        queue_end.add(new Pair<>(endWord, 1));
        Map<String, Integer> visited_begin = new HashMap<>();
        Map<String, Integer> visited_end = new HashMap<>();
        visited_begin.put(beginWord, 1);
        visited_end.put(endWord, 1);
        while (!queue_begin.isEmpty() && !queue_end.isEmpty()) {
            int result = visitWordNode22(queue_begin, visited_begin, visited_end);
            if (result > -1) return result;
            result = visitWordNode22(queue_end, visited_begin, visited_end);
            if (result > -1) return result;
        }
        return 0;
    }

    int length22 = 0;
    private Map<String, List<String>> allDict22 = new HashMap<>();

    private int visitWordNode22(Queue<Pair<String, Integer>> queue, Map<String, Integer> visited, Map<String, Integer> other_visited) {
        Pair<String, Integer> node = queue.remove();
        String word = node.getKey();
        int level = node.getValue();
        for (int i = 0; i < length22; i++) {
            String newWord = word.substring(0, i) + "*" + word.substring(i + 1, length22);
            for (String adjacentWord : allDict22.getOrDefault(newWord, new ArrayList<>())) {
                if (other_visited.containsKey(adjacentWord)) return level + other_visited.get(adjacentWord);
                if (!visited.containsKey(adjacentWord)) {
                    visited.put(adjacentWord, level + 1);
                    queue.add(new Pair<>(adjacentWord, level + 1));
                }
            }
        }
        return -1;
    }

    /***
     * DFS
     * 时间复杂度:O() - 77.78%
     * 空间复杂度:O() - 5.13%
     * 优点:
     * 缺点:
     * 第三快
     * 时间复杂度：O(M×N)，其中 MM 是单词的长度 NN 是单词表中单词的总数。找到所有的变换需要对每个单词做 MM 次操作。同时，最坏情况下广度优先搜索也要访问所有的 NN 个单词。
     * 空间复杂度：O(M×N)，要在 all_combo_dict 字典中记录每个单词的 MM 个通用状态。访问数组的大小是 NN。广搜队列最坏情况下需要存储 NN 个单词。
     *
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
     * 双向 广度+深度 优先搜索
     * 第二快
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
        this.L11 = beginWord.length();
        wordList.forEach(
                word -> {
                    for (int i = 0; i < L11; i++) {
                        String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L11);
                        List<String> transformations = this.allComboDict11.getOrDefault(newWord, new ArrayList<>());
                        transformations.add(word);
                        this.allComboDict11.put(newWord, transformations);
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
            int ans = visitWordNode11(Q_begin, visitedBegin, visitedEnd);
            if (ans > -1) return ans;
            ans = visitWordNode11(Q_end, visitedEnd, visitedBegin);
            if (ans > -1) return ans;
        }
        return 0;
    }

    private int L11;
    private Map<String, List<String>> allComboDict11;

    LadderLength() {
        this.L11 = 0;
        this.allComboDict11 = new HashMap<>();
        this.length22 = 0;
        this.allDict22 = new HashMap<>();
    }

    private int visitWordNode11(Queue<Pair<String, Integer>> Q, Map<String, Integer> visited, Map<String, Integer> othersVisited) {
        Pair<String, Integer> node = Q.remove();
        String word = node.getKey();
        int level = node.getValue();
        for (int i = 0; i < this.L11; i++) {
            String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L11);
            for (String adjacentWord : this.allComboDict11.getOrDefault(newWord, new ArrayList<>())) {
                if (othersVisited.containsKey(adjacentWord)) return level + othersVisited.get(adjacentWord);
                if (!visited.containsKey(adjacentWord)) {
                    visited.put(adjacentWord, level + 1);
                    Q.add(new Pair(adjacentWord, level + 1));
                }
            }
        }
        return -1;
    }

    /***
     * 双向广度优先遍历
     * 第一快
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength111(String beginWord, String endWord, List<String> wordList) {
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

    /***
     * 双向BFS
     * 第一快
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength1111(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> start = new HashSet<>(), end = new HashSet<>(), words = new HashSet<>(wordList);
        start.add(beginWord);
        end.add(endWord);
        if (!words.contains(endWord)) return 0;
        return deBfs(start, end, words, 2);
    }

    private int deBfs(HashSet<String> start, HashSet<String> end, HashSet<String> words, int depth) {
        if (start.size() > end.size()) return deBfs(end, start, words, depth);
        words.removeAll(start);
        HashSet<String> next = new HashSet<>();
        for (String str : start) {
            char[] chars = str.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char temp = chars[i];
                for (char j = 'a'; j <= 'z'; j++) {
                    chars[i] = j;
                    String word = new String(chars);
                    if (words.contains(word)) {
                        if (end.contains(word)) return depth;
                        next.add(word);
                    }
                }
                chars[i] = temp;
            }
        }
        if (start.isEmpty()) return 0;
        return deBfs(next, end, words, depth + 1);
    }
}
