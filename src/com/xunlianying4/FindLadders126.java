package com.xunlianying4;

import java.util.*;

// 第二遍
// 给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。转换需遵循如下规则：
// 每次转换只能改变一个字母。
// 转换后得到的单词必须是字典中的单词。
// 说明:
// 如果不存在这样的转换序列，返回一个空列表。
// 所有单词具有相同的长度。
// 所有单词只由小写字母组成。
// 字典中不存在重复的单词。
// 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
public class FindLadders126 {
    /**
     * 先双向bfs，再dfs
     * 时间复杂度:O() - 99.78%
     * 空间复杂度:O() - 98.59%
     * 优点:
     * 缺点:
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLadders1(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> results = new ArrayList<>();
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return results;
        Set<String> beginSet = new HashSet<>(), endSet = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);
        Map<String, List<String>> neighborsMap = new HashMap<>(); // 这是做什么用的？？？
        if (!doubleBfs1(wordSet, beginSet, endSet, neighborsMap, true)) return results; // 不包含
        dfs1(neighborsMap, results, beginWord, endWord, new LinkedList<>());
        return results;
    }

    private boolean doubleBfs1(Set<String> wordSet, Set<String> beginSet, Set<String> endSet, Map<String, List<String>> neighborsMap, boolean isFromBeginToEnd) {
        if (beginSet.size() == 0) return false;
        wordSet.removeAll(beginSet);
        boolean flag = false;
        Set<String> newBeginSet = new HashSet<>();
        for (String begin : beginSet) {
            char[] chars = begin.toCharArray();
            for (int a = 0; a < chars.length; a++) {
                char temp = chars[a];
                for (char b = 'a'; b <= 'z'; b++) {
                    chars[a] = b;
                    String newWord = new String(chars);
                    if (!wordSet.contains(newWord)) continue;
                    newBeginSet.add(newWord);
                    String key = isFromBeginToEnd ? begin : newWord;
                    String neighbor = isFromBeginToEnd ? newWord : begin;
                    if (!neighborsMap.containsKey(key)) neighborsMap.put(key, new ArrayList<>());
                    neighborsMap.get(key).add(neighbor);
                    if (endSet.contains(newWord)) flag = true;
                }
                chars[a] = temp;
            }
        }
        if (flag) return true;
        if (newBeginSet.size() > endSet.size())
            return doubleBfs1(wordSet, endSet, newBeginSet, neighborsMap, !isFromBeginToEnd);
        return doubleBfs1(wordSet, newBeginSet, endSet, neighborsMap, isFromBeginToEnd);
    }

    private void dfs1(Map<String, List<String>> neighborsMap, List<List<String>> results, String begin, String end, Deque<String> path) {
        if (results.size() > 0 && path.size() == 0) return;
        path.addLast(begin); // add last 添加到路径末尾
        if (begin.equals(end)) {
            results.add(new ArrayList<>(path)); // 避免引用问题
        } else if (neighborsMap.containsKey(begin)) { // 有相邻词
            for (String neighbor : neighborsMap.get(begin)) {
                dfs1(neighborsMap, results, neighbor, end, path);
            }
        }
        path.removeLast(); // 退一步，以便找另一条路径
    }

    /**
     * 双向BFS + DFS
     * 时间复杂度:O() - 98.73%
     * 空间复杂度:O() - 88.56%
     * 优点:
     * 缺点:
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLadders5(String beginWord, String endWord, List<String> wordList) {
        // 结果集
        List<List<String>> res = new ArrayList<>();
        Set<String> words = new HashSet<>(wordList);
        // 字典中不包含目标单词
        if (!words.contains(endWord)) {
            return res;
        }
        // 存放关系：每个单词可达的下层单词
        Map<String, List<String>> mapTree = new HashMap<>();
        Set<String> begin = new HashSet<>(), end = new HashSet<>();
        begin.add(beginWord);
        end.add(endWord);
        if (buildTree5(words, begin, end, mapTree, true)) {
            dfs5(res, mapTree, beginWord, endWord, new LinkedList<>());
        }
        return res;
    }

    // 双向BFS，构建每个单词的层级对应关系
    private boolean buildTree5(Set<String> words, Set<String> begin, Set<String> end, Map<String, List<String>> mapTree, boolean isFront) {
        if (begin.size() == 0) {
            return false;
        }
        // 始终以少的进行探索
        if (begin.size() > end.size()) {
            return buildTree5(words, end, begin, mapTree, !isFront);
        }
        // 在已访问的单词集合中去除
        words.removeAll(begin);
        // 标记本层是否已到达目标单词
        boolean isMeet = false;
        // 记录本层所访问的单词
        Set<String> nextLevel = new HashSet<>();
        for (String word : begin) {
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char temp = chars[i];
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    chars[i] = ch;
                    String str = String.valueOf(chars);
                    if (words.contains(str)) {
                        nextLevel.add(str);
                        // 根据访问顺序，添加层级对应关系：始终保持从上层到下层的存储存储关系
                        // true: 从上往下探索：word -> str
                        // false: 从下往上探索：str -> word（查找到的 str 是 word 上层的单词）
                        String key = isFront ? word : str;
                        String nextWord = isFront ? str : word;
                        // 判断是否遇见目标单词
                        if (end.contains(str)) {
                            isMeet = true;
                        }
                        if (!mapTree.containsKey(key)) {
                            mapTree.put(key, new ArrayList<>());
                        }
                        mapTree.get(key).add(nextWord);
                    }
                }
                chars[i] = temp;
            }
        }
        if (isMeet) {
            return true;
        }
        return buildTree5(words, nextLevel, end, mapTree, isFront);
    }

    // DFS: 组合路径
    private void dfs5(List<List<String>> res, Map<String, List<String>> mapTree, String beginWord, String endWord, LinkedList<String> list) {
        list.add(beginWord);
        if (beginWord.equals(endWord)) {
            res.add(new ArrayList<>(list));
            list.removeLast();
            return;
        }
        if (mapTree.containsKey(beginWord)) {
            for (String word : mapTree.get(beginWord)) {
                dfs5(res, mapTree, word, endWord, list);
            }
        }
        list.removeLast();
    }

    /**
     * 双向广度优先遍历
     * 时间复杂度:O() - 95.92%
     * 空间复杂度:O() - 85.17%
     * 优点:
     * 缺点:
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLadders6(String beginWord, String endWord, List<String> wordList) {
        // 先将 wordList 放到哈希表里，便于判断某个单词是否在 wordList 里
        List<List<String>> res = new ArrayList<>();
        Set<String> wordSet = new HashSet<>(wordList);
        if (wordSet.size() == 0 || !wordSet.contains(endWord)) {
            return res;
        }
        // 第 1 步：使用双向广度优先遍历得到后继结点列表 successors
        // key：字符串，value：广度优先遍历过程中 key 的后继结点列表
        Map<String, Set<String>> successors = new HashMap<>();
        boolean found = bidirectionalBfs6(beginWord, endWord, wordSet, successors);
        if (!found) {
            return res;
        }
        // 第 2 步：基于后继结点列表 successors ，使用回溯算法得到所有最短路径列表
        Deque<String> path = new ArrayDeque<>();
        path.addLast(beginWord);
        dfs6(beginWord, endWord, successors, path, res);
        return res;
    }

    private boolean bidirectionalBfs6(String beginWord,
                                      String endWord,
                                      Set<String> wordSet,
                                      Map<String, Set<String>> successors) {
        // 记录访问过的单词
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        visited.add(endWord);

        Set<String> beginVisited = new HashSet<>();
        beginVisited.add(beginWord);
        Set<String> endVisited = new HashSet<>();
        endVisited.add(endWord);

        int wordLen = beginWord.length();
        boolean forward = true;
        boolean found = false;
        // 在保证了 beginVisited 总是较小（可以等于）大小的集合前提下，&& !endVisited.isEmpty() 可以省略
        while (!beginVisited.isEmpty() && !endVisited.isEmpty()) {
            // 一直保证 beginVisited 是相对较小的集合，方便后续编码
            if (beginVisited.size() > endVisited.size()) {
                Set<String> temp = beginVisited;
                beginVisited = endVisited;
                endVisited = temp;

                // 只要交换，就更改方向，以便维护 successors 的定义
                forward = !forward;
            }
            Set<String> nextLevelVisited = new HashSet<>();
            // 默认 beginVisited 是小集合，因此从 beginVisited 出发
            for (String currentWord : beginVisited) {
                char[] charArray = currentWord.toCharArray();
                for (int i = 0; i < wordLen; i++) {
                    char originChar = charArray[i];
                    for (char j = 'a'; j <= 'z'; j++) {
                        if (charArray[i] == j) {
                            continue;
                        }
                        charArray[i] = j;
                        String nextWord = new String(charArray);
                        if (wordSet.contains(nextWord)) {
                            if (endVisited.contains(nextWord)) {
                                found = true;
                                // 在另一侧找到单词以后，还需把这一层关系添加到「后继结点列表」
                                addToSuccessors6(successors, forward, currentWord, nextWord);
                            }

                            if (!visited.contains(nextWord)) {
                                nextLevelVisited.add(nextWord);
                                addToSuccessors6(successors, forward, currentWord, nextWord);
                            }
                        }
                    }
                    charArray[i] = originChar;
                }
            }
            beginVisited = nextLevelVisited;
            visited.addAll(nextLevelVisited);
            if (found) break;
        }
        return found;
    }

    private void dfs6(String beginWord, String endWord, Map<String, Set<String>> successors, Deque<String> path, List<List<String>> res) {
        if (beginWord.equals(endWord)) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (!successors.containsKey(beginWord)) return;
        Set<String> successorWords = successors.get(beginWord);
        for (String successor : successorWords) {
            path.addLast(successor);
            dfs6(successor, endWord, successors, path, res);
            path.removeLast();
        }
    }

    private void addToSuccessors6(Map<String, Set<String>> successors, boolean forward, String currentWord, String nextWord) {
        if (!forward) {
            String temp = currentWord;
            currentWord = nextWord;
            nextWord = temp;
        }
        successors.computeIfAbsent(currentWord, a -> new HashSet<>());
        successors.get(currentWord).add(nextWord);
    }

    /**
     * 双向广度优先搜索
     * 时间复杂度:O() - 93.15%
     * 空间复杂度:O() - 92.66%
     * 优点:
     * 缺点:
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLadders7(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ans = new ArrayList<>();
        if (!wordList.contains(endWord)) return ans;
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        bfs7(beginWord, endWord, wordList, map);
        ArrayList<String> temp = new ArrayList<String>();
        temp.add(beginWord);
        findLaddersHelper7(beginWord, endWord, map, temp, ans);
        return ans;
    }

    private void findLaddersHelper7(String beginWord, String endWord, HashMap<String, ArrayList<String>> map,
                                    ArrayList<String> temp, List<List<String>> ans) {
        if (beginWord.equals(endWord)) {
            ans.add(new ArrayList<String>(temp));
            return;
        }
        ArrayList<String> neighbors = map.getOrDefault(beginWord, new ArrayList<String>());
        for (String neighbor : neighbors) {
            temp.add(neighbor);
            findLaddersHelper7(neighbor, endWord, map, temp, ans);
            temp.remove(temp.size() - 1);
        }
    }

    private void bfs7(String beginWord, String endWord, List<String> wordList, HashMap<String, ArrayList<String>> map) {
        Set<String> set1 = new HashSet<String>();
        set1.add(beginWord);
        Set<String> set2 = new HashSet<String>();
        set2.add(endWord);
        Set<String> wordSet = new HashSet<String>(wordList);
        bfsHelper7(set1, set2, wordSet, true, map);
    }

    private boolean bfsHelper7(Set<String> set1, Set<String> set2, Set<String> wordSet, boolean direction,
                               HashMap<String, ArrayList<String>> map) {
        if (set1.isEmpty()) return false;
        if (set1.size() > set2.size()) return bfsHelper7(set2, set1, wordSet, !direction, map);
        wordSet.removeAll(set1);
        wordSet.removeAll(set2);
        boolean done = false;
        Set<String> set = new HashSet<String>();
        for (String str : set1) {
            for (int i = 0; i < str.length(); i++) {
                char[] chars = str.toCharArray();
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    if (chars[i] == ch) continue;
                    chars[i] = ch;
                    String word = new String(chars);
                    String key = direction ? str : word;
                    String val = direction ? word : str;
                    ArrayList<String> list = map.containsKey(key) ? map.get(key) : new ArrayList<String>();
                    if (set2.contains(word)) {
                        done = true;
                        list.add(val);
                        map.put(key, list);
                    }
                    if (!done && wordSet.contains(word)) {
                        set.add(word);
                        list.add(val);
                        map.put(key, list);
                    }
                }
            }
        }
        return done || bfsHelper7(set2, set, wordSet, !direction, map);
    }

    /**
     * 回溯 + DFS
     * 时间复杂度:O() - 82.20%
     * 空间复杂度:O() - 42.67%
     * 优点:
     * 缺点:
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLadders8(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ans = new ArrayList<>();
        if (!wordList.contains(endWord)) return ans;
        HashMap<String, Integer> distance = new HashMap<>();
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        bfs8(beginWord, endWord, wordList, map, distance);
        ArrayList<String> temp = new ArrayList<String>();
        temp.add(beginWord);
        findLaddersHelper8(beginWord, endWord, map, distance, temp, ans);
        return ans;
    }

    private void findLaddersHelper8(String beginWord, String endWord, HashMap<String, ArrayList<String>> map,
                                    HashMap<String, Integer> distance, ArrayList<String> temp, List<List<String>> ans) {
        if (beginWord.equals(endWord)) {
            ans.add(new ArrayList<String>(temp));
            return;
        }
        ArrayList<String> neighbors = map.getOrDefault(beginWord, new ArrayList<String>());
        for (String neighbor : neighbors) {
            //判断层数是否符合
            if (distance.get(beginWord) + 1 == distance.get(neighbor)) {
                temp.add(neighbor);
                findLaddersHelper8(neighbor, endWord, map, distance, temp, ans);
                temp.remove(temp.size() - 1);
            }
        }
    }

    public void bfs8(String beginWord, String endWord, List<String> wordList, HashMap<String, ArrayList<String>> map, HashMap<String, Integer> distance) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        distance.put(beginWord, 0);
        boolean isFound = false;
        int depth = 0;
        Set<String> dict = new HashSet<>(wordList);
        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            for (int j = 0; j < size; j++) {
                String temp = queue.poll();
                ArrayList<String> neighbors = getNeighbors8(temp, dict);
                map.put(temp, neighbors);
                for (String neighbor : neighbors) {
                    if (!distance.containsKey(neighbor)) {
                        distance.put(neighbor, depth);
                        if (neighbor.equals(endWord)) isFound = true;
                        queue.offer(neighbor);
                    }
                }
            }
            if (isFound) break;
        }
    }

    private ArrayList<String> getNeighbors8(String node, Set<String> dict) {
        ArrayList<String> res = new ArrayList<String>();
        char chs[] = node.toCharArray();
        for (char ch = 'a'; ch <= 'z'; ch++) {
            for (int i = 0; i < chs.length; i++) {
                if (chs[i] == ch) continue;
                char old_ch = chs[i];
                chs[i] = ch;
                if (dict.contains(String.valueOf(chs))) res.add(String.valueOf(chs));
                chs[i] = old_ch;
            }
        }
        return res;
    }

    /**
     * BFS
     * 时间复杂度:O() - 37.64%
     * 空间复杂度:O() - 20.35%
     * 优点:
     * 缺点:
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLadders9(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ans = new ArrayList<>();
        if (!wordList.contains(endWord)) return ans;
        bfs9(beginWord, endWord, wordList, ans);
        return ans;
    }

    public void bfs9(String beginWord, String endWord, List<String> wordList, List<List<String>> ans) {
        Queue<List<String>> queue = new LinkedList<>();
        List<String> path = new ArrayList<>();
        path.add(beginWord);
        queue.offer(path);
        boolean isFound = false;
        Set<String> dict = new HashSet<>(wordList);
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Set<String> subVisited = new HashSet<>();
            for (int j = 0; j < size; j++) {
                List<String> p = queue.poll();
                String temp = p.get(p.size() - 1);
                ArrayList<String> neighbors = getNeighbors9(temp, dict);
                for (String neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        if (neighbor.equals(endWord)) {
                            isFound = true;
                            p.add(neighbor);
                            ans.add(new ArrayList<String>(p));
                            p.remove(p.size() - 1);
                        }
                        p.add(neighbor);
                        queue.offer(new ArrayList<String>(p));
                        p.remove(p.size() - 1);
                        subVisited.add(neighbor);
                    }
                }
            }
            visited.addAll(subVisited);
            if (isFound) break;
        }
    }

    private ArrayList<String> getNeighbors9(String node, Set<String> dict) {
        ArrayList<String> res = new ArrayList<String>();
        char chs[] = node.toCharArray();
        for (char ch = 'a'; ch <= 'z'; ch++) {
            for (int i = 0; i < chs.length; i++) {
                if (chs[i] == ch) continue;
                char old_ch = chs[i];
                chs[i] = ch;
                if (dict.contains(String.valueOf(chs))) res.add(String.valueOf(chs));
                chs[i] = old_ch;
            }
        }
        return res;
    }
}
