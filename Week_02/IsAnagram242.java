package com.xunlianying2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 第一遍
// 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
public class IsAnagram242 {
    /**
     * 数组代替hashmap
     * 时间复杂度:O() - 99.89%
     * 空间复杂度:O() - 96.63%
     * 优点:
     * 缺点:
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram1(String s, String t) {
        int[] count1 = new int[26];
        for (char i : s.toCharArray())
            count1[i - 97] += 1; // count1[i - 97]++;
        for (char i : t.toCharArray())
            count1[i - 97] -= 1; // count1[i - 97]--;
        for (int i = 0; i < 26; i++) {
            if (count1[i] != 0) return false;
        }
        return true;
    }

    /**
     * 数组实现hash
     * 时间复杂度:O(n) - 86.56%
     * 空间复杂度:O(1) - 27.80%
     * 优点:
     * 缺点:
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram2(String s, String t) {
        int lens = s.length(), lent = t.length();
        if (lens != lent) return false;
        int[] table = new int[26];
        for (int i = 0; i < lens; i++) {
            table[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < lent; i++) {
            table[t.charAt(i) - 'a']--;
            if (table[t.charAt(i) - 'a'] < 0) return false;
        }
        return true;
    }

    /**
     * hashmap - 最慢，这道题不适合用hashmap
     * 时间复杂度:O() - 18.99%
     * 空间复杂度:O() - 9.19%
     * 优点:
     * 缺点:
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram3(String s, String t) {
        int lens = s.length(), lent = t.length();
        if (lens != lent) return false;
        Map<Character, Integer> charMap1 = new HashMap<>((int) (lens / 0.75F + 1.0F));
        Map<Character, Integer> charMap2 = new HashMap<>((int) (lent / 0.75F + 1.0F));
        for (char c : s.toCharArray())
            charMap1.put(c, charMap1.getOrDefault(c, 0) + 1);
        for (char c : t.toCharArray())
            charMap2.put(c, charMap2.getOrDefault(c, 0) + 1);
        if (charMap1.size() != charMap2.size()) return false;
        for (char c : s.toCharArray()) {
            if (!charMap1.get(c).equals(charMap2.getOrDefault(c, 0))) return false;
        }
        return true;
    }

    /**
     * 暴力求解：先排序，后对比是否相等。
     * 时间复杂度:O(nlogn) - 86.56% - 排序 - O(nlogn)，比较 - O(n)
     * 空间复杂度:O(1) - 95.34% - 空间取决于排序实现，如果使用 heapsort，通常需要O(1)。注意，在 Java 中，toCharArray() 制作了一个字符串的拷贝，所以它花费 O(n)额外的空间，但是我们忽略了这一复杂性分析，因为：这依赖于语言的细节。这取决于函数的设计方式。例如，可以将函数参数类型更改为 char[]。
     * 这个时间复杂度分析，比我想的要复杂，值得学习
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram4(String s, String t) {
        if (s.length() != t.length()) return false;
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        Arrays.sort(sArray);
        Arrays.sort(tArray);
        if (Arrays.equals(sArray, tArray)) {
            return true;
        } else {
            return false;
        }
    }
}
