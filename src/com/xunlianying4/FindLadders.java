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
public class FindLadders {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> results = new ArrayList<>();
        HashSet<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return results;
        HashSet<String> beginSet = new HashSet<>(), endSet = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);
        HashMap<String, List<String>> neighborsMap = new HashMap<>();
        if (!doubledfs(wordSet, beginSet, endSet, neighborsMap, true)) return results;
        dfs();
        return results;
    }


    private boolean doubledfs(Set<String> wordSet, Set<String> beginSet, Set<String> endSet
            , Map<String, List<String>> neighborsMap, boolean isfrombegintoend) {
        if

        return false;
    }

    private void dfs() {
    }
}
