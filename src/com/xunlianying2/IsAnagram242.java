package com.xunlianying2;

import java.util.Arrays;

// 第一遍
// 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
public class IsAnagram242 {

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
    public static boolean isAnagram1(String s, String t) {
        if (s == null && t == null) return true;
        if ((s == null) & (t == null) == false) return false;
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

    /**
     * hash
     * 时间复杂度:O(n) - 49.73%
     * 空间复杂度:O(1) - 28.28%
     * 优点:
     * 缺点:
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram11(String s, String t) {
        if (s == null && t == null) return true;
        if ((s == null) & (t == null) == false) return false;
        if (s.length() != t.length()) return false;
        int[] sArray = new int[26];
        for (int i = 0; i < s.length(); i++) {
            sArray[s.charAt(i) - 'a']++;
            sArray[t.charAt(i) - 'a']--;
        }
        for (int i : sArray) {
            if (i != 0) return false;
        }
        return true;
    }

    /**
     * 优化的hash
     * 时间复杂度:O(n) - 64.47%
     * 空间复杂度:O(1) - 85.17%
     * 优点:
     * 缺点:
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram111(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] table = new int[26];
        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            table[t.charAt(i) - 'a']--;
            if (table[t.charAt(i) - 'a'] < 0) return false;
        }
        return true;
    }
}
