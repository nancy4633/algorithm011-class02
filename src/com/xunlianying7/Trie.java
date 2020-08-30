package com.xunlianying7;

// 第三遍
// 字典树 - Trie Tree
public class Trie {
    public boolean isEnd;
    public String val;
    public Trie[] next;

    public Trie() {
        isEnd = false;
        val = null;
        next = new Trie[26];

    }

    public void insert(String word) {
        if (word == null || word.length() == 0) return;
        char[] characters = word.toCharArray();
        Trie trie = this;
        for (int i = 0; i < characters.length; i++) {
            int number = characters[i] - 'a';
            if (trie.next[number] == null) trie.next[number] = new Trie();
            trie = trie.next[number];
        }
        trie.isEnd = true;
        trie.val = word;
    }

    public boolean search(String word) {
        Trie trie = searchPrefix(word);
        return trie != null && trie.isEnd;
    }

    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    public Trie searchPrefix(String prefix) {
        Trie trie = this;
        char[] characters = prefix.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            trie = trie.next[characters[i] - 'a'];
            if (trie == null) return null;
        }
        return trie;
    }
}
