package com.xunlianying7.zuoye1;

// 第五遍
// 实现字典树
public class Trie208 {
    public boolean isEnd;
    public String val;
    public Trie208[] next;

    /**
     * Initialize your data structure here.
     */
    public Trie208() {
        isEnd = false;
        val = null;
        next = new Trie208[26]; // 26个字母
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        if (word == null || word.length() == 0) return;
        Trie208 current = this;
        char[] characters = word.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            if (current.next[characters[i] - 'a'] == null) current.next[characters[i] - 'a'] = new Trie208();
            current = current.next[characters[i] - 'a'];
        }
        current.isEnd = true;
        current.val = word;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        Trie208 current = searchPrefix(word);
        return current != null && current.isEnd;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    public Trie208 searchPrefix(String prefix) {
        Trie208 current = this;
        char[] characters = prefix.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            if (current.next[characters[i] - 'a'] == null) return null;
            current = current.next[characters[i] - 'a'];
        }
        return current;
    }
}
